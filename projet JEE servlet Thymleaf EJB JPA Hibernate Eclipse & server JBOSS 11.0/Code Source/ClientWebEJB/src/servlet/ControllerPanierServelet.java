package servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;
import model.Article;
import model.IArticleLocal;
import model.IPanierRemote;
import model.Panier;

@WebServlet(name="csPanier", urlPatterns={"/panier"})
public class ControllerPanierServelet extends HttpServlet{
	private static final Logger log = Logger.getLogger(ControllerPanierServelet.class.getName());
	@EJB
	private IPanierRemote panier;
	@EJB
	private IArticleLocal article;
	
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
				String nom_panier= req.getParameter("nom");
				panier.createPanier(nom_panier);
			} else if (action.equals("Supp")) {
				int id = Integer.parseInt(req.getParameter("id"));
				panier.deletePanier(id);
			}else if (action.equals("Vider")) {
				int idp = Integer.parseInt(req.getParameter("idp"));
				req.setAttribute("CleanPanier", panier.cleanListArticleInPanier(idp));
				req.setAttribute("idp", req.getParameter("idp"));
			}
			else if (action.equals("Edit_add")) {
				List<Article> listArticle= this.article.getAllArticles();
				req.setAttribute("Articles", listArticle);
				req.setAttribute("idp", req.getParameter("idp"));				
			}
			else if (action.equals("Edit_supp")) {
				int idp = Integer.parseInt(req.getParameter("idp"));
				req.setAttribute("ArticlesIn", panier.listArticleInPanier(idp));
				req.setAttribute("idp", req.getParameter("idp"));				
			}
			else if (action.equals("UPDATE_ADD")) {
				int ida = Integer.parseInt(req.getParameter("ida"));
				int idp = Integer.parseInt(req.getParameter("idp"));
				panier.addArticleToPanier(ida, idp);
			}
			else if (action.equals("UPDATE_SUPP")) {
				int ida = Integer.parseInt(req.getParameter("ida"));
				int idp = Integer.parseInt(req.getParameter("idp"));
				panier.removeArticleInPanier(ida, idp); 
			}
			else if (action.equals("GET_TOTAL")) {
				int idp = Integer.parseInt(req.getParameter("idp"));
				req.setAttribute("PrixTotal", Double.toString(panier.totalPriceOfPanier(idp)));
				req.setAttribute("id_panier", req.getParameter("idp"));
			}
		}
		List<Panier> listPanier= panier.getAllPaniers();
		req.setAttribute("Paniers", listPanier);
		req.getRequestDispatcher("Panier.jsp").forward(req, resp);
	}
}
