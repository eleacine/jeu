package Shooter.DeplacementTest;

import java.util.ArrayList;
import java.util.Iterator;

public class EnnemiManager {

    public Manager manager;
    public ArrayList<Ennemi> ennemis;
    public Plateau plateau;
    public Player player;

    private long lastShotTime;
    private long shootingInterval = 2000; // Période de tir en millisecondes

    public EnnemiManager(Player player, Plateau plateau) {
        this.player = player;
        this.plateau = plateau;
        this.manager = plateau.manager;
        ennemis = new ArrayList<Ennemi>();
        ennemis.add(new Ennemi(1, 100, 100));
        this.lastShotTime = System.currentTimeMillis();
    }

   
    // a faire en fonction du type d'ennemis ajuster la vitesse de tir
    public void update() {
        for (Ennemi ennemi : ennemis) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastShotTime > shootingInterval) {
                // Si le temps écoulé depuis le dernier tir est supérieur à la période de tir
                Bullet bullet = new Bullet(ennemi.x, ennemi.y, player.x, player.y, false, ennemi.power);
                plateau.projectilesManager.getEnemyBullets().add(bullet);
                lastShotTime = currentTime; // Met à jour le temps du dernier tir
            }
        }
    }

    public void suppEnnemi (){
        Iterator<Ennemi> it = ennemis.iterator();
        while (it.hasNext()) {
            Ennemi ennemi = it.next();
            if (ennemi.sante <= 0) {
                // System.out.println("Santé restante ennemi " + ennemi.id + " : " + ennemi.sante);
                it.remove();
            }
        }
    }

  






}
