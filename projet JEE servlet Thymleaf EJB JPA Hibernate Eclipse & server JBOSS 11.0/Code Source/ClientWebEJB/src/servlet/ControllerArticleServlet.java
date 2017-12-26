package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;
import model.Article;
import model.IArticleLocal;
import model.IArticleRemote;

@WebServlet(name="csArticle", urlPatterns={"/article"})
public class ControllerArticleServlet extends HttpServlet{

	//injecction des dependence 
	@EJB
	private IArticleRemote article;
	private static final Logger log = Logger.getLogger(ControllerArticleServlet.class
			  .getName());
	//si on utlise pas l'annotation l'injection se fait dans le constructeur
	/*ControllerArticleServlet(){
		try {
			Context ctx = new InitialContext();
			article = (IArticleLocal) ctx.lookup("ejb:/TP3_EJB/ART!model.IArticleLocal");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String action=req.getParameter("action");
		if ((action != null) && (req.getSession()!=null)) {
			if (action.equals("Save")) {
				double prix=Double.parseDouble(req.getParameter("prix"));
				String libelle=req.getParameter("libelle");
				article.addArticle(new Article(prix, libelle));
			}else if (action.equals("Supp")) {
				log.error("This is an error message");
				int id = Integer.parseInt(req.getParameter("id"));
				log.error("This id="+id);
				article.removeArticle(id);
			}
			else if (action.equals("Edit")) {
				int id = Integer.parseInt(req.getParameter("id"));
				Article ar = article.getArticle(id);
				req.setAttribute("article", ar);
			}else if (action.equals("Update")) {
				int id = Integer.parseInt(req.getParameter("id"));
				double prix=Double.parseDouble(req.getParameter("prix"));
				String libelle =req.getParameter("libelle");
				Article ar = new Article(prix, libelle);
				ar.setId(id);
				log.error("This id="+ar.getId());
				article.updateArticle(ar);
			}
		}
		List<Article> listArticle= article.getAllArticles();
		req.setAttribute("Articles", listArticle);
		req.getRequestDispatcher("Article.jsp").forward(req, resp);
	}
}
