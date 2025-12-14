async function loadPatients() {
  const list = document.getElementById("patientList");
  list.innerHTML = "";

  try {
    const patients = await apiGet("/patients");
    patients.forEach(p => {
      const li = document.createElement("li");
      li.textContent = `${p.fullName} (${p.phone || "-"})`;
      list.appendChild(li);
    });
  } catch (e) {
    alert("Failed to load patients");
  }
}

document.getElementById("patientForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const body = {
    tenantId: document.getElementById("tenantId").value,
    fullName: document.getElementById("fullName").value,
    phone: document.getElementById("phone").value,
    email: document.getElementById("email").value
  };

  try {
    await apiPost("/patients", body);
    loadPatients();
  } catch (e) {
    alert("Failed to create patient");
  }
});

loadPatients();

