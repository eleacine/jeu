import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Plateau extends JFrame {
    private Gardien gardien;
    private Joueur joueur;

    public Plateau() {
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gardien = new Gardien(50, getHeight() / 2 - 15);
        joueur = new Joueur(200, getHeight() / 2 - 15);

        setContentPane(new PlateauPanel());

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                joueur.deplacer(e);
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setFocusable(true);

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gardien.deplacer();
                gardien.suivreJoueur(joueur);
                gardien.deplacerVision(joueur);
                repaint();
            }
        });
        timer.start();
    }

    class PlateauPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            dessinerPlateau(g);
            gardien.dessiner(g);
            gardien.dessinerVision(g);
            joueur.dessiner(g);
        }

        private void dessinerPlateau(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Plateau plateau = new Plateau();
                plateau.setVisible(true);
            }
        });
    }
}

class Joueur {
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

class Gardien {
    private int x;
    private int y;
    private int visionAngle;

    public Gardien(int x, int y) {
        this.x = x;
        this.y = y;
        this.visionAngle = 340; // Angle initial pour la vision périphérique
    }

    public void deplacer() {
        x += 2; // Réduire la vitesse de déplacement de l'ennemi
        if (x > 400) {
            x = -30;
        }
    }

    public void suivreJoueur(Joueur joueur) {
        int deltaX = joueur.getX() - x;
        int deltaY = joueur.getY() - y;

        double angleToPlayer = Math.atan2(deltaY, deltaX);
        x += Math.cos(angleToPlayer) * 2; // Ajuster la vitesse de suivi
        y += Math.sin(angleToPlayer) * 2;
    }

    public void deplacerVision(Joueur joueur) {
        int deltaX = joueur.getX() - x;
        int deltaY = joueur.getY() - y;
        visionAngle = (int) Math.toDegrees(Math.atan2(deltaY, deltaX));

        if (visionAngle < 0) {
            visionAngle += 360;
        }
    }

    public void dessiner(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 30, 30);
    }

    public void dessinerVision(Graphics g) {
        g.setColor(new Color(255, 255, 255, 100));
        g.fillArc(x - 50, y - 50, 130, 130, visionAngle, 30);
    }
}
