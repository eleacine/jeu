package Shooter.Managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import Shooter.model.Player;

public class PlayerManager extends KeyAdapter {

    protected GameManager gameManager;
    protected Player player;
    // private boolean upPressed;
    // private boolean downPressed;
    // private boolean leftPressed;
    // private boolean rightPressed;

    // private boolean canMove = false;

    public PlayerManager(GameManager gameManager) {
        this.gameManager = gameManager;
        this.player = gameManager.getPlayer();
    }

    // public void handleKeyPress() {

    // if (upPressed) {
    // // moveForward();
    // moveUp();
    // }
    // if (downPressed) {
    // // moveBackward();
    // moveDown();
    // }
    // if (!upPressed && !downPressed) {
    // player.setYSpeed(0);
    // }
    // if (leftPressed) {
    // // player.setDirection(player.getDirection() - player.getRotationSpeed());
    // moveLeft();
    // }
    // if (rightPressed) {
    // // player.setDirection(player.getDirection() + player.getRotationSpeed());
    // moveRight();
    // }

    // }

    // public void moveUp() {
    // player.setYSpeed(player.getMaxSpeed() * -1);
    // }

    // public void moveDown() {
    // player.setYSpeed(player.getMaxSpeed());
    // }

    // public void moveLeft() {
    // player.setXSpeed(player.getMaxSpeed() * -1);
    // }

    // public void moveRight() {
    // player.setXSpeed(player.getMaxSpeed());
    // }

    // public void moveForward() {
    // int xSpeed = (int) Math.round(player.getMaxSpeed() *
    // Math.cos(player.getDirection()));
    // int ySpeed = (int) Math.round(player.getMaxSpeed() *
    // Math.sin(player.getDirection()));
    // player.setXSpeed(xSpeed);
    // player.setYSpeed(ySpeed);
    // }

    // public void moveBackward() {

    // int xSpeed = (int) Math.round(-player.getMaxSpeed() *
    // Math.cos(player.getDirection()));
    // int ySpeed = (int) Math.round(-player.getMaxSpeed() *
    // Math.sin(player.getDirection()));
    // player.setXSpeed(xSpeed);
    // player.setYSpeed(ySpeed);
    // }

    // public void update() {
    // checkPlayerlimits();
    // checkObstacle(); // Passez la direction au contrôle des obstacles
    // canMove();

    // player.setX(player.getX() + player.getXSpeed());
    // player.setY(player.getY() + player.getYSpeed());

    // }

    // ------------------------------------------------------------------------
    public void update() {
        // Mettre à jour la position du joueur en fonction de sa vitesse
        int newX = player.getX() + player.getXSpeed();
        int newY = player.getY() + player.getYSpeed();

        // Vérifier si la prochaine position est valide (sans collision avec un mur)
        if (isValidPosition(newX, newY)) {
            player.setX(newX);
            player.setY(newY);
        } else {
            // Réinitialiser la vitesse si la position n'est pas valide
            player.setXSpeed(0);
            player.setYSpeed(0);
        }
    }

    

    //  ajouter une méthode pour gerer les cases de ralentissement 

    private boolean isValidPosition(float x, float y) {
        // Vérifier si la position est à l'intérieur des limites du plateau de jeu
        int playerSize = player.getSize();
        int minX = playerSize;
        int minY = playerSize;
        int maxX = 1440 - playerSize;
        int maxY = 840 - playerSize;

        if (x < minX || x > maxX || y < minY || y > maxY) {
            return false;
        }

        // Obtenir les indices de la case correspondante dans le tableau du plateau de
        // jeu
        int xIndex = (int) (x / 40);
        int yIndex = (int) (y / 40);

        // Obtenir le type de la case
        int caseType = ManagerCase.getCaseType(gameManager.getGamePlateau().level_tab[yIndex][xIndex]);

        // Vérifier si la case est un mur, un mur cassant ou une case bloquante
        return (caseType != ManagerCase.MUR && caseType != ManagerCase.MUR_CASSANT && caseType != ManagerCase.BLOQUE);
    }

    // -----------------------------------------------------------------------

    /**
     * Vérifie la présence d'obstacles autour du joueur et ajuste son comportement
     * en conséquence.
     */
    // public void checkObstacle() {
    //     // Obtenir les indices actuels du joueur sur le plateau de jeu
    //     int currentXIndex = (int) (player.getX() / 40);
    //     int currentYIndex = (int) (player.getY() / 40);

