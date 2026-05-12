import React, { useCallback, useEffect, useMemo, useState } from 'react';
import { AuthContext } from './AuthContext';
import { clearAuth, loadStoredAuth, saveAuth } from './authStorage';
import { getApiBase } from '../config/apiBase';

/** Re-export so screens can import hook next to the provider if desired. */
export { useAppAuth } from './AuthContext';

async function fetchMe(token) {
  const base = getApiBase();
  const res = await fetch(`${base}/api/auth/me`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  if (!res.ok) throw new Error('unauthorized');
  return res.json();
}

export function AppAuthProvider({ children }) {
  const [token, setToken] = useState(null);
  const [user, setUser] = useState(null);
  const [isReady, setIsReady] = useState(false);

  useEffect(() => {
    let cancelled = false;
    (async () => {
      const { token: t, user: u } = await loadStoredAuth();
      if (cancelled) return;
      if (t) {
        try {
          const me = await fetchMe(t);
          setToken(t);
          setUser(me);
        } catch {
          await clearAuth();
          setToken(null);
          setUser(null);
        }
      } else {
        setToken(null);
        setUser(null);
      }
      setIsReady(true);
    })();
    return () => {
      cancelled = true;
    };
  }, []);

  const login = useCallback(async (username, password) => {
    const base = getApiBase();
    let res;
    try {
      res = await fetch(`${base}/api/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: username.trim(), password }),
      });
    } catch {
      throw new Error('network');
    }
    const data = await res.json().catch(() => ({}));
    if (!res.ok) {
      throw new Error('auth');
    }
    const accessToken = data.access_token;
    const u = data.user;
    if (!accessToken || !u) {
      throw new Error('auth');
    }
    await saveAuth(accessToken, u);
    setToken(accessToken);
    setUser(u);
  }, []);

  const logout = useCallback(async () => {
    await clearAuth();
    setToken(null);
    setUser(null);
  }, []);

  const isAuthenticated = Boolean(token && user);
  const isAdmin = user?.role === 'Admin';
  const userRole = user?.role ?? '';

  const accountName = user?.display_name || user?.username || '';

  const value = useMemo(
    () => ({
      token,
      user,
      login,
      logout,
      signOut: logout,
      isAuthenticated,
      isAdmin,
      userRole,
      isReady,
      accountName,
    }),
    [token, user, login, logout, isAuthenticated, isAdmin, userRole, isReady, accountName],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
