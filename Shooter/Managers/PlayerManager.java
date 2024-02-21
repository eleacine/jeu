package Shooter.Managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import Shooter.model.Player;

public class PlayerManager extends KeyAdapter {

    protected GameManager gameManager;
    protected Player player;
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
            player.setYSpeed(0);
        }
        if (leftPressed) {
            player.setDirection(player.getDirection() - player.getRotationSpeed());
        }
        if (rightPressed) {
            player.setDirection(player.getDirection() + player.getRotationSpeed());
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
        int xSpeed = (int) Math.round(player.getMaxSpeed() * Math.cos(player.getDirection()));
        int ySpeed = (int) Math.round(player.getMaxSpeed() * Math.sin(player.getDirection()));
        player.setXSpeed(xSpeed);
        player.setYSpeed(ySpeed);
    }

    public void moveBackward() {

        int xSpeed = (int) Math.round(-player.getMaxSpeed() * Math.cos(player.getDirection()));
        int ySpeed = (int) Math.round(-player.getMaxSpeed() * Math.sin(player.getDirection()));
        player.setXSpeed(xSpeed);
        player.setYSpeed(ySpeed);
    }

    public void update() {
        checkPlayerlimits();
        checkObstacle();

        player.setX(player.getX() + player.getXSpeed());
        player.setY(player.getY() + player.getYSpeed());

    }

    private void checkPlayerlimits() {

        int minX = player.getSize();
        int minY = player.getSize();

        int maxX = 1440 - player.getSize();

        int maxY = 840 - player.getSize();

        if (player.getX() < minX) {

            player.setX(minX);

        } else if (player.getX() > maxX) {

            player.setX(maxX);
        }

        if (player.getY() < minY) {

            player.setY(minY);
        } else if (player.getY() > maxY) {

            player.setY(maxY);
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
        // int getX() = (int) player.getX();
        // int getY() = (int) player.getY();

        // int xSpeed = player.xSpeed;
        // int ySpeed = player.ySpeed;

        // if (xSpeed > 0) {
        // player.getX() = currentXIndex * 50 - player.getSize();
        // } else if (xSpeed < 0) {
        // player.getX() = (currentXIndex + 1) * 50;
        // }

        // if (ySpeed > 0) {
        // player.getY() = currentYIndex * 50 - player.getSize();
        // } else if (ySpeed < 0) {
        // player.getY() = (currentYIndex + 1) * 50;
        // }
    }

    public void moveUp() {
        player.setYSpeed(player.getYSpeed() - player.getMaxSpeed());
    }

    public void moveDown() {
        player.setYSpeed(player.getYSpeed() + player.getMaxSpeed());
    }

    public void stop() {
        player.setXSpeed(0);
        player.setYSpeed(0);
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
    // Bullet b = new Bullet(player.getX() + 25, player.getY(), (float)
    // player.direction);
    // // faire une animation du joueur pour simuler le tir
    // plateau.projectilesManager.getPlayerBullets().add(b);
    // }
    // }

}