    //     // Obtenir le type de la case actuelle et de la prochaine case
    //     int currentCaseID = gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex];
    //     // int nextCaseID =
    //     // gameManager.getGamePlateau().level_tab[nextYIndex][nextXIndex];

    //     // Obtenir le type de la case sur laquelle se trouve actuellement le joueur
    //     int currentCaseType = ManagerCase.getCaseType(currentCaseID);
    //     // Obtenir le type de la case vers laquelle se dirige le joueur
    //     // int nextCaseType = ManagerCase.getCaseType(nextCaseID);

    //     // Vérifier le type de la case
    //     switch (currentCaseType) {
    //         // Cas où le joueur rencontre un mur
    //         case ManagerCase.MUR:
    //             canMove = false;
    //             if (!canMove) {
    //                 // Bloquer le mouvement du joueur en ajustant sa vitesse à zéro
    //                 player.setMaxSpeed(0);
    //             }
    //             break;
    //         // Cas où le joueur rencontre un obstacle ralentisseur (eau)
    //         case ManagerCase.OBSTACLE:
    //             player.setMaxSpeed(1);
    //             break;

    //         default:
    //             player.setMaxSpeed(2);
    //             break;
    //     }
    // }

    // test 2 Marylou
    // private void canMove() {
    //     int nextXIndex = player.getX() / 40;
    //     int nextYIndex = player.getY() / 40;
    //     if (!canMove) {
    //         if (rightPressed) {
    //             int nextTileType = gameManager.getGamePlateau().level_tab[nextYIndex][nextXIndex - 1];
    //             int casetype = ManagerCase.getCaseType(nextTileType);
    //             if (casetype != ManagerCase.MUR) {
    //                 canMove = true;
    //                 player.setMaxSpeed(2);
    //             }
    //         } else if (upPressed) {
    //             int nextTileType = gameManager.getGamePlateau().level_tab[nextYIndex - 1][nextXIndex];
    //             int casetype = ManagerCase.getCaseType(nextTileType);
    //             if (casetype != ManagerCase.MUR) {
    //                 canMove = true;
    //                 player.setMaxSpeed(2);
    //             }
    //         }

    //     }
    // }

    // test 1 eleacine
    // private boolean isMovingTowardsSolidGround(int currentXIndex, int currentYIndex) {
    //     // Calculate the next tile in the direction the player is facing
    //     int nextXIndex = currentXIndex + (int) Math.round(Math.cos(player.getDirection()));
    //     int nextYIndex = currentYIndex + (int) Math.round(Math.sin(player.getDirection()));

    //     // Check if the next tile is within the bounds of the game board
    //     if (nextXIndex >= 0 && nextYIndex >= 0 &&
    //             nextXIndex < gameManager.getGamePlateau().level_tab[0].length &&
    //             nextYIndex < gameManager.getGamePlateau().level_tab.length) {

    //         // Get the type of the tile the player is moving towards
    //         int nextTileType = gameManager.getGamePlateau().level_tab[nextYIndex][nextXIndex];
    //         int casetype = ManagerCase.getCaseType(nextTileType);
    //         // Check if the next tile is a SOL (solid ground)
    //         return casetype == ManagerCase.SOL;
    //     }
    //     // If the next tile is out of bounds, consider it as not solid ground
    //     return false;
    // }

    /*
     * DIRECTION A OUBLIER
     * int[] xOffsets = { 0, 0, 1, -1, 1, -1, 1, -1 };
     * int[] yOffsets = { 1, -1, 0, 0, 1, -1, -1, 1 };
     *
     *
     * case ManagerCase.MUR:
     * // Check for obstacle in forward direction
     * //a conserver ca marche pour droite et gauche
     * int forwardX = currentXIndex +
     * (int)Math.round(Math.cos(player.getDirection()));
     * int forwardY = currentYIndex +
     * (int)Math.round(Math.sin(player.getDirection()));
     * //bloque le joueur quand il recule dans le if(isObstacle(forwardX, forwardY))
     * int bloqueDerriereX = currentXIndex -
     * (int)Math.round(Math.sin(player.getDirection()));
     * int bloqueDerriereY = currentYIndex +
     * (int)Math.round(Math.cos(player.getDirection()));
     * 
     * //int forwardX = currentXIndex -
     * (int)Math.round(Math.cos(player.getDirection()));
     * //int forwardY = currentYIndex +
     * (int)Math.round(Math.sin(player.getDirection()));
     * 
     * if (isObstacle(forwardX, forwardY) || isObstacle(bloqueDerriereX,
     * bloqueDerriereY)) {
     * stop(); // Stop player's movement if there's an obstacle ahead
     * }
     * break;
     */

