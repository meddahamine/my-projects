package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="article")
public class Article implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="prix")
	private double prix;
	@Column(name="libelle")
	private String libelle;
	
	public Article(double prix, String libelle) {
		super();
		this.prix = prix;
		this.libelle = libelle;
	}
	public Article() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
