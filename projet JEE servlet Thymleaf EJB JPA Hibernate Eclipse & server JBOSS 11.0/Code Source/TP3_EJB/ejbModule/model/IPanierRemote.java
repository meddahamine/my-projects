package model;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

@Remote
public interface IPanierRemote {
	
	List<Panier> getAllPaniers();
	void addArticleToPanier(int ida, int idp);
	void removeArticleInPanier(int ida, int idp);
	void createPanier(String nom);
	void deletePanier(int idp);
	double totalPriceOfPanier(int idp);
	List<Article> listArticleInPanier(int idp);	
	List<Article> cleanListArticleInPanier(int idp);	
	Panier getPanierById(int id);
}
