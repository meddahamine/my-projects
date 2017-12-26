package net.sigma.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sigma.spring.dao.IFournisseurDAO;
import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller routes accesses to the application to the appropriate
 * hanlder methods. 
 * @author www.sigma.net
 *
 */
@Controller
public class HomeController {

	@Autowired
	protected IFournisseurDAO fournisseurDAO;
	
	@RequestMapping({"/","/home"})
	public ModelAndView homePage(HttpServletRequest request, ModelAndView model) throws IOException{
		/*User user =(User) request.getSession().getAttribute("userConnected");
		if(user!=null){
			model.setViewName("redirect:/"+user.getType()+"/");
		}else{*/ 
		
		if(request.getSession().getAttribute("baseURL")==null){
			/*Save base URL on Session*/
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String ctx = request.getContextPath();
			String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
			request.getSession().setAttribute("baseURL", base);
		}
		
		model.addObject("listFournisseurs", fournisseurDAO.list());
		
		model.setViewName("accueil");
		//}
		
		return model;
	}
	
	@RequestMapping(value = "/inscriptionFournisseur", method = RequestMethod.GET)
	public ModelAndView saveFournisseur(ModelAndView model) throws IOException{
		Fournisseur newFournisseur = new Fournisseur();
		model.addObject("Fournisseur", newFournisseur);
		
		model.setViewName("inscription");
		return model;
	}
	
}
