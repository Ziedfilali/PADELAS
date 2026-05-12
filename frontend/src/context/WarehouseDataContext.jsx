import React, {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useState,
} from 'react';

import { getApiBase } from '../config/apiBase';
import * as mockData from '../data/mockData';

const WarehouseContext = createContext(null);

/** Exclude Padel API rows where names didn't resolve (would show as Unknown in the UI). */
export function padelMatchHasNamedPlayers(m) {
  const norm = s => String(s ?? '').trim().toLowerCase();
  const bad = s => !norm(s) || norm(s) === 'unknown' || norm(s) === 'tbd';
  if (!m?.team1 || !m?.team2) return false;
  return (
    !bad(m.team1.player1) &&
    !bad(m.team1.player2) &&
    !bad(m.team2.player1) &&
    !bad(m.team2.player2)
  );
}

function sortMatchesByDateDesc(list) {
  return [...list].sort((a, b) => (b.date || '').localeCompare(a.date || ''));
}

const W_CLUSTER_NAMES = ['Elite band', 'High tier', 'Mid tier', 'Developing'];
const W_CLUSTER_COLORS = ['#00FF57', '#5BA3F5', '#F5C518', '#FF7D26'];

/** Align with API / DW variants (not only lowercase team_1). */
export function normalizeWinnerUi(w) {
  if (w === null || w === undefined || w === '') return null;
  const n = typeof w === 'number' ? w : Number(String(w).trim());
  if (n === 1) return 'team_1';
  if (n === 2) return 'team_2';
  let s = String(w).trim().toLowerCase().replace(/[\s-]+/g, '_');
  if (['team_1', 'team1', 't1', 'pair_1', 'pair1', 'side_1', 'side1'].includes(s)) {
    return 'team_1';
  }
  if (['team_2', 'team2', 't2', 'pair_2', 'pair2', 'side_2', 'side2'].includes(s)) {
    return 'team_2';
  }
  return null;
}

function simpleIdFromName(name) {
  let h = 0;
  for (let i = 0; i < name.length; i += 1) {
    h = (h * 31 + name.charCodeAt(i)) | 0;
  }
  return `p-${Math.abs(h).toString(16).slice(0, 10)}`;
}

/** When /overview returns matches but empty players (legacy winner encoding), rebuild client-side. */
export function derivePlayersFromMatches(matches) {
  if (!Array.isArray(matches) || matches.length === 0) return [];
  const recs = [];
  for (const m of matches) {
    const nw = normalizeWinnerUi(m?.winner);
    if (!nw || !m.team1 || !m.team2) continue;
    const addRow = (name, stats, win) => {
      if (!name || String(name).trim() === '') return;
      recs.push({
        name: String(name).trim(),
        win,
        aces: Number(stats?.aces) || 0,
        doubleFaults: Number(stats?.doubleFaults) || 0,
        totalPointsWon: Number(stats?.totalPointsWon) || 0,
        totalWonOnReturn: Number(stats?.totalWonOnReturn) || 0,
      });
    };
    if (nw === 'team_1') {
      addRow(m.team1.player1, m.team1.stats, 1);
      addRow(m.team1.player2, m.team1.stats, 1);
      addRow(m.team2.player1, m.team2.stats, 0);
      addRow(m.team2.player2, m.team2.stats, 0);
    } else {
      addRow(m.team1.player1, m.team1.stats, 0);
      addRow(m.team1.player2, m.team1.stats, 0);
      addRow(m.team2.player1, m.team2.stats, 1);
      addRow(m.team2.player2, m.team2.stats, 1);
    }
  }
  if (recs.length === 0) return [];

  const byName = new Map();
  for (const r of recs) {
    if (!byName.has(r.name)) {
      byName.set(r.name, { wins: [], aces: [], doubleFaults: [], tpw: [], twr: [] });
    }
    const b = byName.get(r.name);
    b.wins.push(r.win);
    b.aces.push(r.aces);
    b.doubleFaults.push(r.doubleFaults);
    b.tpw.push(r.totalPointsWon);
    b.twr.push(r.totalWonOnReturn);
  }

  const rows = [...byName.entries()].map(([name, b]) => {
    const n = b.wins.length;
    const mean = arr => (n ? arr.reduce((s, x) => s + x, 0) / n : 0);
    return {
      name,
      winRate: mean(b.wins),
      aces: mean(b.aces),
      doubleFaults: mean(b.doubleFaults),
      totalPointsWon: mean(b.tpw),
      totalWonOnReturn: mean(b.twr),
      matchesPlayed: n,
    };
  });
  rows.sort((a, b) => b.winRate - a.winRate);

  const N = rows.length;
  return rows.map((row, idx) => {
    const rank = idx + 1;
    const cluster = Math.min(3, Math.floor((rank - 1) * 4 / Math.max(N, 1)));
    return {
      id: simpleIdFromName(row.name),
      name: row.name,
      country: '--',
      ranking: rank,
      cluster,
      clusterName: W_CLUSTER_NAMES[cluster],
      profileColor: W_CLUSTER_COLORS[cluster],
      stats: {
        winRate: row.winRate,
        aces: row.aces,
        doubleFaults: row.doubleFaults,
        totalPointsWon: row.totalPointsWon,
        totalWonOnReturn: row.totalWonOnReturn,
        matchesPlayed: row.matchesPlayed,
      },
      recentResults: [],
    };
  });
}

