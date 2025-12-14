async function loadAppointments() {
  const appts = await apiGet("/appointments/upcoming");
  const list = document.getElementById("appointmentList");
  list.innerHTML = "";

  appts.forEach(a => {
    const li = document.createElement("li");
    li.textContent =
      new Date(a.appointmentTime).toLocaleString() +
      " - " + a.patientName;
    list.appendChild(li);
  });
}

async function createSlots() {
  await apiPost("/slots/generate", {
    days: 4
  });
  alert("Slots created");
}

loadAppointments();
