package EmploiDuTemps;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * classe Seance.
 */
public class Seance implements InterfaceCreation{
	private Module module;
	private CaseHoraire caseHoraire; 
	private Groupe groupe;
	private Salles salle;
	private Prof professeur;
	 Jour jour;

	public Seance(Prof professeur, CaseHoraire caseHoraire, Module module,Groupe groupe,Salles salle, Jour jour){
		this.module = module;
		this.professeur=professeur;
		this.caseHoraire= caseHoraire;
		this.groupe= groupe;
		this.salle= salle;
		this.jour= jour;
		
	}
	public String getProf(){
		return professeur.getNom();
	}
	public String getGroupe(){
		return groupe.getNomGroupe();
	}
	public Seance() {
	
	}
	
	
	/**
	 * methode creerSeance() permet de creer  une nouvelle seance dans lemplois du temps. 
	 * @param un Prof.
	 * @param une Case Horaire.
	 * @param un Module.
	 * @param un Groupe.
	 * @param une Salle.
	 * @param un Jour.
	 * @return une Seance.
	 */
	
	public Seance creerSeance(Prof professeur,CaseHoraire caseHoraire, Module module,Groupe groupe,Salles salle, Jour jour){
		
		Seance seance = new Seance(professeur,caseHoraire,module, groupe, salle, jour);
		
		return seance;
	}
	
	/**
	 * methode afficheSeance() permet d'afficher une seance.  
	 * @param une Seance.
	 */
	public 	void afficheSeance(Seance s){
		System.out.println("Le prof: "+s.getProf()+" donne le cours de "+s.module.getIntitule()+" a "+s.caseHoraire.getNom()+" au "+ s.groupe.getNomGroupe()+" dans la salle "+s.salle.getNom());
	}
	
	
	/**
	 * methode afficheListeSeance() permet d'afficher la liste des seances existantes.  
	 * @param une Seance.
	 */
	
	public 	void afficheListeSeance(List<Seance> l){
		l.stream()
		.forEach(s->System.out.println("Le prof: "+s.getProf()+" donne le cours de "+s.module.getIntitule()+" a "+s.caseHoraire.getNom()+" le "+s.jour.toString()+" au "+ s.groupe.getNomGroupe()+" dans la salle "+s.salle.getNom()));
		
	}
	
	/**
	 * methode afficherSeanceGroupe() permet d'afficher la liste des seances d'un groupe d'etudiant.  
	 * @param un str qui est le groupe spécifique de l'etudiant.
	 * @param une liste de Seance.
	 */
	public void afficherSeanceGroupe(String str, List<Seance> l){
		l.stream()
		.filter(s->str.equals(s.getGroupe()))
		.forEach(s->System.out.println("Le prof: "+s.getProf()+" donne le cours de "+s.module.getIntitule()+" a "+s.caseHoraire.getNom()+" au "+ s.groupe.getNomGroupe()+" dans la salle "+s.salle.getNom()));
	}
	
	/**
	 * methode afficherSalleEnseignant() permet d'afficher les salles occupées par un prof.  
	 * @param un str qui est le nom du prof.
	 * @param une liste de seance.
	 */
	public static void afficherSalleEnseignant(String str,List<Seance> listeSeance) {
		
		listeSeance	.stream()
					.filter(s->str.equals(s.getProf()))
					.forEach(s->System.out.println(s.getSalle()));
		
	}	
	
	/**
	 * methode afficherSalleGroupe() permet d'afficher les salle occupee par un groupe d'etudiant.  
	 * @param un str qui est le nom d'un groupe d'étudiant. 
	 * @param une liste de seance.
	 */
	public static void afficherSalleGroupe(String str,List<Seance> listeSeance) {
		
		listeSeance .stream()
				   	.filter(s->str.equals(s.getGroupe()))
				   	.forEach(s->System.out.println(s.getSalle()));
		
		
	}
		
	/**
	 * methode salleLibreCreneau () permet d'afficher les salles disponibles dans un creneau donné. 
	 * @param un str qui correspond a une case horaire.
	 * @param une map de salle. 
	 * @param une liste de Seance.
	 * @param un jour.
	 * @return  Map de salle libre dans un créneau donnée.
	 */
	public Map<String, String> salleLibreCreneau (String str,Map<String, String> m, List<Seance> listeSeance, String j) {
		
		Map<String, String> mapDispoCreneau = new HashMap<>();
		mapDispoCreneau= m;
		for(Seance s: listeSeance){
			if(str.equals(s.caseHoraire.getNom()) && j.equals(s.jour.toString())){
				mapDispoCreneau.remove(s.salle.nomString);
				
			}
		}
		return mapDispoCreneau;
		
	}
	public Seance StringToSeance(String str){
		Seance s = new Seance();
		
		return s;
		
	}
	
	
	/**
	 * methode ajouterSeance() permet d'ajouter une seance a la liste des seances deja existantes.
	 * @param une seance.
	 * @return une Liste de seance.
	 */

public List<Seance> ajouterSeance(Seance s, List<Seance> listeSeance) {
	listeSeance.add(s);
	return listeSeance;
}

/**
 * methode supprimerSeance() permet de supprimer une seance dans la liste de seance.
 *  @param un creneau.
 * @param un jour.
 *  @param une Liste seance.
 * @return une Liste de seance.
 */
public List<Seance> supprimerSeance(String creneau, String jour, List<Seance> listeSeance) {
	Seance tmp= new Seance();
	for(Seance s:listeSeance){
		if((creneau.equals(s.caseHoraire.getNom())) && (jour.equals(s.jour.toString()))){
			tmp= s;
		}
			
	}
	listeSeance.remove(tmp);
	return listeSeance;
}
public String getSalle() {
	return salle.getNom();
}
/**
 * methode afficherSeanceProf() permet d'afficher les seances occupées par un prof.
 * @param un str qui correspnt au nom d'un prof.
 * @return une Liste de seance.
 */
public void afficherSeanceProf(String str, List<Seance> l) {
	l.stream()
	.filter(s->str.equals(s.getProf()))
	.forEach(s->System.out.println("Le prof: ||"+s.getProf()+"|| donne le cours de|| "+s.module.getIntitule()+" a|| "+s.caseHoraire.getNom()+" au|| "+ s.groupe.getNomGroupe()+" dans la salle ||"+s.salle.getNom()));
	
}
	

	
}