export function WarehouseDataProvider({ children }) {
  const [matches, setMatches] = useState(() => [...mockData.MATCHES]);
  const [tournaments, setTournaments] = useState(() => [...mockData.TOURNAMENTS]);
  const [monthlyViews, setMonthlyViews] = useState(() => [...mockData.MONTHLY_VIEWS]);
  const [players, setPlayers] = useState(() => [...mockData.PLAYERS]);
  const [source, setSource] = useState('mock');
  /** @type {'sql' | 'csv' | null} — from API when source is warehouse (sql = DW, csv = data/*.csv seed) */
  const [dataOrigin, setDataOrigin] = useState(null);
  const [ready, setReady] = useState(false);
  const [lastOverviewAt, setLastOverviewAt] = useState(null);
  /** Live + scheduled matches from padelapi.org for the current calendar month */
  const [padelScheduleMatches, setPadelScheduleMatches] = useState([]);
  /** Loaded via backend `/api/padel/month-schedule` (token stays on model-service, not in the client bundle) */
  const [padelScheduleMeta, setPadelScheduleMeta] = useState({
    loadedAt: null,
    month: null,
    matchCount: 0,
    error: null,
  });

  const loadPadelSchedule = useCallback(async () => {
    const ts = () => new Date().toISOString();
    try {
      const res = await fetch(`${getApiBase()}/api/padel/month-schedule`);
      let json = {};
      try {
        json = await res.json();
      } catch {
        json = {};
      }
      if (!res.ok) {
        const detail =
          typeof json?.detail === 'string'
            ? json.detail
            : Array.isArray(json?.detail)
              ? json.detail.map(d => d.msg || d).join(', ')
              : `HTTP ${res.status}`;
        setPadelScheduleMatches([]);
        setPadelScheduleMeta({
          loadedAt: ts(),
          month: null,
          matchCount: 0,
          error: detail,
        });
        return;
      }
      const list = Array.isArray(json.matches) ? json.matches : [];
      const filtered = list.filter(padelMatchHasNamedPlayers);
      setPadelScheduleMatches(filtered);
      setPadelScheduleMeta({
        loadedAt: ts(),
        month: json.month ?? null,
        matchCount: filtered.length,
        error: null,
      });
    } catch (e) {
      setPadelScheduleMatches([]);
      const msg = e instanceof Error ? e.message : String(e);
      setPadelScheduleMeta({
        loadedAt: ts(),
        month: null,
        matchCount: 0,
        error: msg || 'network_error',
      });
    }
  }, []);

  const loadOverview = useCallback(async () => {
    try {
      const res = await fetch(`${getApiBase()}/api/analytics/overview`);
      if (!res.ok) throw new Error(`overview ${res.status}`);
      const json = await res.json();
      if (Array.isArray(json.matches) && json.matches.length > 0) {
        setMatches(json.matches);
        setTournaments(Array.isArray(json.tournaments) ? json.tournaments : []);
        setMonthlyViews(Array.isArray(json.monthlyViews) ? json.monthlyViews : [...mockData.MONTHLY_VIEWS]);
        const o = json.dataOrigin;
        setDataOrigin(o === 'sql' || o === 'csv' ? o : null);
        const fromApi = Array.isArray(json.players) ? json.players : [];
        const derived =
          fromApi.length > 0 ? fromApi : derivePlayersFromMatches(json.matches);
        if (derived.length > 0) {
          setPlayers(derived);
          setSource(json.source === 'warehouse' ? 'warehouse' : 'mock');
        } else {
          setPlayers([...mockData.PLAYERS]);
          setSource('mock');
          setDataOrigin(null);
        }
      } else {
        setMatches([...mockData.MATCHES]);
        setTournaments([...mockData.TOURNAMENTS]);
        setMonthlyViews([...mockData.MONTHLY_VIEWS]);
        setPlayers([...mockData.PLAYERS]);
        setSource('mock');
        setDataOrigin(null);
      }
    } catch {
      setMatches([...mockData.MATCHES]);
      setTournaments([...mockData.TOURNAMENTS]);
      setMonthlyViews([...mockData.MONTHLY_VIEWS]);
      setPlayers([...mockData.PLAYERS]);
      setSource('mock');
      setDataOrigin(null);
    } finally {
      setLastOverviewAt(new Date().toISOString());
      setReady(true);
    }
  }, []);

  useEffect(() => {
    loadOverview();
    loadPadelSchedule();
  }, [loadOverview, loadPadelSchedule]);

  const getMatchById = useCallback(
    mid => {
      const fromPadel = padelScheduleMatches.find(m => m.id === mid);
      if (fromPadel) return fromPadel;
      return matches.find(m => m.id === mid) || null;
    },
    [matches, padelScheduleMatches],
  );

  const getTournamentById = useCallback(
    tid => tournaments.find(t => t.id === tid) || null,
    [tournaments],
  );

  const getMatchesByTournament = useCallback(
    tid => matches.filter(m => m.tournamentId === tid),
    [matches],
  );

  const getLiveMatches = useCallback(() => {
    const fromDw = matches.filter(m => m.status === 'live');
    const fromPadel = padelScheduleMatches.filter(m => m.status === 'live');
    const ids = new Set(fromDw.map(m => m.id));
    return [...fromDw, ...fromPadel.filter(m => !ids.has(m.id))];
  }, [matches, padelScheduleMatches]);

  const getRecentMatches = useCallback(
    (limit = 10) =>
      sortMatchesByDateDesc(matches.filter(m => m.status === 'finished')).slice(
        0,
        limit,
      ),
    [matches],
  );

  const getPlayerLastFive = useCallback(
    (playerName, excludeId) =>
      sortMatchesByDateDesc(
        matches.filter(
          m =>
            m.id !== excludeId &&
            (m.team1.player1 === playerName ||
              m.team1.player2 === playerName ||
              m.team2.player1 === playerName ||
              m.team2.player2 === playerName),
        ),
      ).slice(0, 5),
    [matches],
  );

  const refreshAll = useCallback(async () => {
    await Promise.all([loadOverview(), loadPadelSchedule()]);
  }, [loadOverview, loadPadelSchedule]);

  const value = useMemo(
    () => ({
      matches,
      tournaments,
      monthlyViews,
      players,
      source,
      dataOrigin,
      ready,
      lastOverviewAt,
      padelScheduleMatches,
      padelScheduleMeta,
      refresh: refreshAll,
      getMatchById,
      getTournamentById,
      getMatchesByTournament,
      getLiveMatches,
      getRecentMatches,
      getPlayerLastFive,
    }),
    [
      matches,
      tournaments,
      monthlyViews,
      players,
      source,
      dataOrigin,
      ready,
      lastOverviewAt,
      padelScheduleMatches,
      padelScheduleMeta,
      refreshAll,
      getMatchById,
      getTournamentById,
      getMatchesByTournament,
      getLiveMatches,
      getRecentMatches,
      getPlayerLastFive,
    ],
  );

  return (
    <WarehouseContext.Provider value={value}>{children}</WarehouseContext.Provider>
  );
}

export function useWarehouseData() {
  const ctx = useContext(WarehouseContext);
  if (!ctx) {
    throw new Error('useWarehouseData must be used within WarehouseDataProvider');
  }
  return ctx;
}
