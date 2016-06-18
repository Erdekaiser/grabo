/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Fabian
 */
public class IconLoader {
    
    static public final String STANDARD_THEME = "dracula";
    static private final String[] DATEIENDUNG = new String[]{".gif", ".jpg", ".png"};
    
    static private IconLoader iconloader;
    
    private String root;
    
    private String theme;
    private ArrayList<String> themelist;
    
    private ImageIcon ammo, destruction, loss, obstacle, opponent, vortex, win, gameover, gewonnen;
    private ImageIcon[] player, navigator;
    
    
    
    IconLoader(){
        root = System.getProperty("user.dir") + File.separator + "images" + File.separator;
        themelist = new ArrayList<String>();
        try {
            File datei = new File(this.root);
            File[] files = datei.listFiles();
            for(int i = 0; i < files.length; i++){
                if(!files[i].isDirectory()) continue;
                this.themelist.add(files[i].getName());
            }        
        } catch(Exception e){}
        player = new ImageIcon[9];
        navigator = new ImageIcon[9];
        if (theme == null){
            setTheme(IconLoader.STANDARD_THEME);
        }
        
    }
    
    private void loadIcons(){
        this.ammo = this.getIcon("ammo");
        this.destruction = this.getIcon("destruction");
        this.loss = this.getIcon("loss");
        this.obstacle = this.getIcon("obstacle");
        this.opponent = this.getIcon("opponent");
        this.vortex = this.getIcon("vortex");
        this.win = this.getIcon("win");
        this.gameover = this.getIcon("gameover");
        this.gewonnen = this.getIcon("gewonnen");
        
        //Player Array List mit Icons füllen
        for(int i = 0; i < 9; i++){
            //tolle nummerierung der Bilder ... 5 fehlt. dafür gibts nen player.gif ohne Nr.
            if(i==4){
                this.player[i] = this.getIcon("player");
            }else{
                this.player[i] = this.getIcon("player" + (i+1));
            }
        }
        
        //Button Array List mit Icons füllen
        for(int i = 0; i < 9; i++){
            this.theme = "navigator";
            this.navigator[i] = this.getIcon("taste" + (i+1));
        }
    }
    
    //set-Methode
    public void setTheme(String name){
        this.theme = name;
        this.loadIcons();
        this.theme = name;
    }
    
    //get-Methoden
    private ImageIcon getIcon(String datei){
        String basisdatei = this.root + this.theme + File.separator + datei;
        if(datei == "gewonnen" || datei == "gameover"){
            basisdatei = this.root + datei;
        }
        for(int i = 0; i < IconLoader.DATEIENDUNG.length; i++){
            File tempdatei = new File(basisdatei + IconLoader.DATEIENDUNG[i]);
            if(tempdatei.exists()) return new ImageIcon(tempdatei.getAbsolutePath());
            //System.out.println(tempdatei.getAbsolutePath());
        }
        System.out.println("Dateipfad: " + datei + " nicht gefunden!");
        return null;
    }
    
    public static IconLoader getTheme(){
        if(IconLoader.iconloader == null){
            IconLoader.iconloader = new IconLoader();
        }
        return IconLoader.iconloader;
    }
    
    public String[] getThemelist(){
        int size = this.themelist.size();
        String[] tempthemelist = new String[size];
        for(int i = 0; i < size; i++){
            tempthemelist[i] = this.themelist.get(i);
        }
        return tempthemelist;
    }
    
    public ImageIcon getPlayerIcon(int richtung){
        return player[richtung-1];
    }
    
    public String getCurrentTheme(){
        return this.theme;
    }
    
    public ImageIcon getAmmoIcon(){
        return ammo;
    }
    
    public ImageIcon getDestructionIcon(){
        return destruction;
    }
    
    public ImageIcon getLossIcon(){
        return loss;
    }
    
    public ImageIcon getObstacleIcon(){
        return obstacle;
    }
    
    public ImageIcon getOpponentIcon(){
        return opponent;
    }
    
    public ImageIcon getVortexIcon(){
        return vortex;
    }
    
    public ImageIcon getWinIcon(){
        return win;
    }
    
    public ImageIcon getNavigatorIcon(int nummer){
        return navigator[nummer];
    }
    
    public ImageIcon getGewonnenIcon(){
        return gewonnen;
    }
    
    public ImageIcon getGameoverIcon(){
        return gameover;
    }
}
