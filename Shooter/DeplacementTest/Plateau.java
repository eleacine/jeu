package Shooter.DeplacementTest;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class Plateau extends JPanel {

    public Manager manager;
    public EnnemiManager ennemiManager;
    public ProjectilesManager projectilesManager;
    public Player player;
    public MyMouseListener mouseListener;

    public Plateau(Player p) {
        this.player = p;    
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        manager = new Manager(p, this);
        ennemiManager = new EnnemiManager(p, this);
        projectilesManager = new ProjectilesManager(p, this);
        mouseListener = new MyMouseListener(p, new Crosshair(), projectilesManager);
        this.addKeyListener(manager);
        this.addMouseMotionListener(mouseListener);
        this.addMouseListener(mouseListener);
        
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
        mouseListener.getCrosshair().draw(g);
        g.setColor(Color.WHITE);
        g.fillOval((int) player.getX()-10, (int) player.getY()-10, player.getSize(), player.getSize());



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

        // int triangleSize = 35;
        // int extendedLength = 5; // Adjust this value to make the tip more noticeable

        // int[] triangleX = { 0, -triangleSize / 2, triangleSize / 2, 0 };
        // int[] triangleY = { triangleSize + extendedLength, extendedLength, extendedLength, triangleSize + extendedLength };

        

        // Polygon trianglePolygon = new Polygon(triangleY, triangleX, triangleY.length);

        // double triangleXPos = manager.player.x;
        // double triangleYPos = manager.player.y;

        // Graphics2D g2d = (Graphics2D) g;
        // AffineTransform at = AffineTransform.getTranslateInstance(triangleXPos, triangleYPos);

        // at.rotate(manager.player.direction); // rotation du triangle
        // g2d.setTransform(at); // applique la transformation
        // g2d.fill(trianglePolygon); // dessine le triangle
        // g2d.setTransform(new AffineTransform()); // Reset the transform

        // Dessiner les balles du joueur
        for (Bullet playerBullet : projectilesManager.getplayerBullets()) {
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
