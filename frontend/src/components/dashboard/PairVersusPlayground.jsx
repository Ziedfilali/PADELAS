import React, { useEffect, useMemo, useState } from 'react';
import {
  View, Text, StyleSheet, ScrollView, TouchableOpacity, ActivityIndicator,
} from 'react-native';
import { colors, fontSize, radius, spacing } from '../../theme';
import { ML_METRICS } from '../../data/mockData';
import { predictMatchup } from '../../services/api';
import { useWarehouseData } from '../../context/WarehouseDataContext';
import { extractUniquePairsFromMatches } from '../../utils/teamPairs';
import WinProbabilityChart from '../charts/WinProbabilityChart';

export default function PairVersusPlayground({ desktop = false }) {
  const { matches } = useWarehouseData();
  const pairs = useMemo(() => extractUniquePairsFromMatches(matches), [matches]);
  const [aKey, setAKey] = useState(null);
  const [bKey, setBKey] = useState(null);
  const [busy, setBusy] = useState(false);
  const [result, setResult] = useState(null);
  const version = ML_METRICS.winnerClassification?.version;

  useEffect(() => {
    if (pairs.length < 2) return;
    setAKey(prev => (prev && pairs.some(p => p.key === prev) ? prev : pairs[0].key));
  }, [pairs]);

  useEffect(() => {
    if (pairs.length < 2) return;
    setBKey(prev => {
      if (prev && pairs.some(p => p.key === prev)) return prev;
      const other = pairs.find(p => p.key !== pairs[0].key);
      return other ? other.key : pairs[0].key;
    });
  }, [pairs]);

  useEffect(() => {
    if (pairs.length < 2 || !aKey) return;
    if (bKey === aKey) {
      const alt = pairs.find(p => p.key !== aKey);
      if (alt) setBKey(alt.key);
    }
  }, [aKey, bKey, pairs]);

  const pairA = pairs.find(p => p.key === aKey);
  const pairB = pairs.find(p => p.key === bKey);

  const runPredict = async () => {
    if (!pairA || !pairB || pairA.key === pairB.key) return;
    setBusy(true);
    setResult(null);
    try {
      const payload = {
        tournament_name: 'Prediction playground',
        round: 'Custom pair vs pair',
        team1_player1_name: pairA.player1,
        team1_player2_name: pairA.player2,
        team2_player1_name: pairB.player1,
        team2_player2_name: pairB.player2,
      };
      const { data } = await predictMatchup(payload);
      setResult(data);
    } finally {
      setBusy(false);
    }
  };

  const btnDisabled =
    busy || !pairA || !pairB || pairA.key === pairB.key || pairs.length < 2;

  const probability =
    result && typeof result.team_1_probability === 'number'
      ? result.team_1_probability
      : null;

  if (pairs.length < 2) {
    return (
      <View style={[styles.card, desktop && styles.cardDesktop]}>
        <Text style={styles.title}>Prediction playground</Text>
        <Text style={styles.sub}>
          Need at least two distinct pairs in your match data to run a custom prediction.
        </Text>
      </View>
    );
  }

  return (
    <View style={[styles.card, desktop && styles.cardDesktop]}>
      <Text style={styles.emoji}>🧪</Text>
      <Text style={styles.title}>Prediction playground</Text>
      <Text style={styles.sub}>Pick two doubles pairs — the model scores team 1 vs team 2.</Text>

      <Text style={styles.teamLabel}>Team 1 (pair)</Text>
      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
        contentContainerStyle={styles.chipsRow}
      >
        {pairs.map(p => (
          <TouchableOpacity
            key={p.key}
            style={[styles.chip, p.key === aKey && styles.chipActiveA]}
            onPress={() => setAKey(p.key)}
            activeOpacity={0.85}
          >
            <Text style={[styles.chipText, p.key === aKey && styles.chipTextActive]} numberOfLines={2}>
              {p.label}
            </Text>
          </TouchableOpacity>
        ))}
      </ScrollView>

      <Text style={styles.vs}>VS</Text>

      <Text style={[styles.teamLabel, styles.teamLabelB]}>Team 2 (pair)</Text>
      <ScrollView
        horizontal
        showsHorizontalScrollIndicator={false}
        contentContainerStyle={styles.chipsRow}
      >
        {pairs.map(p => (
          <TouchableOpacity
            key={`b-${p.key}`}
            style={[styles.chip, p.key === bKey && styles.chipActiveB]}
            onPress={() => setBKey(p.key)}
            activeOpacity={0.85}
          >
            <Text style={[styles.chipText, p.key === bKey && styles.chipTextActiveB]} numberOfLines={2}>
              {p.label}
            </Text>
          </TouchableOpacity>
        ))}
      </ScrollView>

      {pairA && pairB && pairA.key === pairB.key ? (
        <Text style={styles.warn}>Choose two different pairs.</Text>
      ) : null}

      <TouchableOpacity
        style={[styles.predictBtn, btnDisabled && styles.predictBtnDisabled]}
        onPress={runPredict}
        disabled={btnDisabled}
        activeOpacity={0.9}
      >
        {busy ? (
          <ActivityIndicator color={colors.bg.primary} />
        ) : (
          <Text style={styles.predictBtnText}>Run AI prediction</Text>
        )}
      </TouchableOpacity>

      {probability != null && pairA && pairB ? (
        <View style={styles.result}>
          <WinProbabilityChart
            team1={pairA.label}
            team2={pairB.label}
            probability={probability}
            version={version != null ? `v${version}` : ''}
          />
        </View>
      ) : null}
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    marginHorizontal: spacing.md,
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
    gap: spacing.sm,
  },
  cardDesktop: {
    marginHorizontal: 0,
    maxWidth: 920,
    alignSelf: 'center',
    width: '100%',
  },
  emoji: { fontSize: 22, marginBottom: -4 },
  title: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.lg,
    color: colors.text.primary,
  },
  sub: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.sm,
    color: colors.text.muted,
    marginBottom: spacing.xs,
  },
  teamLabel: {
    fontFamily: 'Inter_700Bold',
    fontSize: 10,
    color: colors.green.neon,
    letterSpacing: 1.2,
    marginTop: spacing.xs,
  },
  teamLabelB: {
    color: colors.blue.light,
  },
  chipsRow: {
    flexDirection: 'row',
    gap: spacing.sm,
    paddingVertical: 4,
  },
  chip: {
    paddingHorizontal: 12,
    paddingVertical: 8,
    borderRadius: radius.md,
    backgroundColor: colors.bg.elevated,
    borderWidth: 1,
    borderColor: colors.bg.border,
    maxWidth: 220,
  },
  chipActiveA: {
    borderColor: colors.green.neon,
    backgroundColor: colors.green.muted,
  },
  chipActiveB: {
    borderColor: colors.blue.light,
    backgroundColor: colors.blue.muted,
  },
  chipText: {
    fontFamily: 'Inter_500Medium',
    fontSize: fontSize.xs,
    color: colors.text.secondary,
  },
  chipTextActive: {
    color: colors.green.neon,
  },
  chipTextActiveB: {
    color: colors.blue.light,
  },
  vs: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.sm,
    color: colors.text.muted,
    textAlign: 'center',
    letterSpacing: 4,
    marginVertical: 4,
  },
  warn: {
    fontFamily: 'Inter_500Medium',
    fontSize: fontSize.xs,
    color: colors.accent.orange,
  },
  predictBtn: {
    marginTop: spacing.sm,
    backgroundColor: colors.green.neon,
    paddingVertical: 14,
    borderRadius: radius.md,
    alignItems: 'center',
  },
  predictBtnDisabled: {
    opacity: 0.45,
  },
  predictBtnText: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.md,
    color: colors.bg.primary,
  },
  result: {
    marginTop: spacing.md,
  },
});
