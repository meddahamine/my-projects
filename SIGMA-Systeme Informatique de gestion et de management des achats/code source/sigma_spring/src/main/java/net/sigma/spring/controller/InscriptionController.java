package net.sigma.spring.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import net.sigma.spring.dao.IFournisseurDAO;
import net.sigma.spring.dao.IUserDAO;
import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;



@Controller
public class InscriptionController {
	
	@Autowired
	private IFournisseurDAO fournisseurDAO;
	
	@Autowired
	private IUserDAO userDAO;
	
	@RequestMapping(value = "/inscrireFournisseur", method = RequestMethod.POST)
	public ModelAndView inscriptionFournisseur(WebRequest request, ModelAndView model) throws IOException{	
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
		
		model.addObject("userName",u.getUserName());
		model.addObject("passeWord",u.getPassword());
		model.addObject("inscriptionSuccess","Votre inscription a été prise en compte ");
		model.setViewName("inscSuccess");
		return model;
	}
}
