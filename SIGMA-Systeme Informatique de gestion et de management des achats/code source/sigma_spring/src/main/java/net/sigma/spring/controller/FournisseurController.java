package net.sigma.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.List;
import org.springframework.web.servlet.ModelAndView;
import net.sigma.spring.dao.IFournisseurDAO;
import net.sigma.spring.model.Files;
import net.sigma.spring.model.User;

@Controller
public class FournisseurController  extends ControllerFactory{

	@Autowired
	protected IFournisseurDAO fournisseurDAO;
	
	public FournisseurController(){
		userType="Fournisseur";
	}
	
	@RequestMapping({"/Fournisseur","/Fournisseur/accueil"})
	public ModelAndView espaceAdminEntite(HttpServletRequest request, ModelAndView model) throws IOException{
		return espaceUser(request,model);
	}
	
	@RequestMapping(value = "/Fournisseur/gestionProfil", method = RequestMethod.GET)
	public ModelAndView gestionProfil(HttpServletRequest request, ModelAndView model) throws IOException{
		return editFournisseur(request, model);
	}
	
	@RequestMapping(value = "/Fournisseur/updateFournisseur", method = RequestMethod.POST)
	public ModelAndView fournisseurUpdate( HttpServletRequest request, ModelAndView model) throws IOException{
		return updateFournisseur(request, model);
	}
	
	@RequestMapping(value = "/Fournisseur/uploadFile", method = RequestMethod.GET)
	public ModelAndView fournisseurlistFiles( HttpServletRequest request, ModelAndView model) throws IOException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			List<Files> listFiles = fournisseurDAO.listFiles();
			model.addObject("files", listFiles);
			model.addObject("page_title", userType+" | upload");
			model.addObject("page_name", "upload");
			model.setViewName("index");
		}		
		return model;
	}
	
	@RequestMapping(value = "/Fournisseur/saveFile", method = RequestMethod.POST)
	public ModelAndView fournisseurSaveFile( HttpServletRequest request, ModelAndView model,
			HttpServletResponse response) throws IOException, ServletRequestBindingException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultipartFile multipartFile = multipartRequest.getFile("file");
	        Files file = new Files();
	        file.setFilename(multipartFile.getOriginalFilename());
	        file.setNotes(ServletRequestUtils.getStringParameter(request, "notes"));
	        file.setType(multipartFile.getContentType());
	        file.setFile(multipartFile.getBytes());
	        file.setId_fournisseur(user.getId());
	        fournisseurDAO.saveFile(file);

	        return new ModelAndView("redirect:/"+userType+"/uploadFile");
		}		
		return model;
	}
	
	@RequestMapping(value = "/Fournisseur/downloadFile", method = RequestMethod.GET)
	public ModelAndView fournisseurDownloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
			int fileId = Integer.parseInt(request.getParameter("id"));
	        Files file = fournisseurDAO.find(fileId);
	        response.setContentType(file.getType());
	        response.setContentLength(file.getFile().length);
	        response.setHeader("Content-Disposition","attachment; filename=\"" + file.getFilename() +"\"");
	 
	        FileCopyUtils.copy(file.getFile(), response.getOutputStream());
	 
	        return null;
	 
	    }
	@RequestMapping(value = "/Fournisseur/deleteFile", method = RequestMethod.GET)
	public ModelAndView fournisseurDeleteFile( HttpServletRequest request, ModelAndView model,
			HttpServletResponse response) throws IOException, ServletRequestBindingException{
		User user =(User) request.getSession().getAttribute("userConnected");
		if((user==null)||(!user.getType().equals(userType))){
			model.setViewName("redirect:/login");
		}else{
			int fileId = Integer.parseInt(request.getParameter("id"));
			fournisseurDAO.deleteFile(fileId);
	        return new ModelAndView("redirect:/"+userType+"/uploadFile");
		}		
		return model;
	}
}