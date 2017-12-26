package model;

import java.util.List;

import javax.ejb.Local;

@Local
public interface IArticleLocal {

	void addArticle(Article a);
	List<Article> getAllArticles();
	Article getArticle(int idA);
	void removeArticle(int idA);
	void updateArticle(Article a);
}
