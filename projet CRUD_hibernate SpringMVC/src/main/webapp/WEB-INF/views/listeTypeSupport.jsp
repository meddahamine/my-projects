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
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Id</th>
        				<th>Type</th>
					</tr>
		    	</thead>
	    		<tbody>
					<c:forEach items="${typeSupport}" var="ts">
				        <tr>
				          	 <td>${ts.id}</td>
				         	 <td>${ts.type}</td>
							 <td><a href="<c:url value='/edit-typeSupport/${ts.id}' />" class="btn btn-success custom-width">Edit</a></td>
							 <td><a href="<c:url value='/delete-typeSupport/${ts.id}' />" class="btn btn-danger custom-width">Delete</a></td>
				        </tr>
				    </c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<div class="well">
			 <a href="<c:url value='/newTypeSupport' />">Add New Type Support</a>
		</div>
	</div>
</body>

</html>