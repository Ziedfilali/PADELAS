import React, { useState, useEffect, useRef } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity, StyleSheet,
  Animated, StatusBar, Dimensions, RefreshControl,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { LinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import { colors, fontSize, radius, spacing, shadow } from '../src/theme';
import MatchCard from '../src/components/MatchCard';
import PadelScene from '../src/components/PadelScene';
import { useResponsive } from '../src/hooks/useResponsive';
import { useWarehouseData } from '../src/context/WarehouseDataContext';
import DesktopHome from '../src/layouts/DesktopHome';
import PairVersusPlayground from '../src/components/dashboard/PairVersusPlayground';

const { width } = Dimensions.get('window');

const TOURNAMENT_LIST_PREVIEW = 6;
const PADEL_SCHEDULE_PREVIEW = 4;

const CATEGORY_COLORS = {
  WORLD: { bg: colors.green.muted,  text: colors.green.neon,  border: colors.green.dark },
  MAJOR: { bg: 'rgba(245,197,24,0.15)', text: colors.accent.gold,  border: 'rgba(245,197,24,0.4)' },
  P1:    { bg: colors.blue.muted,   text: colors.blue.light,  border: colors.blue.deep },
  GOLD:  { bg: 'rgba(245,197,24,0.1)',  text: colors.accent.gold,  border: 'rgba(245,197,24,0.3)' },
  RISE:  { bg: 'rgba(255,125,38,0.12)', text: colors.accent.orange, border: 'rgba(255,125,38,0.3)' },
};

function CategoryBadge({ category }) {
  const c = CATEGORY_COLORS[category] || CATEGORY_COLORS.P1;
  return (
    <View style={[styles.catBadge, { backgroundColor: c.bg, borderColor: c.border }]}>
      <Text style={[styles.catText, { color: c.text }]}>{category}</Text>
    </View>
  );
}

function TournamentCard({ tournament, onPress }) {
  const pressAnim = useRef(new Animated.Value(1)).current;
  return (
    <Animated.View style={{ transform: [{ scale: pressAnim }] }}>
      <TouchableOpacity
        activeOpacity={1}
        onPressIn={() => Animated.spring(pressAnim, { toValue: 0.96, useNativeDriver: true }).start()}
        onPressOut={() => Animated.spring(pressAnim, { toValue: 1, useNativeDriver: true }).start()}
        onPress={onPress}
        style={styles.tournamentCard}
      >
        <View style={styles.tcLeft}>
          <View style={styles.tcFlag}>
            <Text style={styles.tcFlagText}>{countryFlag(tournament.country)}</Text>
          </View>
          <View style={styles.tcInfo}>
            <Text style={styles.tcName} numberOfLines={1}>{tournament.name}</Text>
            <Text style={styles.tcLocation}>{tournament.location}</Text>
            <Text style={styles.tcDate}>
              {tournament.startDate} → {tournament.endDate}
            </Text>
          </View>
        </View>
        <View style={styles.tcRight}>
          <CategoryBadge category={tournament.category} />
          {tournament.status === 'live' || tournament.liveMatches > 0 ? (
            <View style={styles.liveIndicator}>
              <View style={styles.liveDot} />
              <Text style={styles.liveText}>LIVE</Text>
            </View>
          ) : tournament.status === 'upcoming' ? (
            <Text style={styles.upcomingText}>Soon</Text>
          ) : (
            <Text style={styles.finishedText}>FT</Text>
          )}
        </View>
      </TouchableOpacity>
    </Animated.View>
  );
}

function LiveMatchBanner({ match }) {
  const router = useRouter();
  const pulseAnim = useRef(new Animated.Value(1)).current;

  useEffect(() => {
    Animated.loop(
      Animated.sequence([
        Animated.timing(pulseAnim, { toValue: 1.02, duration: 800, useNativeDriver: true }),
        Animated.timing(pulseAnim, { toValue: 1,    duration: 800, useNativeDriver: true }),
      ])
    ).start();
  }, []);

  return (
    <Animated.View style={{ transform: [{ scale: pulseAnim }], marginHorizontal: spacing.md }}>
      <TouchableOpacity
        onPress={() => router.push(`/match/${match.id}`)}
        activeOpacity={0.9}
      >
        <LinearGradient
          colors={['#1A0808', '#2A0A0A', '#0A0A0A']}
          style={styles.liveBanner}
        >
          <View style={styles.liveBannerHeader}>
            <View style={styles.liveIndicator}>
              <View style={styles.liveDot} />
              <Text style={styles.liveText}>LIVE NOW</Text>
            </View>
            <Text style={styles.liveTournament}>{match.tournamentName}</Text>
            <Text style={styles.liveRound}>{match.round}</Text>
          </View>
          <View style={styles.liveBannerBody}>
            <View style={styles.liveTeam}>
              <Text style={styles.livePlayer1}>{match.team1.player1}</Text>
              <Text style={styles.livePlayer2}>{match.team1.player2}</Text>
            </View>
            <View style={styles.liveScore}>
              <Text style={styles.liveScoreNum}>{match.team1.score}</Text>
              <Text style={styles.liveScoreDash}>–</Text>
              <Text style={styles.liveScoreNum}>{match.team2.score}</Text>
            </View>
            <View style={styles.liveTeam}>
              <Text style={[styles.livePlayer1, styles.rightAlign]}>{match.team2.player1}</Text>
              <Text style={[styles.livePlayer2, styles.rightAlign]}>{match.team2.player2}</Text>
            </View>
          </View>
          <Text style={styles.liveSetDetail}>
            Sets: {match.team1.sets?.join('-')} · {match.team2.sets?.join('-')}
          </Text>

          {/* AI prediction bar */}
          {match.mlPrediction && (
            <View style={styles.liveAiBar}>
              <Text style={styles.liveAiLabel}>Prediction: {Math.round(match.mlPrediction.team1Probability * 100)}% favor pair 1</Text>
              <View style={styles.liveAiTrack}>
                <View
                  style={[
                    styles.liveAiFill,
                    { width: `${match.mlPrediction.team1Probability * 100}%` },
                  ]}
                />
              </View>
            </View>
          )}
        </LinearGradient>
      </TouchableOpacity>
    </Animated.View>
  );
}

function SectionHeader({ title, subtitle, onSeeAll }) {
  return (
    <View style={styles.sectionHeader}>
      <View style={styles.sectionTitleRow}>
        <View style={styles.sectionAccent} />
        <View style={{ flex: 1 }}>
          <Text style={styles.sectionTitle}>{title}</Text>
          {subtitle ? (
            <Text style={styles.sectionSubtitle}>{subtitle}</Text>
          ) : null}
        </View>
      </View>
      {onSeeAll && (
        <TouchableOpacity onPress={onSeeAll}>
          <Text style={styles.seeAll}>See all</Text>
        </TouchableOpacity>
      )}
    </View>
  );
}

function StatsStrip({ tournamentsCount, matchesCount, playersCount, source }) {
  const items = [
    { label: 'Tournaments', value: String(tournamentsCount ?? '—'), icon: '🏆' },
    { label: 'Matches',     value: String(matchesCount ?? '—'),     icon: '🎾' },
    { label: 'Players',     value: String(playersCount ?? '—'),     icon: '👤' },
    {
      label: 'Data',
      value: source === 'warehouse' ? 'DB' : 'Demo',
      icon: '📡',
    },
  ];
  return (
    <ScrollView horizontal showsHorizontalScrollIndicator={false} style={styles.statsStrip} contentContainerStyle={styles.statsStripContent}>
      {items.map(item => (
        <View key={item.label} style={styles.statChip}>
          <Text style={styles.statIcon}>{item.icon}</Text>
          <Text style={styles.statValue}>{item.value}</Text>
          <Text style={styles.statLabel}>{item.label}</Text>
        </View>
      ))}
    </ScrollView>
  );
}

export default function HomeScreen() {
  // All hooks must be called unconditionally before any early returns
  const { isDesktop } = useResponsive();
  const router        = useRouter();
  const insets        = useSafeAreaInsets();
  const [refreshing, setRefreshing]   = useState(false);
  const [activeFilter, setActiveFilter] = useState('all');
  const [tournamentsExpanded, setTournamentsExpanded] = useState(false);
  const [padelExpanded, setPadelExpanded] = useState(false);
  const headerAnim = useRef(new Animated.Value(0)).current;

  const {
    tournaments,
    matches,
    players,
    source,
    refresh,
    getLiveMatches,
    getRecentMatches,
    padelScheduleMatches,
    padelScheduleMeta,
  } = useWarehouseData();

  const showPadelCards = padelScheduleMatches.length > 0;
  const showPadelError = Boolean(padelScheduleMeta?.error);
  const showPadelEmptyOk =
    Boolean(padelScheduleMeta?.loadedAt) &&
    !padelScheduleMeta?.error &&
    padelScheduleMatches.length === 0;
  const showPadelBand = showPadelCards || showPadelError || showPadelEmptyOk;

  const padelList = padelScheduleMatches;
  const padelShownList = padelExpanded ? padelList : padelList.slice(0, PADEL_SCHEDULE_PREVIEW);
  const padelHasMore = padelList.length > PADEL_SCHEDULE_PREVIEW;

  const liveMatches   = getLiveMatches();
  const recentMatches = getRecentMatches(6);

  const tournamentListOverflow = tournaments.length > TOURNAMENT_LIST_PREVIEW;
  const tournamentsShown =
    tournamentsExpanded || !tournamentListOverflow
      ? tournaments
      : tournaments.slice(0, TOURNAMENT_LIST_PREVIEW);

  useEffect(() => {
    if (!isDesktop) {
      Animated.timing(headerAnim, { toValue: 1, duration: 800, useNativeDriver: true }).start();
    }
  }, [isDesktop]);

  // ── Desktop: completely separate layout ──
  if (isDesktop) return <DesktopHome />;

  const filters = [
    { id: 'all',      label: 'All' },
    { id: 'live',     label: '🔴 Live' },
    { id: 'finished', label: 'Finished' },
    { id: 'upcoming', label: 'Upcoming' },
  ];

  const filteredMatches = activeFilter === 'all'
    ? recentMatches
    : recentMatches.filter(m => m.status === activeFilter);

  const onRefresh = async () => {
    setRefreshing(true);
    try {
      await refresh();
    } finally {
      setRefreshing(false);
    }
  };

  return (
    <View style={[styles.container, { paddingTop: insets.top }]}>
      <StatusBar barStyle="light-content" backgroundColor={colors.bg.primary} />

      <ScrollView
        showsVerticalScrollIndicator={false}
        refreshControl={
          <RefreshControl
            refreshing={refreshing}
            onRefresh={onRefresh}
            tintColor={colors.green.neon}
            colors={[colors.green.neon]}
          />
        }
        contentContainerStyle={{ paddingBottom: 100 }}
      >

        {/* ── HERO ─────────────────────────────────────────────────── */}
        <View style={styles.hero}>
          <PadelScene height={200} />

          <Animated.View
            style={[
              styles.heroOverlay,
              {
                opacity:   headerAnim,
                transform: [{ translateY: headerAnim.interpolate({ inputRange: [0, 1], outputRange: [20, 0] }) }],
              },
            ]}
          >
            <LinearGradient
              colors={['transparent', 'rgba(7,9,15,0.7)', colors.bg.primary]}
              style={StyleSheet.absoluteFill}
            />
            <View style={styles.heroContent}>
              <View style={styles.heroBadge}>
                <View style={styles.heroDot} />
                <Text style={styles.heroBadgeText}>INTERNATIONAL PADEL FEDERATION</Text>
              </View>
              <Text style={styles.heroTitle}>FIP Analytics</Text>
              <Text style={styles.heroSub}>Match analytics & audience insight</Text>
            </View>
          </Animated.View>
        </View>

        {/* ── STATS STRIP ──────────────────────────────────────────── */}
        <StatsStrip
          tournamentsCount={tournaments.length}
          matchesCount={matches.length}
          playersCount={players.length}
          source={source}
        />

        {showPadelBand && (
          <View style={styles.section}>
            <SectionHeader
              title="This month — live & scheduled"
              subtitle={
                showPadelError
                  ? padelScheduleMeta.error
                  : showPadelEmptyOk
                    ? 'No scheduled/live rows from Padel API for this month'
                    : undefined
              }
            />
            {showPadelCards ? (
              <>
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
              </>
            ) : null}
          </View>
        )}

        <View style={[styles.section, { paddingHorizontal: 0 }]}>
          <SectionHeader title="Prediction playground" />
          <PairVersusPlayground />
        </View>

        {/* ── LIVE MATCHES ─────────────────────────────────────────── */}
        {liveMatches.length > 0 && (
          <View style={styles.section}>
            <SectionHeader title="Live Now" />
            {liveMatches.map(m => <LiveMatchBanner key={m.id} match={m} />)}
          </View>
        )}

        {/* ── TOURNAMENTS ──────────────────────────────────────────── */}
        <View style={styles.section}>
          <SectionHeader title="Tournaments" onSeeAll={() => {}} />
          {tournamentsShown.map(t => (
            <TournamentCard
              key={t.id}
              tournament={t}
              onPress={() => router.push(`/tournament/${t.id}`)}
            />
          ))}
          {tournamentListOverflow ? (
            <TouchableOpacity
              style={styles.tournamentsSeeMore}
              onPress={() => setTournamentsExpanded(e => !e)}
              activeOpacity={0.85}
            >
              <Text style={styles.tournamentsSeeMoreText}>
                {tournamentsExpanded
                  ? 'Show less'
                  : `See more (${tournaments.length - TOURNAMENT_LIST_PREVIEW} more)`}
              </Text>
            </TouchableOpacity>
          ) : null}
        </View>

        {/* ── RECENT MATCHES ───────────────────────────────────────── */}
        <View style={styles.section}>
          <SectionHeader title="Matches" />

          {/* Filter pills */}
          <ScrollView
            horizontal
            showsHorizontalScrollIndicator={false}
            style={styles.filtersScroll}
            contentContainerStyle={styles.filtersContent}
          >
            {filters.map(f => (
              <TouchableOpacity
                key={f.id}
                style={[styles.filterPill, activeFilter === f.id && styles.filterPillActive]}
                onPress={() => setActiveFilter(f.id)}
              >
                <Text style={[styles.filterText, activeFilter === f.id && styles.filterTextActive]}>
                  {f.label}
                </Text>
              </TouchableOpacity>
            ))}
          </ScrollView>

          {filteredMatches.map(m => <MatchCard key={m.id} match={m} />)}
          {filteredMatches.length === 0 && (
            <Text style={styles.emptyText}>No matches for this filter</Text>
          )}
        </View>

      </ScrollView>
    </View>
  );
}

function countryFlag(code) {
  const flags = { AR:'🇦🇷', ES:'🇪🇸', QA:'🇶🇦', FR:'🇫🇷', CO:'🇨🇴', MA:'🇲🇦' };
  return flags[code] || '🌍';
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.bg.primary,
  },

  // ── Hero ──
  hero: {
    position: 'relative',
    height: 200,
  },
  heroOverlay: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    height: 200,
    justifyContent: 'flex-end',
  },
  heroContent: {
    padding: spacing.md,
    paddingBottom: spacing.lg,
  },
  heroBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 6,
    marginBottom: 6,
  },
  heroDot: {
    width: 6,
    height: 6,
    borderRadius: 3,
    backgroundColor: colors.green.neon,
  },
  heroBadgeText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 9,
    color: colors.green.neon,
    letterSpacing: 2,
  },
  heroTitle: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['3xl'],
    color: colors.text.primary,
    lineHeight: fontSize['3xl'] + 4,
  },
  heroSub: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.sm,
    color: colors.text.secondary,
  },

  // ── Stats strip ──
  statsStrip: {
    marginTop: -spacing.sm,
    marginBottom: spacing.xs,
  },
  statsStripContent: {
    paddingHorizontal: spacing.md,
    gap: spacing.sm,
  },
  statChip: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.md,
    paddingHorizontal: spacing.md,
    paddingVertical: spacing.sm,
    alignItems: 'center',
    borderWidth: 1,
    borderColor: colors.bg.border,
    minWidth: 80,
  },
  statIcon: { fontSize: 18, marginBottom: 2 },
  statValue: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.xl,
    color: colors.green.neon,
    lineHeight: fontSize.xl + 4,
  },
  statLabel: {
    fontFamily: 'Inter_400Regular',
    fontSize: 9,
    color: colors.text.muted,
    letterSpacing: 0.5,
  },

  // ── Sections ──
  section: {
    marginTop: spacing.lg,
  },
  sectionHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingHorizontal: spacing.md,
    marginBottom: spacing.sm,
  },
  sectionTitleRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
  },
  sectionAccent: {
    width: 3,
    height: 16,
    borderRadius: 2,
    backgroundColor: colors.green.neon,
  },
  sectionTitle: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.md,
    color: colors.text.primary,
    letterSpacing: 0.2,
  },
  sectionSubtitle: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
    marginTop: 4,
  },
  seeAll: {
    fontFamily: 'Inter_500Medium',
    fontSize: fontSize.xs,
    color: colors.green.neon,
  },

  // ── Tournament card ──
  tournamentCard: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    backgroundColor: colors.bg.card,
    marginHorizontal: spacing.md,
    marginVertical: spacing.xs,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
    ...shadow.sm,
  },
  tcLeft: {
    flex: 1,
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
  },
  tcFlag: {
    width: 36,
    height: 36,
    borderRadius: radius.sm,
    backgroundColor: colors.bg.elevated,
    alignItems: 'center',
    justifyContent: 'center',
  },
  tcFlagText: { fontSize: 20 },
  tcInfo: { flex: 1 },
  tcName: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.text.primary,
    marginBottom: 1,
  },
  tcLocation: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.secondary,
  },
  tcDate: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    marginTop: 1,
  },
  tcRight: {
    alignItems: 'flex-end',
    gap: spacing.xs,
  },
  catBadge: {
    paddingHorizontal: 8,
    paddingVertical: 3,
    borderRadius: radius.full,
    borderWidth: 1,
  },
  catText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 10,
    letterSpacing: 1,
  },
  tournamentsSeeMore: {
    marginHorizontal: spacing.md,
    marginTop: spacing.sm,
    paddingVertical: 12,
    alignItems: 'center',
    borderRadius: radius.md,
    backgroundColor: colors.bg.elevated,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  tournamentsSeeMoreText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.green.neon,
  },
  padelSeeMore: {
    marginHorizontal: spacing.md,
    marginTop: spacing.sm,
    paddingVertical: 12,
    alignItems: 'center',
    borderRadius: radius.md,
    backgroundColor: colors.bg.elevated,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  padelSeeMoreText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.green.neon,
  },
  liveIndicator: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4,
    backgroundColor: 'rgba(255,59,59,0.15)',
    paddingHorizontal: 7,
    paddingVertical: 3,
    borderRadius: radius.full,
  },
  liveDot: {
    width: 6,
    height: 6,
    borderRadius: 3,
    backgroundColor: colors.accent.live,
  },
  liveText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 9,
    color: colors.accent.live,
    letterSpacing: 1,
  },
  upcomingText: {
    fontFamily: 'Inter_500Medium',
    fontSize: 10,
    color: colors.blue.light,
  },
  finishedText: {
    fontFamily: 'Inter_500Medium',
    fontSize: 10,
    color: colors.text.muted,
  },

  // ── Live banner ──
  liveBanner: {
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: 'rgba(255,59,59,0.3)',
    marginBottom: spacing.sm,
  },
  liveBannerHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    marginBottom: spacing.sm,
  },
  liveTournament: {
    flex: 1,
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.secondary,
  },
  liveRound: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.xs,
    color: colors.text.muted,
  },
  liveBannerBody: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginBottom: spacing.xs,
  },
  liveTeam: {
    flex: 1,
    gap: 2,
  },
  livePlayer1: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.text.primary,
  },
  livePlayer2: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.secondary,
  },
  rightAlign: { textAlign: 'right' },
  liveScore: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    paddingHorizontal: spacing.md,
  },
  liveScoreNum: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['3xl'],
    color: colors.text.primary,
    lineHeight: fontSize['3xl'] + 4,
  },
  liveScoreDash: {
    fontFamily: 'Inter_300Light',
    fontSize: fontSize.xl,
    color: colors.text.muted,
  },
  liveSetDetail: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    textAlign: 'center',
    marginBottom: spacing.sm,
  },
  liveAiBar: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    paddingTop: spacing.xs,
    borderTopWidth: 1,
    borderTopColor: 'rgba(255,59,59,0.2)',
  },
  liveAiLabel: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 10,
    color: colors.green.neon,
  },
  liveAiTrack: {
    flex: 1,
    height: 4,
    backgroundColor: colors.bg.border,
    borderRadius: 2,
    overflow: 'hidden',
  },
  liveAiFill: {
    height: '100%',
    backgroundColor: colors.green.neon,
    borderRadius: 2,
  },

  // ── Filters ──
  filtersScroll: {
    marginBottom: spacing.sm,
  },
  filtersContent: {
    paddingHorizontal: spacing.md,
    gap: spacing.xs,
  },
  filterPill: {
    paddingHorizontal: 14,
    paddingVertical: 7,
    borderRadius: radius.full,
    backgroundColor: colors.bg.elevated,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  filterPillActive: {
    backgroundColor: colors.green.muted,
    borderColor: colors.green.dark,
  },
  filterText: {
    fontFamily: 'Inter_500Medium',
    fontSize: fontSize.xs,
    color: colors.text.muted,
  },
  filterTextActive: {
    color: colors.green.neon,
  },
  emptyText: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.sm,
    color: colors.text.muted,
    textAlign: 'center',
    padding: spacing.xl,
  },
});
