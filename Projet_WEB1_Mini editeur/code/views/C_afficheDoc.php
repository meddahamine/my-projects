<?php
try
{
	$link = @mysql_connect('localhost://3306/', 'root', '');
	$db_selected = @mysql_select_db('projetweb', $link);
}
catch(Exception $e)
{
	die('Erreur : '.$e->getMessage());
}?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>projet web</title>

        <link rel="stylesheet" href="../resources/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="../resources/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="../resources/css/style.css" type="text/css">

        <script src="../resources/js/bootstrap.min.js"></script>
        <script src="../resources/js/bootstrap.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="contents logincont">
                <div class="regiter">
					<input type="button" name="submit" value="Precedent" OnClick="window.location.href='../views/index1.php'">
                    
                        <h1>List of Documents :</h1>
                        <?php
						$x=$_SESSION['un'];	
						$query1 = "SELECT * FROM `users` WHERE `username` = '$x'";
						$req1 = mysql_query($query1);
						$sql1 = mysql_fetch_array($req1);		
						$x1=$sql1['id'];
						
						$query = "SELECT * FROM `d` WHERE `iduser` = '$x1'";
						$req = mysql_query($query);
						while ($d =mysql_fetch_array($req)){
						?>
						<table>
							<tr>
								<th> Documents :<?php echo " ID : ".$d['id'];?> </th>
							</tr>
						<form action="../controllers/Doc.php" method="POST">
						<tr>						
						<td>TITRE :<input name="titre" value="<?php echo $d['titre'] ?>" readonly="readonly"></td>
						</tr>
						</br>
						<tr>	
						<td><input type="submit" name="submit" value="Edit"></td>
						<td><input type="submit" name="submit" value="Delete"></td>
						</tr>
						</form>
						</br>
						<?php
						}
							mysql_close();
							?>
						</table>
                    
                </div>
            </div>
        </div>
    </body>
</html>
