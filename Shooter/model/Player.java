package Shooter.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class Player {

    private String pseudo;
    private int level = 1;  //1 par défaut
    private int santé;      //faudrait lui donner une valeur par défaut 
    private LinkedList<Armes> listeArmes;

    private int x;
    private int y;

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

    // ------ Getter et Setter ---------
    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String newPseudo) {
        this.pseudo = newPseudo;
    }

    public int getLevel() {
        return this.level;
    }

    public void addLevel() {
        this.level++;
    }
}
