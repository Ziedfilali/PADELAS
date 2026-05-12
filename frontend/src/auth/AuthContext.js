import { createContext, useContext } from 'react';

export const AuthContext = createContext(null);

export function useAppAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) {
    throw new Error('useAppAuth must be used within AppAuthProvider');
  }
  return ctx;
}
