import React, { useState, useEffect, useRef } from 'react';
import { View, StyleSheet, Platform } from 'react-native';
import { Stack, useRouter, usePathname } from 'expo-router';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import { GestureHandlerRootView } from 'react-native-gesture-handler';
import * as SplashScreen from 'expo-splash-screen';
import {
  useFonts,
  Inter_300Light,
  Inter_400Regular,
  Inter_500Medium,
  Inter_600SemiBold,
  Inter_700Bold,
  Inter_800ExtraBold,
  Inter_900Black,
} from '@expo-google-fonts/inter';
import {
  Rajdhani_400Regular,
  Rajdhani_500Medium,
  Rajdhani_600SemiBold,
  Rajdhani_700Bold,
} from '@expo-google-fonts/rajdhani';
import LoadingScreen from '../src/components/LoadingScreen';
import Navbar from '../src/components/Navbar';
import DesktopNav from '../src/components/desktop/DesktopNav';
import { useResponsive } from '../src/hooks/useResponsive';
import { colors } from '../src/theme';
import { AppAuthProvider } from '../src/auth/AuthProvider';
import { useAppAuth } from '../src/auth/AuthContext';
import { WarehouseDataProvider } from '../src/context/WarehouseDataContext';
import { attachGestureController } from '../src/utils/gesture-controller';
import PadelasHero from '../src/components/PadelasHero';
import TTSReadOnHover from '../src/components/TTSReadOnHover';

SplashScreen.preventAutoHideAsync();

function RouteGuard({ children }) {
  const { isAuthenticated, isReady } = useAppAuth();
  const pathname = usePathname();
  const router = useRouter();

  useEffect(() => {
    if (!isReady) return;
    const onLogin = pathname === '/login';
    if (!isAuthenticated && !onLogin) {
      router.replace('/login');
    }
    if (isAuthenticated && onLogin) {
      router.replace('/');
    }
  }, [isReady, isAuthenticated, pathname, router]);

  if (!isReady) return null;

  return children;
}

function RootLayoutContent() {
  const [appReady, setAppReady] = useState(false);
  const [loadingDone, setLoadingDone] = useState(false);
  const { isDesktop } = useResponsive();
  const pathname = usePathname();
  const isLogin = pathname === '/login';
  const gestureCtrlRef = useRef(null);

  // Hand gestures: one controller for the whole app (desktop web). Stays mounted across routes;
  // camera pauses on login; ON/OFF preference survives navigation via sessionStorage.
  useEffect(() => {
    if (Platform.OS !== 'web' || typeof window === 'undefined') return undefined;

    const syncGesture = () => {
      if (!isDesktop) {
        gestureCtrlRef.current?.setEnabled?.(false);
        return;
      }
      if (isLogin) {
        gestureCtrlRef.current?.setEnabled?.(false);
        return;
      }
      if (!gestureCtrlRef.current) {
        gestureCtrlRef.current = attachGestureController({ persistSession: true });
      }
      const wantOn =
        typeof sessionStorage !== 'undefined' &&
        sessionStorage.getItem('mp_gesture_control_enabled') === '1';
      gestureCtrlRef.current.setEnabled(wantOn);
    };

    if (!loadingDone || !appReady) return undefined;

    syncGesture();
    return undefined;
  }, [appReady, loadingDone, isDesktop, isLogin]);

  useEffect(
    () => () => {
      try {
        gestureCtrlRef.current?.destroy?.();
      } catch (e) {}
      gestureCtrlRef.current = null;
    },
    []
  );

  const [fontsLoaded, fontError] = useFonts({
    Inter_300Light,
    Inter_400Regular,
    Inter_500Medium,
    Inter_600SemiBold,
    Inter_700Bold,
    Inter_800ExtraBold,
    Inter_900Black,
    Rajdhani_400Regular,
    Rajdhani_500Medium,
    Rajdhani_600SemiBold,
    Rajdhani_700Bold,
  });

  useEffect(() => {
    if (fontsLoaded || fontError) {
      SplashScreen.hideAsync();
      setAppReady(true);
    }
  }, [fontsLoaded, fontError]);

  if (!appReady) return null;

  if (!loadingDone) {
    return (
      <View style={styles.root}>
        <LoadingScreen onFinish={() => setLoadingDone(true)} />
      </View>
    );
  }

  return (
    <RouteGuard>
      <GestureHandlerRootView style={styles.root}>
        <SafeAreaProvider>
          <View style={[styles.root, isDesktop && styles.desktopRoot]}>
            {!isLogin && isDesktop ? <DesktopNav /> : null}

            <View style={{ flex: 1 }}>
              <Stack
                screenOptions={{
                  headerShown: false,
                  contentStyle: { backgroundColor: colors.bg.primary },
                  animation: isDesktop ? 'none' : 'fade_from_bottom',
                }}
              >
                <Stack.Screen name="login" />
                <Stack.Screen name="index" />
                <Stack.Screen name="tournament/[id]" />
                <Stack.Screen name="match/[id]" />
                <Stack.Screen name="players" />
                <Stack.Screen name="analytics" />
                <Stack.Screen name="powerbi" />
              </Stack>
            </View>

            {!isLogin && !isDesktop ? <Navbar /> : null}
          </View>
          <PadelasHero />
          {Platform.OS === 'web' ? <TTSReadOnHover /> : null}
        </SafeAreaProvider>
      </GestureHandlerRootView>
    </RouteGuard>
  );
}

export default function RootLayout() {
  return (
    <AppAuthProvider>
      <WarehouseDataProvider>
        <RootLayoutContent />
      </WarehouseDataProvider>
    </AppAuthProvider>
  );
}

const styles = StyleSheet.create({
  root: {
    flex: 1,
    backgroundColor: colors.bg.primary,
  },
  desktopRoot: {
    flexDirection: 'row',
  },
});
