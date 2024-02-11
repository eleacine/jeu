package Shooter.DeplacementTest;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Test extends JFrame {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {

        Game game = new Game();

         // Obtenir la taille de l'écran principal
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

         int screenWidth = (int) screenSize.getWidth();
         int screenHeight = (int) screenSize.getHeight();

        // System.out.println(screenWidth + " " + screenHeight);
    
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(game.p);
        // System.out.println(game.p.getWidth() + " " + game.p.getHeight());
        this.setVisible(true);
        game.start();


    }
}
