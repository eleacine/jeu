package Shooter.DeplacementTest;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Test extends JFrame {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        player player = new player();
        plateau plateau = new plateau(player);
        manager manager = new manager(player, plateau);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(plateau);
        this.setVisible(true);


    }
}
