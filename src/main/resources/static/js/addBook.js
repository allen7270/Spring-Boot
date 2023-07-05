function addBook() {
    var book = document.getElementById("book").value;
    var author = document.getElementById("author").value;

    var data = {
        book: book,
        author: author,
    };

    var jsonString = JSON.stringify(data);

    var xhr = new XMLHttpRequest();
    var url = "project/a1";
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            console.log(response);
        }
    };
    xhr.send(jsonString);
}