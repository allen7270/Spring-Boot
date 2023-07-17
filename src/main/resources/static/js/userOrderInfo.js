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
    let checkedCount = 0;

    const searchBook = document.getElementById("searchBook").value;
    const searchAuthor = document.getElementById("searchAuthor").value;
    const searchPrice = document.getElementById("searchPrice").value;
    const searchCount = document.getElementById("searchCount").value;
    const searchLanguage = document.getElementById("searchLanguage").value;
    const searchContent = document.getElementById("searchContent").value;
    const deleteCount = document.getElementById("deleteCount");

    const url = `project/orders?curNum=${pageNumber}&size=${pageSize}&book=${searchBook}&author=${searchAuthor}&price=${searchPrice}&count=${searchCount}&language=${searchLanguage}&content=${searchContent}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {

            const tableBody = document.querySelector("#tableData tbody");
            tableBody.innerHTML = ""; // 清空表格内容

            // table
            data.object.forEach((item, index) => {
                let uuid = item.uuid;

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
                checkbox.id = "choiceCheckbox";
                checkbox.value = uuid + (index + 1);
                iconCheckboxCell.appendChild(checkbox);

                checkbox.addEventListener("change", function () {
                    if (this.checked) {
                        checkedCount++;
                    } else {
                        checkedCount--;
                    }
                    deleteCount.innerText = `${checkedCount}`;
                });


                const iconButton = document.createElement("a");
                iconButton.className = "icon-button";
                const icon = document.createElement("i");
                icon.className = "fas fa-search";
                iconButton.appendChild(icon);
                iconCheckboxCell.appendChild(iconButton);

                const editButton = document.createElement("a");
                editButton.className = "icon-button-edit";
                const editIcon = document.createElement("i");
                editIcon.className = "fas fa-pencil-alt";
                editButton.appendChild(editIcon);
                iconCheckboxCell.appendChild(editButton);

                indexCell.textContent = index + 1;
                bookCell.textContent = item.book;
                authorCell.textContent = item.author;
                priceCell.textContent = item.price;
                countCell.textContent = item.count;

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

                const totalElements = pageData.totalElements;
                const curNum = pageData.curNum;

                const totalPageData = document.getElementById('totalPageData');
                totalPageData.innerText = `| 共${totalElements}筆，第${curNum}頁，共${totalPages}頁`;

                iconButton.onclick = function () {
                    openPopupQuery(uuid);
                };

                editButton.onclick = function () {
                    openPopupEdit(uuid);
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

function openPopupQuery(uuid) {
    const popupContainer = document.getElementById("popupContainer");
    const popupContent = document.getElementById("popupContent");
    const closeButton = document.getElementById("closeButton");

    const url = `project/orders/${uuid}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const sendButton = document.getElementById("sendButton");
            if (sendButton) {
                sendButton.remove();
            }

            const bookData = data.object;
            const getByIdBook = document.getElementById("book");
            const getByIdAuthor = document.getElementById("author");
            const getByIdPrice = document.getElementById("price");
            const getByIdCount = document.getElementById("count");
            const getByIdLanguage = document.getElementById("language");
            const getByIdContent = document.getElementById("content");

            getByIdBook.value = bookData.book;
            getByIdAuthor.value = bookData.author;
            getByIdPrice.value = bookData.price;
            getByIdCount.value = bookData.count;
            getByIdLanguage.value = bookData.language;
            getByIdContent.value = bookData.content;

            [getByIdBook, getByIdAuthor, getByIdPrice, getByIdCount, getByIdLanguage, getByIdContent].forEach(element => {
                element.readOnly = true;
                element.classList.add("readonly-input");
            });
        });

    popupContainer.style.display = "block";

    closeButton.addEventListener("click", function() {
        popupContainer.style.display = "none";
    });
}

function openPopupEdit(uuid) {
    const popupContainer = document.getElementById("popupContainer");
    const popupContent = document.getElementById("popupContent");
    const closeButton = document.getElementById("closeButton");

    const url = `project/orders/${uuid}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const bookData = data.object;
            const getByIdBook = document.getElementById("book");
            const getByIdAuthor = document.getElementById("author");
            const getByIdPrice = document.getElementById("price");
            const getByIdCount = document.getElementById("count");
            const getByIdLanguage = document.getElementById("language");
            const getByIdContent = document.getElementById("content");

            getByIdBook.value = bookData.book;
            getByIdAuthor.value = bookData.author;
            getByIdPrice.value = bookData.price;
            getByIdCount.value = bookData.count;
            getByIdLanguage.value = bookData.language;
            getByIdContent.value = bookData.content;

            [getByIdCount].forEach(element => {
                element.readOnly = false;
                element.classList.remove("readonly-input");
            });

            let sendButton = document.getElementById("sendButton");

            if (!sendButton) {
                sendButton = document.createElement("button");
                sendButton.textContent = "修改";
                sendButton.id = "sendButton";
                popupContent.appendChild(sendButton);
            }

            sendButton.addEventListener("click", function() {
                const editData = {
                    book: getByIdBook.value,
                    author: getByIdAuthor.value,
                    price: getByIdPrice.value,
                    count: getByIdCount.value,
                    language: getByIdLanguage.value,
                    content: getByIdContent.value
                };
                const jsonString = JSON.stringify(editData);

                const xhr = new XMLHttpRequest();
                const url = `project/orders/${uuid}`;
                xhr.open("PUT", url, true);
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 ) {
                        if (xhr.status === 200) {
                            showResultMessage("修改成功！", "success");
                        } else {
                            showResultMessage("修改失敗！", "error");
                        }
                    }
                };
                xhr.send(jsonString);
            });
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
    let rowNum, uuid;
    const data = [];
    const checkboxes = document.querySelectorAll("#choiceCheckbox");

    checkboxes.forEach(function (checkbox) {
        if (checkbox.checked) {
            let value = checkbox.value;
            rowNum = value.substring(36)
            uuid = value.substring(0, 36);
            data.push({
                rowNum: rowNum,
                uuid: uuid,
            });
        }
    });

    const jsonData = {
        objects: data,
    };

    const jsonString = JSON.stringify(jsonData);

    const xhr = new XMLHttpRequest();
    const url = "project/orders";
    xhr.open("DELETE", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 ) {
            if (xhr.status === 200) {
                showResultMessage("訂單刪除成功！", "success");
            } else {
                showResultMessage("訂單刪除失敗！", "error");
            }
        }
    };
    xhr.send(jsonString);
}

function closeModal() {
    const modalOverlay = document.getElementById("modalOverlay");
    modalOverlay.style.display = "none";
    location.reload();
}