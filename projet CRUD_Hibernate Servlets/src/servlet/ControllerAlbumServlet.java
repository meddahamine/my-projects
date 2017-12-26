package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlbumDao;
import dao.AlbumDaoImpl;
import dao.ArtisteDao;
import dao.ArtisteDaoImpl;
import model.Album;
import model.TypeSupport;

public class ControllerAlbumServlet extends HttpServlet{

	private AlbumDao albumdao;
	private ArtisteDao artsitedao;
	@Override
	public void init() throws ServletException {
		albumdao = new AlbumDaoImpl();
		artsitedao = new ArtisteDaoImpl();
	}
	
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
			if (action.equals("Save")) {
				String nom=req.getParameter("nom");
				int idArtiste=Integer.parseInt(req.getParameter("sel"));
				albumdao.save(new Album(nom, idArtiste));
			}else if (action.equals("supp")) {
				int id = Integer.parseInt(req.getParameter("id"));
				albumdao.delete(id);
			}else if (action.equals("edit")) {
				int id = Integer.parseInt(req.getParameter("id"));
				Album al = albumdao.findById(id);
				req.setAttribute("album", al);
			}else if (action.equals("Update")) {
				int id = Integer.parseInt(req.getParameter("id"));
				String nom=req.getParameter("nom");
				int idArtiste=Integer.parseInt(req.getParameter("sel"));
				Album ts = new Album(nom, idArtiste);
				ts.setId(id);
				albumdao.update(ts);
			}
		}
		req.setAttribute("albums", albumdao.list());
		req.setAttribute("artistes", artsitedao.list());
		req.getRequestDispatcher("vues/album.jsp").forward(req, resp);
	}
}
