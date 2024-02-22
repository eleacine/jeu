package Shooter.factory;
import Shooter.model.*;

public class Enemy2Factory implements PersonnageFactory {
    @Override
    public Personnage createPersonnage(){
        return new EnemyMedium();
    } 
}
