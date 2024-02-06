// package Shooter.DeplacementTest;

// import java.awt.Color;
// import javax.swing.JPanel;
// import java.awt.Graphics;

// public class Plateau extends JPanel{

//     public Manager m ;

//     public Plateau(Player p) {
//         this.setBackground(Color.BLACK);
//         this.setFocusable(true);
//         m = new Manager(p, this);
//         this.addKeyListener(m); 
//     }

//     public void update() {
//         m.handleKeyPress();
//         m.update();
//         repaint();
//     }

//     public void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         g.setColor(Color.WHITE);
//         g.fillOval(m.player.x, m.player.y, m.player.size, m.player.size);
//     }

// }
package Shooter.DeplacementTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class Plateau extends JPanel {

    public Manager m;

    public Plateau(Player p) {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        m = new Manager(p, this);
        this.addKeyListener(m);
    }

    public void update() {
        repaint();
        m.handleKeyPress();
        m.update();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        // Dessiner le cercle
        // g.fillOval(m.player.x, m.player.y, m.player.size, m.player.size);

        // Dessiner le triangle isocèle
        int triangleSize = 35;
        int extendedLength = 5; // Adjust this value to make the tip more noticeable

        int[] triangleX = { 0, -triangleSize / 2, triangleSize / 2, 0 };
        int[] triangleY = { triangleSize + extendedLength, 0, 0, triangleSize + extendedLength };

        Polygon trianglePolygon = new Polygon(triangleX, triangleY, triangleX.length);

        double triangleXPos = m.player.x + (m.player.size / 2) + Math.cos(m.player.direction) * (m.player.size / 2);
        double triangleYPos = m.player.y + (m.player.size / 2) + Math.sin(m.player.direction) * (m.player.size / 2);

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform at = AffineTransform.getTranslateInstance(triangleXPos, triangleYPos);
        at.rotate(m.player.direction, 0, 0);
        g2d.setTransform(at);
        g2d.fill(trianglePolygon);
        g2d.setTransform(new AffineTransform()); // Reset the transform

    }
}
