import React, { useState, useRef, useEffect } from 'react';
import {
  View, Text, ScrollView, TouchableOpacity, StyleSheet,
  StatusBar, Animated,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { LinearGradient } from 'expo-linear-gradient';
import { colors, fontSize, radius, spacing } from '../src/theme';
import ClusterChart from '../src/components/charts/ClusterChart';
import { AudienceSegmentCard } from '../src/components/analytics/AudienceInsightsColumn';
import { useWarehouseData } from '../src/context/WarehouseDataContext';
import { useResponsive } from '../src/hooks/useResponsive';

/** Playing styles aligned with performer clusters (same ids 0–3 as similarity model output). */
const PLAYER_STYLE_ARCHETYPES = [
  {
    id: 0,
    name: 'Elite Attacker',
    icon: '⚡',
    color: colors.green.neon,
    desc: 'High aces, aggressive baseline play, and the strongest win-rate band on the map.',
    segmentBehavior: 'Aggressive baseline, big serves, converts pressure points.',
    segmentAction: 'Spotlight as headliners; pair with decisive tie-break narratives.',
  },
  {
    id: 1,
    name: 'Defensive Wall',
    icon: '🛡',
    color: colors.blue.light,
    desc: 'Grinding defenders — long rallies and solid returns constrain opponents.',
    segmentBehavior: 'Long rallies, reliable returns, frustrates attackers.',
    segmentAction: 'Counter-style pairings and endurance storylines resonate here.',
  },
  {
    id: 2,
    name: 'Balanced Pro',
    icon: '⚖️',
    color: colors.accent.gold,
    desc: 'All-round game without one extreme spike — the tactical middle of the field.',
    segmentBehavior: 'Adaptable tactics, steady execution across formats.',
    segmentAction: 'Strong broadcast anchors and tactical breakdown presenters.',
  },
  {
    id: 3,
    name: 'Rising Star',
    icon: '🌟',
    color: colors.accent.orange,
    desc: 'Upside-heavy profile — developing game with streaky, highlight-friendly results.',
    segmentBehavior: 'Momentum swings, big moments; outcomes still consolidating.',
    segmentAction: 'Growth arcs, young-face campaigns, and behind-the-scenes content.',
  },
];

const CLUSTER_INFO = Object.fromEntries(
  PLAYER_STYLE_ARCHETYPES.map(a => [
    a.id,
    { name: a.name, color: a.color, icon: a.icon, desc: a.desc },
  ]),
);

/** Share of roster per archetype (for segment cards); demo split when empty. */
function archetypeSharesFromPlayers(allPlayers) {
  const demoPct = [24, 28, 32, 16];
  const n = allPlayers?.length ?? 0;
  if (!n) {
    return PLAYER_STYLE_ARCHETYPES.map((a, i) => ({ ...a, pct: demoPct[i] }));
  }
  const counts = [0, 0, 0, 0];
  for (const p of allPlayers) {
    const c = Math.min(3, Math.max(0, Number(p.cluster) || 0));
    counts[c] += 1;
  }
  return PLAYER_STYLE_ARCHETYPES.map(a => ({
    ...a,
    pct: Math.round((counts[a.id] / n) * 100),
  }));
}

function countryFlag(code) {
  const flags = { AR: '🇦🇷', ES: '🇪🇸', QA: '🇶🇦', FR: '🇫🇷', CO: '🇨🇴', MA: '🇲🇦' };
  return flags[code] || '🌍';
}

function clusterCountsFromPlayers(allPlayers) {
  return [0, 1, 2, 3].map(id => ({
    id,
    name: CLUSTER_INFO[id].name,
    count: allPlayers.filter(p => p.cluster === id).length,
  }));
}

function ClusterBadge({ cluster }) {
  const info = CLUSTER_INFO[cluster];
  return (
    <View style={[mobStyles.clusterBadge, { borderColor: `${info.color}40`, backgroundColor: `${info.color}15` }]}>
      <Text style={mobStyles.clusterIcon}>{info.icon}</Text>
      <Text style={[mobStyles.clusterLabel, { color: info.color }]}>{info.name}</Text>
    </View>
  );
}

function PlayerCard({ player }) {
  const info = CLUSTER_INFO[player.cluster];
  const pressAnim = useRef(new Animated.Value(1)).current;
  const [expanded, setExpanded] = useState(false);

  return (
    <Animated.View style={{ transform: [{ scale: pressAnim }] }}>
      <TouchableOpacity
        activeOpacity={1}
        onPressIn={() => Animated.spring(pressAnim, { toValue: 0.97, useNativeDriver: true }).start()}
        onPressOut={() => Animated.spring(pressAnim, { toValue: 1, useNativeDriver: true }).start()}
        onPress={() => setExpanded(e => !e)}
        style={mobStyles.playerCard}
      >
        <View style={[mobStyles.stripe, { backgroundColor: info.color }]} />
        <View style={mobStyles.playerMain}>
          <View style={mobStyles.playerTop}>
            <View style={mobStyles.rankBubble}>
              <Text style={mobStyles.rankText}>#{player.ranking}</Text>
            </View>
            <View style={mobStyles.playerInfo}>
              <Text style={mobStyles.playerName}>{player.name}</Text>
              <Text style={mobStyles.playerCountry}>{countryFlag(player.country)} {player.country}</Text>
            </View>
            <View style={mobStyles.formRow}>
              {player.recentResults.map((r, i) => (
                <View
                  key={i}
                  style={[
                    mobStyles.formDot,
                    {
                      backgroundColor:
                        r === 'W' ? colors.green.neon :
                        r === 'L' ? colors.accent.red :
                        colors.accent.gold,
                    },
                  ]}
                />
              ))}
            </View>
            <Text style={[mobStyles.winRate, { color: info.color }]}>
              {Math.round(player.stats.winRate * 100)}%
            </Text>
          </View>
          <ClusterBadge cluster={player.cluster} />
          {expanded && (
            <View style={mobStyles.playerStats}>
              <View style={mobStyles.statsGrid}>
                <StatPill label="Aces/Match" value={player.stats.aces.toFixed(1)} color={colors.green.neon} />
                <StatPill label="Win Rate" value={`${Math.round(player.stats.winRate * 100)}%`} color={info.color} />
                <StatPill label="Dbl Faults" value={player.stats.doubleFaults.toFixed(1)} color={colors.accent.red} />
                <StatPill label="Pts Won" value={player.stats.totalPointsWon.toFixed(0)} color={colors.blue.light} />
                <StatPill label="Return Pts" value={player.stats.totalWonOnReturn.toFixed(0)} color={colors.accent.gold} />
                <StatPill label="Style group" value={info.name} color={info.color} />
              </View>
            </View>
          )}
        </View>
        <Text style={[mobStyles.expandArrow, { color: info.color }]}>{expanded ? '▲' : '▼'}</Text>
      </TouchableOpacity>
    </Animated.View>
  );
}

function StatPill({ label, value, color }) {
  return (
    <View style={mobStyles.statPill}>
      <Text style={[mobStyles.statVal, { color }]}>{value}</Text>
      <Text style={mobStyles.statLbl}>{label}</Text>
    </View>
  );
}

function DesktopPlayerRow({ player }) {
  const info = CLUSTER_INFO[player.cluster];
  const [expanded, setExpanded] = useState(false);

  return (
    <TouchableOpacity
      style={[dpStyles.playerRow, { borderColor: expanded ? `${info.color}40` : colors.bg.border }]}
      onPress={() => setExpanded(e => !e)}
      activeOpacity={0.85}
    >
      <View style={[dpStyles.playerRowAccent, { backgroundColor: info.color }]} />
      <View style={dpStyles.playerRowContent}>
        <View style={dpStyles.playerRowTop}>
          <Text style={dpStyles.playerRank}>#{player.ranking}</Text>
          <View style={{ flex: 1 }}>
            <Text style={dpStyles.playerName}>{player.name}</Text>
            <Text style={dpStyles.playerCountry}>{countryFlag(player.country)} {player.country}</Text>
          </View>
          <View style={[dpStyles.clusterBadge, { borderColor: `${info.color}40`, backgroundColor: `${info.color}15` }]}>
            <Text style={dpStyles.clusterBadgeIcon}>{info.icon}</Text>
            <Text style={[dpStyles.clusterBadgeText, { color: info.color }]}>{info.name}</Text>
          </View>
          <View style={dpStyles.formRow}>
            {player.recentResults.map((r, i) => (
              <View key={i} style={[dpStyles.formDot, {
                backgroundColor: r === 'W' ? colors.green.neon : r === 'L' ? colors.accent.red : colors.accent.gold,
              }]} />
            ))}
          </View>
          <Text style={[dpStyles.winRate, { color: info.color }]}>
            {Math.round(player.stats.winRate * 100)}%
          </Text>
          <Text style={[dpStyles.expandArrow, { color: info.color }]}>{expanded ? '▲' : '▼'}</Text>
        </View>
        {expanded && (
          <View style={dpStyles.playerStats}>
            {[
              { label: 'Aces / Match', val: player.stats.aces.toFixed(1), color: colors.green.neon },
              { label: 'Win Rate', val: `${Math.round(player.stats.winRate * 100)}%`, color: info.color },
              { label: 'Double Faults', val: player.stats.doubleFaults.toFixed(1), color: colors.accent.red },
              { label: 'Points Won', val: player.stats.totalPointsWon.toFixed(0), color: colors.blue.light },
              { label: 'Return Pts', val: player.stats.totalWonOnReturn.toFixed(0), color: colors.accent.gold },
              { label: 'Style group', val: `#${player.cluster} · ${info.name}`, color: info.color },
            ].map(s => (
              <View key={s.label} style={dpStyles.statPill}>
                <Text style={[dpStyles.statVal, { color: s.color }]}>{s.val}</Text>
                <Text style={dpStyles.statLabel}>{s.label}</Text>
              </View>
            ))}
          </View>
        )}
      </View>
    </TouchableOpacity>
  );
}

function DesktopPlayersLayout() {
  const { players: allPlayers, source } = useWarehouseData();
  const warehouseView = source === 'warehouse';
  const chartClusters = clusterCountsFromPlayers(allPlayers);
  const segmentRows = archetypeSharesFromPlayers(allPlayers);
  const chartSubtitle =
    'Win rate vs serve-impact proxy. Colour = playing style group (⚡ Elite Attacker through 🌟 Rising Star).';
  const [activeFilter, setActiveFilter] = useState('all');
  const filteredPlayers =
    activeFilter === 'all'
      ? allPlayers
      : allPlayers.filter(p => p.cluster === parseInt(activeFilter, 10));
  const filterOptions = [
    { id: 'all', label: 'All styles' },
    ...PLAYER_STYLE_ARCHETYPES.map(a => ({ id: String(a.id), label: `${a.icon} ${a.name}` })),
  ];

  return (
    <ScrollView
      style={dpStyles.container}
      showsVerticalScrollIndicator={false}
      contentContainerStyle={{ paddingBottom: 60 }}
    >
      <View style={dpStyles.pageHeader}>
        <LinearGradient colors={[`${colors.green.neon}10`, 'transparent']} style={StyleSheet.absoluteFill} />
        <View style={dpStyles.headerBadge}>
          <Text style={dpStyles.headerBadgeText}>
            {warehouseView ? 'LIVE DATA' : 'SAMPLE DATA'}
          </Text>
        </View>
        <Text style={dpStyles.headerTitle}>Viewer Profiles</Text>
        <Text style={dpStyles.headerSub}>
          {`${allPlayers.length} players · Four playing styles (Elite Attacker, Defensive Wall, Balanced Pro, Rising Star) · `}
          {warehouseView ? 'From your match warehouse' : 'Illustrative sample'}
        </Text>
        <View style={dpStyles.clusterLegend}>
          {Object.entries(CLUSTER_INFO).map(([id, info]) => {
            const count = allPlayers.filter(p => p.cluster === parseInt(id, 10)).length;
            return (
              <View key={id} style={[dpStyles.legendPill, { borderColor: `${info.color}40`, backgroundColor: `${info.color}12` }]}>
                <Text style={dpStyles.legendIcon}>{info.icon}</Text>
                <Text style={[dpStyles.legendName, { color: info.color }]}>{info.name}</Text>
                <Text style={dpStyles.legendCount}>{count}</Text>
              </View>
            );
          })}
        </View>
      </View>

      <View style={{ paddingHorizontal: 48, marginBottom: spacing.lg }}>
        <View style={dpStyles.sectionHeader}>
          <View style={dpStyles.sectionAccent} />
          <Text style={dpStyles.sectionTitle}>Playing-style segments</Text>
        </View>
        <ScrollView horizontal showsHorizontalScrollIndicator={false} contentContainerStyle={{ gap: spacing.sm }}>
          {segmentRows.map(s => (
            <View key={s.id} style={{ width: 260 }}>
              <AudienceSegmentCard
                name={s.name}
                pct={s.pct}
                behavior={s.segmentBehavior}
                action={s.segmentAction}
                accent={s.color}
                shareLabel="of roster"
              />
            </View>
          ))}
        </ScrollView>
      </View>

      <View style={dpStyles.mainGrid}>
        <View style={dpStyles.leftCol}>
          <View style={dpStyles.sectionHeader}>
            <View style={dpStyles.sectionAccent} />
            <Text style={dpStyles.sectionTitle}>Playing-style map</Text>
          </View>
          <View style={dpStyles.chartCard}>
            <ClusterChart players={allPlayers} clusters={chartClusters} subtitle={chartSubtitle} />
          </View>
          <View style={dpStyles.sectionHeader}>
            <View style={dpStyles.sectionAccent} />
            <Text style={dpStyles.sectionTitle}>Group overview</Text>
          </View>
          <View style={dpStyles.clusterGrid}>
            {[0, 1, 2, 3].map(id => {
              const info = CLUSTER_INFO[id];
              const grp = allPlayers.filter(p => p.cluster === id);
              const avgWR = grp.length ? grp.reduce((s, p) => s + p.stats.winRate, 0) / grp.length : 0;
              const avgAces = grp.length ? grp.reduce((s, p) => s + p.stats.aces, 0) / grp.length : 0;
              return (
                <View key={id} style={[dpStyles.clusterCard, { borderColor: `${info.color}40` }]}>
                  <LinearGradient colors={[`${info.color}12`, 'transparent']} style={StyleSheet.absoluteFill} />
                  <Text style={dpStyles.clusterCardIcon}>{info.icon}</Text>
                  <Text style={[dpStyles.clusterCardName, { color: info.color }]}>{info.name}</Text>
                  <Text style={dpStyles.clusterCardDesc}>{info.desc}</Text>
                  <View style={dpStyles.clusterCardStats}>
                    <Text style={dpStyles.clusterCardStat}>{grp.length} players</Text>
                    <Text style={[dpStyles.clusterCardStat, { color: info.color }]}>{Math.round(avgWR * 100)}% avg WR</Text>
                    <Text style={dpStyles.clusterCardStat}>{avgAces.toFixed(1)} aces / match</Text>
                  </View>
                </View>
              );
            })}
          </View>
        </View>

        <View style={dpStyles.rightCol}>
          <View style={dpStyles.sectionHeader}>
            <View style={dpStyles.sectionAccent} />
            <Text style={dpStyles.sectionTitle}>Players ({filteredPlayers.length})</Text>
          </View>
          <View style={dpStyles.filterRow}>
            {filterOptions.map(f => (
              <TouchableOpacity
                key={f.id}
                style={[dpStyles.filterPill, activeFilter === f.id && dpStyles.filterActive]}
                onPress={() => setActiveFilter(f.id)}
              >
                <Text style={[dpStyles.filterText, activeFilter === f.id && dpStyles.filterTextActive]}>{f.label}</Text>
              </TouchableOpacity>
            ))}
          </View>
          <View style={{ gap: spacing.sm }}>
            {filteredPlayers.map(p => (
              <DesktopPlayerRow key={p.id} player={p} />
            ))}
          </View>
        </View>
      </View>
    </ScrollView>
  );
}

export default function PlayersScreen() {
  const insets = useSafeAreaInsets();
  const { isDesktop } = useResponsive();
  const headerAnim = useRef(new Animated.Value(0)).current;
  const { players: allPlayers, source } = useWarehouseData();
  const [activeFilter, setActiveFilter] = useState('all');
  const warehouseView = source === 'warehouse';
  const chartClusters = clusterCountsFromPlayers(allPlayers);
  const segmentRows = archetypeSharesFromPlayers(allPlayers);
  const chartSubtitle =
    'Win rate vs serve activity. Colour = style: ⚡ Elite Attacker, 🛡 Defensive Wall, ⚖️ Balanced Pro, 🌟 Rising Star.';
  const filteredPlayers =
    activeFilter === 'all'
      ? allPlayers
      : allPlayers.filter(p => p.cluster === parseInt(activeFilter, 10));
  const filterOptions = [
    { id: 'all', label: 'All styles' },
    ...PLAYER_STYLE_ARCHETYPES.map(a => ({ id: String(a.id), label: `${a.icon} ${a.name}` })),
  ];

  useEffect(() => {
    if (!isDesktop) {
      Animated.timing(headerAnim, { toValue: 1, duration: 700, useNativeDriver: true }).start();
    }
  }, [isDesktop]);

  if (isDesktop) return <DesktopPlayersLayout />;

  return (
    <View style={[mobStyles.container, { paddingTop: insets.top }]}>
      <StatusBar barStyle="light-content" />
      <ScrollView showsVerticalScrollIndicator={false} contentContainerStyle={{ paddingBottom: 100 }}>
        <Animated.View
          style={[
            mobStyles.header,
            {
              opacity: headerAnim,
              transform: [{ translateY: headerAnim.interpolate({ inputRange: [0, 1], outputRange: [20, 0] }) }],
            },
          ]}
        >
          <LinearGradient colors={[colors.bg.secondary, colors.bg.primary]} style={mobStyles.headerGrad} />
          <View style={mobStyles.headerBadge}>
            <Text style={mobStyles.headerBadgeText}>{warehouseView ? 'LIVE DATA' : 'SAMPLE DATA'}</Text>
          </View>
          <Text style={mobStyles.headerTitle}>Viewer Profiles</Text>
          <Text style={mobStyles.headerSub}>
            {`${allPlayers.length} players · Styles: Elite Attacker, Defensive Wall, Balanced Pro, Rising Star · `}
            {warehouseView ? 'From your match warehouse' : 'Illustrative sample'}
          </Text>
          <View style={mobStyles.metricRow}>
            <View style={mobStyles.metricChip}>
              <Text style={mobStyles.metricLabel}>Players listed</Text>
              <Text style={mobStyles.metricVal}>{allPlayers.length}</Text>
            </View>
            <View style={mobStyles.metricChip}>
              <Text style={mobStyles.metricLabel}>Avg matches each</Text>
              <Text style={mobStyles.metricVal}>
                {allPlayers.length
                  ? (allPlayers.reduce((s, p) => s + (p.stats.matchesPlayed ?? 0), 0) / allPlayers.length).toFixed(1)
                  : '—'}
              </Text>
            </View>
          </View>
        </Animated.View>

        <View style={mobStyles.section}>
          <Text style={mobStyles.sectionTitle}>Playing-style segments</Text>
          <ScrollView horizontal showsHorizontalScrollIndicator={false} contentContainerStyle={{ gap: spacing.sm }}>
            {segmentRows.map(s => (
              <View key={s.id} style={{ width: 240 }}>
                <AudienceSegmentCard
                  name={s.name}
                  pct={s.pct}
                  behavior={s.segmentBehavior}
                  action={s.segmentAction}
                  accent={s.color}
                  shareLabel="of roster"
                />
              </View>
            ))}
          </ScrollView>
        </View>

        <View style={mobStyles.section}>
          <Text style={mobStyles.sectionTitle}>Playing-style map</Text>
          <ClusterChart players={allPlayers} clusters={chartClusters} subtitle={chartSubtitle} />
        </View>

        <View style={mobStyles.section}>
          <Text style={mobStyles.sectionTitle}>Group overview</Text>
          {[0, 1, 2, 3].map(id => {
            const info = CLUSTER_INFO[id];
            const grp = allPlayers.filter(p => p.cluster === id);
            const avgWinRate = grp.length ? grp.reduce((s, p) => s + p.stats.winRate, 0) / grp.length : 0;
            return (
              <View key={id} style={[mobStyles.clusterSummary, { borderColor: `${info.color}40` }]}>
                <LinearGradient colors={[`${info.color}18`, 'transparent']} style={StyleSheet.absoluteFill} />
                <View style={mobStyles.cSumHeader}>
                  <Text style={mobStyles.cSumIcon}>{info.icon}</Text>
                  <View style={{ flex: 1 }}>
                    <Text style={[mobStyles.cSumName, { color: info.color }]}>{info.name}</Text>
                    <Text style={mobStyles.cSumDesc}>{info.desc}</Text>
                  </View>
                </View>
                <View style={mobStyles.cSumStats}>
                  <Text style={mobStyles.cSumCount}>{grp.length} players</Text>
                  <Text style={[mobStyles.cSumWR, { color: info.color }]}>Avg {Math.round(avgWinRate * 100)}% win rate</Text>
                </View>
              </View>
            );
          })}
        </View>

        <View style={mobStyles.section}>
          <Text style={mobStyles.sectionTitle}>Players by group</Text>
          <ScrollView horizontal showsHorizontalScrollIndicator={false} style={mobStyles.filtersScroll} contentContainerStyle={mobStyles.filtersContent}>
            {filterOptions.map(f => (
              <TouchableOpacity
                key={f.id}
                style={[mobStyles.filterPill, activeFilter === f.id && mobStyles.filterActive]}
                onPress={() => setActiveFilter(f.id)}
              >
                <Text style={[mobStyles.filterText, activeFilter === f.id && mobStyles.filterTextActive]}>{f.label}</Text>
              </TouchableOpacity>
            ))}
          </ScrollView>
          <Text style={mobStyles.playerCount}>{filteredPlayers.length} players</Text>
          {filteredPlayers.map(p => (
            <PlayerCard key={p.id} player={p} />
          ))}
        </View>
      </ScrollView>
    </View>
  );
}

const dpStyles = StyleSheet.create({
  container: { flex: 1, backgroundColor: colors.bg.primary },
  pageHeader: {
    paddingHorizontal: 48,
    paddingTop: 40,
    paddingBottom: 28,
    position: 'relative',
    overflow: 'hidden',
  },
  headerBadge: {
    backgroundColor: colors.green.muted,
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: 100,
    alignSelf: 'flex-start',
    marginBottom: 8,
    borderWidth: 1,
    borderColor: `${colors.green.neon}30`,
  },
  headerBadgeText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 9,
    color: colors.green.neon,
    letterSpacing: 2,
  },
  headerTitle: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 48,
    color: colors.text.primary,
    lineHeight: 52,
    marginBottom: 4,
  },
  headerSub: {
    fontFamily: 'Inter_400Regular',
    fontSize: 13,
    color: colors.text.muted,
    marginBottom: 20,
  },
  clusterLegend: { flexDirection: 'row', gap: spacing.sm, flexWrap: 'wrap' },
  legendPill: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 6,
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: 100,
    borderWidth: 1,
  },
  legendIcon: { fontSize: 12 },
  legendName: { fontFamily: 'Inter_600SemiBold', fontSize: 11 },
  legendCount: { fontFamily: 'Inter_700Bold', fontSize: 11, color: colors.text.muted },
  mainGrid: {
    flexDirection: 'row',
    gap: spacing.lg,
    paddingHorizontal: 48,
    alignItems: 'flex-start',
  },
  leftCol: { flex: 1, gap: 0 },
  rightCol: { width: 380, gap: 0 },
  sectionHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    marginBottom: spacing.md,
    marginTop: spacing.lg,
  },
  sectionAccent: { width: 3, height: 18, borderRadius: 2, backgroundColor: colors.green.neon },
  sectionTitle: { fontFamily: 'Inter_700Bold', fontSize: fontSize.md, color: colors.text.primary },
  chartCard: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    borderWidth: 1,
    borderColor: colors.bg.border,
    overflow: 'hidden',
  },
  clusterGrid: { flexDirection: 'row', flexWrap: 'wrap', gap: spacing.md, marginBottom: spacing.md },
  clusterCard: {
    width: '47.5%',
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    overflow: 'hidden',
    position: 'relative',
    gap: 4,
  },
  clusterCardIcon: { fontSize: 24, marginBottom: 4 },
  clusterCardName: { fontFamily: 'Inter_700Bold', fontSize: 13 },
  clusterCardDesc: {
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.muted,
    lineHeight: 16,
    marginBottom: 6,
  },
  clusterCardStats: { flexDirection: 'row', gap: spacing.sm, flexWrap: 'wrap' },
  clusterCardStat: {
    fontFamily: 'Inter_500Medium',
    fontSize: 10,
    color: colors.text.muted,
    backgroundColor: colors.bg.elevated,
    paddingHorizontal: 6,
    paddingVertical: 2,
    borderRadius: radius.xs,
  },
  filterRow: { flexDirection: 'row', flexWrap: 'wrap', gap: spacing.xs, marginBottom: spacing.md },
  filterPill: {
    paddingHorizontal: 14,
    paddingVertical: 6,
    borderRadius: 100,
    backgroundColor: colors.bg.elevated,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  filterActive: { backgroundColor: colors.green.muted, borderColor: colors.green.dark },
  filterText: { fontFamily: 'Inter_500Medium', fontSize: 11, color: colors.text.muted },
  filterTextActive: { color: colors.green.neon, fontFamily: 'Inter_600SemiBold' },
  playerRow: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    borderWidth: 1,
    overflow: 'hidden',
    flexDirection: 'row',
  },
  playerRowAccent: { width: 3, alignSelf: 'stretch' },
  playerRowContent: { flex: 1, padding: spacing.md, gap: spacing.xs },
  playerRowTop: { flexDirection: 'row', alignItems: 'center', gap: spacing.sm },
  playerRank: {
    fontFamily: 'Inter_700Bold',
    fontSize: 11,
    color: colors.text.muted,
    width: 28,
    textAlign: 'center',
  },
  playerName: { fontFamily: 'Inter_600SemiBold', fontSize: 13, color: colors.text.primary },
  playerCountry: { fontFamily: 'Inter_400Regular', fontSize: 10, color: colors.text.muted },
  clusterBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4,
    paddingHorizontal: 8,
    paddingVertical: 3,
    borderRadius: 100,
    borderWidth: 1,
  },
  clusterBadgeIcon: { fontSize: 10 },
  clusterBadgeText: { fontFamily: 'Inter_600SemiBold', fontSize: 10 },
  formRow: { flexDirection: 'row', gap: 3 },
  formDot: { width: 8, height: 8, borderRadius: 4 },
  winRate: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.lg,
    lineHeight: fontSize.lg + 2,
    marginLeft: 'auto',
  },
  expandArrow: { fontSize: 10, marginLeft: spacing.xs },
  playerStats: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    gap: spacing.xs,
    marginTop: spacing.sm,
    paddingTop: spacing.sm,
    borderTopWidth: 1,
    borderTopColor: colors.bg.border,
  },
  statPill: {
    backgroundColor: colors.bg.elevated,
    borderRadius: radius.sm,
    paddingHorizontal: spacing.sm,
    paddingVertical: spacing.xs,
    alignItems: 'center',
    minWidth: 80,
  },
  statVal: { fontFamily: 'Rajdhani_700Bold', fontSize: fontSize.md, lineHeight: fontSize.md + 4 },
  statLabel: { fontFamily: 'Inter_400Regular', fontSize: 9, color: colors.text.muted },
});

