package dionarap;

import java.awt.*;
import javax.swing.*;

//Klasse für das Kindfenster
public class Navigator extends JWindow{
	
        private Hauptfenster hauptfenster;
        
	public Navigator(Hauptfenster Hauptfenster){
		//Hauptfenster als Vater definieren
                super(Hauptfenster);
                hauptfenster = Hauptfenster;
                Octagon octagon = new Octagon();
                
                
		//System.out.println("X:"+Hauptfenster.getX()+" ,Width:"+ Hauptfenster.getWidth()+" ,Y:"+Hauptfenster.getY());
		this.setLocation((int)Hauptfenster.getX()+Hauptfenster.getWidth()+20, (int)Hauptfenster.getY());
                
                this.setShape(octagon);
		//Tastatur Panel hinzufügen
		this.add(new Tastatur());
		this.setVisible(true);
		
                
                this.pack();
	}
        
        
        public Hauptfenster getHauptfenster()
        {
                return hauptfenster;
        }     
}
