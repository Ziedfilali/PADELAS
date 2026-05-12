// Gesture controller driven by MediaPipe Hands (CDN-only, no installs).
// Injects a floating overlay + a cursor dot, and runs fully in-browser.
//
// Usage (browser-only):
//   import { attachGestureController } from '../utils/gesture-controller';
//   const ctrl = attachGestureController({ enabledByDefault: false });
//   // ...
//   ctrl.destroy();
//
// Notes:
// - Works via DOM injection (not React Native primitives).
// - Uses `history.back()` / `history.forward()` for navigation gestures.

/* eslint-disable no-use-before-define */

const OVERLAY_ID = 'gesture-control-overlay';
const CURSOR_ID = 'gesture-control-cursor-dot';
const HIDDEN_VIDEO_ID = 'gesture-control-hidden-video';
const SESSION_KEY = 'mp_gesture_control_enabled';
const SINGLETON_KEY = '__mpGestureControllerSingleton';

/** @deprecated use getAttachedGestureController() */
export const GESTURE_SINGLETON_KEY = SINGLETON_KEY;

/** Space above `bottom: 0` reserved for the fixed gesture dock (padding + preview + controls). Keep in sync with overlay layout below. */
export const GESTURE_CONTROL_OVERLAY_STACK_HEIGHT = 84;

export function getAttachedGestureController() {
  if (typeof window === 'undefined') return null;
  return window[SINGLETON_KEY] || null;
}

function clamp01(n) {
  return Math.min(1, Math.max(0, n));
}

function dist2(a, b) {
  const dx = a.x - b.x;
  const dy = a.y - b.y;
  return dx * dx + dy * dy;
}

function majorityTrue(frames, key, minCount) {
  let c = 0;
  for (const f of frames) if (f[key]) c += 1;
  return c >= minCount;
}

function safeNow() {
  return typeof performance !== 'undefined' ? performance.now() : Date.now();
}

function loadScriptOnce(src) {
  return new Promise((resolve, reject) => {
    const existing = document.querySelector(`script[data-mp-src="${src}"]`);
    if (existing) {
      // Already injected; resolve on next tick.
      resolve();
      return;
    }

    const s = document.createElement('script');
    s.src = src;
    s.async = true;
    s.crossOrigin = 'anonymous';
    s.setAttribute('data-mp-src', src);
    s.onload = () => resolve();
    s.onerror = () => reject(new Error(`Failed to load ${src}`));
    document.head.appendChild(s);
  });
}

async function loadMediaPipeFromCDN() {
  // Script-tag loading tends to work better with Expo/Metro on web.
  // These scripts expose globals on `window`.
  const handsSrc = 'https://cdn.jsdelivr.net/npm/@mediapipe/hands/hands.js';
  const cameraUtilsSrc = 'https://cdn.jsdelivr.net/npm/@mediapipe/camera_utils/camera_utils.js';
  const drawingUtilsSrc = 'https://cdn.jsdelivr.net/npm/@mediapipe/drawing_utils/drawing_utils.js';

  await loadScriptOnce(handsSrc);
  await loadScriptOnce(cameraUtilsSrc);
  await loadScriptOnce(drawingUtilsSrc);

  const Hands = window.Hands;
  const Camera = window.Camera;
  const drawConnectors = window.drawConnectors;
  const drawLandmarks = window.drawLandmarks;

  if (!Hands || !Camera) {
    throw new Error('MediaPipe globals not found (Hands/Camera).');
  }

  return {
    Hands,
    Camera,
    drawing: { drawConnectors, drawLandmarks },
  };
}

function createEl(tag, opts = {}) {
  const el = document.createElement(tag);
  if (opts.className) el.className = opts.className;
  if (opts.id) el.id = opts.id;
  if (opts.style) Object.assign(el.style, opts.style);
  if (opts.text != null) el.textContent = opts.text;
  return el;
}

