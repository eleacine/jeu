package Shooter.Managers;

import java.util.ArrayList;

import Shooter.model.Armes;

public class ManagerArmes {
    //contient fonctions pour les actions de Armes
    public ArrayList<Armes> armes = new ArrayList<Armes>();

    public ManagerArmes(){
        //ajouter les armes
       
    }

    // public void changeArme(int index){
    //     //changer l'arme
    // }

    public void addArme(Armes arme){
        armes.add(arme);
    }

    public ArrayList<Armes> getArmes(){
        return armes;
    }
    
}
