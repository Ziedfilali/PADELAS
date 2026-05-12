import React, { useMemo } from 'react';
import { View, Text, StyleSheet, Dimensions, ScrollView } from 'react-native';
import { LineChart } from 'react-native-chart-kit';
import { LinearGradient } from 'expo-linear-gradient';
import { G, Polyline } from 'react-native-svg';
import { colors, fontSize, radius, spacing } from '../../theme';

const { width } = Dimensions.get('window');
const CHART_WIDTH = width - spacing.md * 2;

const CHART_HEIGHT = 200;
const PADDING_TOP = 16;
const PADDING_RIGHT = 64;

/**
 * Y pixel for a value v (millions), matching chart-kit LineChart (fromZero, all values ≥ 0).
 */
function yForValue(v, height, allValues, paddingTop = PADDING_TOP) {
  const vals = allValues.filter(x => typeof x === 'number' && Number.isFinite(x));
  const ymax = Math.max(...vals, 1e-9);
  const baseHeight = height;
  const calcH = height * (v / ymax);
  return ((baseHeight - calcH) / 4) * 3 + paddingTop;
}

/** Match react-native-chart-kit LineChart: x = paddingRight + i * (w - paddingRight) / xMax; xMax = dataset length */
function xForIndex(i, labelCount, chartWidth) {
  const xMax = Math.max(labelCount, 1);
  return PADDING_RIGHT + (i * (chartWidth - PADDING_RIGHT)) / xMax;
}

function ForecastPolylineDecorator({ width: w, height, actualMs, forecast, paddingTop }) {
  const fc = forecast || [];
  if (!fc.length || actualMs.length < 1) return null;

  const preds = fc.map(x => Number(x.predicted) / 1_000_000 || 0);
  const allVals = [...actualMs, ...preds];
  const n = actualMs.length;
  const labelsLen = n + preds.length;

  const pts = [];
  for (let i = n - 1; i < labelsLen; i++) {
    const val = i === n - 1 ? actualMs[n - 1] : preds[i - n];
    const x = xForIndex(i, labelsLen, w);
    const y = yForValue(val, height, allVals, paddingTop);
    pts.push(`${x},${y}`);
  }

  return (
    <G>
      <Polyline
        points={pts.join(' ')}
        fill="none"
        stroke={colors.blue.light}
        strokeWidth={2.5}
        strokeDasharray="8,6"
        strokeOpacity={0.95}
      />
    </G>
  );
}

