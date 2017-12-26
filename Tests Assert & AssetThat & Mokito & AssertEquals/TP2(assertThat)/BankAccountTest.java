package fr.rouen.mastergil;

import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.*;


public class BankAccountTest {
    @Test
    public void  Creer_compte_sans_params() throws Exception
    {
        //GIVEN
        BankAccount bank= new BankAccount();
        //WHEN
        bank.creerCompte();

       //THEN
        assertThat(bank.solde.getMontant()).isEqualTo(0);//isNotNull();
       // assertThat(bank.solde.getDevise()).isEqualTo(Devise.EURO);
    }

    @Test
    public void  Creer_compte_avec_params()
    {
        //GIVEN
        BankAccount bank= new BankAccount();
        int montant=1000;
        Devise devise=Devise.DOLLAR;
        //WHEN
        bank.creerCompte(montant,devise);

        //THEN
        assertThat(bank.solde.getMontant()).isEqualTo(montant);
        assertThat(bank.solde.getDevise()).isEqualTo(devise);
    }

    @Test
    public void  Consulter_Soldes()
    {
        //GIVEN
        BankAccount bank= new BankAccount();
        int montant = 200;
        bank.creerCompte(montant,Devise.EURO);
        //WHEN
       String affichage= bank.consulterSolde();
        //THEN
        assertThat(affichage).isEqualTo("Votre solde actuel est de "+montant+" EURO");
    }

    @Test
    public void  Deposer_Argent()
    {
        //GIVEN
        BankAccount bank= new BankAccount();
        bank.creerCompte();
        //WHEN
        bank.deposerArgent(1000);
        //THEN
        assertThat(bank.solde.getMontant()).isEqualTo(1000);
        assertThat(bank.solde.getDevise()).isEqualTo(Devise.EURO);
    }

    @Test
    public void  Retirer_Argent()
    {
        //GIVEN
        BankAccount bank= new BankAccount();
        bank.creerCompte();
        //WHEN
        bank.retirerArgent(1000);
        //THEN
        assertThat(bank.solde.getMontant()).isEqualTo(-1000);
        //assertThat(bank.solde.getDevise()).isEqualTo(Devise.EURO);
    }

   @Test
    public void  Solde_positif_vrai()
    {
        //GIVEN
        BankAccount bank= new BankAccount();
        bank.creerCompte(10,Devise.EURO);
        //WHEN
        boolean result=bank.isSoldePositif();
        //THEN
        assertThat(result).isTrue();
    }
	
    @Test
    public void  Solde_positif_Egale0()
    {
        //GIVEN
        BankAccount bank= new BankAccount();
        bank.creerCompte(0,Devise.EURO);
        //WHEN
        boolean result=bank.isSoldePositif();
        //THEN
        assertThat(result).isTrue();
    }

    @Test
    public void  Solde_positif_Faux()
    {
        //GIVEN
        BankAccount bank= new BankAccount();
        bank.creerCompte(-10,Devise.EURO);
        //WHEN
        boolean result=bank.isSoldePositif();
        //THEN
        assertThat(result).isFalse();
    }
}