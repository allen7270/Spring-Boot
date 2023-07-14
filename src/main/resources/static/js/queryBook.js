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
    const url = `project/book?curNum=${pageNumber}&size=${pageSize}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {

            const tableBody = document.querySelector("#tableData tbody");
            tableBody.innerHTML = ""; // 清空表格内容

            // table
            data.object.forEach((item, index) => {
                const row = document.createElement("tr");
                const indexCell = document.createElement("td");
                const iconCell = document.createElement("td");
                const authorCell = document.createElement("td");
                const bookCell = document.createElement("td");
                const priceCell = document.createElement("td");
                const countCell = document.createElement("td");

                const iconButton = document.createElement("a");
                iconButton.className = "icon-button";

                const icon = document.createElement("i");
                icon.className = "fas fa-search";
                iconButton.appendChild(icon);
                iconCell.appendChild(iconButton);

                indexCell.textContent = index + 1;
                authorCell.textContent = item.author;
                bookCell.textContent = item.book;
                priceCell.textContent = item.price;
                countCell.textContent = item.count;

                let uuid = item.uuid;

                row.appendChild(iconCell);
                row.appendChild(indexCell);
                row.appendChild(authorCell);
                row.appendChild(bookCell);
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