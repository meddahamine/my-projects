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
                    <form action="../controllers/LoginController.php" method="POST">
                        <h1>List of Category :</h1>
                        <?php
						$query = "SELECT * FROM `cat`";
						$req = mysql_query($query);
						$i=1;
						while ($d =mysql_fetch_array($req)){
						?>
						<table>
						<tr>	
						<td><?php echo $i.")  " ?>NAME :<?php echo $d['name'] ?></td>
						</tr>
						</br>
						<?php
						$i++;
						}
							mysql_close();
							?>
						</table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
