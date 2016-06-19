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
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    IconLoader loader = IconLoader.getTheme();
    private ListenerButton listenerbutton = new ListenerButton();
    private JPanel pnewGame = new JPanel();
    private JButton btnnewGame = new JButton("Neues Spiel");
    private JPanel ppunktestand = new JPanel();
    private JTextField txtpunkte = new JTextField(5);
    private JPanel pmunition = new JPanel();
    private JLabel lmunition[] = new JLabel[3];
    private ImageIcon imunition = new ImageIcon();
    private Graphics2D muni2D;
    private JPanel pspielfortschritt = new JPanel();
    private JProgressBar pbspielfortschritt;
    private JPanel psettings = new JPanel();
    private JButton btnsettings = new JButton("Settings");
    private float panelwidth = 100;
    private float panelheight = 100;
    private Dimension prefsize = new Dimension((int)panelwidth, (int)panelheight);
    
    //Thread für das Blinken
    Thread blinkThread;
    //Runnable Objekt
    MunitionBlink mBlink;

    
    
    Toolbar(Hauptfenster hauptfenster){
        
        this.hauptfenster = hauptfenster;
        //this.setFloatable(false); //Toolbar unbeweglich
        int gegneranzahl = hauptfenster.getModel().getOpponentCount();
        final int MaxGegner = gegneranzahl;
        int punkteanzahl = hauptfenster.getModel().getScore();
        pbspielfortschritt = new JProgressBar(-MaxGegner, 0);
        lmunition[0] = new JLabel();
        lmunition[1] = new JLabel();
        lmunition[2] = new JLabel();
        imunition = loader.getAmmoIcon();
        
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
        pmunition.setLayout(new BoxLayout(pmunition, BoxLayout.X_AXIS));
        lmunition[0].setBorder(BorderFactory.createLineBorder(Color.black,1));
        lmunition[1].setBorder(BorderFactory.createLineBorder(Color.black,1));
        lmunition[2].setBorder(BorderFactory.createLineBorder(Color.black,1));
        pmunition.add(Box.createHorizontalGlue());
	pmunition.add(lmunition[2]);
	pmunition.add(new Box.Filler(new Dimension(0,0), new Dimension(5,1),  new Dimension(10,1)));
	pmunition.add(lmunition[1]);
	pmunition.add(new Box.Filler(new Dimension(0,0), new Dimension(5,1),  new Dimension(10,1)));
	pmunition.add(lmunition[0]);
        pmunition.add(Box.createHorizontalGlue());
        
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
        
        //Thread f. Blinken
        mBlink = new MunitionBlink(this);
        blinkThread = new Thread(mBlink);
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
    
    public void setAmmo(int ammo, ImageIcon iammo) {
	imunition = iammo;
        for(int i = 0; i < 3; i++ ) {
            lmunition[i].setIcon(null);
            lmunition[i].setText("");
            lmunition[i].setBorder(null);
	}
	if(ammo == 1)
            stopBlinkThread();
	if(ammo <= 3) {
            for(int i = 0; i < ammo; i++ ) {
		lmunition[i].setIcon(imunition);
		lmunition[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
            }
	} else {
            for(int i = 0; i < 2; i++ ) {
		lmunition[i].setIcon(imunition);
            }
            lmunition[2].setText("*"+Integer.toString(ammo));
            
        }
        this.validate();
}
    
    void stopBlinkThread() {
	if(blinkThread.isAlive() == true) {
        	blinkThread.interrupt();
	}
    }
	
    void startBlinkThread() {
	if(blinkThread.isAlive() == false) {
        	blinkThread = new Thread(mBlink);
		blinkThread.start();
	}
    }
    
    public void ammoBlink(boolean blinkOn) {
	if(blinkOn){
            pmunition.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red, 1), "Munition"));
        }else{
            pmunition.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Munition"));
        }
    }    
}
