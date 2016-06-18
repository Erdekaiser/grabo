package dionarap;
//import java.awt.*;
import de.fhwgt.dionarap.controller.DionaRapController;
import de.fhwgt.dionarap.model.data.DionaRapModel;
import java.awt.BorderLayout;
import java.awt.Point;
import javax.swing.*;

//Klasse für das Hauptfenster
public class Hauptfenster extends JFrame {
    
private final DionaRapModel model;
private final DionaRapController controller;
private final Spielfeld feld;
private final Navigator navigator;
private final Toolbar toolbar;

private String toolbarpos;
private Point fensterpos;

public Hauptfenster(Point fensterpos, String toolbarpos)
{
    this.fensterpos = fensterpos;
    this.toolbarpos = toolbarpos;
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("DionaRap");
    
    model = new DionaRapModel();
    controller = new DionaRapController(model);
    navigator = new Navigator(this);
    feld = new Spielfeld(this);
    toolbar = new Toolbar(this);
        
    //Listener werden erzeugt
    ListenerModel listenerModel = new ListenerModel(this);
    TastenListener tastenListener = new TastenListener();
    KomponentenListener komponentenListener = new KomponentenListener();
    
    this.setJMenuBar(new MenuBar(this, new ListenerMenu(this)));
        
    //Listener werden zugewiesen
    this.addComponentListener(komponentenListener);
    this.addKeyListener(tastenListener);
    //Immer wenn sich das Spielfeld ändert wird der listener aufgerufen 
    model.addModelChangedEventListener(listenerModel);
      
    this.add(feld);
    this.setVisible(true);
    toolbarUpDown(toolbarpos);
 
    this.pack();
    
    feld.draw();
    
    //zu Beginn wird das fenster in die Mitte d. Bildschirmes gesetzt, danach übernimmt es immer die vorhergehende pos
    if(fensterpos != null){
        this.setLocation(fensterpos);
    } else {
        this.setLocationRelativeTo(null);
    }
    
    //Fokus wird wieder auf das Fenster gelegt. Falls nicht würden man erneut in das fenster klicken müssen
    this.requestFocus();
}

public DionaRapModel getModel()
{
    return model;
}
public Navigator getNavigator()
{
    return navigator;
}
public DionaRapController getController()
{
    return controller;
}
public Spielfeld getSpielfeld()
{
    return feld;
}

public void startNewGame(){
    fensterpos = this.getLocation();
        
    this.navigator.dispose();
    this.dispose();
     
        
    new Hauptfenster(fensterpos, toolbarpos);
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

public static void main(String[] args)
{
    //Init
    Point fensterpos = null;
    String toolbarpos = "Oben";
    
    new Hauptfenster(fensterpos, toolbarpos);
}
	
}