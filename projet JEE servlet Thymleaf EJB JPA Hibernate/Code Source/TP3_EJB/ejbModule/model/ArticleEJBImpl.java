package model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//EJB SESSION
@Stateless(name="ART")
public class ArticleEJBImpl implements IArticleRemote, IArticleLocal{

	//interface JPA 
	@PersistenceContext(unitName="UP_ART")
	private EntityManager em;
	
	@Override
	public void addArticle(Article a) {
		em.persist(a);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticles() {
		Query req = em.createQuery("from Article");
		return req.getResultList();
	}

	@Override
	public Article getArticle(int idA) {
		Article a = em.find(Article.class, idA);
		if (a==null) throw new RuntimeException("Article introvable !!");
		return em.find(Article.class, idA);
	}

	@Override
	public void removeArticle(int idA) {
		Article a = em.find(Article.class, idA);
		if (a==null) throw new RuntimeException("Article introvable !!");
		Query req = em.createQuery("delete from Article where id="+idA);
		req.executeUpdate();
	}

	@Override
	public void updateArticle(Article a) {
		Query req = em.createQuery("update Article set prix="+
									a.getPrix()+",libelle='"+
									a.getLibelle()+"' where id="+
									a.getId());
		req.executeUpdate();
	}

}
