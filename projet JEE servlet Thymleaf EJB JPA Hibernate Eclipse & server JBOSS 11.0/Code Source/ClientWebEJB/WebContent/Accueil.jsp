<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>

</head>
<body>

	    <h1>liste des options :</h1>
	
	    <form action="accueil" method="post">
			<table>
			<tr>
				<td>
					<h3>Gerer les paniers :</h3>
					<input type="submit" name="action" value="gererPanier">
				</td>
			</tr>
			<tr>
				<td>
				<h3>Gerer les articles :</h3>
					<input type="submit" name="action" value="gererArticle">
				</td>
			</tr>
			<tr>
				<td>
				<h3>Deconnexion :</h3>
					<input type="submit" name="action" value="Exit">
				</td>
			</tr>
			</table>
		</form>

</body>
</html>
