package EmploiDuTemps;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * classe Donnes.
 */
public class Donnees{
	static Module complexite= new Module("complexite","obligatoire");
	static Module java= new Module("java","obligatoire");
	static Module reseau= new Module("reseau","obligatoire");
	static Module typeEtProg= new Module("typeEtProg","optionnel");
	static Module surete= new Module("surete","optionnel");
	
	static List<Module> liste1 = new ArrayList<>();
	static List<Module> liste2 = new ArrayList<>();
	
	static List<Module> listeObligatoire = new ArrayList<Module>();
	

	static Etudiant E1= new Etudiant("ADDAD","Dihia","012345","Informatique","Master 1",liste1);
	static Etudiant E2= new Etudiant("HAMITOUCHE","Sonia","016589","Informatique","Master 1",liste1);
	
	static Etudiant E3= new Etudiant("ADDAD","Soraya","011245","Informatique","Master 1",liste2);
	static Etudiant E4= new Etudiant("HAMITOUCHE","Saliha","018795","Informatique","Master 1",liste2);
	
	static List<Etudiant> listeG1 = new ArrayList<>();
	static List<Etudiant> listeG2 = new ArrayList<>();
	
	static Groupe G1 = new Groupe("groupe1", listeG1);
	static Groupe G2 = new Groupe("groupe2", listeG2);
	 
	
	static LocalTime heureDebut1 = LocalTime.of(9,30);
	static LocalTime heureFin1= LocalTime.of(12,30);

	static LocalTime heureDebut3 = LocalTime.of(9,30);
	static LocalTime heureFin3= LocalTime.of(12,30);
	
	static LocalTime heureDebut2 = LocalTime.of(13,30);
	static LocalTime heureFin2= LocalTime.of(16,30);
	
	static CaseHoraire case1;
	static CaseHoraire case2;
	static CaseHoraire case3;
	
	static CaseHoraire c = new CaseHoraire();
	
	
	
	static Salles p2001= new Salles("p2001", "Cours");
	static Salles p2002= new Salles("p2002", "Cours");
	static Salles p2003= new Salles("p2003", "Machine");
	static Salles p2004= new Salles("p2004", "TP");
	static Salles p2005= new Salles("p2005", "Cours");
	
	static Salles t2004= new Salles("T2001", "Machine");
	static Salles t2005= new Salles("T2002", "Machine");
	
	static List<Salles> listeSalle=new ArrayList<>();
	
	
	static List<Salles> listeSalle2=new ArrayList<>();
	
	
	static List<Salles> listeSalleGlobal=new ArrayList<>();
	
	static Map<String, String> mapSalleDispo = new HashMap<>();
	
	static Map<Salles, String> salleDispoCreneau = new HashMap<>();
	
	static List<Seance> listeSeance= new ArrayList<>();
	
	
	
	
	
	String nomBatiment;
	private static List<Batiment> listeBatiment = new ArrayList<>();
	private static Map<Batiment, List<Salles>>mapBatimentSalle= new HashMap<Batiment, List<Salles>>();
	
	
	static Seance seance = new Seance();
	
