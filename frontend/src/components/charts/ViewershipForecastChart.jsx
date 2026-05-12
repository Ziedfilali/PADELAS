import React, { useMemo } from 'react';
import { View, Text, StyleSheet, Dimensions } from 'react-native';
import Svg, { Polygon, Polyline, Line, Circle } from 'react-native-svg';
import { colors, fontSize, radius, spacing } from '../../theme';
import { buildDailyViewershipSeries } from '../../data/analyticsInsights';
import { ML_METRICS } from '../../data/mockData';

const { width: WIN_W } = Dimensions.get('window');

const PAD = { l: 44, r: 12, t: 12, b: 28 };
const H = 220;

function scaleSeries(series, innerW, innerH) {
  const vals = [];
  for (const p of series) {
    if (p.actual != null) vals.push(p.actual);
    if (p.forecast != null) {
      vals.push(p.forecast, p.lower ?? p.forecast, p.upper ?? p.forecast);
    }
  }
  const vmin = Math.min(...vals) * 0.92;
  const vmax = Math.max(...vals) * 1.08;
  const n = Math.max(series.length - 1, 1);

  const xAt = i => PAD.l + (i / n) * innerW;
  const yAt = v =>
    PAD.t + innerH - ((v - vmin) / Math.max(vmax - vmin, 1)) * innerH;

  return { vmin, vmax, xAt, yAt, innerW, innerH };
}

export default function ViewershipForecastChart({
  monthlyViews,
  forecast = ML_METRICS.timeSeries.forecast,
  compact = false,
}) {
  const chartW = compact ? Math.min(320, WIN_W - 48) : Math.min(WIN_W - spacing.md * 2, 720);
  const innerW = chartW - PAD.l - PAD.r;
  const innerH = H - PAD.t - PAD.b;

  const series = useMemo(
    () => buildDailyViewershipSeries(monthlyViews, forecast),
    [monthlyViews, forecast],
  );

  const { xAt, yAt } = useMemo(
    () => scaleSeries(series, innerW, innerH),
    [series, innerW, innerH],
  );

  const splitIdx = series.findIndex(p => p.forecast != null);
  const actualPts = series
    .map((p, i) => (p.actual != null ? `${xAt(i)},${yAt(p.actual)}` : null))
    .filter(Boolean)
    .join(' ');

  const foreStart = Math.max(0, splitIdx - 1);
  const forecastLine = series
    .map((p, i) => (i >= foreStart && p.forecast != null ? `${xAt(i)},${yAt(p.forecast)}` : null))
    .filter(Boolean)
    .join(' ');

  const bandPoints = [];
  for (let i = foreStart; i < series.length; i++) {
    const p = series[i];
    if (p.forecast == null) continue;
    bandPoints.push({ x: xAt(i), y: yAt(p.upper ?? p.forecast) });
  }
  for (let i = series.length - 1; i >= foreStart; i--) {
    const p = series[i];
    if (p.forecast == null) continue;
    bandPoints.push({ x: xAt(i), y: yAt(p.lower ?? p.forecast) });
  }
  const bandPoly =
    bandPoints.length > 2 ? bandPoints.map(pt => `${pt.x},${pt.y}`).join(' ') : '';

  const joinX = splitIdx > 0 ? xAt(splitIdx - 1) : xAt(0);
  const joinY =
    splitIdx > 0 && series[splitIdx - 1]?.actual != null
      ? yAt(series[splitIdx - 1].actual)
      : yAt(series[0]?.actual || 0);

  return (
    <View style={[styles.wrap, { width: chartW }]}>
      <Text style={styles.title}>Viewership Forecast – Next 14 Days</Text>
      <View style={styles.legend}>
        <LegendDot color={colors.green.neon} label="Actual Views" />
        <LegendDot color={colors.blue.light} label="Forecasted Views" />
        <LegendDot color="rgba(91,163,245,0.35)" label="Confidence Range" square />
      </View>
      <Svg width={chartW} height={H}>
        {[0, 0.25, 0.5, 0.75, 1].map(t => {
          const y = PAD.t + innerH * t;
          return (
            <Line
              key={`g-${t}`}
              x1={PAD.l}
              y1={y}
              x2={PAD.l + innerW}
              y2={y}
              stroke={colors.bg.border}
              strokeWidth={1}
              strokeDasharray="4,4"
            />
          );
        })}
        {bandPoly ? (
          <Polygon points={bandPoly} fill="rgba(91,163,245,0.18)" stroke="transparent" />
        ) : null}
        {actualPts ? (
          <Polyline points={actualPts} fill="none" stroke={colors.green.neon} strokeWidth={2.5} />
        ) : null}
        {forecastLine ? (
          <Polyline
            points={forecastLine}
            fill="none"
            stroke={colors.blue.light}
            strokeWidth={2.5}
            strokeDasharray="6,4"
          />
        ) : null}
        {splitIdx > 0 && series[splitIdx]?.forecast != null ? (
          <Line
            x1={joinX}
            y1={joinY}
            x2={xAt(splitIdx)}
            y2={yAt(series[splitIdx].forecast)}
            stroke={colors.blue.light}
            strokeWidth={1.5}
            strokeDasharray="4,6"
          />
        ) : null}
        <Circle cx={joinX} cy={joinY} r={4} fill={colors.green.neon} stroke={colors.bg.card} strokeWidth={2} />
      </Svg>
      <View style={styles.axisNote}>
        <Text style={styles.axisCap}>Past 30 days</Text>
        <Text style={styles.axisCap}>Next 14 days →</Text>
      </View>
    </View>
  );
}

function LegendDot({ color, label, square }) {
  return (
    <View style={styles.legendItem}>
      <View style={[square ? styles.legSq : styles.legDot, { backgroundColor: color }]} />
      <Text style={styles.legendText}>{label}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  wrap: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    paddingVertical: spacing.md,
    paddingHorizontal: spacing.sm,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  title: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.md,
    color: colors.text.primary,
    marginBottom: spacing.sm,
    paddingHorizontal: spacing.xs,
  },
  legend: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    gap: spacing.md,
    marginBottom: spacing.xs,
    paddingHorizontal: spacing.xs,
  },
  legendItem: { flexDirection: 'row', alignItems: 'center', gap: 6 },
  legDot: { width: 8, height: 8, borderRadius: 4 },
  legSq: { width: 10, height: 8, borderRadius: 2 },
  legendText: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  axisNote: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingHorizontal: PAD.l / 2,
    marginTop: 2,
  },
  axisCap: {
    fontFamily: 'Inter_400Regular',
    fontSize: 9,
    color: colors.text.muted,
  },
});
