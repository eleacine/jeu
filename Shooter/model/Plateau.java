package Shooter.model;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;

import Shooter.GUI.LevelsPage;
import Shooter.Managers.*;

public class Plateau extends JPanel {
    protected int[][] level_tab;
    protected ManagerCase tile_manager;
    public Graphics plateau_graphic; // on utilise pour "enregistrer" notre image graphique puis pouvoir la modifier
                                     // dans le update
    public PlayerManager playerManager;
    public EnnemiManager ennemiManager;
    public ProjectilesManager projectilesManager;
    public Player player;
    public MyMouseListener mouseListener;

    public Plateau() {

        // this.level_tab = LevelsPage.getLevelData();
        // this.player = new Player(null);

        // tile_manager = new ManagerCase();
        // playerManager = new PlayerManager(player, this);
        // ennemiManager = new EnnemiManager(player, this);
        // projectilesManager = new ProjectilesManager(player, this);
        // mouseListener = new MyMouseListener(player, new Crosshair(),
        // projectilesManager);

        // this.addKeyListener(playerManager);
        // this.addMouseMotionListener(mouseListener);
        // this.addMouseListener(mouseListener);

    }

    public Plateau(Player player, PlayerManager playerManager, MyMouseListener mouseListener,
            ProjectilesManager projectilesManager) {
        this.level_tab = LevelsPage.getLevelData();
        this.player = player;

        tile_manager = new ManagerCase();
        this.playerManager = playerManager;
        ennemiManager = new EnnemiManager(player, this);
        this.projectilesManager = projectilesManager;
        this.mouseListener = mouseListener;
    }

    public void render_plateau(Graphics g) {
        for (int y = 0; y < level_tab.length; y++) {
            for (int x = 0; x < level_tab[y].length; x++) {
                int id = level_tab[y][x];
                g.drawImage(tile_manager.getSprite(id), x * 50, y * 50, null);
            }
        }
        this.plateau_graphic = g;
        // drawButtons(g);
    }

    public void update_pleateau(int x, int y, int type_case) {
        plateau_graphic.drawImage(tile_manager.getSprite(type_case), x * 50, y * 50, null);
    }

    public void update() {
        playerManager.handleKeyPress();
        ennemiManager.update();
        ennemiManager.suppEnnemi();
        playerManager.update();

        projectilesManager.hitEnnemi();
        projectilesManager.hitPlayer();
        projectilesManager.suppBulletPlayer();
        projectilesManager.suppBulletEnnemi();

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        mouseListener.getCrosshair().draw(g);
        g.setColor(Color.DARK_GRAY);
        g.fillOval((int) player.getX(), (int) player.getY(), player.getSize(),
                player.getSize());

        // System.out.println(player.getX() + " " + player.getY());

        // Dessiner les balles du joueur
        for (Bullet playerBullet : projectilesManager.getplayerBullets()) {
            playerBullet.createBullet(g);
        }

        // Dessiner les balles des ennemis
        for (Bullet enemyBullet : projectilesManager.getEnemyBullets()) {
            enemyBullet.createBullet(g);
        }

        // Dessiner les ennemis
        for (Enemy ennemi : ennemiManager.ennemis) {
            g.setColor(Color.BLUE);
            g.fillOval(ennemi.x, ennemi.y, ennemi.getSize(), ennemi.getSize());
            drawDetectionRadius(g, ennemi);
            ennemi.drawBarVie(g);
        }
    }

    private void drawDetectionRadius(Graphics g, Enemy ennemi) {
        if (ennemi instanceof EnemyMedium) {
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
        }
    }

}
