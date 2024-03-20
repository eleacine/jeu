package Shooter.model;

import java.awt.Color;
import Shooter.Managers.ManagerCase;

public class EnemySniper extends Enemy {

    public EnemySniper(int x, int y) {
        super(x, y, 50, 100, 1, 2, 20, 50, 350, 200, new Color(255, 0, 255));
    }

    // @Override
    // public void updateBehavior(Player player, int[][] map) {
    //     if (isPlayerInRange(player) && !hasAWallinSight(player.getX(), player.getY(), map)){
    //         // Positionner l'ennemi et tirer dès que le joueur est à portée
    //         // Positionnement de l'ennemi (peut être implémenté selon les besoins spécifiques)
    //     } else {
    //         // Se déplacer vers le joueur pour tirer s'il n'est pas à portée
    //         moveEnemyTowardsPlayer(player.getX(), player.getY(), map);
    //     }
    // }

    public void moveEnemyTowardsPlayer(int playerX, int playerY, int[][] map) {
        int deltaX = Integer.signum(playerX - this.x); // Signum pour obtenir -1, 0 ou 1 selon la direction du mouvement
        int deltaY = Integer.signum(playerY - this.y); // Signum pour obtenir -1, 0 ou 1 selon la direction du mouvement

        int newX = this.x + deltaX * 40; // Mouvement selon la direction en tenant compte de la taille de la case
        int newY = this.y + deltaY * 40; // Mouvement selon la direction en tenant compte de la taille de la case

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

    public boolean hasAWallinSight (int xTarget, int yTarget, int[][] map) {
        int x = this.x;
        int y = this.y;

        while (x != xTarget && y != yTarget) {
            if (isWall(x, y, map)) {
                return true;
            }
            x += (xTarget - x) / Math.abs(xTarget - x);
            y += (yTarget - y) / Math.abs(yTarget - y);
        }
        return false;
    }

    private boolean isWall(int x, int y, int[][] map) {
        int xMap = x / 40;
        int yMap = y / 40;
        if (ManagerCase.getCaseType(map[yMap][xMap]) == ManagerCase.MUR) {
            return true;
        }
        return false;
    }

    public boolean isPlayerInRange(Player player) {
        return Math.sqrt(Math.pow(player.getX() - this.x, 2) + Math.pow(player.getY() - this.y, 2)) < 300;
    }


}
