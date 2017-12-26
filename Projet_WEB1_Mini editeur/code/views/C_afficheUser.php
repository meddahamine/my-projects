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
                    
                        <h1>List of users :</h1>
                        <?php
						$query = "SELECT * FROM `users`";
						$req = mysql_query($query);
						while ($d =mysql_fetch_array($req)){
						?>
						<form action="../controllers/LoginController.php" method="POST">
						<table>
							<tr>
								<th> User <?php echo " ID : ".$d['id'];?> </th>
							</tr>
						<tr>	
						<td><?php echo "NAME : ".$d['name']; ?></td>
						</tr>
						<tr>
						<td>EMAIL :<input name="email" value="<?php echo $d['email'] ?>" readonly="readonly"></td>
						</tr>
						<tr>
						<td><?php echo "USERNAME : ".$d['username'] ?></td>
						</tr>
						<tr>	
						<td><input type="submit" name="submit" value="Delete"/></td>
						</tr>
						</br>
						</form>
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
