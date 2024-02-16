package Shooter.Managers;

import java.util.ArrayList;
import java.util.Iterator;

import Shooter.model.Enemy;
import Shooter.model.EnemyBasique;
import Shooter.model.EnemyMedium;
import Shooter.model.Plateau;
import Shooter.model.Player;

public class EnnemiManager {

    public PlayerManager manager;
    public ArrayList<Enemy> ennemis;
    public Plateau plateau;
    public Player player;

    public EnnemiManager(Player player, Plateau plateau) {
        this.player = player;
        this.plateau = plateau;
        this.manager = plateau.playerManager;
        ennemis = new ArrayList<Enemy>();
        ennemis.add(new EnemyBasique());
        ennemis.add(new EnemyMedium());
    }

    public void update() {
        for (Enemy ennemi : ennemis) {

            if (ennemi.getSize() >= 0) {
                ennemi.updateBehavior(player);
                if (ennemi.isPlayerDetected(player)) {
                    ennemi.shootBehavior(player, plateau.projectilesManager);
                }
                // ennemi.shootBehavior(player, plateau.projectilesManager);

                if (ennemi.detectCollision(player.x, player.y, player.size)) {
                    player.sante -= ennemi.getCollisionPower();
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
                System.out.println("Santé restante ennemi " + ennemi.id + " : " + ennemi.sante);
                it.remove();
            }
        }
    }

}
