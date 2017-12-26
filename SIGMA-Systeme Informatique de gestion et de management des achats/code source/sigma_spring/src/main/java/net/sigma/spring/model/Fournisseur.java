package net.sigma.spring.model;

public class Fournisseur {
	private int id;
	private String name;
	private String email;
	private String address;
	private String telephone;
	private int num_siret;
	private String code_ape;
	private String libelle_code_ape;
	private String raison_social;
	private String nom_societe;
	private int date_creation;
	private String type_achat;
	private int code_famille;
	private String libelle_famille;
	private String site_web;
	private String adresse_societe;
	private int code_postal;
	private String ville_societe;
	private String logo;
	private int score;

	public Fournisseur() {
		super();
	}

	public Fournisseur(String name, String email, String address, String telephone) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getNum_siret() {
		return num_siret;
	}

	public void setNum_siret(int num_siret) {
		this.num_siret = num_siret;
	}

	public String getCode_ape() {
		return code_ape;
	}

	public void setCode_ape(String code_ape) {
		this.code_ape = code_ape;
	}

	public String getLibelle_code_ape() {
		return libelle_code_ape;
	}

	public void setLibelle_code_ape(String libelle_code_ape) {
		this.libelle_code_ape = libelle_code_ape;
	}

	public String getRaison_social() {
		return raison_social;
	}

	public void setRaison_social(String raison_social) {
		this.raison_social = raison_social;
	}

	public String getNom_societe() {
		return nom_societe;
	}

	public void setNom_societe(String nom_societe) {
		this.nom_societe = nom_societe;
	}

	public int getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(int date_creation) {
		this.date_creation = date_creation;
	}

	public String getType_achat() {
		return type_achat;
	}

	public void setType_achat(String type_achat) {
		this.type_achat = type_achat;
	}

	public int getCode_famille() {
		return code_famille;
	}

	public void setCode_famille(int code_famille) {
		this.code_famille = code_famille;
	}

	public String getLibelle_famille() {
		return libelle_famille;
	}

	public void setLibelle_famille(String libelle_famille) {
		this.libelle_famille = libelle_famille;
	}

	public String getSite_web() {
		return site_web;
	}

	public void setSite_web(String site_web) {
		this.site_web = site_web;
	}

	public String getAdresse_societe() {
		return adresse_societe;
	}

	public void setAdresse_societe(String adresse_societe) {
		this.adresse_societe = adresse_societe;
	}

	public int getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(int code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille_societe() {
		return ville_societe;
	}

	public void setVille_societe(String ville_societe) {
		this.ville_societe = ville_societe;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
