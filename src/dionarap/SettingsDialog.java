/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author Fabian
 */
public class SettingsDialog extends JDialog{
    private JLabel Beschreibung1, Beschreibung2, Beschreibung3, Beschreibung4, Beschreibung5;
    private JButton Annehmen, Abbrechen;
    private JSlider Slider1, Slider2, Slider3;
    private JTextField TextField1, TextField2, TextField3, TextField4;
    private JCheckBox TextBox1, TextBox2, TextBox3;
    private Dimension prefSize;
    
    
    SettingsDialog(Hauptfenster hf){

        Beschreibung1 = new JLabel();
        Slider1 = new JSlider();
        prefSize = new Dimension(100,200);
        
        System.out.println(hf.getSettings().getSettings());
        
        
        //Zeile1 - Wartezeit der Gegner zu Beginn
        System.out.println(Settings.WGB);
        Beschreibung1.setText(Settings.WGB+":");
        Beschreibung1.setVisible(true);
       
        
        this.setVisible(true);
        
        this.setLayout(new GridLayout(2,11));
        
        //Zeile1
        this.add(Beschreibung1);
        this.add(Slider1);
    }
}
