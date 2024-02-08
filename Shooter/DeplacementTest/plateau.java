package Shooter.DeplacementTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Plateau extends JPanel {

    public Manager m;
    public Player p;

    public Plateau(Player p) {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.p = p;
        m = new Manager(p, this);
        this.addKeyListener(m);
         // Définir la taille préférée du plateau pour occuper toute la zone de dessin de la fenêtre
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         this.setPreferredSize(screenSize);
         this.setMaximumSize(screenSize);
      //   this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

         System.out.println("screensize :" + screenSize);
    }

    public void update() {
        m.handleKeyPress();
        m.update();
        repaint();
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        // Image du joueur jtest
        URL imageUrl = getClass().getResource("j_test.png");
        ImageIcon originalImage = new ImageIcon(imageUrl);
        int newWidth = getWidth() - 880;
        int newHeight = getHeight() - 80;
        Image resizedImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon joueurImage = new ImageIcon(resizedImage);

        double x = (getWidth() - joueurImage.getIconWidth()) / 2 + p.x;
        double y = (getHeight() - joueurImage.getIconHeight()) / 2 + p.y;
        

        Graphics2D g2d = (Graphics2D) g;

        
        AffineTransform at = AffineTransform.getTranslateInstance(x - joueurImage.getIconWidth() / 2, y - joueurImage.getIconHeight() / 2);
        at.rotate(p.direction, joueurImage.getIconWidth() / 2, joueurImage.getIconHeight() / 2);

        g2d.setTransform(at);

        g2d.drawImage(joueurImage.getImage(), 0, 0, newWidth, newHeight, this);
        g2d.setTransform(new AffineTransform());
     
        
    }
}
