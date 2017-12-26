package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;

import model.Album;

public class AlbumDaoImpl implements AlbumDao{

	@Override
	public Album findById(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Object al = session.get(Album.class, id);
		if (al==null) throw new RuntimeException("Album introuvable");
		session.getTransaction().commit();
		return (Album)al;
	}

	@Override
	public void save(Album album) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(album);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
	}

	@Override
	public List<Album> list() {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query req=session.createQuery("select a from Album a");//from le nom de la classe (model)
		List<Album> albums= req.list();
		session.getTransaction().commit();
		return albums;
	}

	@Override
	public void delete(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Object al = session.get(Album.class, id);
		if (al==null) throw new RuntimeException("Album introuvable");
		session.delete(al);
		session.getTransaction().commit();
	}

	@Override
	public void update(Album al) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(al);
		session.getTransaction().commit();
	}

}
