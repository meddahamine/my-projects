<?php
session_start();
if(!(isset($_SESSION['un'])) && (isset($_SESSION['pw'])))
{
    include '../controllers/LoginController.php';
}
?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page Administrateur</title>

        <link rel="stylesheet" href="../resources/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="../resources/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="../resources/css/style.css" type="text/css">
        <link rel="stylesheet" href="../resources/css/menu.css" type="text/css">
        

        <script src="../resources/js/bootstrap.min.js"></script>
        <script src="../resources/js/bootstrap.js"></script>

    </head>
    <body>
        <div class="container">
    <header>
                <h2>Welcome 
					<?php if(isset($_SESSION['un']))
				{
					echo $_SESSION['un'];
				}
				?>
				<form action="../controllers/logout.php" method="POST">
					<input type="submit" name="submit" value="LougOut" onclick="noBack()">
				</form>
				<script type="text/javascript"> 
				//fonction eviter le retour en arriere de navigateur apres la deconnexion
					function noBack(){window.history.forward()} 
						noBack(); 
						window.onload=noBack; 
						window.onpageshow=function(evt){if(evt.persisted)noBack()} 
						window.onunload=function(){void(0)} 
				</script> 
				</a></h2> 
				  <div class="clear"> <nav>
                        <a class="btn-danger active" href="index1admin.php">Home Page</a>
                        <a class="btn-danger active" href="?page=afficheUser">Show List of Users</a>
                    <a class="btn-danger active" href="?page=afficheCat">Show List of Category</a>
                    </nav></div>
        
		
    <div class="clearl"></div>
            <section id="page">
                    <?php
                    if (@$_GET['page']) {
                        $url = "C_".$_GET['page'] . ".php";
                        if (is_file($url)) {
                            include $url;
                        } else {
                            echo 'requested file is not found !';
                        }
                    } else {
                        include './mainadmin.php';
                    }
                    ?>
                </section>
            <footer>
                <p>&nbsp;</p>
            </footer>
    </div>
    </body>
</html>
