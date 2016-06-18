/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Fabian
 */
public class ListenerMenu implements ActionListener {
    private Hauptfenster hauptfenster;
    
    ListenerMenu(Hauptfenster hauptfenster){
        this.hauptfenster = hauptfenster;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals(MenuBar.CMD_TOGGLENAVIGATOR)){
            this.hauptfenster.navigatorOnOff();
            return;
        }
        
        if(e.getActionCommand().contains("swing.")){
            this.hauptfenster.setLookAndFeel(e.getActionCommand());
            return;
        }
        
        if(e.getActionCommand().matches("Oben") || e.getActionCommand().matches("Unten")){
            this.hauptfenster.toolbarUpDown(e.getActionCommand());
            return;
        }
    }
}
