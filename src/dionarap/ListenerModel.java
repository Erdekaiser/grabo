/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.DionaRapListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ak-141584
 */
public class ListenerModel implements DionaRapListener
{
    private Hauptfenster mainfenster;
    
    public ListenerModel(Hauptfenster hauptfenster)
    {
        mainfenster = hauptfenster; 
    }

    @Override
    public void modelChanged(DionaRapChangedEvent drce) 
    {
        mainfenster.getToolbar().setScore(mainfenster.getModel().getScore());
        mainfenster.getToolbar().setOpponent(mainfenster.getModel().getOpponentCount());
        mainfenster.getToolbar().setAmmo(mainfenster.getModel().getShootAmount(), IconLoader.getTheme().getAmmoIcon());

        mainfenster.getSpielfeld().delete();
        mainfenster.getSpielfeld().draw();
    }

    @Override
    public void statusChanged(GameStatusEvent gse) 
    {
        Spielfeld feld = mainfenster.getSpielfeld();
        if(gse.isGameWon()){
            feld.updateStatus(GameStatus.gamewon);
            spielendeDialog("You win!", "Victory", IconLoader.getTheme().getGewonnenIcon());
        }
                
        if(gse.isGameOver()){
            feld.updateStatus(GameStatus.gameover);
            spielendeDialog("You lose!", "Game Over", IconLoader.getTheme().getGameoverIcon());
        }
    }
    
    private void spielendeDialog(String nachricht, String titel, ImageIcon icon){
        String[] options = new String[]{"Neues Spiel", "Abbruch"};
        
        mainfenster.getController().deactivateMultiThreading();
        
        int n = JOptionPane.showOptionDialog(mainfenster,
                nachricht,
                titel,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon,
                options,
                options[0]);
        
        if(n == JOptionPane.YES_OPTION){
            mainfenster.startNewGame();
        }
        else if (n == JOptionPane.NO_OPTION || n == JOptionPane.CLOSED_OPTION){
              mainfenster.getToolbar().setNewGameButton(); //newGame Button sichtbar machen
            //System.out.println("Keine Option wurde gewählt!");
        }      
    }
}
