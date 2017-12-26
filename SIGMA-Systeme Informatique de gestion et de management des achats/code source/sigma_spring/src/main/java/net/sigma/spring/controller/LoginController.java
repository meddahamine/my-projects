package net.sigma.spring.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sigma.spring.dao.AdminDAO;
import net.sigma.spring.dao.IAdminDAO;
import net.sigma.spring.dao.IFournisseurDAO;
import net.sigma.spring.dao.IUserDAO;
import net.sigma.spring.model.Admin;
import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@SessionAttributes("userConnected")
public class LoginController {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	protected IFournisseurDAO fournisseurDAO;

	@Autowired
	protected IAdminDAO adminDAO;
	
	@RequestMapping(value="/login")
	public ModelAndView loginView(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if(user!=null){
			model.setViewName("redirect:/"+user.getType()+"/");
		}else{
			model.setViewName("login");
		}
		
		return model;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request, ModelAndView model,@ModelAttribute("userConnected") User u, SessionStatus sessionStatus) throws IOException{
		request.getSession().setAttribute("userConnected", null);
		sessionStatus.setComplete();
		model.setViewName("redirect:/");
		
		return model;
	}
	
	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
	public ModelAndView connexionUser(HttpServletRequest request, ModelAndView model,@ModelAttribute User user) throws SQLException, IOException {
		User userConnecte = userDAO.getUserLogin(user);
		if(userConnecte==null){
			model.addObject("error", "Nom d'utilisateur ou/et Mot de passe invalides");
			model.setViewName("login");
		}else{
			request.getSession().setAttribute("userConnected", userConnecte);
			
			Admin admin = adminDAO.get(userConnecte.getId());
			if(admin!=null){
				request.getSession().setAttribute("adminConnected", admin);
			}
			
			Fournisseur fournisseur = fournisseurDAO.get(userConnecte.getId());
			if(fournisseur!=null){
				request.getSession().setAttribute("fournisseur", fournisseur);
			}
			
			//Set session Timeout
			request.getSession().setMaxInactiveInterval(20*60);
			model.setViewName("redirect:/"+userConnecte.getType()+"/");
		}
		return model;
	}
}
