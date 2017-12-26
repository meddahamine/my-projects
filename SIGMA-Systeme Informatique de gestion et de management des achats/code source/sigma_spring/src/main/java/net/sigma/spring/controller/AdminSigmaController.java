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

import net.sigma.spring.dao.IFournisseurDAO;
import net.sigma.spring.dao.IUserDAO;
import net.sigma.spring.model.Admin;
import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;

@Controller
public class AdminSigmaController extends ControllerFactory{

	public AdminSigmaController(){
		userType="AdminSigma";
	}
	
	@RequestMapping({"/AdminSigma","/AdminSigma/accueil"})
	public ModelAndView espaceAdminSigma(HttpServletRequest request, ModelAndView model) throws IOException{
		return espaceUser(request,model);
	}
	
	@RequestMapping(value = "/AdminSigma/gestionProfil", method = RequestMethod.GET)
	public ModelAndView gestionProfil(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int acheteurId = Integer.parseInt(request.getParameter("id"));
			
			model.addObject("adminSigma", adminDAO.get(acheteurId));
			String u="AdminSigma";
			if (user.getType().equals(u)) {
				model.addObject("adminSigmaUser", userDAO.get(acheteurId));
			}
			model.addObject("page_title", userType+" | Modifier Admin Sigma");
			model.addObject("page_name", "adminSigmaUpdate");
			model.setViewName("index");
		}
		return model;
	}
	
	@RequestMapping(value = "/AdminSigma/updateAdminSigma", method = RequestMethod.POST)
	public ModelAndView adminSigmaUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
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
			String ch="AdminSigma";
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
		return new ModelAndView("redirect:/"+userType+"/gestionAdminSigma");
	}
	
	@RequestMapping(value="/AdminSigma/gestionFournisseur")
	public ModelAndView gestionFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return listFournisseur(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/newFournisseur", method = RequestMethod.GET)
	public ModelAndView addFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return newFournisseur(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/editFournisseur", method = RequestMethod.GET)
	public ModelAndView modifFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return editFournisseur(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/saveFournisseur", method = RequestMethod.POST)
	public ModelAndView fournisseurSave( HttpServletRequest request, ModelAndView model) throws IOException{
		return saveFournisseur(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/updateFournisseur", method = RequestMethod.POST)
	public ModelAndView fournisseurUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateFournisseur(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/deleteFournisseur", method = RequestMethod.GET)
	public ModelAndView fournisseurDelete(HttpServletRequest request, ModelAndView model) throws IOException{
		return deleteFournisseur(request, model);
	}
	
	@RequestMapping(value="/AdminSigma/test")
	public ModelAndView espaceAdminTest(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals("AdminSigma"))){
			model.setViewName("redirect:/login");
		}else{
			User u = (User) request.getSession().getAttribute("userConnected");
			model.addObject("password", u.getPassword());
			model.setViewName("AdminSigma/test");
		}
		return model;
	}
	
	@RequestMapping(value="/AdminSigma/gestionAdminContenu")
	public ModelAndView gestionAdminContenu(HttpServletRequest request, ModelAndView model) throws IOException{
		return listAdminContenu(request, model);
	}
	
	@RequestMapping(value="/AdminSigma/gestionAdminEntite")
	public ModelAndView gestionAdminEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		return listAdminEntite(request, model);
	}
	
	@RequestMapping(value="/AdminSigma/gestionResponsableAchat")
	public ModelAndView gestionResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		return listResponsableAchat(request, model);
	}
	
	@RequestMapping(value="/AdminSigma/gestionAcheteur")
	public ModelAndView gestionAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return listAcheteur(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/editAdminContenu", method = RequestMethod.GET)
	public ModelAndView modifAdminContenu(HttpServletRequest request, ModelAndView model) throws IOException{
		return editAdminContenu(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/editAdminEntite", method = RequestMethod.GET)
	public ModelAndView modifAdminEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		return editAdminEntite(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/editResponsableAchat", method = RequestMethod.GET)
	public ModelAndView modifResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		return editResponsableAchat(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/editAcheteur", method = RequestMethod.GET)
	public ModelAndView modifAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return editAcheteur(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/newAdminContenu", method = RequestMethod.GET)
	public ModelAndView addAdminContenu(HttpServletRequest request, ModelAndView model) throws IOException{
		return newAdminContenu(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/newAdminEntite", method = RequestMethod.GET)
	public ModelAndView addAdminEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		return newAdminEntite(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/newResponsableAchat", method = RequestMethod.GET)
	public ModelAndView addResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		return newResponsableAchat(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/newAcheteur", method = RequestMethod.GET)
	public ModelAndView addAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return newAcheteur(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/saveAdminContenu", method = RequestMethod.POST)
	public ModelAndView adminContenuSave( HttpServletRequest request, ModelAndView model) throws IOException{
		return saveAdminContenu(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/saveAdminEntite", method = RequestMethod.POST)
	public ModelAndView adminEntiteSave( HttpServletRequest request, ModelAndView model) throws IOException{
		return saveAdminEntite(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/saveResponsableAchat", method = RequestMethod.POST)
	public ModelAndView responsableAchatSave(HttpServletRequest request, ModelAndView model) throws IOException{
		return saveResponsableAchat(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/saveAcheteur", method = RequestMethod.POST)
	public ModelAndView acheteurSave(HttpServletRequest request, ModelAndView model) throws IOException{
		return saveAcheteur(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/updateAdminContenu", method = RequestMethod.POST)
	public ModelAndView adminContenuUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateAdminContenu(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/updateAdminEntite", method = RequestMethod.POST)
	public ModelAndView adminEntiteUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateAdminEntite(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/updateResponsableAchat", method = RequestMethod.POST)
	public ModelAndView responsableAchatUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateResponsableAchat(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/updateAcheteur", method = RequestMethod.POST)
	public ModelAndView acheteurUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateAcheteur(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/deleteAdminContenu", method = RequestMethod.GET)
	public ModelAndView deleteAdminContenu(HttpServletRequest request, ModelAndView model) throws IOException{
		return adminContenuDelete(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/deleteAdminEntite", method = RequestMethod.GET)
	public ModelAndView deleteAdminEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		return adminEntiteDelete(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/deleteResponsableAchat", method = RequestMethod.GET)
	public ModelAndView deleteResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		return responsableAchatDelete(request, model);
	}
	
	@RequestMapping(value = "/AdminSigma/deleteAcheteur", method = RequestMethod.GET)
	public ModelAndView deleteAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return acheteurDelete(request, model);
	}
}