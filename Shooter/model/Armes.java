package Shooter.model;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Armes {
    protected int power;
    protected boolean type; // true pour arme portée à la main et false pour arme de sol
    protected int munition; // nombre de munitons que comporte l'arme
    protected int distance; // la distance a laquelle l'arme tire
    protected int recharge; // recharge de l'arme
    protected int dureeRecharge; // durée de la recharge

    public Color color;
    public String nom;

    public Armes(String nom,int power, boolean type, int munition, Color color) {
        this.power = power;
        this.type = type;
        this.munition = munition;
        this.color = color;
        this.nom=nom;
    }


    public Armes(String nom,int power, boolean type, int munition, Color color,int dureeRecharge,int distance) {
        this.nom=nom;
        this.power = power;
        this.type = type;
        this.munition = munition;
        this.distance = distance;
        this.recharge = munition;
        this.dureeRecharge = dureeRecharge;
        this.color = color;
    }

    public void shoot() {
        if (this.munition > 0) {
            this.munition -= 1;

        }
    }

    public void besoinRecharge() {
        if (this.munition == 0) {
            // Définir la durée du timer en millisecondes
            long dureeTimer = this.dureeRecharge; // Par exemple, 1 minute

            // Créer un objet Timer
            Timer timer = new Timer();

            final Armes arme = this;

            // Créer une tâche à exécuter lorsque le timer se déclenche
            TimerTask tache = new TimerTask() {

                @Override
                public void run() {
                    // Code à exécuter à la fin du timer
                    arme.munition = arme.recharge;

                    // Arrêter le timer après l'exécution de la tâche
                    timer.cancel();
                }
            };

            // Planifier la tâche pour s'exécuter après la durée spécifiée
            timer.schedule(tache, dureeTimer);

            // Afficher un message avant le début du timer
            // System.out.println("Le timer est lancé pour " + (this.dureeRecharge / 1000) +
            // " secondes.");

        }
    }

    // ---------- Getters & Setters ----------

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getMunition() {
        return munition;
    }

    public void setMunition(int munition) {
        this.munition = munition;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getRecharge() {
        return recharge;
    }

    public void setRecharge(int recharge) {
        this.recharge = recharge;
    }

    public int getDureeRecharge() {
        return dureeRecharge;
    }

    public void setDureeRecharge(int dureeRecharge) {
        this.dureeRecharge = dureeRecharge;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
