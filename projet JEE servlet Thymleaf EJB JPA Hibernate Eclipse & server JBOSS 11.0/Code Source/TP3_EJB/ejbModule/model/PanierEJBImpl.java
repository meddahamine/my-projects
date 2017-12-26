package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful(name="PANIER")
public class PanierEJBImpl implements IPanierRemote{

	@PersistenceContext(unitName="UP_ART")
	private EntityManager em;
	@EJB
	private IArticleLocal article;
	
	@Override
	public void addArticleToPanier(int ida, int idp) {
		Panier pan = getPanierById(idp);
		pan.addArticle(article.getArticle(ida));
		em.merge(pan);		
	}

	@Override
	public void removeArticleInPanier(int ida, int idp) {
		Panier pan = getPanierById(idp);
		pan.deleteArticle(ida);		
		em.merge(pan);
	}

	@Override
	public void createPanier(String nom) {
		Panier pan = new Panier(nom);
		em.persist(pan);		
	}

	@Override
	public void deletePanier(int idp) {
		Panier p = getPanierById(idp);
		p.setArticles(null);
		em.remove(em.contains(p) ? p : em.merge(p));		
	}

	@Override
	public double totalPriceOfPanier(int idp) {
		Panier pan = this.getPanierById(idp);
		List<Article> articles = new ArrayList<Article>();
		articles = pan.getArticles();
		double prix_total = 0;
		for (Iterator<Article> iterator = articles.iterator(); iterator.hasNext();) {
		    Article a =  iterator.next();
		    prix_total += a.getPrix();       
		}
		return prix_total;
	}

	@Override
	public List<Article> listArticleInPanier(int idp) {
		Panier pan = this.getPanierById(idp);
		List<Article> listArticle= pan.getArticles();
		return listArticle;
	}
	
	@Override
	public List<Article> cleanListArticleInPanier(int idp) {
		Panier pan = this.getPanierById(idp);
		List<Article> listArticle= pan.cleanPanier();
		return listArticle;
	}

	@Override
	public Panier getPanierById(int id) {
		Panier p = em.find(Panier.class, id);
		if (p==null) throw new RuntimeException("Article introvable !!");
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Panier> getAllPaniers() {
		Query req = em.createQuery("from Panier");
		return req.getResultList();
	}
	
	
}
