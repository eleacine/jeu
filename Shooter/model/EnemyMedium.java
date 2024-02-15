package Shooter.model;

import java.awt.Color;

public class EnemyMedium extends Enemy {

    public EnemyMedium() {
        this.speed = 1;
        this.x = 550;
        this.y = 100;
        this.sante = 100;
        this.size = 75;
        this.frequency = 1000;
        this.detectionRadius = 300;

        this.color = new Color(0, 255, 0);
    }

  
    @Override
    public void updateBehavior(Player player) {
        // Implémentez le comportement spécifique pour cet ennemi
        // Par exemple, suivi simple du joueur si détecté
        if (isPlayerDetected(player)) {
            this.speed = 2;
            float angle = calculateAngle(x, y, player.x, player.y);
            calculateDifferences(angle);
            x += differenceX;
            y += differenceY;
        }
    }

    

}
