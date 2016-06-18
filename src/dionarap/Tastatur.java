package dionarap;

import java.awt.*;
import javax.swing.*;
import static javax.swing.SwingConstants.LEADING;
import static javax.swing.SwingConstants.RIGHT;

//Klasse für das Panel des Kindfensters
public class Tastatur extends JPanel
{

    public Tastatur()
    {
        IconLoader loader = new IconLoader();
        this.setLayout(new GridLayout(3, 3));
        ListenerBewegung listenerBewegung = new ListenerBewegung();
        ListenerWaffe listenerWaffe = new ListenerWaffe();
        
        //Nummern auf den Buttons anzeigen ... in umgekehrter reihenfolge
        for(int i = 9; i > 0; i--)
        {
            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            
            //JLabel label = new JLabel();
            JButton Knopf =  new JButton(loader.getNavigatorIcon(i-1));
            Knopf.setPreferredSize(new Dimension (50, 50));
            Knopf.setMargin(new Insets(0, 0, 0, 0));
 
            Knopf.setActionCommand(Integer.toString(i));
            //Knopf.add(label);
            this.add(Knopf);
            //this.setBorder(BorderFactory.createLineBorder(Color.RED));
            //Abfrage ob Tasteninput ein Schuss ist
            if(Integer.valueOf(Knopf.getActionCommand()) == 5) 
            {
                Knopf.addActionListener(listenerWaffe);
            }else{
                Knopf.addActionListener(listenerBewegung);
            }
            
        }
    }
}
