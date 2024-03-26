package Shooter.model;

import java.awt.Color;

import Shooter.Managers.ProjectilesManager;

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

    public EnemyBasique(int x, int y) {
        // super(50, 100, 1, 2, 10, 50, 1000, 500, new Color(255, 0, 0));
        super(x, y, 50, 100, 1, 2, 10, 50, 1000, 250, new Color(255, 0, 0));
    }

    @Override
    	public void shootBehavior(Player player, ProjectilesManager projectilesManager) {
		// Implémentez la logique de tir spécifique pour cet ennemi
		// Par exemple, tirer une balle vers le joueur
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastShotTime > getFrequency()) {
			Bullet bullet = new Bullet(x, y, player.x, player.y, power);
			projectilesManager.getEnemyBullets().add(bullet);
			lastShotTime = currentTime;
		}
	}


    

  

}
