package Shooter.DeplacementTest;

public class Player extends Personnage {

	public int size = 45;
	public int x = 800;
	public int y = 400;
	public int xSpeed = 0;
	public int ySpeed = 0;
	public int maxSpeed = 2;
	public int health = 500;

	public double direction = 0;
	public double rotationSpeed = Math.PI / 100;

 


    public boolean detectCollision(float newX, float newY, int newSize) {
        if (x - newSize < newX && x + size > newX && y - newSize < newY && y + size > newY) {
            return true;
        }
        return false;
    }

    // public boolean detectCollision(float newX, float newY, int newSize){
	// 	if (xCor - (newSize-2) <= newX && xCor + size >= (newX+2) && yCor - (newSize-2) <= newY && yCor + size >= (newY+2)){
	// 		return true;
	// 	}
	// 	return false;
	// }

    // ------------- Getters et setters ---------------------------

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

    public int getX (){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    

}
