/**
 * Read-on-hover TTS — floating control (web only). Alt+R toggles, Esc stops.
 */
import React, { useCallback, useEffect, useRef, useState } from 'react';
import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  Platform,
  ScrollView,
  useWindowDimensions,
} from 'react-native';
import { usePathname } from 'expo-router';
import {
  isSupported,
  initVoices,
  getVoices,
  setRate,
  setVolume,
  setVoiceByUri,
  getVoiceUri,
  getRate,
  getVolume,
  speak,
  primeSpeechEngine,
  loadTtsPrefsSync,
} from '../utils/tts-controller';
import {
  installPadelasTtsHover,
  uninstallPadelasTtsHover,
  setTtsHoverEnabledGetter,
  refreshGestureTtsSubscription,
} from '../utils/tts-hover-listener';
import { colors } from '../theme';

const LS_ENABLED = 'padelas_tts_enabled';

function readLsEnabled() {
  try {
    const v = localStorage.getItem(LS_ENABLED);
    if (v === '0') return false;
    if (v === '1') return true;
    return true;
  } catch (_) {
    return true;
  }
}

function writeLsEnabled(v) {
  try {
    localStorage.setItem(LS_ENABLED, v ? '1' : '0');
  } catch (_) {}
}

export default function TTSReadOnHover() {
  const pathname = usePathname();
  const { width } = useWindowDimensions();
  const enabledRef = useRef(false);
  const [on, setOn] = useState(() => (Platform.OS === 'web' ? readLsEnabled() : false));
  const [expanded, setExpanded] = useState(false);
  const [voices, setVoices] = useState([]);
  const [rateLabel, setRateLabel] = useState('Normal');
  const [volPct, setVolPct] = useState(100);
  const [voiceTick, setVoiceTick] = useState(0);
  const hoverMounted = useRef(false);
  const mobilePrimed = useRef(false);

  enabledRef.current = on;

  useEffect(() => {
    setTtsHoverEnabledGetter(() => enabledRef.current);
  }, [on]);

  useEffect(() => {
    if (Platform.OS !== 'web') return;
    loadTtsPrefsSync();
    const r = getRate();
    if (r <= 0.82) setRateLabel('Slow');
    else if (r >= 1.12) setRateLabel('Fast');
    else setRateLabel('Normal');
    setVolPct(Math.round(getVolume() * 100));
  }, []);

  useEffect(() => {
    if (Platform.OS !== 'web' || typeof window === 'undefined' || !isSupported()) return undefined;

    const offVoices = initVoices((v) => setVoices([...v]));

    const primeOnce = () => {
      if (mobilePrimed.current) return;
      mobilePrimed.current = true;
      primeSpeechEngine();
    };
    window.addEventListener('pointerdown', primeOnce, { once: true, passive: true });
    window.addEventListener('touchstart', primeOnce, { once: true, passive: true });

    if (!hoverMounted.current) {
      hoverMounted.current = true;
      installPadelasTtsHover({
        isEnabled: () => enabledRef.current,
      });
    }

    const onToggleRequest = () => {
      setOn((prev) => {
        const next = !prev;
        writeLsEnabled(next);
        return next;
      });
    };
    window.addEventListener('padelas-tts-toggle-request', onToggleRequest);

    return () => {
      window.removeEventListener('pointerdown', primeOnce);
      window.removeEventListener('touchstart', primeOnce);
      window.removeEventListener('padelas-tts-toggle-request', onToggleRequest);
      offVoices();
      if (hoverMounted.current) {
        hoverMounted.current = false;
        uninstallPadelasTtsHover();
      }
    };
  }, []);

  useEffect(() => {
    if (Platform.OS !== 'web') return;
    refreshGestureTtsSubscription();
  }, [pathname]);

  useEffect(() => {
    if (!expanded || Platform.OS !== 'web') return;
    setVoices([...getVoices()]);
  }, [expanded]);

  const toggle = useCallback(() => {
    setOn((prev) => {
      const next = !prev;
      writeLsEnabled(next);
      return next;
    });
  }, []);

  const applyRate = useCallback((r, label) => {
    setRate(r);
    setRateLabel(label);
  }, []);

  const applyVol = useCallback((pct) => {
    setVolPct(pct);
    setVolume(pct / 100);
  }, []);

  const pickVoice = useCallback((uri) => {
    setVoiceByUri(uri);
    setVoices([...getVoices()]);
    setVoiceTick((t) => t + 1);
  }, []);

  const testVoice = useCallback(() => {
    speak('Hi, I am PADELAS HERO reading your padel data');
  }, []);

  if (Platform.OS !== 'web' || !isSupported()) return null;

  const isNarrow = width < 520;
  const right = isNarrow ? 8 : 16;
  const top = isNarrow ? 8 : 56;

  return (
    <View
      style={[styles.wrap, { top, right }]}
      pointerEvents="box-none"
      accessibilityLabel="Read on hover text to speech"
    >
      <View style={styles.pillRow}>
        <TouchableOpacity style={styles.mainPill} onPress={toggle} activeOpacity={0.88}>
          <Text style={styles.mainPillIcon}>{on ? '🔊' : '🔇'}</Text>
          <Text style={styles.mainPillTxt}>
            Read on hover {on ? 'ON' : 'OFF'}
          </Text>
        </TouchableOpacity>
        <TouchableOpacity
          style={styles.gear}
          onPress={() => setExpanded((e) => !e)}
          accessibilityLabel="TTS settings"
        >
          <Text style={styles.gearTxt}>⚙</Text>
        </TouchableOpacity>
      </View>

      {expanded ? (
        <View style={styles.panel}>
          <Text style={styles.panelTitle}>Voice & speed</Text>
          <Text style={styles.panelHint}>Voices (tap to select)</Text>
          <ScrollView
            key={voiceTick}
            style={styles.voiceList}
            nestedScrollEnabled
            keyboardShouldPersistTaps="handled"
          >
            {voices.slice(0, 48).map((v, idx) => {
              const sel = getVoiceUri() === v.voiceURI;
              return (
                <TouchableOpacity
                  key={`${v.voiceURI}-${idx}`}
                  style={[styles.voiceRow, sel && styles.voiceRowSel]}
                  onPress={() => pickVoice(v.voiceURI)}
                >
                  <Text style={styles.voiceRowTxt} numberOfLines={1}>
                    {v.name} ({v.lang})
                  </Text>
                </TouchableOpacity>
              );
            })}
          </ScrollView>

          <Text style={styles.lbl}>Speed</Text>
          <View style={styles.row3}>
            <TouchableOpacity style={styles.miniBtn} onPress={() => applyRate(0.75, 'Slow')}>
              <Text style={styles.miniBtnTxt}>Slow</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.miniBtn} onPress={() => applyRate(1, 'Normal')}>
              <Text style={styles.miniBtnTxt}>Normal</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.miniBtn} onPress={() => applyRate(1.25, 'Fast')}>
              <Text style={styles.miniBtnTxt}>Fast</Text>
            </TouchableOpacity>
          </View>
          <Text style={styles.subtle}>Current: {rateLabel}</Text>

          <Text style={styles.lbl}>Volume</Text>
          <View style={styles.row3}>
            {[0, 50, 100].map((p) => (
              <TouchableOpacity
                key={p}
                style={[styles.miniBtn, volPct === p && styles.miniBtnSel]}
                onPress={() => applyVol(p)}
              >
                <Text style={styles.miniBtnTxt}>{p}%</Text>
              </TouchableOpacity>
            ))}
          </View>

          <TouchableOpacity style={styles.testBtn} onPress={testVoice}>
            <Text style={styles.testBtnTxt}>Test voice</Text>
          </TouchableOpacity>
          <Text style={styles.help}>Alt+R toggle · Esc stop</Text>
        </View>
      ) : null}
    </View>
  );
}

