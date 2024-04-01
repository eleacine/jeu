package Shooter.model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Shooter.Managers.ManagerCase;
import Shooter.Managers.ProjectilesManager;

public class EnemySniper extends Enemy {

    public EnemySniper(int x, int y) {
        super(x, y, 50, 100, 1, 2, 20, 50, 400, 250, new Color(255, 0, 255));
    }

    public boolean isPlayerInRange(Player player, int[][] map) {
        if (isWallBetween(x, y, map, x, y)) {
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

    public boolean isWallBetween(int x1, int y1, int[][] map, int x2, int y2) {
        // Calculer les coordonnées des cases de la ligne de mire entre (x1, y1) et (x2,
        // y2)
        List<Point> lineOfSight = calculateLineOfSight(x1, y1, x2, y2);

        // Vérifier chaque case de la ligne de mire pour la présence d'un mur
        for (Point p : lineOfSight) {

            if (isWall(p.x, p.y, map)) {
                return false; // Un mur a été trouvé sur la ligne de mire
            }
        }

        return true; // Aucun mur trouvé sur la ligne de mire
    }

    private List<Point> calculateLineOfSight(int x1, int y1, int x2, int y2) {
        List<Point> lineOfSight = new ArrayList<>();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        int currentX = x1;
        int currentY = y1;

        while (true) {
            lineOfSight.add(new Point(currentX, currentY));

            if (currentX == x2 && currentY == y2) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                currentX += sx;
            }
            if (e2 < dx) {
                err += dx;
                currentY += sy;
            }
        }

        return lineOfSight;
    }

    private boolean isWall(int xPos, int yPos, int[][] map) {
        // Vérifie si une case est un mur
        int x = convertPositionToTile(xPos);
        int y = convertPositionToTile(yPos);
        return map[y][x] == ManagerCase.MUR || map[y][x] == ManagerCase.MUR_CASSANT;
    }

}
