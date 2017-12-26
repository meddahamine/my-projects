package com.spring.hibernate.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.hibernate.model.Album;
import com.spring.hibernate.model.Artiste;
import com.spring.hibernate.model.TitreMusical;
import com.spring.hibernate.model.TypeSupport;
import com.spring.hibernate.service.AlbumService;
import com.spring.hibernate.service.ArtisteService;
import com.spring.hibernate.service.TitreMusicalService;
import com.spring.hibernate.service.TypeSupportService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	private TypeSupportService typesupportservice;
	
	@Autowired
	private TitreMusicalService titremusicalservice;
	
	@Autowired
	private AlbumService albumservice;
	
	@Autowired
	private ArtisteService artisteservice;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listPrincipal(ModelMap model) {
				return "home";
	}
	@RequestMapping(value = { "/listTypeSupport" }, method = RequestMethod.GET)
	public String listTypeSupport(ModelMap model) {
		List<TypeSupport> typeSupport = typesupportservice.list();
		model.addAttribute("typeSupport", typeSupport);
		return "listeTypeSupport";
	}
	
	@RequestMapping(value = { "/listTitreMusical" }, method = RequestMethod.GET)
	public String listTitreMusical(ModelMap model) {
		List<TitreMusical> titreMusical = titremusicalservice.list();
		List<TypeSupport> typeSupport = typesupportservice.list();
		List<Album> album = albumservice.list();
		model.addAttribute("typeSupport", typeSupport);
		model.addAttribute("album", album);
		model.addAttribute("titreMusical", titreMusical);
		return "listeTitreMusical";
	}
	
	@RequestMapping(value = { "/listAlbum" }, method = RequestMethod.GET)
	public String listAlbum(ModelMap model) {
		List<Album> album = albumservice.list();
		model.addAttribute("album", album);
		return "listeAlbum";
	}
	
	@RequestMapping(value = { "/listArtiste" }, method = RequestMethod.GET)
	public String listArtiste(ModelMap model) {
		List<Artiste> artiste = artisteservice.list();
		model.addAttribute("artiste", artiste);
		return "listeArtiste";
	}
	
	@RequestMapping(value = { "/listTitreMusical" }, method = RequestMethod.POST)
	public String listFiltre(@RequestParam("sel") String value, @RequestParam("sel1") String value1,
			@RequestParam("sel2") String value2,@RequestParam("sel3") String value3,ModelMap model) {
		System.err.println("value = "+value);
		System.err.println("value1 = "+value1);
		System.err.println("value2 = "+value2);
		System.err.println("value3 = "+value3);
		List<TitreMusical> tmf = new ArrayList<TitreMusical>();
		if (!value1.equals("0")) {
			//System.out.println("dans if"+value1);
			tmf.add(titremusicalservice.findById(Integer.parseInt(value1)));
		}
		if (!value2.equals("0")) {
			System.out.println("dans if"+value2);
			tmf = titremusicalservice.findByIdforgien(Integer.parseInt(value2));
		}
		if (!value3.equals("0")) {
			System.out.println("dans if"+value3);
			tmf = titremusicalservice.findByIdforgien(Integer.parseInt(value3));
		}
//		int valueInt = Integer.parseInt(value);
//		int value2Int = Integer.parseInt(value2);
//		int value3Int = Integer.parseInt(value3);
		List<TypeSupport> typeSupport = typesupportservice.list();
//		List<TitreMusical> titreMusical = titremusicalservice.list();
		List<Album> album = albumservice.list();		
		model.addAttribute("typeSupport", typeSupport);
		model.addAttribute("titreMusical", tmf);
		model.addAttribute("album", album);
		return "listeTitreMusical";
	}
	
	@RequestMapping(value = { "/newTypeSupport" }, method = RequestMethod.GET)
	public String newTypeSupport(ModelMap model) {
		TypeSupport typeSupport = new TypeSupport();
		model.addAttribute("typeSupport", typeSupport);
		return "formulaireSupport";
	}
	
	@RequestMapping(value = { "/newTypeSupport" }, method = RequestMethod.POST)
	public String saveTypeSupport( TypeSupport typeSupport,	ModelMap model) {
		typesupportservice.save(typeSupport);
		model.addAttribute("success", "Type Support : (" + typeSupport.getType() + ") registered successfully");
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "/newArtiste" }, method = RequestMethod.GET)
	public String newArtiste(ModelMap model) {
		Artiste artiste = new Artiste();
		model.addAttribute("artiste", artiste);
		return "formulaireArtiste";
	}
	
	@RequestMapping(value = { "/newArtiste" }, method = RequestMethod.POST)
	public String saveArtiste( Artiste artiste,	ModelMap model) {
		artisteservice.save(artiste);
		model.addAttribute("success", "Artiste : (" + artiste.getNom() + ") registered successfully");
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "/newAlbum" }, method = RequestMethod.GET)
	public String newAlbum(ModelMap model) {
		Album album = new Album();
		List<Artiste> artiste = artisteservice.list();
		model.addAttribute("artiste", artiste);
		model.addAttribute("album", album);
		return "formulaireAlbum";
	}
	
	@RequestMapping(value = { "/newAlbum" }, method = RequestMethod.POST)
	public String saveAlbum( @RequestParam("sel") String value,Album album,	ModelMap model) {
		int valueInt = Integer.parseInt(value);
		//albumservice.findById(valueInt);
		album.setIdArtist(valueInt);
		albumservice.save(album);
		model.addAttribute("success", "Album Nom : (" + album.getNom() + ") registered successfully");
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "/newTitreMusical" }, method = RequestMethod.GET)
	public String newTitreMusical(ModelMap model) {
		TitreMusical titreMusical = new TitreMusical();
		List<Album> album = albumservice.list();
		List<TypeSupport> typeSupport = typesupportservice.list();
		model.addAttribute("typeSupport", typeSupport);
		model.addAttribute("album", album);
		model.addAttribute("titreMusical", titreMusical);
		return "formulaireTitreMusical";
	}
	
	@RequestMapping(value = { "/newTitreMusical" }, method = RequestMethod.POST)
	public String saveTitreMusical( @RequestParam("sel") String value,@RequestParam("sel1") String value1,
			TitreMusical titreMusical,	ModelMap model) {
		titreMusical.setIdAlbum(Integer.parseInt(value));
		titreMusical.setIdType(Integer.parseInt(value1));
		titremusicalservice.save(titreMusical);
		model.addAttribute("success", "Titre Musical : (" + titreMusical.getTitre() + ") registered successfully");
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "/edit-typeSupport/{id}" }, method = RequestMethod.GET)
	public String editTypeSupport(@PathVariable String id, ModelMap model) {
		int idInt = Integer.parseInt(id);
		TypeSupport typesupport = typesupportservice.findById(idInt);
		model.addAttribute("typeSupport", typesupport);
		model.addAttribute("edit", true);
		return "formulaireSupport";
	}
	
	@RequestMapping(value = { "/edit-typeSupport/{id}" }, method = RequestMethod.POST)
	public String updateTypeSupport( TypeSupport typeSupport,	ModelMap model) {
		typesupportservice.update(typeSupport);
		model.addAttribute("success", "Type Support (" + typeSupport.getType()+ ") updated successfully");
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "/edit-artiste/{id}" }, method = RequestMethod.GET)
	public String editArtiste(@PathVariable String id, ModelMap model) {
		int idInt = Integer.parseInt(id);
		Artiste artiste = artisteservice.findById(idInt);
		model.addAttribute("artiste", artiste);
		model.addAttribute("edit", true);
		return "formulaireArtiste";
	}
	
	@RequestMapping(value = { "/edit-artiste/{id}" }, method = RequestMethod.POST)
	public String updateArtiste( Artiste artiste,	ModelMap model) {
		artisteservice.update(artiste);
		model.addAttribute("success", "Artiste (" + artiste.getNom()+ ") updated successfully");
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "/edit-album/{id}" }, method = RequestMethod.GET)
	public String editAlbum(@PathVariable String id, ModelMap model) {
		Album album = albumservice.findById(Integer.parseInt(id));
		List<Artiste> artiste = artisteservice.list();
		model.addAttribute("artiste", artiste);
		model.addAttribute("album", album);
		model.addAttribute("edit", true);
		return "formulaireAlbum";
	}
	
	@RequestMapping(value = { "/edit-album/{id}" }, method = RequestMethod.POST)
	public String updateAlbum(@RequestParam("sel") String value, Album album,	ModelMap model) {
		album.setIdArtist(Integer.parseInt(value));
		albumservice.update(album);
		model.addAttribute("success", "Type Support (" + album.getNom()+ ") updated successfully");
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "/edit-titreMusical/{id}" }, method = RequestMethod.GET)
	public String editTitreMusical(@PathVariable String id, ModelMap model) {
		//int idInt = Integer.parseInt(id);
		TitreMusical titreMusical = titremusicalservice.findById(Integer.parseInt(id));
		List<Album> album = albumservice.list();
		List<TypeSupport> typeSupport = typesupportservice.list();
		model.addAttribute("typeSupport", typeSupport);
		model.addAttribute("album", album);
		model.addAttribute("titreMusical", titreMusical);
		model.addAttribute("edit", true);
		return "formulaireTitreMusical";
	}
	
	@RequestMapping(value = { "/edit-titreMusical/{id}" }, method = RequestMethod.POST)
	public String updateTitreMusical(@RequestParam("sel") String value, @RequestParam("sel1") String value1,
			TitreMusical titreMusical,	ModelMap model) {
		titreMusical.setIdAlbum(Integer.parseInt(value));
		titreMusical.setIdType(Integer.parseInt(value1));
		titremusicalservice.update(titreMusical);
		model.addAttribute("success", "Titre Musical (" + titreMusical.getTitre()+ ") updated successfully");
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "/delete-typeSupport/{id}" }, method = RequestMethod.GET)
	public String deleteTypeSupport(@PathVariable String id, ModelMap model) {
		//System.out.println("id= "+id);
		typesupportservice.delete(id);
		List<TypeSupport> typeSupport = typesupportservice.list();
		model.addAttribute("typeSupport", typeSupport);
		return "listTypeSupport";
	}
	
	@RequestMapping(value = { "/delete-album/{id}" }, method = RequestMethod.GET)
	public String deleteAlbum(@PathVariable String id, ModelMap model) {
		System.out.println("id= "+id);
		albumservice.delete(id);
		List<Album> album = albumservice.list();
		model.addAttribute("album", album);
		return "listeAlbum";
	}
	
	@RequestMapping(value = { "/delete-artiste/{id}" }, method = RequestMethod.GET)
	public String deleteArtiste(@PathVariable String id, ModelMap model) {
		//System.out.println("id= "+id);
		artisteservice.delete(id);
		List<Artiste> artiste = artisteservice.list();
		model.addAttribute("artiste", artiste);
		return "listeArtiste";
	}
	
	@RequestMapping(value = { "/delete-titreMusical/{id}" }, method = RequestMethod.GET)
	public String deleteTitreMusical(@PathVariable String id, ModelMap model) {
		//System.out.println("id= "+id);
		titremusicalservice.delete(id);
		List<TitreMusical> titreMusical = titremusicalservice.list();
		model.addAttribute("titreMusical", titreMusical);
		return "listeTitreMusical";
	}
}
