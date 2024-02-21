package Shooter.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Player extends Personnage{

    protected String pseudo;
	protected double direction = 0;
	protected double rotationSpeed = Math.PI / 100; 
    protected ArrayList<Armes> armes = new ArrayList<Armes>();
    protected int currentArme;
    
    

    public Player(String pseudo) {
        super(1000, 500, 0, 0, 45, 2, 200);
        this.pseudo = pseudo;
        
        this.armes.add(new A1());
        this.armes.add(new A2());
        this.armes.add(new A3());
        currentArme = 0;
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

    public ArrayList<Armes> getArmes() {
        return armes;
    }

    public void setArmes(ArrayList<Armes> armes) {
        this.armes = armes;
    }

    public int getCurrentArme() {
        return currentArme;
    }

    public void setCurrentArme(int currentArme) {
        this.currentArme = currentArme;
    }

}