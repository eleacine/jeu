package Shooter.model;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.List;

public class A4 extends Armes {
    private int x;
    private int y;
    private int dimension = 40;
    private boolean isGrenadeActivated = false;
    private Timer appearanceTimer;
    private Timer explosionTimer;
    private Timer disappearanceTimer;

    public A4() {
        super("grenade", 50, false, 5, Color.GRAY,2000,400,true);
    }

    public A4(int x, int y) {
        super("grenade", 50, false, 5, Color.GRAY,2000,400,true);
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

    public void draw(Graphics g, List<Personnage> enemies, int playerX, int playerY) {
        
        double distanceToPlayer = Math.sqrt(Math.pow(playerX - x, 2) + Math.pow(playerY - y, 2));
    
        if (!isGrenadeActivated || (isGrenadeActivated && distanceToPlayer <= getDistance())) {
            g.setColor(this.color);
            g.fillOval(x, y, dimension, dimension);
    
            if (isGrenadeActivated) {
                drawExplosion(x, y, g, enemies);
            }
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
        isGrenadeActivated = false;
        this.dimension = 0;
    }

    //-----------------GETTER & SETTER---------------------------
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public boolean isGrenadeActivated() {
        return isGrenadeActivated;
    }

    public void setGrenadeActivated(boolean grenadeActivated) {
        isGrenadeActivated = grenadeActivated;
    }

    public Timer getAppearanceTimer() {
        return appearanceTimer;
    }

    public void setAppearanceTimer(Timer appearanceTimer) {
        this.appearanceTimer = appearanceTimer;
    }

    public Timer getExplosionTimer() {
        return explosionTimer;
    }

    public void setExplosionTimer(Timer explosionTimer) {
        this.explosionTimer = explosionTimer;
    }

    public Timer getDisappearanceTimer() {
        return disappearanceTimer;
    }

    public void setDisappearanceTimer(Timer disappearanceTimer) {
        this.disappearanceTimer = disappearanceTimer;
    }


}
