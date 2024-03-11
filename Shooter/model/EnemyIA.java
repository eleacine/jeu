package Shooter.model;

import java.awt.Color;

// import Shooter.Managers.GameManager;

public class EnemyIA extends Enemy {  

    /*  peut suivre le joueur 
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

    public EnemyIA(int x, int y) {
        //x:1250
        //y:200
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
            // Si la case suivante est un mur, l'ennemi doit choisir une direction alternative
            int[] alternativeDirection = chooseAlternativeDirection(deltaX, deltaY, map);
            newX = this.x + alternativeDirection[1];
            newY = this.y + alternativeDirection[0];

            if (isValidMove(newX, newY, map)) {
                this.x = newX;
                this.y = newY;
            }
        }
    }

    private int[] chooseAlternativeDirection(int deltaX, int deltaY, int[][] map) {
        // Choisir une direction alternative pour contourner le mur
        int newX = deltaX / 40;
        int newY = deltaY / 40;
        if (deltaX != 0) {
            // Si le déplacement horizontal, essayer de se déplacer verticalement
            return new int[]{0, (deltaY < 0 || map[newY + 1][newX +1] != 7) ? 1 : -1};
        } else {
            // Si le déplacement vertical, essayer de se déplacer horizontalement
            return new int[]{(deltaX < 0 || map[newY + 1][newX +1] != 7 ) ? 1 : -1, 0};
        }
    }

    private boolean isValidMove(int x, int y, int[][] map) {
        int xMap = x / 40;
        int yMap = y / 40;
        // System.out.println(map[yMap][xMap]);
        return x >= 0 && x < 1500 && y >= 0 && y < 800 && map[yMap + 1][xMap + 1] != 7;
    }

}
