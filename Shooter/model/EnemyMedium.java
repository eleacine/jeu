package Shooter.model;

import java.awt.Color;

public class EnemyMedium extends Enemy {

    public EnemyMedium() {
        super(550, 100, 50, 100, 2, 2, 20, 50, 1000, 200, new Color(255, 255, 0));
    }

  
    @Override
    public void updateBehavior(Player player) {
        // Implémentez le comportement spécifique pour cet ennemi
        // Par exemple, suivi simple du joueur si détecté
        if (isPlayerDetected(player)) {
            this.maxSpeed = 2;
            float angle = calculateAngle(x, y, player.x, player.y);
            calculateDifferences(angle);
            x += differenceX;
            y += differenceY;
        }
    }

    

}
