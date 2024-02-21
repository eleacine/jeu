package Shooter.Managers;

import java.util.ArrayList;
import java.util.Iterator;

import Shooter.model.Enemy;
import Shooter.model.Player;
import Shooter.model.Bullet;

public class ProjectilesManager {

    public PlayerManager m;
    public EnnemiManager ennemiManager;
    public Player player;
    public ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();
    public ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();
    public GameManager gameManager;

    
    public ProjectilesManager(Player player, GameManager gameManager ) {
        this.player = player;
        this.gameManager = gameManager;
        this.m = gameManager.getPlayerManager();
        this.ennemiManager = gameManager.getEnnemiManager();
    }

    public void hitEnnemi() {
        for (Bullet bullet : playerBullets) {
            for (Enemy ennemi : ennemiManager.ennemis) {
                if (ennemi.detectCollision(bullet.getX(), bullet.getY(), bullet.getSize())) {
                    ennemi.sante -= bullet.getDegats();
                    // System.out.println("Santé restante ennemi " + ennemi.id + " : " + ennemi.sante);
                    bullet.size = 0;
                    if (ennemi.sante <= 0) {
                        // System.err.println("ennemi mort");
                        ennemi.size = 0;
                    }
                }
            }
        }

    }

    public void hitPlayer() {
        for (Bullet bullet : enemyBullets) {
         //   System.out.println("player size"+player.size);
            if (player.detectCollision(bullet.getX(), bullet.getY(), bullet.getSize())) {
                player.sante -= bullet.getDegats();
                bullet.size = 0;
                // if (player.sante <= 0) {
                //     player.size = 0;
                // }
            }
        }
    }

    public void suppBulletPlayer() {
        // Supprimer les projectiles hors des limites
        Iterator<Bullet> it = playerBullets.iterator();
        while (it.hasNext()) {
            Bullet bullet = it.next();
            if (bullet.isOutOfBounds(1480, 840)|| bullet.getSize() == 0) {
                it.remove();
            }
        }       
    }

    public void suppBulletEnnemi() {
        // Supprimer les projectiles hors des limites
        Iterator<Bullet> it = enemyBullets.iterator();
        while (it.hasNext()) {
            Bullet bullet = it.next();
            if (bullet.isOutOfBounds(1480, 840)|| bullet.getSize() == 0) {
                it.remove();
            }
        }
    }

    public void addBulletPlayer(Bullet b) {
        playerBullets.add(b);
    }

    public void addBulletEnemy(Bullet b) {
        enemyBullets.add(b);
    }


    public ArrayList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public ArrayList<Bullet> getplayerBullets() {
        return playerBullets;
    }
    
}
