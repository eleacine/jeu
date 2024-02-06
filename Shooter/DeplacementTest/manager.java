// package Shooter.DeplacementTest;

// import java.awt.event.KeyAdapter;
// import java.awt.event.KeyEvent;

// public class Manager extends KeyAdapter {

//     public Plateau plateau;
//     public Player player;
//     private boolean upPressed;
//     private boolean downPressed;
//     private boolean leftPressed;
//     private boolean rightPressed;

//     public Manager(Player player,Plateau plateau) {
//         this.player = player;
//         this.plateau = plateau;
//     }

//     public void update() {
//         player.x += player.xSpeed;
//         player.y += player.ySpeed;

//     }

//     public void moveUp() {       
//         player.ySpeed = player.maxSpeed * -1;

//     }

//     public void moveDown() {
//         player.ySpeed = player.maxSpeed;
//     }

//     public void moveLeft() {
//         player.xSpeed = player.maxSpeed * -1;
//     }

//     public void moveRight() {
//         player.xSpeed = player.maxSpeed;
//     }

//     public void stop() {
//         player.xSpeed = 0;
//         player.ySpeed = 0;
//     }

//     @Override
//     public void keyPressed(KeyEvent e) {
//         int code = e.getKeyCode();

//         if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
//             upPressed = true;
//         }
//         if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
//             downPressed = true;
//         }
//         if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
//             leftPressed = true;
//         }
//         if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
//             rightPressed = true;
//         }
//         plateau.update();
//     }

//     @Override
//     public void keyReleased(KeyEvent e) {
//         int code = e.getKeyCode();

//         if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
//             upPressed = false;
//         }
//         if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
//             downPressed = false;
//         }
//         if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
//             leftPressed = false;
//         }
//         if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
//             rightPressed = false;
//         }
//     }
//     public void handleKeyPress() {
//         if (upPressed) {
//             moveUp();
//         }
//         if (downPressed) {
//             moveDown();
//         }
//         if (leftPressed) {
//             moveLeft();
//         }
//         if (rightPressed) {
//             moveRight();
//         }
//         if (!upPressed && !downPressed) {
//             player.ySpeed = 0;
//         }
//         if (!leftPressed && !rightPressed) {
//             player.xSpeed = 0;
//         }
//     }
// }

package Shooter.DeplacementTest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Manager extends KeyAdapter {

    public Plateau plateau;
    public Player player;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

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
    

    public void update() {
        player.x += player.xSpeed;
        player.y += player.ySpeed;
    }

    public void moveUp() {
        player.ySpeed = -player.maxSpeed;
    }

    public void moveDown() {
        player.ySpeed = player.maxSpeed;
    }

    public void stop() {
        player.xSpeed = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

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
        plateau.update();
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
}
