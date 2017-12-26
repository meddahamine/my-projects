package EmploiDuTemps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.Messaging.SyncScopeHelper;
/**
 * classe Salles.
 */
public class Salles implements InterfaceCreation{
	protected int num;
	protected String batiment;
	protected int etage;
	public String nomString;
	private String type;
	public Salles(){}
	
	public Salles(String batiment, int etage,int num){
		this.num= num;
		this.batiment= batiment;
		this.etage =etage;
	}
	public Salles(String str3, String type) {
		this.nomString= str3;
		this.type = type;
	}

	public int getNum(){
		return num;
	}
	public String getBatiment(){
		return batiment;
	}
	public int getEtage(){
		return etage;
	}
	public String getChamps(){
		return getBatiment()+ getEtage()+getNum();
	}
	
		
	/**
	 * methode  creerMapSalleDispo()  remmet la liste des salles de l'université à disponible. 
	 * @param une Liste de Salles.
	 * @return une Map de salle avec le mot disponible comme valeur.
	 */
public Map<String, String> creerMapSalleDispo(List<Salles> liste){
	Map<String, String> mapSalle = new HashMap<>();
	for(Salles s: liste){
		mapSalle.put(s.nomString, "disponible");
	}
	return mapSalle;
} 

/**
 * methode  afficheMapSalle()  affiche les salle avec comme indication disponible ou non disponible.  
 * @param une Map de Salles.
 */
public void afficheMapSalle(Map<String, String> mapSalle){
		mapSalle.forEach((k, v)->{System.out.println(k+" "+v);});
		
	
	
}

/**
 * methode  afficheMapSalle2()  affiche juste les salles de la map.  
 * @param une Map de Salles.
 */
public void afficheMapSalle2(Map<String, String> mapSalle){
	mapSalle.forEach((k, v)->{System.out.println(k);});


}
/**
 * methode afficheSalleType()  affiche le type des salles.
 * @param une lsite de Salle.
 */
public void afficheSalleType(List<Salles> listSalle){
	for(Salles s : listSalle)
		System.out.println(s.nomString+" "+s.getType());


}
private String getType() {
	return type;
}

/**
 * methode modifSalleDispo() permet de modiffier une salle de la map qui a été résérvé, en la remettant à non disponible.
 * @param une Map de salle.
 * @param une salle.
 * @return une Map de salle.
 */
public Map<String, String> modifSalleDispo(Map<String, String> m, Salles salle ) {
	
	String s= m.get(salle.nomString);
	s = "non disponible";
	m.put(salle.nomString, s);
	return m;
}

/**
 * methode salleDispo() permet d'afficher les salles disponibles. 
 * @param une Map de salle.
 */
public void salleDispo(Map<Salles, String> m){
	m.forEach((k, v)->{if (v =="disponible") System.out.println(k.batiment+k.etage+k.num+" "+v);});
	
}

/**
 * methode ajouterSalle() permet d'ajouter une salle à la liste de salle.  
 * @param une salle.
 * @param une liste de Salle.
 * @return une liste de Salle. 
 */
public List<Salles> ajouterSalle(Salles s, List<Salles> listeSalle) {
	listeSalle.add(s);
	return listeSalle;
}

/**
 * methode supprimerSalle() permet de supprimer une salle dans la liste de salle.  
 * @param une Salle.
 * @param une liste de Salle.
 * @return une liste de Salle. 
 */
public List<Salles> supprimerSalle(Salles s, List<Salles> listeSalle) {
	listeSalle.remove(s);
	return listeSalle;
}

public String getNom() {
	return nomString;
}


	

}
