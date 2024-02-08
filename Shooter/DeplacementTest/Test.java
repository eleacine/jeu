package Shooter.DeplacementTest;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;


public class Test extends JFrame {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
     
        Player player = new Player();
        Plateau plateau = new Plateau(player);
        Manager manager = new Manager(player, plateau);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(plateau);
        this.setVisible(true);


    }
}
