package Shooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Armes {
    public int power;
    public  boolean type; // true pour arme portée à la main et false pour arme de sol
    public int munition;//nombre de munitons que comporte l'arme
    public int distance;// la distance a laquelle l'arme tire
    public int recharge;
    public Color color;
    public int dureeRecharge;

    public Armes(int power, boolean type, int munition, Color color) {
        this.power = power;
        this.type = type;
        this.munition = munition;
        //this.distance=distance;
        //this.recharge=munition;
        this.color = color;
    }


    public Armes(int power, boolean type, int munition, Color color,int dureeRecharge,int distance) {
        this.power = power;
        this.type = type;
        this.munition = munition;
        //this.distance=distance;
        this.recharge=munition;
        this.dureeRecharge=dureeRecharge;
        this.color = color;
        this.distance=distance;
    }

    public void shoot (){
        if (this.munition > 0){
            this.munition -= 1;
          
        }
    }

    public void besoinRecharge(){
        if(this.munition==0){
        // Définir la durée du timer en millisecondes
        long dureeTimer = this.dureeRecharge;  // Par exemple, 1 minute

        // Créer un objet Timer
        Timer timer = new Timer();

        final Armes arme = this;

        // Créer une tâche à exécuter lorsque le timer se déclenche
        TimerTask tache = new TimerTask() {

            @Override
            public void run() {
                // Code à exécuter à la fin du timer
                arme.munition=arme.recharge;
                
                // Arrêter le timer après l'exécution de la tâche
                timer.cancel();
            }
        };

        // Planifier la tâche pour s'exécuter après la durée spécifiée
        timer.schedule(tache, dureeTimer);

        // Afficher un message avant le début du timer
        //System.out.println("Le timer est lancé pour " + (this.dureeRecharge / 1000) + " secondes.");
            
        }
    }


    

}
