function addBook() {
    const book = document.getElementById("book").value;
    const author = document.getElementById("author").value;
    const price = document.getElementById("price").value;
    const count = document.getElementById("count").value;
    const language = document.getElementById("language").value;
    const content = document.getElementById("content").value;

    const data = {
        book: book,
        author: author,
        price: price,
        count: count,
        language: language,
        content: content
    };

    const jsonString = JSON.stringify(data);

    const xhr = new XMLHttpRequest();
    const url = "project/book";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 ) {
            if (xhr.status === 200) {
                showResultMessage("書籍新增成功！", "success");
            } else {
                showResultMessage("書籍新增失敗！", "error");
            }
        }
    };
    xhr.send(jsonString);

    // 清空欄位
    const inputs = document.querySelectorAll('input[type="text"]');
    inputs.forEach(function(input) {
        input.value = '';
    });
}