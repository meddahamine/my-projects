package dao;

import java.util.List;
import model.Album;

public interface AlbumDao {

	Album findById(int id);
	void save(Album album);
	List<Album> list();
	void delete(int id);
	void update(Album al);
}
