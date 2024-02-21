package Shooter.model;

import java.awt.Color;
import java.awt.Graphics;

// Mine => explose si un personnage passe dessus
public class A3 extends Armes {

    public int x;
    public int y;
    public int dimension = 40;
    
    public A3() {
        super(50, false, 5, Color.ORANGE);
    }

    public A3(int x, int y) {
        super(50, false, 5, Color.ORANGE);
        this.x = x;
        this.y = y;
    }

    @Override
    public void pose() {
        System.out.println("");
        

    }

    public void draw (int x, int y, Graphics g){
        g.setColor(this.color);
        g.fillOval(x, y, dimension, dimension);
    }
    
}
