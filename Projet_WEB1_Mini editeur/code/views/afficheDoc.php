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
					<input type="button" name="submit" value="Precedent" OnClick="window.location.href='../index.php'">
                    
                        <h1>List of Documents :</h1>
                        <?php					
						$query = "SELECT * FROM `d`";
						$req = mysql_query($query);
						$i=1;
						while ($d =mysql_fetch_array($req)){
						?>
						<table>
						<form action="DocAnonyme.php" method="POST">
						<tr>						
						<td><?php echo $i.")  " ?>TITRE :<input name="titre" value="<?php echo $d['titre'] ?>" readonly="readonly"></td>
						</tr>
						</br>
						<tr>	
						<td><input type="submit" name="submit" value="Edit"></td>
						</tr>
						</form>
						</br>
						<?php
						$i++;
						}
							mysql_close();
							?>
						</table>
                    
                </div>
            </div>
        </div>
    </body>
</html>
