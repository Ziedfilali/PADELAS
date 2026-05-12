/**
 * Global read-on-hover TTS (mouse + optional gesture virtual cursor).
 * Depends on ./tts-controller.js — Web Speech API only.
 */

import {
  speak,
  stop,
  prepareTextForSpeech,
  estimateSpeechSeconds,
  onSpeechEnd,
} from './tts-controller';

/** RN Web: root <Text> is a <div dir="auto">, not <span>; nested Text uses span. */
function isReadableRnWebTextHost(el) {
  if (!el || el.nodeType !== 1 || el.tagName !== 'DIV') return false;
  if (el.getAttribute && el.getAttribute('data-tts-skip') != null) return false;
  const raw = ((el.innerText != null ? el.innerText : el.textContent) || '').trim();
  if (raw.length < 3 || raw.length > 520) return false;
  if (prepareTextForSpeech(raw) == null) return false;
  if (el.getAttribute && el.getAttribute('dir') === 'auto') return true;
  const n = el.childElementCount;
  if (n === 0) return true;
  if (n === 1) {
    const fc = el.firstElementChild;
    if (fc && fc.tagName === 'SPAN') return true;
  }
  return false;
}
import { getAttachedGestureController } from './gesture-controller';

const READABLE = new Set([
  'P',
  'H1',
  'H2',
  'H3',
  'H4',
  'H5',
  'H6',
  'LI',
  'TD',
  'TH',
  'SPAN',
  'LABEL',
  'A',
  'BUTTON',
  'FIGCAPTION',
  'BLOCKQUOTE',
]);

/** RN Web / a11y: text often lives in a div with a semantic role, not only in P/H/SPAN. */
const READABLE_ROLES = new Set([
  'text',
  'heading',
  'paragraph',
  'cell',
  'gridcell',
  'rowheader',
  'columnheader',
  'link',
  'listitem',
  'tab',
  'menuitem',
]);

const STYLE_ID = 'padelas-tts-hover-styles';
const READING_CLASS = 'padelas-tts-reading';

let _installed = false;
let _pointerOverHandler = null;
let _pointerOutHandler = null;
let _keyHandler = null;
let _gestureUnsub = null;
let _hoverTimer = null;
let _pendingHoverEl = null;
let _readingEl = null;
let _currentCleaned = null;
let _isEnabled = () => false;
let _gestureDwellTimer = null;
let _gestureAnchor = { x: 0, y: 0, el: null };

function ensureStyles() {
  if (typeof document === 'undefined') return;
  if (document.getElementById(STYLE_ID)) return;
  const st = document.createElement('style');
  st.id = STYLE_ID;
  st.textContent = `
    .${READING_CLASS} {
      position: relative !important;
    }
    .${READING_CLASS}::after {
      content: '';
      position: absolute;
      left: 0;
      right: 0;
      bottom: 0;
      height: 2px;
      pointer-events: none;
      background: linear-gradient(90deg, #00FF57, #5BA3F5);
      transform: scaleX(0);
      transform-origin: left center;
      animation: padelasTtsUnderline var(--padelas-tts-dur, 3s) linear forwards;
    }
    @keyframes padelasTtsUnderline {
      to { transform: scaleX(1); }
    }
  `;
  document.head.appendChild(st);
}

function nearestReadable(el) {
  if (!el || typeof el.closest !== 'function') return null;
  if (el.nodeType !== 1) return null;
  if (el.closest('[data-tts-skip]')) return null;
  let cur = el;
  while (cur && cur !== document.documentElement) {
    if (cur.nodeType === 1) {
      if (cur.getAttribute && cur.getAttribute('data-tts-skip') != null) return null;
      if (cur.hasAttribute && cur.hasAttribute('data-tts')) return cur;
      if (READABLE.has(cur.tagName)) return cur;
      if (isReadableRnWebTextHost(cur)) return cur;
      const role = (cur.getAttribute && cur.getAttribute('role')) || '';
      const rl = String(role).toLowerCase();
      if (rl && READABLE_ROLES.has(rl)) return cur;
    }
    cur = cur.parentElement;
  }
  return null;
}

