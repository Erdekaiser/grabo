
package dionarap;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ListenerBewegung implements ActionListener
{
        @Override
    //Listener Wird für Button def.
    public void actionPerformed(ActionEvent e) 
    {
        JButton bewegen = (JButton) e.getSource();
        Navigator navi = (Navigator) bewegen.getTopLevelAncestor();
        navi.getHauptfenster().getController().movePlayer(Integer.valueOf(bewegen.getActionCommand()));
        //System.out.println(e.getActionCommand());
        navi.getHauptfenster().requestFocus();
    }
    
}
