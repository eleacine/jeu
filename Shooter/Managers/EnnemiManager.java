package Shooter.Managers;

import java.util.ArrayList;
import java.util.Iterator;

import Shooter.model.Enemy;
import Shooter.model.EnemyBasique;
import Shooter.model.EnemyMedium;


public class EnnemiManager {

    public ArrayList<Enemy> ennemis;

    public GameManager gameManager;

    public EnnemiManager(GameManager gameManager) {
        this.gameManager = gameManager;
        ennemis = new ArrayList<Enemy>();
        ennemis.add(new EnemyBasique());
        ennemis.add(new EnemyMedium());
    }

    public void update() {
        for (Enemy ennemi : ennemis) {

            if (ennemi.getSize() >= 0) {
                ennemi.updateBehavior(gameManager.getPlayer());
                if (ennemi.isPlayerDetected(gameManager.getPlayer())) {
                    ennemi.shootBehavior(gameManager.getPlayer(), gameManager.getProjectilesManager());
                }
                // ennemi.shootBehavior(player, plateau.projectilesManager);

                if (ennemi.detectCollision(gameManager.getPlayer().x, gameManager.getPlayer().y, gameManager.getPlayer().size)) {
                    gameManager.getPlayer().sante -= ennemi.getCollisionPower();
                    ennemi.size = 0;
                    // if (player.sante <= 0) {
                    // player.size = 0;
                    // }
                }
            }
        }
    }

    

    public void suppEnnemi() {
        Iterator<Enemy> it = ennemis.iterator();
        while (it.hasNext()) {
            Enemy ennemi = it.next();
            if (ennemi.getSize() <= 0 || ennemi.sante <= 0) {
                // System.out.println("Santé restante ennemi " + ennemi.id + " : " + ennemi.sante);
                it.remove();
            }
        }
    }

}
