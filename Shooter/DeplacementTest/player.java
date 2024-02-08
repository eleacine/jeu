package Shooter.DeplacementTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player {
    
    public int x, y;
    public int xSpeed, ySpeed;
    public int size = 35;
    public double direction; // Ajout de la propriété direction
    public double rotationSpeed = Math.PI / 14; // Vitesse de rotation en radians par seconde
    public int maxSpeed = 15;



    public Player() {
        
      x=600;
        y=600;
       // x = getWidth() / 2;
       /// y = getHeight() / 2;
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





