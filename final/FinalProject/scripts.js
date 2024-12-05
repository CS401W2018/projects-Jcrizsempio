document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent the default form submission

        // Collect form inputs into an object
        const formData = {
            name: document.getElementById("name").value,
            email: document.getElementById("email").value,
            movieChoice: document.getElementById("movieChoice") ? document.getElementById("movieChoice").value : null, // Optional
            player: document.getElementById("player").value,
            comments: document.getElementById("comments").value,
            agreeTerms: document.querySelector('input[name="agreeTerms"]').checked,
        };

        // Validate form data (example: name and email required)
        if (!formData.name || !formData.email || !formData.comments) {
            alert("Please fill out all required fields!");
            return;
        }

        // Log the form data to the console (you can replace this with an actual AJAX call to save it on the server)
        console.log("Form Data:", formData);

        // Optionally, display a success message or send it via AJAX (e.g., to a server)
        alert("Thank you for contacting us! We’ll get back to you soon.");

        // After submission, reset the form
        form.reset();
    });
});
