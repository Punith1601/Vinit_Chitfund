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

// JavaScript function to send AJAX requests
function sendPaymentData(action) {
    let data = {};
    switch (action) {
        case 'deposit':
            data = {
                amount: document.getElementById('deposit-amount').value,
                accountNumber: document.getElementById('deposit-accountNumber').value,
                password: document.getElementById('deposit-password').value,
                action: 'deposit'
            };
            break;
        case 'withdraw':
            data = {
                amount: document.getElementById('withdraw-amount').value,
                accountNumber: document.getElementById('withdraw-accountNumber').value,
                password: document.getElementById('withdraw-password').value,
                action: 'withdraw'
            };
            break;
        case 'transfer':
            data = {
                amount: document.getElementById('transfer-amount').value,
                fromAccountNumber: document.getElementById('transfer-fromAccountNumber').value,
                toAccountNumber: document.getElementById('transfer-toAccountNumber').value,
                password: document.getElementById('transfer-password').value,
                action: 'transfer'
            };
            break;
        case 'schedule':
            data = {
                amount: document.getElementById('schedule-amount').value,
                accountNumber: document.getElementById('schedule-accountNumber').value,
                password: document.getElementById('schedule-password').value,
                action: 'schedule'
            };
            break;
        default:
            return;
    }

    console.log(data)

    // Create an XMLHttpRequest object
    var xhr = new XMLHttpRequest();

    // Configure it: POST-request for the URL /paymentServlet
    xhr.open("POST", "PaymentServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    // Handle the response
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // Update the page with the response
            alert(xhr.responseText);
        }
    };

    // Convert data object to URL-encoded string
    var encodedData = Object.keys(data).map(function (key) {
        return encodeURIComponent(key) + '=' + encodeURIComponent(data[key]);
    }).join('&');

    // Send the request with the data
    xhr.send(encodedData);

    document.querySelectorAll(".popup.active").forEach((popup) => {
        popup.classList.remove("active");
    });

}