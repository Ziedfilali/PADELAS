import React, { useEffect, useRef } from 'react';
import { View, Text, StyleSheet, Animated } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { colors, fontSize, radius, spacing } from '../theme';

export default function StatsBar({ label, value1, value2, unit = '', delay = 0 }) {
  const anim1 = useRef(new Animated.Value(0)).current;
  const anim2 = useRef(new Animated.Value(0)).current;

  const total = (value1 || 0) + (value2 || 0);
  const pct1  = total > 0 ? value1 / total : 0.5;
  const pct2  = total > 0 ? value2 / total : 0.5;

  useEffect(() => {
    Animated.sequence([
      Animated.delay(delay),
      Animated.parallel([
        Animated.timing(anim1, { toValue: pct1, duration: 900, useNativeDriver: false }),
        Animated.timing(anim2, { toValue: pct2, duration: 900, useNativeDriver: false }),
      ]),
    ]).start();
  }, [pct1, pct2]);

  const width1 = anim1.interpolate({ inputRange: [0, 1], outputRange: ['0%', '100%'] });
  const width2 = anim2.interpolate({ inputRange: [0, 1], outputRange: ['0%', '100%'] });

  const isT1Better = value1 > value2;
  const isT2Better = value2 > value1;

  return (
    <View style={styles.row}>
      {/* Value 1 */}
      <Text style={[styles.val, isT1Better && styles.valHighlight]}>
        {value1}{unit}
      </Text>

      {/* Bars */}
      <View style={styles.barsWrapper}>
        <Text style={styles.label}>{label}</Text>
        <View style={styles.bars}>
          {/* Left bar (team 1 fills right-to-left) */}
          <View style={styles.barTrack}>
            <View style={styles.barInner}>
              <Animated.View style={[styles.barFill1Wrap, { width: width1 }]}>
                <LinearGradient
                  colors={[colors.green.mid, colors.green.neon]}
                  start={{ x: 0, y: 0 }}
                  end={{ x: 1, y: 0 }}
                  style={StyleSheet.absoluteFill}
                />
              </Animated.View>
            </View>
          </View>

          {/* Center dot */}
          <View style={styles.centerDot} />

          {/* Right bar (team 2) */}
          <View style={styles.barTrack}>
            <View style={styles.barInner}>
              <Animated.View style={[styles.barFill2Wrap, { width: width2 }]}>
                <LinearGradient
                  colors={[colors.blue.light, colors.blue.mid]}
                  start={{ x: 0, y: 0 }}
                  end={{ x: 1, y: 0 }}
                  style={StyleSheet.absoluteFill}
                />
              </Animated.View>
            </View>
          </View>
        </View>
      </View>

      {/* Value 2 */}
      <Text style={[styles.val, styles.valRight, isT2Better && styles.valHighlight2]}>
        {value2}{unit}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  row: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    marginVertical: 6,
  },
  val: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.sm,
    color: colors.text.secondary,
    width: 38,
    textAlign: 'right',
  },
  valRight: {
    textAlign: 'left',
  },
  valHighlight: {
    color: colors.green.neon,
  },
  valHighlight2: {
    color: colors.blue.light,
  },
  barsWrapper: {
    flex: 1,
    gap: 4,
  },
  label: {
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
    color: colors.text.muted,
    textAlign: 'center',
    letterSpacing: 0.3,
  },
  bars: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 3,
    height: 8,
  },
  barTrack: {
    flex: 1,
    height: 8,
    backgroundColor: colors.bg.elevated,
    borderRadius: radius.full,
    overflow: 'hidden',
  },
  barInner: {
    flex: 1,
    height: '100%',
    flexDirection: 'row',
    justifyContent: 'flex-end',
  },
  barFill1Wrap: {
    height: '100%',
    borderRadius: radius.full,
    overflow: 'hidden',
  },
  barFill2Wrap: {
    height: '100%',
    borderRadius: radius.full,
    overflow: 'hidden',
  },
  centerDot: {
    width: 4,
    height: 4,
    borderRadius: 2,
    backgroundColor: colors.bg.border,
  },
});
