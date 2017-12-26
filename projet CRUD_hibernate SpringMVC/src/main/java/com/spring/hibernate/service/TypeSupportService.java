package com.spring.hibernate.service;

import java.util.List;

import com.spring.hibernate.model.TypeSupport;

public interface TypeSupportService {
	
	TypeSupport findById(int id);
	void save(TypeSupport typesupport);
    List<TypeSupport> list();
    void update(TypeSupport typesupport);
    void delete(String id);
}
