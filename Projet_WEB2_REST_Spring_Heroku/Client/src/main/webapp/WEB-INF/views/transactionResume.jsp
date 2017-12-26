<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@ page import="org.w3c.dom.NodeList" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="org.w3c.dom.Node" %>
<%@ page import="org.w3c.dom.Element" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEPA Client | Transactions Résumées</title>
    </head>
    <body>
    	<jsp:include page="menu.jsp"/>
    	<div align="center">
	    <% Document doc = (Document) request.getAttribute("listTransactions"); 
	    	NodeList nList = doc.getElementsByTagName("Transaction");
	    	for (int i = 0; i < nList.getLength(); i++) {

	    		Node nNode = nList.item(i);

	    		%><br> <div style="font-weight: bold;"> <%out.print("---> Transaction "+(i+1));%> </div>
	    		
	    		<%if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	    			Element eElement = (Element) nNode;

	    			%> <br> <%out.print("Num : " + eElement.getElementsByTagName("Num").item(0).getTextContent());
	    			%> <br> <%out.print("Identifiant : " + eElement.getElementsByTagName("Identifiant").item(0).getTextContent());
	    			%> <br> <%out.print("Montant : " + eElement.getElementsByTagName("Montant").item(0).getTextContent());
	    			%> <br> <%out.print("Date : " + eElement.getElementsByTagName("Date").item(0).getTextContent());
	    			%> <br> <%out.print("/*******************************************************************************/");
	    			
	    			%> <br> <%
	    			}
	    	}
	    	%>
	    </div>
    </body>
</html>
