import React, { useState, useRef, useEffect } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity, StyleSheet,
  Animated, StatusBar, Dimensions, ActivityIndicator,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { LinearGradient } from 'expo-linear-gradient';
import { useLocalSearchParams, useRouter } from 'expo-router';
import { colors, fontSize, radius, spacing, shadow } from '../../src/theme';
import StatsBar from '../../src/components/StatsBar';
import WinProbabilityChart from '../../src/components/charts/WinProbabilityChart';
import { useWarehouseData } from '../../src/context/WarehouseDataContext';
import { predictMatchup } from '../../src/services/api';
import { ML_METRICS } from '../../src/data/mockData';
import { useResponsive } from '../../src/hooks/useResponsive';

const { width } = Dimensions.get('window');

const TABS = [
  { id: 'summary',    icon: '📋', label: 'Summary'  },
  { id: 'stats',      icon: '📊', label: 'Stats'    },
  { id: 'h2h',        icon: '⚔️',  label: 'H2H'     },
  { id: 'ai',         icon: '🤖', label: 'Prediction' },
  { id: 'lastfive',   icon: '📅', label: 'Last 5'   },
];

// ─── SCORE HEADER ─────────────────────────────────────────────────────────────
function ScoreHeader({ match }) {
  const t1Win = match.winner === 'team_1';
  const t2Win = match.winner === 'team_2';
  const pulseAnim = useRef(new Animated.Value(1)).current;

  useEffect(() => {
    if (match.status === 'live') {
      Animated.loop(
        Animated.sequence([
          Animated.timing(pulseAnim, { toValue: 1.04, duration: 700, useNativeDriver: true }),
          Animated.timing(pulseAnim, { toValue: 1,    duration: 700, useNativeDriver: true }),
        ])
      ).start();
    }
  }, []);

  return (
    <LinearGradient
      colors={['#0A0F1E', '#0D1428', '#07090F']}
      style={styles.scoreHeader}
    >
      {/* Tournament + round */}
      <View style={styles.scoreMeta}>
        <Text style={styles.scoreMetaTournament}>{match.tournamentName}</Text>
        <View style={styles.dot} />
        <Text style={styles.scoreMetaRound}>{match.round}</Text>
        <View style={styles.dot} />
        <Text style={styles.scoreMetaDate}>{match.date}</Text>
      </View>

      {/* Status */}
      {match.status === 'live' ? (
        <Animated.View style={[styles.liveChip, { transform: [{ scale: pulseAnim }] }]}>
          <View style={styles.liveDot} />
          <Text style={styles.liveChipText}>LIVE · {match.time}</Text>
        </Animated.View>
      ) : match.status === 'finished' ? (
        <Text style={styles.ftChip}>FT · {match.duration}</Text>
      ) : (
        <Text style={styles.upcomingChip}>{match.time}</Text>
      )}

      {/* Scores */}
      <View style={styles.scoresRow}>
        {/* Team 1 */}
        <View style={[styles.teamBlock, t1Win && styles.teamBlockWinner]}>
          <Text style={[styles.teamPlayer, t1Win && styles.teamPlayerWinner]} numberOfLines={1}>
            {match.team1.player1}
          </Text>
          <Text style={[styles.teamPlayer2, t1Win && styles.teamPlayerWinner]} numberOfLines={1}>
            {match.team1.player2}
          </Text>
          {match.team1.sets && (
            <View style={styles.setsRow}>
              {match.team1.sets.map((s, i) => (
                <Text key={i} style={styles.setNum}>{s}</Text>
              ))}
            </View>
          )}
        </View>

        {/* Score */}
        <View style={styles.mainScore}>
          <Text style={[styles.mainScoreNum, t1Win && { color: colors.green.neon }]}>
            {match.team1.score}
          </Text>
          <Text style={styles.mainScoreDash}>–</Text>
          <Text style={[styles.mainScoreNum, t2Win && { color: colors.green.neon }]}>
            {match.team2.score}
          </Text>
        </View>

        {/* Team 2 */}
        <View style={[styles.teamBlock, styles.teamBlockRight, t2Win && styles.teamBlockWinner]}>
          <Text style={[styles.teamPlayer, styles.rightText, t2Win && styles.teamPlayerWinner]} numberOfLines={1}>
            {match.team2.player1}
          </Text>
          <Text style={[styles.teamPlayer2, styles.rightText, t2Win && styles.teamPlayerWinner]} numberOfLines={1}>
            {match.team2.player2}
          </Text>
          {match.team2.sets && (
            <View style={[styles.setsRow, styles.rightRow]}>
              {match.team2.sets.map((s, i) => (
                <Text key={i} style={styles.setNum}>{s}</Text>
              ))}
            </View>
          )}
        </View>
      </View>

      {/* Views */}
      <Text style={styles.viewsChip}>
        👁 {match.views?.toLocaleString()} views · {match.interactions?.toLocaleString()} interactions
      </Text>
    </LinearGradient>
  );
}

