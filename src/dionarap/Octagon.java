/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dionarap;

import java.awt.Polygon;

/**
 *
 * @author Fabian
 */
public class Octagon extends Polygon {
    Octagon(){
        super();
        
        //Eckpunkte des Polygons festlegen
        this.addPoint(50, 0);
        this.addPoint(100, 0);
        this.addPoint(150, 50);
        this.addPoint(150, 100);
        this.addPoint(100, 150);
        this.addPoint(50, 150);
        this.addPoint(0, 100);
        this.addPoint(0, 50);
    }
}