	/**
	 * methode accueil()  permet d'afficher les deux premieres options: "etudiant" ou "prof", point de départ du logiciel. 
	 */
	public static void accueil(){
		
		listeObligatoire= complexite.creerListe(complexite, java, reseau); 
		List<Module> listee = new ArrayList<Module>();
		
		listee = complexite.creerListe(complexite, java, reseau); 
		
	
		liste1= surete.creerListeModuleFinale(listeObligatoire, surete);
		
		liste2= typeEtProg.creerListeModuleFinale(listee, typeEtProg);
		
		listeG1.add(E1);
		listeG1.add(E2);
		
		
		
		listeG2.add(E3);
		listeG2.add(E4);
		
		
	
		
		/*
		for(Etudiant e: listeG1){
			System.out.println(e.nom+" "+e.prenom+" "+e.identifiant+" "+e.getFiliere()+" "+e.getNiveau());
		}
		for(Etudiant e: listeG2){
			System.out.println(e.nom+" "+e.prenom+" "+e.identifiant+" "+e.getFiliere()+" "+e.getNiveau());
		}
		
		*/
		
		case1= c.creerCaseHoraire(heureDebut1, heureFin1);
		case2= c.creerCaseHoraire(heureDebut2, heureFin2);
		case3= c.creerCaseHoraire(heureDebut3, heureFin3);
		
		listeSalle = p2001.creerListe(p2001, p2002, p2003, p2004,p2005);
		listeSalle2 = t2004.creerListe(t2004, t2005);
		
		mapSalleDispo= p2001.creerMapSalleDispo(listeSalle);
		//p2001.afficheMapSalle(mapSalleDispo);
		
		
		Prof bes = new Prof("Bes", "Alexis", "1111","Complexite");
		Prof varacca= new Prof("Varacca", "Daniele","2222", "Java");
		Prof mokdad= new Prof("Mokdad", "Lynda","2332", "Resaux");
		Prof sovanna= new Prof("Sovanna", "Tan","2442", "Design pattern");
		
		
	
		
		Seance s = new Seance();
		
		Seance s1= s.creerSeance(bes,case1, complexite, G1, p2001, Jour.lundi);
		Seance s2= s.creerSeance(varacca,case2, java, G1, p2002, Jour.mardi);
		Seance s3= s.creerSeance(mokdad,case3, reseau, G2, p2003, Jour.mardi);
		
		
		
		listeSeance =s1.creerListe(s1, s2);
		listeSeance= s1.ajouterSeance(s3, listeSeance);
		listeSalleGlobal.addAll(listeSalle);
		listeSalleGlobal.addAll(listeSalle2);
		
		
		System.out.println("                                                                                                   ");
		System.out.println("          *****************************''liste de séances existante''*****************************");
		System.out.println("                                                                                                   ");
		s.afficheListeSeance(listeSeance);
		System.out.println("                                                                                                   ");
		System.out.println("          ****************************************************************************************");
		
		
		
		
		while(true){
			System.out.println("Vous etes: ");
			System.out.println("1:Etudiant");
			System.out.println("2:Enseignant");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			if(str.equals("1"))
				estEtudiant();
			else if(str.equals("2"))
				estEnseignant();
			else
				System.out.println("entrez 1 ou 2 uniquement");
		}
	}
	/**
	 * methode associerSalleBatiment() permet d'associer une liste de salle à un batiment. 
	 * @param un batiment.
	 * @param  une Liste de salle.
	 * @return une map de batiment avec ces salles.
	 */
	public static Map<Batiment, List<Salles>> associerSalleBatiment(Batiment b, List<Salles> l){
		mapBatimentSalle.put(b, l);
		return mapBatimentSalle;
	}
	
	
	/**
	 * methode estEnseignant()  permet d'afficher les options du "prof". 
	 */
	