/**
 * RN Web: hover target is often an outer View (div); readable text is a descendant span.
 * Also walk the hit-test stack at the pointer position.
 */
function resolveReadableFromPointerEvent(ev) {
  const t = ev && ev.target;
  let r = nearestReadable(t);
  if (r) return r;
  const x = ev.clientX;
  const y = ev.clientY;
  if (typeof x !== 'number' || typeof y !== 'number' || Number.isNaN(x + y)) return null;
  try {
    const stack = document.elementsFromPoint(x, y);
    for (let i = 0; i < stack.length; i++) {
      r = nearestReadable(stack[i]);
      if (r) return r;
    }
  } catch (_) {}
  return null;
}

function stripReadingClass(el) {
  if (!el || !el.classList) return;
  try {
    el.classList.remove(READING_CLASS);
    el.style.removeProperty('--padelas-tts-dur');
  } catch (_) {}
}

function applyReadingClass(el, cleanedText) {
  if (_readingEl && _readingEl !== el) stripReadingClass(_readingEl);
  _readingEl = el;
  if (!el) return;
  const sec = estimateSpeechSeconds(cleanedText);
  try {
    el.style.setProperty('--padelas-tts-dur', `${sec}s`);
    el.classList.add(READING_CLASS);
  } catch (_) {}
}

function extractRawText(el) {
  if (!el) return '';
  const custom = el.getAttribute && el.getAttribute('data-tts');
  if (custom != null && String(custom).trim() !== '') return String(custom);
  return (el.innerText != null ? el.innerText : el.textContent) || '';
}

function trySpeakElement(el) {
  if (!_isEnabled() || !el) return;
  const raw = extractRawText(el);
  const cleaned = prepareTextForSpeech(raw);
  if (!cleaned) return;
  if (cleaned === _currentCleaned && typeof window !== 'undefined' && window.speechSynthesis?.speaking) {
    return;
  }
  _currentCleaned = cleaned;
  applyReadingClass(el, cleaned);
  speak(cleaned);
}

function clearHoverTimer() {
  if (_hoverTimer) {
    clearTimeout(_hoverTimer);
    _hoverTimer = null;
  }
  _pendingHoverEl = null;
}

function clearGestureDwell() {
  if (_gestureDwellTimer) {
    clearTimeout(_gestureDwellTimer);
    _gestureDwellTimer = null;
  }
  _gestureAnchor = { x: 0, y: 0, el: null };
}

function onSpeechFinished() {
  if (_readingEl) stripReadingClass(_readingEl);
  _readingEl = null;
  _currentCleaned = null;
}

function handlePointerOver(ev) {
  if (!_isEnabled()) return;
  if (ev.pointerType === 'touch') return;
  const el = resolveReadableFromPointerEvent(ev);
  if (!el) {
    clearHoverTimer();
    return;
  }
  const related = ev.relatedTarget;
  if (related && el.contains(related)) return;

  if (_pendingHoverEl === el && _hoverTimer) return;

  if (_pendingHoverEl !== el) clearHoverTimer();

  const delay = el.hasAttribute('data-tts-priority') ? 0 : 600;
  _pendingHoverEl = el;
  _hoverTimer = setTimeout(() => {
    _hoverTimer = null;
    if (_pendingHoverEl !== el) return;
    trySpeakElement(el);
  }, delay);
}

function handlePointerOut(ev) {
  if (!_isEnabled()) return;
  if (ev.pointerType === 'touch') return;
  const from = ev.target;
  const to = ev.relatedTarget;
  const readable = nearestReadable(from);
  if (!readable) return;
  if (to && readable.contains(to)) return;
  clearHoverTimer();
  stripReadingClass(readable);
}

function handleKeyDown(ev) {
  if (ev.key === 'Escape') {
    stop();
    onSpeechFinished();
    return;
  }
  if (ev.altKey && (ev.key === 'r' || ev.key === 'R')) {
    ev.preventDefault();
    try {
      window.dispatchEvent(new CustomEvent('padelas-tts-toggle-request'));
    } catch (_) {}
  }
}

