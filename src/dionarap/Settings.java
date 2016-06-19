/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.data.MTConfiguration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fabian
 */
public class Settings {
    
    static public final String WGB = "Wartezeit der Gegner zu Beginn";
    static public final String VES = "Verzögerung eines Schusses";
    static public final String WGS = "Wartezeit eines Gegners vor jedem Schnritt";
    static public final String ZWG = "Zufällige Wartezeit der Gegner";
    static public final String GKH = "Gegner meiden Kollision mit Hindernis";
    static public final String GKG = "Gegner meiden Kollision mit anderen Gegnern";
    static public final String AZS = "Anzahl Zeilen des Spielfelds";
    static public final String ASS = "Anzahl der Spalten eines Spielfelds";
    static public final String AHI = "Anzahl Hindernisse";
    static public final String ADG = "Anzahl der Gegner";
    
    private HashMap<String, String>spieleinstellungen = new HashMap<String, String>();
    private MTConfiguration conf;
    private Hauptfenster fenster;
    private final Spielfeld feld;
    private final DionaRapModel model;
    private final DionaRapController controller;
    
    Settings(Hauptfenster fenster, HashMap<String, String> TEMPspieleinstellungen){
        spieleinstellungen = TEMPspieleinstellungen;
        initSpieleinstellungen();
        this.fenster = fenster;
        model = new DionaRapModel(
            Integer.parseInt(spieleinstellungen.get(ASS)),
            Integer.parseInt(spieleinstellungen.get(AZS)),
            Integer.parseInt(spieleinstellungen.get(ADG)),
            Integer.parseInt(spieleinstellungen.get(AHI))
            );
        controller = new DionaRapController(model);
        //Immer wenn sich das Spielfeld ändert wird der listener aufgerufen 
        ListenerModel listenerModel = new ListenerModel(fenster);
        model.addModelChangedEventListener(listenerModel);
                        
        feld = new Spielfeld(fenster, Integer.parseInt(spieleinstellungen.get(AZS)), Integer.parseInt(spieleinstellungen.get(ASS)));
        setSettings(spieleinstellungen);
        
    }
    
    public String getSingleSettings(String key){
        return spieleinstellungen.get(key);
    }
    
    public void setSingleSettings(String key, String value){
        spieleinstellungen.put(key, value);
    }
    
    public HashMap<String, String> getSettings(){
        return this.spieleinstellungen;
    }
    
    public void setSettings(HashMap<String, String> map){
        spieleinstellungen = map;
        //System.out.println(map);
        //System.out.println(spieleinstellungen);
        if(conf == null){
            conf = new MTConfiguration();
        }
        conf.setOpponentStartWaitTime(Integer.parseInt(spieleinstellungen.get(WGB)));
        conf.setShotWaitTime(Integer.parseInt(spieleinstellungen.get(VES)));
        conf.setOpponentWaitTime(Integer.parseInt(spieleinstellungen.get(WGS)));
        conf.setRandomOpponentWaitTime(Boolean.parseBoolean(spieleinstellungen.get(ZWG))); 
        conf.setAvoidCollisionWithObstacles(Boolean.parseBoolean(spieleinstellungen.get(GKH)));
        conf.setAvoidCollisionWithOpponent(Boolean.parseBoolean(spieleinstellungen.get(GKG))); 
        //spieleinstellungen.put(AZS, (map.get(AZS)));
        //Stand Aufgabe müssen diese Werte nicht geändert werden können.
        conf.setAlgorithmAStarActive(true); 
        conf.setMinimumTime(800);               // 0,8 Sekunden
        conf.setShotGetsOwnThread(true);        // nicht unbegrenzte Anzahl Schüsse 
        conf.setDynamicOpponentWaitTime(false); // immer gleichlang warten 
            
        controller.setMultiThreaded(conf);
    }
    
    private void initSpieleinstellungen(){
        if(spieleinstellungen.isEmpty()){
            spieleinstellungen.put(WGB, "5000");    //Setzt die Wartezeit der Gegner zu Beginn (Schlaf).
            spieleinstellungen.put(VES, "500");     //Legt die Zeit fest, die ein Schuss benötigt, um von einem Feld zum Nächsten zu gelangen.
            spieleinstellungen.put(WGS, "2000");    //Setzt die Intervallzeit zwischen Bewegungen eines Gegners.
            spieleinstellungen.put(ZWG, "false");
            spieleinstellungen.put(GKH, "true");
            spieleinstellungen.put(GKG, "false");
            spieleinstellungen.put(AZS, "25");
            spieleinstellungen.put(ASS, "15");
            spieleinstellungen.put(AHI, "10");
            spieleinstellungen.put(ADG, "10");
            //System.out.println(spieleinstellungen);
        }
    }
    
    public DionaRapModel getModel()
    {
        return model;
    }

    public DionaRapController getController()
    {
        return controller;
    }
    
    public Spielfeld getSpielfeld()
    {
        return feld;
    }
}
