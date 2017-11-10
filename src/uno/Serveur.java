/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uno;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author jiji
 */
public class Serveur {
    private int nb_players;

    public Serveur(int nb_players) {
        this.nb_players = nb_players;
    }
    public void ajouter_annuaire()
    {
     
    
        try{
            System.out.println("le serveur");
           // Runtime.getRuntime().exec("rmiregistry 8888");
            LocateRegistry.createRegistry(1099);
            if(nb_players==2)
            {
                Partie partie=new Partie(2);
                Naming.rebind("rmi://localhost:1099/partie", partie);
                System.out.println("attente de client");
                
            }
            else
                 if(nb_players==3)
            {
                Partie partie=new Partie(3);
                Naming.rebind("rmi://localhost:1099/partie", partie);
                System.out.println("attente de client");
                
            }
            else
                      if(nb_players==4)
            {
                Partie partie=new Partie(4);
                Naming.rebind("rmi://localhost:8888/partie", partie);
                System.out.println("attente de client");
                
            }
            
        }catch(Exception e){
             System.out.println("erreur de liaison de l'objet");
             e.printStackTrace();
        }
        
    }
}
