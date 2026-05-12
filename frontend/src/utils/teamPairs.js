/** Stable key for a doubles pair (order-independent). */
export function canonicalPairKey(playerA, playerB) {
  const a = String(playerA || '').trim();
  const b = String(playerB || '').trim();
  if (!a || !b) return '';
  return [a, b].sort().join('\u0000');
}

/**
 * Unique doubles pairs observed in match data (for pair-vs-pair pickers).
 * @param {Array<{ team1: { player1: string, player2: string }, team2: { player1: string, player2: string } }>} matches
 */
export function extractUniquePairsFromMatches(matches) {
  const map = new Map();
  for (const m of matches || []) {
    const sides = [m?.team1, m?.team2];
    for (const side of sides) {
      if (!side?.player1 || !side?.player2) continue;
      const key = canonicalPairKey(side.player1, side.player2);
      if (!key || map.has(key)) continue;
      map.set(key, {
        key,
        player1: side.player1.trim(),
        player2: side.player2.trim(),
        label: `${side.player1.trim()} / ${side.player2.trim()}`,
      });
    }
  }
  return [...map.values()].sort((x, y) => x.label.localeCompare(y.label));
}
