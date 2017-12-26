<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin SIGMA | Test</title>
</head>
<body>
    <div style="color: green">${password}</div>
    <div style="color: green">Bonjour : ${userConnected.userName}</div>
    <a href="accueil"><button style="float:left;">Retour</button></a>
</body>
</html>