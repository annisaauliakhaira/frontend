function addRequestHeader() {
    $(document).ajaxSend((evt, req) => {
        req.setRequestHeader('Authorization', 'Basic ' + getToken());
    });
}

function setToken(username, password) {
    localStorage.setItem('token', btoa(`${username}:${password}`));
}

function getToken() {
    return localStorage.getItem('token');
}



