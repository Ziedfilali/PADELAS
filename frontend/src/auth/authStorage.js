import { Platform } from 'react-native';

const TOKEN_KEY = 'padel_auth_token_v1';
const USER_KEY = 'padel_auth_user_v1';

async function getAsyncStorage() {
  try {
    return require('@react-native-async-storage/async-storage').default;
  } catch {
    return null;
  }
}

export async function loadStoredAuth() {
  try {
    if (Platform.OS === 'web' && typeof localStorage !== 'undefined') {
      const token = localStorage.getItem(TOKEN_KEY);
      const userJson = localStorage.getItem(USER_KEY);
      const user = userJson ? JSON.parse(userJson) : null;
      return { token: token || null, user };
    }
    const AsyncStorage = await getAsyncStorage();
    if (!AsyncStorage) return { token: null, user: null };
    const [token, userJson] = await Promise.all([
      AsyncStorage.getItem(TOKEN_KEY),
      AsyncStorage.getItem(USER_KEY),
    ]);
    const user = userJson ? JSON.parse(userJson) : null;
    return { token: token || null, user };
  } catch {
    return { token: null, user: null };
  }
}

export async function saveAuth(token, user) {
  const userJson = JSON.stringify(user);
  if (Platform.OS === 'web' && typeof localStorage !== 'undefined') {
    localStorage.setItem(TOKEN_KEY, token);
    localStorage.setItem(USER_KEY, userJson);
    return;
  }
  const AsyncStorage = await getAsyncStorage();
  if (AsyncStorage) {
    await AsyncStorage.multiSet([
      [TOKEN_KEY, token],
      [USER_KEY, userJson],
    ]);
  }
}

export async function clearAuth() {
  try {
    if (Platform.OS === 'web' && typeof localStorage !== 'undefined') {
      localStorage.removeItem(TOKEN_KEY);
      localStorage.removeItem(USER_KEY);
      return;
    }
    const AsyncStorage = await getAsyncStorage();
    if (AsyncStorage) {
      await AsyncStorage.multiRemove([TOKEN_KEY, USER_KEY]);
    }
  } catch {
    /* ignore */
  }
}
