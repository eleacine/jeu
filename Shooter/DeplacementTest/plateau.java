// package Shooter.DeplacementTest;

// import java.awt.Color;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.Polygon;
// import java.awt.geom.AffineTransform;
// import javax.swing.JPanel;

// public class Plateau extends JPanel {

//     public Manager m;

//     public Plateau(Player p) {
//         this.setBackground(Color.BLACK);
//         this.setFocusable(true);
//         m = new Manager(p, this);
//         this.addKeyListener(m);
//     }

//     public void update() {
//         repaint();
//         m.handleKeyPress();
//         m.update();
//         repaint();
//     }

//     public void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         g.setColor(Color.WHITE);

//         // Dessiner le cercle
//         // g.fillOval(m.player.x, m.player.y, m.player.size, m.player.size);

//         // Dessiner le triangle isocèle
//         int triangleSize = 35;
//         int extendedLength = 5; // Adjust this value to make the tip more noticeable

//         int[] triangleX = { 0, -triangleSize / 2, triangleSize / 2, 0 };
//         int[] triangleY = { triangleSize + extendedLength, 0, 0, triangleSize + extendedLength };

//         Polygon trianglePolygon = new Polygon(triangleY, triangleX, triangleY.length);

//         double triangleXPos = m.player.x + (m.player.size / 2) + Math.cos(m.player.direction) * (m.player.size / 2);
//         double triangleYPos = m.player.y + (m.player.size / 2) + Math.sin(m.player.direction) * (m.player.size / 2);

//         Graphics2D g2d = (Graphics2D) g;
//         AffineTransform at = AffineTransform.getTranslateInstance(triangleXPos, triangleYPos);
//         at.rotate(m.player.direction); // rotation du triangle
//         g2d.setTransform(at); //applique la transformation
//         g2d.fill(trianglePolygon); // dessine le triangle
//         // g2d.setTransform(new AffineTransform()); // Reset the transform

//     }
// }

package Shooter.DeplacementTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class plateau extends JPanel {

    public manager m;

    public plateau(player p) {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        m = new manager(p, this);
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

        // Dessiner le cercle et le triangle avec rotation
        drawCombinedShape((Graphics2D) g, m.player.x, m.player.y, m.player.size, m.player.direction);
    }

    // private void drawCombinedShape(Graphics2D g2d, int x, int y, int size, double direction) {
    //     // Dessiner le cercle (base du triangle)
    //     int circleRadius = size;
    //     g2d.fillOval(x, y, circleRadius, circleRadius);

    //     // Dessiner le triangle isocèle (pointe)
    //     int triangleSize = 35;
    //     int extendedLength = 5;

    //     int[] triangleX = { 0, -triangleSize / 2, triangleSize / 2, 0 };
    //     int[] triangleY = { triangleSize + extendedLength, 0, 0, triangleSize + extendedLength };

    //     Polygon trianglePolygon = new Polygon(triangleY, triangleX, triangleY.length);

    //     double triangleXPos = x + 170; // Centre du cercle
    //     double triangleYPos = y + 105; // Modifier cette ligne
    

    //     AffineTransform at = AffineTransform.getTranslateInstance(triangleXPos, triangleYPos);
    //     at.rotate(direction);
    //     g2d.setTransform(at);
    //     g2d.fill(trianglePolygon);
    //     g2d.setTransform(new AffineTransform()); // Réinitialiser la transformation
    // }

    private void drawCombinedShape(Graphics2D g2d, int x, int y, int size, double direction) {
        // Dessiner le cercle (base du triangle)
        int circleRadius = size;
        int circleX = x - circleRadius / 2; // Adjust the x-position to center the circle
        int circleY = y - circleRadius / 2; // Adjust the y-position to center the circle
        g2d.fillOval(circleX, circleY, circleRadius, circleRadius);
    
        // Dessiner le triangle isocèle (pointe)
        int triangleSize = 35;
        int extendedLength = 5;
    
        int[] triangleX = { 0, -triangleSize / 2, triangleSize / 2, 0 };
        int[] triangleY = { triangleSize + extendedLength, 0, 0, triangleSize + extendedLength };
    
        Polygon trianglePolygon = new Polygon(triangleY, triangleX, triangleY.length);
    
        double triangleXPos = x + 170; // Centre du cercle
        double triangleYPos = y + 105; // Adjust this line
    
        AffineTransform at = AffineTransform.getTranslateInstance(triangleXPos, triangleYPos);
        at.rotate(direction);
        g2d.setTransform(at);
        g2d.fill(trianglePolygon);
        g2d.setTransform(new AffineTransform()); // Réinitialiser la transformation
    }
    
}
