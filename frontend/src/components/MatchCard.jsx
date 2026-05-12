import React, { useRef } from 'react';
import { View, Text, TouchableOpacity, StyleSheet, Animated } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import { colors, fontSize, radius, spacing, shadow } from '../theme';

const STATUS_COLOR = {
  live:     colors.accent.live,
  finished: colors.text.muted,
  upcoming: colors.blue.light,
};

const STATUS_BG = {
  live:     'rgba(255,59,59,0.15)',
  finished: 'rgba(255,255,255,0.05)',
  upcoming: colors.blue.muted,
};

function ResultBadge({ result }) {
  const bg =
    result === 'W' ? colors.green.mid :
    result === 'L' ? colors.accent.red :
    'rgba(245,197,24,0.3)';
  const color =
    result === 'W' ? colors.green.neon :
    result === 'L' ? colors.accent.red :
    colors.accent.gold;
  return (
    <View style={[styles.badge, { backgroundColor: bg }]}>
      <Text style={[styles.badgeText, { color }]}>{result}</Text>
    </View>
  );
}

function ScoreBox({ score, isWinner }) {
  return (
    <View style={[styles.scoreBox, isWinner && styles.scoreBoxWinner]}>
      <Text style={[styles.scoreText, isWinner && styles.scoreTextWinner]}>
        {score}
      </Text>
    </View>
  );
}

