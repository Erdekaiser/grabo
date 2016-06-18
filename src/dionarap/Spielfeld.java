package dionarap;

import de.fhwgt.dionarap.model.data.DionaRapModel;
import de.fhwgt.dionarap.model.objects.*;
import java.awt.*;
import javax.swing.*;

/*Klasse für das Panel des Hauptfensters*/
public class Spielfeld extends JPanel{
    
private int Spalten;
private int Zeilen;    
private JLabel spielfeld_array[][];    

private GameStatus status = GameStatus.game;

	
    public Spielfeld(Hauptfenster fenster, int Zeilen, int Spalten){
            
            Hauptfenster hauptfenster = fenster;
            
            ListenerMouse mouseListener = new ListenerMouse(hauptfenster);
            
            this.Zeilen = Zeilen;
            this.Spalten = Spalten;
            
            this.spielfeld_array = new JLabel[Zeilen][Spalten];
            
            //System.out.println("Spalten: " + Spalten + " Zeilen: " + Zeilen);
            
            /*Gridlayout erstellen*/
            this.setLayout(new GridLayout(Spalten, Zeilen));

            /*Label Array füllen*/
            for(int j = 0; j < Spalten; j++)
            {
                    for(int i = 0; i < Zeilen; i++){
                            spielfeld_array[i][j] = new JLabel("",JLabel.CENTER);
                            spielfeld_array[i][j].setFont(new Font("Arial", Font.BOLD, 20) );
                            if((i+j)%2 == 0){
                                    spielfeld_array[i][j].setBackground(Color.black);
                                    spielfeld_array[i][j].setForeground(Color.white);   
                            }else{
                                    spielfeld_array[i][j].setBackground(Color.white);
                                    spielfeld_array[i][j].setForeground(Color.black);
                            }
                            spielfeld_array[i][j].setPreferredSize(new Dimension(50, 50));
                            spielfeld_array[i][j].setOpaque(true);
                            spielfeld_array[i][j].setVisible(true);
                            //Mouse Listener an JLabel binden
                            spielfeld_array[i][j].addMouseListener(mouseListener);
                            mouseListener.setSpielfeld(spielfeld_array);
                            this.add(spielfeld_array[i][j]);
                    }
            }
    }
      

    public void draw()
    {
        //Oberste instance des Spielfelds wird aufgerufen
        Hauptfenster fenster = (Hauptfenster) this.getTopLevelAncestor();
        
        DionaRapModel model = fenster.getModel();

        AbstractPawn[] pawns = fenster.getModel().getAllPawns();
        
        IconLoader loader = IconLoader.getTheme();
        
        //Schleife um array zu durchlaufen
        for(int i = 0; i < pawns.length; i++)
        {
            
            if(model.isGameOver()){
                this.updateStatus(GameStatus.gameover);
            }
            if(model.isGameWon()){
                this.updateStatus(GameStatus.gamewon);    
            }
            
            //Überprüfen ob in Pawns ein Player vorhanden ist
            if(pawns[i] instanceof Player)
            {
                Player p = (Player)pawns[i];
                //Spieler mit P wird auf dem Spielfeld erzeugt
                if(this.status == GameStatus.gameover){
                    spielfeld_array[pawns[i].getX()][pawns[i].getY()].setIcon(loader.getLossIcon());
                } else if (this.status == GameStatus.gamewon){
                    spielfeld_array[pawns[i].getX()][pawns[i].getY()].setIcon(loader.getWinIcon());
                } else {
                    spielfeld_array[pawns[i].getX()][pawns[i].getY()].setIcon(loader.getPlayerIcon(p.getViewDirection()));
                }
            }
            
                //Überprüfen ob in Pawns ein Opponent vorhanden ist
            if(pawns[i] instanceof Opponent)
            {
                //Spieler mit P wird auf dem Spielfeld erzeugt
                spielfeld_array[pawns[i].getX()][pawns[i].getY()].setIcon(loader.getOpponentIcon());              
            }
            
                //Überprüfen ob in Pawns ein Obstacle vorhanden ist
            if(pawns[i] instanceof Obstacle)
            {
                //Spieler mit P wird auf dem Spielfeld erzeugt
                spielfeld_array[pawns[i].getX()][pawns[i].getY()].setIcon(loader.getObstacleIcon());
            }
            
                //Überprüfen ob in Pawns ein Vortex vorhanden ist
            if(pawns[i] instanceof Vortex)
            {
                //Spieler mit P wird auf dem Spielfeld erzeugt
                spielfeld_array[pawns[i].getX()][pawns[i].getY()].setIcon(loader.getVortexIcon());
                spielfeld_array[pawns[i].getX()][pawns[i].getY()].setForeground(Color.red);
            }
            
            if(pawns[i] instanceof Ammo){
                spielfeld_array[pawns[i].getX()][pawns[i].getY()].setIcon(loader.getAmmoIcon());
            }
            
            if(pawns[i] instanceof Destruction)
            {
                spielfeld_array[pawns[i].getX()][pawns[i].getY()].setIcon(loader.getDestructionIcon());
            }
        }  
    }
    
    public void delete()
    {
        for(int i = 0; i < Zeilen; i++)
        {
            for(int j = 0; j < Spalten; j++){
                spielfeld_array[i][j].setIcon(null);         
            }
        }
    }  
    
    public void updateStatus(GameStatus status){
        if(this.status == GameStatus.game){
            this.status = status;
        }
    }
    
    public void setZeilenSpalten(int x, int y){
        this.Zeilen = x;
        this.Spalten = y;
    }
}
