package com.spring.hibernate.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.spring.hibernate.model.Artiste;

@Repository("artisteDao")
public class ArtisteDaoImpl extends AbstractDao<Integer, Artiste> implements ArtisteDao{

	public void save(Artiste artiste) {
		persist(artiste);
	}

	@SuppressWarnings("unchecked")
	public List<Artiste> list() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Distinct Pour les doublons
		List<Artiste> list = (List<Artiste>) criteria.list();
		return list;
	}

	public void delete(String id) {
		int idInt = Integer.parseInt(id);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", idInt));
		Artiste artiste = (Artiste)crit.uniqueResult();
		delete(artiste);
	}

	public Artiste findById(int id) {
		Artiste artiste = getByKey(id);
		return artiste;
	}

}
