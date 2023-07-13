function addBook() {
    var book = document.getElementById("book").value;
    var author = document.getElementById("author").value;
    var price = document.getElementById("price").value;
    var count = document.getElementById("count").value;
    var language = document.getElementById("language").value;
    var content = document.getElementById("content").value;

    var data = {
        book: book,
        author: author,
        price: price,
        count: count,
        language: language,
        content: content
    };

    var jsonString = JSON.stringify(data);

    var xhr = new XMLHttpRequest();
    var url = "project/book";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 ) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                console.log(response);
                showResultMessage("書籍新增成功！", "success");
            } else {
                showResultMessage("書籍新增失敗！", "error");
            }
        }
    };
    xhr.send(jsonString);

    // 清空欄位
    // var inputs = document.querySelectorAll('input[type="text"]');
    // inputs.forEach(function(input) {
    //     input.value = '';
    // });
}

function showResultMessage(message, type) {
    var modalOverlay = document.getElementById("modalOverlay");
    var modalMessage = document.getElementById("modalMessage");
    modalMessage.innerHTML = message;
    modalMessage.classList.remove("success", "error");
    modalMessage.classList.add(type);
    modalOverlay.style.display = "flex";
}

function closeModal() {
    var modalOverlay = document.getElementById("modalOverlay");
    modalOverlay.style.display = "none";
}