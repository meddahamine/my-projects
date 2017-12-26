package net.sigma.spring.dao;

import net.sigma.spring.model.Admin;
import net.sigma.spring.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by root on 05/04/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
    @Mock
    JdbcTemplate jdbcMock;
    @Mock
    User userMock;

    @InjectMocks
    IUserDAO userDAO;

    @Test
    public void getUserLogin(){
        //Given
        int id=14;
        String type="autresaisie";
        String login="autre@sigma.com";
        String password="rouen";
        String tel="0547845469";

        userDAO=new UserDAO();
        userMock=new User(id,login,password,type);

        User user2=new User();
       // user2=UserDAO.getUserLogin(userMock);

        //When
        String sql = "SELECT * FROM admin WHERE admin_id="+id;
        //Admin ad=new Admin();
        //ad= adminDAO.get(id);


        //Then



        //assertEquals(admin, ad);
    }
}
