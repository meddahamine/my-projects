<?php
/*
 * projet_Register : RegisterSec Class
 */
class RegisterSec
{    

    private $titre;
	private $editeur;
	private $edit;
	private $id;
	private $idDoc;
    private $cxn;   // Database Object
    
    function __construct($data)
    {
		 // Connect to database
        $this->connectToDb();
        if(is_array($data))
        { 
			if($_POST['submit']== "save"){
					$this->setData($data);
					$this->rgisterSec();
			}else{
				if($_POST['submit']== "Update"){
					$this->setIdSectionModif($data);
					$this->modifSec();}
					}
        }
        else
        {
            throw new Exception("Error: Data must be in an array.");
        } 
    }
    
    private function setData($data)
    {
        $this->titre    = $data['titreS'];
		$this->editeur    = $data['sec'];
		$this->edit    = $data['abst'];
		$this->id    = $data['id'];
		$this->idDoc    =$data['idNewDocument'];
    }
	private function setIdSectionModif($data)
    {	
		$this->titre    = $data['titreS'];
		$this->editeur    = $data['sec'];
		$this->edit    = $data['abst'];
		$this->id    = $data['idSectionModif'];
    }
    
    private function connectToDb()
    {
        //include 'models/Database.php';
        $vars = "../include/vars.php";
        $this->cxn = new Database($vars);
    }
    
    function rgisterSec()
    {     		
        $query = "INSERT INTO `s` (`titre`,`contenu`,`iddoc`,`idsec`) VALUES ('$this->titre','$this->editeur','$this->idDoc','$this->id')";
        $sql = mysql_query($query);
		
    }
	function modifSec()
    {     		
        $query = "UPDATE `s` SET `titre`='$this->titre',`contenu`='$this->editeur' WHERE `id`='$this->id'";
        $sql = mysql_query($query);
    }
    function close()
    {
        $this->cxn->close();
    }
   
}

?>
