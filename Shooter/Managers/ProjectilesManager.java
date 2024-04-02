package Shooter.Managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Shooter.model.Enemy;
import Shooter.model.Personnage;
import Shooter.model.Player;
import Shooter.model.Bullet;
import Shooter.model.A4;



public class ProjectilesManager {

    protected PlayerManager m;
    protected EnnemiManager ennemiManager;
    protected Player player;
    protected ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();
    protected ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();
    protected List<Personnage> enemies;
    protected GameManager gameManager;


   

    
    public ProjectilesManager(Player player, GameManager gameManager ) {

    // private Timer explosionTimer;
    // private long explosionDelay = 1700;
    // private boolean isGrenadeActivated = false;
    // private BufferedImage[] explosionImage;
    // private int explosionFrameIndex = 0;
    // private Timer explosionAnimationTimer;
    // private long explosionAnimationDelay = 500;



        this.player = player;
        this.gameManager = gameManager;
        this.m = gameManager.getPlayerManager();
        this.ennemiManager = gameManager.getEnnemiManager();
    }

    public void hitEnnemi() {
        for (Bullet bullet : playerBullets) {
            for (Personnage perso : ennemiManager.getPerso_list()) {
                if (perso instanceof Enemy) {
                    Enemy ennemi = (Enemy) perso;
                    if (ennemi.detectCollision(bullet.getX(), bullet.getY(), bullet.getSize())) {
                        ennemi.infligerDegats(bullet.getDegats());
                        // System.out.println("Santé restante ennemi " + ennemi.id + " : " +
                        // ennemi.sante);
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
            // System.out.println("player size"+player.size);
            checkAndDestroyBulletOnWall(bullet);
            if (player.detectCollision(bullet.getX(), bullet.getY(), bullet.getSize()) && bullet.getSize() != 0) {
                // player.sante -= bullet.getDegats();
                player.infligerDegats(bullet.getDegats());
                bullet.size = 0;
                // if (player.sante <= 0) {
                // player.size = 0;
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

            // Vérifiez la collision avec le mur
            checkAndDestroyBulletOnWall(bullet);

            if (bullet.isOutOfBounds(1480, 840) || bullet.getSize() == 0
                    || bullet.getDistanceTraveled() >= m.getPlayer().getArmes().get(currentArme).getDistance()) {
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
             // Vérifiez la collision avec le mur
            
            if (bullet.isOutOfBounds(1480, 840)|| bullet.getSize() == 0) {
                it.remove();
            }
            
        }
    }

    public void grenadeDegat(List<Personnage> ennemis, A4 grenade) {
        if(grenade.getIsActivated()==true){

        
        for (Personnage personnage : enemies) {
            double distance = Math.sqrt(Math.pow(personnage.getX() - grenade.getX(), 2) + Math.pow(personnage.getY() - grenade.getY(), 2));
            if (distance <= 75) {
                personnage.infligerDegats(10);
            }
        }
    }
    }

/* 
    public void grenadeDegat(List<Personnage> ennemis, A4 grenade) {
        if(grenade.getIsActivated()==true){

        
        for (Personnage personnage : enemies) {
            double distance = Math.sqrt(Math.pow(personnage.getX() - grenade.getX(), 2) + Math.pow(personnage.getY() - grenade.getY(), 2));
            if (distance <= 75) {
                personnage.infligerDegats(10);
            }
        }
    }
    }

*/





   /* 
    public static void loadMineExplosion(){
        System.out.println("dans load a load");
        BufferedImage atlas=Enregistrement.getSpriteAtlas();
        explosionImage=new BufferedImage[1];
        
        explosionImage[0]= atlas.getSubimage(7*40, 2*40, 40, 40);
    }

    public void drawExplosion(int x, int y, Graphics g){
        System.out.println("draw explosion");
        loadMineExplosion();
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(explosionImage[0], x , y , 2*40, 2*40, null);
        g2d.dispose();
    }

=======
            // Vérifiez la collision avec le mur
>>>>>>> 291fb0c289e1225be6e6ae4c35556c582981f022

            if (bullet.isOutOfBounds(1480, 840) || bullet.getSize() == 0) {
                it.remove();
            }

        }
    }

    /*
     * public static void loadMineExplosion(){
     * System.out.println("dans load a load");
     * BufferedImage atlas=Enregistrement.getSpriteAtlas();
     * explosionImage=new BufferedImage[1];
     * 
     * explosionImage[0]= atlas.getSubimage(7*40, 2*40, 40, 40);
     * }
     * 
     * public void drawExplosion(int x, int y, Graphics g){
     * System.out.println("draw explosion");
     * loadMineExplosion();
     * Graphics2D g2d = (Graphics2D) g.create();
     * g2d.drawImage(explosionImage[0], x , y , 2*40, 2*40, null);
     * g2d.dispose();
     * }
     * 
     * 
     * public void HitMine (Graphics g){
     * this.g = g;
     * for (A3 mine : ennemiManager.getGameManager().getGamePlateau().pieges){
     * if (ennemiManager.getPlayer().detectCollision(mine.getX(), mine.getY(),
     * mine.getDimension())){
     * System.out.println("collision mine");
     * ennemiManager.getPlayer().infligerDegats(mine.getPower());
     * drawExplosion(mine.getX(), mine.getY(), g);
     * // mine.dimension = 0;s
     * mine.setDimension(0);
     * }
     * 
     * if (ennemiManager.getPerso_list().size() > 1){
     * for (Personnage perso : ennemiManager.getPerso_list()){
     * if(perso instanceof Enemy){
     * Enemy ennemi=(Enemy)perso;
     * if (ennemi.detectCollision(mine.getX(), mine.getY(), mine.getDimension())){
     * 
     * ennemi.infligerDegats(mine.getPower());
     * drawExplosion(mine.getX(), mine.getY(), g);
     * // mine.dimension = 0;
     * mine.setDimension(0);
     * }
     * }
     * }
     * }
     * }
     * }
     * 
     * 
     * public void suppMine(){
     * Iterator<A3> it = gameManager.getGamePlateau().pieges.iterator();
     * while (it.hasNext()) {
     * A3 mine = it.next();
     * if (mine.getDimension() == 0) {
     * it.remove();
     * }
     * }
     * }
     */
    private void checkAndDestroyBulletOnWall(Bullet bullet) {
        int currentXIndex = (int) (bullet.getX() / 40);
        int currentYIndex = (int) (bullet.getY() / 40);

        if (currentXIndex >= 0 && currentXIndex < gameManager.getGamePlateau().level_tab[0].length &&
                currentYIndex >= 0 && currentYIndex < gameManager.getGamePlateau().level_tab.length) {

            int caseID = gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex];
            int casetype = ManagerCase.getCaseType(caseID);
            // Vérifiez si la munition est sur une case murale
            if (casetype == ManagerCase.MUR || casetype == ManagerCase.MUR_CASSANT) {
                // Marquez la munition comme détruite
                bullet.setSize(0);
            }
        }
    }



    public void update() {
        suppBulletPlayer();
        suppBulletEnnemi();
        hitEnnemi();
        hitPlayer();
        // HitMine(g);
        // suppMine();
    }

    // Vérifie et met à jour la case si le projectile est sur une case de type 2
    // (obstacle)
    private void checkAndUpdateCase(Bullet bullet) {
        int currentXIndex = (int) (bullet.getX() / 40);
        int currentYIndex = (int) (bullet.getY() / 40);

        if (currentXIndex >= 0 && currentXIndex < gameManager.getGamePlateau().level_tab[0].length &&
                currentYIndex >= 0 && currentYIndex < gameManager.getGamePlateau().level_tab.length) {

            // Vérifier que detruitMur est vrai avant de mettre à jour la case
            int caseID = gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex];
            int casetype = ManagerCase.getCaseType(caseID);

            if (casetype == ManagerCase.MUR_CASSANT
                    && m.getPlayer().getArmes().get(m.getPlayer().getCurrentArme()).getDetruitMur()) {

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
