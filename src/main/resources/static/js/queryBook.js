let pageSize = 5; // 初始頁面5筆
let page = 1; // 初始第一頁

function submitForm(value) {
    pageSize = value;
    fetchData(page, pageSize);
}

function submitPage(value) {
    page = value;
    fetchData(page, pageSize);
}

function fetchData(pageNumber, pageSize) {

    const searchBook = document.getElementById("searchBook").value;
    const searchAuthor = document.getElementById("searchAuthor").value;
    const searchPrice = document.getElementById("searchPrice").value;
    const searchCount = document.getElementById("searchCount").value;
    const searchLanguage = document.getElementById("searchLanguage").value;
    const searchContent = document.getElementById("searchContent").value;

    const url = `project/book?curNum=${pageNumber}&size=${pageSize}&book=${searchBook}&author=${searchAuthor}&price=${searchPrice}&count=${searchCount}&language=${searchLanguage}&content=${searchContent}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {

            const tableBody = document.querySelector("#tableData tbody");
            tableBody.innerHTML = ""; // 清空表格内容

            // table
            data.object.forEach((item, index) => {
                const row = document.createElement("tr");
                const iconCheckboxCell = document.createElement("td");
                const indexCell = document.createElement("td");
                const bookCell = document.createElement("td");
                const authorCell = document.createElement("td");
                const priceCell = document.createElement("td");
                const countCell = document.createElement("td");

                iconCheckboxCell.className = "icon-checkbox-cell";

                const checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkbox.id = "deleteCheckbox";
                iconCheckboxCell.appendChild(checkbox);

                const iconButton = document.createElement("a");
                iconButton.className = "icon-button";
                const icon = document.createElement("i");
                icon.className = "fas fa-search";
                iconButton.appendChild(icon);
                iconCheckboxCell.appendChild(iconButton);

                indexCell.textContent = index + 1;
                bookCell.textContent = item.book;
                authorCell.textContent = item.author;
                priceCell.textContent = item.price;
                countCell.textContent = item.count;

                let uuid = item.uuid;

                row.appendChild(iconCheckboxCell);
                row.appendChild(indexCell);
                row.appendChild(bookCell);
                row.appendChild(authorCell);
                row.appendChild(priceCell);
                row.appendChild(countCell);

                tableBody.appendChild(row);

                const pageData = data["page"];
                const totalPages = pageData.totalPages;
                addButtons(totalPages);

                iconButton.onclick = function () {
                    openPopup(uuid);
                };

            });

            // 清空欄位
            const inputs = document.querySelectorAll('input[type="text"]');
            inputs.forEach(function(input) {
                input.value = '';
            });

        })
        .catch(error => {
            console.error(error);
        });
}

// init
fetchData(page, pageSize);

function addButtons(totalPages) {
    const paginationContainer = document.getElementById("paginationButtons");
    paginationContainer.innerHTML = ""; // 清空容器内容

    for (let i = 1; i <= totalPages; i++) {
        const button = document.createElement("button");
        button.textContent = i;
        button.className = "button";
        button.onclick = function () {
            submitPage(i);
        };
        paginationContainer.appendChild(button);
    }
}

function openPopup(uuid) {
    const popupContainer = document.getElementById("popupContainer");
    const popupContent = document.getElementById("popupContent");
    const closeButton = document.getElementById("closeButton");

    const url = `project/book/${uuid}`;

    let book, author, price, count, language, content;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const bookData = data.object;
            book = bookData.book;
            author = bookData.author;
            price = bookData.price;
            count = bookData.count;
            language = bookData.language;
            content = bookData.content;
            document.getElementById("book").innerText = book;
            document.getElementById("author").innerText = author;
            document.getElementById("price").innerText = price;
            document.getElementById("count").innerText = count;
            document.getElementById("language").innerText = language;
            document.getElementById("content").innerText = content;
        });

    // 顯示彈出區域
    popupContainer.style.display = "block";

    // 處理關閉按鈕的點擊事件
    closeButton.addEventListener("click", function() {
        // 隱藏彈出區域
        popupContainer.style.display = "none";
    });
}

function deleteBooks() {


}