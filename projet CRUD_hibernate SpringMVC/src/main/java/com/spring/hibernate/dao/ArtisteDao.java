package com.spring.hibernate.dao;

import java.util.List;

import com.spring.hibernate.model.Artiste;

public interface ArtisteDao {

	Artiste findById(int id);
	void save(Artiste artiste);
	List<Artiste> list();
	void delete(String id);
}
