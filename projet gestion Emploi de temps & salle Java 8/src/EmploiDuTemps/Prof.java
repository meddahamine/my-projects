package EmploiDuTemps;

import java.util.List;
/**
 * classe Prof.
 */
public class Prof extends Personne {
	private  String module;
	
	
	public Prof(){}
	public Prof(String nom){
		super(nom);
	}
	
	public Prof(String nom,String prenom, String identifiant,String module){
		super(nom, prenom, identifiant);
		this.module= module;
	}
	
	public String getCours(){
		return module;
	}
	

}
