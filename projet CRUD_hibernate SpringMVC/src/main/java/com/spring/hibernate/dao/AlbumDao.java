package com.spring.hibernate.dao;

import java.util.List;
import com.spring.hibernate.model.Album;

public interface AlbumDao {

	Album findById(int id);
	void save(Album album);
	List<Album> list();
	void delete(String id);
}
