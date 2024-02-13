package Shooter.DeplacementTest;

public class Personnage {

    public int x, y;
    public int xSpeed, ySpeed;
    public int size;
    public int maxSpeed;
    public int sante; // deux foncitons infligerDegats et soigner

	public boolean detectCollision(float newX, float newY, int newSize){
		if (x - newSize < newX && x + size > newX && y - newSize < newY && y + size > newY){
			return true;
		}
		return false;
	}

    // ------------- Getters et setters ---------------------------
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getSante() {
        return sante;
    }

    public void setSante(int sante) {
        this.sante = sante;
    }

    public void infligerDegats(int degats) {
        this.sante -= degats;
    }

    public void soigner(int soin) {
        this.sante += soin;
    }


}
