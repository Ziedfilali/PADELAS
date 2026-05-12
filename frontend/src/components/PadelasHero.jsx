/**
 * PADELAS HERO — warehouse-aware assistant (web only). Streams from POST /api/padelas-hero/chat.
 */
import React, { useCallback, useEffect, useMemo, useRef, useState } from 'react';
import {
  ActivityIndicator,
  Keyboard,
  Platform,
  ScrollView,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  useWindowDimensions,
  View,
} from 'react-native';
import { usePathname } from 'expo-router';
import { getApiBase } from '../config/apiBase';
import { GESTURE_CONTROL_OVERLAY_STACK_HEIGHT } from '../utils/gesture-controller';

function getOrCreateSessionId() {
  if (typeof window === 'undefined' || !window.sessionStorage) {
    return `ph-${Date.now()}`;
  }
  let s = window.sessionStorage.getItem('padelas_hero_sid');
  if (!s || s.length < 8) {
    s = `ph-${Date.now()}-${Math.random().toString(36).slice(2, 11)}`;
    window.sessionStorage.setItem('padelas_hero_sid', s);
  }
  return s;
}

function renderSimpleMarkdown(text) {
  if (!text) return null;
  const parts = String(text).split(/(\*\*[^*]+\*\*)/g);
  return parts.map((chunk, i) => {
    const m = chunk.match(/^\*\*([^*]+)\*\*$/);
    if (m) {
      return (
        <Text key={i} style={{ fontWeight: '700' }}>
          {m[1]}
        </Text>
      );
    }
    return (
      <Text key={i} style={{ color: '#111827' }}>
        {chunk}
      </Text>
    );
  });
}

const SUGGESTIONS = [
  { key: '1', label: '🏆 Top players right now', text: 'Who are the top 5 players by win rate in our warehouse right now?' },
  { key: '2', label: '📊 Recent match results', text: 'Summarize the 10 most recent matches with scores and winners from our data.' },
  { key: '3', label: '🗓 Upcoming tournaments', text: 'Which tournaments look upcoming or still open in our dataset?' },
  { key: '4', label: '📈 Best win rate?', text: 'Which player has the best win rate with at least 10 matches played?' },
];

