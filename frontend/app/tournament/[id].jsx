import React, { useState } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity, StyleSheet,
  StatusBar, Animated, Dimensions,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { LinearGradient } from 'expo-linear-gradient';
import { useLocalSearchParams, useRouter } from 'expo-router';
import { colors, fontSize, radius, spacing, shadow } from '../../src/theme';
import MatchCard from '../../src/components/MatchCard';
import { useWarehouseData } from '../../src/context/WarehouseDataContext';
import { useResponsive } from '../../src/hooks/useResponsive';

const { width } = Dimensions.get('window');

const CATEGORY_COLORS = {
  WORLD: [colors.green.dark, colors.green.mid],
  MAJOR: ['#7A4500', '#C47000'],
  P1:    [colors.blue.deep, colors.blue.mid],
  GOLD:  ['#5A3A00', '#A06A00'],
  RISE:  ['#6A2A00', '#C05000'],
};

function RoundTab({ label, active, onPress }) {
  return (
    <TouchableOpacity
      onPress={onPress}
      style={[styles.roundTab, active && styles.roundTabActive]}
    >
      <Text style={[styles.roundTabText, active && styles.roundTabTextActive]}>{label}</Text>
    </TouchableOpacity>
  );
}

function InfoRow({ icon, label, value }) {
  return (
    <View style={styles.infoRow}>
      <Text style={styles.infoIcon}>{icon}</Text>
      <Text style={styles.infoLabel}>{label}</Text>
      <Text style={styles.infoValue}>{value}</Text>
    </View>
  );
}

