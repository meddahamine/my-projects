package com.spring.hibernate.dao;

import java.util.List;

import com.spring.hibernate.model.TitreMusical;

public interface TitreMusicalDao {

	TitreMusical findById(int id);
	void save(TitreMusical titremusical);
	List<TitreMusical> list();
	List<TitreMusical> findByIdforgien(int id);
	void delete(String id);
}
