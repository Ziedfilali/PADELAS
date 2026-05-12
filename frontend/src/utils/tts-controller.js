/**
 * PADELAS read-on-hover — Web Speech API (no dependencies).
 * @see tts-hover-listener.js for hover / gesture wiring.
 */

const LS_RATE = 'padelas_tts_rate';
const LS_VOLUME = 'padelas_tts_volume';
const LS_VOICE = 'padelas_tts_voice_uri';

let _rate = 1.0;
let _volume = 1.0;
let _pickedVoiceUri = null;
let _lastSpeakAt = 0;
let _lastSpokenText = '';
let _speakThrottleTimer = null;

function clamp(n, lo, hi) {
  return Math.min(hi, Math.max(lo, n));
}

export function isSupported() {
  return (
    typeof window !== 'undefined' &&
    typeof window.speechSynthesis !== 'undefined' &&
    typeof SpeechSynthesisUtterance !== 'undefined'
  );
}

export function loadTtsPrefsSync() {
  loadPrefs();
}

function loadPrefs() {
  try {
    const r = parseFloat(localStorage.getItem(LS_RATE) || '1');
    if (!Number.isNaN(r)) _rate = clamp(r, 0.7, 1.4);
    const v = parseFloat(localStorage.getItem(LS_VOLUME) || '1');
    if (!Number.isNaN(v)) _volume = clamp(v, 0, 1);
    _pickedVoiceUri = localStorage.getItem(LS_VOICE) || null;
  } catch (_) {}
}

function saveVoiceUri(uri) {
  _pickedVoiceUri = uri || null;
  try {
    if (uri) localStorage.setItem(LS_VOICE, uri);
    else localStorage.removeItem(LS_VOICE);
  } catch (_) {}
}

export function setRate(rate) {
  _rate = clamp(Number(rate) || 1, 0.7, 1.4);
  try {
    localStorage.setItem(LS_RATE, String(_rate));
  } catch (_) {}
}

export function setVolume(vol) {
  _volume = clamp(Number(vol) || 1, 0, 1);
  try {
    localStorage.setItem(LS_VOLUME, String(_volume));
  } catch (_) {}
}

export function getRate() {
  return _rate;
}

export function getVolume() {
  return _volume;
}

/** Score-like "6-4" → "6 to 4" (digits on both sides of hyphen/dash). */
function humanizeScores(text) {
  return String(text).replace(/(\d)\s*[-–]\s*(\d)/g, '$1 to $2');
}

function stripHtml(html) {
  if (!html || typeof html !== 'string') return '';
  const tmp = typeof document !== 'undefined' ? document.createElement('div') : null;
  if (tmp) {
    tmp.innerHTML = html;
    return tmp.innerText || tmp.textContent || '';
  }
  return html.replace(/<[^>]+>/g, ' ');
}

function looksLikeUrl(s) {
  return /^https?:\/\//i.test(s.trim()) || /^www\./i.test(s.trim());
}

/**
 * Clean and shorten text for TTS.
 * @returns {string|null} null = skip speaking
 */
export function prepareTextForSpeech(raw) {
  if (raw == null) return null;
  let s = typeof raw === 'string' ? raw : String(raw);
  s = stripHtml(s);
  s = humanizeScores(s);
  s = s.replace(/\s+/g, ' ').trim();
  if (s.length < 3) return null;
  if (looksLikeUrl(s)) return null;
  if (/^[\d\s.,:;+\-–]+$/.test(s)) return null;
  if (/^[^\w\u00C0-\u024F]+$/i.test(s.replace(/\s/g, ''))) return null;

  let out = s;
  const firstStop = out.search(/[.!?](\s|$)/);
  if (firstStop >= 12 && firstStop <= 420) {
    out = out.slice(0, firstStop + 1).trim();
  }
  if (out.length > 300) {
    out = out.slice(0, 300);
    const sp = out.lastIndexOf(' ');
    if (sp > 200) out = out.slice(0, sp);
  }
  out = out.trim();
  if (out.length < 3) return null;
  return out;
}