    /**
     * Vérifie si la case aux indices spécifiés est un obstacle.
     * xIndex Indice en x de la case.
     * yIndex Indice en y de la case.
     * True si la case est un obstacle, sinon false.
     */

    // private boolean isObstacle(int xIndex, int yIndex) {
    //     // Vérifier si les indices spécifiés sont dans les limites du plateau de jeu
    //     if (xIndex >= 0 && yIndex >= 0 &&
    //             xIndex < gameManager.getGamePlateau().level_tab[0].length &&
    //             yIndex < gameManager.getGamePlateau().level_tab.length) {

    //         int caseID = gameManager.getGamePlateau().level_tab[yIndex][xIndex];
    //         int casetype = ManagerCase.getCaseType(caseID);
    //         // Vérifier si la case est un obstacle (type 2)
    //         if (casetype == ManagerCase.MUR || casetype == ManagerCase.MUR_CASSANT) {
    //             float newX = player.getX() + player.getXSpeed();
    //             float newY = player.getY() + player.getYSpeed();

    //             // Vérifier si le joueur peut continuer sans se déplacer vers l'obstacle dans
    //             // n'importe quelle direction
    //             if (!isMovingTowardsObstacle(newX, newY, xIndex * 40, yIndex * 40)) {
    //                 return false; // Le joueur peut continuer
    //             } else {
    //                 return true; // Le joueur est trop proche de l'obstacle, considérez-le comme un obstacle
    //             }
    //         }
    //     }
    //     return false; // Si ce n'est pas un obstacle ou n'existe pas

    // }

    /**
     * Vérifie si le joueur se déplace en direction d'un obstacle.
     * newX Nouvelle position en x du joueur.
     * newY Nouvelle position en y du joueur.
     * obstacleX Position en x de l'obstacle.
     * obstacleY Position en y de l'obstacle.
     * True si le joueur se déplace vers l'obstacle, sinon false.
     */
    // private boolean isMovingTowardsObstacle(float newX, float newY, float obstacleX, float obstacleY) {
    //     // Calculer la distance en x et en y entre le joueur et l'obstacle
    //     double playerToObstacleX = obstacleX - newX;
    //     double playerToObstacleY = obstacleY - newY;

    //     // Obtenir les composantes de direction du joueur
    //     double playerDirectionX = Math.cos(player.getDirection());
    //     double playerDirectionY = Math.sin(player.getDirection());

    //     // Calculer le produit scalaire (dot product)
    //     double dotProduct = playerToObstacleX * playerDirectionX + playerToObstacleY * playerDirectionY;

    //     // Vérifier si le produit scalaire est négatif, indiquant que le joueur se
    //     // déplace vers l'obstacle
    //     return dotProduct > 0;
    // }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Mettre à jour les variables selon les touches pressées
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
            player.setYSpeed(-player.getMaxSpeed());
        } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            player.setYSpeed(player.getMaxSpeed());
        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_Q) {
            player.setXSpeed(-player.getMaxSpeed());
        } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            player.setXSpeed(player.getMaxSpeed());
        }

        if (code == KeyEvent.VK_SPACE) {
            gameManager.managerArmes.changeArme();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // Réinitialiser la vitesse lorsque la touche est relâchée
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_Z || code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            player.setYSpeed(0);
        } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_Q || code == KeyEvent.VK_RIGHT
                || code == KeyEvent.VK_D) {
            player.setXSpeed(0);
        }
    }

    // @Override
    // public void keyPressed(KeyEvent e) {
    // int code = e.getKeyCode();
    // // System.out.println("keyPressed : " + code);
    // if (code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
    // upPressed = true;
    // } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
    // downPressed = true;
    // } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_Q) {
    // leftPressed = true;
    // } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
    // rightPressed = true;
    // }

    // if (code == KeyEvent.VK_SPACE) {
    // gameManager.managerArmes.changeArme();
    // }

    // }

    // @Override
    // public void keyReleased(KeyEvent e) {
    // int code = e.getKeyCode();

    // if (code == KeyEvent.VK_UP || code == KeyEvent.VK_Z) {
    // upPressed = false;
    // stop();
    // } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
    // downPressed = false;
    // stop();
    // } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_Q) {
    // leftPressed = false;
    // stop();
    // } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
    // rightPressed = false;
    // stop();
    // }

    // }

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