export default function TournamentDetail() {
  const { id } = useLocalSearchParams();
  const router  = useRouter();
  const insets  = useSafeAreaInsets();
  const { isDesktop } = useResponsive();

  const { getTournamentById, getMatchesByTournament } = useWarehouseData();
  const tournament = getTournamentById(id);
  const matches    = getMatchesByTournament(id);

  const rounds  = [...new Set(matches.map(m => m.round))];
  const [activeRound, setActiveRound] = useState('All');

  if (!tournament) {
    return (
      <View style={styles.container}>
        <Text style={styles.errorText}>Tournament not found</Text>
      </View>
    );
  }

  const gradColors = CATEGORY_COLORS[tournament.category] || [colors.blue.deep, colors.blue.mid];

  const filteredMatches = activeRound === 'All'
    ? matches
    : matches.filter(m => m.round === activeRound);

  // Group by round
  const groupedByRound = filteredMatches.reduce((acc, m) => {
    if (!acc[m.round]) acc[m.round] = [];
    acc[m.round].push(m);
    return acc;
  }, {});

  return (
    <View style={[styles.container, { paddingTop: isDesktop ? 0 : insets.top }]}>
      <StatusBar barStyle="light-content" />

      {/* Back button */}
      <TouchableOpacity
        style={[styles.backBtn, { top: isDesktop ? 12 : insets.top + 12 }]}
        onPress={() => router.back()}
      >
        <Text style={styles.backIcon}>←</Text>
      </TouchableOpacity>

      <ScrollView
        showsVerticalScrollIndicator={false}
        contentContainerStyle={[
          { paddingBottom: 100 },
          isDesktop && { maxWidth: 1100, alignSelf: 'center', width: '100%' },
        ]}
      >

        {/* ── HERO HEADER ─────────────────────────────────────────── */}
        <LinearGradient
          colors={[...gradColors, colors.bg.primary]}
          start={{ x: 0, y: 0 }}
          end={{ x: 1, y: 1 }}
          style={styles.hero}
        >
          <View style={styles.heroContent}>
            <View style={styles.heroBadgeRow}>
              <View style={[styles.catBadge]}>
                <Text style={styles.catText}>{tournament.category}</Text>
              </View>
              {tournament.status !== 'upcoming' && (
                <View style={styles.surfaceBadge}>
                  <Text style={styles.surfaceText}>{tournament.surface}</Text>
                </View>
              )}
            </View>
            <Text style={styles.heroFlag}>{countryFlag(tournament.country)}</Text>
            <Text style={styles.heroName}>{tournament.name}</Text>
            <Text style={styles.heroLoc}>{tournament.location}</Text>
            <Text style={styles.heroDates}>{tournament.startDate} – {tournament.endDate}</Text>

            {/* Prize */}
            <View style={styles.prizeRow}>
              <Text style={styles.prizeIcon}>🏆</Text>
              <Text style={styles.prizeVal}>{tournament.prize}</Text>
            </View>
          </View>
        </LinearGradient>

        {/* ── STATS GRID ──────────────────────────────────────────── */}
        <View style={styles.statsGrid}>
          <StatBox icon="🎾" label="Total Matches" value={tournament.totalMatches} />
          <StatBox icon="🔴" label="Live Now"       value={tournament.liveMatches} highlight={tournament.liveMatches > 0} />
          <StatBox icon="📊" label="Rounds"          value={rounds.length} />
          <StatBox icon="💰" label="Prize Pool"      value={tournament.prize} small />
        </View>

        {/* ── TOURNAMENT INFO ──────────────────────────────────────── */}
        <View style={styles.infoCard}>
          <Text style={styles.cardTitle}>Tournament Info</Text>
          <InfoRow icon="📍" label="Location"  value={tournament.location} />
          <InfoRow icon="🎾" label="Surface"   value={tournament.surface} />
          <InfoRow icon="📅" label="Start"     value={tournament.startDate} />
          <InfoRow icon="🏁" label="End"       value={tournament.endDate} />
          <InfoRow icon="🌍" label="Category"  value={tournament.category} />
        </View>

        {/* ── ROUNDS FILTER ────────────────────────────────────────── */}
        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Matches</Text>
          <ScrollView
            horizontal
            showsHorizontalScrollIndicator={false}
            style={styles.roundScroll}
            contentContainerStyle={styles.roundScrollContent}
          >
            <RoundTab label="All" active={activeRound === 'All'} onPress={() => setActiveRound('All')} />
            {rounds.map(r => (
              <RoundTab key={r} label={r} active={activeRound === r} onPress={() => setActiveRound(r)} />
            ))}
          </ScrollView>
        </View>

        {/* ── MATCH LIST ───────────────────────────────────────────── */}
        {Object.entries(groupedByRound).map(([round, roundMatches]) => (
          <View key={round} style={styles.roundGroup}>
            <View style={styles.roundHeader}>
              <View style={styles.roundLine} />
              <Text style={styles.roundLabel}>{round}</Text>
              <View style={styles.roundLine} />
            </View>
            {roundMatches.map(m => <MatchCard key={m.id} match={m} />)}
          </View>
        ))}

        {filteredMatches.length === 0 && (
          <Text style={styles.emptyText}>No matches in this round</Text>
        )}

      </ScrollView>
    </View>
  );
}

