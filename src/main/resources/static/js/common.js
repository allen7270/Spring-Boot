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

const logoutButton = document.getElementById('logoutButton');
logoutButton.addEventListener('click', function() {
    const form = document.createElement('form');
    form.method = 'POST';
    form.action = '/logout';
    document.body.appendChild(form);
    form.submit();
});