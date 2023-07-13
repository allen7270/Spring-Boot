function register() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    console.log(username)

    const data = {
        username: username,
        password: password,
    };

    const jsonString = JSON.stringify(data);

    const xhr = new XMLHttpRequest();
    const url = "project/user";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 ) {
            if (xhr.status === 200) {
                showResultMessage("註冊成功！", "success");
            } else {
                showResultMessage("註冊失敗！", "error");
            }
        }
    };
    xhr.send(jsonString);
}

function closeModal() {
    const modalOverlay = document.getElementById("modalOverlay");
    modalOverlay.style.display = "none";

    const newPageURL = "/login.html";
    window.location.href = newPageURL;
}