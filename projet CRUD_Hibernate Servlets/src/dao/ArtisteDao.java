package dao;

import java.util.List;

import model.Artiste;

public interface ArtisteDao {

	Artiste findById(int id);
	void save(Artiste artiste);
	List<Artiste> list();
	void delete(int id);
	void update(Artiste ar);
}
