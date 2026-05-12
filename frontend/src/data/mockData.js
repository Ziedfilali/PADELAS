// ─── TOURNAMENTS ──────────────────────────────────────────────────────────────
export const TOURNAMENTS = [
  {
    id: 'fip-world-2024',
    name: 'FIP World Padel Championship',
    short: 'World Championship',
    location: 'Buenos Aires, Argentina',
    country: 'AR',
    category: 'WORLD',
    surface: 'Indoor Hard',
    status: 'ongoing',
    startDate: '2024-11-10',
    endDate: '2024-11-17',
    prize: '$500,000',
    rounds: ['Round of 16', 'Quarterfinal', 'Semifinal', 'Final'],
    totalMatches: 15,
    liveMatches: 1,
  },
  {
    id: 'premier-padel-doha',
    name: 'Premier Padel P1 Doha',
    short: 'P1 Doha',
    location: 'Doha, Qatar',
    country: 'QA',
    category: 'P1',
    surface: 'Outdoor Hard',
    status: 'finished',
    startDate: '2024-10-01',
    endDate: '2024-10-08',
    prize: '$350,000',
    rounds: ['Round of 32', 'Round of 16', 'Quarterfinal', 'Semifinal', 'Final'],
    totalMatches: 31,
    liveMatches: 0,
  },
  {
    id: 'fip-rise-bogota',
    name: 'FIP Rise Bogotá',
    short: 'Rise Bogotá',
    location: 'Bogotá, Colombia',
    country: 'CO',
    category: 'RISE',
    surface: 'Indoor Hard',
    status: 'upcoming',
    startDate: '2024-12-01',
    endDate: '2024-12-08',
    prize: '$90,000',
    rounds: ['Round of 16', 'Quarterfinal', 'Semifinal', 'Final'],
    totalMatches: 15,
    liveMatches: 0,
  },
  {
    id: 'fip-gold-casablanca',
    name: 'FIP Gold Casablanca',
    short: 'Gold Casablanca',
    location: 'Casablanca, Morocco',
    country: 'MA',
    category: 'GOLD',
    surface: 'Indoor Clay',
    status: 'finished',
    startDate: '2024-09-15',
    endDate: '2024-09-22',
    prize: '$150,000',
    rounds: ['Round of 16', 'Quarterfinal', 'Semifinal', 'Final'],
    totalMatches: 15,
    liveMatches: 0,
  },
  {
    id: 'premier-padel-paris',
    name: 'Premier Padel Major Paris',
    short: 'Major Paris',
    location: 'Paris, France',
    country: 'FR',
    category: 'MAJOR',
    surface: 'Indoor Clay',
    status: 'finished',
    startDate: '2024-07-20',
    endDate: '2024-07-28',
    prize: '$750,000',
    rounds: ['Round of 32', 'Round of 16', 'Quarterfinal', 'Semifinal', 'Final'],
    totalMatches: 31,
    liveMatches: 0,
  },
];

