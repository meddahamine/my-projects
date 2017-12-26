/*------------------------------------------PARTIE-1------------------------------------------*/

Service web geographique :
Pour tester cette parite le code se trouve dans le dossier � PARTIE 1 �

- ouvrir le fichier index.html avec un navigateur 
- double clic sur une position donn�e dans la carte permet de r�cuperer les coordonn�es 
	sous forme donn�e XML OU JSON qui seront affiche juste en bas de la page.
	
/*------------------------------------------PARTIE-2------------------------------------------*/

Creation de service :
Pour tester cettte partie, �cexute le projet qui se trouve dans le dossier � PARTIE 2 �
-Pour afficher le message � Universit� de Rouen � dans une page Web sous forme d'un fichier XML
il suffitde taper cette URL l'adressehttp://127.0.0.1:8090/test sur le navigateur
- la meme chose pour le message � R�ponse du service REST � � l'addresse : http://127.0.0.1:8084/hello/world 

/*------------------------------------------PARTIE-3------------------------------------------*/

Service RESTFUL : Implentation de service RESTFUL.
Pour tester cette parite le code se trouve dans le dossier � PARTIE 3 �
deux m�thode pour lance les tests :

	1- un appele aux fonctions qui sont developp�es dans le server � partir du client (main classe client)==> concerne la methode GET, POST, PUT et DELETE. 
	2- taper l'URL sur le navigateur pour declencher la requ�te ==> concerne que la methode GET. 
NB : 
	1- {position} s'�crit sous forme altitude;longitude ex: /find/near/0.51452;1.20547 ou /find/near/0.51452d;1.20547d
	2- vous pouvez voir les message qui sont retourn�s par le service sur la console server ex : affichage des infos d'un animal
	Les focntions sont :
� La fonction qui permet de retourne l'ensemble des animaux du centre ==> GET
� La fonction qui permet d�ajoute un animal au centre ==> POST 
� La fonction qui permet de retourne l�animal identifi� par {animal_id} GET 
� La fonction qui permet de modifie l'ensemble des animaux ==> PUT 
� La fonction qui permet de supprime l'ensemble des animaux ==> DELETE 
� La fonction qui permet de cr�er l�animal identifi� par {animal_id} ==> POST 
� La fonction qui permet de modifier l�animal identifi� par {animal_id} ==> PUT 
� La fonction qui permet de supprimer l�animal identifi� par {animal_id} ==> DELETE 
� La fonction qui permet de rechercher un animal par son nom {name} ==> GET 
� La fonction qui permet de rechercher un animal par sa position {position} ==> GET 
� La fonction qui permet de rechercher des animaux pr�s d�une position {position} ==> GET 
� La fonction qui permet de r�cup�rer des informations d'un animal identifi� par {animal_id} ==> GET 

les appelle aux fonctions a partir du client sont comme suit :

	1- Ajouter une cage
        	client.add_cage(Cage g);
        		voir le resultat sur le navigateur : /animals
		
        2- Supprimer une cage
        	client.delete_cage(Cage g);
        		voir le resultat sur le navigateur : /animals
		
        3- Ajoute un animal
        	client.add_animal(Animal a);
        		voir le resultat sur le navigateur : /animals
        
	4- Modifie l'ensemble des animaux : la modification rajoute "Modifi�" au nom de chaque animal
        	client.edit_animals();
        		voir le resultat sur le navigateur : /animals
		
        5- Supprime l'ensemble des animaux
        	client.delete_animals();
        		voir le resultat sur le navigateur : /animals
		
        6- Cr�e l�animal identifi� par {animal_id}
        	client.add_animal_By_Id(new Animal(Stirng nomAnimal, Stirng nomCage, Stirng nomSpecies, UUID.randomUUID()), String animal_id);
        		voir le resultat sur le navigateur : /animals
		
        7- Modifie l�animal identifi� par {animal_id}
        	client.edit_animal_By_Id(Stirng animal_id);
        		voir le resultat sur le navigateur : /animals
		
        8- Supprime l�animal identifi� par {animal_id}
        	client.delete_animal_By_Id(Stirng animal_id);
        		voir le resultat sur le navigateur : /animals
		
        9- Recherche d'un animal par son nom
        	client.find_animal_By_Name(Stirng nomAnimal);
        		voir le resultat sur le navigateur : /find/byName/{name}
		
        10- Recherche d'un animal par position
        	client.find_animal_By_Position("latitude;longitude");
        		voir le resultat sur le navigateur : /find/byName/{position}
		
        11- Recherche d'un animal pr�s d�une position
        	client.find_animal_Near_Position("latitude;longitude");
        		voir le resultat sur le navigateur : /find/near/{position}
        
	12- R�cup�ration des info. Wolfram d�un animal
        	client.animal_Infos_Wolfram(Stirng animal_id);
        		voir le resultat sur le navigateur : /animals/{animal_id}/wolf
		
        13- R�cup�ration des info. Du trajet depuis une position GPS jusqu�� votre centre en utilisant le service Graphhopper
        client.animal_Infos_Trajet("latitude;longitude");
		voir le resultat sur le navigateur : /center/journey/from/{position}

	14- Supprimer l'ensemble des animaux d'une cage
		client.delete_animals_Cage(String cageName);
			url : /cage/deleteAnimals/{cageName}

	15- Modifie l'ensemble des animaux d'une cage
		client.edit_animals_Cage(Animal animal);
			url : /cage/edit

	16- Supprime un animal par son nom
    		client.delete_animal_By_Name(String animalName);
			url : /animalDelete

	16- R�cup�re des info. Wolfram d�un animal par son nom
    		client.animal_Infos_Wolfram_byName(String name);
			url : /wolf