package uno;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.CardGame;
import ch.aplu.jcardgame.Deck;
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
public class Carte {

    
      public enum Suit
  {
    r,g,b,y
  }

  public enum Rank
  {
    zero ,one ,two ,three ,four ,five ,six ,seven ,eight ,nine,PlusTwo,Reverse,skip,PlusFour,colour
  }
  public final Deck cartes ;
 
  private String type="";
 
   public Carte()
   {
   cartes=new Deck(Suit.values(), Rank.values(), "back"); 
  // tableau contient cartes 
        
   }
   

    public Deck getcartes() {
        
        return cartes;
    }
   
    public Card getCarte(Hand a)
    {
        return a.getLast(); // getLast(): donner la derniere carte de talon
    }

    
    
 

 public Rank getRankPlusFour(){
     return Rank.PlusFour;
 }

  public Rank getReverse(){
     return Rank.Reverse;
 }
 
 
  public Rank getPlusTwo(){
     return Rank.PlusTwo;
 }
  
   public Rank getskip(){
     return Rank.skip;
 }
 
   public Rank getcolour(){
     return Rank.colour;
 }
}
