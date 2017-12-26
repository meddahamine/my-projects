package com.spring.hibernate.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.spring.hibernate.model.Album;

@Repository("albumDao")
public class AlbumDaoImpl extends AbstractDao<Integer, Album> implements AlbumDao{

	@SuppressWarnings("unchecked")
	public List<Album> list() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Distinct Pour les doublons
		List<Album> list = (List<Album>) criteria.list();
		return list;
	}
	
	public Album findById(int id) {
		Album album = getByKey(id);
		return album;
	}

	public void save(Album album) {
		persist(album);
	}

	public void delete(String id) {
		int idInt = Integer.parseInt(id);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", idInt));
		Album album = (Album)crit.uniqueResult();
		delete(album);	
	}

}
