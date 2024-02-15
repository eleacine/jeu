package Shooter.DeplacementTest;

public class Ennemi extends Personnage {

	public int id;
	public int power = 10; // faire des dégats de collision et de tir
	public int collisionPower = 50;
	public int speed;
	public int destX;
	public int destY;
	public float differenceX;
	public float differenceY;

	public Ennemi(int id, int destX, int destY) {
		this.speed = 2;
		this.x = 200;
		this.y = 100;
		this.sante = 100;
		this.id = id;
		this.size = 55;
	}

	public void calculateDifferences(float angle) {
		this.differenceX = (float) Math.cos(angle) * this.speed;
		this.differenceY = (float) Math.sin(angle) * this.speed;
	}

	public void move() {
		if (x > destX + 10) {
			x -= speed;
		}
		if (x < destX + 10) {
			x += speed;
		}
		if (y > destY + 10) {
			y -= speed;
		}
		if (y < destY + 10) {
			y += speed;
		}
	}

	public boolean detectCollision(float newX, float newY, int newSize) {
		if (x - newSize < newX && x + size > newX && y - newSize < newY && y + size > newY) {
			return true;
		}
		return false;
	}

	// ------------- Getters et setters ---------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getCollisionPower() {
		return collisionPower;
	}

	public void setCollisionPower(int collisionPower) {
		this.collisionPower = collisionPower;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDestX() {
		return destX;
	}

	public void setDestX(int destX) {
		this.destX = destX;
	}

	public int getDestY() {
		return destY;
	}

	public void setDestY(int destY) {
		this.destY = destY;
	}

	public float getDifferenceX() {
		return differenceX;
	}

	public void setDifferenceX(float differenceX) {
		this.differenceX = differenceX;
	}

	public float getDifferenceY() {
		return differenceY;
	}

	public void setDifferenceY(float differenceY) {
		this.differenceY = differenceY;
	}

}
