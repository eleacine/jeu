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
    private static final double BUFFER_DISTANCE = 30.0;

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

        // Check obstacles in front, behind, right, left, and diagonally
        int[] xOffsets = { 0, 0, 1, -1, 1, -1, 1, -1 };
        int[] yOffsets = { 1, -1, 0, 0, 1, -1, -1, 1 };

        for (int i = 0; i < xOffsets.length; i++) {
            int adjacentX = currentXIndex + xOffsets[i];
            int adjacentY = currentYIndex + yOffsets[i];

            if (isObstacle(adjacentX, adjacentY)) {
                // There's an obstacle, change player direction to opposite
                stop(); // Stop checking once an obstacle is found
            }
        }

        int tileType = gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex];

        switch (tileType) {
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

    private boolean isObstacle(int xIndex, int yIndex) {
        if (xIndex >= 0 && yIndex >= 0 &&
                xIndex < gameManager.getGamePlateau().level_tab[0].length &&
                yIndex < gameManager.getGamePlateau().level_tab.length) {

            int tileType = gameManager.getGamePlateau().level_tab[yIndex][xIndex];

            // Check if the tile is an obstacle (type 2)
            if (tileType == 2) {
                // Check if the player can continue in the current direction without moving
                // towards the obstacle
                float newX = player.getX() + player.getXSpeed();
                float newY = player.getY() + player.getYSpeed();

                // Calculate the distance between the player's current position and the obstacle
                double distanceToObstacle = Math
                        .sqrt(Math.pow(newX - (xIndex * 50), 2) + Math.pow(newY - (yIndex * 50), 2));

                // Check if the player can continue without moving towards the obstacle (e.g.,
                // with some buffer distance)
                if (distanceToObstacle > BUFFER_DISTANCE) {
                    return false; // The player can continue
                } else {
                    return true; // The player is too close to the obstacle, consider it as an obstacle
                }
            }
        }

        return false; // Indices out of bounds or the tile is not an obstacle
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

    public Player getPlayer() {
        return this.player;
    }
}
