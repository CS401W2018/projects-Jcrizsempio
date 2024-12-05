<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Submission</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <nav>
            <a href="index.html">Home</a>
            <a href="history.html">History</a>
            <a href="strategies.html">Strategies</a>
            <a href="gear.html">Gear</a>
            <a href="moments.html">NBA Moments</a>
            <a href="teams.html">Famous Teams</a>
            <a href="contact.html">Contact</a>
        </nav>
    </header>
    <main>
        <div class="content">
            <h2>Form Submission Details</h2>
            <p><strong>Name:</strong> <?php echo htmlspecialchars($_POST['name']); ?></p>
            <p><strong>Email:</strong> <?php echo htmlspecialchars($_POST['email']); ?></p>
            <p><strong>G.O.A.T.:</strong> <?php echo htmlspecialchars($_POST['player']); ?></p>
            <p><strong>Comments:</strong> <?php echo nl2br(htmlspecialchars($_POST['comments'])); ?></p>
            <a href="contact.html">Go Back</a>
        </div>
    </main>
    <footer>
        <p>&copy; 2024 Jashreel Criz Sempio</p>
    </footer>
</body>
</html>
