package Shooter.Managers;

import java.util.ArrayList;

import Shooter.model.Armes;
import Shooter.model.Player;
import Shooter.model.A3;

public class ManagerArmes {
    //contient fonctions pour les actions de Armes
    // public ArrayList<Armes> armes = new ArrayList<Armes>();
    public Player player;
    public GameManager gameManager;

    public ManagerArmes( GameManager gameManager){
        //ajouter les armes
        this.gameManager = gameManager;
        this.player = gameManager.getPlayer();
       
    }

    public void changeArme(){
        //changer l'arme
        if (player.currentArme == player.armes.size() -1){
            player.currentArme = 0;
        } else {
            player.currentArme += 1;
        }
    }




    public void rechargeArme(){
        //recharger l'arme
    }


 
    
    
}
