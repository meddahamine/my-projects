package fr.rouen.mastergil.user;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
/**
 * Created by meddaami on 06/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private User user;
    @Mock
    private Authority authority;

    @InjectMocks
    private UserService userService;

    @Test
    public void informlationTest(){
        userService = new UserService();
        userService.createUserInformation("Login", "password","firstName","lastName","email","langKey");
        user = new User();
        authority = new Authority();
        Mockito.when(user.getLogin()).thenReturn("Login");
        Mockito.when(user.getPassword()).thenReturn("passwords");
        Mockito.when(user.getFirstName()).thenReturn("firstName");
        Mockito.when(user.getLastName()).thenReturn("lastName");
        Mockito.when(user.getEmail()).thenReturn("email");
        Mockito.when(user.getLangKey()).thenReturn("langKey");
    }

}
