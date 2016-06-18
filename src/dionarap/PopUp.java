/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author WhiteShadow
 */
public class PopUp extends JPopupMenu 
{
    JMenuItem alien;
    JMenuItem dracula;
    JMenuItem helsing;
    JMenuItem luke;
    JMenuItem spacewars;
    JMenuItem squarehead;
    JMenuItem vader;
    Hauptfenster fenster;
    int testvar = 0;
    public PopUp(Hauptfenster hauptfenster)
    {
        this.fenster = hauptfenster;
        String[] themes = IconLoader.getTheme().getThemelist();
        String currenttheme = IconLoader.getTheme().getCurrentTheme();
        //Erstellen der Popup Items
        alien = new JMenuItem("alien");
        dracula = new JMenuItem("dracula");
        helsing = new JMenuItem("helsing");
        luke = new JMenuItem("luke");
        spacewars = new JMenuItem("spacewars");
        squarehead = new JMenuItem("squarehead");
        vader = new JMenuItem("vader");
        
        //Reseten der Backround Color
        alien.setBackground(Color.LIGHT_GRAY);
        dracula.setBackground(Color.LIGHT_GRAY);
        helsing.setBackground(Color.LIGHT_GRAY);
        luke.setBackground(Color.LIGHT_GRAY);
        spacewars.setBackground(Color.LIGHT_GRAY);
        squarehead.setBackground(Color.LIGHT_GRAY);
        vader.setBackground(Color.LIGHT_GRAY);
            
        System.out.println(currenttheme);
        //Einfärben des momentan gewählten thema
        switch(currenttheme)
        {
            case "alien": alien.setBackground(Color.CYAN); break;
            case "dracula": dracula.setBackground(Color.CYAN); break;
            case "helsing": helsing.setBackground(Color.CYAN); break;
            case "luke": luke.setBackground(Color.CYAN); break;
            case "spacewars": spacewars.setBackground(Color.CYAN); break;
            case "squarehead": squarehead.setBackground(Color.CYAN); break;
            case "vader": vader.setBackground(Color.CYAN); break;
            default: alien.setBackground(Color.CYAN); break;
        }
        
        alien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aa) 
            {
                IconLoader.getTheme().setTheme("alien");
                fenster.getSpielfeld().draw();
            }
        });
        dracula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ad) 
            {
                IconLoader.getTheme().setTheme("dracula");
                fenster.getSpielfeld().draw();
            }
        });
        helsing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ah) 
            {
                IconLoader.getTheme().setTheme("helsing");
                fenster.getSpielfeld().draw();
            }
        });
        luke.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent al) 
            {
                IconLoader.getTheme().setTheme("luke");
                fenster.getSpielfeld().draw();
            }
        });
         spacewars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent as) 
            {
                IconLoader.getTheme().setTheme("spacewars");
                fenster.getSpielfeld().draw();
            }
        });
        squarehead.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aq) 
            {
                IconLoader.getTheme().setTheme("squarehead");
                fenster.getSpielfeld().draw();
            }
        });
        vader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent av) 
            {
                IconLoader.getTheme().setTheme("vader");
                fenster.getSpielfeld().draw();
            }
        });
        add(alien);
        add(dracula);
        add(helsing);
        add(luke);
        add(spacewars);
        add(squarehead);
        add(vader);
        
//        
//        String s = (String)JOptionPane.showInputDialog(fenster,
//        nachricht,
//        titel,
//        JOptionPane.PLAIN_MESSAGE,
//        UIManager.getIcon("OptionPane.questionIcon"),
//        themes,
//        currenttheme);
//
//        if((s  != null) && (s.length() >0))
//        {
//            IconLoader.getTheme().setTheme(s);
//        }
//        System.out.println("Detected Mouse Right Click!");

    }

    
}
