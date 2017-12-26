<?php
/*
 * Register Class
 */
class Register
{    
    private $name;
    private $email;
    private $username;
    private $password;
    private $cxn;   // Database Object
    
    function __construct($data)
    {
		// Connect to database
        $this->connectToDb();
		
        if(is_array($data)){
			$this->setData($data);
			$this->rgisterUser();
			}
        else 
		{
			if(is_string($data))
			{	
				$this->setEmail($data);
				if($_POST['submit']== "Display"){
					$this->displayUser();
				}elseif($_POST['submit']== "Delete"){
					$this->deleteUser();
				}else {
					$this->setName($data);
					if($_POST['submit']== "RegisterC"){
					$this->rgisterCat();}
					elseif($_POST['submit']== "DeleteC"){
						$this->deleteCat();}
						elseif($_POST['submit']== "DisplayC"){
						$this->displayCat();}
					else{ 
						throw new Exception("Error: Data must be in an array.");
					}
				}
			}
		}
	}
	
	//les setter
    function setData($data)
    {
        $this->name     = $data['name'];
        $this->email    = $data['email'];
        $this->username = $data['username'];
        $this->password = $data['password'];
    }
	function setEmail($data)
    {
        $this->email    = $data;
    }
	function setName($data)
    {
        $this->name    = $data;
    }
	//les getter
	function getName()
    {
        return $this->name;
    }
	function getEmail()
    {
        return $this->email;
    }
	function getUsername()
    {
        return $this->username;
    }
	function getPassword()
    {
        return $this->password;
    }

    //connexion BDD
    function connectToDb()
    {
        include '../models/Database.php';
        $vars = "../include/vars.php";
        $this->cxn = new Database($vars);
    }
    //register user
    function rgisterUser()
    {
		$query = "SELECT * FROM `users` WHERE `email` = '$this->email'";
		$req = mysql_query($query);
        $sql = mysql_fetch_array($req);
		if($sql>0){
			echo"<script language='JavaScript' >alert('The account already exists !');</script>";
		}else{
			//`users`:: `id`, `name`, `username`, `password`, `email`
			$query = "INSERT INTO `users` (`name`, `username`, `password`, `email`) VALUES ('$this->name','$this->username','$this->password','$this->email')";
			$sql = mysql_query($query);
			
			if($sql)   {
				echo"<script language='JavaScript' >alert('The account has been created.');</script>";
			}
			else      {  //throw new Exception("Error: not registerd $sql \n");
				$message  = 'Requête invalide : ' . mysql_error() . "\n";
				$message .= 'Requête complète : ' . $sql . "\n";
				die($message);
			}
        }
    }
	function rgisterCat()
    {
        //`users`:: `id`, `name`, `username`, `password`, `email`
        $query = "INSERT INTO `cat` (`name`) VALUES ('$this->name')";
        $sql = mysql_query($query);
        
        if($sql)   {
			echo"<script language='JavaScript' >alert('The category has been created.');</script>";
		}
        else      {  throw new Exception("Error: not registerd $sql \n");
		$message  = 'Requête invalide : ' . mysql_error() . "\n";
   		$message .= 'Requête complète : ' . $sql . "\n";
    	die($message);
		}
        
    }
	//display User
	function displayUser()
    {
        $query = "SELECT * FROM `users` WHERE `email` = '$this->email'";
		$req = mysql_query($query);
        $sql = mysql_fetch_array($req);
        
        if($sql>0)
        {		
            $this->name=$sql['name'];
			$this->email=$sql['email'];
			$this->username=$sql['username'];
			$this->password=$sql['password'];
			echo"<script language='JavaScript' >alert('The user has been recovered.');</script>";
        }
        else
        {	
			throw new Exception( "<script language='javascript'> alert('Invalid identifier !');</script>");
        }   
    }
	//display Category
	function displayCat()
    {
        $query = "SELECT * FROM `cat` WHERE `name` = '$this->name'";
		$req = mysql_query($query);
        $sql = mysql_fetch_array($req);
        
        if($sql>0)
        {		
            $this->name=$sql['name'];
			echo"<script language='JavaScript' >alert('The category has been recovered.');</script>";
        }
        else
        {	
			throw new Exception( "<script language='javascript'>alert('Invalid identifier !');</script>");
        }   
    }	
	//delete User
	function deleteUser()
    {
        $query = "DELETE FROM `users` WHERE `email` = '$this->email'";
		$sql = mysql_query($query);
        
        if($sql)
        {
            echo"<script language='JavaScript' >alert('The user has been deleted.');</script>";
        }
        else
        {	
			throw new Exception( "<script language='javascript'>alert('Invalid identifier !');</script>");
        }   
    }
	//delete Category
	function deleteCat()
    {
        $query = "DELETE FROM `cat` WHERE `name` = '$this->name'";
		$sql = mysql_query($query);
        
        if($sql)
        {
            echo"<script language='JavaScript' >alert('The category has been deleted.');</script>";
        }
        else
        {	
			throw new Exception( "<script language='javascript'>alert('Invalid identifier !');</script>");
        }   
    }
    function close()
    {
        $this->cxn->close();
    }   
}
?>
