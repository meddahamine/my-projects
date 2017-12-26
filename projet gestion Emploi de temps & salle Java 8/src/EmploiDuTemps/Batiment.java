package EmploiDuTemps;
import java.util.ArrayList;
import java.util.List;

/**
 * classe Batiment.
 */

public class Batiment extends BatimentBuilder{
	private final String nom;
	List<Salles> salleBatiment = new ArrayList<>();
	int nbEtage;
	String faculte;
	
	
	public Batiment(BatimentBuilder batiment){
		this.nom= batiment.nom;
		this.salleBatiment= batiment.salleBatiment;
		this.nbEtage= batiment.nbEtage;
		this.faculte= batiment.faculte;
	}
	
	
	public Batiment(String nom) {
		this.nom= nom;
	}
	
	/**
	 * méthode afficheLiseteBatiment() qui affiche tous les batiments de l'université. 
	 * @param une liste de batiment. 
	 */
	public void afficheListeBatiment(List<Batiment> l){
		for(Batiment b: l)
			System.out.println(b.nom);
	}
	
	/**
	 * méthode ajouterBatiment() qui permet d'ajouter un batiment à la liste des batiment de l'universite.
	 * @param une liste de Batiment. 
	 * @param un Batiment.
	 * @retun une liste de Batiment. 
	 */
	public List<Batiment> ajouterBatiment(List<Batiment> l, Batiment b){
		l.add(b);
		return l;
	}
	/**
	 * methode afficheinfoBatiment() qui affiche les détail des batiments de l'université. 
	 * @param une liste de Batiment. 
	 */
	public 	void afficheInfoBatiment(List<Batiment> l){
		l.stream()
		.forEach(s->System.out.println(s.nom+" "+" "+s.getListe()+" "+s.nbEtage+" "+ s.faculte));
		
	}
	
	private List<String> getListe() {
		List<Salles> l= new ArrayList<>();
		List<String> l2= new ArrayList<>();
		l= salleBatiment;
		for(Salles s: l){
			 l2.add(s.getNom());
		}
		return l2;
	}
	
}
