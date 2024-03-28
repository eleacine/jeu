package Shooter.model;

import java.awt.Color;
import Shooter.Managers.ManagerCase;

public class EnemySniper extends Enemy {

    public EnemySniper(int x, int y) {
        super(x, y, 50, 100, 1, 2, 20, 50, 350, 350, new Color(255, 0, 255));
    }

    public boolean isPlayerInRange(Player player, int[][] map) {
        if (isPlayerDetected(player)) {
            return !isWallBetween(x, y, map, player.getX(), player.getY());
        }
        return false;
    }


    private boolean isWallBetween(int startX, int startY, int[][] map, int targetX, int targetY) {
        // Calculer les deltas et les incréments pour les axes X et Y
        int dx = Math.abs(targetX - startX);
        int dy = Math.abs(targetY - startY);
        int sx = startX < targetX ? 1 : -1;
        int sy = startY < targetY ? 1 : -1;
    
        // Variables de contrôle pour l'algorithme de trajectoire
        int err = dx - dy;
        int err2;
    
        // Position initiale
        int currentX = startX;
        int currentY = startY;
    
        // Boucle à travers la trajectoire du projectile
        while (currentX != targetX || currentY != targetY) {
            // Vérifier si la case actuelle est un mur
            if (isWall(currentX, currentY, map)) {
                return true;
            }
    
            // Calculer la prochaine position sur la trajectoire
            err2 = 2 * err;
            if (err2 > -dy) {
                err -= dy;
                currentX += sx;
            }
            if (err2 < dx) {
                err += dx;
                currentY += sy;
            }
        }
    
        // Si aucun mur n'est rencontré le long de la trajectoire, retourner false
        return false;
    }
    
    
    private boolean isWall(int xPos, int yPos, int[][] map) {
        // Vérifie si une case est un mur
        int x = convertPositionToTile(xPos);
        int y = convertPositionToTile(yPos);
        return (map[y][x] == ManagerCase.MUR || map[y][x] == ManagerCase.MUR_CASSANT);
    }
    

}
