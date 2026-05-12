import React, { useState, useRef, useEffect } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity, StyleSheet,
  Animated, Dimensions,
} from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import { colors, fontSize, radius, spacing, shadow } from '../theme';
import PadelScene from '../components/PadelScene';
import { useWarehouseData } from '../context/WarehouseDataContext';
import PairVersusPlayground from '../components/dashboard/PairVersusPlayground';
import MatchCard from '../components/MatchCard';
const { width: WIN_W } = Dimensions.get('window');

/** Initial rows shown in the tournaments table before expanding */
const TOURNAMENT_TABLE_PREVIEW = 6;

const PADEL_SCHEDULE_PREVIEW = 4;

// ─── HELPERS ──────────────────────────────────────────────────────────────────
function countryFlag(code) {
  const f = { AR:'🇦🇷', ES:'🇪🇸', QA:'🇶🇦', FR:'🇫🇷', CO:'🇨🇴', MA:'🇲🇦' };
  return f[code] || '🌍';
}

// ─── HERO SECTION ─────────────────────────────────────────────────────────────
function HeroSection() {
  const textAnim  = useRef(new Animated.Value(0)).current;
  const statsAnim = useRef(new Animated.Value(0)).current;
  const router = useRouter();
  const { getLiveMatches, matches, players, tournaments } = useWarehouseData();

  useEffect(() => {
    Animated.sequence([
      Animated.timing(textAnim,  { toValue: 1, duration: 900, useNativeDriver: true }),
      Animated.timing(statsAnim, { toValue: 1, duration: 600, useNativeDriver: true }),
    ]).start();
  }, []);

  const liveMatches = getLiveMatches();

  return (
    <View style={styles.hero}>
      {/* 3D padel court bg */}
      <View style={StyleSheet.absoluteFill}>
        <PadelScene height="100%" />
      </View>

      {/* Dark overlay gradient */}
      <LinearGradient
        colors={['rgba(7,9,15,0.2)', 'rgba(7,9,15,0.6)', 'rgba(7,9,15,0.95)']}
        start={{ x: 0, y: 0 }} end={{ x: 0, y: 1 }}
        style={StyleSheet.absoluteFill}
      />
      {/* Left-to-right fade */}
      <LinearGradient
        colors={['rgba(7,9,15,0.85)', 'transparent']}
        start={{ x: 0, y: 0.5 }} end={{ x: 0.6, y: 0.5 }}
        style={StyleSheet.absoluteFill}
      />

      <View style={styles.heroInner}>
        {/* Left: Text */}
        <Animated.View
          style={[
            styles.heroLeft,
            {
              opacity: textAnim,
              transform: [{ translateX: textAnim.interpolate({ inputRange: [0,1], outputRange: [-40, 0] }) }],
            },
          ]}
        >
          <View style={styles.heroBadgeRow}>
            <View style={styles.heroBadge}>
              <View style={styles.heroBadgeDot} />
              <Text style={styles.heroBadgeText}>INTERNATIONAL PADEL FEDERATION</Text>
            </View>
          </View>

          <Text style={styles.heroTitle}>FIP{'\n'}Analytics</Text>
          <Text style={styles.heroSub}>
            Tournaments, ranking, and recent results — one place for the federation desk.
          </Text>

          {/* CTA buttons */}
          <View style={styles.heroCtas}>
            <TouchableOpacity style={styles.ctaPrimary} onPress={() => router.push('/')} activeOpacity={0.9}>
              <LinearGradient
                colors={[colors.green.mid, colors.green.neon]}
                start={{ x: 0, y: 0 }} end={{ x: 1, y: 0 }}
                style={StyleSheet.absoluteFill}
              />
              <Text style={styles.ctaPrimaryText}>⚡  Explore matches</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.ctaSecondary} onPress={() => router.push('/analytics')} activeOpacity={0.85}>
              <Text style={styles.ctaSecondaryText}>📈  Viewership</Text>
            </TouchableOpacity>
          </View>

          {/* Hero stats row */}
          <Animated.View style={[styles.heroStats, { opacity: statsAnim }]}>
            {[
              { val: String(tournaments?.length ?? '—'), label: 'Tournaments' },
              { val: String(matches?.length ?? '—'), label: 'Matches tracked' },
              { val: String(players?.length ?? '—'), label: 'Players in ranking' },
            ].map(s => (
              <View key={s.label} style={styles.heroStat}>
                <Text style={styles.heroStatVal}>{s.val}</Text>
                <Text style={styles.heroStatLabel}>{s.label}</Text>
              </View>
            ))}
          </Animated.View>
        </Animated.View>

        {/* Right: Live match ticker */}
        {liveMatches.length > 0 && (
          <Animated.View style={[styles.heroRight, { opacity: textAnim }]}>
            <LiveTickerCard match={liveMatches[0]} />
          </Animated.View>
        )}
      </View>
    </View>
  );
}

