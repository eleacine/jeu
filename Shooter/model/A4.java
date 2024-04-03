package Shooter.model;

// import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
// import java.nio.Buffer;

// import javax.swing.SwingUtilities;
import javax.swing.Timer;

// import Shooter.Managers.GameManager;

import java.util.ArrayList;
import java.util.List;

// public class A4 extends Armes {
//     private int x;
//     private int y;
//     private int dimension = 40;
//     private boolean isGrenadeActivated = false;
//     private Timer explosionTimer;
//     private long explosionDelay = 1000; 
//     public long activationTime;
//     private boolean isPlaced = false;
//     private BufferedImage[] grenadeImage;
//     private BufferedImage[] explosionImage;
//    private int explosionFrameIndex = 0;
//     private Timer explosionAnimationTimer;
//     private long explosionAnimationDelay = 500; 


//     public A4() {
//         super("grenade", 50, false, 5,3, 5000, 400, true);
//         loadGrenadeImage();
//     }

//     public A4(int x, int y) {
//         super("grenade", 50, false, 5,3, 5000, 400, true);
//         this.x = x;
//         this.y = y;
//         loadGrenadeImage();
//         setupExplosionTimer();  
//     }

//     private void loadGrenadeImage() {
//         BufferedImage atlas=Enregistrement.getSpriteAtlas();
//         grenadeImage=new BufferedImage[1];
//         grenadeImage[0]= atlas.getSubimage(6*40, 4*40, 40, 40);
        
//     }

//     private void loadExplosionImage() {
//             BufferedImage atlas=Enregistrement.getSpriteAtlas();
//             explosionImage=new BufferedImage[6];
//             for (int i =0 ; i <6;i++){
//                 explosionImage[i]= atlas.getSubimage((2+i)*40, 5*40, 40, 40);
//             }         
//     }
    
//     public void draw(Graphics g, List<Personnage> enemies, int playerX, int playerY) {
//         long currentTime = System.currentTimeMillis();
//         long elapsedTime = currentTime - activationTime;
    
       
//             if (grenadeImage != null) {
//                 Graphics2D g2d = (Graphics2D) g.create();
//                 g2d.drawImage(grenadeImage[0], x, y, dimension, dimension, null);
//                 g2d.dispose();
//             } if (isGrenadeActivated && elapsedTime >= explosionDelay) {
//                 drawExplosion(x, y, g, enemies);
//             }
//         }
    
//    // }
    
    

//     private void setupExplosionTimer() {
//        // System.out.println("setupExplosionTimer");
//         explosionTimer = new Timer((int) explosionDelay, new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 isGrenadeActivated = false;
//                 explosionTimer.stop();
//                // deleteGrenade();
//             }
//         });
//     }
//     public void activateGrenade() {
//         if (!isGrenadeActivated && getMunition() > 0) {
//             isGrenadeActivated = true;
//             activationTime = System.currentTimeMillis();
//             //activationTime = System.currentTimeMillis();
//             //startExplosionAnimation(g, ennemies); // Démarre l'animation de l'explosion
//            // shoot();
//         }
//     }
    
//     public void startExplosionAnimation(Graphics g, List<Personnage> enemies) {
//         explosionAnimationTimer = new Timer((int) explosionAnimationDelay, new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 drawExplosion(x, y, g, enemies);
//             }
//         });
//         explosionAnimationTimer.start();
//     }



//     private void drawExplosion(int x, int y, Graphics g, List<Personnage> enemies) {
//         loadExplosionImage();
//       //  if (explosionImage != null) {
//            // if (explosionFrameIndex < explosionImage.length) {
//                 Graphics2D g2d = (Graphics2D) g.create();
//                 g2d.drawImage(explosionImage[explosionFrameIndex], x , y , 2 * 40, 2 * 40, null);
//                 g2d.dispose();
//                 explosionFrameIndex++; 
//             } /*else {
//                 explosionFrameIndex = 0;
//                 explosionAnimationTimer.stop();
//                 deleteGrenade();
//                 isGrenadeActivated = false;
//             }
//         }
    
//         for (Personnage personnage : enemies) {
//             double distance = Math.sqrt(Math.pow(personnage.x - x, 2) + Math.pow(personnage.y - y, 2));
//             if (distance <= 75) {
//                 personnage.infligerDegats(10);
//             }
//         }
//         */
//     //}
    
    
    


       
    

