import React, { useRef } from 'react';
import { View, Text, TouchableOpacity, StyleSheet, Animated } from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { useRouter, usePathname } from 'expo-router';
import { useAppAuth } from '../../auth/AuthContext';
import { colors, fontSize, radius, spacing } from '../../theme';

const NAV_ITEMS = [
  { label: 'Dashboard',  icon: '⚡', route: '/'          },
  { label: 'Players',    icon: '👤', route: '/players'   },
  { label: 'Analytics',  icon: '📊', route: '/analytics' },
  { label: 'Power BI',   icon: '📊', route: '/powerbi'   },
];

function NavItem({ item, active }) {
  const router   = useRouter();
  const scaleAnim = useRef(new Animated.Value(1)).current;

  return (
    <Animated.View style={{ transform: [{ scale: scaleAnim }] }}>
      <TouchableOpacity
        onPress={() => router.push(item.route)}
        onPressIn={() => Animated.spring(scaleAnim, { toValue: 0.96, useNativeDriver: true }).start()}
        onPressOut={() => Animated.spring(scaleAnim, { toValue: 1,    useNativeDriver: true }).start()}
        style={[styles.navItem, active && styles.navItemActive]}
        activeOpacity={1}
      >
        {active && (
          <LinearGradient
            colors={[colors.green.muted, 'transparent']}
            start={{ x: 0, y: 0 }} end={{ x: 1, y: 0 }}
            style={StyleSheet.absoluteFill}
          />
        )}
        {active && <View style={styles.activeBar} />}
        <Text style={styles.navIcon}>{item.icon}</Text>
        <Text style={[styles.navLabel, active && styles.navLabelActive]}>
          {item.label}
        </Text>
      </TouchableOpacity>
    </Animated.View>
  );
}

export default function DesktopNav() {
  const pathname = usePathname();
  const router = useRouter();
  const { logout } = useAppAuth();

  const onSignOut = async () => {
    await logout();
    router.replace('/login');
  };

  return (
    <View style={styles.sidebar}>
      <LinearGradient
        colors={[colors.bg.secondary, colors.bg.primary]}
        style={StyleSheet.absoluteFill}
      />
      <View style={styles.borderRight} />

      {/* Logo */}
      <View style={styles.logo}>
        <LinearGradient
          colors={[colors.green.mid, colors.green.neon]}
          style={styles.logoBall}
        >
          <View style={styles.logoSeam1} />
          <View style={styles.logoSeam2} />
        </LinearGradient>
        <View style={styles.logoText}>
          <Text style={styles.logoFip}>FIP</Text>
          <Text style={styles.logoPadel}>PADEL</Text>
        </View>
      </View>

      <View style={styles.divider} />

      {/* Nav section label */}
      <Text style={styles.navSectionLabel}>NAVIGATION</Text>

      {/* Nav items */}
      <View style={styles.navList}>
        {NAV_ITEMS.map(item => (
          <NavItem
            key={item.route}
            item={item}
            active={pathname === item.route}
          />
        ))}
      </View>

      <View style={styles.divider} />

      {/* Footer */}
      <View style={styles.sidebarFooter}>
        <TouchableOpacity style={styles.signOutBtn} onPress={onSignOut} activeOpacity={0.85}>
          <Text style={styles.signOutIcon}>↩</Text>
          <Text style={styles.signOutText}>Sign out</Text>
        </TouchableOpacity>
        <Text style={styles.footerPowered}>powered by PADELAS</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  sidebar: {
    width: 220,
    height: '100%',
    paddingTop: spacing.xl,
    paddingBottom: spacing.lg,
    position: 'relative',
    overflow: 'hidden',
  },
  borderRight: {
    position: 'absolute',
    top: 0, bottom: 0, right: 0,
    width: 1,
    backgroundColor: colors.bg.border,
  },

  // ── Logo ──
  logo: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    paddingHorizontal: spacing.lg,
    marginBottom: spacing.lg,
  },
  logoBall: {
    width: 34,
    height: 34,
    borderRadius: 17,
    alignItems: 'center',
    justifyContent: 'center',
    overflow: 'hidden',
    shadowColor: colors.green.neon,
    shadowOffset: { width: 0, height: 0 },
    shadowOpacity: 0.6,
    shadowRadius: 8,
    elevation: 6,
  },
  logoSeam1: {
    position: 'absolute',
    width: 50, height: 2,
    backgroundColor: 'rgba(255,255,255,0.3)',
    transform: [{ rotate: '30deg' }],
  },
  logoSeam2: {
    position: 'absolute',
    width: 50, height: 2,
    backgroundColor: 'rgba(255,255,255,0.3)',
    transform: [{ rotate: '-30deg' }],
  },
  logoText: { gap: -4 },
  logoFip: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 18,
    color: colors.green.neon,
    letterSpacing: 4,
    lineHeight: 20,
    textShadowColor: colors.green.neon,
    textShadowOffset: { width: 0, height: 0 },
    textShadowRadius: 8,
  },
  logoPadel: {
    fontFamily: 'Rajdhani_600SemiBold',
    fontSize: 11,
    color: colors.text.secondary,
    letterSpacing: 3,
  },

  divider: {
    height: 1,
    backgroundColor: colors.bg.border,
    marginHorizontal: spacing.md,
    marginVertical: spacing.md,
  },

  // ── Nav ──
  navSectionLabel: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 9,
    color: colors.text.muted,
    letterSpacing: 2,
    paddingHorizontal: spacing.lg,
    marginBottom: spacing.xs,
  },
  navList: {
    gap: 2,
    paddingHorizontal: spacing.sm,
  },
  navItem: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.sm,
    paddingHorizontal: spacing.md,
    paddingVertical: 11,
    borderRadius: radius.md,
    position: 'relative',
    overflow: 'hidden',
  },
  navItemActive: {},
  activeBar: {
    position: 'absolute',
    left: 0, top: 8, bottom: 8,
    width: 3,
    borderRadius: 2,
    backgroundColor: colors.green.neon,
  },
  navIcon: { fontSize: 16, width: 22, textAlign: 'center' },
  navLabel: {
    fontFamily: 'Inter_500Medium',
    fontSize: fontSize.sm,
    color: colors.text.muted,
  },
  navLabelActive: {
    color: colors.green.neon,
    fontFamily: 'Inter_600SemiBold',
  },

  // ── Footer ──
  sidebarFooter: {
    position: 'absolute',
    bottom: spacing.lg,
    left: spacing.lg,
    right: spacing.lg,
  },
  signOutBtn: {
    height: 36,
    borderRadius: radius.md,
    borderWidth: 1,
    borderColor: colors.bg.border,
    backgroundColor: colors.bg.card,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    gap: spacing.xs,
    marginBottom: spacing.sm,
  },
  signOutIcon: {
    color: colors.accent.red,
    fontSize: 13,
    fontFamily: 'Inter_700Bold',
  },
  signOutText: {
    color: colors.text.primary,
    fontFamily: 'Inter_600SemiBold',
    fontSize: 12,
  },
  footerPowered: {
    fontFamily: 'Inter_500Medium',
    fontSize: 11,
    color: colors.text.muted,
    letterSpacing: 0.5,
    marginTop: spacing.xs,
  },
});

