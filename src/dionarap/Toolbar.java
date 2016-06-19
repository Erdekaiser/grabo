/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author Fabian
 */
public class Toolbar extends JPanel{
   
    Hauptfenster hauptfenster;
    private ListenerButton listenerbutton = new ListenerButton();
    private JPanel pnewGame = new JPanel();
    private JButton btnnewGame = new JButton("Neues Spiel");
    private JPanel ppunktestand = new JPanel();
    private JTextField txtpunkte = new JTextField(5);
    private JPanel pmunition = new JPanel();
    //Obj. für Muniton fehlt noch
    private JPanel pspielfortschritt = new JPanel();
    private JProgressBar pbspielfortschritt;
    private JPanel psettings = new JPanel();
    private JButton btnsettings = new JButton("Settings");
    private float panelwidth = 100;
    private float panelheight = 100;
    private Dimension prefsize = new Dimension((int)panelwidth, (int)panelheight);
    
    Toolbar(Hauptfenster hauptfenster){
        
        this.hauptfenster = hauptfenster;
        //this.setFloatable(false); //Toolbar unbeweglich
        int gegneranzahl = hauptfenster.getModel().getOpponentCount();
        final int MaxGegner = gegneranzahl;
        int punkteanzahl = hauptfenster.getModel().getScore();
        pbspielfortschritt = new JProgressBar(-MaxGegner, 0);
       
        //New Game
        pnewGame.setPreferredSize(prefsize);
        pnewGame.setLayout(new BoxLayout(pnewGame, BoxLayout.Y_AXIS));
        btnnewGame.setActionCommand("NewGame");
        btnnewGame.addActionListener(listenerbutton);
        btnnewGame.setEnabled(false);
        btnnewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnewGame.add(Box.createVerticalGlue());
        pnewGame.add(btnnewGame);
        pnewGame.add(Box.createVerticalGlue());
        
        
        //Punktestand
        ppunktestand.setPreferredSize(prefsize);
        ppunktestand.setBorder(BorderFactory.createLineBorder(Color.black));
        ppunktestand.setBorder(BorderFactory.createTitledBorder("Punktestand"));
        ppunktestand.setLayout(new BoxLayout(ppunktestand, BoxLayout.Y_AXIS));
        txtpunkte.setMaximumSize(new Dimension(txtpunkte.getPreferredSize().width, txtpunkte.getPreferredSize().height));
        txtpunkte.setEditable(false);
        txtpunkte.setText(Integer.toString(punkteanzahl));
        ppunktestand.add(Box.createVerticalGlue());
        ppunktestand.add(txtpunkte);
        ppunktestand.add(Box.createVerticalGlue());

        
        //Munition
        pmunition.setPreferredSize(prefsize);
        pmunition.setBorder(BorderFactory.createLineBorder(Color.black));
        pmunition.setBorder(BorderFactory.createTitledBorder("Munition"));
        //pmunition.setLayout(new BoxLayout(pmunition, BoxLayout.X_AXIS));
        
        
        //Spielfortschritts
        pspielfortschritt.setPreferredSize(prefsize);
        pspielfortschritt.setBorder(BorderFactory.createLineBorder(Color.black));
        pspielfortschritt.setBorder(BorderFactory.createTitledBorder("Spielfortschritt"));
        pspielfortschritt.setLayout(new BoxLayout(pspielfortschritt, BoxLayout.Y_AXIS));
        pbspielfortschritt.setValue(-gegneranzahl);
        pbspielfortschritt.setStringPainted(true);
        pbspielfortschritt.setPreferredSize(new Dimension(75,20));
        pspielfortschritt.add(Box.createVerticalGlue());
        pspielfortschritt.add(pbspielfortschritt);
        pspielfortschritt.add(Box.createVerticalGlue());
        
        
        //Settings
        psettings.setPreferredSize(prefsize);
        psettings.setLayout(new BoxLayout(psettings, BoxLayout.Y_AXIS));
        btnsettings.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnsettings.setActionCommand("Settings");
        btnsettings.addActionListener(listenerbutton);
        psettings.add(Box.createVerticalGlue());
        psettings.add(btnsettings);
        psettings.add(Box.createVerticalGlue());

       
        this.setLayout(new GridLayout(1,5));
        
        this.add(pnewGame);
        this.add(ppunktestand);
        this.add(pmunition);
        this.add(pspielfortschritt);
        this.add(psettings);
    }
    
    public void setNewGameButton(){
        btnnewGame.setEnabled(true);
    }
    
    public void setScore(int score){
        this.txtpunkte.setText(Integer.toString(score));
    }
    
    public void setOpponent(int gegner){
        this.pbspielfortschritt.setValue(-gegner);
    }
}
