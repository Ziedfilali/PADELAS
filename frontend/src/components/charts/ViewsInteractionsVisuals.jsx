import React, { useMemo } from 'react';
import { View, Text, StyleSheet, Dimensions, ScrollView } from 'react-native';
import { BarChart, LineChart } from 'react-native-chart-kit';
import { colors, fontSize, radius, spacing } from '../../theme';
import { buildViewsInteractionSeries } from '../../utils/monthlyEngagement';

const { width: WIN_W } = Dimensions.get('window');
const pad = spacing.md * 2;
const INNER = Math.min(720, WIN_W - pad);

const chartCfg = theme => ({
  backgroundGradientFrom: colors.bg.card,
  backgroundGradientTo: colors.bg.card,
  backgroundGradientFromOpacity: 0,
  backgroundGradientToOpacity: 0,
  color: theme,
  labelColor: () => colors.text.muted,
  strokeWidth: 2,
  barPercentage: 0.55,
  propsForBackgroundLines: { stroke: colors.bg.border, strokeDasharray: '4,4' },
  propsForLabels: { fontFamily: 'Inter_400Regular', fontSize: 9 },
  decimalPlaces: 0,
});

/**
 * Monthly views bars + interactions bars + engagement (interactions as % of views) line —
 * federation-friendly linkage without jargon.
 */
export default function ViewsInteractionsVisuals({ monthlyViews = [], matches = [] }) {
  const rows = useMemo(
    () => buildViewsInteractionSeries(monthlyViews, matches),
    [monthlyViews, matches],
  );

  const labels = rows.map(r => r.label);
  const viewsM = rows.map(r => Number((r.views / 1_000_000).toFixed(2)));
  // Interactions are often orders of magnitude smaller than views — use thousands so bars aren’t all 0.00M.
  const intK = rows.map(r => Number((r.interactions / 1_000).toFixed(1)));
  const eng = rows.map(r => Number(Math.min(r.engagementRate, 80).toFixed(1))); // cap for readability

  const w = Math.max(INNER, labels.length * 48);
  const lastRow = rows[rows.length - 1];

  const sumV = rows.reduce((s, r) => s + r.views, 0);
  const sumI = rows.reduce((s, r) => s + r.interactions, 0);
  const overallRate = sumV > 0 ? ((sumI / sumV) * 100).toFixed(1) : '—';

  if (labels.length === 0) {
    return (
      <View style={[styles.wrap, styles.empty]}>
        <Text style={styles.summaryTitle}>Views & interactions</Text>
        <Text style={styles.emptyText}>Load match data with dates to chart monthly behaviour.</Text>
      </View>
    );
  }

  return (
    <ScrollView horizontal showsHorizontalScrollIndicator={false} contentContainerStyle={styles.horizontal}>
      <View style={styles.column}>
        <Text style={styles.summaryTitle}>How views relate to interactions</Text>
        <Text style={styles.summarySub}>
          Each month sums finished-match streams (views) and fan actions (interactions). If your warehouse does not store
          interactions yet, we estimate them from views (14% default) so the chart is still comparable month to month.
          Engagement rate = interactions ÷ views.
        </Text>

        <View style={styles.kpiStrip}>
          <View style={styles.kpiChip}>
            <Text style={styles.kpiLbl}>Tracked months</Text>
            <Text style={[styles.kpiVal, { color: colors.green.neon }]}>{rows.length}</Text>
          </View>
          <View style={styles.kpiChip}>
            <Text style={styles.kpiLbl}>Latest month · interactions per 1K views</Text>
            <Text style={[styles.kpiVal, { color: colors.blue.light }]}>
              {lastRow ? lastRow.perThousand.toFixed(0) : '—'}
            </Text>
          </View>
          <View style={styles.kpiChip}>
            <Text style={styles.kpiLbl}>Window avg · interaction share of views</Text>
            <Text style={[styles.kpiVal, { color: colors.accent.gold }]}>{overallRate}%</Text>
          </View>
        </View>

        <View style={styles.card}>
          <Text style={styles.cardTitle}>Monthly views (million)</Text>
          <BarChart
            data={{
              labels,
              datasets: [{ data: viewsM.length ? viewsM : [0] }],
            }}
            width={w}
            height={200}
            chartConfig={{
              ...chartCfg(() => colors.green.neon),
              decimalPlaces: 2,
              formatYLabel: v => `${v}`,
            }}
            style={styles.chart}
            fromZero
            showValuesOnTopOfBars={false}
            yAxisSuffix="M"
          />
        </View>

        <View style={styles.card}>
          <Text style={styles.cardTitle}>Monthly interactions (thousands)</Text>
          <BarChart
            data={{
              labels,
              datasets: [{ data: intK.length ? intK : [0] }],
            }}
            width={w}
            height={200}
            chartConfig={{
              ...chartCfg(() => colors.blue.light),
              decimalPlaces: 1,
            }}
            style={styles.chart}
            fromZero
            yAxisSuffix="K"
          />
        </View>

        <View style={styles.card}>
          <Text style={styles.cardTitle}>Engagement rate over time (%)</Text>
          <Text style={styles.cardHint}>Interaction volume as a percentage of views (same cohort of matches).</Text>
          <LineChart
            data={{
              labels,
              datasets: [{ data: eng.length ? eng : [0] }],
            }}
            width={w}
            height={200}
            chartConfig={{
              ...chartCfg(() => colors.accent.orange),
              decimalPlaces: 1,
              propsForDots: { r: '4', strokeWidth: '2', stroke: colors.accent.orange },
            }}
            style={styles.chart}
            bezier
            fromZero
            yAxisSuffix="%"
          />
        </View>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  horizontal: { paddingHorizontal: spacing.md, paddingBottom: spacing.md },
  column: {
    gap: spacing.md,
    maxWidth: 900,
    width: INNER,
    flex: 1,
  },
  wrap: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  empty: {
    marginHorizontal: spacing.md,
    marginVertical: spacing.sm,
    alignSelf: 'center',
    width: INNER,
  },
  emptyText: { fontFamily: 'Inter_400Regular', fontSize: 12, color: colors.text.muted, marginTop: 6 },
  summaryTitle: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.md,
    color: colors.text.primary,
  },
  summarySub: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.secondary,
    lineHeight: 18,
    marginBottom: spacing.xs,
  },
  kpiStrip: { flexDirection: 'row', flexWrap: 'wrap', gap: spacing.sm },
  kpiChip: {
    flex: 1,
    minWidth: 140,
    backgroundColor: colors.bg.elevated,
    borderRadius: radius.md,
    padding: spacing.sm,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  kpiLbl: { fontFamily: 'Inter_400Regular', fontSize: 10, color: colors.text.muted },
  kpiVal: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.lg,
    marginTop: 2,
  },
  card: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  cardTitle: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.text.primary,
    marginBottom: spacing.xs,
  },
  cardHint: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    marginBottom: spacing.xs,
  },
  chart: { borderRadius: radius.md },
});
