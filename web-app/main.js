import * as THREE from "https://cdn.jsdelivr.net/npm/three@0.162.0/build/three.module.js";

const form = document.getElementById("predictForm");
const resultBox = document.getElementById("resultBox");
const confidenceWrap = document.getElementById("confidenceWrap");
const confidenceBar = document.getElementById("confidenceBar");
const historyList = document.getElementById("historyList");
const apiStatus = document.getElementById("apiStatus");
const apiUrl = document.getElementById("apiUrl");
const apiUrlLabel = document.getElementById("apiUrlLabel");
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

function updateApiLabel() {
  apiUrlLabel.textContent = apiUrl.value.replace(/\/$/, "");
}

function setApiStatus(type, text) {
  apiStatus.className = `status ${type}`;
  apiStatus.textContent = text;
}

async function checkApiHealth() {
  updateApiLabel();
  setApiStatus("pending", "Checking API...");
  try {
    const base = apiUrl.value.replace(/\/$/, "");
    const res = await fetch(`${base}/health`, { method: "GET" });
    if (!res.ok) throw new Error(`HTTP ${res.status}`);
    setApiStatus("ok", "API online");
  } catch (err) {
    setApiStatus("error", "API unreachable");
    console.error(err);
  }
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
  const base = data.apiUrl.replace(/\/$/, "");
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
    const response = await fetch(`${base}/predict/matchup`, {
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
    showError("Could not reach API. Check API URL and CORS.", "");
    console.error(err);
  }
});

apiUrl.addEventListener("change", checkApiHealth);
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

// Three.js lightweight neon scene
const canvas = document.getElementById("scene");
const renderer = new THREE.WebGLRenderer({ canvas, antialias: true, alpha: true });
renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2));
renderer.setSize(window.innerWidth, window.innerHeight);

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(60, window.innerWidth / window.innerHeight, 0.1, 100);
camera.position.z = 9;

const globe = new THREE.Mesh(
  new THREE.IcosahedronGeometry(1.6, 8),
  new THREE.MeshStandardMaterial({
    color: 0x7c3aed,
    emissive: 0x1e1b4b,
    metalness: 0.4,
    roughness: 0.2,
    wireframe: false,
  })
);
scene.add(globe);

const ring = new THREE.Mesh(
  new THREE.TorusGeometry(2.6, 0.06, 16, 200),
  new THREE.MeshBasicMaterial({ color: 0x67e8f9 })
);
ring.rotation.x = 1.1;
scene.add(ring);

const pointLight = new THREE.PointLight(0x67e8f9, 1.8, 40);
pointLight.position.set(3, 2, 6);
scene.add(pointLight);
scene.add(new THREE.AmbientLight(0xffffff, 0.35));

const stars = [];
for (let i = 0; i < 90; i += 1) {
  const star = new THREE.Mesh(
    new THREE.SphereGeometry(0.025, 8, 8),
    new THREE.MeshBasicMaterial({ color: 0xffffff })
  );
  star.position.set((Math.random() - 0.5) * 22, (Math.random() - 0.5) * 12, -Math.random() * 12);
  stars.push(star);
  scene.add(star);
}

function animate() {
  globe.rotation.y += 0.0045;
  globe.rotation.x += 0.0015;
  ring.rotation.z += 0.006;
  stars.forEach((s, idx) => {
    s.position.y += Math.sin(Date.now() * 0.0003 + idx) * 0.00045;
  });
  renderer.render(scene, camera);
  requestAnimationFrame(animate);
}

window.addEventListener("resize", () => {
  camera.aspect = window.innerWidth / window.innerHeight;
  camera.updateProjectionMatrix();
  renderer.setSize(window.innerWidth, window.innerHeight);
});

renderHistory();
updateApiLabel();
checkApiHealth();
animate();
