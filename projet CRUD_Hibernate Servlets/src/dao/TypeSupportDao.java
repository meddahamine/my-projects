package dao;

import java.util.List;

import model.TypeSupport;

public interface TypeSupportDao {
	TypeSupport findById(int id);
	void save(TypeSupport typesupport);
	List<TypeSupport> list();
	void delete(int id);
	void update(TypeSupport ts);
}