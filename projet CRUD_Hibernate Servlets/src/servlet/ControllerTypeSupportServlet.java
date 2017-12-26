package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TypeSupportDao;
import dao.TypeSupportDaoImpl;
import model.TypeSupport;

public class ControllerTypeSupportServlet extends HttpServlet{

	private TypeSupportDao typesupportdao;
	@Override
	public void init() throws ServletException {
		typesupportdao = new TypeSupportDaoImpl();
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
				String type = req.getParameter("type");
				typesupportdao.save(new TypeSupport(type));
			}else if (action.equals("supp")) {
				int id = Integer.parseInt(req.getParameter("id"));
				typesupportdao.delete(id);
			}else if (action.equals("edit")) {
				int id = Integer.parseInt(req.getParameter("id"));
				TypeSupport ts = typesupportdao.findById(id);
				req.setAttribute("typeSupport", ts);
			}else if (action.equals("Update")) {
				int id = Integer.parseInt(req.getParameter("id"));
				String type = req.getParameter("type");
				TypeSupport ts = new TypeSupport(type);
				ts.setId(id);
				typesupportdao.update(ts);
			}
		}
		req.setAttribute("typeSupports", typesupportdao.list());
		req.getRequestDispatcher("vues/typeSupport.jsp").forward(req, resp);
	}
}
