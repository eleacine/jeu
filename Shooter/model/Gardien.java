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

    public Gardien() {
        super(900, 250, 55, 200, 2, 0, 25, 100, 0, Color.MAGENTA);
        this.visionAngle = 340;
    }

    public void deplacer() {
        x += 2;
        if (x > 1000) {
            x = -30;
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
        g.fillArc(x - 50, y - 50, 130, 130, visionAngle, 30);
    }
}
