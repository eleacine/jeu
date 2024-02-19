import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Gardien {
    private int x;
    private int y;
    private int visionAngle;

    public Gardien(int x, int y) {
        this.x = x;
        this.y = y;
        this.visionAngle = 340; 
    }

    public void deplacer() {
        x += 2; 
        if (x > 400) {
            x = -30;
        }
    }

    public void suivreJoueur(Joueur joueur) {
        int deltaX = joueur.getX() - x;
        int deltaY = joueur.getY() - y;

        double angleToPlayer = Math.atan2(deltaY, deltaX);
        x += Math.cos(angleToPlayer) * 2; 
        y += Math.sin(angleToPlayer) * 2;
    }

    public void deplacerVision(Joueur joueur) {
        int deltaX = joueur.getX() - x;
        int deltaY = joueur.getY() - y;
        visionAngle = (int) Math.toDegrees(Math.atan2(deltaY, deltaX));

        if (visionAngle < 0) {
            visionAngle += 360;
        }
    }

    public void dessiner(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 30, 30);
    }

    public void dessinerVision(Graphics g) {
        g.setColor(new Color(255, 255, 255, 100));
        g.fillArc(x - 50, y - 50, 130, 130, visionAngle, 30);
    }
}
