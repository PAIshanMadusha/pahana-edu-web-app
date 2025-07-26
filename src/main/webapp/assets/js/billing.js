document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('itemSearch');
    if (searchInput) {
        searchInput.addEventListener('input', function () {
            const keyword = this.value.toLowerCase();
            const rows = document.querySelectorAll('#itemTable tbody tr');

            rows.forEach(row => {
                const itemName = row.querySelector('.item-name').textContent.toLowerCase();
                row.style.display = itemName.includes(keyword) ? '' : 'none';
            });
        });
    }

    $(document).ready(function () {
        const $dropdown = $('#accountNumber');
        if ($dropdown.length && $.fn.select2) {
            $dropdown.select2({
                placeholder: "Select customer by account number or email",
                width: '100%'
            });
        }
    });
});

