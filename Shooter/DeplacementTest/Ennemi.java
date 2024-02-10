package Shooter.DeplacementTest;

public class Ennemi extends Personnage{
    
    public int id;
    public int power = 10; // faire des dégats de collision et de tir
    public int speed;
    public int destX;
    public int destY;

    public Ennemi(int id, int destX, int destY) {
        this.speed = 1;
        this.x = 200;
        this.y = 100;
		this.sante = 100;
        this.id = id;
		this.size = 55;
    }    
    
    public void move(){
		if (x > destX + 10){
			x -= speed;
		}
		if (x < destX + 10){
			x += speed;
		}
		if (y > destY + 10){
			y -= speed;
		}
		if (y < destY+ 10){
			y += speed;
		}
	}
	
	public boolean detectCollision(float newX, float newY, int newSize){
		if (x - newSize < newX && x + size > newX && y - newSize < newY && y + size > newY){
			return true;
		}
		return false;
	}

	

   
}
