package Client;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import ch.aplu.jcardgame.TargetArea;
import ch.aplu.jgamegrid.Location;
import java.rmi.Naming;
import uno.PartieInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiji
 */
public class Joueur {
    private int NumJoueur;
   
    public Joueur(int NumJoueur) {
        this.NumJoueur=NumJoueur;
    }

    public int getNum() {
        return NumJoueur;
    }

   

   
    
    
    public void TirerCarte( Hand talon, Hand[] hands, Location[] handLocations,  int nbplayers, Card card)
    {       
        card.setVerso(false);
        talon.setTargetArea(new TargetArea(handLocations[NumJoueur]));
        talon.transfer(card, hands[NumJoueur], true);
        hands[NumJoueur].setTouchEnabled(true);
        
       
        }
    public void JeterCarte(Card card,Hand[] hands,Hand bids)
    {
    card.transfer(bids, true);
        
    }
/*    
     public void direuno( int currentPlayer, Hand [] hands )
  {

  if(hands[currentPlayer].getNumberOfCards()==2)
      {
            //JOptionPane.showMessageDialog(null, "dire uno");
JOptionPane.showConfirmDialog(null, "dire uno ?", "uno", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

      }
  }*/
public static void main( String args[])
{
 try
 {
     PartieInterface joueur=(PartieInterface)Naming.lookup("rmi://localhost:1099/partie");
       System.out.println("Client commence de jouer");
     joueur.initBids();
    
     
 }
    catch( Exception e){
        System.out.println("erreur d'acces");
        System.out.println(e.toString());
    }
    

}
     
    
}
    

