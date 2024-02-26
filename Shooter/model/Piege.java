package Shooter.model;

import java.awt.Color;

public class Piege extends Armes{

    protected int x;
    protected int y;
    protected int dimension;

    public Piege(String nom, int power, boolean type, int munition, Color color) {
        super(nom, power, type, munition, color);
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
