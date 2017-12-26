package net.sigma.spring.dao;

import java.util.List;
import net.sigma.spring.model.Archive;

public interface IArchivesDAO {

	public void save(Archive archive);
	
	public void update(Archive archive);
	
	public Archive get(int archiveId);
	
	public List<Archive> list();
	
	public void delete(int archiveId);
}
