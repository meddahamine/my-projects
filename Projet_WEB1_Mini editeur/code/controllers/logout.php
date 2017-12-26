<?php
/*
 * projet_Logout : Logout
 */
 session_start();
 
 if(isset($_POST['submit']) AND ($_POST['submit'] == "LougOut" ) )
 {		
	 if((isset($_SESSION['un'])) && (isset($_SESSION['pw'])))
	{		
		unset($_SESSION['un']);
		unset($_SESSION['pw']);
		$_SESSION= array();
		session_destroy();
		
		echo"<script language='JavaScript' >alert('You are disconnected !!!');</script>";
		echo"<script language='JavaScript' >function noBack(){window.history.forward()} 
									noBack(); 
									window.onload=noBack; 
									window.onpageshow=function(evt){if(evt.persisted)noBack()} 
									window.onunload=function(){void(0)};</script>";
		echo "<META http-equiv='refresh' content='0; URL=../index.php'>";
	}
}
?>
