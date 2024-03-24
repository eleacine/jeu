package Shooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import Shooter.Managers.GameManager;

import java.util.List;

public class A4 extends Armes {
    private int x;
    private int y;
    private int dimension = 40;
    private boolean isGrenadeActivated = false;
    private Timer explosionTimer;
    private long explosionDelay = 2000;  // Délai d'explosion
    public GameManager gameManager;
    private boolean isPlaced = false;
    private BufferedImage[] grenadeImage;

    public A4() {
        super("grenade", 50, false, 5, Color.GRAY, 2000, 400, true);
        loadGrenadeImage();
    }

    public A4(int x, int y) {
        super("grenade", 50, false, 5, Color.GRAY, 2000, 400, true);
        this.x = x;
        this.y = y;
        loadGrenadeImage();
        setupExplosionTimer();  
    }

    private void loadGrenadeImage() {
        // Charger l'image de la grenade ici
        // Exemple : grenadeImage = ImageIO.read(new File("grenade.png"));
        BufferedImage atlas=Enregistrement.getSpriteAtlas();
        grenadeImage=new BufferedImage[1];
        
        grenadeImage[0]= atlas.getSubimage(8*40, 1*40, 40, 40);
        
    }
    
    public void draw(Graphics g, List<Personnage> enemies, int playerX, int playerY) {
      //  System.out.println("draw grenade");
      //  double distanceToPlayer = Math.sqrt(Math.pow(playerX - x, 2) + Math.pow(playerY - y, 2));
    
        /*if (isGrenadeActivated) {
        //    if (distanceToPlayer <= getDistance()) {
                g.setColor(this.color);
                g.fillOval(x, y, dimension, dimension);
                drawExplosion(x, y, g, enemies);
            } else {
                // Ajuster la position de la grenade en fonction de la distance par rapport au joueur
               // double angleToPlayer = Math.atan2(playerY - y, playerX - x);
             //   x = playerX - (int) (Math.cos(angleToPlayer) * getDistance());
               // y = playerY - (int) (Math.sin(angleToPlayer) * getDistance());
            }*/
            if (isGrenadeActivated) {
                // Dessinez l'image de la grenade si elle est activée
                if (grenadeImage != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.drawImage(grenadeImage[0], x, y, dimension, dimension, null);
                    g2d.dispose();
                } else {
                    // Dessinez un ovale comme avant si l'image de la grenade n'est pas disponible
                    g.setColor(this.color);
                    g.fillOval(x, y, dimension, dimension);
                }
                drawExplosion(x, y, g, enemies);
            } 
        }
   // }
    
    

    private void setupExplosionTimer() {
        System.out.println("setupExplosionTimer");
        explosionTimer = new Timer((int) explosionDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isGrenadeActivated = false;
                explosionTimer.stop();
               // deleteGrenade();
            }
        });
    }

    public void activateGrenade() {
        System.out.println("activateGrenade");
        if (!isGrenadeActivated && getMunition() > 0) {
            isGrenadeActivated = true;
         //   setupExplosionTimer();
            explosionTimer.start();
            shoot();
        }
    }

    private void drawExplosion(int x, int y, Graphics g, List<Personnage> enemies) {
      //  System.out.println("drawExplosion");
        Color grayTransparent = new Color(128, 128, 128, 128);
        g.setColor(grayTransparent);
        g.fillOval(x - 40, y - 40, 150, 150);

        for (Personnage personnage : enemies) {
            System.out.println("verif de distance");
            double distance = Math.sqrt(Math.pow(personnage.x - x, 2) + Math.pow(personnage.y - y, 2));
            if (distance <= 75) {
                personnage.infligerDegats(10);
            }
        }
    }
/* 
    public void deleteGrenade() {
        System.out.println("deleteGrenade");
        isGrenadeActivated = false;
        this.dimension = 0;
    }
*/
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

    public Timer getExplosionTimer() {
        return explosionTimer;
    }

    public void setExplosionTimer(Timer explosionTimer) {
        this.explosionTimer = explosionTimer;
    }

    public boolean getIsPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean isPlaced) {
        this.isPlaced = isPlaced;
    }
}
