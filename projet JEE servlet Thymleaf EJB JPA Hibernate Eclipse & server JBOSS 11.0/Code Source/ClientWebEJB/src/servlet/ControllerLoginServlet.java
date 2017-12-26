package servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.IUserLocal;
import javax.servlet.http.HttpSession;

@WebServlet(name="csLogin", urlPatterns={"/login"})
public class ControllerLoginServlet extends HttpServlet{
		
	@EJB
	private IUserLocal user;
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
			doPost(req, resp);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
			String action=req.getParameter("action");
			if (action != null) {
				if (action.equals("Connexion")) {
					String email = req.getParameter("email");
					String error;
					if(!email.isEmpty()) {
							if(user.verify(email)) {
								HttpSession session = req.getSession();
								resp.sendRedirect(req.getContextPath()+"/accueil");
							}
								error = "erreur 02 : le compte n'existe pas ";
								req.setAttribute("error", error);
					}else if(email.isEmpty()) {
							error = "erreur 01 : veuillez saisir votre adresse mail ";
							req.setAttribute("error", error);
					}
				} 
			}
			req.getRequestDispatcher("Login.jsp").forward(req, resp);
		}
	}


