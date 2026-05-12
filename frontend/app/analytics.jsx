import React, { useRef, useEffect, useState } from 'react';
import {
  View,
  Text,
  ScrollView,
  StyleSheet,
  StatusBar,
  Animated,
  TouchableOpacity,
} from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { LinearGradient } from 'expo-linear-gradient';
import { colors, fontSize, radius, spacing } from '../src/theme';
import { ML_METRICS } from '../src/data/mockData';
import { viewershipKpis } from '../src/data/analyticsInsights';
import { useWarehouseData } from '../src/context/WarehouseDataContext';
import { useResponsive } from '../src/hooks/useResponsive';
import TimeSeriesChart from '../src/components/charts/TimeSeriesChart';
import ViewsInteractionsVisuals from '../src/components/charts/ViewsInteractionsVisuals';
import MatchCard from '../src/components/MatchCard';

const PADEL_ANALYTICS_PREVIEW = 4;

function DesktopAnalyticsLayout() {
  const [padelExpanded, setPadelExpanded] = useState(false);
  const { monthlyViews, matches, padelScheduleMatches } = useWarehouseData();
  const fc = ML_METRICS.timeSeries.forecast;
  const kpis = viewershipKpis(monthlyViews, fc, ML_METRICS);
  const padelShown = padelExpanded
    ? padelScheduleMatches
    : padelScheduleMatches.slice(0, PADEL_ANALYTICS_PREVIEW);
  const padelMore = padelScheduleMatches.length > PADEL_ANALYTICS_PREVIEW;

  return (
    <ScrollView
      style={dStyles.container}
      showsVerticalScrollIndicator={false}
      contentContainerStyle={{ paddingBottom: 60 }}
    >
      <View style={dStyles.pageHeader}>
        <LinearGradient colors={['rgba(7,22,84,0.55)', 'transparent']} style={StyleSheet.absoluteFill} />
        <View style={dStyles.pageBadge}>
          <Text style={dStyles.pageBadgeText}>VIEWERSHIP</Text>
        </View>
        <Text style={dStyles.pageTitle}>Viewership analytics</Text>
        <Text style={dStyles.pageSub}>
          Monthly views, short-term outlook, and how audience actions line up with reach — for planning windows and follow-up
          content.
        </Text>
      </View>

      {padelScheduleMatches.length > 0 ? (
        <View style={dStyles.padelBlock}>
          <Text style={dStyles.padelTitle}>This month — Padel API (live & scheduled)</Text>
          {padelShown.map(m => (
            <MatchCard key={m.id} match={m} />
          ))}
          {padelMore ? (
            <TouchableOpacity
              style={dStyles.padelSeeMore}
              onPress={() => setPadelExpanded(e => !e)}
              activeOpacity={0.85}
            >
              <Text style={dStyles.padelSeeMoreText}>
                {padelExpanded
                  ? 'Show less'
                  : `See more (${padelScheduleMatches.length - PADEL_ANALYTICS_PREVIEW} more)`}
              </Text>
            </TouchableOpacity>
          ) : null}
        </View>
      ) : null}

      <View style={dStyles.statsStrip}>
        <View style={dStyles.statCard}>
          <Text style={dStyles.statIcon}>📈</Text>
          <Text style={[dStyles.statValue, { color: colors.green.neon }]}>{kpis.predictedPeakFmt}</Text>
          <Text style={dStyles.statLabel}>Peak views (forecast)</Text>
        </View>
        <View style={dStyles.statCard}>
          <Text style={dStyles.statIcon}>✓</Text>
          <Text style={[dStyles.statValue, { color: colors.blue.light }]}>{kpis.forecastConfidence}%</Text>
          <Text style={dStyles.statLabel}>Forecast confidence</Text>
        </View>
        <View style={dStyles.statCard}>
          <Text style={dStyles.statIcon}>📅</Text>
          <Text style={[dStyles.statValue, { color: colors.accent.gold, fontSize: fontSize.sm }]}>
            {kpis.topPredictedDay}
          </Text>
          <Text style={dStyles.statLabel}>Top predicted day</Text>
        </View>
        <View style={dStyles.statCard}>
          <Text style={dStyles.statIcon}>↗</Text>
          <Text style={[dStyles.statValue, { color: colors.accent.orange }]}>
            {kpis.audienceGrowthPct >= 0 ? '+' : ''}
            {kpis.audienceGrowthPct}%
          </Text>
          <Text style={dStyles.statLabel}>Growth vs last month</Text>
        </View>
      </View>

      <View style={dStyles.chartColumn}>
        <View style={dStyles.chartBlock}>
          <TimeSeriesChart data={monthlyViews} forecast={fc} />
        </View>
        <View style={dStyles.chartBlock}>
          <ViewsInteractionsVisuals monthlyViews={monthlyViews} matches={matches} />
        </View>
      </View>
    </ScrollView>
  );
}

