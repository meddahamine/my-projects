package net.sigma.spring.dao;

import java.util.List;

import net.sigma.spring.model.Admin;

public interface IAdminDAO {

	public void save(Admin admin);
	
	public void update(Admin admin);
	
	public void delete(int adminId);
	
	public Admin get(int adminId);
	
	public List<Admin> list(String type);
	
	public List<Admin> listAll();
}
