package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="panier")
public class Panier implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_panier")
	private int id_panier;
	@Column(name="nom_panier")
	private String nom_panier;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "contenu", joinColumns = {
			@JoinColumn(name = "id_panier", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "id_article", nullable = false, updatable = false) })
	private List<Article> articles = new ArrayList<Article>(0);
	
	public Panier(String nom_panier, List<Article> articles) {
		this.nom_panier = nom_panier;
		this.articles = articles;
	}
	
	public Panier(String nom_panier) {
		this.nom_panier = nom_panier;
	}
	
	public Panier() {
	}

	public void deleteArticle(int id){
		for (Iterator<Article> iterator = this.articles.iterator(); iterator.hasNext();) {
		    Article a =  iterator.next();
		    if (a.getId()==id) {
		        iterator.remove();
		        return;
		    }       
		}
	}
	
	public void addArticle(Article a){
		this.articles.add(a);
	}
	
	public List<Article> cleanPanier(){
		this.articles.clear();
		return articles;
	}
	
	public int getId_panier() {
		return id_panier;
	}
	public void setId_panier(int id_panier) {
		this.id_panier = id_panier;
	}
	public String getNom_panier() {
		return nom_panier;
	}
	public void setNom_panier(String nom_panier) {
		this.nom_panier = nom_panier;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
