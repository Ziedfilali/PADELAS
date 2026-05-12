import React from 'react';
import { View, Text, StyleSheet, Dimensions } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { colors, fontSize, radius, spacing } from '../../theme';

const { width } = Dimensions.get('window');

const CLUSTER_COLORS = {
  0: colors.green.neon,
  1: colors.blue.light,
  2: colors.accent.gold,
  3: colors.accent.orange,
};

export default function ClusterChart({ players, clusters, subtitle }) {
  const sub =
    subtitle ||
    'Win rate (horizontal) versus serve activity (vertical). Colours match playing-style groups (Elite Attacker → Rising Star).';
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Player playing-style map</Text>
      <Text style={styles.subtitle}>{sub}</Text>

      {/* Scatter plot simulation */}
      <View style={styles.scatterArea}>
        <LinearGradient
          colors={[colors.bg.elevated, colors.bg.secondary]}
          style={styles.scatterBg}
        />

        {/* Axis labels */}
        <Text style={styles.axisX}>Match success (win rate) →</Text>
        <Text style={styles.axisY}>Serve impact (avg aces) ↑</Text>

        {/* Grid lines */}
        {[0.25, 0.5, 0.75].map(v => (
          <View key={v} style={[styles.gridH, { top: `${(1 - v) * 100}%` }]} />
        ))}
        {[0.25, 0.5, 0.75].map(v => (
          <View key={v} style={[styles.gridV, { left: `${v * 100}%` }]} />
        ))}

        {/* Player dots */}
        {players.map(p => {
          const x = `${p.stats.winRate * 100 * 0.9 + 5}%`;
          const y = `${(1 - p.stats.aces / 12) * 100 * 0.8 + 10}%`;
          const color = CLUSTER_COLORS[p.cluster];
          return (
            <View
              key={p.id}
              style={[
                styles.dot,
                {
                  left: x,
                  top:  y,
                  backgroundColor: color,
                  shadowColor: color,
                },
              ]}
            />
          );
        })}
      </View>

      {/* Legend */}
      <View style={styles.legend}>
        {clusters.map(c => (
          <View key={c.id} style={styles.legendItem}>
            <View style={[styles.legendDot, { backgroundColor: CLUSTER_COLORS[c.id] }]} />
            <View>
              <Text style={styles.legendName}>{c.name}</Text>
              <Text style={styles.legendCount}>{c.count} players</Text>
            </View>
          </View>
        ))}
      </View>
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
  title: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.md,
    color: colors.text.primary,
    marginBottom: 2,
  },
  subtitle: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
    marginBottom: spacing.md,
  },
  scatterArea: {
    height: 220,
    borderRadius: radius.md,
    overflow: 'hidden',
    position: 'relative',
    marginBottom: spacing.md,
  },
  scatterBg: {
    ...StyleSheet.absoluteFillObject,
  },
  axisX: {
    position: 'absolute',
    bottom: 8,
    right: 12,
    fontFamily: 'Inter_400Regular',
    fontSize: 9,
    color: colors.text.muted,
  },
  axisY: {
    position: 'absolute',
    top: 8,
    left: 8,
    fontFamily: 'Inter_400Regular',
    fontSize: 9,
    color: colors.text.muted,
  },
  gridH: {
    position: 'absolute',
    left: 0,
    right: 0,
    height: 1,
    backgroundColor: colors.bg.border,
  },
  gridV: {
    position: 'absolute',
    top: 0,
    bottom: 0,
    width: 1,
    backgroundColor: colors.bg.border,
  },
  dot: {
    position: 'absolute',
    width: 10,
    height: 10,
    borderRadius: 5,
    marginLeft: -5,
    marginTop: -5,
    shadowOffset: { width: 0, height: 0 },
    shadowOpacity: 0.8,
    shadowRadius: 6,
    elevation: 4,
  },
  legend: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    gap: spacing.sm,
  },
  legendItem: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.xs,
    width: '45%',
  },
  legendDot: {
    width: 12,
    height: 12,
    borderRadius: 6,
  },
  legendName: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.xs,
    color: colors.text.primary,
  },
  legendCount: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
});
