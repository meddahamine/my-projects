<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c' %>
<%@ page import="org.w3c.dom.Document" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEPA Client | Déposer Transaction</title>
    </head>
    <body>
    	<jsp:include page="menu.jsp"/>
    	
    	<br><br>
    	<div align="center">
	    	<form action="addtrx" method="post">
			<table style="border-style:solid;width:400px;">
				<tr>
					<td>Payment Identification :</td>
					<td>
						<input name="PmtId" required="required" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>Instructed Amount :</td>
					<td>
						<input name="InstdAmt" required="required" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>Mandate Identifier :</td>
					<td>
						<input name="MndtId" required="required" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>Date Of Signature :</td>
					<td>
						<input name="DtOfSgntr" required="required" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>BIC :</td>
					<td>
						<input name="BIC" required="required" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>Name :</td>
					<td>
						<input name="Name" required="required" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>IBAN :</td>
					<td>
						<input name="IBAN" required="required" value=""/>
					</td>
				</tr>
				
				<tr>
					<td>Remittance Information :</td>
					<td>
						<input name="RmtInf" required="required" value=""/>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><br><input type="submit" value="Ajouter"></td>
				</tr>
			</table>
			</form>
	    </div>
	    
	    <br>
    	<div align="center">
	    	<form action="addXmlFile" method="post" enctype="multipart/form-data">
			<table style="border-style:solid;width:400px;">
				<tr>
					<td>Fichier XML :</td>
					<td>
						<input type="file" name="file" required="required">
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><br><input type="submit" value="transmettre"></td>
				</tr>
			</table>
			</form>
		</div>
	    
	    <div align="center">
	    	<% Document doc = (Document) request.getAttribute("response");
    			String error = (String) request.getAttribute("error");
	    	
	    	if(doc!=null){
	    		if(doc.getElementsByTagName("success").item(0)!=null){
	    			%> <br> <%out.print(doc.getElementsByTagName("success").item(0).getTextContent());
	    			%> <br> <%out.print("--> Numéro d'enregistrement : " + doc.getElementsByTagName("Num").item(0).getTextContent());
	    		}
	    		if(doc.getElementsByTagName("Error").item(0)!=null){
	    			%> <br> <%out.print("Erreur : " + doc.getElementsByTagName("Error").item(0).getTextContent());
	    		}
	    	}
	    	if(error!=null){
	    		%> <br> <%out.print(error);
	    	}%>
	    </div>
	    
    </body>
</html>
