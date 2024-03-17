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
            // moveForward();
            moveUp();
        }
        if (downPressed) {
            // moveBackward();
            moveDown();
        }
        if (!upPressed && !downPressed) {
            player.setYSpeed(0);
        }
        if (leftPressed) {
            // player.setDirection(player.getDirection() - player.getRotationSpeed());
            moveLeft();
        }
        if (rightPressed) {
            // player.setDirection(player.getDirection() + player.getRotationSpeed());
            moveRight();
        }

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

    public void moveUp() {
        player.setYSpeed(player.getMaxSpeed() * -1);
    }

    public void moveDown() {
        player.setYSpeed(player.getMaxSpeed());
    }

    public void moveLeft() {
        player.setXSpeed(player.getMaxSpeed() * -1);
    }

    public void moveRight() {
        player.setXSpeed(player.getMaxSpeed());
    }

    public void update() {
        checkPlayerlimits();
        checkObstacle(); // Passez la direction au contrôle des obstacles

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

    /**
     * Vérifie la présence d'obstacles autour du joueur et ajuste son comportement
     * en conséquence.
     */
    public void checkObstacle() {
        // Obtenir les indices actuels du joueur sur le plateau de jeu
         
        int currentXIndex = (int) (player.getX() / 40) + (int) Math.signum(player.getXSpeed());
        int currentYIndex = (int) (player.getY() / 40)+ (int) Math.signum(player.getYSpeed());        

            // Vérifier la case devant le joueur
        //int nextXIndex = currentXIndex + (int) Math.signum(player.getXSpeed());
        //int nextYIndex = currentYIndex + (int) Math.signum(player.getYSpeed());

        int[] xOffsets = { 0, 0, 1, -1, 1, -1, 1, -1 };
        int[] yOffsets = { 1, -1, 0, 0, 1, -1, -1, 1 };

        for (int i = 0; i < xOffsets.length; i++) {
        // Vérifier s'il y a un obstacle à la position adjacente
        int adjacentX = currentXIndex + xOffsets[i];
        int adjacentY = currentYIndex + yOffsets[i];
            if (isObstacle(adjacentX, adjacentY)) {
            // Il y a un obstacle devant le joueur, bloquer le mouvement
                stop();
            }
        }

        // Obtenir le type de la case sur laquelle se trouve actuellement le joueur
        int caseID = gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex];
        int casetype = ManagerCase.getCaseType(caseID);
        // Ajuster la vitesse maximale du joueur en fonction du type de case
        switch (casetype) {
            // OBSTACLE RALENTISSEUR EAU
            case ManagerCase.OBSTACLE:
                player.setMaxSpeed(1);
                break;
            // MARCHE NORMAL POUR LE JOUEUR
            case ManagerCase.SOL:
                player.setMaxSpeed(2);
                break;
            default:
                break;
        }
    }

    /**
     * Vérifie si la case aux indices spécifiés est un obstacle.
     * xIndex Indice en x de la case.
     * yIndex Indice en y de la case.
     * True si la case est un obstacle, sinon false.
     */

    private boolean isObstacle(int xIndex, int yIndex) {
        // Vérifier si les indices spécifiés sont dans les limites du plateau de jeu
        if (xIndex >= 0 && yIndex >= 0 &&
                xIndex < gameManager.getGamePlateau().level_tab[0].length &&
                yIndex < gameManager.getGamePlateau().level_tab.length) {

            int caseID = gameManager.getGamePlateau().level_tab[yIndex][xIndex];
            int casetype = ManagerCase.getCaseType(caseID);
            // Vérifier si la case est un obstacle (type 2)
            if (casetype == ManagerCase.MUR || casetype == ManagerCase.MUR_CASSANT|| casetype == ManagerCase.BLOQUE) {
                float newX = player.getX() + player.getXSpeed();
                float newY = player.getY() + player.getYSpeed();

                // Vérifier si le joueur peut continuer sans se déplacer vers l'obstacle dans
                // n'importe quelle direction
                if (!isMovingTowardsObstacle(newX, newY, xIndex, yIndex)) {
                    return false; // Le joueur peut continuer
                } else {
                    return true; // Le joueur est trop proche de l'obstacle, considérez-le comme un obstacle
                }
            }
        }
        return false; // Si ce n'est pas un obstacle ou n'existe pas
       
    }

    /**
     * Vérifie si le joueur se déplace en direction d'un obstacle.
     * newX Nouvelle position en x du joueur.
     * newY Nouvelle position en y du joueur.
     * obstacleX Position en x de l'obstacle.
     * obstacleY Position en y de l'obstacle.
     * True si le joueur se déplace vers l'obstacle, sinon false.
     */
    private boolean isMovingTowardsObstacle(float newX, float newY, float obstacleX, float obstacleY) {
        // Calculer la distance en x et en y entre le joueur et l'obstacle
        double playerToObstacleX = obstacleX - newX;
        double playerToObstacleY = obstacleY - newY;

        // Obtenir les composantes de direction du joueur
        double playerDirectionX = Math.cos(player.getDirection());
        double playerDirectionY = Math.sin(player.getDirection());

        // Calculer le produit scalaire (dot product)
        double dotProduct = playerToObstacleX * playerDirectionX + playerToObstacleY * playerDirectionY;

        // Vérifier si le produit scalaire est négatif, indiquant que le joueur se
        // déplace vers l'obstacle
        return dotProduct < 0;
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
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = true;
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
            stop();
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = false;
            stop();
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
