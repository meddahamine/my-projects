package fr.rouen.mastergil.TDD;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rudy on 12/03/17.
 */
public class BowlingCalculatorTest {


    /**
     * Exemple de tests :
     * <p/>
     * #1 : score avec que des valeurs entières en frame 1
     * #2 : score avec que des valeurs entières en frame 1 et frame 2
     * #3 : score avec que des valeurs entières en frame 1 et frame 2 et 1 spare
     * #4 : score avec que des valeurs entières en frame 1 et frame 2 et 2 spare
     * #5 : score avec que des valeurs entières en frame 1 et frame 2 et 1 strike
     * ...
     * autre exemple :
     * <p/>
     * #1 : score avec que des valeurs à 1 en frame 1
     * #2 : score avec que des valeurs à 2 en frame 1
     * #3 : score avec que des valeurs à 1 en frame 1 et frame 2
     * #4 : score avec que des valeurs à 2 en frame 1 et frame 2
     * #5 : score avec que des valeurs à 2 en frame 1 et frame 2 et 1 spare
     * <p/>
     * S'inspirer des presets du calculator http://warwickbowling.50webs.com/calculator.html APRES les cas "basiques"
     */

    private BowlingCalculator bowlingCalculator;

    @Before
    public void setUp() throws Exception {
        bowlingCalculator = new BowlingCalculator();
    }

    @Test
    public void scoreTestF1(){
        assertEquals(4, bowlingCalculator.score("13;--;--;--;--;--;--;--;--;--"));

    }

    @Test
    public void scoreTestF1_F2(){
        assertEquals(9, bowlingCalculator.score("11;25;--;--;--;--;--;--;--;--"));

    }

    @Test
    public void scoreTestF1_F2_2spare(){
        assertEquals(12, bowlingCalculator.score("11;2/;--;--;--;--;--;--;--;--"));

    }

    @Test
    public void scoreTestF1F2spire2(){
        assertEquals(22, bowlingCalculator.score("11;2/;-/;--;--;--;--;--;--;--"));

    }

    @Test
    public void scoreTestF1_F2_strike(){
        assertEquals(12, bowlingCalculator.score("11;x-;--;--;--;--;--;--;--;--"));

    }

    @Test
    public void scoreTestValeur_1_F1(){
        assertEquals(2, bowlingCalculator.score("11;--;--;--;--;--;--;--;--;--"));

    }

    @Test
    public void scoreTestValeur_2_F1(){
        assertEquals(2, bowlingCalculator.score("11;--;--;--;--;--;--;--;--;--"));

    }

    @Test
    public void scoreTestValeur_1_F1F2(){
        assertEquals(4, bowlingCalculator.score("11;11;--;--;--;--;--;--;--;--"));

    }

    @Test
    public void scoreTestValeur_2_F1F2(){
        assertEquals(8, bowlingCalculator.score("22;22;--;--;--;--;--;--;--;--"));

    }

    @Test
    public void scoreTestValeur_2_F1F2_1Spire(){
        assertEquals(14, bowlingCalculator.score("22;2/;--;--;--;--;--;--;--;--"));
        assertEquals(18, bowlingCalculator.score("22;22;-/;--;--;--;--;--;--;--"));

    }
}
