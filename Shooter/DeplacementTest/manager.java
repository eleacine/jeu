package Shooter.DeplacementTest;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class manager extends KeyAdapter {

    public plateau plateau;
    public player player;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    public manager(player player,plateau plateau) {
        this.player = player;
        this.plateau = plateau;
    }

    public void update() {
        player.x += player.xSpeed;
        player.y += player.ySpeed;
         
    }

    public void moveUp() {       
        player.ySpeed = -4;
      
    }

    public void moveDown() {
        player.ySpeed = 4;
    }

    public void moveLeft() {
        player.xSpeed = -4;
    }

    public void moveRight() {
        player.xSpeed = 4;
    }

    public void stop() {
        player.xSpeed = 0;
        player.ySpeed = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        plateau.update();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
    public void handleKeyPress() {
        if (upPressed) {
            moveUp();
        }
        if (downPressed) {
            moveDown();
        }
        if (leftPressed) {
            moveLeft();
        }
        if (rightPressed) {
            moveRight();
        }
        if (!upPressed && !downPressed && !leftPressed && !rightPressed) {
            stop();
        }
    }
}
