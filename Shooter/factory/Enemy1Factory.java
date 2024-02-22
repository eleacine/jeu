package Shooter.factory;
import Shooter.model.*;

public class Enemy1Factory implements PersonnageFactory {
    @Override
    public Personnage createPersonnage(){
        return new EnemyBasique();
    } 
}
