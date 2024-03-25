package Shooter.Managers;

import java.util.Iterator;
import java.util.List;

import Shooter.model.Enemy;
import Shooter.model.EnemyIA;
import Shooter.model.EnemySniper;
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

                    // arranger ca
                    if (ennemi instanceof EnemyIA) {
                        EnemyIA ennemiIA = (EnemyIA) ennemi;
                      
                        // ennemiIA.moveTowardsPlayer(gameManager.getGamePlateau().floodfill, gameManager.getPlayer());
                        ennemiIA.updateBehavior(gameManager.getPlayer(), gameManager.getGamePlateau().floodfill);
                    
                    } else if (ennemi instanceof EnemySniper) {
                        EnemySniper ennemiSniper = (EnemySniper) ennemi;
                        // ennemiSniper.updateBehavior(gameManager.getPlayer(), gameManager.getGamePlateau().floodfill);
                        System.out.println(ennemiSniper.isPlayerInRange(gameManager.getPlayer(), gameManager.getGamePlateau().floodfill));
                        if (ennemiSniper.isPlayerInRange(gameManager.getPlayer(), gameManager.getGamePlateau().floodfill)) {
                            ennemiSniper.shootBehavior(gameManager.getPlayer(), gameManager.getProjectilesManager());
                        } else {
                            ennemiSniper.moveTowardsPlayer(gameManager.getGamePlateau().floodfill, gameManager.getPlayer());
                        }
            

                    } else if (ennemi.isPlayerDetected(gameManager.getPlayer())) {
                        ennemi.shootBehavior(gameManager.getPlayer(), gameManager.getProjectilesManager());
                    }

                    if (ennemi.detectCollision(gameManager.getPlayer().getX(), gameManager.getPlayer().getY(),
                            gameManager.getPlayer().getSize())) {
                        gameManager.getPlayer().infligerDegats(ennemi.getCollisionPower());
                    }
                }
            }
        }

    }

    public void suppEnnemi() {

        Iterator<Personnage> it = perso_list.iterator();

        while (it.hasNext()) {
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
