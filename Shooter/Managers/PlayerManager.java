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

    public PlayerManager(GameManager gameManager) {
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
        // if (!leftPressed && !rightPressed) {
        // player.xSpeed = 0;
        // si upPressed ou downPressed faire avancer = cause du ralentissement
        // }

        // if (spacePressed && !spacePressedPrev) {
        // shoot();
        // }

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
                blockObstacle(currentXIndex, currentYIndex);
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

    private void blockObstacle(int currentXIndex, int currentYIndex) {
        int x = (int) player.getX();
        int y = (int) player.getY();

        int xSpeed = player.xSpeed;
        int ySpeed = player.ySpeed;

        if (xSpeed > 0) {
            player.x = currentXIndex * 50 - player.size;
        } else if (xSpeed < 0) {
            player.x = (currentXIndex + 1) * 50;
        }

        if (ySpeed > 0) {
            player.y = currentYIndex * 50 - player.size;
        } else if (ySpeed < 0) {
            player.y = (currentYIndex + 1) * 50;
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

        if (code == KeyEvent.VK_SPACE) {
            gameManager.managerArmes.changeArme();

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

    }

    // // ------------- Bullet ----------------

    // public void shoot() {

    // if (spacePressed) {
    // Bullet b = new Bullet(player.x + 25, player.y, (float) player.direction);
    // // faire une animation du joueur pour simuler le tir
    // plateau.projectilesManager.getPlayerBullets().add(b);
    // }
    // }

}
