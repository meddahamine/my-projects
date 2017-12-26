package com.spring.hibernate.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.hibernate.dao.ArtisteDao;
import com.spring.hibernate.model.Artiste;

@Service("artisteService")
@Transactional
public class ArtisteServiceImpl implements ArtisteService{

	@Autowired
	ArtisteDao artisteDao;
	
	public Artiste findById(int id) {
		return artisteDao.findById(id);
	}

	public void save(Artiste artiste) {
		artisteDao.save(artiste);
	}

	public List<Artiste> list() {
		return artisteDao.list();
	}

	public void update(Artiste artiste) {
		Artiste entity = artisteDao.findById(artiste.getId());
		if(entity!=null){
			entity.setNom(artiste.getNom());
		}
	}

	public void delete(String id) {
		artisteDao.delete(id);
	}

}
