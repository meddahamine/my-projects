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
import net.sigma.spring.model.Archive;

public class ArchivesDAO implements IArchivesDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public ArchivesDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public ArchivesDAO(){
		super();
	}
	public void save(Archive archive) {
		// insert
		String sql = "INSERT INTO archive (archive_id, name, email, address, telephone, type)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
	jdbcTemplate.update(sql, archive.getId(), archive.getName(), archive.getEmail(), archive.getAddress(),
			archive.getTelephone(),archive.getType());
	}

	public void update(Archive archive) {
		// update
		String sql = "UPDATE archive SET name=?, email=?, address=?, telephone=?, type=? "
				+ " WHERE archive_id=?";
	jdbcTemplate.update(sql, archive.getName(), archive.getEmail(), archive.getAddress(), 
			archive.getTelephone(), archive.getType(), archive.getId());
	}

	public Archive get(int archiveId) {
		String sql = "SELECT * FROM archive WHERE archive_id=" + archiveId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Archive>() {

			public Archive extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Archive archive = new Archive();
					archive.setId(rs.getInt("admin_id"));
					archive.setName(rs.getString("name"));
					archive.setEmail(rs.getString("email"));
					archive.setAddress(rs.getString("address"));
					archive.setTelephone(rs.getString("telephone"));
					archive.setType(rs.getString("type"));
					return archive;
				}
				
				return null;
			}
		});
	}

	public List<Archive> list() {

		String sql = "SELECT * FROM archive ORDER BY name";
		List<Archive> listArchive = jdbcTemplate.query(sql, new RowMapper<Archive>() {

			public Archive mapRow(ResultSet rs, int rowNum) throws SQLException {
				Archive aArchive = new Archive();
	
				aArchive.setId(rs.getInt("archive_id"));
				aArchive.setName(rs.getString("name"));
				aArchive.setEmail(rs.getString("email"));
				aArchive.setAddress(rs.getString("address"));
				aArchive.setTelephone(rs.getString("telephone"));
				aArchive.setType(rs.getString("type"));
				return aArchive;
			}
			
		});
		
		return listArchive;
	}

	public void delete(int archiveId) {
		String sql = "DELETE FROM archive WHERE archive_id=?";
		jdbcTemplate.update(sql, archiveId);
	}

}
