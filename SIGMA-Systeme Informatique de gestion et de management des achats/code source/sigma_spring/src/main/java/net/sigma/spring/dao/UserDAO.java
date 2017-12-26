package net.sigma.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.sigma.spring.model.Fournisseur;
import net.sigma.spring.model.User;

public class UserDAO implements IUserDAO{

	private JdbcTemplate jdbcTemplate;
	
	public UserDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public UserDAO(){
		super();
	}
	public User getUserLogin(User userLogin){
		String query = "Select * from utilisateur where username ='"+userLogin.getUserName()+"' and password ='"+userLogin.getPassword()+"'";
		return jdbcTemplate.query(query, new ResultSetExtractor<User>() {

			public User extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					User aUser = new User();
					aUser.setId(rs.getInt("user_id"));
					aUser.setUserName(rs.getString("username"));
					aUser.setPassword(rs.getString("password"));
					aUser.setType(rs.getString("type"));
					return aUser;
				}
				return null;
			}
			});
	}

	public void addUser(User user) {
		String sql = "INSERT INTO utilisateur (username, password, type)"
				+ " VALUES (?, ?, ?)";
	jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getType());
	}

	public int getLastId() {
	    String query = "SELECT MAX(user_id) AS id FROM utilisateur";
	    List<Integer> lastId = jdbcTemplate.query(query, new RowMapper<Integer>() {

			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return rs.getInt("id");
			}
		});
	    
		return lastId.get(0).intValue();
	}
	
	public List<User> list(String type) {
		System.out.println(type);
		String sql = "SELECT * FROM utilisateur  where type='" + type+"';";
		List<User> listUser = jdbcTemplate.query(sql, new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User aUser = new User();
	
				aUser.setId(rs.getInt("user_id"));
				aUser.setUserName(rs.getString("username"));
				aUser.setType(rs.getString("type"));
			
				return aUser;
			}
			
		});
		
		return listUser;
	}
	
	public User get(int userId) {
		String sql = "SELECT * FROM utilisateur WHERE user_id=" + userId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {

			public User extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("user_id"));
					user.setUserName(rs.getString("username"));
					user.setType(rs.getString("type"));
					user.setPassword(rs.getString("password"));
					return user;
				}
				
				return null;
			}
			
		});
	}
	
	public void saveOrUpdate(User user) {
		if (user.getId() > 0) {
			String sql = "UPDATE utilisateur SET username=?, password=?, type=? "
						+ " WHERE user_id=?";
			jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), 
					user.getType(), user.getId());
		} else {
			String sql = "INSERT INTO utilisateur (username, password, type)"
						+ " VALUES (?, ?, ?)";
			jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getType());
		}
		
	}
	
	public void delete(int userId) {
		String sql = "DELETE FROM utilisateur WHERE user_id=?";
		jdbcTemplate.update(sql, userId);
	}
}