<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="login-request" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <input type="submit" value="Login">
    </form>
    <p>Don't have an account? <a href="register">Register</a></p>
</body>
</html>