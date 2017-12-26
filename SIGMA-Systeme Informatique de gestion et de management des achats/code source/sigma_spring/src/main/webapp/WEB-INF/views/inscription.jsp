<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  
	<title>SIGMA | Inscription Fournisseur</title>
        
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
			background-image: url("resources/img/mairie_rouen_2v2.jpg") !important;
             background-repeat: no-repeat !important;
		    background-position: center !important; 
            -webkit-background-size: cover !important;
             background-size: cover !important;
             background-position:center !important;
		}
	</style>

</head>
<body class="hold-transition register-page container">

	<div class="row" style="margin-top: 20px;">
		<a href="home"><button class="btn btn-block" 
		    style="float:left;width: 100px;">Accueil</button></a>
	</div>
	
	<div class="register-box" style="margin-top: 0px;">
	  <div class="register-logo">
	    <a href=""><b>SIGMA</b></a>
	  </div>
	
	  <div class="register-box-body">
	    <p class="login-box-msg">Inscription d'un nouveau fournisseur</p>
	
	    <form:form action="inscrireFournisseur" method="post">
	      <div class="form-group has-feedback">
	        <input type="text" name="name" required="required" class="form-control" placeholder="Nom">
	        <span class="glyphicon glyphicon-user form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="text" name="address" required="required" class="form-control" placeholder="Address">
	        <span class="glyphicon glyphicon-home form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="email" name="email" required="required" class="form-control" placeholder="Email">
	        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="text" name="telephone" required="required" class="form-control" placeholder="Téléphone">
	        <span class="glyphicon glyphicon-phone form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="text" name="userName" required="required" class="form-control" placeholder="Nom d'utilisateur">
	        <span class="glyphicon glyphicon-user form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="password" name="password" required="required" class="form-control" placeholder="Mot de passe">
	        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
	      </div>
	      <div class="row">
	        <div class="col-xs-12 col-sm-8">
	          <div class="checkbox icheck">
	            <label>
	              <input type="checkbox"> J'accepte les <a href="#">termes</a>
	            </label>
	          </div>
	        </div>
	        <!-- /.col -->
	        <div class="col-xs-12 col-sm-4">
	          <button type="submit" class="btn btn-primary btn-block btn-flat">Enregistrer</button>
	        </div>
	        <!-- /.col -->
	      </div>
	    </form:form>
	
	    <a href="login" class="text-center">J'ai déjà compte</a>
	  </div>
	  <!-- /.form-box -->
	</div>
	<!-- /.register-box -->

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