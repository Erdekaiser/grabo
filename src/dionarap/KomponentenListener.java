/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author ak-141584
 */
public class KomponentenListener implements ComponentListener
{

    @Override
    public void componentResized(ComponentEvent e) {
        
    }

    @Override
    public void componentMoved(ComponentEvent e) 
    {
        Hauptfenster fenster = (Hauptfenster) e.getSource();
        fenster.getNavigator().setLocation(fenster.getX()+fenster.getWidth()+5, fenster.getY());
    }

    @Override
    public void componentShown(ComponentEvent e) {
        
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        
    }
    
}
