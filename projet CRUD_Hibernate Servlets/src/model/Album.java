package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="album")
public class Album implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Album(String nom, Integer idArtiste) {
		super();
		this.nom = nom;
		this.artisteID = idArtiste;
	}

	public Album() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="idArtiste")
	private Integer artisteID;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getartisteID() {
		return artisteID;
	}

	public void setartisteID(Integer idArtist) {
		this.artisteID = idArtist;
	}	

}