package model;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IArticleRemote {

	void addArticle(Article a);
	List<Article> getAllArticles();
	Article getArticle(int idA);
	void removeArticle(int idA);
	void updateArticle(Article a);
}
