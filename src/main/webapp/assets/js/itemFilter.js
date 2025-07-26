    const searchInput = document.getElementById("itemSearchInput");
    const cards = document.querySelectorAll(".item-card-wrapper");

    searchInput.addEventListener("input", function () {
    const query = this.value.toLowerCase();
    cards.forEach(card => {
    const text = card.textContent.toLowerCase();
    card.style.display = text.includes(query) ? "block" : "none";
    });
});