export default function PadelasHero() {
  const pathname = usePathname();
  const { width } = useWindowDimensions();
  const isWeb = Platform.OS === 'web';
  const isLogin = pathname === '/login';
  const isMobile = width < 640;

  const [open, setOpen] = useState(false);
  const [minimized, setMinimized] = useState(false);
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');
  const [streaming, setStreaming] = useState(false);
  const [thinking, setThinking] = useState(false);
  const scrollRef = useRef(null);

  const sessionId = useMemo(() => (isWeb ? getOrCreateSessionId() : 'native'), [isWeb]);

  const scrollToEnd = useCallback(() => {
    requestAnimationFrame(() => {
      try {
        scrollRef.current?.scrollToEnd?.({ animated: true });
      } catch (_) {}
    });
  }, []);

  useEffect(() => {
    scrollToEnd();
  }, [messages, open, scrollToEnd]);

  const sendMessages = useCallback(
    async (nextMessages) => {
      if (!isWeb) return;
      setThinking(true);
      setStreaming(true);
      const assistantIndex = nextMessages.length;
      setMessages([...nextMessages, { role: 'assistant', content: '' }]);

      try {
        const res = await fetch(`${getApiBase()}/api/padelas-hero/chat`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json', Accept: 'text/event-stream' },
          body: JSON.stringify({ messages: nextMessages, sessionId }),
        });
        if (!res.ok) {
          let detail = `HTTP ${res.status}`;
          try {
            const j = await res.json();
            const d = j.detail ?? j.message;
            detail = Array.isArray(d) ? d.map((x) => x?.msg || x).join(' ') : d || detail;
          } catch (_) {}
          throw new Error(String(detail));
        }
        const reader = res.body?.getReader?.();
        if (!reader) {
          throw new Error('Streaming not supported in this browser.');
        }
        const decoder = new TextDecoder();
        let carry = '';
        let assembled = '';
        while (true) {
          const { done, value } = await reader.read();
          if (done) break;
          carry += decoder.decode(value, { stream: true });
          const chunks = carry.split('\n\n');
          carry = chunks.pop() ?? '';
          for (const block of chunks) {
            const line = block.startsWith('data: ') ? block.slice(6).trim() : block.trim();
            if (!line) continue;
            let ev;
            try {
              ev = JSON.parse(line);
            } catch {
              continue;
            }
            if (ev.type === 'text' && ev.text) {
              setThinking(false);
              assembled += ev.text;
              setMessages((prev) => {
                const copy = [...prev];
                if (copy[assistantIndex]) {
                  copy[assistantIndex] = { role: 'assistant', content: assembled };
                }
                return copy;
              });
            }
            if (ev.type === 'error') {
              throw new Error(ev.message || 'Stream error');
            }
          }
        }
      } catch (e) {
        setMessages((prev) => {
          const copy = [...prev];
          if (copy[assistantIndex]) {
            copy[assistantIndex] = {
              role: 'assistant',
              content: `Sorry — ${String(e?.message || e)}`,
            };
          }
          return copy;
        });
      } finally {
        setThinking(false);
        setStreaming(false);
      }
    },
    [isWeb, sessionId]
  );

  const onSend = useCallback(async () => {
    const q = input.trim();
    if (!q || streaming) return;
    Keyboard.dismiss();
    setInput('');
    const next = [...messages, { role: 'user', content: q }];
    setMessages(next);
    await sendMessages(next);
  }, [input, messages, streaming, sendMessages]);

  const onChip = useCallback(
    (text) => {
      if (streaming) return;
      setInput('');
      const next = [...messages, { role: 'user', content: text }];
      setMessages(next);
      sendMessages(next);
    },
    [messages, streaming, sendMessages]
  );

  if (!isWeb || isLogin) return null;

  const panelWidth = isMobile ? width - 16 : 380;
  const panelHeight = isMobile ? Math.min(Math.round(width * 0.85), 560) : 520;
  // Sit above the fixed gesture dock (desktop web); z-index above gesture overlay (999999).
  const gestureReserve = isWeb && !isMobile ? GESTURE_CONTROL_OVERLAY_STACK_HEIGHT : 0;
  const triggerBottom = 24 + gestureReserve;
  const bottomOffset = triggerBottom + 58 + 18;
  const heroZ = 1100000;

  const panelOuterStyle = isMobile
    ? {
        position: 'fixed',
        left: 8,
        right: 8,
        bottom: 8 + gestureReserve,
        width: undefined,
        maxWidth: width - 16,
        height: panelHeight,
        zIndex: heroZ,
      }
    : {
        position: 'fixed',
        right: 24,
        bottom: bottomOffset,
        width: panelWidth,
        height: panelHeight,
        zIndex: heroZ,
      };

  return (
    <>
      {/* Trigger */}
      <View
        style={[
          styles.triggerWrap,
          {
            position: 'fixed',
            bottom: triggerBottom,
            right: 24,
            zIndex: heroZ,
          },
        ]}
        pointerEvents="box-none"
      >
        <TouchableOpacity
          activeOpacity={0.88}
          onPress={() => {
            setOpen((o) => !o);
            setMinimized(false);
          }}
          style={styles.triggerBtn}
        >
          <Text style={styles.triggerIcon}>⚡</Text>
        </TouchableOpacity>
        <Text style={styles.heroLabel}>HERO</Text>
      </View>

      {/* Panel */}
      {open && !minimized ? (
        <View style={[styles.panel, isMobile ? styles.panelMobile : null, panelOuterStyle]}>
          <View style={styles.header}>
            <View style={styles.avatar}>
              <Text style={styles.avatarTxt}>PH</Text>
            </View>
            <View style={styles.headerCenter}>
              <Text style={styles.headerTitle}>PADELAS HERO</Text>
              <Text style={styles.headerSub}>Padel Intelligence</Text>
            </View>
            <View style={styles.headerRight}>
              <View
                style={[
                  styles.statusDot,
                  streaming || thinking ? styles.statusYellow : styles.statusGreen,
                ]}
              />
              <TouchableOpacity onPress={() => setMinimized(true)} style={styles.hdrBtn}>
                <Text style={styles.hdrBtnTxt}>−</Text>
              </TouchableOpacity>
              <TouchableOpacity
                onPress={() => {
                  setOpen(false);
                  setMinimized(false);
                }}
                style={styles.hdrBtn}
              >
                <Text style={styles.hdrBtnTxt}>×</Text>
              </TouchableOpacity>
            </View>
          </View>

          <ScrollView
            ref={scrollRef}
            style={styles.msgScroll}
            contentContainerStyle={styles.msgScrollContent}
            keyboardShouldPersistTaps="handled"
          >
            {messages.length === 0 ? (
              <View style={styles.empty}>
                <View style={styles.emptyAvatar}>
                  <Text style={styles.emptyAvatarTxt}>PH</Text>
                </View>
                <Text style={styles.emptyHi}>{"Hi! I'm PADELAS HERO 👋"}</Text>
                <Text style={styles.emptyHint}>
                  Ask me anything about your players, matches and tournaments.
                </Text>
                <View style={styles.chipGrid}>
                  {SUGGESTIONS.map((s) => (
                    <TouchableOpacity
                      key={s.key}
                      style={styles.chip}
                      onPress={() => onChip(s.text)}
                      disabled={streaming}
                    >
                      <Text style={styles.chipTxt}>{s.label}</Text>
                    </TouchableOpacity>
                  ))}
                </View>
              </View>
            ) : (
              messages.map((m, idx) => (
                <View
                  key={idx}
                  style={[
                    styles.bubbleRow,
                    m.role === 'user' ? styles.bubbleRowUser : styles.bubbleRowBot,
                  ]}
                >
                  <View
                    style={[
                      styles.bubble,
                      m.role === 'user' ? styles.bubbleUser : styles.bubbleBot,
                    ]}
                  >
                    {m.role === 'assistant' &&
                    streaming &&
                    idx === messages.length - 1 &&
                    !String(m.content || '').trim() ? (
                      <View style={styles.dotsRow}>
                        <View style={[styles.dot, styles.dot1]} />
                        <View style={[styles.dot, styles.dot2]} />
                        <View style={[styles.dot, styles.dot3]} />
                      </View>
                    ) : m.role === 'assistant' ? (
                      <Text style={styles.bubbleBotTxt}>{renderSimpleMarkdown(m.content)}</Text>
                    ) : (
                      <Text style={styles.bubbleUserTxt}>{m.content}</Text>
                    )}
                  </View>
                </View>
              ))
            )}
          </ScrollView>

          <View style={styles.inputBar}>
            <TextInput
              style={styles.input}
              placeholder="Ask about your padel data..."
              placeholderTextColor="#9CA3AF"
              value={input}
              onChangeText={setInput}
              editable={!streaming}
              onSubmitEditing={onSend}
              returnKeyType="send"
            />
            <TouchableOpacity
              style={[styles.sendBtn, streaming && styles.sendBtnOff]}
              onPress={onSend}
              disabled={streaming || !input.trim()}
            >
              {streaming ? (
                <ActivityIndicator color="#fff" size="small" />
              ) : (
                <Text style={styles.sendArrow}>➤</Text>
              )}
            </TouchableOpacity>
          </View>
        </View>
      ) : null}

      {open && minimized ? (
        <TouchableOpacity
          style={[
            styles.miniBar,
            { position: 'fixed', right: 24, bottom: bottomOffset, zIndex: heroZ },
          ]}
          onPress={() => setMinimized(false)}
        >
          <Text style={styles.miniBarTxt}>PADELAS HERO — tap to expand</Text>
        </TouchableOpacity>
      ) : null}
    </>
  );
}

