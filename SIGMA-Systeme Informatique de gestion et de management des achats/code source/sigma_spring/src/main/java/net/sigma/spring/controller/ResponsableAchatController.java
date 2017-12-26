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
import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;

@Controller
public class ResponsableAchatController extends ControllerFactory{

	public ResponsableAchatController(){
		userType="ResponsableAchat";
	}
	
	@RequestMapping({"/ResponsableAchat","/ResponsableAchat/accueil"})
	public ModelAndView espaceResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		return espaceUser(request,model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/gestionProfil", method = RequestMethod.GET)
	public ModelAndView gestionProfil(HttpServletRequest request, ModelAndView model) throws IOException{
		return editResponsableAchat(request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/updateResponsableAchat", method = RequestMethod.POST)
	public ModelAndView responsableAchatUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateResponsableAchat(request, model);
	}
	
	@RequestMapping(value="/ResponsableAchat/gestionFournisseur")
	public ModelAndView gestionFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return listFournisseur(request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/newFournisseur", method = RequestMethod.GET)
	public ModelAndView addFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return newFournisseur(request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/editFournisseur", method = RequestMethod.GET)
	public ModelAndView modifFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return editFournisseur(request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/saveFournisseur", method = RequestMethod.POST)
	public ModelAndView fournisseurSave( HttpServletRequest request, ModelAndView model) throws IOException{
		return saveFournisseur(request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/updateFournisseur", method = RequestMethod.POST)
	public ModelAndView fournisseurUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateFournisseur(request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/deleteFournisseur", method = RequestMethod.GET)
	public ModelAndView fournisseurDelete(HttpServletRequest request, ModelAndView model) throws IOException{
		return deleteFournisseur(request, model);
	}
	
	@RequestMapping(value="/ResponsableAchat/gestionAcheteur")
	public ModelAndView gestionAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return listAcheteur(request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/editAcheteur", method = RequestMethod.GET)
	public ModelAndView modifAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return editAcheteur(request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/newAcheteur", method = RequestMethod.GET)
	public ModelAndView addAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return newAcheteur(request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/saveAcheteur", method = RequestMethod.POST)
	public ModelAndView acheteurSave(/*@ModelAttribute User Acheteur,*/ HttpServletRequest request, ModelAndView model) throws IOException{
		return saveAcheteur(/*Acheteur,*/ request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/updateAcheteur", method = RequestMethod.POST)
	public ModelAndView acheteurUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateAcheteur(request, model);
	}
	
	@RequestMapping(value = "/ResponsableAchat/deleteAcheteur", method = RequestMethod.GET)
	public ModelAndView deleteAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return acheteurDelete(request, model);
	}
}