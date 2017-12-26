package net.sigma.spring.controller;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import net.sigma.spring.dao.IFournisseurDAO;
import net.sigma.spring.dao.IUserDAO;
import net.sigma.spring.model.User;

@RunWith(MockitoJUnitRunner.class)
public class ControllerFactoryTest {
	
	@Mock
	IFournisseurDAO fournisseurDAO;
	@Mock
	IUserDAO userDAO;
	@Mock
	String userType;
	
	@InjectMocks
	ControllerFactory controllerFactory;
	@InjectMocks
	ModelAndView model;
	@InjectMocks
	HttpServletRequest request;	
	
	@Test(expected = MockitoAssertionError.class)
	public void espaceUserTest1() throws IOException{
		//Given
		request = Mockito.mock(HttpServletRequest.class);
		model=Mockito.mock(ModelAndView.class);
		model.setViewName("redirect:/login");
		//User user =(User) request.getSession().getAttribute("userConnected");
		//User user=null;

		//When
		ModelAndView res=controllerFactory.espaceUser(request, model);
		//Then
		
		Mockito.verify(model, Mockito.times(1)).setViewName("redirect:/login");
		assertEquals("redirect:/login",res.getViewName());
		
	}
	
	@Test(expected = MockitoException.class)
	public void espaceUserTest2() throws IOException{
		//Given
		String userType="AdminSigma";
		request = Mockito.mock(HttpServletRequest.class);
		model=Mockito.mock(ModelAndView.class);
		model.setViewName("index");
		//User user =(User) request.getSession().getAttribute("userConnected");
		
		model.addObject("success", "Connexion Valide");
		model.addObject("page_title", userType+" | Accueil");
		model.addObject("page_name", "accueil");
		model.setViewName("index");
		
		//When
		//model.addObject("page_name", "accueil");
		//model.getViewName();
		ModelAndView res=controllerFactory.espaceUser(request, model);
		//Then
		
		Mockito.verify(model, Mockito.times(1)).addObject(Mockito.eq("success"), Mockito.eq("Connexion Valide"));
		Mockito.verify(model, Mockito.times(1)).addObject(Mockito.eq("page_title"), Mockito.eq("AdminSigma"+" | Accueil"));
		Mockito.verify(model, Mockito.times(1)).addObject(Mockito.eq("page_name"), Mockito.eq("accueil"));
		Mockito.verify(model, Mockito.times(1)).setViewName("index");
		
		assertEquals("index",res.getViewName());
		
	}

}
