package com.spring.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.hibernate.dao.TypeSupportDao;
import com.spring.hibernate.model.TypeSupport;


@Service("typeSupportService")
@Transactional
public class TypeSupportServiceImpl implements TypeSupportService {
	
	@Autowired
	private TypeSupportDao typeSupport;
	
	public void save(TypeSupport typesupport) {
		typeSupport.save(typesupport);
	}
	
	public List<TypeSupport> list() {
		return typeSupport.list();
	}

	public TypeSupport findById(int id) {
		return typeSupport.findById(id);
	}
	
	public void update(TypeSupport typesupport) {
		TypeSupport entity = typeSupport.findById(typesupport.getId());
		if(entity!=null){
			entity.setType(typesupport.getType());
		}
	}
	
	public void delete(String id) {
		typeSupport.delete(id);
	}
}
