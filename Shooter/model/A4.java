package Shooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class A4 extends Armes {

    public int x;
    public int y;
    public int dimension = 40;
    private boolean isGrenadeActivated = false;
    private Timer explosionTimer;

    public A4() {
        super(50, false, 5, Color.GRAY);
    }

    public A4(int x, int y) {
        super(50, false, 5, Color.GRAY);
        this.x = x;
        this.y = y;
        setupExplosionTimer();
    }

    private void setupExplosionTimer() {
        explosionTimer = new Timer(1300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isGrenadeActivated = true;
                explosionTimer.stop();
            }
        });
    }

    public void activateGrenade() {
        if (!isGrenadeActivated) {
            explosionTimer.start();
        }
    }

    public void draw(int x, int y, Graphics g) {
        g.setColor(this.color);
        g.fillOval(x, y, dimension, dimension);

        if (isGrenadeActivated) {
            drawExplosion(x, y, g);
        }
    }
   
        private void drawExplosion(int x, int y, Graphics g) {
            
            Color grisTransparent = new Color(128, 128, 128, 128);
            g.setColor(grisTransparent);
            g.fillOval(x - 50, y - 50, 150, 150);
        }
    }
    
