package Shooter.DeplacementTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.Iterator;

public class Plateau extends JPanel {

    public Manager manager;
    public EnnemiManager ennemiManager;
    public ProjectilesManager projectilesManager;

    public Plateau(Player p) {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        manager = new Manager(p, this);
        ennemiManager = new EnnemiManager(p, this);
        projectilesManager = new ProjectilesManager(p, this);
        this.addKeyListener(manager);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        System.out.println(screenWidth + " " + screenHeight);
        this.setSize(screenWidth, screenHeight);
    }

    public void update() {
        manager.handleKeyPress();
        ennemiManager.update();
        ennemiManager.suppEnnemi();
        manager.update();

        projectilesManager.hitEnnemi();
        projectilesManager.hitPlayer();
        projectilesManager.suppBulletPlayer();
        projectilesManager.suppBulletEnnemi();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        // // Dessiner le joueur
        // URL imageUrl = getClass().getResource("j_test.png");
        // ImageIcon originalImage = new ImageIcon(imageUrl);
        // int newWidth = getWidth() - 800;
        // int newHeight = getHeight() - 80;
        // Image resizedImage = originalImage.getImage().getScaledInstance(newWidth,
        // newHeight, Image.SCALE_SMOOTH);
        // ImageIcon joueurImage = new ImageIcon(resizedImage);

        // double x = (getWidth() - joueurImage.getIconWidth()) / 2 + m.player.x;
        // double y = (getHeight() - joueurImage.getIconHeight()) / 2 + m.player.y;

        // Graphics2D g2d = (Graphics2D) g;

        // AffineTransform at = AffineTransform.getTranslateInstance(x -
        // joueurImage.getIconWidth() / 2, y - joueurImage.getIconHeight() / 2);
        // at.rotate(m.player.direction, joueurImage.getIconWidth() / 2,
        // joueurImage.getIconHeight() / 2);

        // g2d.setTransform(at);

        // g2d.drawImage(joueurImage.getImage(), 0, 0, newWidth, newHeight, this);
        // g2d.setTransform(new AffineTransform());

        // Dessiner le triangle isocèle
        int triangleSize = 35;
        int extendedLength = 5; // Adjust this value to make the tip more noticeable

        int[] triangleX = { 0, -triangleSize / 2, triangleSize / 2, 0 };
        int[] triangleY = { triangleSize + extendedLength, 0, 0, triangleSize + extendedLength };

        Polygon trianglePolygon = new Polygon(triangleY, triangleX, triangleY.length);

        double triangleXPos = manager.player.x;
        double triangleYPos = manager.player.y;

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = AffineTransform.getTranslateInstance(triangleXPos, triangleYPos);

        at.rotate(manager.player.direction); // rotation du triangle
        g2d.setTransform(at); // applique la transformation
        g2d.fill(trianglePolygon); // dessine le triangle
        g2d.setTransform(new AffineTransform()); // Reset the transform

        // Dessiner les balles du joueur
        for (Bullet playerBullet : projectilesManager.getPlayerBullets()) {
            playerBullet.createBullet(g);
        }

        // Dessiner les balles des ennemis
        for (Bullet enemyBullet : projectilesManager.getEnemyBullets()) {
            enemyBullet.createBullet(g);
        }

        // Dessiner les ennemis
        for (Ennemi ennemi : ennemiManager.ennemis) {
            g.setColor(Color.RED);
            g.fillOval(ennemi.x, ennemi.y, ennemi.getSize(), ennemi.getSize());
        }
    }
}