function LiveTickerCard({ match }) {
  const router   = useRouter();
  const pulseAnim = useRef(new Animated.Value(1)).current;

  useEffect(() => {
    Animated.loop(
      Animated.sequence([
        Animated.timing(pulseAnim, { toValue: 1.015, duration: 800, useNativeDriver: true }),
        Animated.timing(pulseAnim, { toValue: 1,     duration: 800, useNativeDriver: true }),
      ])
    ).start();
  }, []);

  return (
    <Animated.View style={{ transform: [{ scale: pulseAnim }] }}>
      <TouchableOpacity
        onPress={() => router.push(`/match/${match.id}`)}
        activeOpacity={0.9}
        style={styles.liveTicker}
      >
        <LinearGradient
          colors={['rgba(255,59,59,0.08)', 'rgba(17,24,39,0.98)']}
          style={StyleSheet.absoluteFill}
        />
        <View style={styles.liveTickerBorder} />

        <View style={styles.liveTickerTop}>
          <View style={styles.liveChip}>
            <View style={styles.liveDot} />
            <Text style={styles.liveChipText}>LIVE</Text>
          </View>
          <Text style={styles.liveTickerTournament} numberOfLines={1}>
            {match.tournamentName}
          </Text>
        </View>

        <Text style={styles.liveTickerRound}>{match.round}</Text>

        <View style={styles.liveScoreBlock}>
          <View style={styles.liveTeamCol}>
            <Text style={styles.liveTeamName} numberOfLines={1}>{match.team1.player1}</Text>
            <Text style={styles.liveTeamName2} numberOfLines={1}>{match.team1.player2}</Text>
          </View>
          <View style={styles.liveScoreCenter}>
            <Text style={styles.liveScoreNum}>{match.team1.score}</Text>
            <Text style={styles.liveScoreDash}>–</Text>
            <Text style={styles.liveScoreNum}>{match.team2.score}</Text>
          </View>
          <View style={[styles.liveTeamCol, { alignItems: 'flex-end' }]}>
            <Text style={styles.liveTeamName} numberOfLines={1}>{match.team2.player1}</Text>
            <Text style={styles.liveTeamName2} numberOfLines={1}>{match.team2.player2}</Text>
          </View>
        </View>

        {/* Sets row */}
        <Text style={styles.liveSets}>
          Sets {match.team1.sets?.join('-')}  ·  {match.team2.sets?.join('-')}
        </Text>

        <Text style={styles.liveTickerCta}>View match details →</Text>
      </TouchableOpacity>
    </Animated.View>
  );
}

