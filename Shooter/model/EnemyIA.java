// package Shooter.model;

// import java.awt.Color;
// import Shooter.Managers.ManagerCase;

// public class EnemyIA extends Enemy {

//     public EnemyIA(int x, int y) {
//         super(x, y, 50, 100, 1, 2, 20, 50, 350, 200, new Color(255, 0, 255));
//     }

//     @Override
//     public void updateBehavior(Player player, int[][] map) {
//         // moveEnemyTowardsPlayer(player.x, player.y);
//     }

//     public void moveEnemyTowardsPlayer(int playerX, int playerY, int[][] map) {
//         int deltaX = (this.x < playerX) ? 1 : ((this.x > playerX) ? -1 : 0);
//         int deltaY = (this.y < playerY) ? 1 : ((this.y > playerY) ? -1 : 0);

//         int newX = this.x + deltaX;
//         int newY = this.y + deltaY;

//         if (isValidMove(newX, newY, map)) {
//             this.x = newX;
//             this.y = newY;
//         } else {
//             int[] alternativeDirection = chooseAlternativeDirection(deltaX, deltaY, map);
//             newX = this.x + alternativeDirection[0];
//             newY = this.y + alternativeDirection[1];

//             if (isValidMove(newX, newY, map)) {
//                 this.x = newX;
//                 this.y = newY;
//             }
//         }
//     }

//     private int[] chooseAlternativeDirection(int deltaX, int deltaY, int[][] map) {
//         int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Droite, Bas, Gauche, Haut
        
//         for (int[] dir : directions) {
//             int newX = this.x / 40 + dir[0];
//             int newY = this.y / 40 + dir[1];
            
//             if (isValidMove(newX * 40, newY * 40, map)) {
//                 return dir; // Retourner la direction valide trouvée
//             }
//         }
        
//         return new int[]{0, 0}; // Aucune direction valide trouvée, rester immobile
//     }

//     private boolean isValidMove(int x, int y, int[][] map) {
//         int xMap = x / 40;
//         int yMap = y / 40;
        
//         return x >= 0 && x < 1500 && y >= 0 && y < 800 && ManagerCase.getCaseType(map[yMap][xMap]) != ManagerCase.MUR;
//     }
// }

package Shooter.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import Shooter.Managers.ManagerCase;

public class EnemyIA extends Enemy {

    public EnemyIA(int x, int y) {
        super(x, y, 50, 100, 1, 2, 20, 50, 350, 200, new Color(255, 0, 255));
    }

    @Override
    public void updateBehavior(Player player, int[][] map) {
        // int playerX = player.getX()/40;
        // int playerY = player.getY()/40;
                
        // moveTowardsPlayer(distances, map);
    }


    public void moveTowardsPlayer(int[][] distances, Player player) {

        int nextX = x / 40; // Convertir la position X de l'ennemi en coordonnées de tableau
        int nextY = y / 40; // Convertir la position Y de l'ennemi en coordonnées de tableau
    
        // Déterminer la direction vers la case avec la distance la plus courte
        int minDistance = distances[nextY][nextX];
        int dirX = 0;
        int dirY = 0;
    
        // Liste des directions
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    
        // Parcourir toutes les directions pour trouver la case avec la distance la plus courte
        for (int[] dir : directions) {
            int newX = nextX + dir[0];
            int newY = nextY + dir[1];
            
            // System.out.println("dirX: " + dirX + " dirY: " + dirY);
            System.out.println(" minDistance: " + minDistance + " distances[newY][newX]: " + distances[newY][newX]);

            // Vérifier si la case est valide et si la distance est plus courte
            if (newX >= 0 && newX < distances[0].length && newY >= 0 && newY < distances.length
                    && distances[newY][newX] < minDistance) {
                        System.out.println(dir[0] + " " + dir[1] + " " + distances[newY][newX]);
                minDistance = distances[newY][newX];
                dirX = dir[0];
                dirY = dir[1];
            }   
        }

        System.out.println("EnemyIA: " + x + " " + y + " " + dirX + " " + dirY);
        System.out.println("EnemyIA: " + x/40 + " " + y/40);

    
        // Déplacer l'ennemi dans la direction choisie
        x += dirX; // Mettre à jour la position X de l'ennemi
        y += dirY; // Mettre à jour la position Y de l'ennemi

        System.out.println("EnemyIA 2: " + x + " " + y);
        System.out.println("");
    }
    
}

