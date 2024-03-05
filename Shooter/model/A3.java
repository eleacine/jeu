package Shooter.model;

import java.awt.Color;
import java.awt.Graphics;

import Shooter.Managers.GameManager;

// Mine => explose si un personnage passe dessus
public class A3 extends Armes {

    protected int x;
    protected int y;
    public GameManager gameManager;
    protected int dimension = 40;
    
    public A3() {
        super("Mine",50, false, 5, Color.RED,2000,100,true);
    }

    public A3(int x, int y) {
        super("Mine",50, false, 5, Color.RED,2000,100,true);
 
        this.x = x;
        this.y = y;
    }




    public void draw(Graphics g, int playerX, int playerY) {
        double distanceToPlayer = Math.sqrt(Math.pow(playerX - x, 2) + Math.pow(playerY - y, 2));
        
        if (distanceToPlayer <= getDistance()) {
            g.setColor(this.color);
            g.fillOval(x, y, dimension, dimension);
        } else {
            deleteMine();
        }
    }

    public void deleteMine() {
        this.x = -100;
        this.y = -100;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
}
