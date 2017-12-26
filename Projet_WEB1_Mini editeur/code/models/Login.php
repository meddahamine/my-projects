<?php
/*
 * projet_Login : Login Class
 */
class Login {
    private $username;
    private $password;
    private $cxn;       // database object
            
    function __construct($username,$password)
    {
        // set data
        $this->setData($username, $password);
        // connect DB
        $this->connectToDb();
        // get Data
		$this->getData();      
    }
    
    private function setData($username,$password)
    {
        $this->username = $username;
        $this->password = $password;
    }
    
    private function connectToDb()
    {
        include '../models/Database.php';
        $vars = "../include/vars.php";
        $this->cxn = new Database($vars);
    }
    
    function getData()
    {
		if( $this->username =="admin" && $this->password=="1234"){
			$query = "SELECT * FROM `admin` WHERE `username` = '$this->username' AND `mdp` = '$this->password'";
		}else{
			$query = "SELECT * FROM `users` WHERE `username` = '$this->username' AND `password` = '$this->password'";}
        $sql   = mysql_query($query);
        
        if(mysql_num_rows($sql)>0)
        {
            return TRUE;
			
        }
        else
        {	
		throw new Exception( "<script language='javascript'> alert('Invalid identifier !');
		</script> <META http-equiv='refresh' content='0; URL=../index.php'>");
        }
        
    }
    
    function close()
    {
        $this->cxn->close();
    }  
}
?>
