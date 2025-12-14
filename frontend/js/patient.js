async function loadSlots() {
  const slots = await apiGet("/slots/available");
  const list = document.getElementById("slotList");
  list.innerHTML = "";

  slots.forEach(s => {
    const li = document.createElement("li");
    li.textContent = new Date(s.startTime).toLocaleString();

    const btn = document.createElement("button");
    btn.textContent = "Book";
    btn.onclick = () => bookSlot(s.id);

    li.appendChild(btn);
    list.appendChild(li);
  });
}

async function bookSlot(slotId) {
  await apiPost("/appointments", {
    slotId: slotId,
    patientId: "PATIENT_ID_HARDCODED_FOR_NOW"
  });
  alert("Appointment booked");
  loadSlots();
}

loadSlots();
