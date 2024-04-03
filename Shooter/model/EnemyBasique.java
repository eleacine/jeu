package Shooter.model;

import java.awt.Color;

import Shooter.Managers.ProjectilesManager;

public class EnemyBasique extends Enemy {

    /*
     * est statique
     * position x : 50
     * position y : 100
     * taille : 50
     * sante : 100
     * speed : 1
     * dégats de tir : 10
     * dégat de collision : 50
     * fréq de tir : 1 seconde
     * radius de détection : 500 px
     */

    public EnemyBasique(int x, int y) {
        super(x, y, 50, 100, 1, 2, 10, 50, 1000, 250, new Color(255, 0, 0));
    }

    @Override
    public void updateBehavior(Player player, int[][] map, ProjectilesManager projectilesManager) {
        if (isPlayerDetected(player)) {
            if (isWallBetween(x, y, map, player.getX(), player.getY()) == false) {
                shootBehavior(player, projectilesManager);
            }
        }

    }

    @Override   
    public void shootBehavior(Player player, ProjectilesManager projectilesManager) {
        shoot(player, projectilesManager);
    }

}
