import axios from 'axios';
import {
  TOURNAMENTS, MATCHES, PLAYERS, ML_METRICS, MONTHLY_VIEWS,
  getMatchesByTournament, getMatchById, getTournamentById,
  getLiveMatches, getRecentMatches,
} from '../data/mockData';

const API_BASE =
  (typeof process !== 'undefined' && process.env.EXPO_PUBLIC_API_BASE) ||
  'http://localhost:8000';

const client = axios.create({
  baseURL: API_BASE,
  timeout: 8000,
});

async function safeGet(endpoint, fallback) {
  try {
    const { data } = await client.get(endpoint);
    return { data, fromApi: true };
  } catch {
    return { data: fallback, fromApi: false };
  }
}

async function safePost(endpoint, body, fallback) {
  try {
    const { data } = await client.post(endpoint, body);
    return { data, fromApi: true };
  } catch {
    return { data: fallback, fromApi: false };
  }
}

// ─── API METHODS ──────────────────────────────────────────────────────────────

export async function checkHealth() {
  return safeGet('/health', { status: 'mock', time: new Date().toISOString() });
}

export async function fetchTournaments() {
  return { data: TOURNAMENTS, fromApi: false };
}

export async function fetchTournament(id) {
  return { data: getTournamentById(id), fromApi: false };
}

export async function fetchMatches() {
  return { data: MATCHES, fromApi: false };
}

export async function fetchMatchesByTournament(tournamentId) {
  return { data: getMatchesByTournament(tournamentId), fromApi: false };
}

export async function fetchMatch(id) {
  return { data: getMatchById(id), fromApi: false };
}

export async function fetchLiveMatches() {
  return { data: getLiveMatches(), fromApi: false };
}

export async function fetchRecentMatches(limit = 10) {
  return { data: getRecentMatches(limit), fromApi: false };
}

export async function fetchPlayers() {
  return { data: PLAYERS, fromApi: false };
}

export async function fetchMLMetrics() {
  return safeGet('/models/versions', ML_METRICS);
}

export async function fetchModelVersions() {
  return safeGet('/models/versions', {});
}

export async function fetchMonthlyViews() {
  return { data: MONTHLY_VIEWS, fromApi: false };
}

export async function predictMatchup(payload) {
  const fallback = {
    status: 'ok',
    model_name: 'PadelWinnerClassifier',
    winner: 'team_1',
    team_1_probability: 0.64,
    winner_team_players: [payload.team1_player1_name, payload.team1_player2_name],
    head_to_head_matches_count: 4,
  };
  return safePost('/predict/matchup', payload, fallback);
}

export async function trainAllModels() {
  return safePost('/train/all', {}, {
    status: 'ok',
    executed_at: new Date().toISOString(),
    results: {
      winner_classification:  { model: 'PadelWinnerClassifier',  version: 4, accuracy: 0.851 },
      views_regression:       { model: 'PadelViewsRegression',   version: 4, r2: 0.731 },
      players_clustering:     { model: 'PadelPlayersClustering', version: 4, silhouette: 0.618 },
      timeseries_forecasting: { model: 'PadelTimeSeriesForecast',version: 4, train_r2: 0.895 },
    },
  });
}

// Individual model training — calls /train/all and returns only the relevant slice
export async function trainModel(modelKey) {
  const MOCK = {
    winner_classification:  { model: 'PadelWinnerClassifier',  version: 4, accuracy: 0.851, metric: 'Accuracy', metricVal: '85.1%' },
    views_regression:       { model: 'PadelViewsRegression',   version: 4, r2: 0.731,       metric: 'R²',       metricVal: '0.731' },
    players_clustering:     { model: 'PadelPlayersClustering', version: 4, silhouette: 0.618,metric: 'Silhouette',metricVal: '0.618' },
    timeseries_forecasting: { model: 'PadelTimeSeriesForecast',version: 4, train_r2: 0.895,  metric: 'Train R²', metricVal: '0.895' },
  };
  const { data, fromApi } = await safePost('/train/all', {}, null);
  if (fromApi && data?.results?.[modelKey]) {
    return { data: data.results[modelKey], fromApi: true };
  }
  return { data: MOCK[modelKey], fromApi: false };
}
