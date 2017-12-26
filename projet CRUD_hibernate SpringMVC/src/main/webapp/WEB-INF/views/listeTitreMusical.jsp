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
		
		<script>
            function filtre(){  
            	//alert("hello");
            	var e = document.getElementById("sel");
            	var value = e.options[e.selectedIndex].value;
            	//alert(value);
            	var s1 = document.getElementById("sel1");
            	var s2 = document.getElementById("sel2");
            	var s3 = document.getElementById("sel3");
            	if (value == 1){s1.style="display:true;";
				        		s2.style="display:none;";
				        		s3.style="display:none;";}
            	if (value == 2){s1.style="display:none;";
				        		s2.style="display:true;";
				        		s3.style="display:none;";}
            	if(value == 3){ s1.style="display:none;";
				        	    s2.style="display:none;";
				        		s3.style="display:true;";}
            }
        </script>
		
		<div class="panel panel-default">
		 	<form:form method="POST" modelAttribute="filtre" class="form-horizontal">
		 		<div class="row floatRight">
					<select id="sel" name="sel" onchange="filtre()">
						<option value="0">----Select----</option>
						<option value="1">Titre</option>
						<option value="2">Album</option>
						<option value="3">type Support</option>
					</select>
					<select id="sel1" name="sel1" style="display:none;" path="titreMusical">
						<option value="0">----Select----</option>
						<c:forEach items="${titreMusical}" var="tm">
							    <option value="${tm.id}">${tm.titre}</option>
							</c:forEach>
					</select>
					<select id="sel2" name="sel2" style="display:none;" path="album">
						<option value="0">----Select----</option>
						<c:forEach items="${album}" var="al">
							    <option value="${al.id}">${al.nom}</option>
							</c:forEach>
					</select>
					<select id="sel3" name="sel3" style="display:none;" path="typeSupport">
						<option value="0">----Select----</option>
						<c:forEach items="${typeSupport}" var="ts" >
							    <option value="${ts.id}">${ts.type}</option>
							</c:forEach>
					</select>
					<div class="form-actions">
						<input type="submit" value="Filter" class="btn btn-primary btn-sm"/>
					</div>
				</div>
			</form:form>
		 </div>
		
		
		
		
		  	<div class="panel-heading"><span class="lead"> Titre Musical List </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Id</th>
        				<th>Titre</th>
					</tr>
		    	</thead>
	    		<tbody>
					<c:forEach items="${titreMusical}" var="tm">
				        <tr>
				          	 <td>${tm.id}</td>
				         	 <td>${tm.titre}</td>
							 <td><a href="<c:url value='/edit-titreMusical/${tm.id}' />" class="btn btn-success custom-width">Edit</a></td>
							 <td><a href="<c:url value='/delete-titreMusical/${tm.id}' />" class="btn btn-danger custom-width">Delete</a></td>
				        </tr>
				    </c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<div class="well">
			 <a href="<c:url value='/newTitreMusical' />">Add New Titre Musical</a>
		</div>
	</div>
</body>

</html>