<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Album Registration</title>
	<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
		<div class="well lead">Titre Musical Registration Form</div>
	 	<form:form method="POST" modelAttribute="titreMusical" class="form-horizontal">
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="titre">Nom</label>
					<div class="col-md-7">
						<form:input type="text" path="titre" id="titre" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="titre" class="help-inline"/>
						</div>
						<select id="sel" name="sel" path="album">
						<option value="0">----Select----</option>
						<c:forEach items="${album}" var="al">
							    <option value="${al.id}">${al.nom}</option>
							</c:forEach>
					</select>
					<select id="sel1" name="sel1" path="typeSupport">
						<option value="0">----Select----</option>
						<c:forEach items="${typeSupport}" var="al">
							    <option value="${al.id}">${al.type}</option>
							</c:forEach>
					</select>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/listTitreMusical' />">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>