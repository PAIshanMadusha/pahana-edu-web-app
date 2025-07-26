document.addEventListener('DOMContentLoaded', () => {
    const toggleButton = document.getElementById('sidebarToggle');

    if (toggleButton) {
        // Restore sidebar state on load
        if (localStorage.getItem('sidebarExpanded') === 'true') {
            document.body.classList.add('sidebar-expanded');
        }

        // Toggle sidebar
        toggleButton.addEventListener('click', () => {
            document.body.classList.toggle('sidebar-expanded');
            const isExpanded = document.body.classList.contains('sidebar-expanded');
            localStorage.setItem('sidebarExpanded', isExpanded.toString());
            console.log('Sidebar toggled, now expanded:', isExpanded);
        });
    }
});