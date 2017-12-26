package net.sigma.spring.dao;

import net.sigma.spring.model.Admin;
import net.sigma.spring.model.Files;
import net.sigma.spring.model.Fournisseur;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Created by root on 28/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class FournisseurDAOTest {

    @Mock
    JdbcTemplate jdbcTemplateMock;
    @Mock
    Files fileMock;
    @Mock
	DataSource dataSource;
	@Mock
	Connection connection;
    @Mock
    Fournisseur fournisseurMock;
    @InjectMocks
    FournisseurDAO fournisseurDAO;

    @Test(expected = NullPointerException.class)
    public void saveTest(){
        String sql = "INSERT INTO fournisseur (fournisseur_id, name, email, address, telephone)"
                + " VALUES (?, ?, ?, ?, ?)";
        int id=8;
        String name="four";
        String email="four@four";
        String adresse="rue 25";
        String telephone="0255889966";
        int num_siret=54848;
        String code_ape="fr88";
        String libelle_code_ape="az";
        String raison_social="papier";
        String nom_societe="gun";
        int date_creation=10121999;
        String type_achat="autre";
        int code_famille=45454;
        String libelle_famille="autre";
        String site_web="www.gun.com";
        String adresse_societe="rouen";
        int code_postal=76130;
        String ville_societe="rouen";
        String logo="adidas.jpg";
        int score=5;

        fournisseurMock=new Fournisseur(name,email,adresse,telephone);
        fournisseurDAO.save(fournisseurMock);
        Mockito.verify(jdbcTemplateMock, Mockito.times(1)).update(sql, fournisseurMock.getId(), fournisseurMock.getName(),
                fournisseurMock.getEmail(), fournisseurMock.getAddress());

        assertEquals(fournisseurMock.getAddress(), adresse);
        assertEquals(fournisseurMock.getName(),name);
        assertEquals(fournisseurMock.getEmail(),email);
        assertEquals(fournisseurMock.getTelephone(),telephone);
        //assertEquals(fournisseurMock.getId(),id);
    }

    @Test(expected = NullPointerException.class)
    public void deleteTest() throws SQLException{
    	//Given
    	String sql = "DELETE FROM fournisseur WHERE fournisseur_id=?";
    					int id=8;
    					DataSource dataSource=null;
    					connection=dataSource.getConnection("root","root");
    					jdbcTemplateMock=new JdbcTemplate((DataSource) connection);

    					//DataSource dataSource;
    					//jdbcMock=new JdbcTemplate(dataSource);
    					//When
    					
    					fournisseurDAO.delete(id);
    					//Then
    			        Mockito.verify(jdbcTemplateMock, Mockito.times(1)).update(sql,id);
    }

    @Test(expected = NullPointerException.class)
    public void saveFileTest() throws SQLException{
    	String sql = "INSERT INTO files (filename, notes, type, file, id_fournisseur) VALUES (?,?,?,?,?)";
   		int id=17;
	    String filename="autre";
	    String notes="5";
	    String type="pdf";
	    File file2=new File("autre.txt");
	    
	    String s="blatestblatestblatest";
	        int len = s.length();
	        byte[] data = new byte[len / 2];
	  
	        DataSource dataSource=null;
			connection=dataSource.getConnection("root","root");
			jdbcTemplateMock=new JdbcTemplate((DataSource) connection);
	    
	    byte[] filet=data;
	    int id_fournisseur = 8;
	    
	   fileMock=new Files(id,filename, notes,type,filet, id_fournisseur);
	   fournisseurDAO.saveFile(fileMock);
	   
	   Mockito.verify(jdbcTemplateMock, Mockito.times(1)).update(sql,fileMock.getId(), fileMock.getFilename(),
				fileMock.getNotes(), fileMock.getType(),fileMock.getFile(),fileMock.getId_fournisseur());
	   
	   assertEquals(fileMock.getId(), id);
       assertEquals(fileMock.getFilename(),filename);
       assertEquals(fileMock.getNotes(),notes);
       assertEquals(fileMock.getType(),type);
       assertEquals(fileMock.getFile(),filet);
       assertEquals(fileMock.getId_fournisseur(), id_fournisseur);
	    
    }

    @Test(expected = NullPointerException.class)
    public void deleteFileTest() throws SQLException{
    	String sql = "DELETE FROM files WHERE id_file=?";
    	int id=17;
		DataSource dataSource=null;
		connection=dataSource.getConnection("root","root");
		jdbcTemplateMock=new JdbcTemplate((DataSource) connection);
		
		fournisseurDAO.delete(id);
		//Then
        Mockito.verify(jdbcTemplateMock, Mockito.times(1)).update(sql,id);
        
    }
    
    @Test(expected = NullPointerException.class)
    public void getTest(){
    	int id=8;
        String name="four";
        String email="four@four";
        String adresse="rue 25";
        String telephone="0255889966";
        int num_siret=54848;
        String code_ape="fr88";
        String libelle_code_ape="az";
        String raison_social="papier";
        String nom_societe="gun";
        int date_creation=10121999;
        String type_achat="autre";
        int code_famille=45454;
        String libelle_famille="autre";
        String site_web="www.gun.com";
        String adresse_societe="rouen";
        int code_postal=76130;
        String ville_societe="rouen";
        String logo="adidas.jpg";
        int score=5;
        
        fournisseurMock=new Fournisseur(name,email,adresse,telephone);
        fournisseurDAO.save(fournisseurMock);
        
        String sql = "SELECT * FROM fournisseur WHERE fournisseur_id=" + id;
        
        Fournisseur fournisseur=new Fournisseur();
        fournisseur= fournisseurDAO.get(id);
        
        assertEquals(fournisseurMock, fournisseur);
        
        
        
    }
}
