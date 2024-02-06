package Shooter.DeplacementTest;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;

public class plateau extends JPanel{

    public manager m ;
    
    public plateau(player p) {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        m = new manager(p, this);
        this.addKeyListener(m); 
    }
    
    public void update() {
        m.handleKeyPress();
        m.update();
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(m.player.x, m.player.y, m.player.size, m.player.size);
    }
    


    
}