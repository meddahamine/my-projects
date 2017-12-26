package com.spring.hibernate.dao;

import java.util.List;

import com.spring.hibernate.model.TypeSupport;

public interface TypeSupportDao {
	TypeSupport findById(int id);
	void save(TypeSupport typesupport);
	List<TypeSupport> list();
	void delete(String id);
}