let menuContainer = document.getElementById('menu-container');
let hamburgerBtn = document.querySelector('.hamburger-btn');
hamburgerBtn.addEventListener('click', () => {
    menuContainer.classList.toggle('active');
});
document.addEventListener('click', (e) => {
    if (!menuContainer.contains(e.target)) {
        if(!hamburgerBtn.contains(e.target)){
            menuContainer.classList.remove('active');
        }
    }
});
function toggleMenu() {
    menuContainer.classList.toggle('active');
}
hamburgerBtn.addEventListener('click', toggleMenu);

const adminBtn = document.getElementById('admin-btn');
const loginBtn = document.getElementById('login-btn');
const loginOverlay = document.getElementById('login-overlay');
const loginPopup = document.getElementById('login-popup');
const regBtn = document.getElementById('reg-btn');
const registerOverlay = document.getElementById('register-overlay');
const registerPopup = document.getElementById('register-popup');
const cancelLogBtn = document.getElementById('cancel-log-btn');
const cancelRegBtn = document.getElementById('cancel-reg-btn');
const loginRegBtn = document.getElementById('login-reg-btn');

loginBtn.addEventListener('click', () => {
    loginOverlay.style.display = 'block';
    loginPopup.style.display = 'block';
});

regBtn.addEventListener('click', () => {
    loginOverlay.style.display = 'none';
    loginPopup.style.display = 'none';
    registerOverlay.style.display = 'block';
    registerPopup.style.display = 'block';
});

cancelLogBtn.addEventListener('click', closeLoginForm);
cancelRegBtn.addEventListener('click', closeRegForm);
loginRegBtn.addEventListener('click', () => {
    registerOverlay.style.display = 'none';
    registerPopup.style.display = 'none';
    loginOverlay.style.display = 'block';
    loginPopup.style.display = 'block';
});

function submitLoginForm() {
    const form = document.getElementById('log-form');
    const formData = new FormData(form);

    fetch('/loginconfirm', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            if (data.loggedIn) {
                window.location.reload();
            } else {
                handleError(form);
            }
        });
}

function submitRegForm() {
    const form = document.getElementById('reg-form');
    const formData = new FormData(form);

    fetch('/registerconfirm', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            if (data.registered) {
                window.location.reload();
            } else {
                handleError(form);
            }
        });
}

function closeLoginForm() {
    loginOverlay.style.display = 'none';
    loginPopup.style.display = 'none';
}

function closeRegForm() {
    registerOverlay.style.display = 'none';
    registerPopup.style.display = 'none';
}

function handleError(form) {
    const inputs = form.querySelectorAll('input');
    inputs.forEach(input => {
        if (!input.classList.contains('except-input')) {
            input.classList.add('except-input');
        }
    });
}

function removeErrorClass(form) {
    const inputs = form.querySelectorAll('input');
    inputs.forEach(input => {
        input.classList.remove('except-input');
    });
}

function submitLogout() {
    fetch('/logout', {
        method: 'POST'
    })
        .then(response => response.json())
        .then(data => {
            if (data.loggedOut) {
                window.location.reload();
            }
        });
}
