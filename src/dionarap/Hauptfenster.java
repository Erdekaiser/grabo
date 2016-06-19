package dionarap;
//import java.awt.*;
import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.data.MTConfiguration;
import java.awt.BorderLayout;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.*;

//Klasse für das Hauptfenster
public class Hauptfenster extends JFrame {
    


private final Navigator navigator;
private final Toolbar toolbar;

private SettingsDialog settingsDialog;
private String toolbarpos;
private Point fensterpos;
private Settings settings;

private HashMap<String, String>TEMPspieleinstellungen;

public Hauptfenster(Point fensterpos, String toolbarpos, HashMap<String, String>spieleinstellungen)
{
    this.fensterpos = fensterpos;
    this.toolbarpos = toolbarpos;
    this.TEMPspieleinstellungen = spieleinstellungen;
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("DionaRap");
    
    settings = new Settings(this, TEMPspieleinstellungen);
    
    navigator = new Navigator(this);
    toolbar = new Toolbar(this);
 
    //Listener werden erzeugt
    TastenListener tastenListener = new TastenListener();
    KomponentenListener komponentenListener = new KomponentenListener();
    
    //Konfig für Multi-Thread initialisieren.
    this.setJMenuBar(new MenuBar(this, new ListenerMenu(this)));
        
    //Listener werden zugewiesen
    this.addComponentListener(komponentenListener);
    this.addKeyListener(tastenListener);
      
    this.add(settings.getSpielfeld());
    this.setVisible(true);  
    toolbarUpDown(toolbarpos);
     
    this.pack();
    
    settings.getSpielfeld().draw();
    
    //zu Beginn wird das fenster in die Mitte d. Bildschirmes gesetzt, danach übernimmt es immer die vorhergehende pos
    if(fensterpos != null){
        this.setLocation(fensterpos);
    } else {
        this.setLocationRelativeTo(null);
    }
    
    //Fokus wird wieder auf das Fenster gelegt. Falls nicht würden man erneut in das fenster klicken müssen
    this.requestFocus();
}

public Hauptfenster(){
    this(null, "Oben", new HashMap<String,String>());
}

public DionaRapModel getModel()
{
    return settings.getModel();
}
public Navigator getNavigator()
{
    return navigator;
}
public DionaRapController getController()
{
    return settings.getController();
}
public Spielfeld getSpielfeld()
{
    return settings.getSpielfeld();
}

public Settings getSettings(){
    return settings;
}

public void startNewGame(){
    fensterpos = this.getLocation();
    TEMPspieleinstellungen = settings.getSettings();
    this.navigator.dispose();
    this.dispose();
    //this.getModel().removeModelChangedEventListener(this.getSettings().getListenerModel());
    this.getController().deactivateMultiThreading();
    new Hauptfenster(fensterpos, toolbarpos, TEMPspieleinstellungen);
    //System.out.println("startNewGame: " + TOOLBARPOS);
}

public Toolbar getToolbar(){
    return toolbar;
}

public void navigatorOnOff(){
    this.navigator.setVisible(!this.navigator.isVisible());
}

public void setLookAndFeel(String lookAndFeel){
    try{
        
        UIManager.setLookAndFeel(lookAndFeel);
        SwingUtilities.updateComponentTreeUI(this);
        SwingUtilities.updateComponentTreeUI(this.navigator);
        pack();
    } catch (Exception e){
        System.out.println("setLookAndFeel Exception " + e);
    }
    
}

public void toolbarUpDown(String Position){
    toolbarpos = Position;
    
    if("Oben".equals(Position)) {
        this.add(toolbar, BorderLayout.NORTH);
    } else {
        this.add(toolbar, BorderLayout.SOUTH);
    }
    pack();
}

public String getToolbarPos(){
    return toolbarpos;
}

public void newSettingDialog(){
    settingsDialog = new SettingsDialog(this);
}

public static void main(String[] args)
{
    new Hauptfenster();
}
	
}