package EmploiDuTemps;
import java.util.ArrayList;
import java.util.List;

/**
 * classe BatimentBuilder.
 */
public class BatimentBuilder {
	protected String nom;
	public List<Salles> salleBatiment = new ArrayList<>();
	public int nbEtage;
	public String faculte;
	
	public BatimentBuilder (){
	
	}
	public BatimentBuilder (String nom){
		this.nom= nom;
		
	}
	public BatimentBuilder salleBatiment(List<Salles> salleBatiment){
		this.salleBatiment= salleBatiment ;
		return this;
	}
	public BatimentBuilder nbEtage(int nbEtage){
		this.nbEtage= nbEtage;
		return this;
	}
	public BatimentBuilder faculte(String faculte){
		this.faculte= faculte;
		return this;
	}
	/**
	 * methode build() Permet de creer de nouveaux batiments dans l'université. 
	 * @return un Batiment.
	 */
	 public Batiment build() {
            return new Batiment(this);
        }
}
