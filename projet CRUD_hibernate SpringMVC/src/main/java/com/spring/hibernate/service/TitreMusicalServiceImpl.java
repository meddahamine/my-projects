package com.spring.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.hibernate.dao.TitreMusicalDao;
import com.spring.hibernate.model.TitreMusical;

@Service("titreMusicalService")
@Transactional
public class TitreMusicalServiceImpl implements TitreMusicalService{

	@Autowired
	private TitreMusicalDao titreMusical;
	
	public List<TitreMusical> list() {
		return titreMusical.list();
	}

	public TitreMusical findById(int id) {
		return titreMusical.findById(id);
	}

	public void save(TitreMusical titremusical) {
		titreMusical.save(titremusical);
	}

	public void update(TitreMusical titremusical) {
		TitreMusical entity = titreMusical.findById(titremusical.getId());
		if(entity!=null){
			entity.setTitre(titremusical.getTitre());
			entity.setIdAlbum(titremusical.getIdAlbum());
			entity.setIdType(titremusical.getIdType());
		}
	}

	public void delete(String id) {
		titreMusical.delete(id);
	}

	public List<TitreMusical> findByIdforgien(int id) {
		return titreMusical.findByIdforgien(id);
	}

}
