package net.sigma.spring.dao;

import java.util.List;

import net.sigma.spring.model.Entite;

public interface IEntiteDAO {

public void save(Entite entite);
	
	public void update(Entite entite);
	
	public void delete(int entite);
	
	public Entite get(int entiteId);
	
	public List<Entite> list();
}
