/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uno;

import ch.aplu.jcardgame.Card;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author jiji
 */
public interface PartieInterface extends Remote {
    
    
     public void positioncart() throws RemoteException;
     public void dealingOut() throws RemoteException;
      public void DeterminerGangnant()throws RemoteException;
     public void affichehand()throws RemoteException;
       public void nextplayer(int sens)throws RemoteException;
     public void jeter_selon_nbre_joueur(Card card)throws RemoteException;
    public void tirer_selon_nbre_joueur(Card card)throws RemoteException;
     public void setSens(int sens)throws RemoteException;
      public void initBids()throws RemoteException;
}
