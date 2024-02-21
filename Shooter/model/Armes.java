package Shooter.model;

public abstract class Armes {
    public int power;
    public  boolean type; // true pour arme portée à la main et false pour arme de sol
    public int munition;

    public Armes(int power, boolean type, int munition) {
        this.power = power;
        this.type = type;
        this.munition = munition;
    }

    public void shoot (){
        if (this.munition > 0){
            this.munition -= 1;
            // tir();
        }
    }

    // public abstract void tir();
}
