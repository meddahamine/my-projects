L�objectif de ces deux TPs (TP5 et TP6) est l'utilisaton du syst�me EIP 
(Enterprise Integration Patterns) qu'est un syst�me repose sur l'�change de messages

Question 1 :
nous avons cr�er un syst�me consistant � envoyer un message entre un producteur et un
consommateur
	
1)   sairair le message sur la conosole a l'aide de systeme.in 
     l'ajout d'un consomer-2
     deux cas se presentent :
	 while le message saise est different de mot "exit"
		appelle a la fonction producerConsumer1(String message) 
		qui prend en param�te le message afin de le transmettre au consomateur 1
		avec sendBody("direct:consumer-1", msg);
		le contenu du message sera afficher sur la console 
		avec la methode from("direct:consumer-1").to("log:affiche-1-log");
		r�p�te en tant le saise est different de mot "exit"	sinon quitter	

2) le routage du message par le contenu de l'ent�te
a-   l'ajout d'un consomer-2
	while le message saise est different de mot "exit"
		appelle a la fonction producerConsumer2(String message) 
		qui prend en param�te le message afin de le transmettre au consomateur 2
		avec sendBody("direct:consumer-2", msg);
		le contenu du message sera afficher dans un fichier
		from("direct:consumer-2").to("file:messages");
		r�p�te en tant le saise est different de mot "exit"
	sinon quitter

b- l'ajout d'une nouvelle route consumer-all qui va envoyer le message � consumer-2 si l'ent�te
     destinataire contient � �crire � ou � consumer-1 sinon.
	while le message saise est different de mot "exit"
		saisir le ent�te 
		appelle a la fonction producerConsumerAll(message, head); 
		qui prend en param�te le message et header
		on a utilis� Map<String, Object> pour pouvoir utilise la methode
		sendBodyAndHeaders("direct:consumer-all", msg,headers);
		qui va envoyer le message � consumer-2 si l'ent�te
    		destinataire contient � �crire � ou � consumer-1 sinon.
		r�p�te en tant le saise est different de mot "exit"
	sinon quitter

c- l'ajout d'une nouvelle route consumer-all qui va envoyer ajouter � l'ent�te 
        destinataire avec la valeur � �crire � si le message envoy� commence par la lettre� w �.
	while le message saise est different de mot "exit"
		saisir le ent�te 
		appelle a la fonction producerConsumerAll(message, head); 
		qui prend en param�te le message et header
		on a utilis� Map<String, Object> pour pouvoir utilise la methode
		sendBodyAndHeaders("direct:consumer-all", msg,headers);
		ajouter � l'ent�te 
        destinataire avec la valeur � �crire � si le message envoy� commence par la lettre� w �.
		r�p�te en tant le saise est different de mot "exit"
	sinon quitter

3) int�gration certaines fonctionnalit�s du service REST du pr�c�dent TP.
 
a- methode qui cherche un animal par son nom et affiche le resultat
	apple � la methode producerConsumerNameAnimal("Tac") qui prend comme parametre le  nom de l'animal 
	qu'on veut chercher
	producerConsumer.producerConsumerNameAnimal("Tac");
	cette methode permet d'ajouter le nom donne en parm�tre dans la requete GET
	et faire apple au service du tp pr�c�dent avec la m�thode qui contient la methode 
	de recherche d'un animal par son nom 
	.setHeader(Exchange.HTTP_METHOD,constant("GET"))
	.to("http://localhost:8085/rest-service/animals/find/byName/"+name)
	en suite afficher le contenu du body de la r�ponse re�u dans le log ( console) avec 
	.log("reponse received : ${body}");  

b- integration du service Geonames
	on utilise la methode producerConsumerPositionCenter("Tac"); qui prend comme parametre le  nom de l'animal 
	qui retourne la position g�ographique des zoos o� se trouvent les animaux recherch�s.
	producerConsumer.producerConsumerPositionCenter("Tac");

4) ex�cution de plusieurs (3 ou plus) instances de Tomcat qui fusionne les differente donne
   a l'aide de Camal qu'on intergre precedement � l'intergation du service REST
	La m�thode producerConsumerAnimalsServers(); permet de retourner une liste d'animeaux depuis 3 serveurs
