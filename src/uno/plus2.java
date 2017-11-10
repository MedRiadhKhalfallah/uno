package uno;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiji
 */
public class plus2 extends Carte {
    public plus2()
    {
        
    }
    public void PunitionPlus2(Hand talon, int currentPlayer, Hand[] hands, Carte cartes)
    {
      for (int i=0;i<2;i++)
                   {
                   Card top = cartes.getCarte(talon);
                   top.setVerso(false);
                   talon.transfer(top, hands[currentPlayer], true);
                   }
                   hands[currentPlayer].setTouchEnabled(false);  
    }
}
