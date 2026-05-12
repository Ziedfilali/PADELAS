import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { colors, fontSize, radius, spacing } from '../../theme';
import { ML_METRICS } from '../../data/mockData';
import { segmentSharesFromPlayers, viewershipKpis } from '../../data/analyticsInsights';
import ViewershipForecastChart from '../charts/ViewershipForecastChart';
import { useWarehouseData } from '../../context/WarehouseDataContext';

export function AnalyticsHealthCard() {
  const last = ML_METRICS.winnerClassification.trainedAt || ML_METRICS.timeSeries.trainedAt;
  const d = last
    ? new Date(last).toLocaleDateString(undefined, { month: 'short', day: 'numeric', year: 'numeric' })
    : 'recently';
  return (
    <View style={styles.healthCard}>
      <View style={styles.healthBadgeWrap}>
        <View style={styles.healthDot} />
        <Text style={styles.healthBadge}>All systems go – forecasts up to date</Text>
      </View>
      <Text style={styles.healthSub}>Models last retrained · {d} · Updates daily</Text>
    </View>
  );
}

export function InsightKpiCard({ icon, headline, detail, accent, arrow }) {
  return (
    <View style={[styles.kpiMini, { borderColor: `${accent}36` }]}>
      <LinearGradient colors={[`${accent}12`, 'transparent']} style={StyleSheet.absoluteFill} />
      <Text style={styles.kpiIcon}>{icon}</Text>
      <Text style={[styles.kpiHeadline, { color: accent }]}>{headline}</Text>
      <View style={styles.kpiDetailRow}>
        <Text style={styles.kpiDetail}>{detail}</Text>
        {arrow ? <Text style={[styles.kpiArrow, { color: accent }]}>{arrow}</Text> : null}
      </View>
    </View>
  );
}

export function AudienceSegmentCard({ name, pct, behavior, action, accent, shareLabel }) {
  const share = shareLabel || 'of audience';
  return (
    <View style={[styles.segmentCard, { borderColor: `${accent}40` }]}>
      <Text style={[styles.segmentName, { color: accent }]}>{name}</Text>
      <Text style={styles.segmentPct}>{pct}% {share}</Text>
      <Text style={styles.segmentBehavior}>{behavior}</Text>
      <Text style={styles.segmentAction}>{action}</Text>
    </View>
  );
}

/** Full right-column stack: forecast chart, KPI grid, segments, health (used on home + analytics). */
export function AudienceInsightsColumn({ compactChart = false }) {
  const { monthlyViews, players } = useWarehouseData();
  const kpis = viewershipKpis(monthlyViews, ML_METRICS.timeSeries.forecast, ML_METRICS);
  const segments = segmentSharesFromPlayers(players);
  const segColors = [colors.green.neon, colors.blue.light, colors.accent.gold, colors.accent.orange];

  return (
    <>
      <ViewershipForecastChart
        monthlyViews={monthlyViews}
        forecast={ML_METRICS.timeSeries.forecast}
        compact={compactChart}
      />

      <View style={styles.kpiGrid}>
        <InsightKpiCard
          icon="📈"
          headline={kpis.predictedPeakFmt}
          detail="Predicted peak views"
          accent={colors.green.neon}
          arrow={kpis.peakTrendUp ? '↑' : '→'}
        />
        <InsightKpiCard
          icon="✓"
          headline={`${kpis.forecastConfidence}%`}
          detail="Forecast confidence"
          accent={colors.blue.light}
        />
        <InsightKpiCard
          icon="📅"
          headline={kpis.topPredictedDay}
          detail="Top predicted day"
          accent={colors.accent.gold}
        />
        <InsightKpiCard
          icon="👥"
          headline={`${kpis.audienceGrowthPct >= 0 ? '+' : ''}${kpis.audienceGrowthPct}%`}
          detail="Audience growth vs last month"
          accent={colors.accent.orange}
          arrow={kpis.audienceGrowthPct >= 0 ? '↑' : '↓'}
        />
      </View>

      <Text style={styles.rightSectionTitle}>Audience segments</Text>
      <View style={styles.segmentGrid}>
        {segments.map((s, i) => (
          <AudienceSegmentCard
            key={s.id}
            name={s.name}
            pct={s.pct}
            behavior={s.topBehavior}
            action={s.action}
            accent={segColors[i % segColors.length]}
          />
        ))}
      </View>

      <AnalyticsHealthCard />
    </>
  );
}

const styles = StyleSheet.create({
  rightSectionTitle: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.sm,
    color: colors.text.primary,
    marginTop: spacing.md,
    marginBottom: spacing.xs,
  },
  kpiGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    gap: spacing.sm,
    marginTop: spacing.md,
  },
  kpiMini: {
    width: '47.5%',
    borderRadius: radius.md,
    padding: spacing.sm,
    borderWidth: 1,
    overflow: 'hidden',
    position: 'relative',
    minHeight: 88,
  },
  kpiIcon: { fontSize: 14, marginBottom: 4 },
  kpiHeadline: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 20,
    lineHeight: 24,
  },
  kpiDetailRow: { flexDirection: 'row', alignItems: 'center', gap: 4, marginTop: 4 },
  kpiDetail: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    flex: 1,
  },
  kpiArrow: { fontFamily: 'Inter_700Bold', fontSize: 14 },
  segmentGrid: { gap: spacing.sm, marginTop: spacing.xs },
  segmentCard: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.md,
    padding: spacing.sm,
    borderWidth: 1,
    gap: 4,
  },
  segmentName: {
    fontFamily: 'Inter_700Bold',
    fontSize: 12,
  },
  segmentPct: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 10,
    color: colors.text.muted,
  },
  segmentBehavior: {
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.secondary,
    lineHeight: 16,
  },
  segmentAction: {
    fontFamily: 'Inter_500Medium',
    fontSize: 10,
    color: colors.green.neon,
    marginTop: 2,
  },
  healthCard: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: `${colors.green.neon}35`,
    marginTop: spacing.md,
    gap: 6,
  },
  healthBadgeWrap: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
  },
  healthDot: {
    width: 8,
    height: 8,
    borderRadius: 4,
    backgroundColor: colors.green.neon,
  },
  healthBadge: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 12,
    color: colors.text.primary,
    flex: 1,
  },
  healthSub: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
});
