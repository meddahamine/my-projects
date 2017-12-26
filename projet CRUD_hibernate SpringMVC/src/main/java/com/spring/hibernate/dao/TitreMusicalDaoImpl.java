package com.spring.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.spring.hibernate.model.TitreMusical;

@Repository("titreMusicalDao")
public class TitreMusicalDaoImpl extends AbstractDao<Integer, TitreMusical> implements TitreMusicalDao{

	@SuppressWarnings("unchecked")
	public List<TitreMusical> list() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Distinct Pour les doublons
		List<TitreMusical> list = (List<TitreMusical>) criteria.list();
		return list;
	}

	public TitreMusical findById(int id) {
			TitreMusical titremusical = getByKey(id);
			return titremusical;
	}

	public void save(TitreMusical titremusical) {
		persist(titremusical);
	}

	public void delete(String id) {
		int idInt = Integer.parseInt(id);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", idInt));
		TitreMusical titremusical = (TitreMusical)crit.uniqueResult();
		delete(titremusical);
	}

	public List<TitreMusical> findByIdforgien(int id) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Distinct Pour les doublons
		List<TitreMusical> list = (List<TitreMusical>) criteria.addQueryHint("where id="+id).list();
		return list;
	}
}
