<?php
/*
 * projet_Register : RegisterSsec Class
 */
class RegisterSsec
{    

    private $titre;
	private $editeur;
	private $edit;
	private $ids;
	private $idDoc;
	private $idss;
    private $cxn;   // Database Object
    
    function __construct($data)
    {
        if(is_array($data))
        {
            $this->setData($data);
        }
        else
        {
            throw new Exception("Error: Data must be in an array.");
        }
        
        // Connect to database
        $this->connectToDb();
        // insert user data
        $this->rgisterSsec();
        
    }
    
    private function setData($data)
    {
        $this->titre    = $data['titreSS'];
		$this->editeur    = $data['ssec'];
		$this->edit    = $data['abst'];
		$this->ids    = $data['idS'];
		$this->idss    = $data['idSS'];
		$this->idDoc    =$data['idNewDocument'];
    }
    
    private function connectToDb()
    {
        //include 'models/Database.php';
        $vars = "../include/vars.php";
        $this->cxn = new Database($vars);
    }
    
    function rgisterSsec()
    {     
        $query = "INSERT INTO `s` (`titre`,`contenu`,`iddoc`,`idsec`,`idssec`) VALUES ('$this->titre','$this->editeur','$this->idDoc','$this->ids','$this->idss')";
        $sql = mysql_query($query);
    }
    function close()
    {
        $this->cxn->close();
    }
   
}

?>
