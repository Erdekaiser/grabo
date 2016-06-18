/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author Fabian
 */
public class ListenerNewGame implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e){
        JButton newGame = (JButton)e.getSource();
        Hauptfenster hf = (Hauptfenster)(newGame.getTopLevelAncestor());
        
        hf.startNewGame();
    }
}
