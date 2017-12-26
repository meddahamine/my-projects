package com.spring.hibernate.service;

import java.util.List;
import com.spring.hibernate.model.Album;

public interface AlbumService {

	Album findById(int id);
	void save(Album album);
    List<Album> list();
    void update(Album album);
    void delete(String id);
}
