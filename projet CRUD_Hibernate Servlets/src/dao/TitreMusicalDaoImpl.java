package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import model.TitreMusical;
import util.HibernateUtil;

public class TitreMusicalDaoImpl implements TitreMusicalDao{

	@Override
	public TitreMusical findById(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Object tm = session.get(TitreMusical.class, id);
		if (tm==null) throw new RuntimeException("Titre Musical introuvable");
		session.getTransaction().commit();
		return (TitreMusical)tm;
	}

	@Override
	public void save(TitreMusical titremusical) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(titremusical);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
	}

	@Override
	public List<TitreMusical> list() {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query req=session.createQuery("select tm from TitreMusical tm");//fom le nom de la classe (model)
		List<TitreMusical> titreMusicals= req.list();
		session.getTransaction().commit();
		return titreMusicals;
	}

	@Override
	public void delete(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Object tm = session.get(TitreMusical.class, id);
		if (tm==null) throw new RuntimeException("Titre Musical introuvable");
		session.delete(tm);
		session.getTransaction().commit();
	}

	@Override
	public void update(TitreMusical tm) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(tm);
		session.getTransaction().commit();
	}

	
}
