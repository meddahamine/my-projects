package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="csAccueil", urlPatterns={"/accueil"})
public class ControllerAccueilServlet extends HttpServlet{

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
				if (action.equals("gererPanier")) {
							resp.sendRedirect(req.getContextPath()+"/panier");
				}if (action.equals("gererArticle")) {
					resp.sendRedirect(req.getContextPath()+"/article");
				}if (action.equals("Exit")) {
					req.getSession().setAttribute("userEmail", null);
					resp.sendRedirect(req.getContextPath()+"/login");
				}  
			}
			req.getRequestDispatcher("Accueil.jsp").forward(req, resp);
		}
}
