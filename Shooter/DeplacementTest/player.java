// package Shooter.DeplacementTest;

// public class Player {
    
//     public int x, y;
//     public int xSpeed, ySpeed;
//     public int size = 35;
//     public int maxSpeed = 4;

//     public Player() {
//         x = 600;
//         y = 400;
//         xSpeed = 0;
//         ySpeed = 0;
//     }

// }

package Shooter.DeplacementTest;

public class Player {
    
    public int x, y;
    public int xSpeed, ySpeed;
    public int size = 20;
    public double direction; // Ajout de la propriété direction
    public double rotationSpeed = Math.PI / 15; // Vitesse de rotation en radians par seconde
    public int maxSpeed = 4;

    public Player() {
        x = 600;
        y = 400;
        xSpeed = 0;
        ySpeed = 0;
        direction = 0; // Initialiser la direction à zéro (vers la droite)
    }
}

