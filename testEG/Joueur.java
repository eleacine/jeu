import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Joueur {
    private int x;
    private int y;

    public Joueur(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void deplacer(KeyEvent e) {
        int deplacement = 5;
        if (e.getKeyCode() == KeyEvent.VK_UP && y > 0) {
            y -= deplacement;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && y < 270) {
            y += deplacement;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && x > 0) {
            x -= deplacement;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && x < 370) {
            x += deplacement;
        }
    }

    public void dessiner(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, 30, 30);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}