export default function MatchCard({ match, compact = false }) {
  const router  = useRouter();
  const pressAnim = useRef(new Animated.Value(1)).current;

  const handlePressIn  = () => Animated.spring(pressAnim, { toValue: 0.97, useNativeDriver: true }).start();
  const handlePressOut = () => Animated.spring(pressAnim, { toValue: 1,    useNativeDriver: true }).start();

  const t1Win = match.winner === 'team_1';
  const t2Win = match.winner === 'team_2';

  return (
    <Animated.View style={{ transform: [{ scale: pressAnim }] }}>
      <TouchableOpacity
        activeOpacity={1}
        onPressIn={handlePressIn}
        onPressOut={handlePressOut}
        onPress={() => router.push(`/match/${match.id}`)}
        style={styles.card}
      >
        {/* Live indicator stripe */}
        {match.status === 'live' && (
          <LinearGradient
            colors={['rgba(255,59,59,0.6)', 'transparent']}
            start={{ x: 0, y: 0 }}
            end={{ x: 1, y: 0 }}
            style={styles.liveStripe}
          />
        )}

        {/* Header row */}
        <View style={styles.header}>
          <View style={[styles.statusPill, { backgroundColor: STATUS_BG[match.status] }]}>
            {match.status === 'live' && <View style={styles.liveDot} />}
            <Text style={[styles.statusText, { color: STATUS_COLOR[match.status] }]}>
              {match.status === 'live' ? 'LIVE' : match.status === 'finished' ? match.duration || 'FT' : match.time}
            </Text>
          </View>
          <Text style={styles.round}>{match.round}</Text>
          <Text style={styles.views}>
            👁 {match.views >= 1000 ? `${(match.views / 1000).toFixed(0)}K` : match.views}
          </Text>
        </View>

        {/* Match body */}
        <View style={styles.body}>
          {/* Team 1 */}
          <View style={styles.teamRow}>
            <View style={styles.playerNames}>
              <Text style={[styles.playerName, t1Win && styles.playerNameWinner]} numberOfLines={1}>
                {match.team1.player1}
              </Text>
              <Text style={[styles.playerName, t1Win && styles.playerNameWinner, styles.player2]} numberOfLines={1}>
                {match.team1.player2}
              </Text>
            </View>
            <View style={styles.scoreArea}>
              {match.team1.sets?.map((g, i) => (
                <Text key={i} style={[styles.setScore, i === match.team1.sets.length - 1 && styles.setScoreLast]}>
                  {g}
                </Text>
              ))}
              <ScoreBox score={match.team1.score} isWinner={t1Win} />
            </View>
          </View>

          <View style={styles.divider} />

          {/* Team 2 */}
          <View style={styles.teamRow}>
            <View style={styles.playerNames}>
              <Text style={[styles.playerName, t2Win && styles.playerNameWinner]} numberOfLines={1}>
                {match.team2.player1}
              </Text>
              <Text style={[styles.playerName, t2Win && styles.playerNameWinner, styles.player2]} numberOfLines={1}>
                {match.team2.player2}
              </Text>
            </View>
            <View style={styles.scoreArea}>
              {match.team2.sets?.map((g, i) => (
                <Text key={i} style={[styles.setScore, i === match.team2.sets.length - 1 && styles.setScoreLast]}>
                  {g}
                </Text>
              ))}
              <ScoreBox score={match.team2.score} isWinner={t2Win} />
            </View>
          </View>
        </View>

        {/* ML Prediction strip */}
        {match.mlPrediction && !compact && (
          <View style={styles.mlStrip}>
            <Text style={styles.mlLabel}>Win prediction</Text>
            <View style={styles.mlBar}>
              <LinearGradient
                colors={[colors.green.mid, colors.green.neon]}
                start={{ x: 0, y: 0 }}
                end={{ x: 1, y: 0 }}
                style={[styles.mlFill, { width: `${match.mlPrediction.team1Probability * 100}%` }]}
              />
            </View>
            <Text style={styles.mlPct}>
              {Math.round(match.mlPrediction.team1Probability * 100)}% T1
            </Text>
          </View>
        )}
      </TouchableOpacity>
    </Animated.View>
  );
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    marginHorizontal: spacing.md,
    marginVertical: spacing.xs,
    overflow: 'hidden',
    borderWidth: 1,
    borderColor: colors.bg.border,
    ...shadow.md,
  },
  liveStripe: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    height: 2,
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: spacing.md,
    paddingTop: spacing.sm + 2,
    paddingBottom: spacing.xs,
    gap: spacing.sm,
  },
  statusPill: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: 8,
    paddingVertical: 3,
    borderRadius: radius.full,
    gap: 5,
  },
  liveDot: {
    width: 6,
    height: 6,
    borderRadius: 3,
    backgroundColor: colors.accent.live,
  },
  statusText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 10,
    letterSpacing: 0.8,
  },
  round: {
    flex: 1,
    fontFamily: 'Inter_500Medium',
    fontSize: fontSize.xs,
    color: colors.text.secondary,
  },
  views: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
  },
  body: {
    paddingHorizontal: spacing.md,
    paddingBottom: spacing.sm,
    gap: 4,
  },
  teamRow: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 6,
  },
  playerNames: {
    flex: 1,
    gap: 1,
  },
  playerName: {
    fontFamily: 'Inter_500Medium',
    fontSize: fontSize.sm,
    color: colors.text.secondary,
  },
  playerNameWinner: {
    color: colors.text.primary,
    fontFamily: 'Inter_700Bold',
  },
  player2: {
    fontSize: fontSize.xs,
    opacity: 0.85,
  },
  scoreArea: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 6,
  },
  setScore: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.sm,
    color: colors.text.muted,
    minWidth: 14,
    textAlign: 'center',
  },
  setScoreLast: {
    color: colors.text.secondary,
  },
  scoreBox: {
    width: 32,
    height: 32,
    borderRadius: radius.sm,
    backgroundColor: colors.bg.elevated,
    alignItems: 'center',
    justifyContent: 'center',
  },
  scoreBoxWinner: {
    backgroundColor: colors.green.muted,
    borderWidth: 1,
    borderColor: colors.green.dark,
  },
  scoreText: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.md,
    color: colors.text.secondary,
  },
  scoreTextWinner: {
    color: colors.green.neon,
  },
  divider: {
    height: 1,
    backgroundColor: colors.bg.border,
    marginVertical: 2,
  },
  badge: {
    paddingHorizontal: 8,
    paddingVertical: 2,
    borderRadius: radius.full,
  },
  badgeText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 10,
    letterSpacing: 0.5,
  },
  mlStrip: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingHorizontal: spacing.md,
    paddingVertical: 8,
    borderTopWidth: 1,
    borderTopColor: colors.bg.border,
    gap: spacing.sm,
  },
  mlLabel: {
    fontFamily: 'Inter_500Medium',
    fontSize: 10,
    color: colors.text.muted,
    letterSpacing: 0.5,
  },
  mlBar: {
    flex: 1,
    height: 4,
    backgroundColor: colors.bg.border,
    borderRadius: 2,
    overflow: 'hidden',
  },
  mlFill: {
    height: '100%',
    borderRadius: 2,
  },
  mlPct: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 10,
    color: colors.green.neon,
  },
});
