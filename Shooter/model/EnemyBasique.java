package Shooter.model;

import java.awt.Color;

public class EnemyBasique extends Enemy{

    /* est statique
     * position x : 50
     * position y : 100
     * taille : 50
     * sante : 100
     * speed : 1
     * dégats de tir : 10
     * dégat de collision : 50
     * fréq de tir : 1 seconde
     * radius de détection : 500 px
     */

    public EnemyBasique() {
        // super(50, 100, 1, 2, 10, 50, 1000, 500, new Color(255, 0, 0));
        super(100, 50, 50, 100, 1, 2, 10, 50, 1000, 500, new Color(255, 0, 0));
    }

    

  

}
