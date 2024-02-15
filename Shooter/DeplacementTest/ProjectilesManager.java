package Shooter.DeplacementTest;

import java.util.ArrayList;
import java.util.Iterator;

public class ProjectilesManager {

    public Manager m;
    public EnnemiManager ennemiManager;
    public Plateau p;
    public Player player;
    public ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();
    public ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();

    public ProjectilesManager(Player player, Plateau p) {
        this.player = player;
        this.p = p;
        this.m = p.manager;
        ennemiManager = p.ennemiManager;
    }

    public void hitEnnemi() {
        for (Bullet bullet : playerBullets) {
            for (Ennemi ennemi : ennemiManager.ennemis) {
                if (ennemi.detectCollision(bullet.getX(), bullet.getY(), bullet.getSize())) {
                    ennemi.sante -= bullet.getDegats();
                    // System.out.println("Santé restante ennemi " + ennemi.id + " : " + ennemi.sante);
                    bullet.size = 0;
                    if (ennemi.sante <= 0) {
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
                if (player.sante <= 0) {
                    player.size = 0;
                }
            }
        }
    }

    public void suppBulletPlayer() {
        // Supprimer les projectiles hors des limites
        Iterator<Bullet> it = playerBullets.iterator();
        while (it.hasNext()) {
            Bullet bullet = it.next();
            if (bullet.isOutOfBounds(p.getWidth()+250, p.getHeight()+200)|| bullet.getSize() == 0) {
                it.remove();
            }
        }       
    }

    public void suppBulletEnnemi() {
        // Supprimer les projectiles hors des limites
        Iterator<Bullet> it = enemyBullets.iterator();
        while (it.hasNext()) {
            Bullet bullet = it.next();
            if (bullet.isOutOfBounds(p.getWidth()+250, p.getHeight()+200)|| bullet.getSize() == 0) {
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
