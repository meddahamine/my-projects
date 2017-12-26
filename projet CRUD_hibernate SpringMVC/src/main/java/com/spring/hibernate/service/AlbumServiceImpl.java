package com.spring.hibernate.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.hibernate.dao.AlbumDao;
import com.spring.hibernate.model.Album;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService{

	@Autowired
	private AlbumDao albumDao;
	
	public Album findById(int id) {
		return albumDao.findById(id);
	}

	public void save(Album album) {
		albumDao.save(album);
	}

	public List<Album> list() {
		return albumDao.list();
	}

	public void update(Album album) {
		Album entity = albumDao.findById(album.getId());
		if(entity!=null){
			entity.setNom(album.getNom());
			entity.setIdArtist(album.getIdArtist());
		}
	}

	public void delete(String id) {
		albumDao.delete(id);
	}

}
