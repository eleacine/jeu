package Shooter.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class Player extends Personnage{

    private String pseudo; 
    

    public Player(String pseudo) {
        this.pseudo = pseudo;
        this.sante=0; 
        this.x=0;
        this.y=9;
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

   
}
