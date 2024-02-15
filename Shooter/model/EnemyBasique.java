package Shooter.model;

import java.awt.Color;

public class EnemyBasique extends Enemy{

    public EnemyBasique() {
        this.speed = 1;
        this.x = 200;
        this.y = 100;
        this.sante = 100;
        this.size = 40;
        this.frequency = 2000;
        this.detectionRadius = 750;

        this.color = new Color(255, 0, 0);
    }

    

  

}
