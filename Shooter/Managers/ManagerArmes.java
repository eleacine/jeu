package Shooter.Managers;

import Shooter.model.Armes;
import Shooter.model.Player;

public class ManagerArmes {
    //contient fonctions pour les actions de Armes
    
    public Player player;
    public GameManager gameManager;

    public ManagerArmes( GameManager gameManager){
        //ajouter les armes
        this.gameManager = gameManager;
        this.player = gameManager.getPlayer();
       
    }

    public void changeArme(){
        //changer l'arme
        if (player.getCurrentArme() == player.getArmes().size() -1){
            player.setCurrentArme(0);
        } else {
            player.setCurrentArme(player.getCurrentArme() + 1);
        }
    }

    public void recharge(){
        //recharger les armes
        for (Armes arme : player.getArmes()) {
            arme.reinitialiser();
            
        }
    }
    
}
