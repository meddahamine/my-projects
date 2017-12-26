<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Artiste</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<script type="text/javascript">
		function confirmation(id){
			var conf = confirm("Êtes vous sur de vouloir supprimer ?");
			if(conf==true)
				document.location="artistelist.php?action=supp&id="+id;
		}
	</script>
</head>
<body>
<div>
	<form action="saveArtiste.php" method="post">
		<table>
			<tr>
				<td>ID:</td>
				<td>${artiste.id}<input type="hidden" name="id" value="${artiste.id}"></td>
			</tr>
			<tr>
				<td>Nom:</td>
				<td><input type="text" name="nom" value="${artiste.nom}"></td>
			</tr>
			<tr>
				<td>
				<c:if test="${artiste==null}">
					<input type="submit" name="action" value="Save">
				</c:if>
				<c:if test="${artiste!=null}">
					<input type="submit" name="action" value="Update">
				</c:if>
				</td>
			</tr>
		</table>
	</form>
</div>
<div>
	<table class="table1">
		<tr>
			<th>ID</th>
			<th>Nom</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${artistes}" var="ar">
			<tr>
				<td>${ar.id}</td>
				<td>${ar.nom}</td>
				<td>
					<a href="javascript:confirmation('${ar.id}')">Supprimer</a>
					<a href="artistelist.php?action=edit&id=${ar.id}">Modifier</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>