function handleVirtualCursor(x, y) {
  if (!_isEnabled()) return;
  const ax = _gestureAnchor.x;
  const ay = _gestureAnchor.y;
  const movedFar = Math.hypot(x - ax, y - ay) > 20;
  if (_gestureAnchor.el == null && ax === 0 && ay === 0) {
    _gestureAnchor = { x, y, el: null };
    return;
  }
  if (movedFar) {
    _gestureAnchor = { x, y, el: null };
    clearGestureDwell();
  }

  let r = null;
  try {
    const stack = document.elementsFromPoint(x, y);
    for (let i = 0; i < stack.length; i++) {
      r = nearestReadable(stack[i]);
      if (r) break;
    }
  } catch (_) {
    return;
  }
  if (!r) {
    clearGestureDwell();
    return;
  }

  if (_gestureAnchor.el !== r) {
    _gestureAnchor = { x, y, el: r };
    clearGestureDwell();
    const delay = r.hasAttribute('data-tts-priority') ? 0 : 800;
    _gestureDwellTimer = setTimeout(() => {
      _gestureDwellTimer = null;
      trySpeakElement(r);
    }, delay);
  }
}

function wireGestureCursor() {
  unwireGestureCursor();
  const tick = () => {
    const ctrl = getAttachedGestureController();
    if (ctrl && typeof ctrl.subscribeVirtualCursor === 'function') {
      _gestureUnsub = ctrl.subscribeVirtualCursor(handleVirtualCursor);
    }
  };
  tick();
  if (typeof window !== 'undefined') {
    window.setTimeout(tick, 1200);
  }
}

function unwireGestureCursor() {
  if (typeof _gestureUnsub === 'function') {
    try {
      _gestureUnsub();
    } catch (_) {}
  }
  _gestureUnsub = null;
  clearGestureDwell();
}

/**
 * @param {{ isEnabled: () => boolean }} options
 */
export function installPadelasTtsHover(options = {}) {
  if (typeof document === 'undefined') return () => {};
  _isEnabled = typeof options.isEnabled === 'function' ? options.isEnabled : () => false;

  if (_installed) {
    return uninstallPadelasTtsHover;
  }
  _installed = true;

  ensureStyles();
  onSpeechEnd(onSpeechFinished);

  _pointerOverHandler = (e) => handlePointerOver(e);
  _pointerOutHandler = (e) => handlePointerOut(e);
  _keyHandler = (e) => handleKeyDown(e);

  document.addEventListener('pointerover', _pointerOverHandler, true);
  document.addEventListener('pointerout', _pointerOutHandler, true);
  document.addEventListener('keydown', _keyHandler, true);

  wireGestureCursor();

  return uninstallPadelasTtsHover;
}

export function uninstallPadelasTtsHover() {
  if (!_installed) return;
  _installed = false;
  if (_pointerOverHandler) {
    try {
      document.removeEventListener('pointerover', _pointerOverHandler, true);
    } catch (_) {}
  }
  if (_pointerOutHandler) {
    try {
      document.removeEventListener('pointerout', _pointerOutHandler, true);
    } catch (_) {}
  }
  if (_keyHandler) {
    try {
      document.removeEventListener('keydown', _keyHandler, true);
    } catch (_) {}
  }
  _pointerOverHandler = null;
  _pointerOutHandler = null;
  _keyHandler = null;
  clearHoverTimer();
  clearGestureDwell();
  unwireGestureCursor();
  _gestureAnchor = { x: 0, y: 0, el: null };
  stop();
  onSpeechFinished();
  onSpeechEnd(null);
}

export function setTtsHoverEnabledGetter(fn) {
  _isEnabled = typeof fn === 'function' ? fn : () => false;
}

export function refreshGestureTtsSubscription() {
  if (!_installed) return;
  wireGestureCursor();
}