function dispatchSyntheticClick(clientX, clientY) {
  const target = document.elementFromPoint(clientX, clientY);
  if (!target) return false;

  const init = {
    clientX,
    clientY,
    bubbles: true,
    cancelable: true,
    view: window,
    buttons: 1,
    button: 0,
  };

  const fireOn = (el, type) => {
    try {
      el.dispatchEvent(
        new PointerEvent(type, { ...init, pointerId: 1, pointerType: 'mouse', isPrimary: true })
      );
    } catch (e) {
      try {
        el.dispatchEvent(new MouseEvent(type, init));
      } catch (_) {}
    }
  };

  // React Native Web / Pressable often respond to native `.click()` on the DOM node.
  const chain = [];
  let n = target;
  for (let i = 0; i < 14 && n; i += 1) {
    chain.push(n);
    n = n.parentElement;
  }

  for (const el of chain) {
    if (el instanceof HTMLElement && typeof el.click === 'function') {
      try {
        el.click();
        return true;
      } catch (_) {}
    }
  }

  fireOn(target, 'pointerdown');
  fireOn(target, 'mousedown');
  fireOn(target, 'pointerup');
  fireOn(target, 'mouseup');
  fireOn(target, 'click');

  return true;
}

function findScrollableAncestorAt(clientX, clientY) {
  const start = document.elementFromPoint(clientX, clientY);
  if (!start) return document.scrollingElement || document.documentElement;
  let cur = start;
  for (let i = 0; i < 22 && cur; i += 1) {
    const st = window.getComputedStyle(cur);
    const oy = st.overflowY;
    const canY =
      (oy === 'auto' || oy === 'scroll' || oy === 'overlay') &&
      cur.scrollHeight > cur.clientHeight + 12;
    if (canY) return cur;
    cur = cur.parentElement;
  }
  return document.scrollingElement || document.documentElement;
}

/** Pick swipe direction from recent index-tip samples (more forgiving than single pair). */
function consumeSwipeDirection(samples, opts) {
  if (!samples || samples.length < opts.swipeMinSamples) return null;
  const newest = samples[samples.length - 1];
  const windowed = samples.filter((s) => newest.t - s.t <= opts.swipeTimeWindowMs);
  if (windowed.length < opts.swipeMinSamples) return null;
  const oldest = windowed[0];
  const last = windowed[windowed.length - 1];
  const dt = last.t - oldest.t;
  if (dt < opts.swipeMinDtMs || dt > opts.swipeTimeWindowMs) return null;
  const dx = last.x - oldest.x;
  const dy = last.y - oldest.y;
  const thr = opts.swipeDistanceThreshold;
  if (Math.abs(dy) >= Math.abs(dx) && Math.abs(dy) > thr) {
    if (dy < 0) return 'up';
    if (dy > 0) return 'down';
  }
  if (Math.abs(dx) > thr) {
    if (dx < 0) return 'left';
    if (dx > 0) return 'right';
  }
  return null;
}

