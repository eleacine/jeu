package Shooter.model;

import java.awt.Color;
import Shooter.Managers.ManagerCase;

public class EnemyIA extends Enemy {

    public EnemyIA(int x, int y) {
        super(x, y, 50, 100, 1, 2, 20, 50, 350, 200, new Color(255, 0, 255));
    }

    @Override
    public void updateBehavior(Player player) {
        // moveEnemyTowardsPlayer(player.x, player.y);
    }

    public void moveEnemyTowardsPlayer(int playerX, int playerY, int[][] map) {
        int deltaX = (this.x < playerX) ? 1 : ((this.x > playerX) ? -1 : 0);
        int deltaY = (this.y < playerY) ? 1 : ((this.y > playerY) ? -1 : 0);

        int newX = this.x + deltaX;
        int newY = this.y + deltaY;

        if (isValidMove(newX, newY, map)) {
            this.x = newX;
            this.y = newY;
        } else {
            int[] alternativeDirection = chooseAlternativeDirection(deltaX, deltaY, map);
            newX = this.x + alternativeDirection[0];
            newY = this.y + alternativeDirection[1];

            if (isValidMove(newX, newY, map)) {
                this.x = newX;
                this.y = newY;
            }
        }
    }

    private int[] chooseAlternativeDirection(int deltaX, int deltaY, int[][] map) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Droite, Bas, Gauche, Haut
        
        for (int[] dir : directions) {
            int newX = this.x / 40 + dir[0];
            int newY = this.y / 40 + dir[1];
            
            if (isValidMove(newX * 40, newY * 40, map)) {
                return dir; // Retourner la direction valide trouvée
            }
        }
        
        return new int[]{0, 0}; // Aucune direction valide trouvée, rester immobile
    }

    private boolean isValidMove(int x, int y, int[][] map) {
        int xMap = x / 40;
        int yMap = y / 40;
        return x >= 0 && x < 1500 && y >= 0 && y < 800 && ManagerCase.getCaseType(map[yMap][xMap]) != ManagerCase.MUR;
    }
}

