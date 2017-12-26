<?php
session_start();
if(!(isset($_SESSION['un'])) && (isset($_SESSION['pw'])))
{
    include '../controllers/LoginController.php';
    die();
}
$_SESSION['doc'] = null;

// delete DOC
    if(isset($_POST['submit']) AND ($_POST['submit'] == "Delete" ) )
    {	
        $data   = $_POST['titre'];
        try {
            include '../models/RegisterDoc.php';
            new RegisterDoc($data);
			
        } catch (Exception $exc) {
            echo $exc->getMessage();
        }
		echo "<META http-equiv='refresh' content='0; URL=../views/index1.php?page=afficheDoc'>";
    }
	
// Edit DOC
    if(isset($_POST['submit']) AND ($_POST['submit'] == "Edit" ) )
    {	
        $data   = $_POST['titre'];
        try {
            include '../models/RegisterDoc.php';
            $doc = new RegisterDoc($data);
			$xx=$doc->getDoc();
			$xx1=$doc->getTitreD();
			$xx2=$doc->getAbst();
			$xx3=$doc->getIdDoc();
			$_SESSION['doc'] = serialize($xx);
			$_SESSION['titreD'] = serialize($xx1);
			$_SESSION['abst'] = serialize($xx2);
			$_SESSION['idDoc'] = serialize($xx3);
			
        } catch (Exception $exc) {
            echo $exc->getMessage();
        }
		echo "<META http-equiv='refresh' content='0; URL=../views/index1.php'>";
    }

?>
