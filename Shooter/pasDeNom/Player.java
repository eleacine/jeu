package Shooter.pasDeNom;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Player {

    private String pseudo;
    private int level = 1;

    public Player(String pseudo) {
        this.pseudo = pseudo;
    }

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
