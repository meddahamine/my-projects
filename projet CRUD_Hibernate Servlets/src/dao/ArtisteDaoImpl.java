package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import model.Artiste;
import util.HibernateUtil;

public class ArtisteDaoImpl implements ArtisteDao{

	@Override
	public Artiste findById(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Object ar = session.get(Artiste.class, id);
		if (ar==null) throw new RuntimeException("Album introuvable");
		session.getTransaction().commit();
		return (Artiste)ar;
	}

	@Override
	public void save(Artiste artiste) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(artiste);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
	}

	@Override
	public List<Artiste> list() {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query req=session.createQuery("select a from Artiste a");//fom le nom de la classe (model)
		List<Artiste> artistes= req.list();
		session.getTransaction().commit();
		return artistes;
	}

	@Override
	public void delete(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Object ar = session.get(Artiste.class, id);
		if (ar==null) throw new RuntimeException("Artiste introuvable");
		session.delete(ar);
		session.getTransaction().commit();
	}

	@Override
	public void update(Artiste ar) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(ar);
		session.getTransaction().commit();
	}

	

}
