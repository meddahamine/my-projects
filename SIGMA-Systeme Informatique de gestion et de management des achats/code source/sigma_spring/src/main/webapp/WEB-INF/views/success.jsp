<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>success</title>
</head>
<body>
	
	<c:forEach items="${fileNames }" var="fileName" >
		File <b>${fileName }</b> Uploaded successfully <br/>
	</c:forEach>

</body>
</html>