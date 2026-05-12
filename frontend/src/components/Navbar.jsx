import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet, StatusBar } from 'react-native';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { BlurView } from 'expo-blur';
import { useRouter, usePathname } from 'expo-router';
import { colors, fontSize, fontWeight, spacing } from '../theme';

const NAV_ITEMS = [
  { label: 'Home',       icon: '⚡', route: '/'           },
  { label: 'Players',    icon: '👤', route: '/players'    },
  { label: 'Analytics',  icon: '🤖', route: '/analytics'  },
  { label: 'Power BI',   icon: '📊', route: '/powerbi'    },
];

export default function Navbar() {
  const insets   = useSafeAreaInsets();
  const router   = useRouter();
  const pathname = usePathname();

  return (
    <View style={[styles.wrapper, { paddingBottom: insets.bottom || 8 }]}>
      <BlurView intensity={80} tint="dark" style={StyleSheet.absoluteFill} />
      <View style={styles.border} />
      <View style={styles.inner}>
        {NAV_ITEMS.map((item) => {
          const active = pathname === item.route;
          return (
            <TouchableOpacity
              key={item.route}
              style={styles.tab}
              onPress={() => router.push(item.route)}
              activeOpacity={0.7}
            >
              <View style={[styles.iconBubble, active && styles.iconBubbleActive]}>
                <Text style={styles.icon}>{item.icon}</Text>
              </View>
              <Text style={[styles.label, active && styles.labelActive]}>
                {item.label}
              </Text>
              {active && <View style={styles.activeDot} />}
            </TouchableOpacity>
          );
        })}
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  wrapper: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    backgroundColor: 'rgba(7,9,15,0.92)',
  },
  border: {
    height: 1,
    backgroundColor: colors.bg.border,
  },
  inner: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    paddingTop: spacing.sm,
    paddingHorizontal: spacing.md,
  },
  tab: {
    flex: 1,
    alignItems: 'center',
    gap: 4,
    paddingVertical: 4,
  },
  iconBubble: {
    width: 40,
    height: 40,
    borderRadius: 12,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'transparent',
  },
  iconBubbleActive: {
    backgroundColor: colors.green.muted,
  },
  icon: {
    fontSize: 18,
  },
  label: {
    fontFamily: 'Inter_500Medium',
    fontSize: fontSize.xs,
    color: colors.text.muted,
    letterSpacing: 0.5,
  },
  labelActive: {
    color: colors.green.neon,
  },
  activeDot: {
    width: 4,
    height: 4,
    borderRadius: 2,
    backgroundColor: colors.green.neon,
  },
});