export function attachGestureController(userOptions = {}) {
  if (typeof window === 'undefined' || typeof document === 'undefined') {
    return {
      destroy() {},
      setEnabled() {},
    };
  }

  if (window[SINGLETON_KEY]) {
    return window[SINGLETON_KEY];
  }

  const persistSession = Boolean(userOptions.persistSession);

  const options = {
    enabledByDefault: false,
    // Euclidean distance thumb–index in normalized space (easier than 0.05).
    pinchThreshold: 0.1,
    pinchConfirmFrames: 4,
    // X threshold for thumb "extended" heuristic.
    thumbXThreshold: 0.11,
    // How far movement must be for swipes (normalized).
    swipeDistanceThreshold: 0.07,
    swipeMinSamples: 5,
    swipeMinDtMs: 120,
    // Swipe direction must occur within this time window.
    swipeTimeWindowMs: 900,
    // Open palm cooldown.
    openPalmCooldownMs: 1500,
    // Global cooldown between triggers.
    globalCooldownMs: 650,
    // Pinch click debounce.
    pinchDebounceMs: 350,
    // Fist hold before triggering.
    fistHoldMs: 800,
    // Scroll amount factor for swipe up/down.
    scrollFactor: 0.6,
    // Max hands (for performance + stability).
    maxNumHands: 1,
    // Smooth detection: majority threshold in the last 10 frames.
    smoothMinCount: 5,
    debug: false,
    ...userOptions,
  };

  // Remove any existing overlay/cursor to avoid duplicates.
  const existing = document.getElementById(OVERLAY_ID);
  if (existing) existing.remove();
  const existingCursor = document.getElementById(CURSOR_ID);
  if (existingCursor) existingCursor.remove();
  const existingHiddenVideo = document.getElementById(HIDDEN_VIDEO_ID);
  if (existingHiddenVideo) existingHiddenVideo.remove();

  const overlay = createEl('div', {
    id: OVERLAY_ID,
    style: {
      position: 'fixed',
      right: '14px',
      bottom: '14px',
      zIndex: 999999,
      display: 'flex',
      alignItems: 'flex-end',
      gap: '10px',
      padding: '10px 10px',
      background: 'rgba(0,0,0,0.45)',
      border: '1px solid rgba(255,255,255,0.18)',
      borderRadius: '12px',
      backdropFilter: 'blur(10px)',
      WebkitBackdropFilter: 'blur(10px)',
      userSelect: 'none',
    },
  });

  // Cursor dot lives elsewhere so it overlays the whole viewport.
  const cursorDot = createEl('div', {
    id: CURSOR_ID,
    style: {
      position: 'fixed',
      width: '14px',
      height: '14px',
      borderRadius: '999px',
      background: 'rgba(0,255,102,0.95)',
      border: '2px solid rgba(255,255,255,0.85)',
      boxShadow: '0 0 0 3px rgba(0,255,102,0.15), 0 10px 30px rgba(0,0,0,0.35)',
      transform: 'translate(-50%, -50%) scale(0.9)',
      left: '-100px',
      top: '-100px',
      opacity: 0,
      pointerEvents: 'none',
      zIndex: 999998,
      transition: 'opacity 120ms ease',
    },
  });

  const previewVideo = createEl('video', {
    style: {
      width: '80px',
      height: '60px',
      borderRadius: '10px',
      overflow: 'hidden',
      background: 'rgba(255,255,255,0.06)',
      border: '1px solid rgba(255,255,255,0.15)',
      objectFit: 'cover',
      transform: 'scaleX(-1)',
      WebkitTransform: 'scaleX(-1)',
    },
  });
  previewVideo.muted = true;
  previewVideo.playsInline = true;
  previewVideo.autoplay = true;
  previewVideo.setAttribute('playsinline', '');
  previewVideo.setAttribute('webkit-playsinline', '');
  previewVideo.setAttribute('muted', '');

  const labelBlock = createEl('div', {
    style: {
      display: 'flex',
      flexDirection: 'column',
      gap: '6px',
      minWidth: '160px',
    },
  });

  const labelText = createEl('div', {
    style: {
      fontFamily: 'system-ui, -apple-system, Segoe UI, Roboto, Arial, sans-serif',
      fontSize: '12px',
      color: 'rgba(255,255,255,0.92)',
      letterSpacing: '0.2px',
      lineHeight: '14px',
      whiteSpace: 'nowrap',
      overflow: 'hidden',
      textOverflow: 'ellipsis',
      maxWidth: '220px',
    },
    text: 'gesture: —',
  });

  const pausedText = createEl('div', {
    style: {
      fontFamily: 'system-ui, -apple-system, Segoe UI, Roboto, Arial, sans-serif',
      fontSize: '11px',
      color: 'rgba(255,200,80,0.95)',
      display: 'none',
    },
    text: 'paused',
  });

  const toggleBtn = createEl('button', {
    style: {
      appearance: 'none',
      cursor: 'pointer',
      border: '1px solid rgba(255,255,255,0.22)',
      background: 'rgba(255,255,255,0.06)',
      color: 'rgba(255,255,255,0.92)',
      borderRadius: '10px',
      padding: '8px 10px',
      fontSize: '12px',
      fontFamily: 'system-ui, -apple-system, Segoe UI, Roboto, Arial, sans-serif',
    },
    text: options.enabledByDefault ? 'Gesture control: ON' : 'Gesture control: OFF',
  });

  const feedbackDot = createEl('div', {
    style: {
      width: '12px',
      height: '12px',
      borderRadius: '999px',
      background: 'rgba(255,255,255,0.25)',
      boxShadow: '0 0 0 3px rgba(255,255,255,0.05)',
      transition: 'background-color 100ms ease',
      marginBottom: '4px',
      pointerEvents: 'none',
    },
  });

  const column = createEl('div', {
    style: {
      display: 'flex',
      flexDirection: 'column',
      gap: '8px',
    },
  });

  column.appendChild(labelText);
  column.appendChild(pausedText);
  column.appendChild(toggleBtn);

  overlay.appendChild(previewVideo);
  overlay.appendChild(column);
  overlay.appendChild(feedbackDot);

  document.body.appendChild(overlay);
  document.body.appendChild(cursorDot);

  const hiddenVideo = createEl('video', {
    id: HIDDEN_VIDEO_ID,
    style: { display: 'none' },
  });
  hiddenVideo.muted = true;
  hiddenVideo.playsInline = true;
  hiddenVideo.autoplay = true;
  hiddenVideo.setAttribute('playsinline', '');
  hiddenVideo.setAttribute('webkit-playsinline', '');
  hiddenVideo.setAttribute('muted', '');
  document.body.appendChild(hiddenVideo);

  const dot = cursorDot;

  let enabled = Boolean(options.enabledByDefault);
  let hands = null;
  let Camera = null;
  let camera = null;
  let stream = null;
  /** Clone of `stream` for preview only — one MediaStream on two video elements often shows black. */
  let previewStream = null;
  let rafSendThrottle = 0;
  let cooldownUntil = 0;
  let recognitionPausedUntil = 0;
  let lastTriggerAt = -Infinity;
  let lastPinchClickAt = -Infinity;
  let state = 'IDLE';

  let frameBuffer = []; // last 10 frames of boolean features
  let swipeSamples = []; // index point positions in last 600ms
  let fistHoldStart = null;
  let pinchStreak = 0;
  let virtualCursor = { x: window.innerWidth / 2, y: window.innerHeight / 2 };
  let startGeneration = 0;

  const cursorFrameListeners = new Set();
  let lastCursorNotifyT = 0;
  function notifyVirtualCursorFrame() {
    const px = virtualCursor.x;
    const py = virtualCursor.y;
    cursorFrameListeners.forEach((fn) => {
      try {
        fn(px, py);
      } catch (_) {}
    });
  }
  function maybeNotifyVirtualCursor(throttleMs = 48) {
    const t = safeNow();
    if (t - lastCursorNotifyT < throttleMs) return;
    lastCursorNotifyT = t;
    notifyVirtualCursorFrame();
  }

  function setOverlayLabel(text) {
    labelText.textContent = `gesture: ${text}`;
  }

  function setPaused(isPaused) {
    pausedText.style.display = isPaused ? 'block' : 'none';
  }

  function flashFeedback() {
    feedbackDot.style.background = 'rgba(0,255,102,1)';
    window.setTimeout(() => {
      feedbackDot.style.background = 'rgba(255,255,255,0.25)';
    }, 140);
  }

  function flashCursor() {
    dot.style.opacity = 1;
    dot.style.transform = 'translate(-50%, -50%) scale(1.9)';
    dot.style.background = 'rgba(255,255,255,0.95)';
    dot.style.borderColor = 'rgba(0,255,102,1)';
    window.setTimeout(() => {
      dot.style.transform = 'translate(-50%, -50%) scale(1.0)';
      dot.style.background = 'rgba(0,255,102,0.95)';
      dot.style.borderColor = 'rgba(255,255,255,0.85)';
    }, 140);
  }

  function setCursorVisible(x, y) {
    const clampedX = Math.max(0, Math.min(window.innerWidth, x));
    const clampedY = Math.max(0, Math.min(window.innerHeight, y));
    virtualCursor = { x: clampedX, y: clampedY };
    dot.style.left = `${clampedX}px`;
    dot.style.top = `${clampedY}px`;
    dot.style.opacity = 1;
    dot.style.transform = 'translate(-50%, -50%) scale(1.0)';
    maybeNotifyVirtualCursor();
  }

  function stopTracks() {
    try {
      if (previewStream) {
        previewStream.getTracks().forEach((t) => t.stop());
      }
    } catch (e) {}
    previewStream = null;
    try {
      if (stream) {
        stream.getTracks().forEach((t) => t.stop());
      }
    } catch (e) {}
    stream = null;
    try {
      previewVideo.srcObject = null;
      hiddenVideo.srcObject = null;
    } catch (e) {}
  }

  async function playVideoElement(el, label) {
    try {
      await el.play();
      return true;
    } catch (e) {
      try {
        await new Promise((r) => setTimeout(r, 80));
        await el.play();
        return true;
      } catch (e2) {
        if (options.debug) {
          // eslint-disable-next-line no-console
          console.warn(`[gesture] play failed (${label})`, e2);
        }
        return false;
      }
    }
  }

  function stopCamera() {
    if (camera) {
      try {
        camera.stop();
      } catch (e) {}
      camera = null;
    }
  }

  function clearGestureSideEffects() {
    frameBuffer = [];
    swipeSamples = [];
    fistHoldStart = null;
    recognitionPausedUntil = Math.max(recognitionPausedUntil, cooldownUntil);
  }

  function canTrigger(now) {
    return now >= cooldownUntil && now - lastTriggerAt >= options.globalCooldownMs;
  }

  function gestureBufferStable(key) {
    const minCount = Math.min(options.smoothMinCount, 10);
    const frames = frameBuffer;
    if (frames.length < 5) {
      return Boolean(frames[frames.length - 1]?.[key]);
    }
    return majorityTrue(frames, key, minCount);
  }

  function computeFeatures(handLandmarks) {
    // Landmarks indices.
    const wrist = handLandmarks[0];
    const thumbTip = handLandmarks[4];
    const indexMcp = handLandmarks[5];
    const indexTip = handLandmarks[8];

    const middleMcp = handLandmarks[9];
    const middleTip = handLandmarks[12];

    const ringMcp = handLandmarks[13];
    const ringTip = handLandmarks[16];

    const pinkyMcp = handLandmarks[17];
    const pinkyTip = handLandmarks[20];

    const thumbXDist = Math.abs(thumbTip.x - wrist.x);
    const thumbExt = thumbXDist > options.thumbXThreshold;

    const indexExt = indexTip.y < indexMcp.y;
    const middleExt = middleTip.y < middleMcp.y;
    const ringExt = ringTip.y < ringMcp.y;
    const pinkyExt = pinkyTip.y < pinkyMcp.y;

    const pinchDist2 = dist2({ x: thumbTip.x, y: thumbTip.y }, { x: indexTip.x, y: indexTip.y });
    const pinch = pinchDist2 < options.pinchThreshold * options.pinchThreshold;

    const indexPoint = indexExt && !middleExt && !ringExt && !pinkyExt && !thumbExt;
    const openPalm = thumbExt && indexExt && middleExt && ringExt && pinkyExt;
    const fist = !thumbExt && !indexExt && !middleExt && !ringExt && !pinkyExt;

    // Cursor coordinates (mirror to match preview).
    const indexTipMirrorX = 1 - indexTip.x;
    const indexTipMirrorY = indexTip.y;

    return {
      thumbExt,
      indexExt,
      middleExt,
      ringExt,
      pinkyExt,
      pinch,
      indexPoint,
      openPalm,
      fist,
      indexTipMirrorX,
      indexTipMirrorY,
    };
  }

  function applyStateMachine(now, hasHand) {
    if (!enabled) {
      state = 'IDLE';
      return;
    }
    if (now < cooldownUntil) {
      state = 'COOLDOWN';
      return;
    }
    if (now < recognitionPausedUntil) {
      state = 'COOLDOWN';
      return;
    }
    if (hasHand) {
      state = 'TRACKING';
    } else {
      state = 'IDLE';
    }
  }

  function triggerCooldown(now, extraMs = 0) {
    cooldownUntil = Math.max(cooldownUntil, now + options.globalCooldownMs + extraMs);
    lastTriggerAt = now;
    state = 'COOLDOWN';
  }

  async function start() {
    if (!enabled) return;

    const gen = ++startGeneration;

    // Reset cooldown state when enabling.
    cooldownUntil = 0;
    recognitionPausedUntil = 0;
    lastTriggerAt = -Infinity;
    lastPinchClickAt = -Infinity;
    clearGestureSideEffects();

    try {
      setOverlayLabel('starting camera...');
      setPaused(false);

      if (!hands) {
        const mp = await loadMediaPipeFromCDN();
        if (!enabled || startGeneration !== gen) return;
        Camera = mp.Camera;
        hands = new mp.Hands({
          locateFile: (file) =>
            `https://cdn.jsdelivr.net/npm/@mediapipe/hands/${file}`,
        });
        hands.setOptions({
          maxNumHands: options.maxNumHands,
          modelComplexity: 1,
          minDetectionConfidence: 0.55,
          minTrackingConfidence: 0.55,
        });
      }
    } catch (e) {
      setOverlayLabel('MediaPipe load error');
      return;
    }

    try {
      stopCamera();
      stopTracks();

      setOverlayLabel('requesting camera...');
      const constraints = {
        video: { facingMode: 'user' },
        audio: false,
      };
      stream = await navigator.mediaDevices.getUserMedia(constraints);
      if (!enabled || startGeneration !== gen) {
        stopTracks();
        return;
      }

      // Dedicated stream for preview: sharing one MediaStream across two <video> elements
      // often yields a black preview (Chrome/Edge/Firefox) while MediaPipe still runs.
      try {
        previewStream = typeof stream.clone === 'function' ? stream.clone() : stream;
      } catch (e) {
        previewStream = stream;
      }
      previewVideo.srcObject = previewStream;
      hiddenVideo.srcObject = stream;

      const previewOk = await playVideoElement(previewVideo, 'preview');
      const hiddenOk = await playVideoElement(hiddenVideo, 'hidden');
      if (!enabled || startGeneration !== gen) {
        stopTracks();
        return;
      }
      if (!previewOk && !hiddenOk) {
        setOverlayLabel('camera play blocked — tap OFF then ON');
        stopTracks();
        return;
      }
      if (!previewOk && hiddenOk) {
        setOverlayLabel('listening (preview blocked — toggle OFF/ON)');
      } else {
        setOverlayLabel('listening...');
      }

      hands.onResults((results) => {
        if (!enabled) return;
        const now = safeNow();

        const multi = results?.multiHandLandmarks;
        const hasHand = Array.isArray(multi) && multi.length > 0;

        applyStateMachine(now, hasHand);
        setPaused(now < recognitionPausedUntil);

        if (!hasHand) {
          dot.style.opacity = 0;
          feedbackDot.style.background = 'rgba(255,255,255,0.25)';
          feedbackDot.style.boxShadow = '0 0 0 3px rgba(255,255,255,0.05)';
          setOverlayLabel('—');
          frameBuffer = [];
          swipeSamples = [];
          fistHoldStart = null;
          pinchStreak = 0;
          return;
        }

        // Use first hand.
        const hand = multi[0];
        const features = computeFeatures(hand);

        // Always update last known cursor position for click targets.
        const xPx = features.indexTipMirrorX * window.innerWidth;
        const yPx = features.indexTipMirrorY * window.innerHeight;
        virtualCursor = { x: xPx, y: yPx };
        maybeNotifyVirtualCursor();

        // Smooth gesture detection.
        frameBuffer.push({ ...features, t: now });
        if (frameBuffer.length > 10) frameBuffer.shift();

        const stableIndexPoint = gestureBufferStable('indexPoint');
        const stablePinch = gestureBufferStable('pinch');
        const stableOpenPalm = gestureBufferStable('openPalm');
        const stableFist = gestureBufferStable('fist');

        const pinchOkRaw =
          features.pinch && !features.openPalm && !features.fist && now >= recognitionPausedUntil;
        if (pinchOkRaw) pinchStreak += 1;
        else pinchStreak = 0;

        // Swipe: track index tip whenever index is up (not full "index point" pose) — smoother swipes.
        const swipeTrackOk =
          features.indexExt &&
          !features.openPalm &&
          !features.fist &&
          enabled &&
          now >= recognitionPausedUntil;
        if (swipeTrackOk) {
          swipeSamples.push({
            x: features.indexTipMirrorX,
            y: features.indexTipMirrorY,
            t: now,
          });
          const cutoff = now - options.swipeTimeWindowMs - 120;
          swipeSamples = swipeSamples.filter((s) => s.t >= cutoff);
        } else if (stableOpenPalm || stableFist) {
          swipeSamples = [];
        }

        // Update overlay label.
        if (now < recognitionPausedUntil) {
          setOverlayLabel('—');
          // pausedText handled via setPaused.
        } else if (stableOpenPalm) {
          setOverlayLabel('OPEN PALM');
        } else if (stableFist) {
          setOverlayLabel('FIST');
        } else if (pinchStreak >= 2 || stablePinch) {
          setOverlayLabel('PINCH');
        } else if (stableIndexPoint) {
          setOverlayLabel('INDEX POINT');
        } else {
          setOverlayLabel(state === 'TRACKING' ? 'tracking...' : '—');
        }

        // Cursor dot follow (virtual cursor).
        const showCursor =
          (stableIndexPoint || (features.indexExt && !features.openPalm && !features.fist)) &&
          now >= recognitionPausedUntil &&
          now >= cooldownUntil;
        if (showCursor) {
          setCursorVisible(xPx, yPx);
          feedbackDot.style.background = 'rgba(0,255,102,0.75)';
          feedbackDot.style.boxShadow = '0 0 0 3px rgba(0,255,102,0.2)';
        } else {
          dot.style.opacity = 0;
          feedbackDot.style.background = 'rgba(255,255,255,0.25)';
          feedbackDot.style.boxShadow = '0 0 0 3px rgba(255,255,255,0.05)';
        }

        // Gesture triggers:
        // - If "recognition paused" (open palm), we fully ignore recognition.
        // - For global cooldown, we still track hold gestures (fist), but triggers wait.
        if (now < recognitionPausedUntil) return;

        // Priority order to reduce ambiguous triggers.
        if (stableOpenPalm) {
          if (!canTrigger(now)) return;
          state = 'GESTURE_DETECTED';
          setPaused(true);
          flashFeedback();
          setOverlayLabel('OPEN PALM');
          triggerCooldown(now, options.openPalmCooldownMs - options.globalCooldownMs);
          recognitionPausedUntil = now + options.openPalmCooldownMs;
          return;
        }

        if (stableFist) {
          // Hold fist for 800ms before triggering (trigger only when cooldown allows).
          if (fistHoldStart == null) fistHoldStart = now;
          if (now - fistHoldStart >= options.fistHoldMs && canTrigger(now)) {
            fistHoldStart = null;
            state = 'GESTURE_DETECTED';
            flashFeedback();
            setOverlayLabel('FIST');
            try {
              history.back();
            } catch (e) {}
            triggerCooldown(now);
          }
          return;
        }
        // If not fist, reset hold timer.
        fistHoldStart = null;

        if (pinchStreak >= options.pinchConfirmFrames) {
          if (!canTrigger(now)) return;
          if (now - lastPinchClickAt >= options.pinchDebounceMs) {
            lastPinchClickAt = now;
            pinchStreak = 0;
            state = 'GESTURE_DETECTED';
            flashFeedback();
            flashCursor();
            setOverlayLabel('PINCH (click)');
            dispatchSyntheticClick(virtualCursor.x, virtualCursor.y);
            triggerCooldown(now);
          }
          return;
        }

        const swipeDir = consumeSwipeDirection(swipeSamples, options);
        const swipePoseOk =
          features.indexExt && !features.pinch && pinchStreak < options.pinchConfirmFrames;
        if (swipeDir && swipePoseOk) {
          if (!canTrigger(now)) return;
          if (swipeDir === 'up') {
            state = 'GESTURE_DETECTED';
            flashFeedback();
            setOverlayLabel('SWIPE UP');
            const scrollEl = findScrollableAncestorAt(virtualCursor.x, virtualCursor.y);
            scrollEl.scrollBy({ top: -window.innerHeight * options.scrollFactor, behavior: 'smooth' });
            triggerCooldown(now);
            swipeSamples = [];
            return;
          }
          if (swipeDir === 'down') {
            state = 'GESTURE_DETECTED';
            flashFeedback();
            setOverlayLabel('SWIPE DOWN');
            const scrollEl = findScrollableAncestorAt(virtualCursor.x, virtualCursor.y);
            scrollEl.scrollBy({ top: window.innerHeight * options.scrollFactor, behavior: 'smooth' });
            triggerCooldown(now);
            swipeSamples = [];
            return;
          }
          if (swipeDir === 'left') {
            state = 'GESTURE_DETECTED';
            flashFeedback();
            setOverlayLabel('SWIPE LEFT');
            try {
              history.back();
            } catch (e) {}
            triggerCooldown(now);
            swipeSamples = [];
            return;
          }
          if (swipeDir === 'right') {
            state = 'GESTURE_DETECTED';
            flashFeedback();
            setOverlayLabel('SWIPE RIGHT');
            try {
              history.forward();
            } catch (e) {}
            triggerCooldown(now);
            swipeSamples = [];
            return;
          }
        }
      });

      // Use Camera utils but cap sending rate to ~30fps.
      camera = new Camera(hiddenVideo, {
        onFrame: async () => {
          if (!enabled) return;
          const now = safeNow();
          if (now - rafSendThrottle < 33) return; // ~30fps
          rafSendThrottle = now;
          await hands.send({ image: hiddenVideo });
        },
        width: 640,
        height: 480,
      });

      // Start streaming frames.
      camera.start();
      if (!enabled || startGeneration !== gen) {
        stopCamera();
        stopTracks();
      }
    } catch (e) {
      setOverlayLabel('camera error (permission?)');
      stopTracks();
    }
  }

  function setEnabled(nextEnabled) {
    const next = Boolean(nextEnabled);
    if (next === enabled) {
      if (!next) return;
      if (stream && camera) return;
    }
    enabled = next;
    toggleBtn.textContent = enabled ? 'Gesture control: ON' : 'Gesture control: OFF';
    if (!enabled) {
      // Turn off recognition and stop the camera.
      try {
        stopCamera();
        stopTracks();
      } catch (e) {}
      frameBuffer = [];
      swipeSamples = [];
      fistHoldStart = null;
      pinchStreak = 0;
      setPaused(false);
      setOverlayLabel('—');
      dot.style.opacity = 0;
      state = 'IDLE';
    } else {
      // Start only after user clicks toggle (best for browser autoplay policies).
      start();
    }
  }

  toggleBtn.addEventListener('click', () => {
    const next = !enabled;
    if (persistSession && typeof sessionStorage !== 'undefined') {
      sessionStorage.setItem(SESSION_KEY, next ? '1' : '0');
    }
    setEnabled(next);
  });

  // Initial state.
  setEnabled(enabled);

  const api = {
    setEnabled,
    getVirtualCursor: () => ({ x: virtualCursor.x, y: virtualCursor.y }),
    subscribeVirtualCursor(cb) {
      if (typeof cb !== 'function') return () => {};
      cursorFrameListeners.add(cb);
      return () => {
        cursorFrameListeners.delete(cb);
      };
    },
    destroy() {
      try {
        cursorFrameListeners.clear();
      } catch (e) {}
      try {
        setEnabled(false);
      } catch (e) {}

      try {
        delete window[SINGLETON_KEY];
      } catch (e) {}

      try {
        overlay.remove();
      } catch (e) {}
      try {
        cursorDot.remove();
      } catch (e) {}
      try {
        hiddenVideo.remove();
      } catch (e) {}
    },
  };

  window[SINGLETON_KEY] = api;
  return api;
}

