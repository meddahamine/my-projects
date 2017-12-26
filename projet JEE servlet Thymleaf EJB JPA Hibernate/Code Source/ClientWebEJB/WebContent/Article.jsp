<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste Article</title>
<script type="text/javascript">
		function confirmation(id){
			var conf = confirm("Êtes vous sur de vouloir supprimer ? id = "+id);
			
			if(conf==true)
				document.location="article?action=Supp&id="+id;
		}
	</script>
</head>
<body>
	<form action="article" method="post">
	<table>
			<tr>
				<td>ID:</td>
				<td>${article.id}<input type="hidden" name="id" value="${article.id}"></td>
			</tr>
			<tr>
				<td>Prix :</td>
				<td><input type="text" name="prix" value="${article.prix}"></td>
			</tr>
			<tr>
				<td>libellé :</td>
				<td><input type="text" name="libelle" value="${article.libelle}"></td>
			</tr>
		
		<tr>
				<td>
				<c:if test="${article==null}">
					<input type="submit" name="action" value="Save">
				</c:if>
				<c:if test="${article!=null}">
					<input type="submit" name="action" value="Update">
				</c:if>
				</td>
			</tr>
		</table>
	</form>
	<table border="1" width="80%">
		<tr>
			<th>ID</th><th>Prix</th><th>Libellé</th><th>Actions</th>
		</tr>
		<c:forEach items="${Articles}" var="a">
			<tr>
				<td>${a.id}</td>
				<td>${a.prix}</td>
				<td>${a.libelle}</td>
				<td>
					<a href="javascript:confirmation('${a.id}')">Supprimer</a>
					<a href="article?action=Edit&id=${a.id}">Modifier</a>
				</td>
			</tr>
		</c:forEach>
	
	</table>
</body>
</html>