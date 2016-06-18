/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author ak-141584
 */
public class ListenerWaffe implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        JButton waffe = (JButton) e.getSource();
        Navigator navi = (Navigator) waffe.getTopLevelAncestor();
        navi.getHauptfenster().getController().shoot();
        navi.getHauptfenster().requestFocus();
    }
}
