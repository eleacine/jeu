package Shooter.model;

import java.awt.*;


public class Gardien extends Enemy {
   
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

    public void dessiner(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 30, 30);
    }

    public void dessinerVision(Graphics g) {
        g.setColor(new Color(255, 255, 255, 100));
        g.fillArc(x - 50, y - 50, 130, 130, visionAngle, 30);
    }
}

