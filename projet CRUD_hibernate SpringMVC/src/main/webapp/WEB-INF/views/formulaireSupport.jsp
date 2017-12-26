<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Support Type Registration</title>
	<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
		<div class="well lead">Type Support Registration Form</div>
	 	<form:form method="POST" modelAttribute="typeSupport" class="form-horizontal">
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="type">Type</label>
					<div class="col-md-7">
						<form:input type="text" path="type" id="type" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="type" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/listTypeSupport' />">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>