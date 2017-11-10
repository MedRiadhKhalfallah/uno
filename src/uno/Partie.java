package uno;



import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.CardAdapter;
import ch.aplu.jcardgame.CardGame;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jcardgame.Hand;
import ch.aplu.jcardgame.RowLayout;
import ch.aplu.jcardgame.StackLayout;
import ch.aplu.jcardgame.TargetArea;
import ch.aplu.jgamegrid.Location;
import static java.awt.Color.RED;
import static java.lang.System.exit;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;
import Client.Joueur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiji
 */
 
public class Partie extends UnicastRemoteObject implements PartieInterface {
CardGame Grid;

  private final Carte cartes = new Carte(); // partie contient plusieurs cartes
    
   private final Location[] handLocations =
  {
    new Location(),
    new Location(),
    new Location(),
    new Location(),
    new Location()
  }; 
   private final Location bidLocations =   new Location(680, 350);// la position de la carte où on jette
    
 private int currentPlayer ; 
 private final int nbPlayers;
  private Hand[] hands; // les mains des joueurs
  private Hand talon; // talon c'est la carte qui est responsable sur la destribution des cartes
    private Hand bids; // poubelle
    
 private Joueur j1;
 private Joueur j2;
 private Joueur j3;
 private Joueur j4;
 
  private int sens;
  
  public Partie(int nb) throws RemoteException {
        
     // super(1350, 700, RED ); // taille de la fenetre et la couleur du fond 
        this.Grid = new CardGame(1350, 700, RED );
      
      this.nbPlayers=nb;
      
      currentPlayer=0;
      
      sens=-1;
       
         
        // création des joueurs selon nb passer en paramétre
            if (nbPlayers==2)
        {
        j1=new Joueur(0);
        j2=new Joueur(1); 
        
       
        }
           else  if (nbPlayers==3)
        {
        j1=new Joueur(0);
        j2=new Joueur(1); 
        j3=new Joueur(2);
        } else 
           if (nbPlayers==4)
        {
        j1=new Joueur(0);
        j2=new Joueur(1); 
        j3=new Joueur(2);
        j4=new Joueur(3);
        }
      
        positioncart(); // fonction permet de donner la position des mains des joueurs  selon le nombre de joueurs
         
        int reply = JOptionPane.showConfirmDialog(
            null,
            "vous pouver quitter???",
            "DESTRIBUTION",
            JOptionPane.YES_NO_OPTION);
       
        if(reply != JOptionPane.YES_OPTION )        
        {  
            dealingOut();  // destribution des cartes
            affichehand(); //  permet d'afficher les cartes du joueur en cours
 
              //initBids();
              hands[0].setTouchEnabled(true);// activer la main du premier joueur
              talon.setTouchEnabled(true); // activer le talon
        }
        else {
            exit(0);
        }
    
      
    }

  
    public void positioncart()
  {
  if(this.nbPlayers==2)
    {handLocations[0]=new Location(680,100);
    handLocations[1]=new Location(680,600);
    handLocations[2]=new Location(100,350);
        
    }
    if(this.nbPlayers==3)
    {handLocations[0]=new Location(680,100);
    handLocations[1]=new Location(680,600);
    handLocations[2]=new Location(1150,350);
    handLocations[3]=new Location(100,350);
        
    }
    if(this.nbPlayers==4)
    {handLocations[0]=new Location(680,100);
    handLocations[1]=new Location(200,350);
    handLocations[2]=new Location(680,600);
    handLocations[3]=new Location(1150,350);
    handLocations[4]=new Location(100,100);
        
    }
  }
  
  
    
