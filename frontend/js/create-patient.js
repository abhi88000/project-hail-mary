document.getElementById("patientForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const message = document.getElementById("message");
  message.innerText = "";

  const body = {
    tenantId: document.getElementById("tenantId").value,
    fullName: document.getElementById("fullName").value,
    phone: document.getElementById("phone").value,
    email: document.getElementById("email").value
  };

  try {
    await apiPost("/patients", body);
    message.innerText = "Patient created successfully";
    message.style.color = "green";
  } catch (err) {
    message.innerText = err;   // 👈 show backend message
    message.style.color = "red";
  }
});