const mobStyles = StyleSheet.create({
  container: { flex: 1, backgroundColor: colors.bg.primary },
  header: {
    paddingHorizontal: spacing.md,
    paddingTop: spacing.md,
    paddingBottom: spacing.lg,
    position: 'relative',
    overflow: 'hidden',
  },
  headerGrad: { ...StyleSheet.absoluteFillObject },
  headerBadge: {
    backgroundColor: colors.green.muted,
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: radius.full,
    alignSelf: 'flex-start',
    marginBottom: spacing.xs,
  },
  headerBadgeText: { fontFamily: 'Inter_700Bold', fontSize: 9, color: colors.green.neon, letterSpacing: 2 },
  headerTitle: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['2xl'],
    color: colors.text.primary,
    lineHeight: fontSize['2xl'] + 4,
    marginBottom: 2,
  },
  headerSub: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
    marginBottom: spacing.md,
  },
  metricRow: { flexDirection: 'row', gap: spacing.sm },
  metricChip: {
    flex: 1,
    backgroundColor: colors.bg.card,
    borderRadius: radius.md,
    padding: spacing.sm,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  metricLabel: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    marginBottom: 2,
  },
  metricVal: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.xl,
    color: colors.green.neon,
    lineHeight: fontSize.xl + 4,
  },
  section: { paddingHorizontal: spacing.md, marginBottom: spacing.lg },
  sectionTitle: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.md,
    color: colors.text.primary,
    marginBottom: spacing.sm,
  },
  clusterSummary: {
    borderRadius: radius.lg,
    padding: spacing.md,
    marginBottom: spacing.sm,
    borderWidth: 1,
    overflow: 'hidden',
    position: 'relative',
  },
  cSumHeader: { flexDirection: 'row', alignItems: 'flex-start', gap: spacing.sm, marginBottom: spacing.xs },
  cSumIcon: { fontSize: 22 },
  cSumName: { fontFamily: 'Inter_700Bold', fontSize: fontSize.sm },
  cSumDesc: { fontFamily: 'Inter_400Regular', fontSize: fontSize.xs, color: colors.text.muted, marginTop: 2 },
  cSumStats: { flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' },
  cSumCount: { fontFamily: 'Inter_400Regular', fontSize: fontSize.xs, color: colors.text.muted },
  cSumWR: { fontFamily: 'Inter_700Bold', fontSize: fontSize.xs },
  filtersScroll: { marginBottom: spacing.sm },
  filtersContent: { gap: spacing.xs, paddingRight: spacing.sm },
  filterPill: {
    paddingHorizontal: 14,
    paddingVertical: 7,
    borderRadius: radius.full,
    backgroundColor: colors.bg.elevated,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  filterActive: { backgroundColor: colors.green.muted, borderColor: colors.green.dark },
  filterText: { fontFamily: 'Inter_500Medium', fontSize: fontSize.xs, color: colors.text.muted },
  filterTextActive: { color: colors.green.neon },
  playerCount: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
    marginBottom: spacing.xs,
  },
  playerCard: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    marginBottom: spacing.sm,
    borderWidth: 1,
    borderColor: colors.bg.border,
    flexDirection: 'row',
    alignItems: 'flex-start',
    overflow: 'hidden',
  },
  stripe: { width: 3, alignSelf: 'stretch' },
  playerMain: { flex: 1, padding: spacing.sm, gap: spacing.xs },
  playerTop: { flexDirection: 'row', alignItems: 'center', gap: spacing.sm },
  rankBubble: {
    width: 28,
    height: 28,
    borderRadius: radius.xs,
    backgroundColor: colors.bg.elevated,
    alignItems: 'center',
    justifyContent: 'center',
  },
  rankText: { fontFamily: 'Inter_700Bold', fontSize: 10, color: colors.text.muted },
  playerInfo: { flex: 1 },
  playerName: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.text.primary,
    marginBottom: 1,
  },
  playerCountry: { fontFamily: 'Inter_400Regular', fontSize: 10, color: colors.text.muted },
  formRow: { flexDirection: 'row', gap: 3 },
  formDot: { width: 8, height: 8, borderRadius: 4 },
  winRate: { fontFamily: 'Rajdhani_700Bold', fontSize: fontSize.lg, lineHeight: fontSize.lg + 4 },
  expandArrow: { padding: spacing.sm, fontSize: 10 },
  clusterBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 5,
    paddingHorizontal: 8,
    paddingVertical: 4,
    borderRadius: radius.full,
    borderWidth: 1,
    alignSelf: 'flex-start',
  },
  clusterIcon: { fontSize: 11 },
  clusterLabel: { fontFamily: 'Inter_600SemiBold', fontSize: 10, letterSpacing: 0.5 },
  playerStats: {
    marginTop: spacing.xs,
    paddingTop: spacing.xs,
    borderTopWidth: 1,
    borderTopColor: colors.bg.border,
  },
  statsGrid: { flexDirection: 'row', flexWrap: 'wrap', gap: spacing.xs },
  statPill: {
    backgroundColor: colors.bg.elevated,
    borderRadius: radius.sm,
    padding: spacing.xs,
    alignItems: 'center',
    minWidth: 70,
  },
  statVal: { fontFamily: 'Rajdhani_700Bold', fontSize: fontSize.md, lineHeight: fontSize.md + 4 },
  statLbl: { fontFamily: 'Inter_400Regular', fontSize: 9, color: colors.text.muted },
});
