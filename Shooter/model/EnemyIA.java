package Shooter.model;

import java.awt.Color;

public class EnemyIA extends Enemy {

    public EnemyIA(int x, int y) {
        super(x, y, 50, 100, 2, 2, 20, 50, 350, 200, new Color(255, 0, 255));
    }

    @Override
    public void updateBehavior(Player player, int[][] map) {
        moveTowardsPlayer(map, player);
    }

}
