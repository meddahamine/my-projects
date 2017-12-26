package com.spring.hibernate.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.spring.hibernate.model.TypeSupport;

@Repository("typeSupportDao")
public class TypeSupportDaoImpl extends AbstractDao<Integer, TypeSupport> implements TypeSupportDao{


	@SuppressWarnings("unchecked")
	public List<TypeSupport> list() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Distinct Pour les doublons
		List<TypeSupport> list = (List<TypeSupport>) criteria.list();
		return list;
	}
	
	public void save(TypeSupport typesupport) {
		persist(typesupport);
	}
	
	public TypeSupport findById(int id) {
		TypeSupport typesupport = getByKey(id);
		return typesupport;
	}
	
	public void delete(String id) {
		//System.out.println("id= "+id);
		int idInt = Integer.parseInt(id);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", idInt));
		TypeSupport typeSupport = (TypeSupport)crit.uniqueResult();
		delete(typeSupport);
	}
}