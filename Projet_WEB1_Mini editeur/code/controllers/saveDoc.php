<?php
session_start();
if(!(isset($_SESSION['un'])) && (isset($_SESSION['pw'])))
{
    include 'LoginController.php';
    die();
}

?>
<?php
    if(isset($_POST['submit']) AND $_POST['submit'] == "save" )
	{
        $data['titre']       = $_POST['titreDoc'];
		$data['abst']      = $_POST['trs'];
		$data['cat']      = $_POST['trsC'];
		$data['i']      = $_POST['trsi'];
		$data['j']      = $_POST['trsj'];
		include '../models/RegisterDoc.php';
		include '../models/RegisterSec.php';
		include '../models/RegisterSsec.php';
        try {
            $saveNewDoc = new RegisterDoc($data);
			$xx=$saveNewDoc->getIdNewDoc();
			$data['idNewDocument'] = $xx;
			for ($i = 0; $i < $data['i']; $i++) {
				$data['sec'] = $_POST['contenuS'.$i];
				$data['titreS'] = $_POST['titreS'.$i];
				$data['id'] = $_POST['id'.$i];
				new RegisterSec($data);
				
				for ($j = 0; $j < $data['j']; $j++) {
					$data['ssec'] = $_POST['contenuSS'.$j];
					$data['titreSS'] = $_POST['titreSS'.$j];
					$data['idS'] = $_POST['idS'.$j];
					$data['idSS'] = $_POST['idSS'.$j];
					
					if ($data['idS'] == $data['id']){
						new RegisterSsec($data);
						}
				} 
			}
			
			
        } catch (Exception $exc) {
            echo $exc->getMessage();
        } 
		
    }  
	
	
	if(isset($_POST['submit']) AND $_POST['submit'] == "Update" )
	{
        $data['idDocModif']       = $_POST['idDocModif'];
		$data['titre']      = $_POST['titreDocModif'];
		$data['abst']      = $_POST['trcModif'];
		$data['cat']      = $_POST['trsC'];
		$data['indexI']      = $_POST['indexI'];
				
        try {
            include '../models/RegisterDoc.php';
            new RegisterDoc($data);
			
			
			include '../models/RegisterSec.php';
			for ($i = 0; $i < $data['indexI']; $i++) {
				$data['idSectionModif'] = $_POST['idSectionModif'.$i];
				$data['titreS'] = $_POST['sectionTitre'.$i];
				$data['sec'] = $_POST['sectionModif'.$i];
				new RegisterSec($data);
			}
            
        } catch (Exception $exc) {
            echo $exc->getMessage();
        } 
		
    }
	echo "<META http-equiv='refresh' content='0; URL=../views/index1.php'>";
?>