       public void dealingOut() // fonction permet la destribution des cartes
  {
   

    hands = cartes.getcartes().dealingOut(nbPlayers, 0, true);  
    // chaque joueur a 0 cartes dans la main intialement
    // true : les cartes sont différents 
    talon = hands[nbPlayers];// el 9adeh min joueurs mech ya3ti lawra9
    talon.setVerso(true); // true : afficher le dos de la carte
      
    for (int i = 0; i < nbPlayers; i++)
    {
       hands[i].setView(Grid, new  RowLayout(handLocations[i], 400,0));
      // setView: changer la vue des cartes dans la main de joueur
      //RowLayout prend en parametre la main de joueur , lees cartes sont adjacents, la rotation de la carte
      hands[i].draw();// draw : dessiner les mains

    }
      talon.setView(Grid, new StackLayout(handLocations[nbPlayers]));
 // talon.setView(this, new StackLayout(handLocations[nbPlayers]));
    /* Les utilisateurs doivent définir la valeur topControl pour basculer
    entre les éléments visibles et ensuite appeler layout ()
    sur le composite qui a le StackLayout*/
    talon.draw(); // dessiner le talon

    
     for (int j = 0; j <7 ; j++)   
    {   
      for (int i = 0; i < nbPlayers; i++)
      {
        Card top = cartes.getCarte(talon);////////???? a demain
        top.setVerso(false);  // false : afficher la face de la derniere carte de talon
        talon.setTargetArea(new TargetArea(handLocations[i])); 
        //setTargetArea :modifier la destination de la carte
        talon.transfer(top, hands[i], true); ////////???? a demain
      
        // tranfer permet d'envoyer la carte 
      
        
      }
    }
    
    
}

  
   public void DeterminerGangnant()
  {
  
      if(hands[currentPlayer].getNumberOfCards()==0)
      {
      JOptionPane.showMessageDialog(null," le joueur numéro: "+currentPlayer+" gagne et exit game");
      exit(0);
        }
  }
  
  public void affichehand() // afficher seulement la main de joueur en cours
{

      if(nbPlayers==4)
      {hands[0].setVerso(true);
      hands[1].setVerso(true);
      hands[2].setVerso(true);
      hands[3].setVerso(true);
       hands[currentPlayer].setVerso(false);}
      if(nbPlayers==3)
      {hands[0].setVerso(true);
      hands[1].setVerso(true);
      hands[2].setVerso(true);
       hands[currentPlayer].setVerso(false);}
      if(nbPlayers==2)
      {hands[0].setVerso(true);
      hands[1].setVerso(true);
       hands[currentPlayer].setVerso(false);}
}
   
  public void nextplayer(int sens)
          
  { 
      DeterminerGangnant();
      
      int nb=nbPlayers-1;
      currentPlayer=currentPlayer + sens;
    if(currentPlayer==-1)
      {currentPlayer=nb;}
      currentPlayer = currentPlayer % nbPlayers;
        affichehand();


      
  }
public void jeter_selon_nbre_joueur(Card card)
{
     if (nbPlayers==2){
             
              if (currentPlayer==j1.getNum())
             
              {
                  j1.JeterCarte(card, hands, bids);
              }
              else if (currentPlayer==j2.getNum())
              {
                  j2.JeterCarte(card, hands, bids);
              }
              }
      else  if (nbPlayers==3){
             
              if (currentPlayer==j1.getNum())
             
              {
                  j1.JeterCarte(card, hands, bids);
              }
              else if (currentPlayer==j2.getNum())
              {
                  j2.JeterCarte(card, hands, bids);
              }
              else if (currentPlayer==j3.getNum())
              {
                  j3.JeterCarte(card, hands, bids);
              } 
              }  
       else  if (nbPlayers==4){
             
              if (currentPlayer==j1.getNum())
             
              {
                  j1.JeterCarte(card, hands, bids);
              }
              else if (currentPlayer==j2.getNum())
              {
                  j2.JeterCarte(card, hands, bids);
              }
              else if (currentPlayer==j3.getNum())
              {
                  j3.JeterCarte(card, hands, bids);
              }
              else if (currentPlayer==j4.getNum())
              {
                  j4.JeterCarte(card, hands, bids);
              }
              } 
}
  
