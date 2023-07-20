function viewPersonnel() {
    const popupContainer = document.getElementById('popupPersonalContainer');
    const personnelTableBody = document.querySelector('#personnelTable tbody');
    const closeButton = document.getElementById("closePersonalButton");

    personnelTableBody.innerHTML = '';

    const url = `project/role?curNum=1&size=5`;

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

                // const pageData = data["page"];
                // const totalPages = pageData.totalPages;
                // addButtons(totalPages);
                //
                // const totalElements = pageData.totalElements;
                // const curNum = pageData.curNum;
                //
                // const totalPageData = document.getElementById('totalPageData');
                // totalPageData.innerText = `| 共${totalElements}筆，第${curNum}頁，共${totalPages}頁`;

            });
        });

    popupContainer.style.display = 'block';

    closeButton.addEventListener("click", function() {
        popupContainer.style.display = "none";
    });
}