package net.sigma.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import net.sigma.spring.model.Admin;
import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;

@Controller
public class AdminContenuController extends ControllerFactory{

	public AdminContenuController(){
		userType="AdminContenu";
	}
	
	@RequestMapping({"/AdminContenu","/AdminContenu/accueil"})
	public ModelAndView espaceResponsableAchat(HttpServletRequest request, ModelAndView model) throws IOException{
		return espaceUser(request,model);
	}
	
	@RequestMapping(value = "/AdminContenu/gestionProfil", method = RequestMethod.GET)
	public ModelAndView gestionProfil(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int acheteurId = Integer.parseInt(request.getParameter("id"));
			model.addObject("adminContenu", adminDAO.get(acheteurId));
			String u="AdminContenu";
			if (user.getType().equals(u)) {
				model.addObject("adminContenuUser", userDAO.get(acheteurId));
			}
			model.addObject("page_title", userType+" | Modifier Admin Contenu");
			model.addObject("page_name", "adminContenuUpdate");
			model.setViewName("index");
		}
		return model;
	}
	
	@RequestMapping(value = "/AdminContenu/updateAdminContenu", method = RequestMethod.POST)
	public ModelAndView adminContenuUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
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
		return new ModelAndView("redirect:/"+userType+"/gestionContenu");
	}
	///////////////////
	@RequestMapping(value="/AdminContenu/gestionMosaique")
	public ModelAndView gestionMosaique(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals("AdminContenu"))){
			model.setViewName("redirect:/login");
		}else{

			model.addObject("listFournisseurs", fournisseurDAO.list());
			
			model.addObject("page_title", userType+" | Gestion Mosaique");
			model.addObject("page_name", "gestionMosaique");
			
			model.setViewName("index");
		}
		
		return model;
	}
}