// ─── PLAYERS ──────────────────────────────────────────────────────────────────
export const PLAYERS = [
  {
    id: 'p1', name: 'Arturo Coello', country: 'ES', ranking: 1,
    cluster: 0, clusterName: 'Elite Attacker',
    stats: { winRate: 0.87, aces: 7.2, doubleFaults: 2.1, totalPointsWon: 142.5, totalWonOnReturn: 58.3 },
    recentResults: ['W','W','W','L','W'],
    profileColor: '#00FF57',
  },
  {
    id: 'p2', name: 'Agustín Tapia', country: 'AR', ranking: 2,
    cluster: 0, clusterName: 'Elite Attacker',
    stats: { winRate: 0.85, aces: 6.8, doubleFaults: 2.4, totalPointsWon: 138.2, totalWonOnReturn: 55.1 },
    recentResults: ['W','W','W','W','L'],
    profileColor: '#00FF57',
  },
  {
    id: 'p3', name: 'Juan Lebrón', country: 'ES', ranking: 3,
    cluster: 1, clusterName: 'Defensive Wall',
    stats: { winRate: 0.79, aces: 4.1, doubleFaults: 1.8, totalPointsWon: 129.6, totalWonOnReturn: 67.4 },
    recentResults: ['W','L','W','W','L'],
    profileColor: '#5BA3F5',
  },
  {
    id: 'p4', name: 'Alejandro Galán', country: 'ES', ranking: 4,
    cluster: 1, clusterName: 'Defensive Wall',
    stats: { winRate: 0.77, aces: 3.9, doubleFaults: 1.6, totalPointsWon: 126.3, totalWonOnReturn: 69.2 },
    recentResults: ['L','W','W','L','W'],
    profileColor: '#5BA3F5',
  },
  {
    id: 'p5', name: 'Federico Chingotto', country: 'AR', ranking: 5,
    cluster: 2, clusterName: 'Balanced Pro',
    stats: { winRate: 0.72, aces: 5.5, doubleFaults: 2.9, totalPointsWon: 118.7, totalWonOnReturn: 51.8 },
    recentResults: ['W','W','L','W','L'],
    profileColor: '#F5C518',
  },
  {
    id: 'p6', name: 'Juan Tello', country: 'AR', ranking: 6,
    cluster: 2, clusterName: 'Balanced Pro',
    stats: { winRate: 0.70, aces: 5.2, doubleFaults: 2.7, totalPointsWon: 115.4, totalWonOnReturn: 52.3 },
    recentResults: ['L','W','W','L','W'],
    profileColor: '#F5C518',
  },
  {
    id: 'p7', name: 'Franco Stupaczuk', country: 'AR', ranking: 7,
    cluster: 2, clusterName: 'Balanced Pro',
    stats: { winRate: 0.68, aces: 5.8, doubleFaults: 3.1, totalPointsWon: 113.2, totalWonOnReturn: 48.6 },
    recentResults: ['W','L','L','W','W'],
    profileColor: '#F5C518',
  },
  {
    id: 'p8', name: 'Martín Di Nenno', country: 'AR', ranking: 8,
    cluster: 3, clusterName: 'Rising Star',
    stats: { winRate: 0.65, aces: 6.1, doubleFaults: 3.5, totalPointsWon: 108.9, totalWonOnReturn: 44.2 },
    recentResults: ['L','W','W','W','L'],
    profileColor: '#FF7D26',
  },
  {
    id: 'p9', name: 'Sanyo Gutiérrez', country: 'AR', ranking: 9,
    cluster: 1, clusterName: 'Defensive Wall',
    stats: { winRate: 0.66, aces: 3.2, doubleFaults: 1.4, totalPointsWon: 110.3, totalWonOnReturn: 72.1 },
    recentResults: ['W','W','L','W','W'],
    profileColor: '#5BA3F5',
  },
  {
    id: 'p10', name: 'Matías Díaz', country: 'AR', ranking: 10,
    cluster: 1, clusterName: 'Defensive Wall',
    stats: { winRate: 0.64, aces: 3.0, doubleFaults: 1.3, totalPointsWon: 107.8, totalWonOnReturn: 70.9 },
    recentResults: ['W','L','W','W','L'],
    profileColor: '#5BA3F5',
  },
  {
    id: 'p11', name: 'Álvaro Cepero', country: 'ES', ranking: 11,
    cluster: 3, clusterName: 'Rising Star',
    stats: { winRate: 0.61, aces: 5.9, doubleFaults: 3.8, totalPointsWon: 104.5, totalWonOnReturn: 41.3 },
    recentResults: ['L','L','W','W','W'],
    profileColor: '#FF7D26',
  },
  {
    id: 'p12', name: 'Lucho Capra', country: 'AR', ranking: 12,
    cluster: 3, clusterName: 'Rising Star',
    stats: { winRate: 0.59, aces: 5.6, doubleFaults: 3.6, totalPointsWon: 101.2, totalWonOnReturn: 39.8 },
    recentResults: ['W','L','W','L','W'],
    profileColor: '#FF7D26',
  },
];

