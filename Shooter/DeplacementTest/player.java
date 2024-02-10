package Shooter.DeplacementTest;

public class Player extends Personnage {
    
    public double direction; // Ajout de la propriété direction
    public double rotationSpeed = Math.PI / 200; // Vitesse de rotation en radians par seconde

    public Player() {
        maxSpeed = 1;
        size = 35;
        x = 600;
        y = 400;
        xSpeed = 0;
        ySpeed = 0;
        direction = 0; // Initialiser la direction à zéro (vers la droite)
    }

    public boolean detectCollision() {
        if (x < 0 || x >= 1850 || y < 0 || y >= 1050) {
            return true;
        }
        return false;
    }

//------------- Getters et setters ---------------------------
    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }


}

