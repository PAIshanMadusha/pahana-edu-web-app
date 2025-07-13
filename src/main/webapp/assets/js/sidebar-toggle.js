document.addEventListener('DOMContentLoaded', function () {
    const toggleBtn = document.getElementById('sidebarToggle');
    toggleBtn.addEventListener('click', function () {
        document.body.classList.toggle('sidebar-expanded');
    });
});
