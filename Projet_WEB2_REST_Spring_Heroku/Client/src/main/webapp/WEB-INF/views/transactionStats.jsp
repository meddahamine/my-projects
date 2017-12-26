<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@ page import="org.w3c.dom.Document" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEPA Client | Statistiques Transactions</title>
    </head>
    <body>
    	<jsp:include page="menu.jsp"/>
    	<div align="center" style="font-weight: bold;">
	    <% Document doc = (Document) request.getAttribute("statistiques"); 
	    			%> <br> <%out.print("Nombre de Transactions : " + doc.getElementsByTagName("NombreTransactions").item(0).getTextContent());
	    			%> <br> <%out.print("Montant Total : " + doc.getElementsByTagName("MontantTotal").item(0).getTextContent());
	    	%>
	    </div>
    </body>
</html>
