package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlbumDao;
import dao.AlbumDaoImpl;
import dao.TitreMusicalDao;
import dao.TitreMusicalDaoImpl;
import dao.TypeSupportDao;
import dao.TypeSupportDaoImpl;
import model.TitreMusical;

public class ControllerTitreMusicalServlet extends HttpServlet{

	private TitreMusicalDao titremusicaldao;
	private TypeSupportDao typesupportdao;
	private AlbumDao albumdao;
	@Override
	public void init() throws ServletException {
		titremusicaldao = new TitreMusicalDaoImpl();
		typesupportdao = new TypeSupportDaoImpl();
		albumdao = new AlbumDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null) {
			if (action.equals("Save")) {
				String titre = req.getParameter("titre");
				int idTypeSupport = Integer.parseInt(req.getParameter("sel1"));
				int idAlbum = Integer.parseInt(req.getParameter("sel2"));
				titremusicaldao.save(new TitreMusical(titre, idTypeSupport, idAlbum));
			}else if (action.equals("supp")) {
				int id = Integer.parseInt(req.getParameter("id"));
				titremusicaldao.delete(id);
			}else if (action.equals("edit")) {
				int id = Integer.parseInt(req.getParameter("id"));
				TitreMusical tm = titremusicaldao.findById(id);
				req.setAttribute("titreMusical", tm);
			}else if (action.equals("Update")) {
				int id = Integer.parseInt(req.getParameter("id"));
				String titre = req.getParameter("titre");
				int idTypeSupport = Integer.parseInt(req.getParameter("sel1"));
				int idAlbum = Integer.parseInt(req.getParameter("sel2"));
				TitreMusical tm = new TitreMusical(titre, idTypeSupport, idAlbum);
				tm.setId(id);
				titremusicaldao.update(tm);
			}
		}
		req.setAttribute("titreMusicals", titremusicaldao.list());
		req.setAttribute("typeSupports", typesupportdao.list());
		req.setAttribute("albums", albumdao.list());
		req.getRequestDispatcher("vues/titreMusical.jsp").forward(req, resp);
	}
}
