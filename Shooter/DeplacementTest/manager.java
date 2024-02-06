package Shooter.DeplacementTest;

public class manager {

    public player p;

    public manager() {
        p = new player();
    }

    public void update() {
        p.x += p.xSpeed;
        p.y += p.ySpeed;
    }

    public void moveUp() {
        p.ySpeed = -1;
    }

    public void moveDown() {
        p.ySpeed = 1;
    }

    public void moveLeft() {
        p.xSpeed = -1;
    }

    public void moveRight() {
        p.xSpeed = 1;
    }

    public void stop() {
        p.xSpeed = 0;
        p.ySpeed = 0;
    }

    
    
}
