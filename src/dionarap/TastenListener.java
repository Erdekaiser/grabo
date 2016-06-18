/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ak-141584
 */
public class TastenListener implements KeyListener
{

    @Override
    public void keyTyped(KeyEvent e) 
    {
        char beschriftung = e.getKeyChar();
        
        Hauptfenster fenster = (Hauptfenster) e.getSource();
        if(beschriftung > '0' && beschriftung <= '9')
        {
            if(beschriftung != '5')
                fenster.getController().movePlayer((int) beschriftung-48);
            else
                fenster.getController().shoot();
        }
        
        //"Focus liegt auf dem fenster"
        fenster.requestFocus();
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        
    }
    
}
