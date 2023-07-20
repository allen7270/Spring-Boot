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

const tableData = [];
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
                let uuid = item.uuid;

                const row = document.createElement("tr");
                const indexCell = document.createElement("td");
                const userNameCell = document.createElement("td");
                const roleNameCell = document.createElement("td");

                indexCell.textContent = index + 1;
                userNameCell.textContent = item.userName;
                // roleNameCell.textContent = item.roleName;

                const selectRole = document.createElement("select");
                const roleOptions = ["admin", "customer"];

                roleOptions.forEach(option => {
                    const roleOption = document.createElement("option");
                    roleOption.textContent = option;
                    selectRole.appendChild(roleOption);
                });

                selectRole.value = item.roleName;
                roleNameCell.appendChild(selectRole);

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

                selectRole.addEventListener("change", function () {
                    const rowData = {
                        uuid: uuid,
                        userName: item.userName,
                        roleName: selectRole.value
                    };
                    tableData.push(rowData);
                });

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

function editPersonnel() {
    const jsonData = {
        objects: tableData,
    };

    const jsonString = JSON.stringify(jsonData);

    const xhr = new XMLHttpRequest();
    const url = `project/role`;
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
}