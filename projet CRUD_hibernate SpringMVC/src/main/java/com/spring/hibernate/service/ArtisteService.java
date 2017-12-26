package com.spring.hibernate.service;

import java.util.List;
import com.spring.hibernate.model.Artiste;

public interface ArtisteService {

	Artiste findById(int id);
	void save(Artiste artiste);
	List<Artiste> list();
	void update(Artiste artiste);
	void delete(String id);
}
