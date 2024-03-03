package Shooter.model;

import java.awt.Color;

// import Shooter.Managers.GameManager;

public class EnemyMedium extends Enemy {  

    /*  peut suivre le joueur 
     * position x : 550
     * position y : 100
     * taille : 50
     * sante : 100
     * speed : 2
     * dégats de tir : 20
     * dégat de collision : 50
     * fréq de tir : 1 seconde
     * radius de détection : 200 px
     */

    public EnemyMedium() {
        super(1250, 200, 50, 100, 2, 2, 20, 50, 1000, 200, new Color(255, 255, 0));
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
