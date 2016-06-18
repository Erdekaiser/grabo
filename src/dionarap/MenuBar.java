/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Fabian
 */
public class MenuBar extends JMenuBar {
    
    static public final String CMD_TOGGLENAVIGATOR = "toggleNavigator";
    
    private Hauptfenster hauptfenster;
    private ListenerMenu listenermenu;
    
    
    public MenuBar(Hauptfenster hauptfenster, ListenerMenu listenermenu){
        this.hauptfenster = hauptfenster;
        this.listenermenu = listenermenu;
        
        //Position Toolbar Menu
        JMenu ansicht = new JMenu("Ansicht");
        ansicht.setMnemonic('a');
        ansicht.add(this.toolbarlayout());
        ansicht.add(this.navigatoronoff("Navigator", MenuBar.CMD_TOGGLENAVIGATOR, true));
        ansicht.add(this.lookandfeel());
        this.add(ansicht);

    }
    
    private JMenuItem toolbarlayout(){    
        String[] tbupdown = new String[]{"Oben", "Unten"};
        
        JMenuItem toolbarlayoutitem = new JMenu("Position Toolbar");
        
        ButtonGroup grouptoolbarlayout = new ButtonGroup();
        String toolbarpos = hauptfenster.getToolbarPos();
        for(int i = 0; i < tbupdown.length; i++){
            toolbarlayoutitem.add(this.createRadioMenuItem(tbupdown[i],
                    tbupdown[i],
                    grouptoolbarlayout,
                    toolbarpos.equals(tbupdown[i])));
        }    
        return toolbarlayoutitem;
    }
       
    private JMenuItem lookandfeel(){
        LookAndFeelInfo[] lafinfo = UIManager.getInstalledLookAndFeels();
        
        JMenuItem menuItemLookAndFeel = new JMenu("Look and Feel");
        
        ButtonGroup groupLookAndFeel = new ButtonGroup();
        String lookandfeel = UIManager.getLookAndFeel().getName();
        for(int i = 0; i < lafinfo.length; i++){
            menuItemLookAndFeel.add(this.createRadioMenuItem(lafinfo[i].getName(),
                    lafinfo[i].getClassName(),
                    groupLookAndFeel,
                    lookandfeel.equals(lafinfo[i].getName())));
        }
        
        return menuItemLookAndFeel;
    }
    
    private JCheckBoxMenuItem navigatoronoff(String title, String command, boolean selected){
        JCheckBoxMenuItem menuitem = new JCheckBoxMenuItem(title);
        menuitem.setActionCommand(command);
        menuitem.addActionListener(this.listenermenu);
        menuitem.setState(selected);
        return menuitem;        
    }
    
    private JRadioButtonMenuItem createRadioMenuItem(String title, String command, ButtonGroup group, boolean selected){
        JRadioButtonMenuItem menuitem = new JRadioButtonMenuItem(title);
        menuitem.setActionCommand(command);
        menuitem.setSelected(selected);
        menuitem.addActionListener(this.listenermenu);
        group.add(menuitem);
        return menuitem;
    }
}
