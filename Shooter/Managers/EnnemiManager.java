package Shooter.Managers;

import java.util.ArrayList;
import java.util.Iterator;

import Shooter.model.Enemy;
import Shooter.model.EnemyBasique;
import Shooter.model.EnemyMedium;


public class EnnemiManager {

    protected ArrayList<Enemy> ennemis;
    protected GameManager gameManager;

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

                if (ennemi.detectCollision(gameManager.getPlayer().getX(), gameManager.getPlayer().getY(), gameManager.getPlayer().getSize())) {
                    // gameManager.getPlayer().getSante() -= ennemi.getCollisionPower();
                    gameManager.getPlayer().infligerDegats(ennemi.getCollisionPower());
                    // ennemi.size = 0;

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
            if (ennemi.getSize() <= 0 || ennemi.getSante() <= 0) {
                // System.out.println("Santé restante ennemi " + ennemi.id + " : " + ennemi.sante);
                it.remove();
            }
        }
    }



//  ---------------- Getters et setters ---------------------------

    public ArrayList<Enemy> getEnnemis() {
        return ennemis;
    }

    public void setEnnemis(ArrayList<Enemy> ennemis) {
        this.ennemis = ennemis;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

}
