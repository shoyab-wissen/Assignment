<html>
<head>
    <title>Register</title>
</head>
<body>
    <h1>Register</h1>
    <form action="register-request" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
        <input type="submit" value="Register">
    </form>
    <p>Already have an account? <a href="login">Login</a></p>
</body>
</html>