// ─── SUMMARY TAB ─────────────────────────────────────────────────────────────
function SummaryTab({ match }) {
  const t1 = match.team1;
  const t2 = match.team2;
  return (
    <ScrollView showsVerticalScrollIndicator={false} style={styles.tabContent}>
      <View style={styles.summaryTeams}>
        {/* Team 1 card */}
        <TeamCard team={t1} isWinner={match.winner === 'team_1'} align="left" />
        <View style={styles.vsSep}><Text style={styles.vsText}>VS</Text></View>
        {/* Team 2 card */}
        <TeamCard team={t2} isWinner={match.winner === 'team_2'} align="right" />
      </View>

      {/* Key moments */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>Match Summary</Text>
        <View style={styles.summaryGrid}>
          <SummaryKV icon="🎯" label="Aces"           t1={t1.stats.aces}          t2={t2.stats.aces} />
          <SummaryKV icon="❌" label="Double Faults"  t1={t1.stats.doubleFaults}  t2={t2.stats.doubleFaults} lowerBetter />
          <SummaryKV icon="⚡" label="1st Serve %"   t1={t1.stats.firstServePct} t2={t2.stats.firstServePct} unit="%" />
          <SummaryKV icon="🏆" label="Points Won"     t1={t1.stats.totalPointsWon}t2={t2.stats.totalPointsWon} />
          <SummaryKV icon="💥" label="Breaks"         t1={t1.stats.breakPointsConverted} t2={t2.stats.breakPointsConverted} />
          <SummaryKV icon="🔄" label="Return Pts"     t1={t1.stats.totalWonOnReturn}     t2={t2.stats.totalWonOnReturn} />
        </View>
      </View>
    </ScrollView>
  );
}

function TeamCard({ team, isWinner, align }) {
  return (
    <View style={[styles.teamCard, isWinner && styles.teamCardWinner, align === 'right' && styles.teamCardRight]}>
      {isWinner && (
        <LinearGradient
          colors={[colors.green.muted, 'transparent']}
          style={StyleSheet.absoluteFill}
        />
      )}
      <Text style={styles.tcFlag}>
        {team.player1Country === 'ES' ? '🇪🇸' : team.player1Country === 'AR' ? '🇦🇷' : '🌍'}
      </Text>
      <Text style={[styles.tcPlayer, align === 'right' && styles.rightText]}>{team.player1}</Text>
      <Text style={[styles.tcPlayer2, align === 'right' && styles.rightText]}>{team.player2}</Text>
      {isWinner && <Text style={styles.winnerLabel}>WINNER 🏆</Text>}
    </View>
  );
}

function SummaryKV({ icon, label, t1, t2, unit = '', lowerBetter = false }) {
  const t1Better = lowerBetter ? t1 < t2 : t1 > t2;
  const t2Better = lowerBetter ? t2 < t1 : t2 > t1;
  return (
    <View style={styles.kvRow}>
      <Text style={[styles.kvVal, t1Better && styles.kvGreen]}>{t1}{unit}</Text>
      <View style={styles.kvCenter}>
        <Text style={styles.kvIcon}>{icon}</Text>
        <Text style={styles.kvLabel}>{label}</Text>
      </View>
      <Text style={[styles.kvVal, styles.kvRight, t2Better && styles.kvBlue]}>{t2}{unit}</Text>
    </View>
  );
}

// ─── STATS TAB ────────────────────────────────────────────────────────────────
function StatsTab({ match }) {
  const s1 = match.team1.stats;
  const s2 = match.team2.stats;
  const statPairs = [
    { label: 'Aces',              v1: s1.aces,              v2: s2.aces },
    { label: 'Double Faults',     v1: s1.doubleFaults,      v2: s2.doubleFaults,     unit: '', lowerBetter: true },
    { label: '1st Serve %',       v1: s1.firstServePct,     v2: s2.firstServePct,     unit: '%' },
    { label: 'Won on 1st Serve',  v1: s1.wonOn1stServe,     v2: s2.wonOn1stServe,    unit: '%' },
    { label: 'Won on 2nd Serve',  v1: s1.wonOn2ndServe,     v2: s2.wonOn2ndServe,    unit: '%' },
    { label: 'Total Points Won',  v1: s1.totalPointsWon,    v2: s2.totalPointsWon },
    { label: 'Break Points Conv.',v1: s1.breakPointsConverted, v2: s2.breakPointsConverted },
    { label: 'Return Pts Won',    v1: s1.totalWonOnReturn,  v2: s2.totalWonOnReturn },
  ];

  return (
    <ScrollView showsVerticalScrollIndicator={false} style={styles.tabContent}>
      {/* Team labels */}
      <View style={styles.statsHeader}>
        <Text style={styles.statsTeam1}>{match.team1.player1} / {match.team1.player2}</Text>
        <Text style={styles.statsMid}>STATS</Text>
        <Text style={styles.statsTeam2}>{match.team2.player1} / {match.team2.player2}</Text>
      </View>

      <View style={styles.card}>
        {statPairs.map((sp, i) => (
          <StatsBar
            key={sp.label}
            label={sp.label}
            value1={sp.v1}
            value2={sp.v2}
            unit={sp.unit || ''}
            delay={i * 80}
          />
        ))}
      </View>
    </ScrollView>
  );
}

// ─── H2H TAB ──────────────────────────────────────────────────────────────────
function H2HTab({ match }) {
  const { matches: allMatches } = useWarehouseData();
  const pair1 = [match.team1.player1, match.team1.player2];
  const pair2 = [match.team2.player1, match.team2.player2];

  // Find all historical H2H matches
  const h2hMatches = allMatches.filter(m =>
    m.id !== match.id && (
      (m.team1.player1 === pair1[0] || m.team1.player2 === pair1[0]) &&
      (m.team2.player1 === pair2[0] || m.team2.player2 === pair2[0])
    ) || (
      (m.team2.player1 === pair1[0] || m.team2.player2 === pair1[0]) &&
      (m.team1.player1 === pair2[0] || m.team1.player2 === pair2[0])
    )
  );

  const t1Wins = h2hMatches.filter(m => {
    const isT1 = m.team1.player1 === pair1[0] || m.team1.player2 === pair1[0];
    return (isT1 && m.winner === 'team_1') || (!isT1 && m.winner === 'team_2');
  }).length;
  const t2Wins = h2hMatches.length - t1Wins;

  return (
    <ScrollView showsVerticalScrollIndicator={false} style={styles.tabContent}>
      {/* Head-to-head summary */}
      <View style={styles.h2hSummary}>
        <LinearGradient
          colors={[colors.bg.elevated, colors.bg.card]}
          style={styles.h2hCard}
        >
          <View style={styles.h2hResult}>
            <View style={styles.h2hTeamStat}>
              <Text style={styles.h2hWins}>{t1Wins}</Text>
              <Text style={styles.h2hTeamName} numberOfLines={1}>
                {match.team1.player1}
              </Text>
            </View>
            <View style={styles.h2hCenter}>
              <Text style={styles.h2hTotal}>{h2hMatches.length || '—'}</Text>
              <Text style={styles.h2hTotalLabel}>matches</Text>
            </View>
            <View style={styles.h2hTeamStat}>
              <Text style={[styles.h2hWins, { color: colors.blue.light }]}>{t2Wins}</Text>
              <Text style={[styles.h2hTeamName, styles.rightText]} numberOfLines={1}>
                {match.team2.player1}
              </Text>
            </View>
          </View>

          {/* Win bar */}
          {h2hMatches.length > 0 && (
            <View style={styles.h2hBar}>
              <LinearGradient
                colors={[colors.green.mid, colors.green.neon]}
                start={{ x: 0, y: 0 }}
                end={{ x: 1, y: 0 }}
                style={[styles.h2hBarFill1, { flex: t1Wins || 0.5 }]}
              />
              <LinearGradient
                colors={[colors.blue.mid, colors.blue.light]}
                start={{ x: 0, y: 0 }}
                end={{ x: 1, y: 0 }}
                style={[styles.h2hBarFill2, { flex: t2Wins || 0.5 }]}
              />
            </View>
          )}
        </LinearGradient>
      </View>

      {/* H2H Match history */}
      <Text style={styles.h2hHistTitle}>Head-to-Head History</Text>
      {h2hMatches.length === 0 && (
        <Text style={styles.emptyText}>No head-to-head data available yet.</Text>
      )}
      {h2hMatches.map(m => (
        <View key={m.id} style={styles.h2hMatchRow}>
          <Text style={styles.h2hDate}>{m.date}</Text>
          <Text style={styles.h2hTournament}>{m.tournamentName}</Text>
          <View style={styles.h2hScoreRow}>
            <Text style={styles.h2hPlayer}>{m.team1.player1}/{m.team1.player2}</Text>
            <Text style={styles.h2hScore}>{m.team1.score}–{m.team2.score}</Text>
            <Text style={[styles.h2hPlayer, styles.rightText]}>{m.team2.player1}/{m.team2.player2}</Text>
          </View>
          <Text style={[
            styles.h2hWinner,
            { color: m.winner === 'team_1' ? colors.green.neon : colors.blue.light },
          ]}>
            {m.winner === 'team_1'
              ? `${m.team1.player1} / ${m.team1.player2} won`
              : `${m.team2.player1} / ${m.team2.player2} won`}
          </Text>
        </View>
      ))}
    </ScrollView>
  );
}

function matchPredictPayload(m) {
  return {
    tournament_name: (m.tournamentName && String(m.tournamentName).trim()) || 'FIP',
    round: (m.round && String(m.round).trim()) || 'Main draw',
    team1_player1_name: m.team1.player1,
    team1_player2_name: m.team1.player2,
    team2_player1_name: m.team2.player1,
    team2_player2_name: m.team2.player2,
  };
}

// ─── PREDICTION TAB (ML matchup model) ────────────────────────────────────────
function AiTab({ match }) {
  const [apiResult, setApiResult] = useState(null);
  const [loading, setLoading] = useState(true);
  const version = ML_METRICS.winnerClassification?.version;

  useEffect(() => {
    let cancelled = false;
    setLoading(true);
    setApiResult(null);
    (async () => {
      const { data } = await predictMatchup(matchPredictPayload(match));
      if (!cancelled) {
        setApiResult(data);
        setLoading(false);
      }
    })();
    return () => {
      cancelled = true;
    };
  }, [match.id]);

  const team1Label = `${match.team1.player1} / ${match.team1.player2}`;
  const team2Label = `${match.team2.player1} / ${match.team2.player2}`;

  const probability =
    apiResult && typeof apiResult.team_1_probability === 'number'
      ? apiResult.team_1_probability
      : match.mlPrediction?.team1Probability != null
        ? match.mlPrediction.team1Probability
        : null;

  return (
    <ScrollView showsVerticalScrollIndicator={false} style={styles.tabContent}>
      {loading ? (
        <View style={styles.predictionLoading}>
          <ActivityIndicator size="large" color={colors.green.neon} />
          <Text style={styles.predictionLoadingText}>Running matchup model…</Text>
        </View>
      ) : null}

      {!loading && probability == null ? (
        <View style={styles.noPrediction}>
          <Text style={styles.noPredictionIcon}>🤖</Text>
          <Text style={styles.noPredictionText}>No prediction available for this matchup yet</Text>
        </View>
      ) : null}

      {!loading && probability != null ? (
        <>
          <WinProbabilityChart
            team1={team1Label}
            team2={team2Label}
            probability={probability}
            version={version != null ? `v${version}` : ''}
          />

          <View style={[styles.card, { marginTop: spacing.md }]}>
            <Text style={styles.cardTitle}>Reading this prediction</Text>
            <Text style={styles.readoutBody}>
              The split shows how often each side is favored before the first serve, using pair history
              and match context from the ML service. It supports planning — not a guarantee of the final
              score.
            </Text>
          </View>

          <View style={[styles.card, { marginTop: spacing.sm }]}>
            <Text style={styles.cardTitle}>Signals that move the needle</Text>
            {[
              { label: 'Total points traded', pct: 0.28 },
              { label: 'First-serve solidity', pct: 0.22 },
              { label: 'Break chances converted', pct: 0.18 },
              { label: 'Return pressure', pct: 0.15 },
              { label: 'Serve winners', pct: 0.10 },
              { label: 'Unforced mistakes', pct: 0.07 },
            ].map((f, i) => (
              <FeatureBar key={f.label} label={f.label} pct={f.pct} delay={i * 100} />
            ))}
          </View>
        </>
      ) : null}
    </ScrollView>
  );
}

function FeatureBar({ label, pct, delay }) {
  const anim = useRef(new Animated.Value(0)).current;
  useEffect(() => {
    Animated.sequence([
      Animated.delay(delay),
      Animated.timing(anim, { toValue: pct, duration: 800, useNativeDriver: false }),
    ]).start();
  }, []);
  const barWidth = anim.interpolate({ inputRange: [0, 1], outputRange: ['0%', '100%'] });
  return (
    <View style={styles.featureRow}>
      <Text style={styles.featureLabel}>{label}</Text>
      <View style={styles.featureTrack}>
        <Animated.View style={[styles.featureFill, { width: barWidth }]}>
          <LinearGradient
            colors={[colors.green.dark, colors.green.neon]}
            start={{ x: 0, y: 0 }} end={{ x: 1, y: 0 }}
            style={StyleSheet.absoluteFill}
          />
        </Animated.View>
      </View>
      <Text style={styles.featurePct}>{Math.round(pct * 100)}%</Text>
    </View>
  );
}

// ─── LAST 5 TAB ───────────────────────────────────────────────────────────────
function LastFiveTab({ match }) {
  const { getPlayerLastFive } = useWarehouseData();
  const team1Matches = getPlayerLastFive(match.team1.player1, match.id);
  const team2Matches = getPlayerLastFive(match.team2.player1, match.id);

  return (
    <ScrollView showsVerticalScrollIndicator={false} style={styles.tabContent}>
      <LastFiveSection
        title={`${match.team1.player1} / ${match.team1.player2}`}
        teamColor={colors.green.neon}
        matches={team1Matches}
        playerName={match.team1.player1}
      />
      <LastFiveSection
        title={`${match.team2.player1} / ${match.team2.player2}`}
        teamColor={colors.blue.light}
        matches={team2Matches}
        playerName={match.team2.player1}
      />
    </ScrollView>
  );
}

function LastFiveSection({ title, teamColor, matches, playerName }) {
  const form = matches.map(m => {
    const isT1 = m.team1.player1 === playerName || m.team1.player2 === playerName;
    if (!m.winner) return 'D';
    return (isT1 && m.winner === 'team_1') || (!isT1 && m.winner === 'team_2') ? 'W' : 'L';
  });

  return (
    <View style={styles.lastFiveSection}>
      <View style={styles.lastFiveHeader}>
        <View style={[styles.lastFiveAccent, { backgroundColor: teamColor }]} />
        <Text style={styles.lastFiveTitle}>{title}</Text>
      </View>
      <View style={styles.formRow}>
        {form.map((r, i) => (
          <View
            key={i}
            style={[
              styles.formBadge,
              {
                backgroundColor:
                  r === 'W' ? colors.green.muted :
                  r === 'L' ? 'rgba(255,59,59,0.2)' :
                  'rgba(245,197,24,0.2)',
                borderColor:
                  r === 'W' ? colors.green.dark :
                  r === 'L' ? 'rgba(255,59,59,0.4)' :
                  'rgba(245,197,24,0.4)',
              },
            ]}
          >
            <Text style={[
              styles.formText,
              {
                color:
                  r === 'W' ? colors.green.neon :
                  r === 'L' ? colors.accent.red :
                  colors.accent.gold,
              },
            ]}>{r}</Text>
          </View>
        ))}
        {form.length === 0 && (
          <Text style={styles.emptyText}>No recent matches found</Text>
        )}
      </View>

      {matches.slice(0, 5).map(m => (
        <View key={m.id} style={styles.lastFiveMatch}>
          <View style={styles.lastFiveMeta}>
            <Text style={styles.lastFiveDate}>{m.date}</Text>
            <Text style={styles.lastFiveTourney} numberOfLines={1}>{m.tournamentName}</Text>
          </View>
          <View style={styles.lastFiveResult}>
            <Text style={styles.lastFiveScore}>
              {m.team1.player1}/{m.team1.player2} {m.team1.score}–{m.team2.score} {m.team2.player1}/{m.team2.player2}
            </Text>
          </View>
        </View>
      ))}
    </View>
  );
}

// ─── MAIN SCREEN ─────────────────────────────────────────────────────────────
export default function MatchDetail() {
  const { id }  = useLocalSearchParams();
  const router  = useRouter();
  const insets  = useSafeAreaInsets();
  const { isDesktop } = useResponsive();
  const [activeTab, setActiveTab] = useState('summary');
  const tabAnim = useRef(new Animated.Value(0)).current;

  const { getMatchById } = useWarehouseData();
  const match = getMatchById(id);

  if (!match) {
    return (
      <View style={styles.container}>
        <Text style={styles.emptyText}>Match not found</Text>
      </View>
    );
  }

  const handleTabChange = (tabId) => {
    Animated.timing(tabAnim, { toValue: 0, duration: 100, useNativeDriver: true }).start(() => {
      setActiveTab(tabId);
      Animated.timing(tabAnim, { toValue: 1, duration: 200, useNativeDriver: true }).start();
    });
  };

  useEffect(() => {
    Animated.timing(tabAnim, { toValue: 1, duration: 300, useNativeDriver: true }).start();
  }, []);

  return (
    <View style={[styles.container, { paddingTop: isDesktop ? 0 : insets.top }]}>
      <StatusBar barStyle="light-content" />

      {/* Back */}
      <TouchableOpacity
        style={[styles.backBtn, { top: isDesktop ? 10 : insets.top + 10 }]}
        onPress={() => router.back()}
      >
        <Text style={styles.backIcon}>←</Text>
      </TouchableOpacity>

      {/* Score header (always visible) */}
      <ScoreHeader match={match} />

      {/* Tab bar */}
      <View style={styles.tabBar}>
        <ScrollView horizontal showsHorizontalScrollIndicator={false} contentContainerStyle={styles.tabBarContent}>
          {TABS.map(tab => (
            <TouchableOpacity
              key={tab.id}
              style={[styles.tab, activeTab === tab.id && styles.tabActive]}
              onPress={() => handleTabChange(tab.id)}
            >
              <Text style={styles.tabIcon}>{tab.icon}</Text>
              <Text style={[styles.tabLabel, activeTab === tab.id && styles.tabLabelActive]}>
                {tab.label}
              </Text>
              {activeTab === tab.id && (
                <LinearGradient
                  colors={[colors.green.neon, colors.green.mid]}
                  start={{ x: 0, y: 0 }} end={{ x: 1, y: 0 }}
                  style={styles.tabActiveLine}
                />
              )}
            </TouchableOpacity>
          ))}
        </ScrollView>
      </View>

      {/* Tab content */}
      <Animated.View style={[styles.tabContentWrapper, { opacity: tabAnim }]}>
        {activeTab === 'summary'  && <SummaryTab  match={match} />}
        {activeTab === 'stats'    && <StatsTab    match={match} />}
        {activeTab === 'h2h'      && <H2HTab      match={match} />}
        {activeTab === 'ai'       && <AiTab       match={match} />}
        {activeTab === 'lastfive' && <LastFiveTab match={match} />}
      </Animated.View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: colors.bg.primary },

  backBtn: {
    position: 'absolute',
    left: spacing.md,
    zIndex: 10,
    width: 36, height: 36,
    borderRadius: radius.sm,
    backgroundColor: 'rgba(0,0,0,0.6)',
    alignItems: 'center', justifyContent: 'center',
  },
  backIcon: { fontSize: 18, color: colors.text.primary },

  // ── Score header ──
  scoreHeader: {
    paddingTop: 48,
    paddingBottom: spacing.md,
    paddingHorizontal: spacing.md,
    alignItems: 'center',
    borderBottomWidth: 1,
    borderBottomColor: colors.bg.border,
  },
  scoreMeta: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.xs,
    marginBottom: spacing.xs,
  },
  dot: {
    width: 3, height: 3, borderRadius: 1.5,
    backgroundColor: colors.text.muted,
  },
  scoreMetaTournament: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  scoreMetaRound: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 10,
    color: colors.text.secondary,
  },
  scoreMetaDate: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  liveChip: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 5,
    backgroundColor: 'rgba(255,59,59,0.15)',
    paddingHorizontal: 12, paddingVertical: 4,
    borderRadius: radius.full,
    borderWidth: 1,
    borderColor: 'rgba(255,59,59,0.4)',
    marginBottom: spacing.sm,
  },
  liveDot: {
    width: 6, height: 6, borderRadius: 3,
    backgroundColor: colors.accent.live,
  },
  liveChipText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 11,
    color: colors.accent.live,
    letterSpacing: 1,
  },
  ftChip: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 11,
    color: colors.text.muted,
    letterSpacing: 1,
    marginBottom: spacing.sm,
  },
  upcomingChip: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 14,
    color: colors.blue.light,
    marginBottom: spacing.sm,
  },
  scoresRow: {
    flexDirection: 'row',
    alignItems: 'center',
    width: '100%',
    marginBottom: spacing.xs,
  },
  teamBlock: {
    flex: 1,
    alignItems: 'flex-start',
  },
  teamBlockRight: {
    alignItems: 'flex-end',
  },
  teamBlockWinner: {},
  teamPlayer: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.text.secondary,
    marginBottom: 2,
  },
  teamPlayer2: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
  },
  teamPlayerWinner: {
    color: colors.text.primary,
  },
  setsRow: {
    flexDirection: 'row',
    gap: 4,
    marginTop: 4,
  },
  rightRow: { justifyContent: 'flex-end' },
  setNum: {
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.muted,
    minWidth: 14,
    textAlign: 'center',
  },
  mainScore: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    paddingHorizontal: spacing.md,
  },
  mainScoreNum: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['4xl'],
    color: colors.text.primary,
    lineHeight: fontSize['4xl'] + 4,
  },
  mainScoreDash: {
    fontFamily: 'Inter_300Light',
    fontSize: fontSize['2xl'],
    color: colors.text.muted,
  },
  viewsChip: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    marginTop: spacing.xs,
  },
  rightText: { textAlign: 'right' },

  // ── Tab bar ──
  tabBar: {
    borderBottomWidth: 1,
    borderBottomColor: colors.bg.border,
    backgroundColor: colors.bg.secondary,
  },
  tabBarContent: {
    paddingHorizontal: spacing.sm,
  },
  tab: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 5,
    paddingHorizontal: 14,
    paddingVertical: 12,
    position: 'relative',
  },
  tabActive: {},
  tabIcon: { fontSize: 13 },
  tabLabel: {
    fontFamily: 'Inter_500Medium',
    fontSize: 12,
    color: colors.text.muted,
    letterSpacing: 0.3,
  },
  tabLabelActive: {
    color: colors.green.neon,
    fontFamily: 'Inter_600SemiBold',
  },
  tabActiveLine: {
    position: 'absolute',
    bottom: 0, left: 0, right: 0,
    height: 2,
    borderRadius: 1,
  },

  // ── Tab content ──
  tabContentWrapper: {
    flex: 1,
  },
  tabContent: {
    flex: 1,
    paddingHorizontal: spacing.md,
    paddingTop: spacing.md,
  },

  // ── Summary tab ──
  summaryTeams: {
    flexDirection: 'row',
    alignItems: 'flex-start',
    gap: spacing.xs,
    marginBottom: spacing.md,
  },
  teamCard: {
    flex: 1,
    backgroundColor: colors.bg.card,
    borderRadius: radius.md,
    padding: spacing.sm,
    borderWidth: 1,
    borderColor: colors.bg.border,
    overflow: 'hidden',
  },
  teamCardWinner: {
    borderColor: colors.green.dark,
  },
  teamCardRight: {
    alignItems: 'flex-end',
  },
  tcFlag: { fontSize: 18, marginBottom: 4 },
  tcPlayer: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 12,
    color: colors.text.primary,
    marginBottom: 2,
  },
  tcPlayer2: {
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.secondary,
  },
  winnerLabel: {
    fontFamily: 'Inter_700Bold',
    fontSize: 9,
    color: colors.green.neon,
    marginTop: 4,
    letterSpacing: 0.5,
  },
  vsSep: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingTop: 20,
  },
  vsText: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 11,
    color: colors.text.muted,
    letterSpacing: 2,
  },
  card: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
    marginBottom: spacing.md,
  },
  cardTitle: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.sm,
    color: colors.text.primary,
    marginBottom: spacing.sm,
    letterSpacing: 0.3,
  },
  readoutBody: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.secondary,
    lineHeight: 20,
  },
  summaryGrid: { gap: 2 },
  kvRow: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 7,
    borderBottomWidth: 1,
    borderBottomColor: colors.bg.border,
  },
  kvVal: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.sm,
    color: colors.text.secondary,
    width: 40,
    textAlign: 'right',
  },
  kvRight: { textAlign: 'left' },
  kvGreen: { color: colors.green.neon },
  kvBlue:  { color: colors.blue.light },
  kvCenter: {
    flex: 1,
    alignItems: 'center',
    gap: 2,
  },
  kvIcon: { fontSize: 14 },
  kvLabel: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    textAlign: 'center',
  },

  // ── Stats tab ──
  statsHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: spacing.md,
    gap: spacing.xs,
  },
  statsTeam1: {
    flex: 1,
    fontFamily: 'Inter_600SemiBold',
    fontSize: 11,
    color: colors.green.neon,
  },
  statsMid: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 11,
    color: colors.text.muted,
    letterSpacing: 2,
  },
  statsTeam2: {
    flex: 1,
    fontFamily: 'Inter_600SemiBold',
    fontSize: 11,
    color: colors.blue.light,
    textAlign: 'right',
  },

  // ── H2H tab ──
  h2hSummary: { marginBottom: spacing.md },
  h2hCard: {
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  h2hResult: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: spacing.sm,
  },
  h2hTeamStat: { flex: 1, gap: 4 },
  h2hWins: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['3xl'],
    color: colors.green.neon,
  },
  h2hTeamName: {
    fontFamily: 'Inter_500Medium',
    fontSize: 11,
    color: colors.text.secondary,
  },
  h2hCenter: {
    alignItems: 'center',
    paddingHorizontal: spacing.md,
  },
  h2hTotal: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['2xl'],
    color: colors.text.primary,
  },
  h2hTotalLabel: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  h2hBar: {
    flexDirection: 'row',
    height: 6,
    borderRadius: 3,
    overflow: 'hidden',
  },
  h2hBarFill1: { borderRadius: 3 },
  h2hBarFill2: { borderRadius: 3 },
  h2hHistTitle: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.sm,
    color: colors.text.primary,
    marginBottom: spacing.sm,
  },
  h2hMatchRow: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.md,
    padding: spacing.sm,
    marginBottom: spacing.sm,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  h2hDate: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  h2hTournament: {
    fontFamily: 'Inter_500Medium',
    fontSize: 11,
    color: colors.text.secondary,
    marginBottom: 4,
  },
  h2hScoreRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.xs,
    marginBottom: 3,
  },
  h2hPlayer: {
    flex: 1,
    fontFamily: 'Inter_500Medium',
    fontSize: 11,
    color: colors.text.primary,
  },
  h2hScore: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.lg,
    color: colors.text.primary,
    paddingHorizontal: spacing.xs,
  },
  h2hWinner: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 10,
    letterSpacing: 0.3,
  },

  featureRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    marginVertical: 4,
  },
  featureLabel: {
    width: 120,
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.muted,
  },
  featureTrack: {
    flex: 1,
    height: 6,
    backgroundColor: colors.bg.elevated,
    borderRadius: 3,
    overflow: 'hidden',
  },
  featureFill: {
    height: '100%',
    borderRadius: 3,
    overflow: 'hidden',
  },
  featurePct: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 11,
    color: colors.green.neon,
    width: 32,
    textAlign: 'right',
  },
  predictionLoading: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: spacing.xl * 2,
    gap: spacing.md,
  },
  predictionLoadingText: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.sm,
    color: colors.text.muted,
  },
  noPrediction: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingTop: 60,
    gap: spacing.md,
  },
  noPredictionIcon: { fontSize: 40 },
  noPredictionText: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.sm,
    color: colors.text.muted,
    textAlign: 'center',
  },

  // ── Last 5 tab ──
  lastFiveSection: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
    marginBottom: spacing.md,
  },
  lastFiveHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    marginBottom: spacing.sm,
  },
  lastFiveAccent: {
    width: 3, height: 16,
    borderRadius: 2,
  },
  lastFiveTitle: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.text.primary,
  },
  formRow: {
    flexDirection: 'row',
    gap: spacing.xs,
    marginBottom: spacing.md,
  },
  formBadge: {
    width: 36, height: 36,
    borderRadius: radius.sm,
    alignItems: 'center',
    justifyContent: 'center',
    borderWidth: 1,
  },
  formText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 13,
  },
  lastFiveMatch: {
    paddingVertical: 8,
    borderBottomWidth: 1,
    borderBottomColor: colors.bg.border,
    gap: 2,
  },
  lastFiveMeta: {
    flexDirection: 'row',
    gap: spacing.sm,
    alignItems: 'center',
  },
  lastFiveDate: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  lastFiveTourney: {
    flex: 1,
    fontFamily: 'Inter_500Medium',
    fontSize: 11,
    color: colors.text.secondary,
  },
  lastFiveResult: {},
  lastFiveScore: {
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.muted,
  },

  emptyText: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.sm,
    color: colors.text.muted,
    textAlign: 'center',
    padding: spacing.lg,
  },
});
