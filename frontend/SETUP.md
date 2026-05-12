# FIP Padel Analytics — React Native (Expo) App

## Stack
- **React Native** (Expo SDK 51)
- **Expo Router** — file-based navigation
- **react-native-reanimated + moti** — Framer Motion-level animations
- **Animated API** — custom 3D padel court scene, loading screen, stat bars
- **react-native-chart-kit** — time series charts
- **expo-linear-gradient + expo-blur** — glassmorphism design
- **@expo-google-fonts/inter + rajdhani** — professional typography

## Install

```bash
cd frontend
npm install
```

## Run

```bash
# Start Expo dev server
npm start

# Run on Android
npm run android

# Run on iOS
npm run ios

# Run on Web (browser)
npm run web
```

## Screens

| Screen | Route | Description |
|--------|-------|-------------|
| **Home** | `/` | FlashScore-style dashboard: live matches, tournament list, recent results |
| **Tournament** | `/tournament/[id]` | Full tournament detail: rounds, match list, stats |
| **Match Detail** | `/match/[id]` | Score header + 5 tabs: Summary · Stats · H2H · AI Prediction · Last 5 |
| **Players** | `/players` | K-Means clustering visualization, player profiles |
| **Analytics** | `/analytics` | ML model metrics, prediction playground, time series forecast, training panel |

## Backend Connection

- Set your API URL in `src/services/api.js`
- Default: `http://localhost:8000`
- All endpoints auto-fallback to mock data if backend is unreachable

## Design Palette

| Token | Hex | Usage |
|-------|-----|-------|
| `green.neon` | `#00FF57` | Primary accent, wins, AI |
| `green.mid` | `#13A337` | Secondary green |
| `blue.light` | `#5BA3F5` | Team 2, analytics |
| `blue.deep` | `#0E3B9C` | Deep backgrounds |
| `bg.primary` | `#07090F` | App background |
| `bg.card` | `#111827` | Cards |

## Features
- 🎾 **3D animated padel court** on home hero (pure Animated API, no native GL needed)
- ⚡ **Animated loading screen** with bouncing ball + progress bar
- 📊 **ML model visualizations**: win probability bar, feature importance, cluster scatter
- 🤖 **Prediction playground**: pick any 4 players, get AI win probability in real-time
- 📈 **Time series chart**: 12 months of views + 3-month forecast
- 🔴 **Live match banner**: pulsing live indicator, real-time score, AI prediction bar
- 👥 **Player clustering**: scatter plot, expandable player cards with full stats
- 📋 **5-tab match detail**: Summary | Stats (animated bars) | H2H | AI | Last 5 form
- 🏆 **Tournament pages**: grouped by round, category badges, prize pool

## Power BI + Microsoft Login (Web)

This frontend now supports Microsoft Entra (Azure AD) sign-in and a `/powerbi` page.

### 1) Publish your PBIX
1. Open `Padelas_finl.pbix` in Power BI Desktop.
2. Publish to your target workspace in Power BI Service.
3. In Power BI Service, copy:
   - Workspace ID
   - Report ID
   - (optional) full Embed URL

### 2) Entra app registration
1. Azure Portal -> Entra ID -> App registrations -> New registration.
2. Add Redirect URI (SPA): `http://localhost:8088`.
3. Save:
   - Directory (tenant) ID
   - Application (client) ID
4. API permissions -> Power BI Service:
   - `Report.Read.All`
   - `Workspace.Read.All`
5. Grant admin consent.

### 3) Frontend env vars
Copy `.env.example` to `.env` and fill values:

- `EXPO_PUBLIC_AZURE_TENANT_ID`
- `EXPO_PUBLIC_AZURE_CLIENT_ID`
- `EXPO_PUBLIC_AZURE_REDIRECT_URI=http://localhost:8088`
- `EXPO_PUBLIC_POWERBI_WORKSPACE_ID`
- `EXPO_PUBLIC_POWERBI_REPORT_ID`
- Optional: `EXPO_PUBLIC_POWERBI_EMBED_URL`

### 4) Build + run Docker
```bash
docker compose build frontend
docker compose up -d frontend
```

Open:
- Main app: `http://localhost:8088/`
- Power BI page: `http://localhost:8088/powerbi`

Use Microsoft sign-in when prompted.
