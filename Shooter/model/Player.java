package Shooter.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Player extends Personnage{

    private String pseudo;

    public int size = 45;
	public int x = 1000;
	public int y = 500;
	public int xSpeed = 0;
	public int ySpeed = 0;
	public int maxSpeed = 2;
	public int health = 500;

	public double direction = 0;
	public double rotationSpeed = Math.PI / 100; 
    

    public Player(String pseudo) {
        this.pseudo = pseudo;
    }

    // récupérer une éventuelle sauvegarde
    public static Player loadPlayer() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Game\\sauvegarde.ser"))) {
            return (Player) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isPseudoIdentique(String playerName) {
        return this.pseudo.equals(playerName);
    }

    public boolean detectCollision(float newX, float newY, int newSize) {
        if (x - newSize < newX && x + size > newX && y - newSize < newY && y + size > newY) {
            return true;
        }
        return false;
    }
    



    // ------ Getter et Setter ---------
    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String newPseudo) {
        this.pseudo = newPseudo;
    }

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
