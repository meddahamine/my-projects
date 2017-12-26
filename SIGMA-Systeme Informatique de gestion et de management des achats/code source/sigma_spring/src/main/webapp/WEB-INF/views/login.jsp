<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>SIGMA | Login</title>
        
 	<link rel="shortcut icon" href="resources/img/LogoRouen.gif">
 	
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="resources/dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="resources/plugins/iCheck/square/blue.css">
  
  <style type="text/css">
		body {
			background-image: url("resources/img/mairie_rouen_3v2.jpg") !important;
             background-repeat: no-repeat !important;
		    background-position: center !important; 
            -webkit-background-size: cover !important;
             background-size: cover !important;
             background-position:center !important;
		}
		
  	.login-page {
  		overflow-y: auto !important;
  		height: 80%;
  	}
  </style>
  
</head>
<body class="hold-transition login-page container">

	<div class="row" style="margin-top: 20px;">
		<a href="home"><button class="btn btn-block" 
		    style="float:left;width: 100px;">Accueil</button></a>
	</div>
	
	<div class="login-box">
	  <div class="login-logo">
	    <a href="#"><b>SIGMA</b></a>
	  </div>
	  <!-- /.login-logo -->
	  <div class="login-box-body">
	    <p class="login-box-msg">Connectez-vous à votre compte SIGMA</p>
	
	    <form:form action="connexion" method="post">
	      <div class="form-group has-feedback">
	        <input type="text" name="userName" required="required" class="form-control" placeholder="User Name">
	        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="password" name="password" required="required" class="form-control" placeholder="Password">
	        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
	      </div>
	      <div style="color: red">${error}</div>
	      <div style="color: green">${success}</div>
	      <div class="row">
	        <div class="col-xs-12 col-sm-7">
	          <div class="checkbox icheck">
	            <label>
	              <input type="checkbox"> Se souvenir de moi
	            </label>
	          </div>
	        </div>
	        <!-- /.col -->
	        <div class="col-xs-12 col-sm-5">
	          <button type="submit" class="btn btn-primary btn-block btn-flat">Se connecter</button>
	        </div>
	        <!-- /.col -->
	      </div>
	    </form:form>
	
		<a href="inscriptionFournisseur">Inscription Fournisseur</a><br><br>
	    <a href="#">Mot de passe oublié</a>
	
	  </div>
	  <!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

<!-- jQuery 2.2.3 -->
<script src="resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="resources/plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
</body>
</html>
