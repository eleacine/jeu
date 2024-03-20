package Shooter.model;

import java.awt.*;

public class Gardien extends Enemy {

    /*ennemi qui bouge et balaye un espace pour repérer le joueur 
     * position x : 900
     * position y : 250
     * taille : 55
     * sante : 200
     * speed : 2
     * dégats de tir : 25
     * dégat de collision : 100
     * fréq de tir : 0 seconde
     * radius de détection : 340 (angle)
     */

    private int visionAngle;
    private boolean playerDetected;
    private int startLocationX;
    private int direction; // 1 pour droite, -1 pour gauche

    public Gardien(int x, int y) {    
        super(x, y, 55, 200, 1, 0, 25, 100, 0, Color.MAGENTA);
        this.visionAngle = 340;
        this.playerDetected = false;
        this.startLocationX = x;
        this.direction = 1; // Commence à se déplacer vers la droite
    }

 
    public void deplacer() {
        x += direction * maxSpeed; // La vitesse est ajustée ici (1 pour un mouvement lent)
        
        if (x > startLocationX + 350) {
            direction = -1; // Change de direction lorsqu'il atteint la limite de droite
        } else if (x < startLocationX) {
            direction = 1; // Change de direction lorsqu'il atteint la limite de gauche
        }
    }

    public void suivreJoueur(Player player) {
        int deltaX = player.getX() - x;
        int deltaY = player.getY() - y;

        double angleToPlayer = Math.atan2(deltaY, deltaX);
        x += Math.cos(angleToPlayer) * 2;
        y += Math.sin(angleToPlayer) * 2;
    }

    public void deplacerVision(Player player) {
        int deltaX = player.getX() - x;
        int deltaY = player.getY() - y;
        visionAngle = (int) Math.toDegrees(Math.atan2(deltaY, deltaX));

        if (visionAngle < 0) {
            visionAngle += 360;
        }
    }

    public void dessinerVision(Graphics g) {
        g.setColor(new Color(255, 255, 255, 100));
        g.fillArc(x - 75, y - 75 , 200, 200, visionAngle, 65);
    }

    private boolean isPlayerInVisionAngle(double angleToPlayer) {
        double startAngle = visionAngle - 15;
        double endAngle = visionAngle + 15;
    
        if (startAngle < 0) {
            startAngle += 360;
        }
    
        if (angleToPlayer < 0) {
            angleToPlayer += 360;
        }
    
        // Vérifier si l'angle du joueur est dans la plage du faisceau balayant
        if (angleToPlayer >= startAngle && angleToPlayer <= endAngle) {
            return true;
        }
    
        return false;
    }

 
    @Override
    public void updateBehavior(Player player, int[][] map) {
        if (!isPlayerDetected(player) && playerDetected == false) {
            deplacer();
            deplacerVision(player);
    
            double angleToPlayer = calculateAngle(x, y, player.x, player.y);
    
            if (isPlayerInVisionAngle(angleToPlayer)) {
                playerDetected = true;
            }
        } else {
            suivreJoueur(player);
        }
    }
    
}
