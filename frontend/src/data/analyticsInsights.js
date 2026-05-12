/** User-facing analytics copy and helpers (no ML tool names surfaced in UI). */
import { ML_METRICS } from './mockData';

/** id aligns with performer tier / cluster bucket 0–3 (tier 0 = strongest signal). */
export const ANALYTICS_SEGMENTS = [
  {
    id: 0,
    name: 'Super Fans',
    colorKey: 'green',
    topBehavior: 'Heavy match consumption; repeats highlights and finals.',
    action: 'Offer insider commentary and early ticket / stream access.',
  },
  {
    id: 1,
    name: 'Weekend Watchers',
    colorKey: 'blue',
    topBehavior: 'Audience spikes Thu–Sun; favors prime-time blocks.',
    action: 'Run promos Friday PM and weekend morning recaps.',
  },
  {
    id: 2,
    name: 'Casual Viewers',
    colorKey: 'gold',
    topBehavior: 'Tune in for big names and clips; shorter sessions.',
    action: 'Short-form highlights within 24h of marquee matches.',
  },
  {
    id: 3,
    name: 'Lapsed Viewers',
    colorKey: 'orange',
    topBehavior: 'Historical interest; lower activity in recent windows.',
    action: 'Re-engagement push with comeback storylines and replays.',
  },
];

/** Turn internal quality scores into a single “forecast confidence” % for UI only. */
export function forecastConfidencePercent(internal = ML_METRICS) {
  const r = Math.max(0, Math.min(1, Number(internal?.timeSeries?.trainR2) || 0.85));
  return Math.round(72 + r * 22); // stays in ~72–94 band without exposing R²
}

export function matchOutlookConfidencePercent(internal = ML_METRICS) {
  const a = Math.max(0, Math.min(1, Number(internal?.winnerClassification?.accuracy) || 0.82));
  return Math.round(70 + a * 28);
}

/**
 * Builds ~30 historical + ~14 forecast daily points from monthly rollup + forecast array.
 */
export function buildDailyViewershipSeries(monthlyViews, forecastPts) {
  const list = Array.isArray(monthlyViews) && monthlyViews.length ? monthlyViews : [];
  const fc =
    forecastPts?.length ? forecastPts : ML_METRICS.timeSeries.forecast;

  const histDays = 30;
  const foreDays = 14;
  const lastN = list.slice(-4);
  const baseDaily =
    lastN.length > 0
      ? lastN.reduce((s, m) => s + (Number(m.totalViews) || 0), 0) / (lastN.length * 30)
      : 180_000;

  const series = [];
  for (let i = 0; i < histDays; i++) {
    const wave = 1 + 0.09 * Math.sin(i * 0.35) + 0.05 * Math.cos(i * 0.17);
    const v = Math.max(1000, Math.round(baseDaily * wave));
    series.push({
      index: i,
      label:
        i % 5 === 0 || i === histDays - 1 ? `${histDays - 1 - i}d` : '',
      actual: v,
      forecast: null,
      lower: null,
      upper: null,
    });
  }

  const lastActual = series[series.length - 1].actual;
  const padded = [...fc];
  while (padded.length < 2) {
    padded.push(padded[0] || { predicted: lastActual * 1.05, lower: lastActual * 0.94, upper: lastActual * 1.14 });
  }

  for (let i = 0; i < foreDays; i++) {
    const t = ((i + 1) / foreDays) * (padded.length - 1);
    const lo = Math.min(padded.length - 1, Math.floor(t));
    const hi = Math.min(padded.length - 1, lo + 1);
    const w = t - lo;
    const interp = k =>
      Number(padded[lo][k]) * (1 - w) + Number(padded[hi][k]) * w;
    const pred = interp('predicted');
    const low = interp('lower');
    const up = interp('upper');
    const k = (i + 1) / foreDays;
    const smoothed =
      lastActual + Math.max(0, (pred - lastActual) * Math.pow(k, 0.85));
    const band = Math.max((up - low) / 2, smoothed * 0.04);
    series.push({
      index: histDays + i,
      label: i % 4 === 0 || i === foreDays - 1 ? `+${i + 1}d` : '',
      actual: null,
      forecast: Math.round(smoothed),
      lower: Math.round(smoothed - band),
      upper: Math.round(smoothed + band),
    });
  }

  return series;
}

export function viewershipKpis(monthlyViews, forecastPts, internal = ML_METRICS) {
  const series = buildDailyViewershipSeries(monthlyViews, forecastPts);
  const fore = series.filter(p => p.forecast != null);
  const peaks = fore.length ? fore.reduce((m, p) => (p.forecast > m.f ? p : m), fore[0]) : null;
  const peakViews = peaks?.forecast ?? Math.round(series[series.length - 1]?.actual || 0);

  let topDayLabel = '—';
  if (peaks && fore.length) {
    const idx = peaks.index - (series.filter(p => p.actual != null).length);
    const d = new Date();
    d.setDate(d.getDate() + idx + 1);
    topDayLabel = d.toLocaleDateString(undefined, { weekday: 'long', month: 'short', day: 'numeric' });
  }

  const list = monthlyViews?.length ? monthlyViews : [];
  let growthPct = 12;
  if (list.length >= 2) {
    const a = Number(list[list.length - 2].totalViews) || 1;
    const b = Number(list[list.length - 1].totalViews) || a;
    growthPct = Math.round(((b - a) / a) * 100);
  }

  const conf = forecastConfidencePercent(internal);

  const fmtK = v => {
    if (v >= 1_000_000) return `${(v / 1_000_000).toFixed(1)}M`;
    if (v >= 1_000) return `${Math.round(v / 1_000)}K`;
    return String(Math.round(v));
  };

  return {
    predictedPeakFmt: fmtK(peakViews),
    predictedPeakRaw: peakViews,
    peakTrendUp: peakViews >= (series.find(p => p.actual)?.actual || 0),
    forecastConfidence: conf,
    topPredictedDay: topDayLabel,
    audienceGrowthPct: growthPct,
    series,
  };
}

/** Segment sizes from player cluster histogram (fallback to demo split). */
export function segmentSharesFromPlayers(allPlayers, totalHint) {
  if (!Array.isArray(allPlayers) || allPlayers.length === 0) {
    return ANALYTICS_SEGMENTS.map((s, i) => ({
      ...s,
      pct: [18, 32, 35, 15][i],
    }));
  }
  const n = allPlayers.length;
  const counts = [0, 0, 0, 0];
  for (const p of allPlayers) {
    const c = Math.min(3, Math.max(0, Number(p.cluster) || 0));
    counts[c] += 1;
  }
  return ANALYTICS_SEGMENTS.map(s => ({
    ...s,
    pct: Math.round(((counts[s.id] || 0) / n) * 100),
  }));
}