  public void tirer_selon_nbre_joueur(Card card)
  {
      
     if (nbPlayers==2){
             
              if (currentPlayer==j1.getNum())
             
              {
                  j1.TirerCarte(talon, hands, handLocations, nbPlayers,card); 
              }
              else if (currentPlayer==j2.getNum())
              {
                 j2.TirerCarte(talon, hands, handLocations, nbPlayers,card); 
              }
              }
      else  if (nbPlayers==3){
             
              if (currentPlayer==j1.getNum())
             
              {
                  j1.TirerCarte(talon, hands, handLocations, nbPlayers,card); 
              }
              else if (currentPlayer==j2.getNum())
              {
                  j2.TirerCarte(talon, hands, handLocations, nbPlayers,card); 
              }
              else if (currentPlayer==j3.getNum())
              {
                  j3.TirerCarte(talon, hands, handLocations, nbPlayers,card); 
              } 
              }  
       else  if (nbPlayers==4){
             
              if (currentPlayer==j1.getNum())
             
              {
                  j1.TirerCarte(talon, hands, handLocations, nbPlayers,card); 
              }
              else if (currentPlayer==j2.getNum())
              {
                  j2.TirerCarte(talon, hands, handLocations, nbPlayers,card); 
              }
              else if (currentPlayer==j3.getNum())
              {
                  j3.TirerCarte(talon, hands, handLocations, nbPlayers,card); 
              }
              else if (currentPlayer==j4.getNum())
              {
                  j4.TirerCarte(talon, hands, handLocations, nbPlayers,card); 
              }
              }
     
  }        

    public void setSens(int sens) {
        this.sens = sens;
    }
  

