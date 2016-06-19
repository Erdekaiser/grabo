/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import de.fhwgt.dionarap.model.data.MTConfiguration;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Fabian
 */
public class SettingsDialog extends JDialog implements ActionListener, ChangeListener, ItemListener, FocusListener{
    //Allg. Komponenten
    private Hauptfenster hauptfenster;
    private MTConfiguration conf = new MTConfiguration();
    private HashMap<String, String>TEMP_spieleinstellungen = new HashMap<String, String>();
    private Dimension prefSize = new Dimension(250,100);
    private static final int SLIDER_MIN = 0;
    private static final int SLIDER_MAX = 10000;
    
    JPanel kpanel = new JPanel();
    
    //Komponenten f. Zeile 1
    JLabel lwbg = new JLabel();
    JSlider swbg;
    //Komponenten f. Zeile 2
    JLabel lves = new JLabel();
    JSlider sves;
    //Komponenten f. Zeile 3
    JLabel lwgs = new JLabel();
    JSlider swgs;
    //Komponenten f. Zeile 4    
    JLabel lzwg = new JLabel();
    JCheckBox czwg = new JCheckBox(Settings.ZWG);
    //Komponenten f. Zeile 5    
    JLabel lgkh = new JLabel();
    JCheckBox cgkh = new JCheckBox(Settings.GKH);
    //Komponenten f. Zeile 6    
    JLabel lgkg = new JLabel();
    JCheckBox cgkg = new JCheckBox(Settings.GKG);
    //Komponenten f. Zeile 7
    JLabel lazs = new JLabel();
    JTextField tazs = new JTextField();
    //Komponenten f. Zeile 8
    JLabel lass = new JLabel();
    JTextField tass = new JTextField();
    //Komponenten f. Zeile 9
    JLabel lahi = new JLabel();
    JTextField tahi = new JTextField();
    //Komponenten f. Zeile 10
    JLabel ladg = new JLabel();
    JTextField tadg = new JTextField();
    //Komponenten f. Zeile 11
    JButton bannehmen = new JButton("Annehmen");
    JButton babbrechen = new JButton("Abbrechen");
    
