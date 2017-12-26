<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@ page import="org.w3c.dom.Document" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEPA Client | Rechercher Transaction</title>
    </head>
    <body>
    	<jsp:include page="menu.jsp"/>
    	<br><br>
    	<div align="center">
	    	<form action="getsearch" method="get">
			<table style="border-style:solid;width:300px;">
				<tr>
					<td>Identifiant :</td>
					<td>
						<input name="identifiant" required="required" value=""/>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><br><input type="submit" value="Rechercher"></td>
				</tr>
			</table>
			</form>
	    </div>
	    
	    <div align="center">
	    	<% Document doc = (Document) request.getAttribute("transaction");
	    		String error = (String) request.getAttribute("error");
	    	if(doc!=null){
	    			%> <br> <%out.print("Payment Identification : " + doc.getElementsByTagName("PmtId").item(0).getTextContent());
	    			%> <br> <%out.print("Instructed Amount : " + doc.getElementsByTagName("InstdAmt").item(0).getTextContent());
	    			%> <br> <%out.print("Mandate Identifier : " + doc.getElementsByTagName("MndtId").item(0).getTextContent());
	    			%> <br> <%out.print("Date Of Signature : " + doc.getElementsByTagName("DtOfSgntr").item(0).getTextContent());
	    			%> <br> <%out.print("BIC : " + doc.getElementsByTagName("BIC").item(0).getTextContent());
	    			%> <br> <%out.print("Name : " + doc.getElementsByTagName("Nm").item(0).getTextContent());
	    			%> <br> <%out.print("IBAN : " + doc.getElementsByTagName("IBAN").item(0).getTextContent());
	    			%> <br> <%out.print("Remittance Information : " + doc.getElementsByTagName("RmtInf").item(0).getTextContent());
			}
			if(error!=null){
    			%> <br> <%out.print(error);
			}%>
	    </div>
    </body>
</html>
