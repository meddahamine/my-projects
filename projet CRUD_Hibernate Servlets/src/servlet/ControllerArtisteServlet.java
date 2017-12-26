package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArtisteDao;
import dao.ArtisteDaoImpl;
import model.Artiste;

public class ControllerArtisteServlet extends HttpServlet{

	private ArtisteDao artistedao;
	@Override
	public void init() throws ServletException {
		artistedao = new ArtisteDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String action=req.getParameter("action");
		if (action != null) {
			if (action.equals("Save")) {
				String nom = req.getParameter("nom");
				artistedao.save(new Artiste(nom));
			}else if (action.equals("supp")) {
				int id = Integer.parseInt(req.getParameter("id"));
				artistedao.delete(id);
			}else if (action.equals("edit")) {
				int id = Integer.parseInt(req.getParameter("id"));
				Artiste ar = artistedao.findById(id);
				req.setAttribute("artiste", ar);
			}else if (action.equals("Update")) {
				int id = Integer.parseInt(req.getParameter("id"));
				String nom = req.getParameter("nom");
				Artiste ar = new Artiste(nom);
				ar.setId(id);
				artistedao.update(ar);
			}
		}
		req.setAttribute("artistes", artistedao.list());
		req.getRequestDispatcher("vues/artiste.jsp").forward(req, resp);
	}
}