    SettingsDialog(Hauptfenster hf){
        super(hf, "Settings");
        this.hauptfenster = hf;
        
        TEMP_spieleinstellungen = hf.getSettings().getSettings();
        
        System.out.println(TEMP_spieleinstellungen);
        
        //Slider Init (MIN, MAX, INIT) Werte
        swbg = new JSlider(JSlider.HORIZONTAL,
                SLIDER_MIN, SLIDER_MAX, Integer.parseInt(TEMP_spieleinstellungen.get(Settings.WGB)));
        sves = new JSlider(JSlider.HORIZONTAL,
                SLIDER_MIN, SLIDER_MAX, Integer.parseInt(TEMP_spieleinstellungen.get(Settings.VES)));
        swgs = new JSlider(JSlider.HORIZONTAL,
                SLIDER_MIN, SLIDER_MAX, Integer.parseInt(TEMP_spieleinstellungen.get(Settings.WGS)));       
        
        //Komponenten f. Zeile 1 - Zelle 1 #WBG
        lwbg.setPreferredSize(prefSize);
        lwbg.setText(Settings.WGB);
        //Zelle 2
        swbg.setName(Settings.WGB);
        swbg.addChangeListener(this);
        swbg.setPreferredSize(prefSize);
        swbg.setMajorTickSpacing(2000);
        swbg.setPaintTicks(true);
        swbg.setPaintLabels(true);
                
        //Komponenten f. Zeile 2 - Zelle 1 #VES
        lves.setPreferredSize(prefSize);
        lves.setText(Settings.VES);
        //Zelle 2
        sves.setName(Settings.VES);
        sves.addChangeListener(this);
        sves.setPreferredSize(prefSize);
        sves.setMajorTickSpacing(2000);
        sves.setPaintTicks(true);
        sves.setPaintLabels(true);
        
        //Komponenten f. Zeile 3 - Zelle 1 #WGS
        lwgs.setPreferredSize(prefSize);
        lwgs.setText(Settings.WGS);
        //Zelle 2
        swgs.setName(Settings.WGS);
        swgs.addChangeListener(this);
        swgs.setPreferredSize(prefSize);
        swgs.setMajorTickSpacing(2000);
        swgs.setPaintTicks(true);
        swgs.setPaintLabels(true);
        
        //Komponenten f. Zeile 4 - Zelle 1 #ZWG
        lzwg.setPreferredSize(prefSize);
        //Zelle 2
        czwg.addItemListener(this);
        czwg.setSelected(Boolean.parseBoolean(TEMP_spieleinstellungen.get(Settings.ZWG)));
        
        //Komponenten f. Zeile 5 - Zelle 1 #GKH
        lgkh.setPreferredSize(prefSize);
        //Zelle 2
        cgkh.addItemListener(this);
        cgkh.setSelected(Boolean.parseBoolean(TEMP_spieleinstellungen.get(Settings.GKH)));
        
        //Komponenten f. Zeile 6 - Zelle 1 #GKG
        lgkg.setPreferredSize(prefSize);
        //Zelle 2
        cgkg.addItemListener(this);
        cgkg.setSelected(Boolean.parseBoolean(TEMP_spieleinstellungen.get(Settings.GKG)));
        
        //Komponenten f. Zeile 7 - Zelle 1 #AZS
        lazs.setPreferredSize(prefSize);
        lazs.setText(Settings.AZS);
        //Zelle 2
        tazs.setText(TEMP_spieleinstellungen.get(Settings.AZS));
        tazs.setName(Settings.AZS);
        tazs.setActionCommand(Settings.AZS);
        tazs.addFocusListener(this);
        
        //Komponenten f. Zeile 8 - Zelle 1 #ASS
        lass.setPreferredSize(prefSize);
        lass.setText(Settings.ASS);
        //Zelle 2
        tass.setText(TEMP_spieleinstellungen.get(Settings.ASS));
        tass.setName(Settings.ASS);
        tass.setActionCommand(Settings.ASS);
        tass.addFocusListener(this);
        
        //Komponenten f. Zeile 9 - Zelle 1 #AHI
        lahi.setPreferredSize(prefSize);
        lahi.setText(Settings.AHI);
        //Zelle 2
        tahi.setText(TEMP_spieleinstellungen.get(Settings.AHI));
        tahi.setName(Settings.AHI);
        tahi.setActionCommand(Settings.AHI);
        tahi.addFocusListener(this);
        
        //Komponenten f. Zeilee 10 - Zell 1 #ADG
        ladg.setPreferredSize(prefSize);
        ladg.setText(Settings.ADG);
        //Zelle 2
        tadg.setText(TEMP_spieleinstellungen.get(Settings.ADG));
        tadg.setName(Settings.ADG);
        tadg.setActionCommand(Settings.ADG);
        tadg.addFocusListener(this);
        
        //Komponenten f. Zeile 11 - Zelle 1 #Buttons
        bannehmen.setActionCommand("NewGame");
        bannehmen.addActionListener(this);
        //Zelle 2
        babbrechen.setActionCommand("CloswWindow");
        babbrechen.addActionListener(this);
        
        
        kpanel.setLayout(new GridLayout(11,2));
        //Zeile 1
        kpanel.add(lwbg);
        kpanel.add(swbg);
        //Zeile 2
        kpanel.add(lves);
        kpanel.add(sves);
        //Zeile 3
        kpanel.add(lwgs);
        kpanel.add(swgs);
        //Zeile 4
        kpanel.add(lzwg);
        kpanel.add(czwg);
        //Zeile 5
        kpanel.add(lgkh);
        kpanel.add(cgkh);
        //Zeile 6
        kpanel.add(lgkg);
        kpanel.add(cgkg);
        //Zeile 7
        kpanel.add(lazs);
        kpanel.add(tazs);
        //Zeile 8
        kpanel.add(lass);
        kpanel.add(tass);
        //Zeile 9
        kpanel.add(lahi);
        kpanel.add(tahi);
        //Zeile 10
        kpanel.add(ladg);
        kpanel.add(tadg);
        //Zeile 11
        kpanel.add(bannehmen);
        kpanel.add(babbrechen);
        
        this.add(kpanel);
        this.setSize(600, 600);
        this.setModal(true);
        this.setResizable(false);
        this.setLocationRelativeTo(hf);
        this.setVisible(true);
    }
    
