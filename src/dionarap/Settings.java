/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import de.fhwgt.dionarap.model.data.MTConfiguration;
import de.fhwgt.dionarap.model.objects.Ammo;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fabian
 */
public class Settings {
    Map<String, String>spieleinstellungen = new HashMap<String, String>();
    
    Settings(Hauptfenster fenster){
        MTConfiguration conf = new MTConfiguration(); 
        conf.setAlgorithmAStarActive(true); 
        conf.setAvoidCollisionWithObstacles(true); 
        conf.setAvoidCollisionWithOpponent(false); 
        conf.setMinimumTime(800);               // 0,8 Sekunden
        conf.setShotGetsOwnThread(true);        // nicht unbegrenzte Anzahl Schüsse 
        conf.setOpponentStartWaitTime(5000);    // 5 Sekunden am Anfang Schlaf 
        conf.setOpponentWaitTime(2000);         // Gegner warten vor jedem Zug 2 Sekunden  
        conf.setShotWaitTime(500);              // ein Schuss benötigt eine halbe Sekunde 
        conf.setRandomOpponentWaitTime(false);  // keine zufällige Wartezeit 
        conf.setDynamicOpponentWaitTime(false); // immer gleichlang warten 
        fenster.getController().setMultiThreaded(conf);
    }
}
