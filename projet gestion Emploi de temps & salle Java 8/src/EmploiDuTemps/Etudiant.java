package EmploiDuTemps;

import java.util.List;

/**
 * classe Etudiant.
 */
public class Etudiant extends Personne{
	private String filiere;
	private String niveau;
	private List<Module> listeModule;
	
	public Etudiant(){}
	public Etudiant(String nom,String prenom, String identifiant,String filiere, String niveau, List<Module> listeModule){
		super(nom, prenom, identifiant);
		this.filiere= filiere;
		this.niveau= niveau;
		this.listeModule= listeModule;
	}
	
	public String getFiliere(){
		return filiere;
	}
	public String getNiveau(){
		return niveau;
	}
	/**
	 * methode ajouterModule() permet d'ajouter un module à une  liste de modules. 
	 * @param un Module.
	 * @param  une Liste de Module.
	 * @return une liste de module modifiée.
	 */
	public List<Module> ajouterModule(Module s, List<Module> listeModule) {
		listeModule.add(s);
		return listeModule;
	}
	/**
	 * methode ajouterEtudiant() permet d'ajouter un etudiant à  la liste des étudiants.
	 * @param un Etudiant.
	 * @param une Liste d'étudiant.
	 * @return une Liste d'étudiant.
	 */
	public List<Etudiant> ajouterEtudiant(Etudiant e, List<Etudiant> listeEtudiant) {
		listeEtudiant.add(e);
		return listeEtudiant;
	}
	
	
}
