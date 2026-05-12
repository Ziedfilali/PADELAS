/**
 * Base URL for FastAPI.
 * - Expo dev (`npx expo start`): use `http://localhost:8000` — Metro has no `/api` proxy; a bare
 *   `/api` URL hits the dev server and fails, so the app falls back to mock data.
 * - Production Docker web: bake `EXPO_PUBLIC_API_BASE` empty → `''` so `fetch(/api/...)` is
 *   same-origin and nginx proxies to model-service (any host/port for the static bundle).
 */
export function getApiBase() {
  if (typeof process !== 'undefined' && process.env.EXPO_PUBLIC_API_BASE !== undefined) {
    const trimmed = String(process.env.EXPO_PUBLIC_API_BASE).trim().replace(/\/$/, '');
    if (trimmed === '') {
      const isDevBundle =
        typeof __DEV__ !== 'undefined'
          ? __DEV__
          : typeof process !== 'undefined' && process.env.NODE_ENV !== 'production';
      if (isDevBundle) {
        return 'http://localhost:8000';
      }
      return '';
    }
    return trimmed;
  }
  return 'http://localhost:8000';
}
