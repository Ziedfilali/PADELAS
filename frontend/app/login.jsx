import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  TouchableOpacity,
  StyleSheet,
  ActivityIndicator,
  KeyboardAvoidingView,
  Platform,
  ScrollView,
} from 'react-native';
import { LinearGradient } from 'expo-linear-gradient';
import { useRouter } from 'expo-router';
import { colors, spacing, radius } from '../src/theme';
import { useAppAuth } from '../src/auth/AuthContext';
import { getApiBase } from '../src/config/apiBase';

export default function LoginScreen() {
  const router = useRouter();
  const { login } = useAppAuth();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const onSubmit = async () => {
    setError('');
    setLoading(true);
    try {
      await login(username, password);
      router.replace('/');
    } catch (e) {
      if (e?.message === 'network') {
        setError(
          `Cannot reach the API (${getApiBase()}). Start model-service with Docker Compose and confirm port 8000 is open.`
        );
      } else {
        setError('Invalid username or password');
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <KeyboardAvoidingView
      style={styles.flex}
      behavior={Platform.OS === 'ios' ? 'padding' : undefined}
    >
      <ScrollView
        contentContainerStyle={styles.scroll}
        keyboardShouldPersistTaps="handled"
      >
        <View style={styles.logoRow}>
          <LinearGradient
            colors={[colors.green.mid, colors.green.neon]}
            style={styles.logoBall}
          >
            <View style={styles.logoSeam1} />
            <View style={styles.logoSeam2} />
          </LinearGradient>
          <View>
            <Text style={styles.logoFip}>FIP</Text>
            <Text style={styles.logoPadel}>PADEL ANALYTICS</Text>
          </View>
        </View>

        <Text style={styles.title}>Sign in</Text>
        <Text style={styles.sub}>
          Use your dashboard credentials. Power BI still uses your Microsoft account in the browser (autoAuth).
        </Text>

        <Text style={styles.label}>Username</Text>
        <TextInput
          style={styles.input}
          value={username}
          onChangeText={setUsername}
          autoCapitalize="none"
          autoCorrect={false}
          editable={!loading}
        />

        <Text style={styles.label}>Password</Text>
        <TextInput
          style={styles.input}
          value={password}
          onChangeText={setPassword}
          secureTextEntry
          editable={!loading}
        />

        {error ? <Text style={styles.error}>{error}</Text> : null}

        <TouchableOpacity
          style={[styles.btn, loading && styles.btnDisabled]}
          onPress={onSubmit}
          disabled={loading || !username.trim() || !password}
          activeOpacity={0.85}
        >
          {loading ? (
            <ActivityIndicator color={colors.text.inverse} />
          ) : (
            <Text style={styles.btnText}>Sign In</Text>
          )}
        </TouchableOpacity>
      </ScrollView>
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({
  flex: {
    flex: 1,
    backgroundColor: colors.bg.primary,
  },
  scroll: {
    flexGrow: 1,
    padding: spacing.lg,
    paddingTop: spacing.xxl,
    maxWidth: 440,
    width: '100%',
    alignSelf: 'center',
  },
  logoRow: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: spacing.md,
    marginBottom: spacing.xl,
  },
  logoBall: {
    width: 44,
    height: 44,
    borderRadius: 22,
    alignItems: 'center',
    justifyContent: 'center',
    overflow: 'hidden',
  },
  logoSeam1: {
    position: 'absolute',
    width: 56,
    height: 2,
    backgroundColor: 'rgba(255,255,255,0.35)',
    transform: [{ rotate: '30deg' }],
  },
  logoSeam2: {
    position: 'absolute',
    width: 56,
    height: 2,
    backgroundColor: 'rgba(255,255,255,0.35)',
    transform: [{ rotate: '-30deg' }],
  },
  logoFip: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 28,
    color: colors.green.neon,
    letterSpacing: 6,
  },
  logoPadel: {
    fontFamily: 'Inter_600SemiBold',
    fontSize: 11,
    color: colors.text.secondary,
    letterSpacing: 2,
  },
  title: {
    fontFamily: 'Rajdhani_700Bold',
    fontSize: 32,
    color: colors.text.primary,
    marginBottom: spacing.sm,
  },
  sub: {
    fontFamily: 'Inter_400Regular',
    fontSize: 13,
    color: colors.text.secondary,
    marginBottom: spacing.xl,
    lineHeight: 20,
  },
  label: {
    fontFamily: 'Inter_500Medium',
    fontSize: 12,
    color: colors.text.muted,
    marginBottom: spacing.xs,
  },
  input: {
    borderWidth: 1,
    borderColor: colors.bg.border,
    borderRadius: radius.md,
    paddingHorizontal: spacing.md,
    paddingVertical: 12,
    fontFamily: 'Inter_400Regular',
    fontSize: 15,
    color: colors.text.primary,
    backgroundColor: colors.bg.card,
    marginBottom: spacing.md,
  },
  error: {
    fontFamily: 'Inter_400Regular',
    fontSize: 13,
    color: colors.accent.red,
    marginBottom: spacing.md,
  },
  btn: {
    marginTop: spacing.sm,
    height: 48,
    borderRadius: radius.md,
    backgroundColor: colors.green.mid,
    alignItems: 'center',
    justifyContent: 'center',
  },
  btnDisabled: {
    opacity: 0.7,
  },
  btnText: {
    fontFamily: 'Inter_700Bold',
    fontSize: 15,
    color: colors.text.inverse,
  },
});
