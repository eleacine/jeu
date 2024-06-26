package Shooter.model;

import java.awt.Color;

import Shooter.Managers.ProjectilesManager;

public class EnemyIA extends Enemy {

    public EnemyIA(int x, int y) {
        super(x, y, 50, 100, 1, 2, 20, 50, 350, 200, new Color(255, 0, 255));
    }

    @Override
    public void updateBehavior(Player player, int[][] map, ProjectilesManager projectilesManager) {
        moveTowardsPlayer(map, player);
    }

}
