package Shooter.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.*;

public class Player extends Personnage {

    protected String pseudo;
    protected double direction = 0;
    protected double rotationSpeed = Math.PI / 100;
    protected ArrayList<Armes> armes = new ArrayList<Armes>();
    protected int currentArme;

    protected ImageIcon sprite;

    public Player(String pseudo) {
        super(1000, 500, 0, 0, 45, 2, 200);
        this.pseudo = pseudo;

        this.armes.add(new A1());
        this.armes.add(new A2());
        this.armes.add(new A3());
        this.armes.add(new A4());
        this.armes.add(new A5());
        currentArme = 0;

        loadSprite();
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

    public void loadSprite() {
        URL imageUrl = getClass().getResource("j_test.png");
        ImageIcon originalImage = new ImageIcon(imageUrl);

        double scale = 5;
        int newWidth = (int) (size * scale);
        int newHeight = (int) (size * scale);

        Image resizedImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon joueurImage = new ImageIcon(resizedImage);
        this.sprite = joueurImage;
    }

    public boolean isPseudoIdentique(String playerName) {
        return this.pseudo.equals(playerName);
    }

    public void reset() {
        this.x = 1000;
        this.y = 500;
        this.direction = 0;
        this.sante = 200;
        this.currentArme = 0;

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

    public ImageIcon getSprite() {
        return sprite;
    }

    public void setSprite(ImageIcon sprite) {
        this.sprite = sprite;
    }

}