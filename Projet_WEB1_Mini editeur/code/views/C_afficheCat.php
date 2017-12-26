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
					<input type="button" name="submit" value="Precedent" OnClick="window.location.href='index1admin.php'">
                    
                        <h1>List of Category :</h1>
                        <?php
						$query = "SELECT * FROM `cat`";
						$req = mysql_query($query);
						while ($d =mysql_fetch_array($req)){
						?>
						<table>
							<tr>
								<th> Category <?php echo " ID : ".$d['id'];?> </th>
							</tr>
						<form action="../controllers/LoginController.php" method="POST">
						<tr>	
						<td>NAME :<input name="name" value="<?php echo $d['name'] ?>" readonly="readonly"></td>
						</tr>
						<tr>	
						<td><input type="submit" name="submit" value="DeleteC"/></td>
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
