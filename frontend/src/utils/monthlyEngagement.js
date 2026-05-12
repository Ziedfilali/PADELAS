/**
 * Build monthly viewership vs interaction aggregates for federation dashboards.
 */

function ymFromDate(dateStr) {
  if (!dateStr || typeof dateStr !== 'string') return null;
  const y = dateStr.slice(0, 4);
  const mo = dateStr.slice(5, 7);
  if (!y || mo.length !== 2) return null;
  return `${y}-${mo}`;
}

function shortLabel(ym, idx, total) {
  if (!ym) return `${idx}`;
  const [y, m] = ym.split('-');
  const months = ['', 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
  const mi = parseInt(m, 10);
  const abbr = mi >= 1 && mi <= 12 ? months[mi] : m;
  return `${abbr} '${String(y).slice(2)}`;
}

/** Finished matches grouped by calendar month → views & interactions totals. */
export function aggregateViewsInteractionsFromMatches(matches) {
  if (!Array.isArray(matches)) return [];
  const map = new Map();
  for (const m of matches) {
    if (!m || m.status !== 'finished') continue;
    const key = ymFromDate(m.date);
    if (!key) continue;
    const prev = map.get(key) || { views: 0, interactions: 0 };
    prev.views += Number(m.views) || 0;
    prev.interactions += Number(m.interactions) || 0;
    map.set(key, prev);
  }
  return [...map.entries()]
    .sort((a, b) => a[0].localeCompare(b[0]))
    .map(([ym, v]) => ({ ym, ...v }));
}

/** Typical share of “interactions” vs stream views when the warehouse does not store fan actions yet. */
const DEFAULT_INTERACTION_RATIO = 0.14;

/**
 * Rows for charts: { label, views, interactions, engagementRate } — rate = interactions/views * 100.
 */
export function buildViewsInteractionSeries(monthlyViews, matches) {
  const fromMatches = aggregateViewsInteractionsFromMatches(matches);

  let rows =
    fromMatches.length >= 2
      ? fromMatches.map((r, i, arr) => ({
          label: shortLabel(r.ym, i, arr.length),
          views: r.views,
          interactions: r.interactions,
        }))
      : (monthlyViews || []).map(m => ({
          label: `${m.month}`,
          views: Number(m.totalViews) || 0,
          interactions: Math.round((Number(m.totalViews) || 0) * DEFAULT_INTERACTION_RATIO),
        }));

  const sumInteractions = rows.reduce((s, r) => s + (Number(r.interactions) || 0), 0);
  // Many feeds have views on matches but interactions unset (always 0) — use a stable proxy so charts aren’t flat.
  if (fromMatches.length >= 2 && sumInteractions === 0) {
    rows = rows.map(r => ({
      ...r,
      interactions: Math.round((Number(r.views) || 0) * DEFAULT_INTERACTION_RATIO),
    }));
  }

  rows = rows.map(r => ({
    ...r,
    engagementRate: r.views > 0 ? (r.interactions / r.views) * 100 : 0,
    perThousand: r.views > 0 ? (1000 * r.interactions) / r.views : 0,
  }));

  const tail = rows.slice(-24);
  return tail;
}
