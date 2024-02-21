package Shooter.model;

import java.awt.Color;

public abstract class Armes {
    public int power;
    public  boolean type; // true pour arme portée à la main et false pour arme de sol
    public int munition;
    public Color color;

    public Armes(int power, boolean type, int munition, Color color) {
        this.power = power;
        this.type = type;
        this.munition = munition;
        this.color = color;
    }

    public void shoot (){
        if (this.munition > 0){
            this.munition -= 1;
            // tir();
        }
    }

    // public abstract void tir();
}