function StatBox({ icon, label, value, highlight = false, small = false }) {
  return (
    <View style={[styles.statBox, highlight && styles.statBoxHighlight]}>
      <Text style={styles.statIcon}>{icon}</Text>
      <Text style={[styles.statValue, highlight && styles.statValueHighlight, small && { fontSize: fontSize.sm }]}>
        {value}
      </Text>
      <Text style={styles.statLabel}>{label}</Text>
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
  backBtn: {
    position: 'absolute',
    left: spacing.md,
    zIndex: 10,
    width: 36,
    height: 36,
    borderRadius: radius.sm,
    backgroundColor: 'rgba(0,0,0,0.5)',
    alignItems: 'center',
    justifyContent: 'center',
  },
  backIcon: {
    fontSize: 18,
    color: colors.text.primary,
  },
  errorText: {
    color: colors.text.secondary,
    textAlign: 'center',
    marginTop: 100,
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.md,
  },

  // ── Hero ──
  hero: {
    paddingTop: 60,
    paddingBottom: spacing.xl,
  },
  heroContent: {
    paddingHorizontal: spacing.lg,
    alignItems: 'flex-start',
  },
  heroBadgeRow: {
    flexDirection: 'row',
    gap: spacing.xs,
    marginBottom: spacing.sm,
  },
  catBadge: {
    backgroundColor: 'rgba(255,255,255,0.15)',
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: radius.full,
    borderWidth: 1,
    borderColor: 'rgba(255,255,255,0.3)',
  },
  catText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 10,
    color: colors.text.primary,
    letterSpacing: 1.5,
  },
  surfaceBadge: {
    backgroundColor: 'rgba(255,255,255,0.1)',
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: radius.full,
  },
  surfaceText: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: 'rgba(255,255,255,0.7)',
  },
  heroFlag: {
    fontSize: 42,
    marginBottom: spacing.xs,
  },
  heroName: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['2xl'],
    color: colors.text.primary,
    lineHeight: fontSize['2xl'] + 4,
    marginBottom: 4,
  },
  heroLoc: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.sm,
    color: 'rgba(255,255,255,0.7)',
    marginBottom: 2,
  },
  heroDates: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: 'rgba(255,255,255,0.5)',
    marginBottom: spacing.sm,
  },
  prizeRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.xs,
    backgroundColor: 'rgba(255,255,255,0.1)',
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: radius.full,
  },
  prizeIcon: { fontSize: 14 },
  prizeVal: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.sm,
    color: colors.accent.gold,
  },

  // ── Stats grid ──
  statsGrid: {
    flexDirection: 'row',
    padding: spacing.md,
    gap: spacing.sm,
    marginTop: -spacing.md,
  },
  statBox: {
    flex: 1,
    backgroundColor: colors.bg.card,
    borderRadius: radius.md,
    padding: spacing.sm,
    alignItems: 'center',
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  statBoxHighlight: {
    borderColor: 'rgba(255,59,59,0.4)',
    backgroundColor: 'rgba(255,59,59,0.08)',
  },
  statIcon: { fontSize: 16, marginBottom: 2 },
  statValue: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.xl,
    color: colors.text.primary,
    lineHeight: fontSize.xl + 4,
  },
  statValueHighlight: {
    color: colors.accent.live,
  },
  statLabel: {
    fontFamily: 'Inter_400Regular',
    fontSize: 9,
    color: colors.text.muted,
    textAlign: 'center',
  },

  // ── Info card ──
  infoCard: {
    backgroundColor: colors.bg.card,
    marginHorizontal: spacing.md,
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
  infoRow: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 8,
    borderBottomWidth: 1,
    borderBottomColor: colors.bg.border,
    gap: spacing.sm,
  },
  infoIcon: { fontSize: 14, width: 20, textAlign: 'center' },
  infoLabel: {
    flex: 1,
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
  },
  infoValue: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.xs,
    color: colors.text.secondary,
  },

  // ── Rounds ──
  section: {
    marginBottom: spacing.sm,
  },
  sectionTitle: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.md,
    color: colors.text.primary,
    paddingHorizontal: spacing.md,
    marginBottom: spacing.sm,
  },
  roundScroll: { marginBottom: spacing.sm },
  roundScrollContent: {
    paddingHorizontal: spacing.md,
    gap: spacing.xs,
  },
  roundTab: {
    paddingHorizontal: 14,
    paddingVertical: 7,
    borderRadius: radius.full,
    backgroundColor: colors.bg.elevated,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  roundTabActive: {
    backgroundColor: colors.green.muted,
    borderColor: colors.green.dark,
  },
  roundTabText: {
    fontFamily: 'Inter_500Medium',
    fontSize: fontSize.xs,
    color: colors.text.muted,
  },
  roundTabTextActive: {
    color: colors.green.neon,
  },
  roundGroup: {
    marginBottom: spacing.sm,
  },
  roundHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: spacing.md,
    marginBottom: spacing.xs,
    gap: spacing.sm,
  },
  roundLine: {
    flex: 1,
    height: 1,
    backgroundColor: colors.bg.border,
  },
  roundLabel: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.xs,
    color: colors.text.muted,
    letterSpacing: 1,
    textTransform: 'uppercase',
  },
  emptyText: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.sm,
    color: colors.text.muted,
    textAlign: 'center',
    padding: spacing.xl,
  },
});
