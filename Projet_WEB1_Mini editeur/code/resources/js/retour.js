 
	//fonction eviter le retour en arriere de navigateur apres la deconnexion
	function noBack(){window.history.forward()} 
		noBack(); 
		window.onload=noBack; 
		window.onpageshow=function(evt){if(evt.persisted)noBack()} 
		window.onunload=function(){void(0)} 