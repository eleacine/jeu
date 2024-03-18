package Shooter.Managers;

import java.util.ArrayList;
import java.util.Iterator;

import Shooter.model.Enemy;
import Shooter.model.Personnage;
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
            for (Personnage perso : ennemiManager.getPerso_list()) {
                if(perso instanceof Enemy){
                    Enemy ennemi=(Enemy)perso;
                    if (ennemi.detectCollision(bullet.getX(), bullet.getY(), bullet.getSize())) {
                        ennemi.infligerDegats(bullet.getDegats());
                        // System.out.println("Santé restante ennemi " + ennemi.id + " : " + ennemi.sante);
                        bullet.size = 0;
                        if (ennemi.getSante() <= 0) {
                            // System.err.println("ennemi mort");
                            ennemi.setSize(0);
                        }
                    }
                }
            }
        }

    }

    public void hitPlayer() {
        for (Bullet bullet : enemyBullets) {
         //   System.out.println("player size"+player.size);
            if (player.detectCollision(bullet.getX(), bullet.getY(), bullet.getSize()) && bullet.getSize() != 0){
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
            int currentArme = player.getCurrentArme();

            if (bullet.isOutOfBounds(1480, 840)|| bullet.getSize() == 0 || bullet.getDistanceTraveled()>= m.getPlayer().getArmes().get(currentArme).getDistance()) {
                it.remove();
            }
            checkAndUpdateCase(bullet);
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
            if (player.detectCollision(mine.getX(), mine.getY(), mine.getDimension())){
                player.infligerDegats(mine.getPower());
                // mine.dimension = 0;
                mine.setDimension(0);
            }

            if (ennemiManager.getPerso_list().size() > 1){
                for (Personnage perso : ennemiManager.getPerso_list()){
                    if(perso instanceof Enemy){
                        Enemy ennemi=(Enemy)perso;
                        if (ennemi.detectCollision(mine.getX(), mine.getY(), mine.getDimension())){
                            ennemi.infligerDegats(mine.getPower());
                            // mine.dimension = 0;
                            mine.setDimension(0);
                        }
                    }
                }
            }
        }
    }



    public void suppMine(){
        Iterator<A3> it = gameManager.getGamePlateau().pieges.iterator();
        while (it.hasNext()) {
            A3 mine = it.next();
            if (mine.getDimension() == 0) {
                it.remove();
            }
        }
    }

    public void update() {
        suppBulletPlayer();
        suppBulletEnnemi();
        hitEnnemi();
        hitPlayer();
        hitMine();
        suppMine();
    }

    // Vérifie et met à jour la case si le projectile est sur une case de type 2 (obstacle)
    private void checkAndUpdateCase(Bullet bullet) {
        int currentXIndex = (int) (bullet.getX() / 40);
        int currentYIndex = (int) (bullet.getY() / 40);

        if (currentXIndex >= 0 && currentXIndex < gameManager.getGamePlateau().level_tab[0].length &&
        currentYIndex >= 0 && currentYIndex < gameManager.getGamePlateau().level_tab.length) {

        // Vérifier que detruitMur est vrai avant de mettre à jour la case
        if ((gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex] == ManagerCase.MUR_C_BAS||gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex] == ManagerCase.MUR_C_HAUT||gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex] == ManagerCase.MUR_C_DROIT||gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex] == ManagerCase.MUR_C_GAUCHE) && m.getPlayer().getArmes().get(m.getPlayer().getCurrentArme()).getDetruitMur()) {

            // La case est de type 2 (obstacle), la changer en type 1 (marche)
            gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex] = ManagerCase.SOL;
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
