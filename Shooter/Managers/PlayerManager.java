package Shooter.Managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import Shooter.model.Player;

public class PlayerManager extends KeyAdapter {

    public GameManager gameManager;
    public Player player;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    private boolean spacePressed;
    private boolean spacePressedPrev;

    public PlayerManager (GameManager gameManager){
        this.gameManager = gameManager;
        this.player = gameManager.getPlayer();
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
       //  if (!leftPressed && !rightPressed) {
         //    player.xSpeed = 0;
             //si upPressed ou downPressed faire avancer = cause du ralentissement
        // }

        // if (spacePressed && !spacePressedPrev) {
        //     shoot();
        // }

        // spacePressedPrev = spacePressed;
    }

    public void moveForward() {
        player.xSpeed = (int) Math.round(player.maxSpeed * Math.cos(player.direction));
        player.ySpeed = (int) Math.round(player.maxSpeed * Math.sin(player.direction));
    }
    
    public void moveBackward() {
        player.xSpeed = (int) Math.round(-player.maxSpeed * Math.cos(player.direction));
        player.ySpeed = (int) Math.round(-player.maxSpeed * Math.sin(player.direction));
    }
    
    
    public void update() {
        checkPlayerlimits();
        checkObstacle();

        player.x += player.xSpeed;
        player.y += player.ySpeed;

      
    }

    private void checkPlayerlimits() {
     
        int minX = player.size;
        int minY = player.size;

        int maxX = 1440 - player.size;

        int maxY = 840 - player.size;
    
        if (player.x < minX) {
            player.x = minX;
        } else if (player.x > maxX) {
            player.x = maxX;
        }
    
        if (player.y < minY) {
            player.y = minY;
        } else if (player.y > maxY) {
            player.y = maxY;
        }
    }


    public void checkObstacle() {
        int currentXIndex = (int) (player.getX() / 50);
        int currentYIndex = (int) (player.getY() / 50);
    
        int tileType = gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex];
    
        switch (tileType) {
            case 2: 
                blockMovementTowardsObstacle(currentXIndex, currentYIndex);
                break;
            case 1:
                player.setMaxSpeed(1);
                break;
            case 0:
                player.setMaxSpeed(2);
                break;
            default:
                break;
        }
    }
    
    private void blockMovementTowardsObstacle(int currentXIndex, int currentYIndex) {

        double obstacleDirection = Math.atan2(player.y - (currentYIndex * 50), player.x - (currentXIndex * 50));
    
        // Calculate the distance between the player and the obstacle
        double distanceToObstacle = Math.sqrt(Math.pow(player.x - currentXIndex * 50, 2) + Math.pow(player.y - currentYIndex * 50, 2));
    
        // Define the size of the tiles in your game
        int tileSize = 50;
    
        // Define a minimum distance to maintain from the obstacle
        double minDistanceToObstacle = player.size * 1.5; // Adjust this value as needed
    
        // If the player is too close to the obstacle, adjust their position
        if (distanceToObstacle < minDistanceToObstacle) {
            // Calculate the adjusted position to maintain the minimum distance
            int adjustedX = (int) (currentXIndex * tileSize + Math.cos(obstacleDirection) * minDistanceToObstacle);
            int adjustedY = (int) (currentYIndex * tileSize + Math.sin(obstacleDirection) * minDistanceToObstacle);
    
            // Update the player's position
            player.x = adjustedX;
            player.y = adjustedY;
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
        // System.out.println("keyPressed : " + code);
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
            upPressed = true;
            moveUp();
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = true;
            moveDown();
        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_Q) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        // if (code == KeyEvent.VK_SPACE) {
        //     spacePressed = true;
        // }

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

        // if (code == KeyEvent.VK_SPACE) {
        //     spacePressed = false;
        // }
    }

    // // ------------- Bullet ----------------

    // public void shoot() {

    //     if (spacePressed) {
    //         Bullet b = new Bullet(player.x + 25, player.y, (float) player.direction);
    //         // faire une animation du joueur pour simuler le tir
    //         plateau.projectilesManager.getPlayerBullets().add(b);
    //     }
    // }



} 
