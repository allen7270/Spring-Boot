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

                iconCell.innerHTML = '<i class="fas fa-search"></i>';
                indexCell.textContent = index + 1;
                authorCell.textContent = item.author;
                bookCell.textContent = item.book;

                row.appendChild(iconCell);
                row.appendChild(indexCell);
                row.appendChild(authorCell);
                row.appendChild(bookCell);

                tableBody.appendChild(row);

                const pageData = data["page"];
                const totalPages = pageData.totalPages;
                addButtons(totalPages);

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