//     public void deleteGrenade() {
//         //System.out.println("deleteGrenade");
//         isGrenadeActivated = false;
//         this.dimension = 0;
//     }

//     //-----------------GETTER & SETTER---------------------------
//     public int getX() {
//         return x;
//     }

//     public void setX(int x) {
//         this.x = x;
//     }

//     public int getY() {
//         return y;
//     }

//     public void setY(int y) {
//         this.y = y;
//     }

//     public int getDimension() {
//         return dimension;
//     }

//     public void setDimension(int dimension) {
//         this.dimension = dimension;
//     }

//     public boolean isGrenadeActivated() {
//         return isGrenadeActivated;
//     }

//     public void setGrenadeActivated(boolean grenadeActivated) {
//         isGrenadeActivated = grenadeActivated;
//     }

//     public Timer getExplosionTimer() {
//         return explosionTimer;
//     }

//     public void setExplosionTimer(Timer explosionTimer) {
//         this.explosionTimer = explosionTimer;
//     }

//     public boolean getIsPlaced() {
//         return isPlaced;
//     }

//     public void setPlaced(boolean isPlaced) {
//         this.isPlaced = isPlaced;
//     }



//     public boolean getIsGrenadeActivated(){
//         return isGrenadeActivated;
//     }
// }

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.image.BufferedImage;
// import java.util.ArrayList;
// import java.util.List;

public class A4 extends Armes {
    private int x;
    private int y;
    private  int dimension = 40;
    private BufferedImage[] grenadeImage;
    private BufferedImage[] explosionImage;
    private boolean isActivated = false;
    private boolean isExploding = false;
    private Timer explosionTimer;
    private List<Timer> explosionTimers;

    public A4(int x, int y) {
        super("grenade", 50, false, 5, 3, 3000, 400, true);
        this.x = x;
        this.y = y;
        loadGrenadeImage();
        loadExplosionImage();
        explosionTimers = new ArrayList<>();
    }

    public A4() {
        super("grenade", 50, false, 5, 3, 3000, 400, true);
        loadGrenadeImage();
        loadExplosionImage();
        explosionTimers = new ArrayList<>();
    }

    public void loadGrenadeImage() {
        BufferedImage atlas = Enregistrement.getSpriteAtlas();
        grenadeImage = new BufferedImage[1];
        grenadeImage[0] = atlas.getSubimage(6 * 40, 4 * 40, 40, 40);
    }

    public void loadExplosionImage() {
        BufferedImage atlas = Enregistrement.getSpriteAtlas();
        explosionImage = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            explosionImage[i] = atlas.getSubimage((2 + i) * 40, 5 * 40, 40, 40);
        }
    }

    // public void activateGrenade(Graphics g) {
    //     isActivated = true;
    //     isExploding = false;
    //     explosionTimer = new Timer(1000, new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             isExploding = true;
    //             drawExplosion(g);
    //             explosionTimers.remove(explosionTimer);
    //             explosionTimer.stop();
    //         }
    //     });
    //     explosionTimers.add(explosionTimer);
    //     explosionTimer.start();
    // }

    public void activateGrenade() {
        isActivated = true;
        // isExploding = false;
        explosionTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isExploding = true;
                explosionTimer.stop();
            }
        });
        explosionTimer.start();
    }
    

    public void drawGrenade(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(grenadeImage[0], x, y, 40, 40, null);
        g2d.dispose();
        // activateGrenade(g2d);
        activateGrenade();
    }

    public void drawExplosion(Graphics g) {
        System.out.println("draw explosion");
        if (isExploding) {
            System.out.println(isExploding);
            Graphics2D g2d = (Graphics2D) g.create();
            for (int i = 0; i < explosionImage.length; i++) {
                System.out.println("dessin de l'explosion"+i);
                g2d.drawImage(explosionImage[i], x, y, 2 * 40, 2 * 40, null);
                System.out.println("dessin termine de l'explosion"+i);
            }
            g2d.dispose();
        }
        deleteGrenade();
    }

    public void deleteGrenade() {
        this.dimension = 0;
        isActivated = false;
        isExploding = false;
    }

    public void stopAllExplosions() {
        for (Timer timer : explosionTimers) {
            timer.stop();
        }
        explosionTimers.clear();
    }

    //-----------------GETTER & SETTER---------------------------\\\
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getIsActivated() {
        return isActivated;
    }
}

