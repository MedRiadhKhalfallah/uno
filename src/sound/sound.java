/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author jiji
 */
public class sound {
    public static Clip c;
    
    public sound(String son)
    {
        try{
            
             AudioInputStream ad= AudioSystem.getAudioInputStream(getClass().getResource(son));
             c=AudioSystem.getClip();
/*GetClip(): Obtient un clip qui peut être utilisé pour la lecture d'un fichier audio ou d'un flux audio.
les données audio de L'interface Clip peuvent être chargées avant la lecture, au lieu d'être diffusées en temps réel.
Étant donné que les données sont préchargées et ont une longueur connue,
vous pouvez définir un clip pour commencer à jouer à n'importe quelle position dans ses données audio. 
Vous pouvez également créer une boucle, de sorte que lorsque le clip est joué, il va cycler à plusieurs reprises. 
 */
         c.open(ad);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
       
    }
    
    public  static void play(){
        c.start();
    }
    public static void stop()
    {
        
        c.stop();
    }
  
}