export default function AnalyticsScreen() {
  const insets = useSafeAreaInsets();
  const { isDesktop } = useResponsive();
  const { monthlyViews, matches, padelScheduleMatches } = useWarehouseData();
  const [padelExpanded, setPadelExpanded] = useState(false);
  const fc = ML_METRICS.timeSeries.forecast;
  const kpis = viewershipKpis(monthlyViews, fc, ML_METRICS);
  const headerAnim = useRef(new Animated.Value(0)).current;
  const padelShownMobile = padelExpanded
    ? padelScheduleMatches
    : padelScheduleMatches.slice(0, PADEL_ANALYTICS_PREVIEW);
  const padelMoreMobile = padelScheduleMatches.length > PADEL_ANALYTICS_PREVIEW;
  useEffect(() => {
    if (!isDesktop) {
      Animated.timing(headerAnim, { toValue: 1, duration: 700, useNativeDriver: true }).start();
    }
  }, [isDesktop, headerAnim]);

  if (isDesktop) return <DesktopAnalyticsLayout />;

  return (
    <View style={[styles.container, { paddingTop: insets.top }]}>
      <StatusBar barStyle="light-content" />
      <ScrollView showsVerticalScrollIndicator={false} contentContainerStyle={{ paddingBottom: 100 }}>
        <Animated.View
          style={[
            styles.header,
            {
              opacity: headerAnim,
              transform: [
                {
                  translateY: headerAnim.interpolate({ inputRange: [0, 1], outputRange: [20, 0] }),
                },
              ],
            },
          ]}
        >
          <LinearGradient colors={['#071654', colors.bg.primary]} style={StyleSheet.absoluteFill} />
          <View style={styles.headerBadge}>
            <Text style={styles.headerBadgeText}>VIEWERSHIP</Text>
          </View>
          <Text style={styles.headerTitle}>Viewership analytics</Text>
          <Text style={styles.headerSub}>Views over time and views vs interactions by month</Text>
        </Animated.View>

        <View style={styles.mobileStats}>
          <View style={styles.mStat}>
            <Text style={[styles.mStatVal, { color: colors.green.neon }]}>{kpis.predictedPeakFmt}</Text>
            <Text style={styles.mStatLbl}>Peak (forecast)</Text>
          </View>
          <View style={styles.mStat}>
            <Text style={[styles.mStatVal, { color: colors.blue.light }]}>{kpis.forecastConfidence}%</Text>
            <Text style={styles.mStatLbl}>Confidence</Text>
          </View>
        </View>

        {padelScheduleMatches.length > 0 ? (
          <View style={styles.sectionContent}>
            <Text style={styles.padelMobileTitle}>This month — live & scheduled</Text>
            {padelShownMobile.map(m => (
              <MatchCard key={m.id} match={m} />
            ))}
            {padelMoreMobile ? (
              <TouchableOpacity
                style={styles.padelSeeMoreMobile}
                onPress={() => setPadelExpanded(e => !e)}
                activeOpacity={0.85}
              >
                <Text style={styles.padelSeeMoreMobileText}>
                  {padelExpanded
                    ? 'Show less'
                    : `See more (${padelScheduleMatches.length - PADEL_ANALYTICS_PREVIEW} more)`}
                </Text>
              </TouchableOpacity>
            ) : null}
          </View>
        ) : null}

        <View style={styles.sectionContent}>
          <TimeSeriesChart data={monthlyViews} forecast={fc} />
        </View>
        <View style={styles.sectionContent}>
          <ViewsInteractionsVisuals monthlyViews={monthlyViews} matches={matches} />
        </View>
      </ScrollView>
    </View>
  );
}

