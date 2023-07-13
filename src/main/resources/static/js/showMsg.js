function showResultMessage(message, type) {
    const modalOverlay = document.getElementById("modalOverlay");
    const modalMessage = document.getElementById("modalMessage");
    modalMessage.innerHTML = message;
    modalMessage.classList.remove("success", "error");
    modalMessage.classList.add(type);
    modalOverlay.style.display = "flex";
}

function closeModal() {
    const modalOverlay = document.getElementById("modalOverlay");
    modalOverlay.style.display = "none";
}