	private static void estEnseignant() {
		
		Seance s = new Seance();
		 Map<String, String> salleDispoCreneau = new HashMap<>();
		while(true){
			
			System.out.println("Vous voulez :");
			System.out.println("1: Afficher les salles libres dans un creneau données");
			System.out.println("2: Réserver une salle");
			System.out.println("3: Afficher les salles occupées par un enseignant");
			System.out.println("4: Afficher les salles occupées par un groupe d'étudiant");
			System.out.println("5: Annuler une séance");
			System.out.println("6: Voir la liste des batiments de l'universite: ");
			System.out.println("7: Voir les types des salles");
			System.out.println("8: Afficher vos séances: ");
			System.out.println("0: retour accueil");
			
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			if (str.equals("1")){
				System.out.println("Entrez votre créneau sous le format (09:30 12:30) hh:mm hh:mm :");
				Scanner sc8 = new Scanner(System.in);
				String str8 = sc.nextLine();
				System.out.println("Entrez le jour de reservation souhaité :");
				Scanner sc9 = new Scanner(System.in);
				String j = sc.nextLine();
				Jour jour = jourReservation(j);
				
				salleDispoCreneau =s.salleLibreCreneau(str8,mapSalleDispo,listeSeance, j);
				p2001.afficheMapSalle2(salleDispoCreneau);
				
			}
				
			else if (str.equals("2")){
				System.out.println("Entrez votre créneau sous le format (09:30 12:30) hh:mm hh:mm :");
				Scanner sc1 = new Scanner(System.in);
				String str1 = sc.nextLine();
				
				
				System.out.println("Entrez le jour de reservation souhaité :");
				Scanner sc10 = new Scanner(System.in);
				String j = sc.nextLine();
				Jour jour = jourReservation(j);
				
				System.out.println("Veuillez choisir une salle parmis ces salles disponibles:");
				
				
				Map<String, String> salleDispoCreneau2 = s.salleLibreCreneau(str1,mapSalleDispo,listeSeance, j);
				p2001.afficheMapSalle2(salleDispoCreneau2);
				Scanner sc3 = new Scanner(System.in);
				String str3 = sc.nextLine();
				
				System.out.println("Entrez votre cours:");
				Scanner sc2 = new Scanner(System.in);
				String str2 = sc.nextLine();
				System.out.println("Entrez le groupe d'etudiant:");
				Scanner sc4 = new Scanner(System.in);
				String str4 = sc.nextLine();
				System.out.println("Entrez votre nom:");
				Scanner sc5 = new Scanner(System.in);
				String str5 = sc.nextLine();
				
				
				Prof p= new Prof(str5);
				CaseHoraire c = new CaseHoraire(str1);
				Module m = new Module(str2);
				Groupe g = new Groupe(str4);
				Salles s1 = new Salles(str3, "");
		
				Seance newSeance= s.creerSeance(p, c, m,g, s1, jour);
				
				
				listeSeance= s.ajouterSeance(newSeance, listeSeance);
				mapSalleDispo= p2001.modifSalleDispo(mapSalleDispo, s1);
				
				System.out.println("Vous avez reserver la seance suivante:");
				s.afficheSeance(newSeance);
				System.out.println("les seances programmees a present sont:");
				System.out.println("                                                                                                   ");
				
				s.afficheListeSeance(listeSeance);
				System.out.println("          ****************************************************************************************");
			}
			else if(str.equals("3")){
				System.out.println("Entrez votre nom:");
				Scanner sc6 = new Scanner(System.in);
				String str6 = sc.nextLine();
			
				s.afficherSalleEnseignant(str6,listeSeance);
				
			}
			else if (str.equals("4")){
				System.out.println("Entrez votre groupe:");
				Scanner sc7 = new Scanner(System.in);
				String str7 = sc.nextLine();
			
				s.afficherSalleGroupe(str7,listeSeance);
			}
			else if (str.equals("5")){
				System.out.println("Entrez le creneau de la seance que vous voulez annuler :");
				Scanner sc11 = new Scanner(System.in);
				String str11 = sc.nextLine();
				System.out.println("Entrez le jour de la seance que vous voulez annuler :");
				Scanner sc12 = new Scanner(System.in);
				String str12 = sc.nextLine();
				System.out.println("les seance à present sont: ");
				listeSeance = s.supprimerSeance(str11,  str12,listeSeance);
				s.afficheListeSeance(listeSeance);
				
			}
			else if(str.equals("6")){
				Batiment b =creerBatiment("P",listeSalle, 4, "faculte des sciences et technologies");
				Batiment b2 =creerBatiment("T",listeSalle2, 5, "faculte des sciences et technologies");
				
				listeBatiment= b.ajouterBatiment(listeBatiment, b);
				listeBatiment= b2.ajouterBatiment(listeBatiment,b2 );
				b.afficheInfoBatiment(listeBatiment);
				
			}
			else if (str.equals ("7")){
				p2001.afficheSalleType(listeSalleGlobal);
			}
			else if (str.equals ("8")){
				System.out.println("Entrez votre nom: ");
				Scanner sc12 = new Scanner(System.in);
				String str12 = sc.nextLine();
				s.afficherSeanceProf(str12, listeSeance);
			}
			else if (str.equals ("0"))
				accueil();
			else
				System.out.println("entrez 1,2,3 uniquement");
		}
		
	}
	
	


