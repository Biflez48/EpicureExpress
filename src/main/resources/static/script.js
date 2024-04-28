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

const loginBtn = document.getElementById('login-btn');
const CancelLogBtn = document.getElementById('cancel-log-btn');
const loginOverlay = document.getElementById('login-overlay');
const loginPopup = document.getElementById('login-popup');

loginBtn.addEventListener('click', () => {
    loginOverlay.style.display = 'block';
    loginPopup.style.display = 'block';
});

CancelLogBtn.addEventListener('click', () => {
    loginOverlay.style.display = 'none';
    loginPopup.style.display = 'none';
});

const regBtn = document.getElementById('reg-btn');
const loginRegBtn = document.getElementById('login-reg-btn');
const CancelRegBtn = document.getElementById('cancel-reg-btn');
const registerOverlay = document.getElementById('register-overlay');
const registerPopup = document.getElementById('register-popup');

regBtn.addEventListener('click', () => {
    loginOverlay.style.display = 'none';
    loginPopup.style.display = 'none';
    registerOverlay.style.display = 'block';
    registerPopup.style.display = 'block';
});

CancelRegBtn.addEventListener('click', () => {
    registerOverlay.style.display = 'none';
    registerPopup.style.display = 'none';
});

loginRegBtn.addEventListener('click', () => {
    registerOverlay.style.display = 'none';
    registerPopup.style.display = 'none';
    loginOverlay.style.display = 'block';
    loginPopup.style.display = 'block';
});


