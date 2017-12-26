package net.sigma.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.sigma.spring.model.Entite;

public class EntiteDAO implements IEntiteDAO {

	private JdbcTemplate jdbcTemplate;
	
	public EntiteDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public EntiteDAO() {
		super();
	}
	
	public void save(Entite entite) {
		// insert
		String sql = "INSERT INTO entite (name, email, address, telephone)"
					+ " VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, entite.getName(), entite.getEmail(), entite.getAddress(), entite.getTelephone());
		
	}

	public void update(Entite entite) {
		// update
		String sql = "UPDATE entite SET name=?, email=?, address=?, "
							+ "telephone=? WHERE entite_id=?";
		jdbcTemplate.update(sql, entite.getName(), entite.getEmail(),
						entite.getAddress(), entite.getTelephone(), entite.getId());
		
	}

	public void delete(int entiteId) {
		String sql = "DELETE FROM entite WHERE entite_id=?";
		jdbcTemplate.update(sql, entiteId);
		
	}

	public Entite get(int entiteId) {
		String sql = "SELECT * FROM entite WHERE entite_id=" + entiteId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Entite>() {

			public Entite extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Entite entite = new Entite();
					entite.setId(rs.getInt("entite_id"));
					entite.setName(rs.getString("name"));
					entite.setEmail(rs.getString("email"));
					entite.setAddress(rs.getString("address"));
					entite.setTelephone(rs.getString("telephone"));
					entite.setMetric(rs.getInt("metric"));
					return entite;
				}
				return null;
			}
		});
	}

	public List<Entite> list() {
		String sql = "SELECT * FROM entite ORDER BY name";
		List<Entite> listentite = jdbcTemplate.query(sql, new RowMapper<Entite>() {

			public Entite mapRow(ResultSet rs, int rowNum) throws SQLException {
				Entite aentite = new Entite();
	
				aentite.setId(rs.getInt("entite_id"));
				aentite.setName(rs.getString("name"));
				aentite.setEmail(rs.getString("email"));
				aentite.setAddress(rs.getString("address"));
				aentite.setTelephone(rs.getString("telephone"));
				aentite.setMetric(rs.getInt("metric"));
				
				return aentite;
			}
			
		});
		
		return listentite;
	}

}