// ─── TOURNAMENT TABLE (Desktop list) ─────────────────────────────────────────
function TournamentTable({ tournaments }) {
  const router = useRouter();
  const [showAll, setShowAll] = useState(false);
  const CATEGORY_COLORS = {
    WORLD: colors.green.neon,
    MAJOR: colors.accent.gold,
    P1:    colors.blue.light,
    GOLD:  colors.accent.gold,
    RISE:  colors.accent.orange,
  };

  const hasMore = tournaments.length > TOURNAMENT_TABLE_PREVIEW;
  const rows = showAll || !hasMore
    ? tournaments
    : tournaments.slice(0, TOURNAMENT_TABLE_PREVIEW);

  return (
    <View style={styles.table}>
      {/* Table header */}
      <View style={styles.tableHeader}>
        <Text style={[styles.tableHeaderCell, { flex: 3 }]}>TOURNAMENT</Text>
        <Text style={[styles.tableHeaderCell, { flex: 1.5 }]}>LOCATION</Text>
        <Text style={[styles.tableHeaderCell, { flex: 1 }]}>CATEGORY</Text>
        <Text style={[styles.tableHeaderCell, { flex: 1 }]}>MATCHES</Text>
        <Text style={[styles.tableHeaderCell, { flex: 1 }]}>PRIZE</Text>
        <Text style={[styles.tableHeaderCell, { flex: 1 }]}>STATUS</Text>
      </View>

      {rows.map((t, i) => (
        <TouchableOpacity
          key={t.id}
          style={[styles.tableRow, i % 2 === 0 && styles.tableRowAlt]}
          onPress={() => router.push(`/tournament/${t.id}`)}
          activeOpacity={0.7}
        >
          {/* Name */}
          <View style={[styles.tableCell, { flex: 3, flexDirection: 'row', alignItems: 'center', gap: spacing.sm }]}>
            <Text style={styles.tableCellFlag}>{countryFlag(t.country)}</Text>
            <Text style={styles.tableCellMain} numberOfLines={1}>{t.name}</Text>
          </View>

          {/* Location */}
          <Text style={[styles.tableCellText, { flex: 1.5 }]} numberOfLines={1}>{t.location}</Text>

          {/* Category */}
          <View style={[styles.tableCell, { flex: 1 }]}>
            <Text style={[styles.tableCatBadge, { color: CATEGORY_COLORS[t.category] || colors.text.secondary }]}>
              {t.category}
            </Text>
          </View>

          {/* Matches */}
          <Text style={[styles.tableCellText, { flex: 1 }]}>{t.totalMatches}</Text>

          {/* Prize */}
          <Text style={[styles.tableCellText, { flex: 1, color: colors.accent.gold }]}>{t.prize}</Text>

          {/* Status */}
          <View style={[styles.tableCell, { flex: 1 }]}>
            <View style={[
              styles.statusPill,
              t.status === 'live'     && styles.statusLive,
              t.status === 'upcoming' && styles.statusUpcoming,
              t.status === 'finished' && styles.statusFinished,
            ]}>
              {t.status === 'live' && <View style={styles.statusDot} />}
              <Text style={[
                styles.statusPillText,
                t.status === 'live'     && { color: colors.accent.live },
                t.status === 'upcoming' && { color: colors.blue.light },
                t.status === 'finished' && { color: colors.text.muted },
              ]}>
                {t.status === 'live' ? 'LIVE' : t.status === 'upcoming' ? 'SOON' : 'FT'}
              </Text>
            </View>
          </View>
        </TouchableOpacity>
      ))}

      {hasMore ? (
        <TouchableOpacity
          style={styles.tableSeeMore}
          onPress={() => setShowAll(s => !s)}
          activeOpacity={0.85}
        >
          <Text style={styles.tableSeeMoreText}>
            {showAll ? 'Show less' : `See more (${tournaments.length - TOURNAMENT_TABLE_PREVIEW} more)`}
          </Text>
        </TouchableOpacity>
      ) : null}
    </View>
  );
}

// ─── RECENT MATCHES GRID ──────────────────────────────────────────────────────
function MatchGrid({ matches }) {
  const router = useRouter();

  return (
    <View style={styles.matchGrid}>
      {matches.map(m => (
        <TouchableOpacity
          key={m.id}
          style={styles.matchGridCard}
          onPress={() => router.push(`/match/${m.id}`)}
          activeOpacity={0.85}
        >
          <LinearGradient
            colors={[colors.bg.elevated, colors.bg.card]}
            style={StyleSheet.absoluteFill}
          />
          {m.status === 'live' && <View style={styles.matchGridLiveBar} />}

          <View style={styles.matchGridHeader}>
            <Text style={styles.matchGridRound}>{m.round}</Text>
            <Text style={styles.matchGridDate}>{m.date}</Text>
          </View>

          <View style={styles.matchGridBody}>
            <View style={styles.matchGridTeam}>
              <Text style={[styles.matchGridPlayer, m.winner === 'team_1' && styles.matchGridPlayerWin]} numberOfLines={1}>
                {m.team1.player1}
              </Text>
              <Text style={styles.matchGridPlayer2} numberOfLines={1}>{m.team1.player2}</Text>
            </View>
            <View style={styles.matchGridScore}>
              <Text style={[styles.matchGridScoreNum, m.winner === 'team_1' && { color: colors.green.neon }]}>
                {m.team1.score}
              </Text>
              <Text style={styles.matchGridScoreDash}>–</Text>
              <Text style={[styles.matchGridScoreNum, m.winner === 'team_2' && { color: colors.green.neon }]}>
                {m.team2.score}
              </Text>
            </View>
            <View style={[styles.matchGridTeam, { alignItems: 'flex-end' }]}>
              <Text style={[styles.matchGridPlayer, m.winner === 'team_2' && styles.matchGridPlayerWin, { textAlign: 'right' }]} numberOfLines={1}>
                {m.team2.player1}
              </Text>
              <Text style={[styles.matchGridPlayer2, { textAlign: 'right' }]} numberOfLines={1}>{m.team2.player2}</Text>
            </View>
          </View>

          <Text style={styles.matchGridViews}>
            👁 {((Number(m.views) || 0) / 1000).toFixed(0)}K
          </Text>
        </TouchableOpacity>
      ))}
    </View>
  );
}