  /************************************ code de initBids() ***********************************************************/
      public void initBids()
      {  
       
           
      for (int i = 0; i < nbPlayers; i++)
    {
      bids = new Hand(cartes.getcartes()); // creation dynamique de bids de type hand
      bids.setView(Grid, new StackLayout(bidLocations));
     /* Les utilisateurs doivent définir la valeur topControl pour basculer
    entre les éléments visibles et ensuite appeler layout ()
    sur le composite qui a le StackLayout*/

      hands[i].setTargetArea(new TargetArea(bidLocations));
      //setTargetArea : modifier le chemin vers bids
     
 hands[i].addCardListener(new CardAdapter()
      {
        public void leftClicked(Card card)
        {
          card.setVerso(false);
   /************************ cas du carte plus 4 ****************************/      
          if (card.getRank()==cartes.getRankPlusFour())
          {       
             jeter_selon_nbre_joueur(card);
                   nextplayer(sens);
                     hands[currentPlayer].setTouchEnabled(false);//yil3ab kan fil tour mta3o
                   talon.setTargetArea(new TargetArea(handLocations[currentPlayer]));
                    new plus4().PunitionPlus4(talon, currentPlayer, hands, cartes);
                   nextplayer(sens);
            
               hands[currentPlayer].setTouchEnabled(true);
          }
     else
   /****************************** carte couleur ***************************************/
           if(card.getRank()==cartes.getcolour())
                   {
                     jeter_selon_nbre_joueur(card);
                    hands[currentPlayer].setTouchEnabled(false);//yil3ab kan fil tour mta3o
                   nextplayer(sens);
                   hands[currentPlayer].setTouchEnabled(true);
                   
                   }
          
     
    else      
      /********************************** carte normaaaaaaaaaale ***************************************/
        
          
     if ((cartes.getCarte(bids)==null)||(card.getSuit()== cartes.getCarte(bids).getSuit())||(card.getRank()== cartes.getCarte(bids).getRank()))
          {  // test: si la poubelle est vide ou la derniere carte du poubelle  égal au numéro du carte séléctionné 
          // ou du meme couleur que la carte séléctionné alors on peut jeter la carte selectionné       
            jeter_selon_nbre_joueur(card);
             hands[currentPlayer].setTouchEnabled(false); // yil3ab kan fil tour mta3o
   /************************ cas du carte +2          *****************************************************/  
             
             if(card.getRank()==cartes.getPlusTwo())
                   {  
                  jeter_selon_nbre_joueur(card); 
                   nextplayer(sens);
                   hands[currentPlayer].setTouchEnabled(false);//yil3ab kan fil tour mta3ou   
                   talon.setTargetArea(new TargetArea(handLocations[currentPlayer]));
                  new plus2().PunitionPlus2(talon, currentPlayer, hands, cartes);
                   } 
                 
      
              /******************************** carte inverser ************************************/
                  if(card.getRank()==cartes.getReverse())
              {
                   jeter_selon_nbre_joueur(card);
                   hands[currentPlayer].setTouchEnabled(false);//yil3ab kan fil tour mta3ou
                   setSens(new inversion().setSens(sens));
                   }
             /***************************************** carte passer *****************************************/     
               if(card.getRank()==cartes.getskip())
              {
                   jeter_selon_nbre_joueur(card);
                   hands[currentPlayer].setTouchEnabled(false);
                   nextplayer(sens);
              }
               
               
             nextplayer(sens);
            hands[currentPlayer].setTouchEnabled(true);
                  
            
          }
          

          
          
          
        }});
    }       
      
   
      
             
    /***************** c bon *************************************/
     
      talon.addCardListener(new CardAdapter()
    {
         public void leftClicked(Card card)
        {
            
        tirer_selon_nbre_joueur(card);
        
        talon.setTouchEnabled(false);
    int reply = JOptionPane.showConfirmDialog(
            null,
            "vous avez une carte à jouer ?",
            "CARTE A JOUER",
            JOptionPane.YES_NO_OPTION);

        
       
        if(reply == JOptionPane.YES_OPTION )
{    
    ////////////////////////////////////
        hands[currentPlayer].setTargetArea(new TargetArea(bidLocations));
      //setTargetArea : modifier le chemin vers bids
     
       hands[currentPlayer].addCardListener(new CardAdapter()
      {
        public void leftClicked(Card card)
        {
          card.setVerso(false);
   /************************ cas du carte plus 4 ****************************/      
          if (card.getRank()==cartes.getRankPlusFour())
          {       
             jeter_selon_nbre_joueur(card);
                   talon.setTouchEnabled(true);
                   nextplayer(sens);
                   talon.setTargetArea(new TargetArea(handLocations[currentPlayer]));
                    new plus4().PunitionPlus4(talon, currentPlayer, hands, cartes);
                   hands[currentPlayer].setTouchEnabled(false);//yil3ab kan fil tour mta3o
                   nextplayer(sens);
            
               hands[currentPlayer].setTouchEnabled(true);
          }
     else
   /****************************** carte couleur ***************************************/
           if(card.getRank()==cartes.getcolour())
                   {
                     jeter_selon_nbre_joueur(card);
                    hands[currentPlayer].setTouchEnabled(false);//yil3ab kan fil tour mta3o
                    talon.setTouchEnabled(true);
                   nextplayer(sens);
                   hands[currentPlayer].setTouchEnabled(true);
                   
                   }
          
     
    else      
      /********************************** carte normaaaaaaaaaale ***************************************/
        
          
     if ((cartes.getCarte(bids)==null)||(card.getSuit()== cartes.getCarte(bids).getSuit())||(card.getRank()== cartes.getCarte(bids).getRank()))
          {  // test: si la poubelle est vide ou la derniere carte du poubelle  égal au numéro du carte séléctionné 
          // ou du meme couleur que la carte séléctionné alors on peut jeter la carte selectionné       
            jeter_selon_nbre_joueur(card);
             hands[currentPlayer].setTouchEnabled(false); // yil3ab kan fil tour mta3o
   /************************             cas du carte +2          *****************************************************/  
             
             if(card.getRank()==cartes.getPlusTwo())
                   {  
                  jeter_selon_nbre_joueur(card); 
                  talon.setTouchEnabled(true);
                   nextplayer(sens);
                   talon.setTargetArea(new TargetArea(handLocations[currentPlayer]));
                  new plus2().PunitionPlus2(talon, currentPlayer, hands, cartes);
                   hands[currentPlayer].setTouchEnabled(false);//yil3ab kan fil tour mta3ou   
                   } 
                 
      
              /******************************** carte inverser ************************************/
                  if(card.getRank()==cartes.getReverse())
              {
                   jeter_selon_nbre_joueur(card);
                   hands[currentPlayer].setTouchEnabled(false);//yil3ab kan fil tour mta3ou
                   talon.setTouchEnabled(true);
                   setSens(new inversion().setSens(sens));
                   }
             /***************************************** carte passer *****************************************/     
               if(card.getRank()==cartes.getskip())
              {
                   jeter_selon_nbre_joueur(card);
                   hands[currentPlayer].setTouchEnabled(false);
                   nextplayer(sens);
              }
               
              talon.setTouchEnabled(true);
             nextplayer(sens);
            hands[currentPlayer].setTouchEnabled(true);
                  
            
          }
          

          
          
          
        }});
        ////////////////////////////////////
}
        else{
            talon.setTouchEnabled(true);
        nextplayer(sens);
        hands[currentPlayer].setTouchEnabled(true);
        
         }
        
        }
    });     
    
       
      }
 
    
  
}
