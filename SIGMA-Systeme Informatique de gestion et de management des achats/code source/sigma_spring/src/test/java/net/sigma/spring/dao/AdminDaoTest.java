package net.sigma.spring.dao;

import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.validateMockitoUsage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.*;
import org.mockito.Mockito;
import org.mockito.invocation.MockHandler;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import junit.framework.Assert;
import net.sigma.spring.model.Admin;
import org.springframework.jdbc.core.ResultSetExtractor;

@RunWith(MockitoJUnitRunner.class)
public class AdminDaoTest{
	
	@Mock
	JdbcTemplate jdbcMock;
	@Mock
	Admin admin;
	@Mock
	DataSource dataSource;
	@Mock
	Connection connection;
	@Mock
	UserDAO user;

	@InjectMocks
	AdminDAO adminDAO;


	//IAdminDAO adminDAO;
	@Test(expected = NullPointerException.class)
	public void saveTest(){
		//Given

		String sql = "INSERT INTO admin (admin_id, name, email, address, telephone)"
				+ " VALUES (?, ?, ?, ?, ?)";
		int id=8;
		String name="autre";
		String email="autre@sigma.com";
		String adresse="rouen";
		String tel="0547845469";
		//jdbcMock.update(sql, admin.getName(), admin.getEmail(),
			//	admin.getAddress(), admin.getTelephone(), admin.getId());

		//DataSource dataSource;
		//jdbcMock=new JdbcTemplate(dataSource);
		//When
		//adminDAO=new AdminDAO(dataSource);
		admin=new Admin(id, name, email, adresse, tel);
		adminDAO.save(admin);
		//Then
		
        Mockito.verify(jdbcMock, Mockito.times(1)).update(sql,admin.getId(), admin.getName(), admin.getEmail(),
				admin.getAddress(), admin.getTelephone());
        
        assertEquals(admin.getAddress(), adresse);
        assertEquals(admin.getName(),name);
        assertEquals(admin.getEmail(),email);
        assertEquals(admin.getTelephone(),tel);
        assertEquals(admin.getId(),id);
	}
	
	@Test(expected = NullPointerException.class)
	public void updateTest() throws SQLException {
		//Given
		
				String sql = "UPDATE admin SET name=?, email=?, address=?, "
							+ "telephone=? WHERE fournisseur_id=?";
				int id=8;
				String name="autreupdate";
				String email="autre@sigma.com";
				String adresse="rouen";
				String tel="0547845469";

				DataSource dataSource=null;
		connection=dataSource.getConnection("root","root");
				jdbcMock=new JdbcTemplate((DataSource) connection);
				//When
				Admin admin=new Admin(id, name, email, adresse, tel);
				adminDAO.update(admin);
				//Then
				
		        Mockito.verify(jdbcMock, Mockito.times(1)).update(sql,admin);
		        
		        assertEquals(admin.getAddress(), adresse);
		        assertEquals(admin.getName(),name);
		        assertEquals(admin.getEmail(),email);
		        assertEquals(admin.getTelephone(),tel);
		        assertEquals(admin.getId(),id);
	}
	
	@Test (expected = NullPointerException.class)
	public void deleteTest(){
		//Given
		String sql = "DELETE FROM admin WHERE admin_id=?";
		int id=8;

		//DataSource dataSource;
		//jdbcMock=new JdbcTemplate(dataSource);
		//When
		
		adminDAO.delete(id);
		//Then
        Mockito.verify(jdbcMock, Mockito.times(1)).update(sql,id);
	}
	
	@Test(expected = NullPointerException.class)
	public void getTest(){
		//Given
		int id=14;
		String name="autresaisie";
		String email="autre@sigma.com";
		String adresse="rouen";
		String tel="0547845469";
		admin=new Admin(id, name, email, adresse, tel);
		adminDAO.save(admin);

		//When
		String sql = "SELECT * FROM admin WHERE admin_id="+id;
		Admin ad=new Admin();
		ad= adminDAO.get(id);


		//Then

		/*Mockito.verify(jdbcMock,Mockito.times(1)).query(sql, new ResultSetExtractor<Admin>() {

			public Admin extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Admin admin1 = new Admin();
					admin1.setId(rs.getInt("admin_id"));
					admin1.setName(rs.getString("name"));
					admin1.setEmail(rs.getString("email"));
					admin1.setAddress(rs.getString("address"));
					admin1.setTelephone(rs.getString("telephone"));
					return admin1;
				}
				return null;
			}
		});*/

		assertEquals(admin, ad);

	}
	
	@Test(expected = NullPointerException.class)
	public void listAllTest(){
		String type= "AdminSigma";
		String sql = "SELECT * FROM utilisateur u, admin a where a.admin_id=u.user_id and u.type='"+type+"'";
		int id=14;
		String name="autresaisie2";
		String email="autre2@sigma.com";
		String adresse="rouen2";
		String tel="0847845469";

		int id1=14;
		String name1="autresaisie";
		String email1="autre@sigma.com";
		String adresse1="rouen";
		String tel1="0547845469";

		admin=new Admin(id, name, email, adresse, tel);
		adminDAO.save(admin);

		admin=new Admin(id1, name1, email1, adresse1, tel1);
		adminDAO.save(admin);
		List<Admin> lstadmin =new ArrayList<Admin>();
		lstadmin=adminDAO.listAll();
		//Then
		Mockito.verify(jdbcMock, Mockito.times(2)).update(sql,admin.getId(), admin.getName(), admin.getEmail(),
				admin.getAddress(), admin.getTelephone());
		Mockito.verify(adminDAO, Mockito.times(1)).listAll();

		assertEquals(lstadmin.get(lstadmin.size()-1).getName(),admin.getName());
		System.out.println(lstadmin.get(lstadmin.size()-1));
	}



}
