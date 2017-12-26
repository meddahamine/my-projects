package net.sigma.spring.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import net.sigma.spring.model.Admin;
import net.sigma.spring.model.Entite;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by root on 28/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class EntiteDAOTest {
	
	@Mock
	JdbcTemplate jdbcTemplateMock;
	@Mock
	Entite entiteMock;
	@Mock
	DataSource dataSource;
	@Mock
	Connection connection;
	@Mock
	UserDAO user;
	
	@InjectMocks
	EntiteDAO entiteDao;
	
	@Test(expected = NullPointerException.class)
	public void saveTest() throws SQLException{
		String sql = "INSERT INTO entite (name, email, address, telephone)"
				+ " VALUES (?, ?, ?, ?)";
		int id=8;
		String name="autre";
		String email="entee@sigma.com";
		String addresse="rouen";
		String telephone="0547845469";
		int metric=2;
		
		
		DataSource dataSource=null;
		connection=dataSource.getConnection("root","root");
		jdbcTemplateMock=new JdbcTemplate((DataSource) connection);
		
		//entiteDao=new EntiteDAO();
		entiteMock=new Entite(id, name, email, addresse, telephone, metric);
		entiteDao.save(entiteMock);
		
		 Mockito.verify(jdbcTemplateMock, Mockito.times(1)).update(sql,entiteMock.getId(), entiteMock.getName(), entiteMock.getEmail(),
					entiteMock.getAddress(), entiteMock.getTelephone(),entiteMock.getMetric());
		
		 
		 assertEquals(entiteMock.getAddress(), addresse);
	        assertEquals(entiteMock.getName(),name);
	        assertEquals(entiteMock.getEmail(),email);
	        assertEquals(entiteMock.getTelephone(),telephone);
	        assertEquals(entiteMock.getId(),id);
	        assertEquals(entiteMock.getMetric(), metric);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void updateTest() throws SQLException{
		String sql = "UPDATE entite SET name=?, email=?, address=?, "
				+ "telephone=? WHERE entite_id=?";
		int id=8;
		String name="autre";
		String email="entee@sigma.com";
		String addresse="rouen";
		String telephone="0547845469";
		int metric=2;

	DataSource dataSource=null;
connection=dataSource.getConnection("root","root");
	jdbcTemplateMock=new JdbcTemplate((DataSource) connection);
	//When
	entiteMock=new Entite(id, name, email, addresse, telephone,metric);
	entiteDao.update(entiteMock);
	//Then
	
    Mockito.verify(jdbcTemplateMock, Mockito.times(1)).update(sql,entiteMock);
    
    assertEquals(entiteMock.getAddress(), addresse);
    assertEquals(entiteMock.getName(),name);
    assertEquals(entiteMock.getEmail(),email);
    assertEquals(entiteMock.getTelephone(),telephone);
    assertEquals(entiteMock.getId(),id);
	}
	
	@Test(expected = NullPointerException.class)
	public void deleteTest() throws SQLException{
		//Given
		String sql = "DELETE FROM entite WHERE entite_id=?";
				int id=8;
				DataSource dataSource=null;
				connection=dataSource.getConnection("root","root");
				jdbcTemplateMock=new JdbcTemplate((DataSource) connection);

				//DataSource dataSource;
				//jdbcMock=new JdbcTemplate(dataSource);
				//When
				
				entiteDao.delete(id);
				//Then
		        Mockito.verify(jdbcTemplateMock, Mockito.times(1)).update(sql,id);
	}
	
	@Test(expected = NullPointerException.class)
	public void getTest(){
		//Given
		int id=8;
		String name="autre";
		String email="entee@sigma.com";
		String addresse="rouen";
		String telephone="0547845469";
		int metric=2;
		entiteMock=new Entite(id, name, email, addresse, telephone,metric);
		entiteDao.update(entiteMock);
		

				//When
		String sql = "SELECT * FROM entite WHERE entite_id=" + id;
		Entite ent=new Entite();
		ent= entiteDao.get(id);



				assertEquals(entiteMock, ent);
	}
	
	
	
}
