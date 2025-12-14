const API_BASE = "http://localhost:8080/api";

async function apiGet(path) {
  const res = await fetch(API_BASE + path);
  if (!res.ok) {
    throw new Error("API GET failed");
  }
  return res.json();
}

async function apiPost(path, body) {
  const res = await fetch(API_BASE + path, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(body)
  });

  if (!res.ok) {
    throw new Error("API POST failed");
  }

  return res.json();
}