/* Fix: remove broken Animated reference — use StyleSheet static pulse on trigger */
const styles = StyleSheet.create({
  triggerWrap: {
    alignItems: 'center',
    gap: 4,
  },
  triggerBtn: {
    width: 58,
    height: 58,
    borderRadius: 29,
    backgroundColor: '#0A0A0A',
    alignItems: 'center',
    justifyContent: 'center',
    borderWidth: 1,
    borderColor: 'rgba(0,200,130,0.35)',
    shadowColor: '#00C882',
    shadowOffset: { width: 0, height: 0 },
    shadowOpacity: 0.55,
    shadowRadius: 14,
    elevation: 8,
  },
  triggerIcon: {
    fontSize: 26,
    color: '#00FF87',
  },
  heroLabel: {
    fontSize: 10,
    color: 'rgba(255,255,255,0.55)',
    letterSpacing: 1.2,
    fontWeight: '600',
  },
  panel: {
    backgroundColor: '#FFFFFF',
    borderRadius: 16,
    overflow: 'hidden',
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 8 },
    shadowOpacity: 0.18,
    shadowRadius: 40,
    elevation: 20,
    borderWidth: 1,
    borderColor: 'rgba(0,0,0,0.06)',
  },
  panelMobile: {
    borderRadius: 16,
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#0A0A0A',
    paddingHorizontal: 12,
    paddingVertical: 10,
    gap: 10,
  },
  avatar: {
    width: 36,
    height: 36,
    borderRadius: 18,
    backgroundColor: '#111827',
    borderWidth: 2,
    borderColor: '#00FF87',
    alignItems: 'center',
    justifyContent: 'center',
  },
  avatarTxt: {
    color: '#00FF87',
    fontWeight: '800',
    fontSize: 12,
  },
  headerCenter: {
    flex: 1,
  },
  headerTitle: {
    color: '#fff',
    fontWeight: '800',
    fontSize: 15,
    letterSpacing: 0.5,
  },
  headerSub: {
    color: 'rgba(255,255,255,0.55)',
    fontSize: 11,
    marginTop: 2,
  },
  headerRight: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 6,
  },
  statusDot: {
    width: 8,
    height: 8,
    borderRadius: 4,
    marginRight: 4,
  },
  statusGreen: { backgroundColor: '#22C55E' },
  statusYellow: { backgroundColor: '#EAB308' },
  hdrBtn: {
    paddingHorizontal: 8,
    paddingVertical: 4,
  },
  hdrBtnTxt: {
    color: '#fff',
    fontSize: 20,
    fontWeight: '300',
  },
  msgScroll: {
    flex: 1,
    backgroundColor: '#F9FAFB',
  },
  msgScrollContent: {
    padding: 12,
    paddingBottom: 20,
  },
  empty: {
    alignItems: 'center',
    paddingTop: 24,
    gap: 10,
  },
  emptyAvatar: {
    width: 64,
    height: 64,
    borderRadius: 32,
    backgroundColor: '#0A0A0A',
    alignItems: 'center',
    justifyContent: 'center',
    borderWidth: 2,
    borderColor: '#00FF87',
  },
  emptyAvatarTxt: {
    color: '#00FF87',
    fontWeight: '900',
    fontSize: 22,
  },
  emptyHi: {
    fontSize: 18,
    fontWeight: '700',
    color: '#111827',
    marginTop: 8,
  },
  emptyHint: {
    fontSize: 13,
    color: '#6B7280',
    textAlign: 'center',
    paddingHorizontal: 12,
    lineHeight: 20,
  },
  chipGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'center',
    gap: 8,
    marginTop: 16,
    maxWidth: 360,
  },
  chip: {
    width: '46%',
    minWidth: 140,
    backgroundColor: '#fff',
    borderRadius: 12,
    borderWidth: 1,
    borderColor: '#E5E7EB',
    paddingVertical: 10,
    paddingHorizontal: 8,
  },
  chipTxt: {
    fontSize: 12,
    color: '#374151',
    textAlign: 'center',
    fontWeight: '600',
  },
  bubbleRow: {
    marginBottom: 10,
    width: '100%',
  },
  bubbleRowUser: { alignItems: 'flex-end' },
  bubbleRowBot: { alignItems: 'flex-start' },
  bubble: {
    maxWidth: '88%',
    paddingVertical: 10,
    paddingHorizontal: 14,
  },
  bubbleUser: {
    backgroundColor: '#00C853',
    borderTopLeftRadius: 18,
    borderTopRightRadius: 18,
    borderBottomLeftRadius: 18,
    borderBottomRightRadius: 4,
  },
  bubbleBot: {
    backgroundColor: '#FFFFFF',
    borderTopLeftRadius: 18,
    borderTopRightRadius: 18,
    borderBottomRightRadius: 18,
    borderBottomLeftRadius: 4,
    borderWidth: 1,
    borderColor: '#E5E7EB',
  },
  bubbleUserTxt: {
    color: '#fff',
    fontSize: 14,
    lineHeight: 20,
  },
  bubbleBotTxt: {
    color: '#111827',
    fontSize: 14,
    lineHeight: 20,
  },
  dotsRow: {
    flexDirection: 'row',
    gap: 6,
    paddingVertical: 4,
  },
  dot: {
    width: 7,
    height: 7,
    borderRadius: 4,
    backgroundColor: '#9CA3AF',
  },
  dot1: { opacity: 0.9 },
  dot2: { opacity: 0.6 },
  dot3: { opacity: 0.35 },
  inputBar: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 10,
    gap: 8,
    borderTopWidth: 1,
    borderTopColor: '#E5E7EB',
    backgroundColor: '#fff',
  },
  input: {
    flex: 1,
    minHeight: 40,
    maxHeight: 100,
    borderRadius: 12,
    borderWidth: 1,
    borderColor: '#E5E7EB',
    paddingHorizontal: 12,
    paddingVertical: 8,
    fontSize: 14,
    color: '#111827',
    outlineStyle: 'none',
  },
  sendBtn: {
    width: 44,
    height: 44,
    borderRadius: 12,
    backgroundColor: '#00C853',
    alignItems: 'center',
    justifyContent: 'center',
  },
  sendBtnOff: {
    opacity: 0.5,
  },
  sendArrow: {
    color: '#fff',
    fontSize: 18,
    fontWeight: '700',
  },
  miniBar: {
    backgroundColor: '#0A0A0A',
    paddingVertical: 10,
    paddingHorizontal: 14,
    borderRadius: 12,
    borderWidth: 1,
    borderColor: 'rgba(0,200,130,0.35)',
  },
  miniBarTxt: {
    color: '#fff',
    fontSize: 12,
    fontWeight: '600',
  },
});
