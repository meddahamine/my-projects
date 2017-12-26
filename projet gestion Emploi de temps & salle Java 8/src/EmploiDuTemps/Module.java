package EmploiDuTemps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * classe Module.
 */
public class Module implements InterfaceCreation {
	List<Module> liste=new ArrayList<>();
	private String intitule;
	private String type;
	
	
	public Module(){}
	public Module(String intitule, String type){
		this.intitule= intitule;
		this.type= type;
		
	}
	public Module(String str2) {
		this.intitule= str2;
	}
	public String getIntitule(){
		return intitule;
	}
	public String getTypeModule(){
		return type;
	}
	
	/**
	 * methode creerListeModuleFinale() permet de  creer la liste des modules obligatoires et le module choisi par l'etudiant.
	 * @param une Liste de Module.
	 * @return une Liste de Module.
	 */
	public List<Module> creerListeModuleFinale(List<Module> listeObligatoire, Module module){
		
		listeObligatoire.add(module);
		return listeObligatoire;

	}
}
