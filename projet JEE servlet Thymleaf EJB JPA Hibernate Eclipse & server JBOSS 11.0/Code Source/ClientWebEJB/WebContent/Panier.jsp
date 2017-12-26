<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Liste Panier</title>
	<script type="text/javascript">
			function confirmation_panier(id){
				var conf = confirm("Êtes vous sur de vouloir supprimer ? id = "+id);
				
				if(conf==true)
					document.location="panier?action=Supp&id="+id;
			}
			function confirmation_article(ida,idp){
				var conf = confirm("Êtes vous sur de vouloir supprimer ? id = "+ida);
				
				if(conf==true)
					document.location="panier?action=UPDATE_SUPP&ida="+ida+"&idp="+idp;
				
			}
	</script>
</head>
<body>
<form action="panier" method="post">
	<table>
			<tr>
				<td>ID:</td>
				<td>${panier.id_panier}<input type="hidden" name="id" value="${panier.id_panier}"></td>
			</tr>
			<tr>
				<td>NOM :</td>
				<td><input type="text" name="nom" value="${panier.nom_panier}"></td>
			</tr>			
		
			<tr>
				<td>
				<c:if test="${panier==null}">
					<input type="submit" name="action" value="Save">
				</c:if>
				<c:if test="${panier!=null}">
					<input type="submit" name="action" value="Update">
				</c:if>
				</td>
			</tr>
		</table>
	</form>
	
	<table border="1" width="80%">
		<tr>
			<th>ID</th><th>Nom</th><th>Actions</th>
		</tr>
		<c:forEach items="${Paniers}" var="p">
			<tr>
				<td>${p.id_panier}</td>
				<td>${p.nom_panier}</td>
				<td>
					<a href="javascript:confirmation_panier('${p.id_panier}')">Supprimer</a>
				</td>
				<td>
					<a href="panier?action=Vider&idp=${p.id_panier}">Vider Panier</a>
				</td>
				<td>
					<a href="panier?action=Edit_add&idp=${p.id_panier}">Add Articles</a>
				</td>
				<td>
					<a href="panier?action=Edit_supp&idp=${p.id_panier}">Contenu panier</a>
				</td>
				<td>
					<a href="panier?action=GET_TOTAL&idp=${p.id_panier}">prix total</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<c:if test="${Articles!=null}">
		<table border="1" width="80%">
		<tr>
			<th>ID</th><th>Prix</th><th>Libellé</th><th>Action</th>
		</tr>
			<c:forEach items="${Articles}" var="a">
				<tr>
					<td>${a.id}</td>
					<td>${a.prix}</td>
					<td>${a.libelle}</td>
					<td>
						<a href="panier?action=UPDATE_ADD&ida=${a.id}&idp=${idp}">Ajouter</a>
					</td>
				</tr>
			</c:forEach>
		</table>		
	</c:if>
	
	<c:if test="${ArticlesIn!=null}">
		<table border="1" width="80%">
		<tr>
			<th>ID</th><th>Prix</th><th>Libellé</th><th>Action</th>
		</tr>
			<c:forEach items="${ArticlesIn}" var="ai">
				<tr>
					<td>${ai.id}</td>
					<td>${ai.prix}</td>
					<td>${ai.libelle}</td>
					<td>
						<a href="javascript:confirmation_article('${ai.id}','${idp}')">Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</table>		
	</c:if>
	
	<c:if test="${CleanPanier.isEmpty()}">
		<h2>Le panier est vide</h2>	
	</c:if>
	
	<c:if test="${id_panier!=null}">
		<h4>Le prix total des articles se trouvant dans le panier ${id_panier} est : ${PrixTotal} Euros</h4>
	</c:if>
</body>
</html>