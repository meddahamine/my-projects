<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projet WEB2</title>
    </head>
    <body>
    	<div align="center">
	        <h1>Création d'un service REST avec SPRING</h1>
	        <h2>Réalisé par : AOUDJIT Said Oulguendouz et MEDDAH Amine</h2>
	        <h3>Date : 20 Avril 2017</h3>
	        <table border="1">
	        	<th>URL</th>
	        	<th>Méthode</th>
	        	<th>Action</th>
	        	
	        	<tr>
	        		<td><a href="detail">/detail</a></td>
					<td>GET</td>
					<td>Renvoie un flux XML contenant la liste des transactions détaillées</td>
	        	</tr>
	        	
	        	<tr>
	        		<td><a href="resume">/resume</a></td>
					<td>GET</td>
					<td>Renvoie un flux XML contenant la liste des transactions résumées</td>
	        	</tr>
	        	
	        	<tr>
	        		<td><a href="stats">/stats</a></td>
					<td>GET</td>
					<td>Afficher une synthèse des transactions stockées, avec les informations suivantes : 
					Nombre de transactions, montant total des transactions</td>
	        	</tr>
	        	
	        	<tr>
	        		<td><a href="">/trx/id</a></td>
					<td>GET</td>
					<td>Renvoie un flux XML décrivant le détail de la transaction d’identifiant id
					avec <strong>id = PmtId</strong></td>
	        	</tr>        	
	        	
	        	<tr>
	        		<td><a href="">/depot</a></td>
					<td>POST</td>
					<td>Reçoit un flux XML décrivant une transaction. <br> 
					Un message de retour indique le résultat de l'opération, avec le numéro 
					d'identification en cas de succès, et un message d'erreur sinon.</td>
	        	</tr>        	        	
			</table>
    	</div>
    </body>
</html>
