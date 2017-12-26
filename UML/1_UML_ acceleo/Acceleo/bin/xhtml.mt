<%
metamodel http://www.eclipse.org/emf/2002/Ecore
%>

<%script type="ecore.EClass" name="xhtml" file="<%name%>.xhtml"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Description de la classe</title>
</head>
<body>
	<h1>Description de la classe : <%name%></h1>
		<p>Liste des attributs :</p>
		<ul>
		<%for (eAllAttributes()) {%>
		<li><%eAttributeType().name%> <%name%></li>
		<%}%>
		</ul>
		
		<p>Liste des references :</p>
		<ul>
		<%for (eAllReferences()) {%>
		<li><%eReferenceType().name%> <%name%></li>
		<%}%>
		</ul>
		
		<p>Liste des operations :</p>
		<ul>
		<%for (eAllOperations()) {%>
		<li><%eType().instanceClassName%> <%name%></li>
		<%}%>
		</ul>
</body>
</html>