export default function TimeSeriesChart({ data, forecast = [] }) {
  const fc = Array.isArray(forecast) ? forecast : [];
  const actuals = useMemo(() => data.map(d => d.totalViews / 1_000_000), [data]);

  const labels = useMemo(() => {
    const base = data.map(d => d.month);
    if (!fc.length) return base;
    return [...base, ...fc.map(f => f.month)];
  }, [data, fc]);

  const chartWidth = Math.max(CHART_WIDTH, labels.length * 55);

  const datasets = useMemo(() => {
    const tailNulls = fc.length ? new Array(fc.length).fill(null) : [];
    return [
      {
        data: fc.length ? [...actuals, ...tailNulls] : actuals,
        color: (opacity = 1) => `rgba(0, 255, 87, ${opacity})`,
        strokeWidth: 2.5,
      },
    ];
  }, [actuals, fc.length]);

  const decorator = useMemo(
    () =>
      fc.length
        ? ({ width: w }) => (
            <ForecastPolylineDecorator
              width={w}
              height={CHART_HEIGHT}
              paddingTop={PADDING_TOP}
              actualMs={actuals}
              forecast={fc}
            />
          )
        : undefined,
    [actuals, fc],
  );

  const chartConfig = {
    backgroundGradientFrom: colors.bg.card,
    backgroundGradientTo: colors.bg.card,
    backgroundGradientFromOpacity: 0,
    backgroundGradientToOpacity: 0,
    color: (opacity = 1) => `rgba(0, 255, 87, ${opacity})`,
    labelColor: () => colors.text.muted,
    strokeWidth: 2.5,
    propsForDots: {
      r: '4',
      strokeWidth: '2',
      stroke: colors.green.neon,
      fill: colors.bg.card,
    },
    propsForBackgroundLines: {
      stroke: colors.bg.border,
      strokeDasharray: '4,4',
    },
    propsForLabels: {
      fontFamily: 'Inter_400Regular',
      fontSize: 9,
    },
    decimalPlaces: 1,
  };

  const peakActual = actuals.length ? Math.max(...actuals) : 0;
  const totalActual = actuals.reduce((a, b) => a + b, 0);
  const firstHalf = actuals.slice(0, Math.ceil(actuals.length / 2));
  const secondHalf = actuals.slice(Math.ceil(actuals.length / 2));
  const h1 = firstHalf.reduce((a, b) => a + b, 0) / Math.max(firstHalf.length, 1);
  const h2 = secondHalf.reduce((a, b) => a + b, 0) / Math.max(secondHalf.length, 1);
  const growthPct = h1 > 0 ? Math.round(((h2 - h1) / h1) * 100) : null;

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Monthly viewership</Text>
        <View style={styles.legendRow}>
          <View style={styles.legendItem}>
            <View style={[styles.legendDot, { backgroundColor: colors.green.neon }]} />
            <Text style={styles.legendText}>Actual views</Text>
          </View>
          {fc.length > 0 && (
            <View style={styles.legendItem}>
              <View style={[styles.legendDot, { backgroundColor: colors.blue.light }]} />
              <Text style={styles.legendText}>Forecasted views</Text>
            </View>
          )}
        </View>
      </View>

      <View style={styles.statsRow}>
        <StatPill label="Peak month" value={`${peakActual.toFixed(1)}M`} color={colors.green.neon} />
        <StatPill label="Total (shown)" value={`${totalActual.toFixed(0)}M`} color={colors.blue.light} />
        <StatPill
          label="Growth (2nd half vs 1st)"
          value={growthPct != null ? `${growthPct >= 0 ? '+' : ''}${growthPct}%` : '—'}
          color={colors.accent.gold}
        />
      </View>

      <ScrollView horizontal showsHorizontalScrollIndicator={false}>
        <LineChart
          data={{
            labels,
            datasets,
          }}
          width={chartWidth}
          height={CHART_HEIGHT}
          chartConfig={chartConfig}
          bezier={!fc.length}
          style={styles.chart}
          withInnerLines
          withOuterLines={false}
          withVerticalLabels
          withHorizontalLabels
          fromZero
          yAxisSuffix="M"
          withShadow={!fc.length}
          hidePointsAtIndex={fc.length ? fc.map((_, i) => actuals.length + i) : []}
          decorator={decorator}
        />
      </ScrollView>

      {fc.length > 0 && (
        <View style={styles.forecastHint}>
          <Text style={styles.forecastHintText}>
            Dashed blue line continues from the last actual month using the same forecast as the cards below.
          </Text>
        </View>
      )}

      {fc.length > 0 && (
        <View style={styles.forecastSection}>
          <Text style={styles.forecastTitle}>Upcoming forecast</Text>
          <View style={styles.forecastCards}>
            {fc.map((f, i) => (
              <View key={i} style={styles.forecastCard}>
                <LinearGradient colors={[colors.blue.muted, 'transparent']} style={StyleSheet.absoluteFill} />
                <Text style={styles.forecastMonth}>{f.month}</Text>
                <Text style={styles.forecastVal}>{(f.predicted / 1_000_000).toFixed(1)}M</Text>
                <Text style={styles.forecastRange}>
                  {(f.lower / 1_000_000).toFixed(1)}–{(f.upper / 1_000_000).toFixed(1)}M
                </Text>
              </View>
            ))}
          </View>
        </View>
      )}
    </View>
  );
}

function StatPill({ label, value, color }) {
  return (
    <View style={styles.statPill}>
      <Text style={[styles.statVal, { color }]}>{value}</Text>
      <Text style={styles.statLabel}>{label}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginBottom: spacing.sm,
    flexWrap: 'wrap',
    gap: spacing.sm,
  },
  title: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.md,
    color: colors.text.primary,
  },
  legendRow: {
    flexDirection: 'row',
    gap: spacing.sm,
    flexWrap: 'wrap',
  },
  legendItem: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4,
  },
  legendDot: {
    width: 8,
    height: 8,
    borderRadius: 4,
  },
  legendText: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  statsRow: {
    flexDirection: 'row',
    gap: spacing.sm,
    marginBottom: spacing.sm,
  },
  statPill: {
    flex: 1,
    backgroundColor: colors.bg.elevated,
    borderRadius: radius.md,
    padding: spacing.sm,
    alignItems: 'center',
    minWidth: 90,
  },
  statVal: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.lg,
    lineHeight: fontSize.lg + 4,
  },
  statLabel: {
    fontFamily: 'Inter_400Regular',
    fontSize: 9,
    color: colors.text.muted,
    textAlign: 'center',
  },
  chart: {
    borderRadius: radius.md,
    marginVertical: spacing.xs,
  },
  forecastHint: {
    marginTop: spacing.xs,
    marginBottom: spacing.xs,
  },
  forecastHintText: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    lineHeight: 15,
  },
  forecastSection: {
    marginTop: spacing.md,
    paddingTop: spacing.md,
    borderTopWidth: 1,
    borderTopColor: colors.bg.border,
  },
  forecastTitle: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.text.secondary,
    marginBottom: spacing.sm,
  },
  forecastCards: {
    flexDirection: 'row',
    gap: spacing.sm,
    flexWrap: 'wrap',
  },
  forecastCard: {
    flex: 1,
    minWidth: 100,
    borderRadius: radius.md,
    padding: spacing.sm,
    borderWidth: 1,
    borderColor: colors.blue.muted,
    overflow: 'hidden',
    alignItems: 'center',
  },
  forecastMonth: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    marginBottom: 2,
  },
  forecastVal: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.xl,
    color: colors.blue.light,
    lineHeight: fontSize.xl + 4,
  },
  forecastRange: {
    fontFamily: 'Inter_400Regular',
    fontSize: 9,
    color: colors.text.muted,
  },
});
