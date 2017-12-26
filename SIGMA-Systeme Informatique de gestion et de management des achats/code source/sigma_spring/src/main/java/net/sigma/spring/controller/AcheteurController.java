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

import net.sigma.spring.dao.IEntiteDAO;
import net.sigma.spring.dao.IFournisseurDAO;
import net.sigma.spring.dao.IUserDAO;
import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;

@Controller
public class AcheteurController extends ControllerFactory{

	@Autowired
	protected IEntiteDAO entiteDAO;
	
	public AcheteurController(){
		userType="Acheteur";
	}
	
	@RequestMapping({"/Acheteur","/Acheteur/accueil"})
	public ModelAndView espaceAcheteur(HttpServletRequest request, ModelAndView model) throws IOException{
		return espaceUser(request,model);
	}
	
	@RequestMapping(value = "/Acheteur/gestionProfil", method = RequestMethod.GET)
	public ModelAndView gestionProfil(HttpServletRequest request, ModelAndView model) throws IOException{
		return editAcheteur(request, model);
	}
	
	@RequestMapping(value = "/Acheteur/updateAcheteur", method = RequestMethod.POST)
	public ModelAndView acheteurUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateAcheteur(request, model);
	}
	
	@RequestMapping(value="/Acheteur/gestionFournisseur")
	public ModelAndView gestionFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return listFournisseur(request, model);
	}
	
	@RequestMapping(value = "/Acheteur/newFournisseur", method = RequestMethod.GET)
	public ModelAndView addFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return newFournisseur(request, model);
	}
	
	@RequestMapping(value = "/Acheteur/editFournisseur", method = RequestMethod.GET)
	public ModelAndView modifFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		return editFournisseur(request, model);
	}
	
	@RequestMapping(value = "/Acheteur/saveFournisseur", method = RequestMethod.POST)
	public ModelAndView fournisseurSave( HttpServletRequest request, ModelAndView model) throws IOException{
		return saveFournisseur(request, model);
	}
	
	@RequestMapping(value = "/Acheteur/updateFournisseur", method = RequestMethod.POST)
	public ModelAndView fournisseurUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateFournisseur(request, model);
	}
	
	@RequestMapping(value = "/Acheteur/deleteFournisseur", method = RequestMethod.GET)
	public ModelAndView fournisseurDelete(HttpServletRequest request, ModelAndView model) throws IOException{
		return deleteFournisseur(request, model);
	}
	
	/********************************Evaluer Fournisseur*************************************/
	@RequestMapping(value = "/Acheteur/evaluerFournisseur", method = RequestMethod.GET)
	public ModelAndView evaluerFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			List<Fournisseur> listFournisseur = fournisseurDAO.list();
			model.addObject("listFournisseur", listFournisseur);
			model.addObject("page_title", userType+" | Évaluer Fournisseur");
			model.addObject("page_name", "evaluerFournisseur");
			model.setViewName("index");
		}
		return model;
	}
	
	@RequestMapping(value = "/Acheteur/evaluateFournisseur", method = RequestMethod.POST)
	public ModelAndView setEvaluation(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			Fournisseur f = new Fournisseur();
			f.setId(Integer.parseInt(request.getParameter("id")));
			f.setScore(Integer.parseInt(request.getParameter("score")));
			fournisseurDAO.evaluer(f);
		}
		return new ModelAndView("redirect:/"+userType+"/evaluerFournisseur");
	}
	
	/********************************Qualifier Fournisseur*************************************/
	@RequestMapping(value = "/Acheteur/qualifierFournisseur", method = RequestMethod.GET)
	public ModelAndView qualifiateFournisseur(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			List<Fournisseur> listFournisseur = fournisseurDAO.list();
			model.addObject("listFournisseur", listFournisseur);
			model.addObject("metric", entiteDAO.list().get(0).getMetric());
			
			model.addObject("page_title", userType+" | Qualifier Fournisseur");
			model.addObject("page_name", "qualifierFournisseur");
			model.setViewName("index");
		}
		return model;
	}
	
	@RequestMapping(value = "/Acheteur/qualifiateFournisseur", method = RequestMethod.POST)
	public ModelAndView setQualification(HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			Fournisseur f = fournisseurDAO.get(Integer.parseInt(request.getParameter("id")));
			int metric = entiteDAO.list().get(0).getMetric();

			if(f.getScore() < metric){
				f.setScore(metric);
				fournisseurDAO.evaluer(f);
			}
		}
		return new ModelAndView("redirect:/"+userType+"/qualifierFournisseur");
	}
	
}