// ─── MATCHES ──────────────────────────────────────────────────────────────────
export const MATCHES = [
  // ── WORLD CHAMPIONSHIP ─────────────────────────────────────────────────────
  {
    id: 'm001',
    tournamentId: 'fip-world-2024',
    tournamentName: 'FIP World Padel Championship',
    round: 'Final',
    date: '2024-11-17',
    time: '18:00',
    status: 'live',
    duration: '1h 42m',
    team1: {
      player1: 'Arturo Coello', player1Country: 'ES',
      player2: 'Agustín Tapia', player2Country: 'AR',
      score: 2,
      sets: [6, 6, 5],
      stats: {
        aces: 8, doubleFaults: 3, firstServePct: 72,
        wonOn1stServe: 78, wonOn2ndServe: 54,
        totalPointsWon: 125, breakPointsConverted: 5,
        totalWonOnReturn: 42,
      },
    },
    team2: {
      player1: 'Juan Lebrón', player1Country: 'ES',
      player2: 'Alejandro Galán', player2Country: 'ES',
      score: 1,
      sets: [2, 7, 4],
      stats: {
        aces: 5, doubleFaults: 4, firstServePct: 68,
        wonOn1stServe: 71, wonOn2ndServe: 49,
        totalPointsWon: 98, breakPointsConverted: 2,
        totalWonOnReturn: 33,
      },
    },
    winner: null,
    views: 245000,
    interactions: 38500,
    mlPrediction: { winner: 'team_1', team1Probability: 0.68, modelVersion: 3 },
  },
  {
    id: 'm002',
    tournamentId: 'fip-world-2024',
    tournamentName: 'FIP World Padel Championship',
    round: 'Semifinal',
    date: '2024-11-15',
    time: '16:00',
    status: 'finished',
    duration: '1h 28m',
    team1: {
      player1: 'Arturo Coello', player1Country: 'ES',
      player2: 'Agustín Tapia', player2Country: 'AR',
      score: 2,
      sets: [6, 6],
      stats: {
        aces: 10, doubleFaults: 2, firstServePct: 75,
        wonOn1stServe: 81, wonOn2ndServe: 58,
        totalPointsWon: 135, breakPointsConverted: 7,
        totalWonOnReturn: 48,
      },
    },
    team2: {
      player1: 'Federico Chingotto', player1Country: 'AR',
      player2: 'Juan Tello', player2Country: 'AR',
      score: 0,
      sets: [3, 4],
      stats: {
        aces: 4, doubleFaults: 6, firstServePct: 62,
        wonOn1stServe: 65, wonOn2ndServe: 44,
        totalPointsWon: 82, breakPointsConverted: 1,
        totalWonOnReturn: 28,
      },
    },
    winner: 'team_1',
    views: 198000,
    interactions: 31200,
    mlPrediction: { winner: 'team_1', team1Probability: 0.74, modelVersion: 3 },
  },
  {
    id: 'm003',
    tournamentId: 'fip-world-2024',
    tournamentName: 'FIP World Padel Championship',
    round: 'Semifinal',
    date: '2024-11-15',
    time: '19:00',
    status: 'finished',
    duration: '2h 05m',
    team1: {
      player1: 'Juan Lebrón', player1Country: 'ES',
      player2: 'Alejandro Galán', player2Country: 'ES',
      score: 2,
      sets: [6, 4, 7],
      stats: {
        aces: 6, doubleFaults: 3, firstServePct: 70,
        wonOn1stServe: 74, wonOn2ndServe: 52,
        totalPointsWon: 148, breakPointsConverted: 4,
        totalWonOnReturn: 61,
      },
    },
    team2: {
      player1: 'Franco Stupaczuk', player1Country: 'AR',
      player2: 'Martín Di Nenno', player2Country: 'AR',
      score: 1,
      sets: [3, 6, 5],
      stats: {
        aces: 7, doubleFaults: 5, firstServePct: 66,
        wonOn1stServe: 69, wonOn2ndServe: 47,
        totalPointsWon: 130, breakPointsConverted: 3,
        totalWonOnReturn: 45,
      },
    },
    winner: 'team_1',
    views: 176000,
    interactions: 28900,
    mlPrediction: { winner: 'team_1', team1Probability: 0.56, modelVersion: 3 },
  },
  {
    id: 'm004',
    tournamentId: 'fip-world-2024',
    tournamentName: 'FIP World Padel Championship',
    round: 'Quarterfinal',
    date: '2024-11-13',
    time: '14:00',
    status: 'finished',
    duration: '1h 15m',
    team1: {
      player1: 'Arturo Coello', player1Country: 'ES',
      player2: 'Agustín Tapia', player2Country: 'AR',
      score: 2,
      sets: [6, 6],
      stats: {
        aces: 12, doubleFaults: 1, firstServePct: 78,
        wonOn1stServe: 84, wonOn2ndServe: 61,
        totalPointsWon: 118, breakPointsConverted: 8,
        totalWonOnReturn: 52,
      },
    },
    team2: {
      player1: 'Sanyo Gutiérrez', player1Country: 'AR',
      player2: 'Matías Díaz', player2Country: 'AR',
      score: 0,
      sets: [1, 2],
      stats: {
        aces: 2, doubleFaults: 7, firstServePct: 58,
        wonOn1stServe: 61, wonOn2ndServe: 40,
        totalPointsWon: 64, breakPointsConverted: 0,
        totalWonOnReturn: 21,
      },
    },
    winner: 'team_1',
    views: 142000,
    interactions: 22800,
    mlPrediction: { winner: 'team_1', team1Probability: 0.81, modelVersion: 3 },
  },
  {
    id: 'm005',
    tournamentId: 'fip-world-2024',
    tournamentName: 'FIP World Padel Championship',
    round: 'Quarterfinal',
    date: '2024-11-13',
    time: '17:00',
    status: 'finished',
    duration: '1h 52m',
    team1: {
      player1: 'Juan Lebrón', player1Country: 'ES',
      player2: 'Alejandro Galán', player2Country: 'ES',
      score: 2,
      sets: [7, 5, 6],
      stats: {
        aces: 5, doubleFaults: 4, firstServePct: 68,
        wonOn1stServe: 72, wonOn2ndServe: 50,
        totalPointsWon: 141, breakPointsConverted: 4,
        totalWonOnReturn: 58,
      },
    },
    team2: {
      player1: 'Álvaro Cepero', player1Country: 'ES',
      player2: 'Lucho Capra', player2Country: 'AR',
      score: 1,
      sets: [6, 7, 2],
      stats: {
        aces: 8, doubleFaults: 5, firstServePct: 64,
        wonOn1stServe: 68, wonOn2ndServe: 46,
        totalPointsWon: 118, breakPointsConverted: 3,
        totalWonOnReturn: 39,
      },
    },
    winner: 'team_1',
    views: 134000,
    interactions: 19700,
    mlPrediction: { winner: 'team_2', team1Probability: 0.44, modelVersion: 3 },
  },
  {
    id: 'm006',
    tournamentId: 'fip-world-2024',
    tournamentName: 'FIP World Padel Championship',
    round: 'Quarterfinal',
    date: '2024-11-13',
    time: '12:00',
    status: 'finished',
    duration: '1h 37m',
    team1: {
      player1: 'Federico Chingotto', player1Country: 'AR',
      player2: 'Juan Tello', player2Country: 'AR',
      score: 2,
      sets: [6, 7],
      stats: {
        aces: 6, doubleFaults: 3, firstServePct: 70,
        wonOn1stServe: 74, wonOn2ndServe: 51,
        totalPointsWon: 122, breakPointsConverted: 5,
        totalWonOnReturn: 47,
      },
    },
    team2: {
      player1: 'Alex Ruiz', player1Country: 'ES',
      player2: 'Edu Alonso', player2Country: 'ES',
      score: 0,
      sets: [3, 5],
      stats: {
        aces: 4, doubleFaults: 5, firstServePct: 63,
        wonOn1stServe: 67, wonOn2ndServe: 44,
        totalPointsWon: 89, breakPointsConverted: 1,
        totalWonOnReturn: 31,
      },
    },
    winner: 'team_1',
    views: 98000,
    interactions: 14200,
    mlPrediction: { winner: 'team_1', team1Probability: 0.63, modelVersion: 3 },
  },
  {
    id: 'm007',
    tournamentId: 'fip-world-2024',
    tournamentName: 'FIP World Padel Championship',
    round: 'Quarterfinal',
    date: '2024-11-13',
    time: '20:00',
    status: 'finished',
    duration: '1h 44m',
    team1: {
      player1: 'Franco Stupaczuk', player1Country: 'AR',
      player2: 'Martín Di Nenno', player2Country: 'AR',
      score: 2,
      sets: [6, 3, 6],
      stats: {
        aces: 9, doubleFaults: 4, firstServePct: 67,
        wonOn1stServe: 71, wonOn2ndServe: 48,
        totalPointsWon: 131, breakPointsConverted: 6,
        totalWonOnReturn: 43,
      },
    },
    team2: {
      player1: 'Sanyo Gutiérrez', player1Country: 'AR',
      player2: 'Matías Díaz', player2Country: 'AR',
      score: 1,
      sets: [1, 6, 4],
      stats: {
        aces: 3, doubleFaults: 3, firstServePct: 71,
        wonOn1stServe: 74, wonOn2ndServe: 53,
        totalPointsWon: 109, breakPointsConverted: 2,
        totalWonOnReturn: 62,
      },
    },
    winner: 'team_1',
    views: 112000,
    interactions: 17500,
    mlPrediction: { winner: 'team_2', team1Probability: 0.41, modelVersion: 3 },
  },

  // ── PREMIER PADEL DOHA ─────────────────────────────────────────────────────
  {
    id: 'm010',
    tournamentId: 'premier-padel-doha',
    tournamentName: 'Premier Padel P1 Doha',
    round: 'Final',
    date: '2024-10-08',
    time: '19:00',
    status: 'finished',
    duration: '1h 38m',
    team1: {
      player1: 'Arturo Coello', player1Country: 'ES',
      player2: 'Agustín Tapia', player2Country: 'AR',
      score: 2,
      sets: [6, 7],
      stats: {
        aces: 11, doubleFaults: 2, firstServePct: 74,
        wonOn1stServe: 80, wonOn2ndServe: 57,
        totalPointsWon: 128, breakPointsConverted: 6,
        totalWonOnReturn: 50,
      },
    },
    team2: {
      player1: 'Federico Chingotto', player1Country: 'AR',
      player2: 'Juan Tello', player2Country: 'AR',
      score: 0,
      sets: [4, 5],
      stats: {
        aces: 5, doubleFaults: 5, firstServePct: 65,
        wonOn1stServe: 68, wonOn2ndServe: 46,
        totalPointsWon: 94, breakPointsConverted: 2,
        totalWonOnReturn: 36,
      },
    },
    winner: 'team_1',
    views: 312000,
    interactions: 48900,
    mlPrediction: { winner: 'team_1', team1Probability: 0.71, modelVersion: 2 },
  },
  {
    id: 'm011',
    tournamentId: 'premier-padel-doha',
    tournamentName: 'Premier Padel P1 Doha',
    round: 'Semifinal',
    date: '2024-10-06',
    time: '17:30',
    status: 'finished',
    duration: '1h 55m',
    team1: {
      player1: 'Arturo Coello', player1Country: 'ES',
      player2: 'Agustín Tapia', player2Country: 'AR',
      score: 2,
      sets: [4, 6, 6],
      stats: {
        aces: 7, doubleFaults: 4, firstServePct: 70,
        wonOn1stServe: 74, wonOn2ndServe: 51,
        totalPointsWon: 143, breakPointsConverted: 4,
        totalWonOnReturn: 55,
      },
    },
    team2: {
      player1: 'Juan Lebrón', player1Country: 'ES',
      player2: 'Alejandro Galán', player2Country: 'ES',
      score: 1,
      sets: [6, 2, 4],
      stats: {
        aces: 4, doubleFaults: 3, firstServePct: 72,
        wonOn1stServe: 75, wonOn2ndServe: 54,
        totalPointsWon: 127, breakPointsConverted: 3,
        totalWonOnReturn: 63,
      },
    },
    winner: 'team_1',
    views: 224000,
    interactions: 35600,
    mlPrediction: { winner: 'team_2', team1Probability: 0.47, modelVersion: 2 },
  },
];

