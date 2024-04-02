package Shooter.model;

// import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
// import java.nio.Buffer;
// import java.util.Iterator;
import java.util.Timer;
// import java.util.TimerTask;

import Shooter.Managers.EnnemiManager;

// Mine => explose si un personnage passe dessus
public class A3 extends Armes {

    protected int x;
    protected int y;
    protected static EnnemiManager ennemiManager;
    protected int dimension = 40;
    private BufferedImage[] mineImage;
    private static BufferedImage[] explosionImage;
    private Timer explosionTimer;
    private long explosionDelay = 1700; 
   // private Graphics g;


    
    public A3() {
        super("Mine",50, false, 5, 2,2000,100,true);
        loadMineImage();
      
    }

    public A3(int x, int y) {
        super("Mine",50, false, 5, 2,2000,100,true);
 
        this.x = x;
        this.y = y;
        loadMineImage();
       
    }

    public void loadMineImage() {
        BufferedImage atlas=Enregistrement.getSpriteAtlas();
        mineImage=new BufferedImage[1]; 
        mineImage[0]= atlas.getSubimage(5*40, 4*40, 40, 40);
        
    }

    public static void loadMineExplosion(){
        BufferedImage atlas=Enregistrement.getSpriteAtlas();
        explosionImage=new BufferedImage[6];
        for (int i =0 ; i <6;i++){
            explosionImage[i]= atlas.getSubimage((2+i)*40, 5*40, 40, 40);
        }  
    }

public void drawExplosion(int x, int y, Graphics g) {
      //  System.out.println("draw explosion");
        loadMineExplosion();
        Graphics2D g2d = (Graphics2D) g.create();
        for(int i = 0; i < explosionImage.length; i++){
        g2d.drawImage(explosionImage[i], x , y , 2*40, 2*40, null);
        g2d.dispose();
        }
      
    }

/* 
 public void HitMine (Graphics g){
    this.g = g;
    for (A3 mine : ennemiManager.getGameManager().getGamePlateau().pieges){
        if (ennemiManager.getPlayer().detectCollision(mine.getX(), mine.getY(), mine.getDimension())){
         System.out.println("collision mine");
         ennemiManager.getPlayer().infligerDegats(mine.getPower());
         drawExplosion(mine.getX(), mine.getY(), g);
            // mine.dimension = 0;s
            mine.setDimension(0);
        }

        if (ennemiManager.getPerso_list().size() > 1){
            for (Personnage perso : ennemiManager.getPerso_list()){
                if(perso instanceof Enemy){
                    Enemy ennemi=(Enemy)perso;
                    if (ennemi.detectCollision(mine.getX(), mine.getY(), mine.getDimension())){
                    
                        ennemi.infligerDegats(mine.getPower());
                        drawExplosion(mine.getX(), mine.getY(), g);
                        // mine.dimension = 0;
                        mine.setDimension(0);
                    }
                }
            }
        }
    }
}
*/
        public void draw( Graphics g, int playerX, int playerY) {
           // double distanceToPlayer = Math.sqrt(Math.pow(playerX - x, 2) + Math.pow(playerY - y, 2));
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(mineImage[0], x, y, dimension, dimension, null);
            g2d.dispose();
                shoot();
            }
  
    

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
    
}
