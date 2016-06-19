/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import de.fhwgt.dionarap.model.data.MTConfiguration;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author Fabian
 */
public class ListenerButton implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton)e.getSource();
        Hauptfenster hf = (Hauptfenster)(button.getTopLevelAncestor());
        
        if(e.getActionCommand() == "NewGame"){
            hf.startNewGame();
        }
        
        if(e.getActionCommand() == "Settings"){
            hf.newSettingDialog();
        }
    }
}
