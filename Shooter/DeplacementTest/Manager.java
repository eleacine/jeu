package Shooter.DeplacementTest;

import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;

public class Manager extends KeyAdapter {

    public Plateau plateau;
    public Player player;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    private boolean spacePressed;
    private boolean spacePressedPrev;


    public Manager(Player player, Plateau plateau) {
        this.player = player;
        this.plateau = plateau;
    }

    public void handleKeyPress() {
        if (upPressed) {
            moveForward();
        }
        if (downPressed) {
            moveBackward();
        }
        if (!upPressed && !downPressed) {
            player.ySpeed = 0;
        }

        if (leftPressed) {
            player.direction -= player.rotationSpeed;
        }

        if (rightPressed) {
            player.direction += player.rotationSpeed;
        }

        if (!leftPressed && !rightPressed) {
            player.xSpeed = 0;
        }

        if (spacePressed && !spacePressedPrev) {
            shoot();
        }

        spacePressedPrev = spacePressed;

       
    }

    
    public void moveForward() {
        player.xSpeed = (int) Math.round(player.maxSpeed * Math.cos(player.direction));
        player.ySpeed = (int) Math.round(player.maxSpeed * Math.sin(player.direction));
        update();
    }
    
    public void moveBackward() {
        player.xSpeed = (int) Math.round(-player.maxSpeed * Math.cos(player.direction));
        player.ySpeed = (int) Math.round(-player.maxSpeed * Math.sin(player.direction));
        update();
    }
    

    // public void update() {
    //     player.x += player.xSpeed;
    //     player.y += player.ySpeed;
    //     // System.out.println("x : " + player.x + " y : " + player.y);
    //     if(player.x>=2043 || player.x<=-360 || player.y>=1379 || player.y<=30){
    //         player.x -= player.xSpeed;
    //         player.y -= player.ySpeed;
    //     }
    //     /*if(player.x>=plateau.getWidth() || player.x<=0 || player.y>=plateau.getHeight() || player.y<=0){
    //        player.x -= player.xSpeed;
    //        player.y -= player.ySpeed;
    //    } */
    
    // }
    
    public void update() {
        player.x += player.xSpeed;
        player.y += player.ySpeed;


        checkPlayerlimits();
    }

    private void checkPlayerlimits() {
     
        int minX = 0;
        int minY = 0;
       // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   // System.out.println("screensize :" + screenSize);
        int maxX = plateau.getWidth(); 
   // System.out.println("plateau.getWidth() : " + plateau.getWidth());
        int maxY = plateau.getHeight(); 
     // System.out.println("plateau.getHeight() : " + plateau.getHeight());
    
        
        if (player.x < minX) {
          System.out.println("player.x : " + player.x + " minX : " + minX);
           

            player.x = minX;
        } else if (player.x > maxX*2) {
         System.out.println("player.x : " + player.x + " maxX : " + maxX);
            player.x = maxX*2;
        }
    
        if (player.y < minY) {
           System.out.println("player.y : " + player.y + " minY : " + minY);
            player.y = minY;
        } else if (player.y > maxY*2) {
         System.out.println("player.y : " + player.y + " maxY : " + maxY);
            player.y = maxY*2;
        }
    }
    public void moveUp() {
        player.ySpeed = -player.maxSpeed;
    }

    public void moveDown() {
        player.ySpeed = player.maxSpeed;
    }

    public void stop() {
        player.xSpeed = 0;
        player.ySpeed = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
            upPressed = true;
            // moveUp();
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = true;
            // moveDown();
        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_Q) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
            upPressed = false;
            stop();
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = false;
            stop();
        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_Q) {
            leftPressed = false;
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }

    // ------------- Bullet ----------------

    public void shoot() {

        if (spacePressed) {
            Bullet b = new Bullet(player.x + 25, player.y, (float) player.direction);
            // faire une animation du joueur pour simuler le tir
            plateau.projectilesManager.getPlayerBullets().add(b);
        }
    }



} 