// ─── MONTHLY VIEWS (Time Series) ──────────────────────────────────────────────
export const MONTHLY_VIEWS = [
  { month: 'Jan', year: 2024, totalViews: 1_240_000, matches: 18 },
  { month: 'Feb', year: 2024, totalViews: 1_580_000, matches: 22 },
  { month: 'Mar', year: 2024, totalViews: 2_140_000, matches: 31 },
  { month: 'Apr', year: 2024, totalViews: 1_890_000, matches: 27 },
  { month: 'May', year: 2024, totalViews: 2_450_000, matches: 35 },
  { month: 'Jun', year: 2024, totalViews: 2_980_000, matches: 42 },
  { month: 'Jul', year: 2024, totalViews: 3_750_000, matches: 51 },
  { month: 'Aug', year: 2024, totalViews: 3_210_000, matches: 44 },
  { month: 'Sep', year: 2024, totalViews: 4_120_000, matches: 58 },
  { month: 'Oct', year: 2024, totalViews: 4_890_000, matches: 63 },
  { month: 'Nov', year: 2024, totalViews: 5_640_000, matches: 71 },
  { month: 'Dec', year: 2024, totalViews: 6_100_000, matches: 80 }, // forecast
];

// ─── ML MODEL METRICS ─────────────────────────────────────────────────────────
export const ML_METRICS = {
  winnerClassification: {
    name: 'PadelWinnerClassifier',
    algorithm: 'Random Forest',
    accuracy: 0.847,
    precision: 0.831,
    recall: 0.862,
    f1: 0.846,
    version: 3,
    trainedAt: '2024-11-16T08:00:00Z',
    trainingMatches: 4280,
  },
  viewsRegression: {
    name: 'PadelViewsRegression',
    algorithm: 'Ridge Regression',
    r2: 0.724,
    mae: 18420,
    rmse: 24680,
    version: 3,
    trainedAt: '2024-11-16T08:05:00Z',
    trainingMatches: 4280,
  },
  playersClustering: {
    name: 'PadelPlayersClustering',
    algorithm: 'K-Means (k=4)',
    silhouetteScore: 0.612,
    nClusters: 4,
    version: 3,
    trainedAt: '2024-11-16T08:10:00Z',
    playersAnalyzed: 256,
    clusters: [
      { id: 0, name: 'Elite Attacker',  count: 28, color: '#00FF57', traits: ['High aces', 'Aggressive baseline', 'High win rate'] },
      { id: 1, name: 'Defensive Wall',  count: 74, color: '#5BA3F5', traits: ['Strong returns', 'Low errors', 'Consistent'] },
      { id: 2, name: 'Balanced Pro',    count: 98, color: '#F5C518', traits: ['All-round game', 'Solid serve', 'Adaptable'] },
      { id: 3, name: 'Rising Star',     count: 56, color: '#FF7D26', traits: ['High potential', 'Developing game', 'Inconsistent'] },
    ],
  },
  timeSeries: {
    name: 'PadelTimeSeriesForecast',
    algorithm: 'Ridge + Lagged Features',
    trainR2: 0.891,
    forecast: [
      { month: 'Dec 2024', predicted: 6_100_000, lower: 5_600_000, upper: 6_600_000 },
      { month: 'Jan 2025', predicted: 5_200_000, lower: 4_700_000, upper: 5_700_000 },
      { month: 'Feb 2025', predicted: 5_800_000, lower: 5_200_000, upper: 6_400_000 },
    ],
    version: 3,
    trainedAt: '2024-11-16T08:15:00Z',
  },
};