const styles = StyleSheet.create({
  wrap: {
    position: 'fixed',
    zIndex: 1200000,
    maxWidth: 280,
    alignItems: 'flex-end',
  },
  pillRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
  },
  mainPill: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    paddingVertical: 8,
    paddingHorizontal: 12,
    borderRadius: 999,
    backgroundColor: 'rgba(17,24,39,0.95)',
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  mainPillIcon: {
    fontSize: 14,
  },
  mainPillTxt: {
    color: colors.text.primary,
    fontSize: 12,
    fontFamily: 'Inter_600SemiBold',
  },
  gear: {
    width: 36,
    height: 36,
    borderRadius: 18,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'rgba(17,24,39,0.95)',
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  gearTxt: {
    color: colors.text.secondary,
    fontSize: 16,
  },
  panel: {
    marginTop: 8,
    padding: 12,
    borderRadius: 12,
    backgroundColor: colors.bg.card,
    borderWidth: 1,
    borderColor: colors.bg.border,
    maxHeight: 420,
    width: 260,
  },
  panelTitle: {
    color: colors.text.primary,
    fontFamily: 'Inter_700Bold',
    fontSize: 13,
    marginBottom: 6,
  },
  panelHint: {
    color: colors.text.muted,
    fontSize: 10,
    marginBottom: 4,
  },
  voiceList: {
    maxHeight: 160,
    marginBottom: 10,
  },
  voiceRow: {
    paddingVertical: 6,
    paddingHorizontal: 8,
    borderRadius: 8,
    marginBottom: 4,
  },
  voiceRowSel: {
    backgroundColor: colors.green.muted,
  },
  voiceRowTxt: {
    color: colors.text.secondary,
    fontSize: 11,
  },
  lbl: {
    color: colors.text.secondary,
    fontSize: 11,
    marginTop: 6,
    marginBottom: 4,
  },
  row3: {
    flexDirection: 'row',
    gap: 6,
  },
  miniBtn: {
    flex: 1,
    paddingVertical: 8,
    borderRadius: 8,
    backgroundColor: colors.bg.elevated,
    borderWidth: 1,
    borderColor: colors.bg.border,
    alignItems: 'center',
  },
  miniBtnSel: {
    borderColor: colors.green.neon,
  },
  miniBtnTxt: {
    color: colors.text.primary,
    fontSize: 11,
    fontFamily: 'Inter_500Medium',
  },
  subtle: {
    color: colors.text.muted,
    fontSize: 10,
    marginTop: 4,
  },
  testBtn: {
    marginTop: 12,
    paddingVertical: 10,
    borderRadius: 10,
    backgroundColor: colors.green.mid,
    alignItems: 'center',
  },
  testBtnTxt: {
    color: colors.text.inverse,
    fontFamily: 'Inter_600SemiBold',
    fontSize: 12,
  },
  help: {
    marginTop: 8,
    color: colors.text.muted,
    fontSize: 10,
    textAlign: 'center',
  },
});
