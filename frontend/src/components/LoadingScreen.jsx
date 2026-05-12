import React, { useEffect, useRef } from 'react';
import { View, Text, StyleSheet, Animated, Easing, Dimensions } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { colors, fontSize, fontWeight } from '../theme';

const { width, height } = Dimensions.get('window');

export default function LoadingScreen({ onFinish }) {
  const ballAnim    = useRef(new Animated.Value(0)).current;
  const fadeAnim    = useRef(new Animated.Value(0)).current;
  const scaleAnim   = useRef(new Animated.Value(0.6)).current;
  const progressAnim = useRef(new Animated.Value(0)).current;
  const ring1Anim   = useRef(new Animated.Value(0)).current;
  const ring2Anim   = useRef(new Animated.Value(0)).current;
  const textFade    = useRef(new Animated.Value(0)).current;
  const subtextFade = useRef(new Animated.Value(0)).current;

  useEffect(() => {
    // Ball bounce
    Animated.loop(
      Animated.sequence([
        Animated.timing(ballAnim, { toValue: -30, duration: 600, easing: Easing.out(Easing.quad), useNativeDriver: true }),
        Animated.timing(ballAnim, { toValue: 0,   duration: 600, easing: Easing.in(Easing.quad),  useNativeDriver: true }),
      ])
    ).start();

    // Expanding rings
    Animated.loop(
      Animated.sequence([
        Animated.timing(ring1Anim, { toValue: 1, duration: 1800, easing: Easing.out(Easing.ease), useNativeDriver: true }),
        Animated.timing(ring1Anim, { toValue: 0, duration: 0, useNativeDriver: true }),
      ])
    ).start();
    setTimeout(() => {
      Animated.loop(
        Animated.sequence([
          Animated.timing(ring2Anim, { toValue: 1, duration: 1800, easing: Easing.out(Easing.ease), useNativeDriver: true }),
          Animated.timing(ring2Anim, { toValue: 0, duration: 0, useNativeDriver: true }),
        ])
      ).start();
    }, 900);

    // Entrance
    Animated.parallel([
      Animated.timing(fadeAnim,  { toValue: 1, duration: 700,  useNativeDriver: true }),
      Animated.spring(scaleAnim, { toValue: 1, friction: 6, tension: 80, useNativeDriver: true }),
    ]).start();

    // Text fades
    Animated.sequence([
      Animated.delay(400),
      Animated.timing(textFade,    { toValue: 1, duration: 500, useNativeDriver: true }),
      Animated.delay(200),
      Animated.timing(subtextFade, { toValue: 1, duration: 500, useNativeDriver: true }),
    ]).start();

    // Progress bar → finish
    Animated.sequence([
      Animated.delay(600),
      Animated.timing(progressAnim, {
        toValue: 1,
        duration: 2000,
        easing: Easing.bezier(0.4, 0, 0.2, 1),
        useNativeDriver: false,
      }),
    ]).start(() => {
      Animated.timing(fadeAnim, { toValue: 0, duration: 400, useNativeDriver: true }).start(() => {
        onFinish?.();
      });
    });
  }, []);

  const ringStyle = (anim) => ({
    opacity:    anim.interpolate({ inputRange: [0, 1], outputRange: [0.6, 0] }),
    transform: [{ scale: anim.interpolate({ inputRange: [0, 1], outputRange: [0.8, 2.4] }) }],
  });

  const progressWidth = progressAnim.interpolate({
    inputRange: [0, 1],
    outputRange: ['0%', '100%'],
  });

  return (
    <Animated.View style={[styles.container, { opacity: fadeAnim }]}>
      <LinearGradient
        colors={['#07090F', '#0D1B2E', '#071654']}
        start={{ x: 0, y: 0 }}
        end={{ x: 1, y: 1 }}
        style={StyleSheet.absoluteFill}
      />

      {/* Ambient glow */}
      <View style={styles.ambientGlow} />
      <View style={styles.ambientGlow2} />

      <Animated.View style={[styles.content, { transform: [{ scale: scaleAnim }] }]}>

        {/* Rings */}
        <View style={styles.ringContainer}>
          <Animated.View style={[styles.ring, ringStyle(ring1Anim)]} />
          <Animated.View style={[styles.ring, styles.ring2, ringStyle(ring2Anim)]} />

          {/* Ball */}
          <Animated.View style={[styles.ballWrapper, { transform: [{ translateY: ballAnim }] }]}>
            <LinearGradient
              colors={[colors.green.neon, colors.green.mid]}
              style={styles.ball}
            >
              {/* Padel ball seam lines */}
              <View style={styles.seam1} />
              <View style={styles.seam2} />
            </LinearGradient>
          </Animated.View>
        </View>

        {/* Logo */}
        <Animated.View style={[styles.logoContainer, { opacity: textFade }]}>
          <Text style={styles.logoFip}>FIP</Text>
          <Text style={styles.logoPadel}>PADEL</Text>
          <View style={styles.logoLine} />
          <Text style={styles.logoSub}>ANALYTICS</Text>
        </Animated.View>

        {/* Tagline */}
        <Animated.Text style={[styles.tagline, { opacity: subtextFade }]}>
          International Padel Federation
        </Animated.Text>

        {/* Progress */}
        <View style={styles.progressContainer}>
          <View style={styles.progressTrack}>
            <Animated.View style={[styles.progressBar, { width: progressWidth }]}>
              <LinearGradient
                colors={[colors.green.neon, colors.green.mid, colors.blue.light]}
                start={{ x: 0, y: 0 }}
                end={{ x: 1, y: 0 }}
                style={StyleSheet.absoluteFill}
              />
              <View style={styles.progressShine} />
            </Animated.View>
          </View>
        </View>

      </Animated.View>
    </Animated.View>
  );
}

