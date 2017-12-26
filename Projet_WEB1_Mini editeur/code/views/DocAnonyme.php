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
					<input type="button" name="submit" value="Precedent" OnClick="window.location.href='afficheDoc.php'">
                        <?php	
						$titre   = $_POST['titre'];						
						//select id du document 
						$query1 = "SELECT * FROM `d` WHERE `titre` = '$titre'";
						$req1 = mysql_query($query1);
						$sql1 = mysql_fetch_array($req1);
							
						$x1=$sql1['id'];
						?>
						<table>
						<h1>Content of Documents :</h1>
						<tr>
						<td><h3>TITRE :</h3></td>
						<td><?php echo $sql1['titre'] ?></td>
						</tr>
						<tr>
						<td><h3>ABSTRACT :</h3></td></br>
						<td><?php echo $sql1['contenu'] ?></td>
						</tr>
						<td><h3>Contenu :</h3></td>
						<?php
						//selectionner les sections appartiennents a ce document
						$query2 = "SELECT * FROM `s` WHERE `iddoc` = '$x1'";
						$req2 = mysql_query($query2);
						
						while ($d =mysql_fetch_array($req2)){
						?>
						
						<tr>						
						<td><?php echo $d['titre'] ?></td>
						</tr>
						<tr>
						<td><?php echo $d['contenu'] ?></td>
						</tr>
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
