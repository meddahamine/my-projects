package fr.rouen.mastergil;


import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {
    @Test
    public void Constructor_sans_params (){

        //GIVEN
        //WHEN
       Money money= new Money();

        //THEN
       Assert.assertEquals(0,money.getMontant());
       Assert.assertEquals(Devise.EURO,money.getDevise());

    }
    @Test
    public void Constructor_avec_params(){
        int montant=10;
        Devise devise=Devise.EURO;
        Money money=new Money(montant,devise);
        Assert.assertEquals(montant,money.getMontant());
        Assert.assertEquals(devise,money.getDevise());
    }

    @Test
    public void isPositif_true_0(){
        int montantnull=0;
        Money moneyNull= new Money(montantnull,Devise.DINAR);
        Assert.assertEquals(true,moneyNull.isPositif());
    }

    @Test
    public void isPositif_true_Positif(){
        int montantPositif=5;
        Money moneyPOS= new Money(montantPositif,Devise.DINAR);
        Assert.assertEquals(true,moneyPOS.isPositif());
    }
    @Test
    public void isPositif_false_Negatif(){
        int montantNegatif=-5;
        Money moneyNEG= new Money(montantNegatif,Devise.DINAR);
        Assert.assertEquals(false,moneyNEG.isPositif());
    }

    @Test
    public void getMontant(){
        int montant =5;
        Money money= new Money(montant,Devise.DINAR);
        Assert.assertEquals(montant,money.getMontant());
    }

    @Test
    public void getDevise(){
        int montant =5;
        Devise devise=Devise.DINAR;
        Money money= new Money(montant,devise);
        Assert.assertEquals(devise,money.getDevise());
    }


    @Test
    public void SetMontant(){
        int montant =5;
        Money money= new Money();
        money.setMontant(montant);
        Assert.assertEquals(montant,money.getMontant());
    }

    @Test
    public void SetDevise(){
        Money money= new Money();
        Devise devise=Devise.DINAR;
        money.setDevise(devise);
        Assert.assertEquals(devise,money.getDevise());
    }

}