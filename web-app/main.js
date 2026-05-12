/** Backend API routed via nginx proxy (/api -> model-service). */
const API_BASE = "/api";

const form = document.getElementById("predictForm");
const resultBox = document.getElementById("resultBox");
const confidenceWrap = document.getElementById("confidenceWrap");
const confidenceBar = document.getElementById("confidenceBar");
const historyList = document.getElementById("historyList");
const swapBtn = document.getElementById("swapBtn");
const demoBtn = document.getElementById("demoBtn");
const clearHistoryBtn = document.getElementById("clearHistoryBtn");

const HISTORY_KEY = "padel_predict_history";
let history = JSON.parse(localStorage.getItem(HISTORY_KEY) || "[]");

function persistHistory() {
  localStorage.setItem(HISTORY_KEY, JSON.stringify(history.slice(0, 12)));
}

function renderHistory() {
  if (!history.length) {
    historyList.innerHTML = '<div class="history-item">No predictions yet.</div>';
    return;
  }
  historyList.innerHTML = history
    .map(
      (h) =>
        `<div class="history-item"><strong>${h.winner}</strong> • ${h.tournament} (${h.round})<br>${h.teams}<br><small>${new Date(
          h.at
        ).toLocaleString()}</small></div>`
    )
    .join("");
}

function winnerCard(response) {
  const winner = response.winner === "team_1" ? "Team 1 wins" : "Team 2 wins";
  const players = (response.winner_team_players || []).join(" & ");
  const probability =
    typeof response.team_1_probability === "number"
      ? `${(response.team_1_probability * 100).toFixed(1)}% team_1 confidence`
      : "Probability not provided";
  return `<div class="winner"><h3>${winner}</h3><p><strong>Players:</strong> ${players}</p><p>${probability}</p></div>`;
}

function showError(message, requestId) {
  const idLine = requestId ? `<p><strong>request_id:</strong> ${requestId}</p>` : "";
  resultBox.innerHTML = `<div class="error"><h3>Prediction failed</h3><p>${message}</p>${idLine}</div>`;
  confidenceWrap.classList.add("hidden");
}

function setConfidence(probability) {
  if (typeof probability !== "number") {
    confidenceWrap.classList.add("hidden");
    return;
  }
  confidenceWrap.classList.remove("hidden");
  confidenceBar.style.width = `${Math.max(0, Math.min(100, probability * 100))}%`;
}

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  const data = Object.fromEntries(new FormData(form).entries());
  const payload = {
    tournament_name: data.tournament_name,
    round: data.round,
    team1_player1_name: data.team1_player1_name,
    team1_player2_name: data.team1_player2_name,
    team2_player1_name: data.team2_player1_name,
    team2_player2_name: data.team2_player2_name,
  };

  resultBox.textContent = "Running prediction...";
  confidenceWrap.classList.add("hidden");

  try {
    const response = await fetch(`${API_BASE}/predict/matchup`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });
    const json = await response.json();
    if (!response.ok || json.status === "error") {
      showError(json.detail || "Unknown error", json.request_id);
      return;
    }

    resultBox.innerHTML = winnerCard(json);
    setConfidence(json.team_1_probability);
    history.unshift({
      at: new Date().toISOString(),
      winner: json.winner,
      tournament: payload.tournament_name,
      round: payload.round,
      teams: `${payload.team1_player1_name} + ${payload.team1_player2_name} vs ${payload.team2_player1_name} + ${payload.team2_player2_name}`,
    });
    history = history.slice(0, 12);
    persistHistory();
    renderHistory();
  } catch (err) {
    showError("Could not reach the prediction service. Ensure the API is running.", "");
    console.error(err);
  }
});

swapBtn.addEventListener("click", () => {
  const t1a = document.getElementById("team1_player1_name");
  const t1b = document.getElementById("team1_player2_name");
  const t2a = document.getElementById("team2_player1_name");
  const t2b = document.getElementById("team2_player2_name");
  [t1a.value, t2a.value] = [t2a.value, t1a.value];
  [t1b.value, t2b.value] = [t2b.value, t1b.value];
});

demoBtn.addEventListener("click", () => {
  document.getElementById("tournament_name").value = "Qatar Major";
  document.getElementById("round").value = "Semifinal";
  document.getElementById("team1_player1_name").value = "Arturo Coello";
  document.getElementById("team1_player2_name").value = "Agustin Tapia";
  document.getElementById("team2_player1_name").value = "Alejandro Galan";
  document.getElementById("team2_player2_name").value = "Juan Lebron";
});

clearHistoryBtn.addEventListener("click", () => {
  history = [];
  persistHistory();
  renderHistory();
});

renderHistory();
