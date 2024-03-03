package Shooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.List;

public class A4 extends Armes {

    protected int x;
    protected int y;
    protected int dimension = 40;
    private boolean isGrenadeActivated = false;
    private Timer appearanceTimer;
    private Timer explosionTimer;
    private Timer disappearanceTimer;

    public A4() {
        super("grenade", 50, false, 5, Color.GRAY);
    }

    public A4(int x, int y) {
        super("grenade", 50, false, 5, Color.GRAY);
        this.x = x;
        this.y = y;
        setupAppearanceTimer();
    }

    private void setupAppearanceTimer() {
        appearanceTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isGrenadeActivated = true;
                appearanceTimer.stop();
                setupExplosionTimer();
                explosionTimer.start();
            }
        });
    }

    private void setupExplosionTimer() {
        explosionTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isGrenadeActivated = false;
                explosionTimer.stop();
                setupDisappearanceTimer();
                disappearanceTimer.start();
            }
        });
    }

    private void setupDisappearanceTimer() {
        disappearanceTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disappearanceTimer.stop();
                
                deleteGrenade();
            }
        });
    }

    public void activateGrenade() {
        if (!isGrenadeActivated) {
            appearanceTimer.start();
        }
    }

    public void draw(int x, int y, Graphics g, List<Personnage> enemies) {
        g.setColor(this.color);
        g.fillOval(x, y, dimension, dimension);

        if (isGrenadeActivated) {
            drawExplosion(x, y, g, enemies);
        }
    }

    private void drawExplosion(int x, int y, Graphics g,  List<Personnage> enemies) {
        Color grayTransparent = new Color(128, 128, 128, 128);
        g.setColor(grayTransparent);
        g.fillOval(x - 50, y - 50, 150, 150);

       
        for (Personnage personnage : enemies) {
            double distance = Math.sqrt(Math.pow(personnage.x - x, 2) + Math.pow(personnage.y - y, 2));
            if (distance <= 75) {
                personnage.infligerDegats(10);
            }
        }
    }

    private void deleteGrenade() {
        //a changer psq elle disparaissent pas encore
        x = -1;
        y = -1;
    }
}