const BALL_SIZE  = 72;
const RING_SIZE  = 110;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: colors.bg.primary,
  },
  ambientGlow: {
    position: 'absolute',
    width: 320,
    height: 320,
    borderRadius: 160,
    backgroundColor: colors.green.glow,
    top: height * 0.2,
    left: width * 0.5 - 160,
    opacity: 0.25,
  },
  ambientGlow2: {
    position: 'absolute',
    width: 240,
    height: 240,
    borderRadius: 120,
    backgroundColor: colors.blue.glow,
    top: height * 0.3,
    left: width * 0.5 - 120,
    opacity: 0.15,
  },
  content: {
    alignItems: 'center',
    gap: 28,
  },
  ringContainer: {
    width: RING_SIZE,
    height: RING_SIZE,
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 8,
  },
  ring: {
    position: 'absolute',
    width: RING_SIZE,
    height: RING_SIZE,
    borderRadius: RING_SIZE / 2,
    borderWidth: 2,
    borderColor: colors.green.neon,
  },
  ring2: {
    borderColor: colors.blue.light,
  },
  ballWrapper: {
    width: BALL_SIZE,
    height: BALL_SIZE,
  },
  ball: {
    width: BALL_SIZE,
    height: BALL_SIZE,
    borderRadius: BALL_SIZE / 2,
    alignItems: 'center',
    justifyContent: 'center',
    shadowColor: colors.green.neon,
    shadowOffset: { width: 0, height: 0 },
    shadowOpacity: 0.9,
    shadowRadius: 20,
    elevation: 12,
    overflow: 'hidden',
  },
  seam1: {
    position: 'absolute',
    width: BALL_SIZE * 1.4,
    height: 2,
    backgroundColor: 'rgba(255,255,255,0.3)',
    borderRadius: 1,
    transform: [{ rotate: '30deg' }],
  },
  seam2: {
    position: 'absolute',
    width: BALL_SIZE * 1.4,
    height: 2,
    backgroundColor: 'rgba(255,255,255,0.3)',
    borderRadius: 1,
    transform: [{ rotate: '-30deg' }],
  },
  logoContainer: {
    alignItems: 'center',
  },
  logoFip: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['3xl'],
    color: colors.green.neon,
    letterSpacing: 10,
    lineHeight: fontSize['3xl'] + 4,
    textShadowColor: colors.green.neon,
    textShadowOffset: { width: 0, height: 0 },
    textShadowRadius: 16,
  },
  logoPadel: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['2xl'],
    color: colors.text.primary,
    letterSpacing: 14,
    lineHeight: fontSize['2xl'] + 4,
  },
  logoLine: {
    width: 80,
    height: 1,
    backgroundColor: colors.green.neon,
    opacity: 0.6,
    marginVertical: 6,
  },
  logoSub: {
    fontFamily: 'Rajdhani_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.text.secondary,
    letterSpacing: 8,
  },
  tagline: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
    letterSpacing: 3,
    textTransform: 'uppercase',
  },
  progressContainer: {
    width: 220,
    marginTop: 8,
  },
  progressTrack: {
    height: 3,
    backgroundColor: colors.bg.border,
    borderRadius: 2,
    overflow: 'hidden',
  },
  progressBar: {
    height: '100%',
    borderRadius: 2,
    overflow: 'hidden',
  },
  progressShine: {
    position: 'absolute',
    right: 0,
    top: -3,
    bottom: -3,
    width: 16,
    backgroundColor: 'rgba(255,255,255,0.5)',
    borderRadius: 8,
  },
});
