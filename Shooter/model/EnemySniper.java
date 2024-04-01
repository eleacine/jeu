package Shooter.model;

import java.awt.Color;
import Shooter.Managers.ManagerCase;
import Shooter.Managers.ProjectilesManager;

public class EnemySniper extends Enemy {

    public EnemySniper(int x, int y) {
        super(x, y, 50, 100, 1, 2, 20, 50, 400, 1000, new Color(255, 0, 255));
    }

    public boolean isPlayerInRange(Player player, int[][] map) {
        // if (isPlayerDetected(player)) {
        //     return !isWallBetween(x, y, map, player.getX(), player.getY());
        // }
        // return false;
        // return isPlayerDetected(player) && isWallBetween(x, y, map, player.getX(), player.getY());
        if (isWallBetween(x, y, map, x, y)){
            if (isPlayerDetected(player)) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void shootBehavior(Player player, ProjectilesManager projectilesManager) {
        shoot(player, projectilesManager);
    }

    protected boolean isWallBetween(int x1, int y1, int[][] map, int x2, int y2) {
        // Vérifie si un mur est entre deux points
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;
        float x = x1;
        float y = y1;
        for (int i = 0; i < steps; i++) {
            x += xIncrement;
            y += yIncrement;
            if (isWall((int) x, (int) y, map)) {
                return true;
            }
        }
        return false;
    }
    

    private boolean isWall(int xPos, int yPos, int[][] map) {
        // Vérifie si une case est un mur
        int x = convertPositionToTile(xPos);
        int y = convertPositionToTile(yPos);
        return map[y][x] == ManagerCase.MUR || map[y][x] == ManagerCase.MUR_CASSANT;
    }

}
