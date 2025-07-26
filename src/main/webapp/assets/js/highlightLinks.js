document.addEventListener("DOMContentLoaded", () => {
    const current = window.location.pathname;
    const sidebarLinks = document.querySelectorAll("#sidebar .nav-link");

    sidebarLinks.forEach(link => {
        const href = link.getAttribute("href");

        if (!href) return;

        // Normalize href and current paths (remove trailing slashes)
        const normalizedHref = href.replace(/\/+$/, "");
        const normalizedCurrent = current.replace(/\/+$/, "");

        if (normalizedHref === normalizedCurrent) {
            link.classList.add("active");
        }
    });
});
