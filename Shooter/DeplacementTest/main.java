package Shooter.DeplacementTest;

import javax.swing.JFrame;

public class main extends JFrame {

    public static void main(String[] args) {
        new main();
    }

    public main() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new plateau());
        this.setVisible(true);
    }
}
