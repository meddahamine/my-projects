package EmploiDuTemps;

/**
 * classe Personne.
 */
public abstract class  Personne {
	public String nom;
	protected String prenom;
	public String identifiant;
	
	public Personne(){
		
	}
public Personne(String nom){
		this.nom= nom;
	}
	public Personne(String nom,String prenom, String identifiant ){
		this.identifiant= identifiant;
		this.prenom= prenom;
		this.nom= nom;
	}
	public void setNom(String nom){
		this.nom=nom;
	}
	public void getNom(String nom){
		this.nom=nom;
	}
	
	public void setPrenom(String prenom){
		this.prenom=prenom;
	}
	
	
	public String getNom(){
		return nom;
	}
	public String getPrenom(){
		return prenom;
	}
	public String getIdentifiant(){
		return identifiant;
	}
	
}
