<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SGMA | Inscription Fournisseur</title>
</head>
<body>
	<a href="${sessionScope.baseURL}"><button style="float:left;">Home</button></a>
    <div style="color: green">${inscriptionSuccess}</div>
    <div style="color: blue">User name : ${userName}</div>
    <div style="color: blue">Passeword : ${passeWord}</div>
</body>
</html>