package Shooter.Managers;

import java.util.ArrayList;

import Shooter.model.Armes;
import Shooter.model.Player;

public class ManagerArmes {
    //contient fonctions pour les actions de Armes
    // public ArrayList<Armes> armes = new ArrayList<Armes>();
    public Player player;

    public ManagerArmes(Player player){
        //ajouter les armes
        this.player = player;
       
    }

    public void changeArme(){
        //changer l'arme
        if (player.currentArme == player.armes.size() -1){
            player.currentArme = 0;
        } else {
            player.currentArme += 1;
        }
    }

    // public void addArme(Armes arme){
    //     armes.add(arme);
    // }

    // public ArrayList<Armes> getArmes(){
    //     return armes;
    // }
    
}
