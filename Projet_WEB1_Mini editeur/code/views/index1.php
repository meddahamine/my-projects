<?php
session_start();
if(!(isset($_SESSION['un'])) && (isset($_SESSION['pw'])))
{
    include '../controllers/LoginController.php';
    die();
}

?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projet Web</title>

        <link rel="stylesheet" href="../resources/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="../resources/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="../resources/css/style.css" type="text/css">
        <link rel="stylesheet" href="../resources/css/menu.css" type="text/css">
        

        <script src="../resources/js/bootstrap.min.js"></script>
        <script src="../resources/js/bootstrap.js"></script>
		<script src="../resources/js/retour.js"></script>	
		
    </head>
    <body>
        <div class="container">
        <style type="text/css">
        div#header-fixed {position:fixed; top:0px; margin:auto; z-index:100000; width:100%;} 
div#my-menus {background:#000; height:75px; opacity:0.8}
</style>
            <header style="height:290px">
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>

              <h2>Welcome 
                <?php if(isset($_SESSION['un']))
				{
					echo $_SESSION['un'];
				}
				?>
				</a> 
              </h2>  
                          <div class="clear"> <nav>
                        <a class="btn-danger active" href="index1.php">Home Page</a>
                        <!--<a class="btn-danger active" href="?page=MainSettings">Main Settings</a>-->
                    <a class="btn-danger active" href="?page=afficheDoc">Show My documents</a>
                    </nav></div>
        </header>

<div class="contents">
                <aside>
                    
<div>
<h3>Document Category</h3>
<SELECT  required onChange="reCat(this)" >
	<OPTION VALUE=""> Select Category ... </OPTION>
      <?php 
       // connexion
		try
		{
			$link = @mysql_connect('localhost://3306/', 'root', '');
			$db_selected = @mysql_select_db('projetweb', $link);
		}
		catch(Exception $e)
		{
			die('Erreur : '.$e->getMessage());
		}
        //requete    
            $req="SELECT * FROM `cat`";
            $res=mysql_query($req);
			//echo '<OPTION VALUE=""> NONE </OPTION>';
            while ($ligne=mysql_fetch_array($res))
            {
               echo '<OPTION VALUE="'.$ligne["name"].'">'.$ligne["name"].'</OPTION>';
            }
      ?>
</SELECT>
<h3>summary :</h3>
<h6><ul id="list" style="width:20%; float:left; position:fixed; text-align:left;">
</ul></h6>
</div>
       
    </aside>
    <div class="clearl"></div>
            <section id="page" style="float:right">
                    <?php
                    if (@$_GET['page']) {
                        $url = "./C_".$_GET['page'] . ".php";
                        if (is_file($url)) {
                            include $url;
                        } else {
                            echo 'requested file is not found !';
                        }
                    } else {
						
							include './main.php';
						  
                    }
                    ?>
                </section>
            <footer>
                <p>&nbsp;</p>
            </footer>
    </div>
	<div>
				<form action="../controllers/logout.php" method="POST">
				<input class="btn-primary btn-lg"type="submit" name="submit" value="LougOut" onclick="noBack()"/>
				</form>
	</div>
    </body>
</html>