    public void onClose(){
        TEMP_spieleinstellungen = hauptfenster.getSettings().getSettings();
        hauptfenster.getSettings().setSettings(TEMP_spieleinstellungen);
        dispose();
    }
    
    //f.Buttons & TextFields
    @Override
    public void actionPerformed(ActionEvent e) {
        if("CloswWindow".equals(e.getActionCommand())){
            onClose();
        }
        if("NewGame".equals(e.getActionCommand())){
            hauptfenster.getSettings().setSettings(TEMP_spieleinstellungen);
            System.out.println("NewGame: "+ TEMP_spieleinstellungen);
            hauptfenster.startNewGame();
        }
    }
    
    //f. Slider
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
 
        if(source.getName().equals(Settings.WGB)){
            int value = source.getValue();
            TEMP_spieleinstellungen.put(Settings.WGB, Integer.toString(value));
        }
        if(source.getName().equals(Settings.VES)){
            int value = (int)source.getValue();
            TEMP_spieleinstellungen.put(Settings.VES, Integer.toString(value));
        }
        if(source.getName().equals(Settings.WGS)){
            int value = (int)source.getValue();
            TEMP_spieleinstellungen.put(Settings.WGS, Integer.toString(value));
        } 
    }
    
    //f. CheckBoxes
    @Override
    public void itemStateChanged(ItemEvent e){
        Object source = e.getItemSelectable();
        
        if(source == czwg){
             if(e.getStateChange() == ItemEvent.DESELECTED){
                TEMP_spieleinstellungen.put(Settings.ZWG, "false");
            }
            if(e.getStateChange() == ItemEvent.SELECTED){
                TEMP_spieleinstellungen.put(Settings.ZWG, "true");
            }
        }
        if(source == cgkh){
            if(e.getStateChange() == ItemEvent.DESELECTED){
                TEMP_spieleinstellungen.put(Settings.GKH, "false");
            }
            if(e.getStateChange() == ItemEvent.SELECTED){
                TEMP_spieleinstellungen.put(Settings.GKH, "true");
            }
        }    
        if(source == cgkg){
            if(e.getStateChange() == ItemEvent.DESELECTED){
                TEMP_spieleinstellungen.put(Settings.GKG, "false");
            }
            if(e.getStateChange() == ItemEvent.SELECTED){
                TEMP_spieleinstellungen.put(Settings.GKG, "true");
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {}

    @Override
    public void focusLost(FocusEvent e) {
        JTextField source = (JTextField)e.getSource();
        
        if(source.getName().equals(Settings.AZS)){
            System.out.println("bin da");
            TEMP_spieleinstellungen.put(Settings.AZS, source.getText());
            System.out.println("mein text ist "+source.getText());
        }
        if(source.getName().equals(Settings.ASS)){
            TEMP_spieleinstellungen.put(Settings.ASS, source.getText());
        }
        if(source.getName().equals(Settings.AHI)){
            TEMP_spieleinstellungen.put(Settings.AHI, source.getText());
        }
        if(source.getName().equals(Settings.ADG)){
            TEMP_spieleinstellungen.put(Settings.ADG, source.getText());
        }
    }
}
