package Shooter.DeplacementTest;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;

public class plateau extends JPanel{

    public manager m = new manager();
    
    public plateau() {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }
    
    public void update() {
        m.update();
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(m.p.x, m.p.y, m.p.size, m.p.size);
    }
    


    
}