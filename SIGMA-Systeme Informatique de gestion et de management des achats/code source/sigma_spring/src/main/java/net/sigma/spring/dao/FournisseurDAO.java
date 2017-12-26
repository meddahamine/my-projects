package net.sigma.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import net.sigma.spring.model.Files;
import net.sigma.spring.model.Fournisseur;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * An implementation of the FournisseurDAO interface.
 * @author www.sigma.net
 *
 */
public class FournisseurDAO implements IFournisseurDAO {

	private JdbcTemplate jdbcTemplate;
	
	public FournisseurDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public FournisseurDAO(){

	}
	public void save(Fournisseur fournisseur) {
		// insert
		String sql = "INSERT INTO fournisseur (fournisseur_id, name, email, address, telephone)"
					+ " VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, fournisseur.getId(), fournisseur.getName(), fournisseur.getEmail(), fournisseur.getAddress(), fournisseur.getTelephone());
	}
	
	public void update(Fournisseur fournisseur) {
		// update
		String sql = "UPDATE fournisseur SET name=?, email=?, address=?, "
					+ "telephone=?,  num_siret=?, code_ape=?, libelle_code_ape=?, raison_social=?, nom_societe=?, date_creation=?, type_achat=?, code_famille=?, libelle_famille=?, site_web=?, adresse_societe=?, code_postal=?, ville_societe=? WHERE fournisseur_id=?";
		jdbcTemplate.update(sql, fournisseur.getName(), fournisseur.getEmail(),
				fournisseur.getAddress(), fournisseur.getTelephone(), fournisseur.getNum_siret(), fournisseur.getCode_ape(), fournisseur.getLibelle_code_ape(), fournisseur.getRaison_social(),
				fournisseur.getNom_societe(), fournisseur.getDate_creation(), fournisseur.getType_achat(), fournisseur.getCode_famille(), fournisseur.getLibelle_famille(), fournisseur.getSite_web(), fournisseur.getAdresse_societe(), fournisseur.getCode_postal(), 
				fournisseur.getVille_societe(), fournisseur.getId());
	}

	public void delete(int fournisseurId) {
		String sql = "DELETE FROM fournisseur WHERE fournisseur_id=?";
		jdbcTemplate.update(sql, fournisseurId);
	}

	public List<Fournisseur> list() {
		String sql = "SELECT * FROM fournisseur ORDER BY name";
		List<Fournisseur> listFournisseur = jdbcTemplate.query(sql, new RowMapper<Fournisseur>() {

			public Fournisseur mapRow(ResultSet rs, int rowNum) throws SQLException {
				Fournisseur aFournisseur = new Fournisseur();
	
				aFournisseur.setId(rs.getInt("fournisseur_id"));
				aFournisseur.setName(rs.getString("name"));
				aFournisseur.setEmail(rs.getString("email"));
				aFournisseur.setAddress(rs.getString("address"));
				aFournisseur.setTelephone(rs.getString("telephone"));
				aFournisseur.setLogo(rs.getString("logo"));
				aFournisseur.setScore(rs.getInt("score"));
				
				return aFournisseur;
			}
			
		});
		
		return listFournisseur;
	}

	public Fournisseur get(int fournisseurId) {
		String sql = "SELECT * FROM fournisseur WHERE fournisseur_id=" + fournisseurId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Fournisseur>() {

			public Fournisseur extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Fournisseur fournisseur = new Fournisseur();
					fournisseur.setId(rs.getInt("fournisseur_id"));
					fournisseur.setName(rs.getString("name"));
					fournisseur.setEmail(rs.getString("email"));
					fournisseur.setAddress(rs.getString("address"));
					fournisseur.setTelephone(rs.getString("telephone"));
					fournisseur.setNum_siret(rs.getInt("num_siret"));
					fournisseur.setCode_ape(rs.getString("code_ape"));
					fournisseur.setLibelle_code_ape(rs.getString("libelle_code_ape"));
					fournisseur.setRaison_social(rs.getString("raison_social"));
					fournisseur.setNom_societe(rs.getString("nom_societe"));
					fournisseur.setDate_creation(rs.getInt("date_creation"));
					fournisseur.setType_achat(rs.getString("type_achat"));
					fournisseur.setCode_famille(rs.getInt("code_famille"));
					fournisseur.setLibelle_famille(rs.getString("libelle_famille"));
					fournisseur.setSite_web(rs.getString("site_web"));
					fournisseur.setAdresse_societe(rs.getString("adresse_societe"));
					fournisseur.setCode_postal(rs.getInt("code_postal"));
					fournisseur.setVille_societe(rs.getString("ville_societe"));
					fournisseur.setLogo(rs.getString("logo"));
					fournisseur.setScore(rs.getInt("score"));
					return fournisseur;
				}
				return null;
			}
		});
	}

	public void saveFile(Files file) {
		String sql = "INSERT INTO files (filename, notes, type, file, id_fournisseur) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sql, file.getFilename(),file.getNotes(), file.getType(), file.getType(), file.getId_fournisseur());
		
	}

	public List<Files> listFiles() {
		String sql = "SELECT * FROM files";
		List<Files> listFile = jdbcTemplate.query(sql, new RowMapper<Files>() {

			public Files mapRow(ResultSet rs, int rowNum) throws SQLException {
				Files afile = new Files();
				afile.setId(rs.getInt("id_file"));
				afile.setFilename(rs.getString("filename"));
				afile.setNotes(rs.getString("notes"));
				afile.setType(rs.getString("type"));
				afile.setFile(rs.getBytes("file"));
				afile.setId_fournisseur(rs.getInt("id_fournisseur"));
				return afile;
			}
		});
		return listFile;
	}

	public void deleteFile(int fileId) {
		String sql = "DELETE FROM files WHERE id_file=?";
		jdbcTemplate.update(sql, fileId);
	}

	public Files find(int fileId) {
		String sql = "SELECT * FROM files WHERE id_file=" + fileId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Files>() {

			public Files extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Files afile = new Files();
					afile.setId(rs.getInt("id_file"));
					afile.setFilename(rs.getString("filename"));
					afile.setNotes(rs.getString("notes"));
					afile.setType(rs.getString("type"));
					afile.setFile(rs.getBytes("file"));
					return afile;
				}
				return null;
			}
		});
	}

	public void evaluer(Fournisseur fournisseur) {
		// update
				String sql = "UPDATE fournisseur SET score=? WHERE fournisseur_id=?";
				jdbcTemplate.update(sql, fournisseur.getScore(), fournisseur.getId());
	}
}
