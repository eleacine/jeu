package Shooter.DeplacementTest;


public class Player extends Personnage {
    
    public int x, y;
    public int xSpeed, ySpeed;
    public int size = 35;
    public double direction; // Ajout de la propriété direction
    public double rotationSpeed = Math.PI / 40; // Vitesse de rotation en radians par seconde
    public int maxSpeed = 1;



    public Player() {
        this.x = 1280 / 2;
        this.y = 800 / 2;
       // x = getWidth() / 2;
       /// y = getHeight() / 2;
        xSpeed = 0;
        ySpeed = 0;
        direction = 0; // Initialiser la direction à zéro (vers la droite)
        sante = 100;
    }

	public boolean detectCollision(float newX, float newY, int newSize){
		if (x - newSize < newX && x + size > newX && y - newSize < newY && y + size > newY){
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




