import React, { useRef, useEffect } from 'react';
import { View, StyleSheet, Animated, Dimensions } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { colors } from '../theme';

const { width } = Dimensions.get('window');

// Pure React Native animated padel court (no native GL dependency)
// Uses Animated + geometric shapes to simulate a 3D perspective court

function CourtLine({ style }) {
  return <View style={[styles.courtLine, style]} />;
}

function Ball({ animX, animY, scale = 1 }) {
  return (
    <Animated.View
      style={[
        styles.ball,
        {
          width: 14 * scale,
          height: 14 * scale,
          borderRadius: 7 * scale,
          transform: [{ translateX: animX }, { translateY: animY }],
        },
      ]}
    />
  );
}

export default function PadelScene({ height: sceneHeight = 220 }) {
  const rotateAnim = useRef(new Animated.Value(0)).current;
  const ball1X = useRef(new Animated.Value(0)).current;
  const ball1Y = useRef(new Animated.Value(0)).current;
  const ball2X = useRef(new Animated.Value(30)).current;
  const ball2Y = useRef(new Animated.Value(-20)).current;
  const glowAnim = useRef(new Animated.Value(0)).current;

  useEffect(() => {
    // Slow perspective shift
    Animated.loop(
      Animated.sequence([
        Animated.timing(rotateAnim, { toValue: 1, duration: 8000, useNativeDriver: true }),
        Animated.timing(rotateAnim, { toValue: 0, duration: 8000, useNativeDriver: true }),
      ])
    ).start();

    // Ball 1 trajectory (simulate rally)
    Animated.loop(
      Animated.sequence([
        Animated.timing(ball1X, { toValue: 80,  duration: 700, useNativeDriver: true }),
        Animated.timing(ball1Y, { toValue: -40, duration: 350, useNativeDriver: true }),
        Animated.timing(ball1Y, { toValue: 0,   duration: 350, useNativeDriver: true }),
        Animated.timing(ball1X, { toValue: -80, duration: 700, useNativeDriver: true }),
        Animated.timing(ball1Y, { toValue: -30, duration: 350, useNativeDriver: true }),
        Animated.timing(ball1Y, { toValue: 0,   duration: 350, useNativeDriver: true }),
      ])
    ).start();

    // Floating ball 2
    Animated.loop(
      Animated.sequence([
        Animated.timing(ball2Y, { toValue: -40, duration: 1200, useNativeDriver: true }),
        Animated.timing(ball2Y, { toValue: 10,  duration: 1200, useNativeDriver: true }),
      ])
    ).start();

    // Glow pulse
    Animated.loop(
      Animated.sequence([
        Animated.timing(glowAnim, { toValue: 1, duration: 2000, useNativeDriver: true }),
        Animated.timing(glowAnim, { toValue: 0, duration: 2000, useNativeDriver: true }),
      ])
    ).start();
  }, []);

  const perspective = rotateAnim.interpolate({
    inputRange: [0, 1],
    outputRange: ['0deg', '4deg'],
  });

  const glowOpacity = glowAnim.interpolate({
    inputRange: [0, 1],
    outputRange: [0.3, 0.7],
  });

  return (
    <View style={[styles.container, { height: sceneHeight }]}>
      {/* Background gradient */}
      <LinearGradient
        colors={['#07090F', '#0D1B2E', '#071654']}
        start={{ x: 0, y: 0 }}
        end={{ x: 1, y: 1 }}
        style={StyleSheet.absoluteFill}
      />

      {/* Animated glow blob */}
      <Animated.View style={[styles.glowBlob, { opacity: glowOpacity }]} />
      <Animated.View style={[styles.glowBlob2, { opacity: glowAnim.interpolate({ inputRange: [0, 1], outputRange: [0.15, 0.35] }) }]} />

      {/* Court in perspective */}
      <Animated.View
        style={[
          styles.courtWrapper,
          { transform: [{ perspective: 600 }, { rotateX: '55deg' }, { rotateZ: perspective }] },
        ]}
      >
        {/* Court surface */}
        <LinearGradient
          colors={['#0A2218', '#0B3320', '#0D4428']}
          style={styles.court}
        >
          {/* Court lines */}
          <CourtLine style={styles.centerLine} />
          <CourtLine style={styles.serviceLineL} />
          <CourtLine style={styles.serviceLineR} />
          <CourtLine style={styles.baselineTop} />
          <CourtLine style={styles.baselineBottom} />
          <CourtLine style={styles.sideLine1} />
          <CourtLine style={styles.sideLine2} />

          {/* Net */}
          <View style={styles.net}>
            {Array.from({ length: 12 }).map((_, i) => (
              <View key={i} style={styles.netPost} />
            ))}
          </View>

          {/* Grid overlay */}
          <LinearGradient
            colors={['rgba(0,255,87,0.04)', 'transparent', 'rgba(0,255,87,0.04)']}
            style={StyleSheet.absoluteFill}
          />
        </LinearGradient>
      </Animated.View>

      {/* Animated balls (on top, no 3D transform) */}
      <View style={styles.ballLayer}>
        <Ball animX={ball1X} animY={ball1Y} scale={1} />
        <Ball animX={ball2X} animY={ball2Y} scale={0.7} />
      </View>

      {/* Fade edges */}
      <LinearGradient
        colors={['#07090F', 'transparent']}
        style={styles.fadeTop}
        pointerEvents="none"
      />
      <LinearGradient
        colors={['transparent', '#07090F']}
        style={styles.fadeBottom}
        pointerEvents="none"
      />
      <LinearGradient
        colors={['#07090F', 'transparent', '#07090F']}
        start={{ x: 0, y: 0.5 }}
        end={{ x: 1, y: 0.5 }}
        style={styles.fadeSides}
        pointerEvents="none"
      />
    </View>
  );
}

