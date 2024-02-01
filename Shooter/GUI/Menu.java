package Shooter.GUI;

import java.awt.*;
import javax.swing.*;

import Shooter.pasDeNom.Player;


public class Menu extends JFrame {

    private CardLayout cardLayout;
    private Player player;
    private JFrame frame;

    public Menu (Player player){
        this.player = player;
        this.frame = new JFrame();
        JLabel label = new JLabel("MENU");
        frame.add(label);
        frame.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);    
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the Entry window
            WelcomePage welcomePage = new WelcomePage("Shooter Game");

            // Add a WindowListener to the Entry window
            welcomePage.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // When the Entry window is closed, get the player and create the Main window
                    Player player = welcomePage.getPlayer();
                    new Menu(player);
                }
            });
        });
    }
    

}
