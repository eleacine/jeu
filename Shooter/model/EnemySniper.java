package Shooter.model;

import java.awt.Color;
import Shooter.Managers.ManagerCase;

public class EnemySniper extends Enemy {

    public EnemySniper(int x, int y) {
        super(x, y, 50, 100, 1, 2, 20, 50, 350, 200, new Color(255, 0, 255));
    }

    public boolean isPlayerInRange(Player player, int[][] map) {
        if (isPlayerDetected(player)) {
            return !isWallBetween(x, y, map, player.getX(), player.getY());
        }
        return false;
    }

    private int convertPositionToTile(int position) {
        // Convertit une position en coordonnées de tableau
        return position / 40;
    }

    
    private boolean isWallBetween(int startX, int startY, int[][] map, int targetX, int targetY) {
        // Vérifie s'il y a un mur sur la ligne entre deux points
        while (startX != targetX || startY != targetY) {
            // Vérifie si la case actuelle est un mur
            if (isWall(startX, startY, map)) {
                return true;
            }
    
            // Déplacements en diagonale
            if (startX != targetX && startY != targetY) {
                int dx = startX < targetX ? 1 : -1;
                int dy = startY < targetY ? 1 : -1;
                startX += dx;
                startY += dy;
            } else {
                startX += startX < targetX ? 1 : startX > targetX ? -1 : 0;
                startY += startY < targetY ? 1 : startY > targetY ? -1 : 0;
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
