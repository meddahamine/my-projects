package net.sigma.spring.dao;

import net.sigma.spring.model.Admin;
import net.sigma.spring.model.Archive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

/**
 * Created by root on 28/04/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ArchiveDAOTest {

    @Mock
    JdbcTemplate jdbcTemplateMock;
    @Mock
    Archive archiveMock;

    @InjectMocks
    ArchivesDAO archivesDAO;

    @Test(expected= MockitoException.class)
    public void saveTest(){
        String sql = "INSERT INTO archive (archive_id, name, email, address, telephone, type)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
      //  JdbcTemplate template = jdbcTemplateMock.createMock(JdbcTemplate.class);
        int id=8;
        String name="autre";
        String email="autre@sigma.com";
        String adresse="rouen";
        String tel="0547845469";
        String type="AdminSigma";

        archiveMock=new Archive(id,name,email,adresse,tel,type);
        archivesDAO.save(archiveMock);

        Mockito.verify(jdbcTemplateMock,Mockito.times(1)).update(sql,archiveMock.getId(), archiveMock.getName(), archiveMock.getEmail(),
                archiveMock.getAddress(), archiveMock.getTelephone(),archiveMock.getType());

        assertEquals(archiveMock.getAddress(), adresse);
        assertEquals(archiveMock.getName(),name);
        assertEquals(archiveMock.getEmail(),email);
        assertEquals(archiveMock.getTelephone(),tel);
        assertEquals(archiveMock.getId(),id);
        assertEquals(archiveMock.getType(),type);
    }


    @Test
    public void deleteTest(){
//Given
        String sql = "DELETE FROM Archive WHERE admin_id=?";
        int id=8;

        //DataSource dataSource;
        //jdbcMock=new JdbcTemplate(dataSource);
        //When

        archivesDAO.delete(id);
        //Then
        Mockito.verify(jdbcTemplateMock, Mockito.times(1)).update(sql,id);

    }

    @Test
    public void getTest(){
        //Given
        int id=14;
        String name="autresaisie";
        String email="autre@sigma.com";
        String adresse="rouen";
        String tel="0547845469";
        String type="AdminSigma";
		Archive archive=new Archive(id, name, email, adresse, tel,type);
		archivesDAO.save(archive);

        //When
        String sql = "SELECT * FROM Archive WHERE admin_id="+id;
        Archive ar=new Archive();
        ar= archivesDAO.get(id);


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

        assertEquals(archive, ar);
    }

    @Test
    public void listTest(){

    }
}
