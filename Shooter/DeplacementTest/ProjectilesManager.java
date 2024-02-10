package Shooter.DeplacementTest;

import java.util.ArrayList;
import java.util.Iterator;

public class ProjectilesManager {

    public Manager m;
    public EnnemiManager ennemiManager;
    public Plateau p;
    public Player player;
    public ArrayList<Bullet> enemybullets = new ArrayList<Bullet>();
    public ArrayList<Bullet> playerbullets = new ArrayList<Bullet>();

    public ProjectilesManager(Player player, Plateau p) {
        this.player = player;
        this.p = p;
        this.m = p.manager;
        ennemiManager = p.ennemiManager;
    }

    public void hitEnnemi() {
        for (Bullet bullet : playerbullets) {
            for (Ennemi ennemi : ennemiManager.ennemis) {
                if (ennemi.detectCollision(bullet.getX(), bullet.getY(), bullet.getSize())) {
                    ennemi.sante -= bullet.getDegat();
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
        for (Bullet bullet : enemybullets) {
            if (player.detectCollision(bullet.getX(), bullet.getY(), bullet.getSize())) {
                player.sante -= bullet.getDegat();
                // System.out.println("Santé restante : " + player.sante);
                bullet.size = 0;
            }
        }
    }

    public void suppBulletPlayer() {
        // Supprimer les projectiles hors des limites
        Iterator<Bullet> it = playerbullets.iterator();
        while (it.hasNext()) {
            Bullet bullet = it.next();
            System.out.println(p.getWidth() + " " + p.getHeight() );
            if (bullet.isOutOfBounds(p.getWidth()+250, p.getHeight()+200)|| bullet.getSize() == 0) {
                it.remove();
            }
        }       
    }

    public void suppBulletEnnemi() {
        // Supprimer les projectiles hors des limites
        Iterator<Bullet> it = enemybullets.iterator();
        while (it.hasNext()) {
            Bullet bullet = it.next();
            if (bullet.isOutOfBounds(p.getWidth()+250, p.getHeight()+200)|| bullet.getSize() == 0) {
                it.remove();
            }
        }
    }
    

    public ArrayList<Bullet> getEnemyBullets() {
        return enemybullets;
    }

    public ArrayList<Bullet> getPlayerBullets() {
        return playerbullets;
    }
    
}
