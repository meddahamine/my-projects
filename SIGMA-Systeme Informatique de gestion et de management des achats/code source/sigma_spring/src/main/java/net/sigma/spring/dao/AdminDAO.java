package net.sigma.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.sigma.spring.model.Admin;
import net.sigma.spring.model.Fournisseur;

public class AdminDAO implements IAdminDAO{

	private JdbcTemplate jdbcTemplate;
	
	
	public AdminDAO(){
		
	}
	
	public AdminDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void save(Admin admin) {
		// insert
				String sql = "INSERT INTO admin (admin_id, name, email, address, telephone)"
							+ " VALUES (?, ?, ?, ?, ?)";
				jdbcTemplate.update(sql, admin.getId(), admin.getName(), admin.getEmail(),
						admin.getAddress(), admin.getTelephone());
	}

	public void update(Admin admin) {
		// update
				String sql = "UPDATE admin SET name=?, email=?, address=?, "
							+ "telephone=? WHERE admin_id=?";
				jdbcTemplate.update(sql, admin.getName(), admin.getEmail(),
						admin.getAddress(), admin.getTelephone(), admin.getId());
	}

	public void delete(int adminId) {
		String sql = "DELETE FROM admin WHERE admin_id=?";
		jdbcTemplate.update(sql, adminId);
	}

	public Admin get(int adminId) {
		String sql = "SELECT * FROM admin WHERE admin_id=" + adminId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Admin>() {

			public Admin extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Admin admin = new Admin();
					admin.setId(rs.getInt("admin_id"));
					admin.setName(rs.getString("name"));
					admin.setEmail(rs.getString("email"));
					admin.setAddress(rs.getString("address"));
					admin.setTelephone(rs.getString("telephone"));
					return admin;
				}
				
				return null;
			}
		});
	}

	public List<Admin> list(String type) {

		String sql = "SELECT * FROM utilisateur u, admin a where a.admin_id=u.user_id and u.type='"+type+"'  ORDER BY name";
		List<Admin> listAdmin = jdbcTemplate.query(sql, new RowMapper<Admin>() {

			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admin aAdmin = new Admin();
	
				aAdmin.setId(rs.getInt("admin_id"));
				aAdmin.setName(rs.getString("name"));
				aAdmin.setEmail(rs.getString("email"));
				aAdmin.setAddress(rs.getString("address"));
				aAdmin.setTelephone(rs.getString("telephone"));
				
				return aAdmin;
			}
			
		});
		
		return listAdmin;
	}

	public List<Admin> listAll() {

		String sql = "SELECT * FROM admin";
		List<Admin> listAdmin = jdbcTemplate.query(sql, new RowMapper<Admin>() {

			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admin aAdmin = new Admin();
	
				aAdmin.setId(rs.getInt("admin_id"));
				aAdmin.setName(rs.getString("name"));
				aAdmin.setEmail(rs.getString("email"));
				aAdmin.setAddress(rs.getString("address"));
				aAdmin.setTelephone(rs.getString("telephone"));
				
				return aAdmin;
			}
			
		});
		
		return listAdmin;
	}
}
