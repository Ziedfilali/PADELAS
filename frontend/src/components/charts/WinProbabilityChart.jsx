import React, { useEffect, useRef } from 'react';
import { View, Text, StyleSheet, Animated } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { colors, fontSize, radius, spacing } from '../../theme';

export default function WinProbabilityChart({ team1, team2, probability, version }) {
  const barAnim = useRef(new Animated.Value(0)).current;
  const fadeAnim = useRef(new Animated.Value(0)).current;

  const p = Math.min(1, Math.max(Number(probability) || 0, 1e-6));
  const pct1 = Math.round(p * 100);
  const pct2 = 100 - pct1;

  useEffect(() => {
    Animated.parallel([
      Animated.timing(fadeAnim, { toValue: 1, duration: 600, useNativeDriver: true }),
      Animated.timing(barAnim, { toValue: p, duration: 1200, useNativeDriver: false }),
    ]).start();
  }, [p]);

  const leftWidth = barAnim.interpolate({
    inputRange: [0, 1],
    outputRange: ['0%', '100%'],
  });

  const dividerLeft = barAnim.interpolate({
    inputRange: [0, p],
    outputRange: ['0%', `${p * 100}%`],
  });

  return (
    <Animated.View style={[styles.container, { opacity: fadeAnim }]}>
      {/* Header */}
      <View style={styles.header}>
        <View style={styles.mlBadge}>
          <Text style={styles.mlIcon}>🤖</Text>
          <Text style={styles.mlLabel}>AI WIN PREDICTION</Text>
        </View>
        {version != null && version !== '' ? (
          <Text style={styles.version}>{String(version)}</Text>
        ) : null}
      </View>

      {/* Team names */}
      <View style={styles.teamsRow}>
        <Text style={styles.team1Name} numberOfLines={2}>{team1}</Text>
        <Text style={styles.vs}>VS</Text>
        <Text style={styles.team2Name} numberOfLines={2}>{team2}</Text>
      </View>

      {/* Probability bar */}
      <View style={styles.barContainer}>
        <View style={styles.barTrack}>
          <LinearGradient
            colors={[colors.blue.navy, colors.blue.deep, colors.blue.mid]}
            start={{ x: 0, y: 0 }}
            end={{ x: 1, y: 0 }}
            style={styles.barRight}
          />
          <Animated.View style={[styles.barFill, { width: leftWidth }]}>
            <LinearGradient
              colors={[colors.green.dark, colors.green.mid, colors.green.neon]}
              start={{ x: 0, y: 0 }}
              end={{ x: 1, y: 0 }}
              style={StyleSheet.absoluteFill}
            />
          </Animated.View>
          <Animated.View style={[styles.barDivider, { left: dividerLeft }]} />
        </View>
      </View>

      {/* Percentages */}
      <View style={styles.pctRow}>
        <View style={styles.pctBlock}>
          <Text style={styles.pct1}>{pct1}%</Text>
          <Text style={styles.pctLabel}>Team 1 Win</Text>
        </View>
        <View style={styles.separator} />
        <View style={[styles.pctBlock, styles.pctBlockRight]}>
          <Text style={styles.pct2}>{pct2}%</Text>
          <Text style={styles.pctLabel}>Team 2 Win</Text>
        </View>
      </View>

      {/* Winner label */}
      <View style={styles.winnerRow}>
        <LinearGradient
          colors={pct1 > 50
            ? [colors.green.muted, 'rgba(0,255,87,0.08)']
            : [colors.blue.muted,  'rgba(91,163,245,0.08)']}
          start={{ x: 0, y: 0 }}
          end={{ x: 1, y: 0 }}
          style={styles.winnerBadge}
        >
          <Text style={styles.winnerIcon}>{pct1 > 50 ? '🏆' : '🏆'}</Text>
          <Text style={[styles.winnerText, { color: pct1 > 50 ? colors.green.neon : colors.blue.light }]}>
            {pct1 > 50 ? team1 : team2} predicted to win
          </Text>
        </LinearGradient>
      </View>

      {/* Confidence indicator */}
      <View style={styles.confidenceRow}>
        <Text style={styles.confidenceLabel}>Confidence</Text>
        <View style={styles.confidenceDots}>
          {[1,2,3,4,5].map(i => (
            <View
              key={i}
              style={[
                styles.dot,
                i <= Math.ceil((Math.abs(pct1 - 50) / 50) * 5) && styles.dotActive,
              ]}
            />
          ))}
        </View>
        <Text style={styles.confidenceVal}>
          {Math.abs(pct1 - 50) < 10 ? 'Low' : Math.abs(pct1 - 50) < 25 ? 'Medium' : 'High'}
        </Text>
      </View>
    </Animated.View>
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
    marginBottom: spacing.md,
  },
  mlBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 6,
    backgroundColor: colors.green.muted,
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: radius.full,
  },
  mlIcon: { fontSize: 12 },
  mlLabel: {
    fontFamily: 'Inter_700Bold',
    fontSize: 10,
    color: colors.green.neon,
    letterSpacing: 1,
  },
  version: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  teamsRow: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginBottom: spacing.md,
    gap: spacing.sm,
  },
  team1Name: {
    flex: 1,
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.green.neon,
    textAlign: 'left',
  },
  vs: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.sm,
    color: colors.text.muted,
    letterSpacing: 2,
  },
  team2Name: {
    flex: 1,
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.blue.light,
    textAlign: 'right',
  },
  barContainer: {
    marginBottom: spacing.md,
  },
  barTrack: {
    position: 'relative',
    height: 32,
    borderRadius: radius.sm,
    overflow: 'hidden',
    flexDirection: 'row',
    backgroundColor: colors.bg.elevated,
  },
  barFill: {
    height: '100%',
    position: 'absolute',
    left: 0,
    top: 0,
    bottom: 0,
    zIndex: 2,
  },
  barDivider: {
    position: 'absolute',
    top: 0,
    bottom: 0,
    width: 2,
    marginLeft: -1,
    backgroundColor: colors.bg.primary,
    zIndex: 3,
  },
  barRight: {
    ...StyleSheet.absoluteFillObject,
    opacity: 0.55,
  },
  pctRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: spacing.sm,
  },
  pctBlock: {
    flex: 1,
    alignItems: 'flex-start',
  },
  pctBlockRight: {
    alignItems: 'flex-end',
  },
  pct1: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['2xl'],
    color: colors.green.neon,
    lineHeight: fontSize['2xl'] + 4,
  },
  pct2: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['2xl'],
    color: colors.blue.light,
    lineHeight: fontSize['2xl'] + 4,
  },
  pctLabel: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
  },
  separator: {
    width: 1,
    height: 40,
    backgroundColor: colors.bg.border,
  },
  winnerRow: {
    marginBottom: spacing.sm,
  },
  winnerBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    paddingHorizontal: 14,
    paddingVertical: 10,
    borderRadius: radius.md,
  },
  winnerIcon: { fontSize: 14 },
  winnerText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
  },
  confidenceRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
  },
  confidenceLabel: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
  },
  confidenceDots: {
    flex: 1,
    flexDirection: 'row',
    gap: 4,
    justifyContent: 'center',
  },
  dot: {
    width: 10,
    height: 10,
    borderRadius: 5,
    backgroundColor: colors.bg.border,
  },
  dotActive: {
    backgroundColor: colors.green.neon,
  },
  confidenceVal: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.xs,
    color: colors.green.bright,
  },
});
