<script language='javascript'>
function aide(){
	alert('Il faut d\'abord s\'inscrire pour avoir l\'accés à l\'application merci !!!');
}
</script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>projet web</title>

        <link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="resources/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="resources/css/style.css" type="text/css">

        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/bootstrap.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="contents logincont">
                <div class="regiter">
                    <form action="controllers/LoginController.php" method="post">
                        <h1>Register new user:</h1>
                        <input required="required" name="name" class="input-lg" type="text" placeholder="Please write your name">
                        <input required="required" name="email" class="input-lg" type="email" placeholder="Please write your email">
                        <input required="required" name="username" class="input-lg" type="text" placeholder="please enter your username">
                        <input required="required" name="password" class="input-lg" type="password" >                    
                        <input class="btn-primary btn-lg" type="submit" name="submit" value="Register">

                    </form>
					<form action="views/afficheCat.php" method="post">
                        <h1>List of Categroy:</h1>                   
                        <input class="btn-primary btn-lg" type="submit" name="display" value="display">

                    </form>
                </div>
                <div class="login">
                    <h1><a onclick="aide()">Login :</a></h1>
                    <form action="controllers/LoginController.php" method="post">
                        <input required="required" name="username" class="input-lg" type="text" placeholder="please enter your username">
                        <input required="required" name="password" class="input-lg" type="password" > 
                        <input class="btn-primary btn-lg" type="submit" name="submit" value="Login">
                    </form>
					 <form action="views/afficheDoc.php" method="post">
					 <h1>List of Documents:</h1> 
                        <input class="btn-primary btn-lg" type="submit" name="display" value="display">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
