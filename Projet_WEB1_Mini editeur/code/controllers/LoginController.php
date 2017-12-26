<?php
/*
 * projet_web:: LoginController
 */
session_start();

if($_POST)
{
    // Login
    if(isset($_POST['submit']) AND $_POST['submit'] == "Login" )
    {	
        $username  = $_POST['username'];
        $password  = $_POST['password'];
        
        try
        {
            include '../models/Login.php';
            $login = new Login($username, $password);
            
            if($login == TRUE)
            {
                
                $_SESSION['un'] = $username;
				$_SESSION['pw'] = $password;
				$_SESSION['tab'] = null;
				$_SESSION['tabC'] = null;
				$_SESSION['doc'] = null;
				if($username=='admin'){
					header('Location:../views/index1admin.php');
				}else{
                header('Location:../views/index1.php');}
            }
            
        }
        catch (Exception $exc)
        {
            echo $exc->getMessage();
        }
        
    }
    // Register User
    if(isset($_POST['submit']) AND $_POST['submit'] == "Register" )
    {
        $data['name']       = $_POST['name'];
        $data['email']      = $_POST['email'];
        $data['username']   = $_POST['username'];
        $data['password']     = $_POST['password'];
		
        try {
            include '../models/Register.php';
            new Register($data);
			if(($_SESSION['un'] == "admin") && ($_SESSION['pw'] == "1234")){
				echo "<META http-equiv='refresh' content='0; URL=../views/index1admin.php'>";
			}else{
				echo "<META http-equiv='refresh' content='0; URL=../index.php'>";
				} 
        } catch (Exception $exc) {
            echo $exc->getMessage();
        }        
    } 
	// Register Category
    if(isset($_POST['submit']) AND $_POST['submit'] == "RegisterC" )
    {
        $data       = $_POST['name'];
		
        try {
            include '../models/Register.php';
            new Register($data);
            
        } catch (Exception $exc) {
            echo $exc->getMessage();
        } 
		echo "<META http-equiv='refresh' content='0; URL=../views/index1admin.php'>";
    } 
	// Consulter && supprimer User
    if(isset($_POST['submit']) AND ($_POST['submit'] == "Display" || $_POST['submit'] == "Delete" )  )
    {	
        $data   = $_POST['email'];
        try {
            include '../models/Register.php';
            $utilisateur=new Register($data);
			$_SESSION['tab'] = serialize($utilisateur);
			//echo"<script>alert('Successful operation user');</script>";
        } catch (Exception $exc) {
            echo $exc->getMessage();
        }
		echo "<META http-equiv='refresh' content='0; URL=../views/index1admin.php'>";     
    } 
	// Consulter && supprimer Category
    if(isset($_POST['submit']) AND ($_POST['submit'] == "DisplayC" || $_POST['submit'] == "DeleteC" )  )
    {	
        $data   = $_POST['name'];
        try {
            include '../models/Register.php';
            $utilisateurC=new Register($data);
			$_SESSION['tabC'] = serialize($utilisateurC);
			//echo"<script>alert('Successful operation category');</script>";
        } catch (Exception $exc) {
            echo $exc->getMessage();
        }
		echo "<META http-equiv='refresh' content='0; URL=../views/index1admin.php'>";
    }
}
else
{
    include '../index.php';
}
?>
