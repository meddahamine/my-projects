package com.spring.hibernate.service;

import java.util.List;
import com.spring.hibernate.model.TitreMusical;

public interface TitreMusicalService {

	TitreMusical findById(int id);
	void save(TitreMusical typesupport);
    List<TitreMusical> list();
    List<TitreMusical> findByIdforgien(int id);
    void update(TitreMusical typesupport);
    void delete(String id);
}
