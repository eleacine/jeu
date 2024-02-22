package Shooter.Managers;

import java.util.ArrayList;
import java.util.Iterator;

import Shooter.model.Enemy;
import Shooter.model.Player;
import Shooter.model.A3;
import Shooter.model.Bullet;

public class ProjectilesManager {

    protected PlayerManager m;
    protected EnnemiManager ennemiManager;
    protected Player player;
    protected ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();
    protected ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();
    protected GameManager gameManager;

    
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
                    ennemi.infligerDegats(bullet.getDegats());
                    bullet.size = 0;
                    if (ennemi.getSante() <= 0) {
                        ennemi.setSize(0);
                    }
                }
            }
        }

    }

    public void hitPlayer() {
        for (Bullet bullet : enemyBullets) {
         //   System.out.println("player size"+player.size);
            if (player.detectCollision(bullet.getX(), bullet.getY(), bullet.getSize())) {
                // player.sante -= bullet.getDegats();
                player.infligerDegats(bullet.getDegats());
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
            int currentArme = player.currentArme;
            
            if (bullet.isOutOfBounds(1480, 840)|| bullet.getSize() == 0 || bullet.getDistanceTraveled()>m.getPlayer().armes.get(currentArme).distance) {
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

    public void hitMine (){
        for (A3 mine : gameManager.getGamePlateau().pieges){
            if (player.detectCollision(mine.x, mine.y, mine.dimension)){
                player.infligerDegats(mine.power);
                mine.dimension = 0;
            }

            if (ennemiManager.ennemis.size() > 0){
                for (Enemy ennemi : ennemiManager.ennemis){
                    if (ennemi.detectCollision(mine.x, mine.y, mine.dimension)){
                        ennemi.infligerDegats(mine.power);
                        mine.dimension = 0;
                    }
                }
            }
        }
    }



    public void suppMine(){
        Iterator<A3> it = gameManager.getGamePlateau().pieges.iterator();
        while (it.hasNext()) {
            A3 mine = it.next();
            if (mine.dimension == 0) {
                it.remove();
            }
        }
    }

    public void update() {
        hitEnnemi();
        hitPlayer();
        suppBulletPlayer();
        suppBulletEnnemi();
        hitMine();
        suppMine();
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
