<%@ page language="java" contentType="text/html; 
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SpringMVC-Hibernate</title>
	<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
		  	<div class="panel-heading"><span class="lead"> Type Support List </span></div>
			<div class="well">
			 <a href="<c:url value='/listTypeSupport' />"> afficher la liste (Type Support) </a>
			</div>
			<div class="panel-heading"><span class="lead"> Album List </span></div>
			<div class="well">
			 <a href="<c:url value='/listAlbum' />"> afficher la liste (Album) </a>
			</div>
			<div class="panel-heading"><span class="lead"> Titre Musical List </span></div>
			<div class="well">
			 <a href="<c:url value='/listTitreMusical' />"> afficher la liste (Titre Musical) </a>
			</div>
			<div class="panel-heading"><span class="lead"> Artiste List </span></div>
			<div class="well">
			 <a href="<c:url value='/listArtiste' />"> afficher la liste (Artiste) </a>
			</div>
		</div>
			 		
		
		
	</div>
</body>

</html>