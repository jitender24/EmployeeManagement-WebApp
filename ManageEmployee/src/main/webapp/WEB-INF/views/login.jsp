<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
    <p><font color="red">${errorMessage}</font></p>
    <form action="${pageContext.request.contextPath}/login" method="POST">
        Name : <input name="name" type="text" placeholder="Type your name"/>
        <br></br>
        Password : <input name="password" type="password" placeholeder="Your name"/> 
        <input type="submit" value="Login" />
    </form>
</body>
</html> 