// ─── HELPERS ──────────────────────────────────────────────────────────────────
export function getMatchesByTournament(tournamentId) {
  return MATCHES.filter(m => m.tournamentId === tournamentId);
}

export function getMatchById(id) {
  return MATCHES.find(m => m.id === id);
}

export function getTournamentById(id) {
  return TOURNAMENTS.find(t => t.id === id);
}

export function getLiveMatches() {
  return MATCHES.filter(m => m.status === 'live');
}

export function getRecentMatches(limit = 10) {
  return MATCHES.filter(m => m.status === 'finished').slice(0, limit);
}

export function getPlayersByCluster(clusterId) {
  return PLAYERS.filter(p => p.cluster === clusterId);
}

export function getPlayerLastFive(playerName) {
  return MATCHES
    .filter(m =>
      m.team1.player1 === playerName || m.team1.player2 === playerName ||
      m.team2.player1 === playerName || m.team2.player2 === playerName
    )
    .slice(0, 5)
    .map(m => {
      const isTeam1 = m.team1.player1 === playerName || m.team1.player2 === playerName;
      const won = m.winner === (isTeam1 ? 'team_1' : 'team_2');
      return {
        ...m,
        result: m.winner ? (won ? 'W' : 'L') : 'D',
        playerTeam: isTeam1 ? 'team1' : 'team2',
      };
    });
}
