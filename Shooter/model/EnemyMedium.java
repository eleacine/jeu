package Shooter.model;

import java.awt.Color;

import Shooter.Managers.ProjectilesManager;

// import Shooter.Managers.GameManager;

public class EnemyMedium extends Enemy {

    /*
     * peut suivre le joueur
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

    public EnemyMedium(int x, int y) {
        // x:1250
        // y:200
        super(x, y, 50, 100, 2, 2, 20, 50, 1000, 100, new Color(255, 255, 0));
    }

    @Override
    public void updateBehavior(Player player, int[][] map) {
        // Implémentez le comportement spécifique pour cet ennemi
        // Par exemple, suivi simple du joueur si détecté
        if (isPlayerDetected(player)) {
            moveTowardsPlayer(map, player);
        }
    }

    @Override
    public void shootBehavior(Player player, ProjectilesManager projectilesManager) {
        // Implémentez la logique de tir spécifique pour cet ennemi
        // Par exemple, tirer une balle vers le joueur
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime > getFrequency()) {
            Bullet bullet = new Bullet(x, y, player.x, player.y, power);
            projectilesManager.getEnemyBullets().add(bullet);
            lastShotTime = currentTime;
        }
    }

}
