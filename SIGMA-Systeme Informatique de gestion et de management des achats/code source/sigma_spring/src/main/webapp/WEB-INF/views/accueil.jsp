<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>
<%@ page import="net.sigma.spring.model.Fournisseur" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SIGMA | Home</title>
        
        <link rel="shortcut icon" href="resources/img/LogoRouen.gif">
        
        <!-- Bootstrap 3.3.6 -->
  		<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
  		
  		<link rel="stylesheet" href="resources/style.css">
		
		<style type="text/css">
			body {
				background-image: url("resources/img/mairie_rouen_1v2.jpg") !important;
             	background-repeat: no-repeat !important;
		    	background-position: center !important; 
            	-webkit-background-size: cover !important;
             	background-size: cover !important;
             	background-position:center !important;
				height: 100% !important;
			}
			
			#mosaique {
				margin-top: 50px;
			}
		</style>
    </head>
    <body class="container">
	    <div class="container" style="margin-top: 20px;">
	    	<%if(session.getAttribute("userConnected")==null){ %>
	    	<div class="row">
	    		<a href="login"><button class="btn btn-block" 
	    		style="float:right;width: 100px;">Connexion</button></a>
	    		
	    		<a href="inscriptionFournisseur"><button class="btn btn-success btn-block" 
	    		style="float:right;width: 100px; margin-right: 10px;">Inscription</button></a>
	    	</div>
	    	<% } else{%>
	    	<div class="row">
	    		<a href="logout"><button class="btn btn-danger btn-block" 
	    		style="float:right;width: 110px;">Deconnexion</button></a>
	    		
	    		<a href="${sessionScope.userConnected.type}/"><button class="btn btn-success btn-block" 
	    		style="float:right;width: 100px;margin-right: 10px;">Espace</button></a>
	    		
	    		<label style="float:left;color:green;font-size: 14px;">Utilisateur connecté : ${sessionScope.userConnected.userName}</label>
	    	</div>
	    	<% }%>
	    </div>
	    
	    <% List<Fournisseur> fournisseurs = (List<Fournisseur>) request.getAttribute("listFournisseurs");
	    	int max = fournisseurs.size();%>
	    
	    <div id="mosaique" class="container">
			
			<div class="mosaique">
				<div class="photobanner">
					<img class="second" src="resources/img/logos/<%out.print(fournisseurs.get(max-1).getLogo());%>">
					<% for(int i=max-2;i>6;i--){%>
		        	<img src="resources/img/logos/<%out.print(fournisseurs.get(i).getLogo());%>">
		        <%}%>
				</div>
			</div>
	    
	    	<div class="mosaique">
		    <div class="photobanner">
		        <img class="first" src="resources/img/logos/<%out.print(fournisseurs.get(0).getLogo());%>">
		        <% for(int i=1;i<20;i++){%>
		        	<img src="resources/img/logos/<%out.print(fournisseurs.get(i).getLogo());%>">
		        <%}%>
		    </div>
			</div>
			
	    </div>
    </body>
</html>
