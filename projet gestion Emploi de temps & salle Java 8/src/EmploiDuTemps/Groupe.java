package EmploiDuTemps;

import java.util.List;
/**
 * classe Groupe.
 */
public class Groupe{
	private String nom;
	private List<Etudiant> listeEtudiant;
	
	
	
	public Groupe(){}
	
	public Groupe(String nom, List<Etudiant> listeEtudiant ){
		this.nom= nom;
		this.listeEtudiant =listeEtudiant;
		
	}

	public Groupe(String str4) {
		this.nom= str4;
	}

	public String getNomGroupe() {
		return nom;
	}
	
	
}