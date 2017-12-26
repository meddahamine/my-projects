package net.sigma.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.sigma.spring.dao.IAdminDAO;
import net.sigma.spring.dao.IArchivesDAO;
import net.sigma.spring.dao.IFournisseurDAO;
import net.sigma.spring.dao.IUserDAO;
import net.sigma.spring.model.Admin;
import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;

public class ControllerFactory {

	@Autowired
	protected IFournisseurDAO fournisseurDAO;
	
	@Autowired
	protected IAdminDAO adminDAO;
	
	@Autowired
	protected IArchivesDAO archivesDAO;
	
	@Autowired
	protected IUserDAO userDAO;
	
	protected String userType;
	
	public ModelAndView espaceUser(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			model.addObject("success", "Connexion Valide");
			model.addObject("page_title", userType+" | Accueil");
			model.addObject("page_name", "accueil");
			model.setViewName("index");
		}
			return model;
	}
	
	/***********************************Fournisseur*************************************************/
	public ModelAndView listFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			List<Fournisseur> listFournisseur = fournisseurDAO.list();
			model.addObject("listFournisseur", listFournisseur);
			model.addObject("page_title", userType+" | Gestion Fournisseur");
			model.addObject("page_name", "gestionFournisseur");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView newFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			model.addObject("page_title", userType+" | Ajouter Fournisseur");
			model.addObject("page_name", "fournisseurForm");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView editFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int fournisseurId = Integer.parseInt(request.getParameter("id"));
			model.addObject("fournisseur", fournisseurDAO.get(fournisseurId));
			String u="Fournisseur";
			if (user.getType().equals(u)) {
				model.addObject("fournisseurUser", userDAO.get(fournisseurId));
			}
			model.addObject("page_title", userType+" | Modifier Fournisseur");
			model.addObject("page_name", "fournisseurUpdate");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView saveFournisseur( HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			User u = new User();
			u.setUserName(request.getParameter("userName"));
			u.setPassword(request.getParameter("password"));
			u.setType("Fournisseur");
			userDAO.addUser(u);
			Fournisseur f = new Fournisseur();
			f.setId(userDAO.getLastId());
			f.setName(request.getParameter("name"));
			f.setAddress(request.getParameter("address"));
			f.setEmail(request.getParameter("email"));
			f.setTelephone(request.getParameter("telephone"));
			fournisseurDAO.save(f);	
		}
		return new ModelAndView("redirect:/"+userType+"/gestionFournisseur");
	}
	
	public ModelAndView updateFournisseur( HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			Fournisseur f = new Fournisseur();
			f.setId(Integer.parseInt(request.getParameter("id")));
			f.setName(request.getParameter("name"));
			f.setAddress(request.getParameter("address"));
			f.setEmail(request.getParameter("email"));
			f.setTelephone(request.getParameter("telephone"));
			
			String ch="Fournisseur";
			if (user.getType().equals(ch)) {
				f.setNum_siret(Integer.parseInt(request.getParameter("num_siret")));
				f.setCode_ape(request.getParameter("code_ape"));
				f.setLibelle_code_ape(request.getParameter("libelle_code_ape"));
				f.setRaison_social(request.getParameter("raison_social"));
				f.setNom_societe(request.getParameter("nom_societe"));
				f.setDate_creation(Integer.parseInt(request.getParameter("date_creation")));
				f.setType_achat(request.getParameter("type_achat"));
				f.setCode_famille(Integer.parseInt(request.getParameter("code_famille")));
				f.setLibelle_famille(request.getParameter("libelle_famille"));
				f.setSite_web(request.getParameter("site_web"));
				f.setAdresse_societe(request.getParameter("adresse_societe"));
				f.setCode_postal(Integer.parseInt(request.getParameter("code_postal")));
				f.setVille_societe(request.getParameter("ville_societe"));
			
				User u = new User();
				u.setId(Integer.parseInt(request.getParameter("idUser")));
				u.setUserName(request.getParameter("username"));
				u.setPassword(request.getParameter("password"));
				u.setType(userType);
				userDAO.saveOrUpdate(u);
				user.setUserName(request.getParameter("username"));
				return new ModelAndView("redirect:/"+userType+"/accueil");
			}
			
			fournisseurDAO.update(f);
		}
		return new ModelAndView("redirect:/"+userType+"/gestionFournisseur");
	}
	
	public ModelAndView deleteFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int fournisseurId = Integer.parseInt(request.getParameter("id"));
			userDAO.delete(fournisseurId);
		}
		
		return new ModelAndView("redirect:/"+userType+"/gestionFournisseur");
	}
	
	/***********************************Admin Contenu*************************************************/
	public ModelAndView listAdminContenu(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			//List<User> listAdminContenu = userDAO.list("AdminContenu");
			List<Admin> listAdminContenu = adminDAO.list("AdminContenu");
			//model.addObject("listAdminEntite", listAdminContenu);
			model.addObject("listAdminContenu", listAdminContenu);
			model.addObject("page_title", userType+" | Gestion Admins Contenu");
			model.addObject("page_name", "gestionAdminContenu");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView newAdminContenu(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			model.addObject("page_title", userType+" | Ajouter Admin Contenu");
			model.addObject("page_name", "adminContenuForm");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView editAdminContenu(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int adminContenuId = Integer.parseInt(request.getParameter("id"));
			model.addObject("adminContenu", adminDAO.get(adminContenuId));
			String u="AdminContenu";
			if (user.getType().equals(u)) {
				model.addObject("adminContenuUser", userDAO.get(adminContenuId));
			}
			model.addObject("page_title", userType+" | Modifier Admin Contenu");
			model.addObject("page_name", "adminContenuUpdate");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView saveAdminContenu(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			User u = new User();
			u.setUserName(request.getParameter("userName"));
			u.setPassword(request.getParameter("password"));
			u.setType("AdminContenu");
			userDAO.addUser(u);
			Admin a = new Admin();
			a.setId(userDAO.getLastId());
			a.setName(request.getParameter("name"));
			a.setAddress(request.getParameter("address"));
			a.setEmail(request.getParameter("email"));
			a.setTelephone(request.getParameter("telephone"));
			adminDAO.save(a);	
		}
		return new ModelAndView("redirect:/"+userType+"/gestionAdminContenu");
	}
	
	public ModelAndView updateAdminContenu( HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			Admin a = new Admin();
			a.setId(Integer.parseInt(request.getParameter("id")));
			a.setName(request.getParameter("name"));
			a.setAddress(request.getParameter("address"));
			a.setEmail(request.getParameter("email"));
			a.setTelephone(request.getParameter("telephone"));
			
			adminDAO.update(a);
			String ch="AdminContenu";
			if (user.getType().equals(ch)) {
				User u = new User();
				u.setId(Integer.parseInt(request.getParameter("idUser")));
				u.setUserName(request.getParameter("username"));
				u.setPassword(request.getParameter("password"));
				u.setType(userType);
				userDAO.saveOrUpdate(u);
				user.setUserName(request.getParameter("username"));
				return new ModelAndView("redirect:/"+userType+"/accueil");
			}
		}
		return new ModelAndView("redirect:/"+userType+"/gestionAdminContenu");
	}
	
	public ModelAndView adminContenuDelete(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int adminContenuId = Integer.parseInt(request.getParameter("id"));
			userDAO.delete(adminContenuId);
		}
		return new ModelAndView("redirect:/"+userType+"/gestionAdminContenu");
	}
	
	/***********************************Admin Entite*************************************************/
	public ModelAndView listAdminEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			//List<User> listAdminEntite = userDAO.list("AdminEntite");
			List<Admin> listAdminEntite = adminDAO.list("AdminEntite");
			//model.addObject("listAdminEntite", listAdminEntite);
			model.addObject("listAdminEntite", listAdminEntite);
			model.addObject("page_title", userType+" | Gestion Admins Entites");
			model.addObject("page_name", "gestionAdminEntite");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView newAdminEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			model.addObject("page_title", userType+" | Ajouter Admin Entite");
			model.addObject("page_name", "adminEntiteForm");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView editAdminEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int adminEntiteId = Integer.parseInt(request.getParameter("id"));
			model.addObject("adminEntite", adminDAO.get(adminEntiteId));
			String u="AdminEntite";
			if (user.getType().equals(u)) {
				model.addObject("adminEntiteUser", userDAO.get(adminEntiteId));
			}
			model.addObject("page_title", userType+" | Modifier Admin Entite");
			model.addObject("page_name", "adminEntiteUpdate");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView saveAdminEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			User u = new User();
			u.setUserName(request.getParameter("userName"));
			u.setPassword(request.getParameter("password"));
			u.setType("AdminEntite");
			userDAO.addUser(u);
			Admin a = new Admin();
			a.setId(userDAO.getLastId());
			a.setName(request.getParameter("name"));
			a.setAddress(request.getParameter("address"));
			a.setEmail(request.getParameter("email"));
			a.setTelephone(request.getParameter("telephone"));
			adminDAO.save(a);	
		}
		return new ModelAndView("redirect:/"+userType+"/gestionAdminEntite");
	}
	
	public ModelAndView updateAdminEntite( HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			Admin a = new Admin();
			a.setId(Integer.parseInt(request.getParameter("id")));
			a.setName(request.getParameter("name"));
			a.setAddress(request.getParameter("address"));
			a.setEmail(request.getParameter("email"));
			a.setTelephone(request.getParameter("telephone"));
			
			adminDAO.update(a);
			String ch="AdminEntite";
			if (user.getType().equals(ch)) {
				User u = new User();
				u.setId(Integer.parseInt(request.getParameter("idUser")));
				u.setUserName(request.getParameter("username"));
				u.setPassword(request.getParameter("password"));
				u.setType(userType);
				userDAO.saveOrUpdate(u);
				user.setUserName(request.getParameter("username"));
				return new ModelAndView("redirect:/"+userType+"/accueil");
			}
		}
		return new ModelAndView("redirect:/"+userType+"/gestionAdminEntite");
	}
	
	public ModelAndView adminEntiteDelete(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int adminEntiteId = Integer.parseInt(request.getParameter("id"));
			userDAO.delete(adminEntiteId);
		}
		return new ModelAndView("redirect:/"+userType+"/gestionAdminEntite");
	}
	
	/***********************************Responsable Achat*************************************************/
	public ModelAndView listResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			List<Admin> listResponsableAchat = adminDAO.list("ResponsableAchat");
			model.addObject("listResponsableAchat", listResponsableAchat);
			model.addObject("page_title", userType+" | Gestion Responsable Achats");
			model.addObject("page_name", "gestionResponsableAchat");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView newResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			model.addObject("page_title", userType+" | Ajouter Responsable Achat");
			model.addObject("page_name", "responsableAchatForm");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView editResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int responsableAchatId = Integer.parseInt(request.getParameter("id"));
			model.addObject("responsableAchat", adminDAO.get(responsableAchatId));
			String u="ResponsableAchat";
			if (user.getType().equals(u)) {
				model.addObject("responsableAchatUser", userDAO.get(responsableAchatId));
			}
			
			model.addObject("page_title", userType+" | Modifier Responsable Achat");
			model.addObject("page_name", "responsableAchatUpdate");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView saveResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			User u = new User();
			u.setUserName(request.getParameter("userName"));
			u.setPassword(request.getParameter("password"));
			u.setType("ResponsableAchat");
			userDAO.addUser(u);
			Admin a = new Admin();
			a.setId(userDAO.getLastId());
			a.setName(request.getParameter("name"));
			a.setAddress(request.getParameter("address"));
			a.setEmail(request.getParameter("email"));
			a.setTelephone(request.getParameter("telephone"));
			adminDAO.save(a);	
		}
		return new ModelAndView("redirect:/"+userType+"/gestionResponsableAchat");
	}
	
	public ModelAndView updateResponsableAchat( HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			Admin a = new Admin();
			a.setId(Integer.parseInt(request.getParameter("id")));
			a.setName(request.getParameter("name"));
			a.setAddress(request.getParameter("address"));
			a.setEmail(request.getParameter("email"));
			a.setTelephone(request.getParameter("telephone"));
			
			adminDAO.update(a);
			String ch="ResponsableAchat";
			if (user.getType().equals(ch)) {
				User u = new User();
				u.setId(Integer.parseInt(request.getParameter("idUser")));
				u.setUserName(request.getParameter("username"));
				u.setPassword(request.getParameter("password"));
				u.setType(userType);
				userDAO.saveOrUpdate(u);
				user.setUserName(request.getParameter("username"));
				return new ModelAndView("redirect:/"+userType+"/accueil");
			}
		}
		return new ModelAndView("redirect:/"+userType+"/gestionResponsableAchat");
	}
	
	public ModelAndView responsableAchatDelete(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int reponsableAchatId = Integer.parseInt(request.getParameter("id"));
			userDAO.delete(reponsableAchatId);
		}
		return new ModelAndView("redirect:/"+userType+"/gestionResponsableAchat");
	}
	
	/********************************************** Acheteur*************************************************/
	public ModelAndView listAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			List<Admin> listAcheteur = adminDAO.list("Acheteur");
			model.addObject("listAcheteur", listAcheteur);
			model.addObject("page_title", userType+" | Gestion Acheteur");
			model.addObject("page_name", "gestionAcheteur");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView newAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			model.addObject("page_title", userType+" | Ajouter Acheteur");
			model.addObject("page_name", "acheteurForm");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView editAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int acheteurId = Integer.parseInt(request.getParameter("id"));
			model.addObject("acheteur", adminDAO.get(acheteurId));
			String u="Acheteur";
			if (user.getType().equals(u)) {
				model.addObject("acheteurUser", userDAO.get(acheteurId));
			}
			model.addObject("page_title", userType+" | Modifier Acheteur");
			model.addObject("page_name", "acheteurUpdate");
			model.setViewName("index");
		}
		return model;
	}
	
	public ModelAndView saveAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{	
			User u = new User();
			u.setUserName(request.getParameter("userName"));
			u.setPassword(request.getParameter("password"));
			u.setType("Acheteur");
			userDAO.addUser(u);
			Admin a = new Admin();
			a.setId(userDAO.getLastId());
			a.setName(request.getParameter("name"));
			a.setAddress(request.getParameter("address"));
			a.setEmail(request.getParameter("email"));
			a.setTelephone(request.getParameter("telephone"));
			adminDAO.save(a);	
		}
		return new ModelAndView("redirect:/"+userType+"/gestionAcheteur");
	}
	
	public ModelAndView updateAcheteur( HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			Admin a = new Admin();
			a.setId(Integer.parseInt(request.getParameter("id")));
			a.setName(request.getParameter("name"));
			a.setAddress(request.getParameter("address"));
			a.setEmail(request.getParameter("email"));
			a.setTelephone(request.getParameter("telephone"));
			
			adminDAO.update(a);
			String ch="Acheteur";
			if (user.getType().equals(ch)) {
				User u = new User();
				u.setId(Integer.parseInt(request.getParameter("idUser")));
				u.setUserName(request.getParameter("username"));
				u.setPassword(request.getParameter("password"));
				u.setType(userType);
				userDAO.saveOrUpdate(u);
				user.setUserName(request.getParameter("username"));
				return new ModelAndView("redirect:/"+userType+"/accueil");
			}
		}
		return new ModelAndView("redirect:/"+userType+"/gestionAcheteur");
	}
	
	public ModelAndView acheteurDelete(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int acheteurId = Integer.parseInt(request.getParameter("id"));
			userDAO.delete(acheteurId);
		}
		return new ModelAndView("redirect:/"+userType+"/gestionAcheteur");
	}
}
