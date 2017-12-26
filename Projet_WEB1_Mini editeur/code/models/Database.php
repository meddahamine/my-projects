<?php
/*
 * projet_web : database connection 
 */
class Database 
{
    private $host;
    private $user;
    private $password;
    private $database;
    
    function __construct($filename)
    {
        if(is_file($filename)) include $filename;
        else throw new Exception("Error!");
        
        $this->host = $host;
        $this->user = $user;
        $this->password = $password;
        $this->database = $database; 
        
        $this->connect();
    }
    
    private function connect()
    {
		$link = @mysql_connect('localhost://3306/', 'root', '');
		$db_selected = @mysql_select_db('projetweb', $link);       
    }
    
    function close()
    {
        mysql_close();
    }
}

?>
