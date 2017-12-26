package dao;

import java.util.List;

import model.TitreMusical;

public interface TitreMusicalDao {

	TitreMusical findById(int id);
	void save(TitreMusical titremusical);
	List<TitreMusical> list();
	void delete(int id);
	void update(TitreMusical tm);
}
