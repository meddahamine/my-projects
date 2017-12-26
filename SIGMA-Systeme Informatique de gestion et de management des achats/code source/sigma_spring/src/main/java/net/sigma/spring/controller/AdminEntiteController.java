package net.sigma.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.sigma.spring.dao.IArchivesDAO;
import net.sigma.spring.dao.IEntiteDAO;
import net.sigma.spring.dao.IFournisseurDAO;
import net.sigma.spring.dao.IUserDAO;
import net.sigma.spring.model.Admin;
import net.sigma.spring.model.Archive;
import net.sigma.spring.model.Entite;
import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;

@Controller
public class AdminEntiteController  extends ControllerFactory{

	@Autowired
	protected IEntiteDAO entiteDAO;
	
	public AdminEntiteController(){
		userType="AdminEntite";
	}
	
	@RequestMapping({"/AdminEntite","/AdminEntite/accueil"})
	public ModelAndView espaceAdminEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		return espaceUser(request,model);
	}
	
	@RequestMapping(value = "/AdminEntite/gestionProfil", method = RequestMethod.GET)
	public ModelAndView gestionProfil(HttpServletRequest request, ModelAndView model) throws IOException{
		return editAdminEntite(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/updateAdminEntite", method = RequestMethod.POST)
	public ModelAndView adminEntiteUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateAdminEntite(request, model);
	}
	
	@RequestMapping(value="/AdminEntite/gestionFournisseur")
	public ModelAndView gestionFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return listFournisseur(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/newFournisseur", method = RequestMethod.GET)
	public ModelAndView addFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return newFournisseur(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/editFournisseur", method = RequestMethod.GET)
	public ModelAndView modifFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return editFournisseur(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/saveFournisseur", method = RequestMethod.POST)
	public ModelAndView fournisseurSave( HttpServletRequest request, ModelAndView model) throws IOException{
		return saveFournisseur(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/updateFournisseur", method = RequestMethod.POST)
	public ModelAndView fournisseurUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateFournisseur(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/deleteFournisseur", method = RequestMethod.GET)
	public ModelAndView fournisseurDelete(HttpServletRequest request, ModelAndView model) throws IOException{
		return deleteFournisseur(request, model);
	}
	
	@RequestMapping(value="/AdminEntite/gestionResponsableAchat")
	public ModelAndView gestionResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		return listResponsableAchat(request, model);
	}
	
	@RequestMapping(value="/AdminEntite/gestionAcheteur")
	public ModelAndView gestionAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return listAcheteur(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/editResponsableAchat", method = RequestMethod.GET)
	public ModelAndView modifResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		return editResponsableAchat(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/editAcheteur", method = RequestMethod.GET)
	public ModelAndView modifAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return editAcheteur(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/newResponsableAchat", method = RequestMethod.GET)
	public ModelAndView addResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		return newResponsableAchat(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/newAcheteur", method = RequestMethod.GET)
	public ModelAndView addAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return newAcheteur(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/saveResponsableAchat", method = RequestMethod.POST)
	public ModelAndView responsableAchatSave(HttpServletRequest request, ModelAndView model) throws IOException{
		return saveResponsableAchat(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/saveAcheteur", method = RequestMethod.POST)
	public ModelAndView acheteurSave(/*@ModelAttribute User Acheteur,*/ HttpServletRequest request, ModelAndView model) throws IOException{
		return saveAcheteur(/*Acheteur,*/ request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/updateResponsableAchat", method = RequestMethod.POST)
	public ModelAndView responsableAchatUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateResponsableAchat(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/updateAcheteur", method = RequestMethod.POST)
	public ModelAndView acheteurUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateAcheteur(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/deleteResponsableAchat", method = RequestMethod.GET)
	public ModelAndView deleteResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		return responsableAchatDelete(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/deleteAcheteur", method = RequestMethod.GET)
	public ModelAndView deleteAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return acheteurDelete(request, model);
	}
	
	@RequestMapping(value = "/AdminEntite/gestionArchives", method = RequestMethod.GET)
	public ModelAndView gestionArchives(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			List<Admin> listAdmin = adminDAO.listAll();
			List<Fournisseur> listFournisseur = fournisseurDAO.list();
			model.addObject("listAdmin", listAdmin);
			model.addObject("listFournisseur", listFournisseur);
			model.addObject("page_title", userType+" | Gestion Archives");
			model.addObject("page_name", "gestionArchives");
			model.setViewName("index");
		}
		return model;
	}
	
	@RequestMapping(value = "/AdminEntite/archiver", method = RequestMethod.GET)
	public ModelAndView Archiver(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int archiveId = Integer.parseInt(request.getParameter("id"));
			User u = userDAO.get(archiveId);
			Archive archive=new Archive();
			archive.setId(archiveId);
			archive.setType(u.getType());
			System.out.println("id = "+archive.getId());
			System.out.println("type = "+archive.getType());
			Admin admin = adminDAO.get(archiveId);
			if (admin!=null) {
				archive.setName(admin.getName());
				archive.setEmail(admin.getEmail());
				archive.setAddress(admin.getAddress());
				archive.setTelephone(admin.getTelephone());
			}else{
				Fournisseur founisseur = fournisseurDAO.get(archiveId);
				archive.setName(founisseur.getName());
				archive.setEmail(founisseur.getEmail());
				archive.setAddress(founisseur.getAddress());
				archive.setTelephone(founisseur.getTelephone());
			}
			
			Archive a1=archivesDAO.get(archiveId);
			if (a1==null) {
				archivesDAO.save(archive);
			}else{
				archivesDAO.update(archive);
			}
			userDAO.delete(archiveId);
		}
		return new ModelAndView("redirect:/"+userType+"/gestionArchives");
	}
	
	@RequestMapping(value = "/AdminEntite/listArchives", method = RequestMethod.GET)
	public ModelAndView listArchives(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			List<Archive> listArchives = archivesDAO.list();
			model.addObject("listArchives", listArchives);
			model.addObject("page_title", userType+" | List Archives");
			model.addObject("page_name", "listArchives");
			model.setViewName("index");
		}
		return model;
	}
	
	@RequestMapping(value = "/AdminEntite/deleteArchive", method = RequestMethod.GET)
	public ModelAndView deleteArchive(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int archiveId = Integer.parseInt(request.getParameter("id"));
			archivesDAO.delete(archiveId);
		}
		return new ModelAndView("redirect:/"+userType+"/gestionAcheteur");
	}
	
	/************************* Gestion des entité *********************************/
	
	@RequestMapping(value="/AdminEntite/gestionEntite")
	public ModelAndView gestionEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			List<Entite> listEntite = entiteDAO.list();
			model.addObject("listEntite", listEntite);
			model.addObject("page_title", userType+" | Gestion Entités");
			model.addObject("page_name", "gestionEntite");
			model.setViewName("index");
		}
		return model;
	}
	
	@RequestMapping(value = "/AdminEntite/newEntite", method = RequestMethod.GET)
	public ModelAndView newEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			model.addObject("page_title", userType+" | Ajouter Entité");
			model.addObject("page_name", "EntiteForm");
			model.setViewName("index");
		}
		return model;
	}
	
	@RequestMapping(value = "/AdminEntite/editEntite", method = RequestMethod.GET)
	public ModelAndView editEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int entiteId = Integer.parseInt(request.getParameter("id"));
			model.addObject("entite", entiteDAO.get(entiteId));

			model.addObject("page_title", userType+" | Modifier Entité");
			model.addObject("page_name", "entiteUpdate");
			model.setViewName("index");
		}
		return model;
	}
	
	@RequestMapping(value = "/AdminEntite/saveEntite", method = RequestMethod.POST)
	public ModelAndView saveEntite( HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			Entite e = new Entite();
			e.setName(request.getParameter("name"));
			e.setAddress(request.getParameter("address"));
			e.setEmail(request.getParameter("email"));
			e.setTelephone(request.getParameter("telephone"));
			entiteDAO.save(e);	
		}
		return new ModelAndView("redirect:/"+userType+"/gestionEntite");
	}
	
	@RequestMapping(value = "/AdminEntite/updateEntite", method = RequestMethod.POST)
	public ModelAndView updateEntite( HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			Entite e = new Entite();
			e.setId(Integer.parseInt(request.getParameter("id")));
			e.setName(request.getParameter("name"));
			e.setAddress(request.getParameter("address"));
			e.setEmail(request.getParameter("email"));
			e.setTelephone(request.getParameter("telephone"));
			entiteDAO.update(e);
		}
		return new ModelAndView("redirect:/"+userType+"/gestionEntite");
	}
	
	@RequestMapping(value = "/AdminEntite/deleteEntite", method = RequestMethod.GET)
	public ModelAndView deleteEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int entiteId = Integer.parseInt(request.getParameter("id"));
			entiteDAO.delete(entiteId);
		}
		
		return new ModelAndView("redirect:/"+userType+"/gestionEntite");
	}
}