<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Titre Musical</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<script type="text/javascript">
		function confirmation(id){
			var conf = confirm("Êtes vous sur de vouloir supprimer ?");
			if(conf==true)
				document.location="titremusicallist.php?action=supp&id="+id;
		}
	</script>
</head>
<body>
<div>
	<form action="saveTitreMusical.php" method="post">
		<table>
			<tr>
				<td>ID:</td>
				<td>${titreMusical.id}<input type="hidden" name="id" value="${titreMusical.id}"></td>
			</tr>
			<tr>
				<td>Titre:</td>
				<td><input type="text" name="titre" value="${titreMusical.titre}"></td>
			</tr>
			<tr>
				<td> select Type Support:</td>
				<td>
				<select id="sel" name="sel1">
						<option value="0">----Select----</option>
						<c:forEach items="${typeSupports}" var="ts">
							    <option value="${ts.id}">${ts.type}</option>
							</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td> select Album:</td>
				<td>
				<select id="sel" name="sel2">
						<option value="0">----Select----</option>
						<c:forEach items="${albums}" var="al">
							    <option value="${al.id}">${al.nom}</option>
							</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
				<c:if test="${titreMusical==null}">
					<input type="submit" name="action" value="Save">
				</c:if>
				<c:if test="${titreMusical!=null}">
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
			<th>Titre</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${titreMusicals}" var="tm">
			<tr>
				<td>${tm.id}</td>
				<td>${tm.titre}</td>
				<td>
					<a href="javascript:confirmation('${tm.id}')">Supprimer</a>
					<a href="titremusicallist.php?action=edit&id=${tm.id}">Modifier</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>