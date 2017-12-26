package net.sigma.spring.dao;

import java.sql.SQLException;
import java.util.List;

import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;

public interface IUserDAO {

	public abstract User getUserLogin(User userLogin);
	
	public abstract void addUser(User user);
	
	//To get last 'id' added
	public abstract int getLastId();
	
	public List<User> list(String type);
	
	public User get(int userId);
	
	public void saveOrUpdate(User user);
	
	public void delete(int userId);
	
}
