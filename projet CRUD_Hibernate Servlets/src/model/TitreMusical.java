package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="titremusical")
public class TitreMusical implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TitreMusical(String titre, Integer idType, Integer idAlbum) {
		super();
		this.titre = titre;
		this.idType = idType;
		this.idAlbum = idAlbum;
	}

	public TitreMusical() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="titre")
	private String titre;
	
	@Column(name="idType")
	private Integer idType;
	
	@Column(name="idAlbum")
	private Integer idAlbum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public Integer getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Integer idAlbum) {
		this.idAlbum = idAlbum;
	}

}