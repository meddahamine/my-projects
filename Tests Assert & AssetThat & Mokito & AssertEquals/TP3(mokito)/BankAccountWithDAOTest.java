package fr.rouen.mastergil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountWithDAOTest {

    @Mock
    JdbcDAO bankDao;

    @Mock
    Connection connection;

    @InjectMocks
    private BankAccountWithDAO bankAccount;

    @Before
    public void setUp() throws Exception {
        Mockito.when(bankDao.setUpConnection()).thenReturn(connection);
    }

    @After
    public void tearDown() throws Exception {
        verify(bankDao, times(1)).setUpConnection();
    }

    @Test
    public void creer_compte_sans_params() throws SQLException, ConnectException {
        //GIVEN
        //WHEN
        bankAccount.creerCompte();
        //THEN
        verify(bankDao, times(1)).creerCompte();

    }

    @Test
    public void creer_compte_sans_params_connexClosed() throws SQLException {
        //GIVEN
        Mockito.when(connection.isClosed()).thenReturn(true);
        //WHEN
        try {
            //THEN
            bankAccount.creerCompte();
            fail("Impossible d'avoir accès à la base de données");
        } catch (ConnectException e) {
            //THEN
            assertThat(e.getMessage()).isEqualTo("Impossible d'avoir accès à la base de données");
            verify(bankDao, never()).creerCompte();
        }
    }

    @Test
    public void creer_compte_savec_params() throws SQLException, ConnectException {
        //GIVEN
        int montant = 20;
        Devise devise = Devise.DOLLAR;
        //WHEN
        bankAccount.creerCompte(montant, devise);
        //THEN
        verify(bankDao, times(1)).creerCompte(montant, devise);

    }

    @Test
    public void tester_consulterSolde() throws SQLException, ConnectException {
        //GIVEN
        int montant = 20;
        Devise devise = Devise.DOLLAR;
        Money money= new Money(montant,devise);
        Mockito.when(bankDao.getSolde()).thenReturn(money);
        //WHEN
        String affichage =bankAccount.consulterSolde();

        //THEN
        verify(bankDao, times(1)).getSolde();
        assertThat(affichage).isEqualTo("Votre solde actuel est de "+montant+" DOLLAR");

    }

   @Test
    public void deposer_argent() throws SQLException, ConnectException {
        //GIVEN
        int montant = 20;
        Devise devise = Devise.DOLLAR;
        Money money= new Money(montant,devise);
        Mockito.when(bankDao.getSolde()).thenReturn(money);
        //WHEN
        String affichage =bankAccount.consulterSolde();

        //THEN
       verify(bankDao, times(1)).creerCompte();
       verify(bankDao, times(1)).getSolde();
       verify(bankDao, times(1)).saveMoney(money);
        assertThat(affichage).isEqualTo("Votre solde actuel est de "+montant+" DOLLAR");

    }
}