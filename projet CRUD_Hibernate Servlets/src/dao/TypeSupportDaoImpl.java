package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import model.TypeSupport;
import util.HibernateUtil;

public class TypeSupportDaoImpl implements TypeSupportDao{

	@Override
	public TypeSupport findById(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Object ts = session.get(TypeSupport.class, id);
		if (ts==null) throw new RuntimeException("Type Support introuvable");
		session.getTransaction().commit();
		return (TypeSupport)ts;
	}

	@Override
	public void save(TypeSupport typesupport) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			session.save(typesupport);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		session.getTransaction().commit();
	}

	@Override
	public List<TypeSupport> list() {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query req=session.createQuery("select ts from TypeSupport ts");//fom le nom de la classe (model)
		List<TypeSupport> typeSupports= req.list();
		session.getTransaction().commit();
		return typeSupports;
	}

	@Override
	public void delete(int id) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Object ts = session.get(TypeSupport.class, id);
		if (ts==null) throw new RuntimeException("Type Support introuvable");
		session.delete(ts);
		session.getTransaction().commit();
	}

	@Override
	public void update(TypeSupport ts) {
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(ts);
		session.getTransaction().commit();
	}

}