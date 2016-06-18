/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;


import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
 *
 * @author Fabian
 */
public class ListenerMouse implements MouseListener{

    private Hauptfenster hauptfenster;
    private JLabel[][] spielfeld;
    private int mouseX;
    private int mouseY;
    
    public void setSpielfeld(JLabel[][] feld){
        spielfeld = feld;
    }
    
    ListenerMouse(Hauptfenster fenster){
        hauptfenster = fenster;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
                        
            //Linke Maustaste
            if(e.getButton() == MouseEvent.BUTTON1)
            {
                //Ermittle geklicktes JLabel
                for(int j = 0; j < 10; j++)
                {
                    for(int i = 0; i < 10; i++){
                        if(e.getSource() == spielfeld[i][j]){
                            mouseX = i;
                            mouseY = j;
                        }
                    }
                }
                
                //aktuelle Spieler Koordinaten von Logik
                int playerX = hauptfenster.getModel().getPlayer().getX();
                int playerY = hauptfenster.getModel().getPlayer().getY();

                //Bewegung nach oben
                if((mouseX+1 == playerX || mouseX-1 == playerX || mouseX == playerX) && (mouseY+1 == playerY || mouseY-1 == playerY || mouseY == playerY)){
                    if(mouseX > playerX && mouseY < playerY) hauptfenster.getController().movePlayer(9);
                    if(mouseX == playerX && mouseY < playerY) hauptfenster.getController().movePlayer(8);
                    if(mouseX < playerX && mouseY < playerY) hauptfenster.getController().movePlayer(7);
                    if(mouseX > playerX && mouseY == playerY) hauptfenster.getController().movePlayer(6);
                    if(mouseX == playerX && mouseY == playerY) hauptfenster.getController().shoot();
                    if(mouseX < playerX && mouseY == playerY) hauptfenster.getController().movePlayer(4);
                    if(mouseX > playerX && mouseY > playerY) hauptfenster.getController().movePlayer(3);
                    if(mouseX == playerX && mouseY > playerY) hauptfenster.getController().movePlayer(2);
                    if(mouseX < playerX && mouseY > playerY) hauptfenster.getController().movePlayer(1);
                }                
            }	    
	    else if(e.getButton() == MouseEvent.BUTTON3)
	    {
                PopUp menu = new PopUp(hauptfenster);
                menu.show(e.getComponent(), e.getX(), e.getY());
	    }
            hauptfenster.getSpielfeld().draw();
            }
    
    

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