const COURT_W = width * 0.85;
const COURT_H = COURT_W * 0.55;

const styles = StyleSheet.create({
  container: {
    overflow: 'hidden',
    alignItems: 'center',
    justifyContent: 'center',
  },
  glowBlob: {
    position: 'absolute',
    width: 250,
    height: 250,
    borderRadius: 125,
    backgroundColor: colors.green.neon,
    top: -40,
    left: width / 2 - 125,
    opacity: 0.3,
  },
  glowBlob2: {
    position: 'absolute',
    width: 200,
    height: 200,
    borderRadius: 100,
    backgroundColor: colors.blue.mid,
    top: 20,
    left: width / 2 - 100,
  },
  courtWrapper: {
    width: COURT_W,
    height: COURT_H,
    marginTop: 30,
  },
  court: {
    flex: 1,
    borderRadius: 4,
    borderWidth: 2,
    borderColor: 'rgba(0,255,87,0.5)',
    overflow: 'hidden',
    position: 'relative',
  },
  courtLine: {
    position: 'absolute',
    backgroundColor: 'rgba(255,255,255,0.5)',
  },
  centerLine: {
    top: '50%',
    left: 0,
    right: 0,
    height: 1.5,
  },
  serviceLineL: {
    top: '15%',
    bottom: '15%',
    left: '25%',
    width: 1.5,
  },
  serviceLineR: {
    top: '15%',
    bottom: '15%',
    right: '25%',
    width: 1.5,
  },
  baselineTop: {
    top: '15%',
    left: 0,
    right: 0,
    height: 1.5,
  },
  baselineBottom: {
    bottom: '15%',
    left: 0,
    right: 0,
    height: 1.5,
  },
  sideLine1: {
    top: 0,
    bottom: 0,
    left: '10%',
    width: 1,
    opacity: 0.3,
  },
  sideLine2: {
    top: 0,
    bottom: 0,
    right: '10%',
    width: 1,
    opacity: 0.3,
  },
  net: {
    position: 'absolute',
    top: '48%',
    left: 0,
    right: 0,
    height: 6,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-around',
  },
  netPost: {
    width: 2,
    height: 6,
    backgroundColor: 'rgba(255,255,255,0.6)',
    borderRadius: 1,
  },
  ballLayer: {
    position: 'absolute',
    top: '30%',
    left: '45%',
    alignItems: 'center',
    justifyContent: 'center',
  },
  ball: {
    backgroundColor: colors.green.neon,
    shadowColor: colors.green.neon,
    shadowOffset: { width: 0, height: 0 },
    shadowOpacity: 0.9,
    shadowRadius: 8,
    elevation: 8,
  },
  fadeTop: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    height: 50,
  },
  fadeBottom: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    height: 60,
  },
  fadeSides: {
    position: 'absolute',
    top: 0,
    bottom: 0,
    left: 0,
    right: 0,
  },
});