const dStyles = StyleSheet.create({
  container: { flex: 1, backgroundColor: colors.bg.primary },
  pageHeader: {
    paddingHorizontal: 48,
    paddingTop: 40,
    paddingBottom: 28,
    position: 'relative',
    overflow: 'hidden',
  },
  pageBadge: {
    backgroundColor: colors.blue.muted,
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: 100,
    alignSelf: 'flex-start',
    marginBottom: 8,
  },
  pageBadgeText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 9,
    color: colors.blue.light,
    letterSpacing: 1.5,
  },
  pageTitle: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 44,
    color: colors.text.primary,
    lineHeight: 48,
    marginBottom: 4,
  },
  pageSub: {
    fontFamily: 'Inter_400Regular',
    fontSize: 13,
    color: colors.text.muted,
    maxWidth: 720,
    lineHeight: 20,
  },
  statsStrip: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    gap: spacing.md,
    paddingHorizontal: 48,
    paddingBottom: 28,
  },
  statCard: {
    flex: 1,
    minWidth: 140,
    backgroundColor: colors.bg.card,
    borderRadius: radius.lg,
    padding: spacing.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
    alignItems: 'center',
    gap: 4,
  },
  statIcon: { fontSize: 18 },
  statValue: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 20,
    textAlign: 'center',
  },
  statLabel: {
    fontFamily: 'Inter_400Regular',
    fontSize: 10,
    color: colors.text.muted,
    textAlign: 'center',
  },
  padelBlock: {
    paddingHorizontal: 48,
    paddingBottom: 28,
    gap: spacing.xs,
    borderBottomWidth: 1,
    borderBottomColor: colors.bg.border,
    marginBottom: spacing.md,
  },
  padelTitle: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 22,
    color: colors.text.primary,
    marginBottom: spacing.sm,
  },
  padelSeeMore: {
    alignSelf: 'center',
    marginTop: spacing.sm,
    paddingVertical: spacing.sm,
    paddingHorizontal: spacing.lg,
  },
  padelSeeMoreText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.green.neon,
  },
  chartColumn: {
    paddingHorizontal: 48,
    gap: spacing.lg,
    paddingBottom: spacing.xl,
  },
  chartBlock: {
    width: '100%',
    maxWidth: 920,
    alignSelf: 'center',
  },
});

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: colors.bg.primary },
  header: {
    paddingHorizontal: spacing.md,
    paddingTop: spacing.md,
    paddingBottom: spacing.lg,
    position: 'relative',
    overflow: 'hidden',
  },
  headerBadge: {
    backgroundColor: colors.blue.muted,
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: radius.full,
    alignSelf: 'flex-start',
    marginBottom: spacing.xs,
  },
  headerBadgeText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 9,
    color: colors.blue.light,
    letterSpacing: 1.5,
  },
  headerTitle: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize['2xl'],
    color: colors.text.primary,
    lineHeight: fontSize['2xl'] + 4,
    marginBottom: 2,
  },
  headerSub: {
    fontFamily: 'Inter_400Regular',
    fontSize: fontSize.xs,
    color: colors.text.muted,
  },
  mobileStats: {
    flexDirection: 'row',
    gap: spacing.sm,
    paddingHorizontal: spacing.md,
    marginBottom: spacing.sm,
  },
  mStat: {
    flex: 1,
    backgroundColor: colors.bg.card,
    borderRadius: radius.md,
    padding: spacing.sm,
    borderWidth: 1,
    borderColor: colors.bg.border,
    alignItems: 'center',
  },
  mStatVal: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: fontSize.md,
  },
  mStatLbl: {
    fontFamily: 'Inter_400Regular',
    fontSize: 9,
    color: colors.text.muted,
    marginTop: 2,
  },
  sectionContent: { paddingHorizontal: spacing.md, marginBottom: spacing.md },
  padelMobileTitle: {
    fontFamily: 'Inter_700Bold',
    fontSize: fontSize.sm,
    color: colors.accent.gold,
    marginBottom: spacing.sm,
  },
  padelSeeMoreMobile: {
    marginTop: spacing.sm,
    paddingVertical: spacing.sm,
    alignItems: 'center',
    borderRadius: radius.md,
    backgroundColor: colors.bg.elevated,
    borderWidth: 1,
    borderColor: colors.bg.border,
  },
  padelSeeMoreMobileText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: fontSize.sm,
    color: colors.green.neon,
  },
});
