fetch("project/a1?curNum=1&size=10")
    .then(response => response.json())
    .then(data => {
        const tableBody = document.querySelector("#tableData tbody");

        // 遍历数据数组，并为每个对象创建表格行
        data.object.forEach((item, index) => {
            const row = document.createElement("tr");
            const indexCell = document.createElement("td");
            const authorCell = document.createElement("td");
            const bookCell = document.createElement("td");

            indexCell.textContent = index + 1;
            authorCell.textContent = item.author;
            bookCell.textContent = item.book;

            row.appendChild(indexCell);
            row.appendChild(authorCell);
            row.appendChild(bookCell);

            tableBody.appendChild(row);
        });
    })
    .catch(error => {
        console.error(error);
    });