function TopPlayersLeaderboard() {
  const { players } = useWarehouseData();
  const rows = players.slice(0, 12).map((p, idx) => ({
    rank: idx + 1,
    name: p.name,
    country: p.country,
    wr: `${Math.round((p.stats?.winRate ?? 0) * 100)}%`,
  }));

  if (rows.length === 0) {
    return <Text style={styles.insightsMuted}>No player stats loaded yet.</Text>;
  }

  return rows.map(p => (
    <View key={p.rank} style={styles.leaderRow}>
      <Text style={[styles.leaderRank, p.rank === 1 && { color: colors.accent.gold }]}>
        #{p.rank}
      </Text>
      <Text style={styles.leaderFlag}>{countryFlag(p.country)}</Text>
      <Text style={styles.leaderName} numberOfLines={1}>{p.name}</Text>
      <Text style={styles.leaderWR}>{p.wr}</Text>
    </View>
  ));
}

// ─── MAIN DESKTOP HOME ────────────────────────────────────────────────────────
export default function DesktopHome() {
  const { tournaments, getRecentMatches, players, padelScheduleMatches, padelScheduleMeta } =
    useWarehouseData();
  const [padelExpanded, setPadelExpanded] = useState(false);
  const recentMatches = getRecentMatches(6);
  const classementShown = Math.min(12, players?.length ?? 0);

  const padelList = padelScheduleMatches;
  const padelShownList = padelExpanded
    ? padelList
    : padelList.slice(0, PADEL_SCHEDULE_PREVIEW);
  const padelHasMore = padelList.length > PADEL_SCHEDULE_PREVIEW;

  const showPadelCards = padelList.length > 0;
  const showPadelError = Boolean(padelScheduleMeta?.error);
  const showPadelEmptyOk =
    Boolean(padelScheduleMeta?.loadedAt) &&
    !padelScheduleMeta?.error &&
    padelScheduleMatches.length === 0;
  const showPadelBand = showPadelCards || showPadelError || showPadelEmptyOk;

  return (
    <ScrollView
      style={styles.container}
      showsVerticalScrollIndicator={false}
      contentContainerStyle={{ paddingBottom: spacing.xxl }}
    >
      {/* ── HERO ──────────────────────────────────────────────────── */}
      <HeroSection />

      {/* ── PADEL API: THIS MONTH (live + scheduled) ────────────── */}
      {showPadelBand ? (
        <View style={styles.padelMonthBand}>
          <View style={[styles.sectionHeader, { paddingHorizontal: 60 }]}>
            <View style={styles.sectionAccent} />
            <Text style={styles.sectionTitle}>This month — live & scheduled</Text>
            <Text style={styles.sectionCount}>{padelScheduleMatches.length}</Text>
          </View>
          {showPadelError ? (
            <Text style={styles.padelMonthErr}>{padelScheduleMeta.error}</Text>
          ) : null}
          {showPadelEmptyOk ? (
            <Text style={styles.padelMonthSub}>
              No scheduled or live matches returned from Padel API for this calendar month (check API coverage or month).
            </Text>
          ) : null}
          {showPadelCards ? (
            <View style={{ paddingHorizontal: 44 }}>
              {padelShownList.map(m => (
                <MatchCard key={m.id} match={m} />
              ))}
              {padelHasMore ? (
                <TouchableOpacity
                  style={styles.padelSeeMore}
                  onPress={() => setPadelExpanded(e => !e)}
                  activeOpacity={0.85}
                >
                  <Text style={styles.padelSeeMoreText}>
                    {padelExpanded
                      ? 'Show less'
                      : `See more (${padelList.length - PADEL_SCHEDULE_PREVIEW} more)`}
                  </Text>
                </TouchableOpacity>
              ) : null}
            </View>
          ) : null}
        </View>
      ) : null}

      {/* ── MAIN GRID ─────────────────────────────────────────────── */}
      <View style={styles.mainGrid}>

        {/* Left: Tournaments + recent matches */}
        <View style={styles.leftCol}>
          <View style={styles.section}>
            <View style={styles.sectionHeader}>
              <View style={styles.sectionAccent} />
              <Text style={styles.sectionTitle}>Tournaments</Text>
              <Text style={styles.sectionCount}>{tournaments.length}</Text>
            </View>
            <TournamentTable tournaments={tournaments} />
          </View>

          <View style={styles.section}>
            <View style={styles.sectionHeader}>
              <View style={styles.sectionAccent} />
              <Text style={styles.sectionTitle}>Recent matches</Text>
            </View>
            <MatchGrid matches={recentMatches} />
          </View>
        </View>

        {/* Right: Classement only */}
        <View style={styles.rightCol}>
          <View style={styles.leaderboardCard}>
            <Text style={styles.leaderboardTitle}>Player classement</Text>
            {classementShown > 0 && (
              <Text style={styles.leaderboardHint}>Top {classementShown} by ranking in your data feed</Text>
            )}
            <TopPlayersLeaderboard />
          </View>
        </View>

      </View>

      <View style={styles.mlBand}>
        <View style={[styles.sectionHeader, { paddingHorizontal: 60 }]}>
          <View style={styles.sectionAccent} />
          <Text style={styles.sectionTitle}>Prediction playground</Text>
          <Text style={styles.sectionCount}>pair vs pair</Text>
        </View>
        <View style={{ paddingHorizontal: 60 }}>
          <PairVersusPlayground desktop />
        </View>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.bg.primary,
  },
  padelMonthBand: {
    paddingTop: spacing.lg,
    paddingBottom: spacing.md,
    borderBottomWidth: 1,
    borderBottomColor: colors.bg.border,
  },
  padelMonthSub: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.sm,
    color: colors.text.muted,
    paddingHorizontal: 60,
    marginBottom: spacing.sm,
    lineHeight: 20,
  },
  padelMonthErr: {
    fontFamily: 'Inter_500Medium',
    fontSize: fontSize.sm,
    color: colors.accent.red,
    paddingHorizontal: 60,
    marginBottom: spacing.sm,
    lineHeight: 20,
  },
  padelSeeMore: {
    alignSelf: 'center',
    marginTop: spacing.sm,
    marginBottom: spacing.xs,
    paddingVertical: spacing.sm,
    paddingHorizontal: spacing.lg,
  },
  padelSeeMoreText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.green.neon,
  },
  mlBand: {
    paddingTop: spacing.xl,
    paddingBottom: spacing.xxl,
    gap: spacing.md,
    borderTopWidth: 1,
    borderTopColor: colors.bg.border,
    marginTop: spacing.lg,
  },

  // ── Hero ──
  hero: {
    height: 480,
    position: 'relative',
    overflow: 'hidden',
  },
  heroInner: {
    flex: 1,
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 60,
    paddingTop: 40,
    paddingBottom: 40,
    gap: 60,
  },
  heroLeft: {
    flex: 1,
    gap: 0,
  },
  heroBadgeRow: {
    marginBottom: 16,
  },
  heroBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    alignSelf: 'flex-start',
    backgroundColor: colors.green.muted,
    paddingHorizontal: 12,
    paddingVertical: 5,
    borderRadius: radius.full,
    borderWidth: 1,
    borderColor: `${colors.green.neon}40`,
  },
  heroBadgeDot: {
    width: 6, height: 6,
    borderRadius: 3,
    backgroundColor: colors.green.neon,
  },
  heroBadgeText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 10,
    color: colors.green.neon,
    letterSpacing: 2,
  },
  heroTitle: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 72,
    color: colors.text.primary,
    lineHeight: 72,
    marginBottom: 16,
    textShadowColor: 'rgba(0,255,87,0.15)',
    textShadowOffset: { width: 0, height: 0 },
    textShadowRadius: 30,
  },
  heroSub: {
    fontFamily: 'Inter_400Regular',
    fontSize: 16,
    color: colors.text.secondary,
    lineHeight: 26,
    marginBottom: 32,
  },
  heroCtas: {
    flexDirection: 'row',
    gap: spacing.md,
    marginBottom: 40,
  },
  ctaPrimary: {
    height: 48,
    paddingHorizontal: 28,
    borderRadius: radius.md,
    alignItems: 'center',
    justifyContent: 'center',
    overflow: 'hidden',
    position: 'relative',
    ...shadow.green,
  },
  ctaPrimaryText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 14,
    color: colors.text.inverse,
    letterSpacing: 0.5,
  },
  ctaSecondary: {
    height: 48,
    paddingHorizontal: 28,
    borderRadius: radius.md,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'rgba(255,255,255,0.07)',
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  ctaSecondaryText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 14,
    color: colors.text.secondary,
  },
  heroStats: {
    flexDirection: 'row',
    gap: 40,
  },
  heroStat: {
    gap: 2,
  },
  heroStatVal: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 28,
    color: colors.green.neon,
    lineHeight: 32,
  },
  heroStatLabel: {
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.muted,
    letterSpacing: 1,
  },
  heroRight: {
    width: 320,
  },

  // ── Live ticker card ──
  liveTicker: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.xl,
    padding: spacing.lg,
    borderWidth: 1,
    borderColor: `rgba(255,59,59,0.25)`,
    overflow: 'hidden',
    position: 'relative',
    ...shadow.lg,
  },
  liveTickerBorder: {
    position: 'absolute',
    top: 0, left: 0, right: 0,
    height: 2,
    backgroundColor: colors.accent.live,
    opacity: 0.7,
  },
  liveTickerTop: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    marginBottom: 4,
  },
  liveChip: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 5,
    backgroundColor: 'rgba(255,59,59,0.15)',
    paddingHorizontal: 8,
    paddingVertical: 3,
    borderRadius: radius.full,
    borderWidth: 1,
    borderColor: 'rgba(255,59,59,0.4)',
  },
  liveDot: {
    width: 6, height: 6,
    borderRadius: 3,
    backgroundColor: colors.accent.live,
  },
  liveChipText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 9,
    color: colors.accent.live,
    letterSpacing: 1,
  },
  liveTickerTournament: {
    flex: 1,
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.muted,
  },
  liveTickerRound: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 12,
    color: colors.text.secondary,
    marginBottom: spacing.md,
    letterSpacing: 0.5,
  },
  liveScoreBlock: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 8,
  },
  liveTeamCol: {
    flex: 1,
    gap: 2,
  },
  liveTeamName: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 13,
    color: colors.text.primary,
  },
  liveTeamName2: {
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.muted,
  },
  liveScoreCenter: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    paddingHorizontal: spacing.md,
  },
  liveScoreNum: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 42,
    color: colors.text.primary,
    lineHeight: 46,
  },
  liveScoreDash: {
    fontFamily: 'Inter_300Light',
    fontSize: 24,
    color: colors.text.muted,
  },
  liveSets: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    textAlign: 'center',
    marginBottom: spacing.sm,
  },
  liveTickerCta: {
    fontFamily: 'Inter_500Medium',
    fontSize: 11,
    color: colors.text.muted,
    textAlign: 'right',
  },

  // ── Main grid ──
  mainGrid: {
    flexDirection: 'row',
    gap: spacing.lg,
    paddingHorizontal: 60,
    paddingTop: 40,
    alignItems: 'flex-start',
  },
  leftCol: {
    flex: 1,
    gap: 40,
  },
  rightCol: {
    width: 340,
    gap: spacing.lg,
  },

  // ── Section ──
  section: {
    gap: spacing.md,
  },
  sectionHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
  },
  sectionAccent: {
    width: 3, height: 18,
    borderRadius: 2,
    backgroundColor: colors.green.neon,
  },
  sectionTitle: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.lg,
    color: colors.text.primary,
    flex: 1,
  },
  sectionCount: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
    backgroundColor: colors.bg.elevated,
    paddingHorizontal: 10,
    paddingVertical: 3,
    borderRadius: radius.full,
  },

  // ── Tournament table ──
  table: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    overflow: 'hidden',
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  tableHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: spacing.md,
    paddingVertical: 10,
    borderBottomWidth: 1,
    borderBottomColor: colors.bg.border,
    backgroundColor: colors.bg.elevated,
  },
  tableHeaderCell: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 9,
    color: colors.text.muted,
    letterSpacing: 1.5,
  },
  tableRow: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: spacing.md,
    paddingVertical: 13,
    borderBottomWidth: 1,
    borderBottomColor: `${colors.bg.border}80`,
  },
  tableRowAlt: {
    backgroundColor: 'rgba(255,255,255,0.015)',
  },
  tableCell: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  tableCellFlag: {
    fontSize: 16,
  },
  tableCellMain: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 13,
    color: colors.text.primary,
    flex: 1,
  },
  tableCellText: {
    fontFamily: 'Inter_400Regular',
    fontSize: 12,
    color: colors.text.secondary,
  },
  tableCatBadge: {
    fontFamily: 'Inter_700Bold',
    fontSize: 10,
    letterSpacing: 1,
  },
  statusPill: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4,
    paddingHorizontal: 8,
    paddingVertical: 3,
    borderRadius: radius.full,
    alignSelf: 'flex-start',
  },
  statusLive:     { backgroundColor: 'rgba(255,59,59,0.15)' },
  statusUpcoming: { backgroundColor: colors.blue.muted },
  statusFinished: { backgroundColor: 'rgba(255,255,255,0.05)' },
  statusDot: {
    width: 5, height: 5,
    borderRadius: 2.5,
    backgroundColor: colors.accent.live,
  },
  statusPillText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 9,
    letterSpacing: 0.8,
  },
  tableSeeMore: {
    paddingVertical: 14,
    paddingHorizontal: spacing.md,
    alignItems: 'center',
    justifyContent: 'center',
    borderTopWidth: 1,
    borderTopColor: colors.bg.border,
    backgroundColor: colors.bg.elevated,
  },
  tableSeeMoreText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 13,
    color: colors.green.neon,
    letterSpacing: 0.3,
  },

  // ── Match grid ──
  matchGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    gap: spacing.md,
  },
  matchGridCard: {
    width: '47%',
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
    overflow: 'hidden',
    position: 'relative',
    ...shadow.sm,
  },
  matchGridLiveBar: {
    position: 'absolute',
    top: 0, left: 0, right: 0,
    height: 2,
    backgroundColor: colors.accent.live,
  },
  matchGridHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: spacing.sm,
  },
  matchGridRound: {
    fontFamily: 'Inter_500Medium',
    fontSize: 10,
    color: colors.text.muted,
    letterSpacing: 0.5,
  },
  matchGridDate: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  matchGridBody: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: spacing.sm,
  },
  matchGridTeam: {
    flex: 1,
    gap: 1,
  },
  matchGridPlayer: {
    fontFamily: 'Inter_500Medium',
    fontSize: 11,
    color: colors.text.secondary,
  },
  matchGridPlayerWin: {
    color: colors.text.primary,
    fontFamily: 'Inter_700Bold',
  },
  matchGridPlayer2: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  matchGridScore: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 5,
    paddingHorizontal: spacing.sm,
  },
  matchGridScoreNum: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 28,
    color: colors.text.secondary,
    lineHeight: 32,
  },
  matchGridScoreDash: {
    fontFamily: 'Inter_300Light',
    fontSize: 16,
    color: colors.text.muted,
  },
  matchGridViews: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    textAlign: 'right',
  },

  insightsMuted: {
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.muted,
  },

  // ── Leaderboard ──
  leaderboardCard: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
    gap: spacing.xs,
  },
  leaderboardTitle: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.sm,
    color: colors.text.primary,
    marginBottom: spacing.xs,
  },
  leaderboardHint: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    marginBottom: spacing.xs,
  },
  leaderRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    paddingVertical: 7,
    borderBottomWidth: 1,
    borderBottomColor: `${colors.bg.border}60`,
  },
  leaderRank: {
    fontFamily: 'Inter_700Bold',
    fontSize: 11,
    color: colors.text.muted,
    width: 24,
    textAlign: 'center',
  },
  leaderFlag: { fontSize: 14 },
  leaderName: {
    flex: 1,
    fontFamily: 'Inter_500Medium',
    fontSize: 12,
    color: colors.text.primary,
  },
  leaderWR: {
    fontFamily: 'Inter_700Bold',
    fontSize: 12,
    color: colors.green.neon,
  },
});
