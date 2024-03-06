package Shooter.Managers;

import java.util.Iterator;
import java.util.List;

import Shooter.model.Enemy;
import Shooter.model.Personnage;

public class EnnemiManager {

    public GameManager gameManager;
    public List<Personnage> perso_list;

    public EnnemiManager(GameManager gameManager) {
        this.gameManager = gameManager;
        this.perso_list = gameManager.getPersoList();
    }

    public void update() {
        for (Personnage perso : perso_list) {
            if (perso instanceof Enemy) {
                Enemy ennemi = (Enemy) perso;
                if (ennemi.getSize() >= 0) {
                    ennemi.updateBehavior(gameManager.getPlayer());
                    if (ennemi.isPlayerDetected(gameManager.getPlayer())) {
                        ennemi.shootBehavior(gameManager.getPlayer(), gameManager.getProjectilesManager());
                    }

                    if (ennemi.detectCollision(gameManager.getPlayer().getX(), gameManager.getPlayer().getY(),
                            gameManager.getPlayer().getSize())) {
                        gameManager.getPlayer().infligerDegats(ennemi.getCollisionPower());
                        // ennemi.size = 0;

                        // if (player.sante <= 0) {
                        // player.size = 0;
                        // }
                    }
                }
            }
        }
    }

    public void suppEnnemi() {

        Iterator<Personnage> it = perso_list.iterator();

        while (it.hasNext()){
            Personnage perso = it.next();
            if (perso instanceof Enemy) {
                Enemy ennemi = (Enemy) perso;
                if (ennemi.getSize() <= 0 || ennemi.getSante() <= 0) {
                    it.remove();
                }
            }
        }
    }

    public List<Personnage> getPerso_list() {
        return perso_list;
    }

}
