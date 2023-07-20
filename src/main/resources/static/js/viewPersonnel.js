let personalPageSize = 5; // 初始頁面5筆
let personalPage = 1; // 初始第一頁

function submitPersonalForm(value) {
    personalPageSize = value;
    viewPersonnel(personalPage, personalPageSize);
}

function submitPersonalPage(value) {
    personalPage = value;
    viewPersonnel(personalPage, personalPageSize);
}
function viewPersonnel() {
    const popupContainer = document.getElementById('popupPersonalContainer');
    const personnelTableBody = document.querySelector('#personnelTable tbody');
    const closeButton = document.getElementById("closePersonalButton");

    personnelTableBody.innerHTML = '';

    const url = `project/role?curNum=${personalPage}&size=${personalPageSize}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            data.object.forEach((item, index) => {
                const row = document.createElement("tr");
                const indexCell = document.createElement("td");
                const userNameCell = document.createElement("td");
                const roleNameCell = document.createElement("td");

                indexCell.textContent = index + 1;
                userNameCell.textContent = item.userName;
                roleNameCell.textContent = item.roleName;

                row.appendChild(indexCell);
                row.appendChild(userNameCell);
                row.appendChild(roleNameCell);

                personnelTableBody.appendChild(row);

                const pageData = data["page"];
                const totalPages = pageData.totalPages;
                addPersonalButtons(totalPages);

                const totalElements = pageData.totalElements;
                const curNum = pageData.curNum;

                const totalPagePersonalData = document.getElementById('totalPagePersonalData');
                totalPagePersonalData.innerText = `| 共${totalElements}筆，第${curNum}頁，共${totalPages}頁`;

            });
        });

    popupContainer.style.display = 'block';

    closeButton.addEventListener("click", function() {
        popupContainer.style.display = "none";
    });
}

function addPersonalButtons(totalPages) {
    const paginationContainer = document.getElementById("paginationPersonalButtons");
    paginationContainer.innerHTML = ""; // 清空容器内容

    for (let i = 1; i <= totalPages; i++) {
        const button = document.createElement("button");
        button.textContent = i;
        button.className = "button";
        button.onclick = function () {
            submitPersonalPage(i);
        };
        paginationContainer.appendChild(button);
    }
}