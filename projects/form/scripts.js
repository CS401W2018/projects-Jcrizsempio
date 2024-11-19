document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent the default form submission

        // Collect form inputs into an object
        const formData = {
            firstName: document.getElementById("firstName").value,
            lastName: document.getElementById("lastName").value,
            movieChoice: document.getElementById("movieChoice").value,
            goOut: document.querySelector('input[name="goOut"]:checked')?.value,
            password: document.getElementById("password").value,
            suggestion: document.getElementById("suggestion").value,
            preferredDate: document.getElementById("date").value,
            preferredTime: document.getElementById("time").value,
            email: document.getElementById("email").value,
            agreeTerms: document.querySelector('input[name="agreeTerms"]').checked,
        };

        // Validate inputs
        const errors = [];
        if (!formData.firstName) errors.push("First name is required.");
        if (!formData.lastName) errors.push("Last name is required.");
        if (!formData.movieChoice) errors.push("Please select a movie.");
        if (!formData.password || formData.password.length < 8) {
            errors.push("Password must be at least 8 characters long.");
        }

        if (errors.length > 0) {
            alert(errors.join("\n"));
            return;
        }

        console.log("Form Data:", formData);

        // Send data via AJAX
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "response.json", true); // Use "GET" for GitHub Pages
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.onload = function () {
            if (xhr.status === 200) {
                const response = JSON.parse(xhr.responseText);
                alert(response.message);
                form.reset(); // Reset the form
            } else {
                alert("There was an error processing your form. Please try again.");
            }
        };
        xhr.send(JSON.stringify(formData));
    });
});
