package Shooter.model;

import java.awt.*;
import javax.swing.*;

import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import Shooter.Managers.*;
import Shooter.factory.PlateauLevelLoader;

public class Plateau extends JPanel {
    public int[][] level_tab;
    public ManagerCase tile_manager;
    public Graphics plateau_graphic; // on utilise pour "enregistrer" notre image graphique puis pouvoir la modifier
                                     // dans le update

    public ArrayList<A3> pieges = new ArrayList<A3>();
    public ArrayList<A4> grenade = new ArrayList<A4>();

    public GameManager gameManager;
    // private static  int tailleCase;

    public Plateau() {
        this.level_tab = PlateauLevelLoader.loadPlayingBoard("Shooter/factory/PlateauLevels.txt", 0);
        // this.level_tab = PlateauLevelLoader.loadPlayingBoard("Shooter/factory/PlateauLevels.txt", 1);

        this.tile_manager = new ManagerCase();
        // this.tailleCase = tile_manager.getTailleCase();

    }

    public void render_plateau(Graphics g) {
        for (int y = 0; y < level_tab.length; y++) {
            for (int x = 0; x < level_tab[y].length; x++) {
                int id = level_tab[y][x];
                g.drawImage(tile_manager.getSprite(id), x * 40, y * 40, null);
            }
        }
        this.plateau_graphic = g;
    }

    public void update_pleateau(int x, int y, int type_case) {
        plateau_graphic.drawImage(tile_manager.getSprite(type_case), x * 40, y * 40, null);
    }

    // public void render_plateau(Graphics g) {
    //     for (int y = 0; y < level_tab.length; y++) {
    //         for (int x = 0; x < level_tab[y].length; x++) {
    //             int id = level_tab[y][x];
    //             g.drawImage(tile_manager.getSprite(id), x * tailleCase, y * tailleCase, null);
    //         }
    //     }
    //     this.plateau_graphic = g;
    // }

    // public void update_pleateau(int x, int y, int type_case) {
    //     plateau_graphic.drawImage(tile_manager.getSprite(type_case), x * tailleCase, y * tailleCase, null);
    // }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.setColor(Color.DARK_GRAY);
        // g.fillOval((int) gameManager.getPlayer().getX(), (int)
        // gameManager.getPlayer().getY(),
        // gameManager.getPlayer().getSize(),
        // gameManager.getPlayer().getSize());

        // Create a new Graphics2D object for gameManager.getPlayer()
        Graphics2D gPlayer = (Graphics2D) g.create();
        drawPlayerMovement(gPlayer);

        // Dessiner les balles du joueur
        for (Bullet playerBullet : gameManager.getProjectilesManager().getplayerBullets()) {
            playerBullet.createBullet(g);
        }

        // Dessiner les balles des ennemis
        for (Bullet enemyBullet : gameManager.getProjectilesManager().getEnemyBullets()) {
            enemyBullet.createBullet(g);
        }

        // Dessiner les ennemis
        for (Personnage perso : gameManager.getEnnemiManager().getPerso_list()) {
            if (perso instanceof Enemy) {
                Enemy ennemi = (Enemy) perso;

                if (ennemi instanceof Gardien) {
                    ((Gardien) ennemi).dessinerVision(g);

                } else {
                    drawDetectionRadius(g, ennemi);
                }
                ennemi.drawEnemy(g);
                ennemi.drawBarVie(g);
            }
        }

        // Dessiner les pièges
        for (A3 piege : pieges) {
            piege.draw(g,gameManager.getPlayer().getX(),gameManager.getPlayer().getY());
        }
        for(A4 grenade : grenade){
            grenade.draw(g,gameManager.getPersoList(),gameManager.getPlayer().getX(),gameManager.getPlayer().getY());
        }

        // print arme et nombre munitions
        int currentArme = gameManager.getPlayer().currentArme;
        Armes armeCourante = gameManager.getPlayer().armes.get(currentArme);

        g.setColor(Color.BLACK);
        g.fillRect(1300, 1, 125, 100);

        g.setColor(Color.WHITE);
        if (currentArme >= 0 && currentArme < gameManager.getPlayer().armes.size()) {
            g.drawString("type:" + armeCourante.nom, 1325, 30);
            g.drawString("munitions:" + armeCourante.munition, 1325, 50);
        }
        g.setColor(armeCourante.color);
        int centerX = (int) (gameManager.getPlayer().getX() + gameManager.getPlayer().getSize() / 2
                - armeCourante.distance * 2 / 2);
        int centerY = (int) (gameManager.getPlayer().getY() + gameManager.getPlayer().getSize() / 2
                - armeCourante.distance * 2 / 2);
        g.drawOval(centerX, centerY, armeCourante.distance * 2, armeCourante.distance * 2);

        // Dessin crosshair
        gameManager.getMyMouseListener().getCrosshair().draw(g);

    }

    private void drawPlayerMovement(Graphics2D g) {

        double scale = 5;
        int newWidth = (int) (gameManager.getPlayer().getSize() * scale);
        int newHeight = (int) (gameManager.getPlayer().getSize() * scale);

        double x = gameManager.getPlayer().getX();
        double y = gameManager.getPlayer().getY();

        AffineTransform at = new AffineTransform();
        at.translate(x - newWidth / 2, y - newHeight / 2);
        at.rotate(gameManager.getPlayer().getDirection(), newWidth / 2, newHeight / 2);

        g.drawImage(gameManager.getPlayer().getSprite().getImage(), at, this);
    }

    private void drawDetectionRadius(Graphics g, Enemy ennemi) {
        // if (ennemi instanceof EnemyMedium) {
        // Convertir le centre de l'ennemi et le rayon de détection en coordonnées
        // d'écran
        int centerX = (int) ennemi.x;
        int centerY = (int) ennemi.y;
        int radius = (ennemi).getDetectionRadius();

        // Définir le niveau de transparence (0.0f complètement transparent, 1.0f
        // complètement opaque)
        float alpha = 0.4f;

        // Créer une nouvelle couleur avec la transparence appropriée
        Color transparentColor = new Color(ennemi.color.getRed(), ennemi.color.getGreen(), ennemi.color.getBlue(),
                (int) (alpha * 255));

        // Dessiner le rayon de détection comme un cercle transparent
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(transparentColor);

        Ellipse2D.Double detectionCircle = new Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius,
                2 * radius);
        g2d.fill(detectionCircle);
        g2d.dispose();
        // }
    }

    public void reset() {
        pieges.clear();
        grenade.clear();
        this.level_tab = PlateauLevelLoader.loadPlayingBoard("Shooter/factory/PlateauLevels.txt", gameManager.getPlayer().getLevel());
    }

}
