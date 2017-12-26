<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connexion</title>

</head>
<body>

	    <p >Connectez-vous :</p>
	
	    <form action="login" method="post">
			<table>
			<tr>
				<td>Email :</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>
					<input type="submit" name="action" value="Connexion">
				</td>
			</tr>
			</table>
		</form>
	
	<c:if test="${error!=null}">
		<p style="color:red;">${error}</p>
	</c:if>
	
	
</body>
</html>