function pickBestVoice(voices) {
  if (!voices || !voices.length) return null;
  const lang = (typeof document !== 'undefined' && document.documentElement.lang) || 'en';
  const short = lang.split('-')[0].toLowerCase();
  if (_pickedVoiceUri) {
    const byUri = voices.find((v) => v.voiceURI === _pickedVoiceUri);
    if (byUri) return byUri;
  }
  const local = voices.find((v) => (v.lang || '').toLowerCase().startsWith(lang.toLowerCase()));
  if (local) return local;
  const enUs = voices.find((v) => (v.lang || '').toLowerCase() === 'en-us');
  if (enUs) return enUs;
  const en = voices.find((v) => (v.lang || '').toLowerCase().startsWith('en'));
  if (en) return en;
  const shortMatch = voices.find((v) => (v.lang || '').toLowerCase().startsWith(short));
  if (shortMatch) return shortMatch;
  return voices[0];
}

function applyVoiceToUtterance(utt, voices) {
  const v = pickBestVoice(voices);
  if (v) {
    try {
      utt.voice = v;
    } catch (_) {}
  }
}

export function getVoices() {
  if (!isSupported()) return [];
  try {
    return window.speechSynthesis.getVoices() || [];
  } catch (_) {
    return [];
  }
}

export function setVoiceByUri(uri) {
  saveVoiceUri(uri);
}

export function getVoiceUri() {
  return _pickedVoiceUri;
}

let _onEndCallback = null;

export function onSpeechEnd(cb) {
  _onEndCallback = typeof cb === 'function' ? cb : null;
}

function doSpeakInternal(cleaned) {
  _lastSpeakAt = typeof performance !== 'undefined' ? performance.now() : Date.now();
  _lastSpokenText = cleaned;

  try {
    window.speechSynthesis.cancel();
  } catch (_) {}

  const voices = getVoices();
  const utt = new SpeechSynthesisUtterance(cleaned);
  utt.rate = _rate;
  utt.volume = _volume;
  applyVoiceToUtterance(utt, voices);

  utt.onend = () => {
    if (_onEndCallback) {
      try {
        _onEndCallback();
      } catch (_) {}
    }
  };
  utt.onerror = () => {
    if (_onEndCallback) {
      try {
        _onEndCallback();
      } catch (_) {}
    }
  };

  try {
    window.speechSynthesis.speak(utt);
  } catch (_) {}
}

export function speak(text) {
  if (!isSupported()) return;
  const cleaned = prepareTextForSpeech(text);
  if (!cleaned) return;

  const now = typeof performance !== 'undefined' ? performance.now() : Date.now();
  if (cleaned === _lastSpokenText && window.speechSynthesis.speaking) return;

  const wait = Math.max(0, 200 - (now - _lastSpeakAt));
  if (wait > 0) {
    if (_speakThrottleTimer) clearTimeout(_speakThrottleTimer);
    _speakThrottleTimer = setTimeout(() => {
      _speakThrottleTimer = null;
      speak(text);
    }, wait);
    return;
  }

  doSpeakInternal(cleaned);
}

export function stop() {
  if (!isSupported()) return;
  if (_speakThrottleTimer) {
    clearTimeout(_speakThrottleTimer);
    _speakThrottleTimer = null;
  }
  try {
    window.speechSynthesis.cancel();
  } catch (_) {}
  _lastSpokenText = '';
}

export function resumeIfNeeded() {
  if (!isSupported()) return;
  try {
    window.speechSynthesis.resume();
  } catch (_) {}
}

/** Call after user gesture (mobile unlock). */
export function primeSpeechEngine() {
  if (!isSupported()) return;
  try {
    window.speechSynthesis.cancel();
    const u = new SpeechSynthesisUtterance('');
    u.volume = 0;
    window.speechSynthesis.speak(u);
    window.speechSynthesis.cancel();
  } catch (_) {}
}

export function initVoices(onReady) {
  if (!isSupported()) return () => {};
  loadPrefs();

  const fire = () => {
    if (typeof onReady === 'function') onReady(getVoices());
  };

  const handler = () => fire();
  try {
    window.speechSynthesis.addEventListener('voiceschanged', handler);
    const v = getVoices();
    if (v.length) fire();
  } catch (_) {
    fire();
  }

  return () => {
    try {
      window.speechSynthesis.removeEventListener('voiceschanged', handler);
    } catch (_) {}
  };
}

export function estimateSpeechSeconds(text) {
  const t = String(text || '');
  return Math.max(1.2, Math.min(45, t.length / 15));
}
