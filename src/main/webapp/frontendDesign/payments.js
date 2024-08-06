// Select all buttons with the class "open-popup"
document.querySelectorAll(".open-popup").forEach((button) => {
    button.addEventListener("click", function () {
        const popupType = this.getAttribute("data-popup");
        if (popupType) { // Check if popupType is not null or empty
            const popup = document.querySelector(`.popup.${popupType}`);
            if (popup) {
                popup.classList.add("active");
            } else {
                console.error(`Popup element with class '${popupType}' not found.`);
            }
        } else {
            console.error('Popup type is missing for this button.');
        }
    });
});

// Close the popup when the close button is clicked
document.querySelectorAll(".popup .close-btn").forEach((button) => {
    button.addEventListener("click", function () {
        const popup = this.closest(".popup");
        if (popup) {
            popup.classList.remove("active");
        } else {
            console.error('Popup element not found for the close button.');
        }
    });
});

// Close the popup when the "Escape" key is pressed
document.addEventListener("keydown", function (event) {
    if (event.key === "Escape") {
        document.querySelectorAll(".popup.active").forEach((popup) => {
            popup.classList.remove("active");
        });
    }
});