	/**
	 * methode jourReservation() permet de convertir les jour de type string en type Jour. 
	 * @param un str qui correspant à un jour.
	 * @return un jour.
	 */	
	public static Jour jourReservation(String str){
		switch(str){
				case "lundi" :		
					return Jour.lundi;
				case "mardi" :		
					return Jour.mardi;
				case "mercredi" :		
					return Jour.mercredi;
				case "jeudi" :		
					return Jour.jeudi;
				case "vendredi" :		
					return Jour.vendredi;
				default:
					System.out.print("veuillez reessayez");
					return null;	
		}
	}
	

	/**
	 * methode estEtudiant()  permet d'afficher les options de "l'etudiant". 
	 */
	public static void estEtudiant(){
		while(true){
			
			System.out.println("Vous etes en:");
			System.out.println("1: faculte de sciences et technologies");
			System.out.println("2: faculte de droit");
			System.out.println("3: faculte de medecine");
			System.out.println("0: retour accueil");
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			if (str.equals("1"))
				niveauSciences();
			else if(str.equals("2"))
				niveauDroit();
			else if(str.equals("3"))
				niveauMedecine();
			else if (str.equals ("0"))
				accueil();
			else
				System.out.println("entrez 1,2,3,4 uniquement");
		}
				
		
		
			 
		
	}
	
	
	
	/**
	 * methode niveauMedcine()  permet a l'etudiant de faire son choix de niveau d'étude. 
	 */
	private static void niveauMedecine() {
		
		
	}
	/**
	 * methode niveauDroit()  permet a l'etudiant de faire son choix de niveau d'étude. 
	 */
	private static void niveauDroit() {
		
		
	}

	/**
	 * methode niveauSciences()  permet a l'etudiant de faire son choix de niveau d'étude. 
	 */
	private static void niveauSciences() {
		while(true){
			
			System.out.println("Vous etes en:");
			System.out.println("Licence 1");
			System.out.println("Licence 2");
			System.out.println("Licence 3");
			System.out.println("Master 1");
			System.out.println("Master 2");
			System.out.println("0: retour accueil");
			
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			if (str.equals("Licence 1"))
				filiere("Licence 1");
			else if (str.equals("Licence 2"))
				filiere("Licence 2");
			else if (str.equals("Licence 3"))
				filiere("Licence 3");
			else if (str.equals("Master 1"))
				filiere("Master 1");
			else if (str.equals("Master 2"))
				filiere("Master 2");
			
			else if (str.equals ("0"))
				accueil();
			else
				System.out.println("veuillez reessayer");
		}
		
	}
	/**
	 * methode filiere()  permet à l'etudiant de faire son choix de spécialité. 
	 */
	private static void filiere(String s) {
		while(true){
			
			System.out.println("Votre filière est:");
			System.out.println("Informatique");
			System.out.println("Chimie");
			System.out.println("Physique");
			System.out.println("Biologie santé");
			System.out.println("0: retour accueil");
			
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			if(str.equals("Informatique")){
				
				System.out.println("Entrez votre groupe:");

				Scanner sc2= new Scanner(System.in);
				String str2 = sc.nextLine();
				seance.afficherSeanceGroupe(str2, listeSeance);
				
			}
			else if(str.equals("Chimie"))
				estEnseignant();
			else if (str.equals ("0"))
				accueil();
			else
				System.out.println("entrez 1,2,3,4,5 uniquement");
		}
		
		
	}
	/**
	 * methode creerBatiment() permet de creer un batiment. 
	 * @param un nom d'un batiment.
	 * @param  une Liste de salle.
	 * @param  le nombre d'étages.
	 * @param  une faculté.
	 * @return un batiment.
	 */
	public static Batiment creerBatiment(String b, List<Salles> l, int e, String f){
		return new
		BatimentBuilder(b)
		.salleBatiment(l)
		.nbEtage(e)
		.faculte(f)
		.build();
		
		
		
	}
	/**
	 * methode main() permet de lancer le logiciel. 
	 */
	public static void main(String[] args){
		
		accueil();
	}
		
	
}
	
