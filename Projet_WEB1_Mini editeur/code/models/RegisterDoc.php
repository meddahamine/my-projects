<?php
/*
 * projet_Register : RegisterDoc Class
 */
class RegisterDoc
{    
    private $titre;
    private $editeur;
	private $cat;
	private $doc;
	private $idDoc;
	private $idNewDoc;
	private $titreD;
	private $abst;
	private $idDocModif;
    private $cxn;   // Database Object
    
    function __construct($data)
    {
		// Connect to database
        $this->connectToDb();
        if(is_array($data))
        {
            $this->setData($data);
			if($_POST['submit']== "save"){
					$this->rgisterDoc();
				}else{
					if($_POST['submit']== "Update"){
						$this->setIdDocModif($data);
						$this->modifDoc();}
					}
        }
        else
        {	if(is_string($data)){
				$this->setTitre($data);
				if($_POST['submit']== "Delete"){
					$this->deleteDoc();
				}else{
					if($_POST['submit']== "Edit"){
					$this->editDoc();}
					}
			}else{
				throw new Exception("Error: Data must be in an array.");
				}
		}
	}
    //les getters
	function getDoc()
    {
        return $this->doc;
    }
	function getIdDoc()
    {
        return $this->idDoc;
    }
	function getTitreD()
    {
        return $this->titreD;
    }
	function getAbst()
    {
        return $this->abst;
    }
	function getIdNewDoc()
    {
        return $this->idNewDoc;
    }
	//les setters
    private function setData($data)
    {
        $this->titre     = $data['titre'];
        $this->editeur    = $data['abst'];
		$this->cat    = $data['cat'];
    }
	private function setIdDocModif($data)
    {
        $this->idDocModif     = $data['idDocModif'];
    }
    private function setTitre($data)
    {
        $this->titre     = $data;
    }
	private function setIdNewDoc($id)
    {
        $this->idNewDoc     = $id;
    }
    private function connectToDb()
    {
        include '../models/Database.php';
        $vars = "../include/vars.php";
        $this->cxn = new Database($vars);
    }
    
    function rgisterDoc()
    {
		$x=$_SESSION['un'];	
		$query1 = "SELECT * FROM `users` WHERE `username` = '$x'";
		$req1 = mysql_query($query1);
        $sql1 = mysql_fetch_array($req1);		
        $x1=$sql1['id'];

		$query2 = "SELECT * FROM `cat` WHERE `name` = '$this->cat'";
		$req2 = mysql_query($query2);
        $sql2 = mysql_fetch_array($req2);		
        $x2=$sql2['id'];

        $query = "INSERT INTO `d` (`titre`, `contenu`,`iduser`,`idcat`) VALUES ('$this->titre','$this->editeur','$x1','$x2')";
        $sql = mysql_query($query);
		$idnew = mysql_insert_id();
		$this->setIdNewDoc($idnew);
        echo"<script language='JavaScript' >alert('Your Document has been created.');</script>";
    }
	
	function modifDoc()
    {
		$x=$_SESSION['un'];	
		$query1 = "SELECT * FROM `users` WHERE `username` = '$x'";
		$req1 = mysql_query($query1);
        $sql1 = mysql_fetch_array($req1);		
        $x1=$sql1['id'];

		$query2 = "SELECT * FROM `cat` WHERE `name` = '$this->cat'";
		$req2 = mysql_query($query2);
        $sql2 = mysql_fetch_array($req2);		
        $x2=$sql2['id'];
		
        $query = "UPDATE `d` SET `titre`='$this->titre', `contenu`='$this->editeur',`iduser`='$x1',`idcat`='$x2' WHERE `id`='$this->idDocModif'";
        $sql = mysql_query($query);
		
        echo"<script language='JavaScript' >alert('Your Document has been modified.');</script>";
    }
	
	//deleteDoc
	function deleteDoc()
    {
        $query1 = "SELECT * FROM `d` WHERE `titre` = '$this->titre'";
		$req1 = mysql_query($query1);
        $sql1 = mysql_fetch_array($req1);		
        $x1=$sql1['id'];
		
		$query2 = "DELETE FROM `s` WHERE `iddoc` = '$x1'";
		$sql2 = mysql_query($query2);
		
		$query = "DELETE FROM `d` WHERE `titre` = '$this->titre'";
		$sql = mysql_query($query);
        
        if($sql)
        {
            echo"<script language='JavaScript' >alert('Your Document has been deleted.');</script>";
        }
        else
        {	
			throw new Exception( "<script language='javascript'>alert('Invalid identifier !');</script>");
        }   
    }
	
	//Edit Doc
	function editDoc()
    {
		//select id du document 
        $query1 = "SELECT * FROM `d` WHERE `titre` = '$this->titre'";
		$req1 = mysql_query($query1);
        $sql1 = mysql_fetch_array($req1);
		
        $x1=$sql1['id'];
        $x2=$sql1['titre'];
        $x3=$sql1['contenu'];
		
		//selectionner les sections appartiennents a ce document
		$query2 = "SELECT * FROM `s` WHERE `iddoc` = '$x1'";
		$req2 = mysql_query($query2);

        if($sql1 )
        {
			$this->doc=$query2;
			$this->idDoc=$x1;
			$this->titreD=$x2;
			$this->abst=$x3;
            echo"<script language='JavaScript' >alert('The docuement has been recovered.');</script>";
        }
        else
        {	
			throw new Exception( "
			<script language='javascript'>alert('Invalid identifier !');</script>");
        }   
    }
	//close connexion
    function close()
    {
        $this->cxn->close();
    }
   
}

?>
