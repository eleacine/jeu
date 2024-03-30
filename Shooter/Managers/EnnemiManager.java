package Shooter.Managers;

import java.util.Iterator;
import java.util.List;

import Shooter.model.Enemy;
import Shooter.model.EnemyIA;
import Shooter.model.EnemySniper;
import Shooter.model.Personnage;
import Shooter.model.Player;

public class EnnemiManager {

    private GameManager gameManager;
    private List<Personnage> perso_list;
    private Player player;

    public EnnemiManager(GameManager gameManager) {
        this.gameManager = gameManager;
        this.perso_list = gameManager.getPersoList();
        this.player = gameManager.getPlayer();
    }

    public void update() {
        for (Personnage perso : perso_list) {
            if (perso instanceof Enemy) {
                Enemy ennemi = (Enemy) perso;
                if (ennemi.getSize() >= 0) {

                    // arranger ca
                    if (ennemi instanceof EnemyIA) {
                        EnemyIA ennemiIA = (EnemyIA) ennemi;

                        ennemiIA.updateBehavior(player, gameManager.getGamePlateau().floodfill);

                    } else if (ennemi instanceof EnemySniper) {
                        EnemySniper ennemiSniper = (EnemySniper) ennemi;
                    
                        if (ennemiSniper.isPlayerInRange(player, gameManager.getGamePlateau().floodfill)) {
                            ennemiSniper.shootBehavior(player, gameManager.getProjectilesManager());
                        } else {
                            ennemiSniper.moveTowardsPlayer(gameManager.getGamePlateau().floodfill, player);
                        }


                    } else if (ennemi.isPlayerDetected(player)) {
                        ennemi.updateBehavior(player, gameManager.getGamePlateau().floodfill);
                        ennemi.shootBehavior(player, gameManager.getProjectilesManager());
                    }

                    if (ennemi.detectCollision(player.getX(), player.getY(),
                            player.getSize())) {
                        player.infligerDegats(ennemi.getCollisionPower());
                        ennemi.setSize(0);
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

    // ------------- Getters et setters ---------------------------
    public List<Personnage> getPerso_list() {
        return perso_list;
    }

    public void setPerso_list(List<Personnage> perso_list) {
        this.perso_list = perso_list;
    }

    public Player getPlayer() {
        return player;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

}
