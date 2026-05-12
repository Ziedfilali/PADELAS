import React, { useEffect, useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Linking,
  Platform,
  ScrollView,
  useWindowDimensions,
} from 'react-native';
import { useResponsive } from '../src/hooks/useResponsive';
import { colors } from '../src/theme';
import { useAppAuth } from '../src/auth/AuthContext';
import { getApiBase } from '../src/config/apiBase';
import { buildReportEmbedUrl, missingPowerBiConfig, powerBiConfig } from '../src/config/powerbi';

function formatViewingLine(user) {
  if (!user?.role) return '';
  if (user.role === 'Admin') return 'Viewing as: Admin — Full access';
  const region = user.role.replace('_Manager', '').replace(/_/g, ' ');
  const who = user.display_name || user.username || 'User';
  return `Viewing as: ${who} — ${region} data only`;
}

export default function PowerBIScreen() {
  const { isDesktop } = useResponsive();
  const { height: winH } = useWindowDimensions();
  const { accountName, user, token, logout, isAdmin, userRole } = useAppAuth();
  const missing = missingPowerBiConfig();
  const [iframeLoaded, setIframeLoaded] = useState(false);
  const [cfg, setCfg] = useState(null);
  const [cfgError, setCfgError] = useState(null);

  const embedUrl = cfg?.embedUrl || buildReportEmbedUrl();

  useEffect(() => {
    setIframeLoaded(false);
  }, [embedUrl]);

  useEffect(() => {
    let cancelled = false;
    if (!token) {
      setCfg(null);
      return undefined;
    }
    (async () => {
      try {
        const res = await fetch(`${getApiBase()}/api/powerbi/embed-config`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        if (!res.ok) throw new Error('embed-config');
        const data = await res.json();
        if (!cancelled) {
          setCfg(data);
          setCfgError(null);
        }
      } catch {
        if (!cancelled) {
          setCfgError('Could not load Power BI embed settings');
          setCfg(null);
        }
      }
    })();
    return () => {
      cancelled = true;
    };
  }, [token]);

  const viewingLine = formatViewingLine(user);

  const embedFrameMin =
    Platform.OS === 'web'
      ? Math.min(Math.max(560, Math.round(winH * 0.62)), 1100)
      : 560;

  return (
    <ScrollView
      style={styles.scroll}
      contentContainerStyle={[styles.scrollContent, isDesktop && styles.scrollContentDesktop]}
      keyboardShouldPersistTaps="handled"
      showsVerticalScrollIndicator
    >
      <View style={[styles.inner, isDesktop && styles.desktopInner]}>
      <View style={styles.headerRow}>
        <View style={styles.headerLeft}>
          <View style={styles.titleRow}>
            <Text style={styles.eyebrow}>MICROSOFT POWER BI</Text>
            {userRole ? (
              <View
                style={[styles.roleBadge, isAdmin ? styles.roleBadgeAdmin : styles.roleBadgeManager]}
              >
                <Text style={[styles.roleBadgeText, isAdmin && styles.roleBadgeTextAdmin]}>
                  {userRole}
                </Text>
              </View>
            ) : null}
          </View>
          <Text style={styles.title}>Dashboard</Text>
          <Text style={styles.sub}>
            Signed in as {accountName || user?.username || 'User'}
          </Text>
          {viewingLine ? <Text style={styles.viewingLine}>{viewingLine}</Text> : null}
        </View>
        <View style={styles.actions}>
          <TouchableOpacity
            style={styles.secondaryBtn}
            onPress={() => Linking.openURL('https://app.powerbi.com')}
          >
            <Text style={styles.secondaryBtnText}>Open Power BI</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.secondaryBtn} onPress={logout}>
            <Text style={styles.secondaryBtnText}>Sign out</Text>
          </TouchableOpacity>
        </View>
      </View>

      <Text style={styles.authHint}>
        Sign in with your Microsoft or school account in this browser so Power BI can load the report (autoAuth). Row-level
        security follows your Microsoft account assignment in Power BI Service.
      </Text>

      {cfgError ? (
        <View style={styles.noticeBox}>
          <Text style={styles.noticeTitle}>Embed settings</Text>
          <Text style={styles.noticeText}>{cfgError}</Text>
        </View>
      ) : null}

      {missing.length > 0 ? (
        <View style={styles.noticeBox}>
          <Text style={styles.noticeTitle}>Missing Power BI config</Text>
          <Text style={styles.noticeText}>{missing.join(', ')}</Text>
        </View>
      ) : null}

      {embedUrl ? (
        Platform.OS === 'web' ? (
          <View style={[styles.iframeContainer, { minHeight: embedFrameMin }]}>
            {!iframeLoaded ? (
              <View style={styles.loadingOverlay} pointerEvents="none">
                <Text style={styles.loadingText}>Chargement du dashboard...</Text>
              </View>
            ) : null}
            {React.createElement('iframe', {
              src: embedUrl,
              style: {
                width: '100%',
                minHeight: embedFrameMin,
                height: embedFrameMin,
                border: 'none',
                display: 'block',
              },
              allowFullScreen: true,
              title: 'Power BI Dashboard',
              onLoad: () => setIframeLoaded(true),
            })}
          </View>
        ) : (
          <View style={styles.noticeBox}>
            <Text style={styles.noticeTitle}>Power BI embed is web-only in this app.</Text>
            <Text style={styles.noticeText}>Open http://localhost:8088/powerbi in your browser.</Text>
          </View>
        )
      ) : (
        <View style={styles.noticeBox}>
          <Text style={styles.noticeTitle}>No report configured yet</Text>
          <Text style={styles.noticeText}>
            Set EXPO_PUBLIC_POWERBI_EMBED_URL or EXPO_PUBLIC_POWERBI_REPORT_ID + EXPO_PUBLIC_POWERBI_CTID.
          </Text>
        </View>
      )}

      <View style={styles.footerInfo}>
        <Text style={styles.footerText}>Workspace: {powerBiConfig.workspaceId || 'n/a'}</Text>
        <Text style={styles.footerText}>Report: {cfg?.reportId || powerBiConfig.reportId || 'n/a'}</Text>
      </View>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  scroll: {
    flex: 1,
    backgroundColor: colors.bg.primary,
  },
  scrollContent: {
    flexGrow: 1,
    paddingBottom: 32,
  },
  scrollContentDesktop: {
    paddingBottom: 48,
  },
  inner: {
    padding: 16,
    gap: 12,
  },
  desktopInner: {
    padding: 28,
  },
  headerRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    gap: 12,
    alignItems: 'flex-start',
    flexWrap: 'wrap',
  },
  headerLeft: {
    flex: 1,
    minWidth: 200,
    gap: 4,
  },
  titleRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 10,
    flexWrap: 'wrap',
  },
  roleBadge: {
    paddingHorizontal: 10,
    paddingVertical: 4,
    borderRadius: 999,
    borderWidth: 1,
  },
  roleBadgeAdmin: {
    borderColor: colors.green.neon,
    backgroundColor: 'rgba(0,255,87,0.12)',
  },
  roleBadgeManager: {
    borderColor: colors.blue.light,
    backgroundColor: colors.blue.muted,
  },
  roleBadgeText: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 10,
    color: colors.blue.light,
    letterSpacing: 0.5,
  },
  roleBadgeTextAdmin: {
    color: colors.green.neon,
  },
  viewingLine: {
    marginTop: 6,
    color: colors.text.secondary,
    fontSize: 13,
    fontFamily: 'Inter_500Medium',
  },
  authHint: {
    color: colors.text.secondary,
    fontSize: 12,
    fontFamily: 'Inter_400Regular',
    lineHeight: 18,
  },
  eyebrow: {
    color: colors.green.neon,
    fontSize: 11,
    fontFamily: 'Inter_700Bold',
    letterSpacing: 1,
  },
  title: {
    color: colors.text.primary,
    fontSize: 28,
    fontFamily: 'Rajdhani_700Bold',
  },
  sub: {
    color: colors.text.secondary,
    fontSize: 13,
    fontFamily: 'Inter_400Regular',
  },
  actions: {
    flexDirection: 'row',
    gap: 8,
  },
  secondaryBtn: {
    borderColor: colors.bg.border,
    borderWidth: 1,
    borderRadius: 10,
    paddingHorizontal: 12,
    paddingVertical: 8,
    backgroundColor: colors.bg.card,
  },
  secondaryBtnText: {
    color: colors.text.primary,
    fontFamily: 'Inter_500Medium',
    fontSize: 12,
  },
  loadingOverlay: {
    ...StyleSheet.absoluteFillObject,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: colors.bg.card,
    zIndex: 1,
  },
  loadingText: {
    color: colors.text.secondary,
    fontFamily: 'Inter_400Regular',
    fontSize: 12,
  },
  noticeBox: {
    borderColor: colors.bg.border,
    borderWidth: 1,
    borderRadius: 12,
    backgroundColor: colors.bg.card,
    padding: 14,
  },
  noticeTitle: {
    color: colors.text.primary,
    fontFamily: 'Inter_700Bold',
    fontSize: 14,
  },
  noticeText: {
    marginTop: 4,
    color: colors.text.secondary,
    fontFamily: 'Inter_400Regular',
    fontSize: 12,
  },
  iframeContainer: {
    position: 'relative',
    minHeight: 560,
    borderColor: colors.bg.border,
    borderWidth: 1,
    borderRadius: 12,
    overflow: 'hidden',
    backgroundColor: colors.bg.card,
  },
  footerInfo: {
    flexDirection: 'row',
    gap: 14,
    flexWrap: 'wrap',
  },
  footerText: {
    color: colors.text.muted,
    fontFamily: 'Inter_400Regular',
    fontSize: 11,
  },
});
