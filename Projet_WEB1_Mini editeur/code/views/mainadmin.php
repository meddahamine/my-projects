
    <link rel="stylesheet" href="../resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="../resources/css/menu.css" type="text/css">
      

    <script src="../resources/js/bootstrap.min.js"></script>
    <script src="../resources/js/bootstrap.js"></script>
	<script src="../views/editer.js"></script>
	<script src="../views/editeur.js"></script>
	


<body onLoad ='loadIframe()'>

    <div class="container">
        <div class="contents logincont">
			<div class="regiter">
				<form action="../controllers/LoginController.php" name="ajoutuser" id="ajoutuser" method="POST" >
					<h3>Register new user:</h3>
					<input required="required" name="name" class="input-lg" type="text" placeholder="Please write his name">
					<input required="required" name="email" class="input-lg" type="email" placeholder="Please write his email">
					<input required="required" name="username" class="input-lg" type="text" placeholder="please enter a username">
					<input required="required" name="password" class="input-lg" type="password" placeholder="please enter a password"> 
					<input type="submit" name="submit" value="Register"/>
				</form>
				
				<form action="../controllers/LoginController.php" name="suppuser" id="suppuser" method="POST" >
					<h3>Delete user:</h3>
					<input required="required" name="email" class="input-lg" type="email" placeholder="Please write his email">
					<input type="submit" name="submit" value="Delete"/>
				</form>
				<form action="../controllers/LoginController.php" name="consuser" id="consuser" method="post" >
					<h3>View user:</h3>
					<input required="required" name="email" class="input-lg" type="email" placeholder="Please write his email">
					<input type="submit" name="submit"  value="Display"/>
					<p>
						<?php
							if($_SESSION['tab']==null){	
							}else{
								include '../models/Register.php';
								$u = unserialize($_SESSION['tab']);
								echo "Name :".$u->getName()."</br>";
								echo "Email :".$u->getEmail()."</br>";
								echo "Username :".$u->getUsername()."</br>";
								echo "Password :".$u->getPassword()."</br>";
								$_SESSION['tab']=null;
							}
						?>
					</p>
				</form>
			</div>
			<div class="login">
				<form action="../controllers/LoginController.php" name="ajoutcat" id="ajoutcat" method="POST" >
					<h3>Register new category:</h3>
					<input required="required" name="name" class="input-lg" type="text" placeholder="Please write his name">
					<input type="submit" name="submit" value="RegisterC"/>
				</form>
				
				<form action="../controllers/LoginController.php" name="suppcat" id="suppcat" method="POST" >
					<h3>Delete category:</h3>
					<input required="required" name="name" class="input-lg" type="text" placeholder="Please write his name">
					<input type="submit" name="submit" value="DeleteC"/>
				</form>
				<form action="../controllers/LoginController.php" name="conscat" id="conscat" method="post" >
					<h3>View category:</h3>
					<input required="required" name="name" class="input-lg" type="text" placeholder="Please write his name">
					<input type="submit" name="submit"  value="DisplayC"/>
					<p>
						<?php
							if($_SESSION['tabC']==null){	
							}else{	
								include '../models/Register.php';
								$u = unserialize($_SESSION['tabC']);
								echo "Name :".$u->getName()."</br>";
								$_SESSION['tabC']=null;
							}
						?>
					</p>
				</form>
			</div>
		</div>
	</div>
    
    

</body>