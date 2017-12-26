<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Type Support</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<script type="text/javascript">
		function confirmation(id){
			var conf = confirm("Êtes vous sur de vouloir supprimer ?");
			if(conf==true)
				document.location="typesupportlist.php?action=supp&id="+id;
		}
	</script>
</head>
<body>
<div>
	<form action="saveTypeSupport.php" method="post">
		<table>
			<tr>
				<td>ID:</td>
				<td>${typeSupport.id}<input type="hidden" name="id" value="${typeSupport.id}"></td>
			</tr>
			<tr>
				<td>Type:</td>
				<td><input type="text" name="type" value="${typeSupport.type}"></td>
			</tr>
			<tr>
				<td>
				<c:if test="${typeSupport==null}">
					<input type="submit" name="action" value="Save">
				</c:if>
				<c:if test="${typeSupport!=null}">
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
			<th>Type</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${typeSupports}" var="ts">
			<tr>
				<td>${ts.id}</td>
				<td>${ts.type}</td>
				<td>
					<a href="javascript:confirmation('${ts.id}')">Supprimer</a>
					<a href="typesupportlist.php?action=edit&id=${ts.id}">Modifier</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>