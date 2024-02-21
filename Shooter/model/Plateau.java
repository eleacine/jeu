package Shooter.model;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import Shooter.Managers.*;
import Shooter.factory.PlateauLevelLoader;


public class Plateau extends JPanel {
    public int[][] level_tab;
    public ManagerCase  tile_manager;
    public Graphics plateau_graphic; //on utilise pour "enregistrer" notre image graphique puis pouvoir la modifier dans le update

    public ArrayList<A3> pieges = new ArrayList<A3>();

    public GameManager gameManager;

    public Plateau() {
        this.level_tab = PlateauLevelLoader.loadPlayingBoard("Shooter/factory/PlateauLevels.txt", 0);
        this.tile_manager = new ManagerCase();
    }
    

    public void render_plateau(Graphics g) {
        for (int y = 0; y < level_tab.length; y++) {
            for (int x = 0; x < level_tab[y].length; x++) {
                int id = level_tab[y][x];
                g.drawImage(tile_manager.getSprite(id), x * 50, y * 50, null);
            }
        }
        this.plateau_graphic = g;
    }

    public void update_pleateau(int x, int y, int type_case) {
        plateau_graphic.drawImage(tile_manager.getSprite(type_case), x * 50, y * 50, null);
    }

         
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameManager.getMyMouseListener().getCrosshair().draw(g);
        g.setColor(Color.DARK_GRAY);
        g.fillOval((int) gameManager.getPlayer().getX(), (int) gameManager.getPlayer().getY(), gameManager.getPlayer().getSize(),
        gameManager.getPlayer().getSize());

        /* 
        for (Armes arme : gameManager.getPlayer().armes) {
            g.fillOval(gameManager.getPlayer().x, gameManager.getPlayer().y, arme.distance, arme.distance);
            //drawDetectionRadius(g, arme);
            //ennemi.drawBarVie(g);
        }*/

        // Dessiner les balles du joueur
        for (Bullet playerBullet : gameManager.getProjectilesManager().getplayerBullets()) {
            playerBullet.createBullet(g);
        }

        // Dessiner les balles des ennemis
        for (Bullet enemyBullet : gameManager.getProjectilesManager().getEnemyBullets()) {
            enemyBullet.createBullet(g);
        }

        // Dessiner les ennemis
        for (Enemy ennemi : gameManager.getEnnemiManager().ennemis) {
            g.setColor(Color.BLUE);
            g.fillOval(ennemi.x, ennemi.y, ennemi.getSize(), ennemi.getSize());
            drawDetectionRadius(g, ennemi);
            ennemi.drawBarVie(g);
        }

        // Dessiner les pièges
        for (A3 piege : pieges) {
            piege.draw(piege.x, piege.y, g);
        }
        //print arme et nombre munitions
        g.setColor(Color.BLACK);
        g.fillRect(1350,1,100,100);
        
    }

    private void drawDetectionRadius(Graphics g, Enemy ennemi) {
        //if (ennemi instanceof EnemyMedium) {
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
        //}
    }

}
