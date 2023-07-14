userName();
function userName() {
    const userName = document.getElementById("userName");
    const url = `project/user/getUserName`;
     fetch(url)
        .then(response => response.text())
        .then(data => {
          userName.innerText = data;
        })
        .catch(error => {
          console.log(error);
        });
}