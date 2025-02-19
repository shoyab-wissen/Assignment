<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome <%= session.getAttribute("user") %> </h1>
    <p>Click <a href="logout">here</a> to logout.</p>
</body>
</html>