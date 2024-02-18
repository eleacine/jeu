// package Shooter.DeplacementTest;

// import java.awt.*;
// import java.awt.geom.AffineTransform;
// import java.awt.geom.Ellipse2D;
// import java.net.URL;

// import javax.swing.ImageIcon;
// import javax.swing.JPanel;

// import Shooter.Managers.*;
// import Shooter.model.*;

// public class Plateau extends JPanel {

//     public PlayerManager manager;
//     public EnnemiManager ennemiManager;
//     public ProjectilesManager projectilesManager;
//     public Player player;
//     public MyMouseListener mouseListener;

//     public Plateau(Player p) {
//         this.player = p;
//         this.setBackground(Color.BLACK);
//         this.setFocusable(true);
//         manager = new PlayerManager(p, this);
//         ennemiManager = new EnnemiManager(p, this);
//         projectilesManager = new ProjectilesManager(p, this);
//         mouseListener = new MyMouseListener(p, new Crosshair(), projectilesManager);
//         this.addKeyListener(manager);
//         this.addMouseMotionListener(mouseListener);
//         this.addMouseListener(mouseListener);

//     }

//     public void update() {
//         manager.handleKeyPress();
//         ennemiManager.update();
//         ennemiManager.suppEnnemi();
//         manager.update();

//         projectilesManager.hitEnnemi();
//         projectilesManager.hitPlayer();
//         projectilesManager.suppBulletPlayer();
//         projectilesManager.suppBulletEnnemi();

//         repaint();
//     }

//     public void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         mouseListener.getCrosshair().draw(g);
//         g.setColor(Color.WHITE);
//         g.fillOval((int) player.getX() - 10, (int) player.getY() - 10, player.getSize(),
//                 player.getSize());

//         // // Dessiner le joueur
//         // URL imageUrl = getClass().getResource("j_test.png");
//         // ImageIcon originalImage = new ImageIcon(imageUrl);
//         // int newWidth = getWidth() - 800;
//         // int newHeight = getHeight() - 80;
//         // Image resizedImage = originalImage.getImage().getScaledInstance(newWidth,
//         // newHeight, Image.SCALE_SMOOTH);
//         // ImageIcon joueurImage = new ImageIcon(resizedImage);

//         // double x = (getWidth() - joueurImage.getIconWidth()) / 2 + m.player.x;
//         // double y = (getHeight() - joueurImage.getIconHeight()) / 2 + m.player.y;

//         // Graphics2D g2d = (Graphics2D) g;

//         // AffineTransform at = AffineTransform.getTranslateInstance(x -
//         // joueurImage.getIconWidth() / 2, y - joueurImage.getIconHeight() / 2);
//         // at.rotate(m.player.direction, joueurImage.getIconWidth() / 2,
//         // joueurImage.getIconHeight() / 2);

//         // g2d.setTransform(at);

//         // g2d.drawImage(joueurImage.getImage(), 0, 0, newWidth, newHeight, this);
//         // g2d.setTransform(new AffineTransform());

//         // Dessiner les balles du joueur
//         for (Bullet playerBullet : projectilesManager.getplayerBullets()) {
//             playerBullet.createBullet(g);
//         }

//         // Dessiner les balles des ennemis
//         for (Bullet enemyBullet : projectilesManager.getEnemyBullets()) {
//             enemyBullet.createBullet(g);
//         }

//         // Dessiner les ennemis
//         for (Enemy ennemi : ennemiManager.ennemis) {
//             g.setColor(Color.RED);
//             g.fillOval(ennemi.x, ennemi.y, ennemi.getSize(), ennemi.getSize());
//             drawDetectionRadius(g, ennemi);
//         }
//     }

//     // public void paintComponent(Graphics g) {
//     // super.paintComponent(g);
//     // mouseListener.getCrosshair().draw(g);

//     // URL imageUrl = getClass().getResource("j_test.png");
//     // ImageIcon originalImage = new ImageIcon(imageUrl);
//     // int playerSize = player.getSize();

//     // double scalingFactor = 4;
//     // int newWidth = (int) Math.max(playerSize * scalingFactor, 1); // Ensure
//     // non-zero width
//     // int newHeight = (int) Math.max(playerSize * scalingFactor, 1); // Ensure
//     // non-zero height

//     // Image resizedImage = originalImage.getImage().getScaledInstance(newWidth,
//     // newHeight, Image.SCALE_SMOOTH);
//     // ImageIcon joueurImage = new ImageIcon(resizedImage);

//     // double x = player.getX() - newWidth / 2;
//     // double y = player.getY() - newHeight / 2;

//     // Graphics2D g2d = (Graphics2D) g;

//     // // Save the original graphics context state
//     // AffineTransform originalTransform = g2d.getTransform();

//     // AffineTransform at = AffineTransform.getTranslateInstance(x, y);
//     // at.rotate(player.getDirection(), newWidth / 2, newHeight / 2);

//     // g2d.setTransform(at);

//     // g2d.drawImage(joueurImage.getImage(), 0, 0, newWidth, newHeight, this);

//     // // Restore the original graphics context state
//     // g2d.setTransform(originalTransform);
//     // // g2d.setTransform(new AffineTransform());

//     // for (Bullet playerBullet : projectilesManager.getplayerBullets()) {
//     // playerBullet.createBullet(g);
//     // }

//     // for (Bullet enemyBullet : projectilesManager.getEnemyBullets()) {
//     // enemyBullet.createBullet(g);
//     // }

//     // for (Ennemi ennemi : ennemiManager.ennemis) {
//     // g.setColor(Color.RED);
//     // g.fillOval(ennemi.x, ennemi.y, ennemi.getSize(), ennemi.getSize());
//     // }

//     // }

//     private void drawDetectionRadius(Graphics g, Enemy ennemi) {
//         if (ennemi instanceof EnemyMedium) {
//             // Convertir le centre de l'ennemi et le rayon de détection en coordonnées d'écran
//             int centerX = (int) ennemi.x;
//             int centerY = (int) ennemi.y;
//             int radius = ((EnemyMedium) ennemi).getDetectionRadius();
    
//             // Définir le niveau de transparence (0.0f complètement transparent, 1.0f complètement opaque)
//             float alpha = 0.5f;
    
//             // Créer une nouvelle couleur avec la transparence appropriée
//             Color transparentColor = new Color(ennemi.color.getRed(), ennemi.color.getGreen(), ennemi.color.getBlue(), (int) (alpha * 255));
    
//             // Dessiner le rayon de détection comme un cercle transparent
//             Graphics2D g2d = (Graphics2D) g.create();
//             g2d.setColor(transparentColor);
            
//             Ellipse2D.Double detectionCircle = new Ellipse2D.Double(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
//             g2d.fill(detectionCircle);
//             g2d.dispose();
//         }
//     }
    

// }
