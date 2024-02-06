package Shooter.GUI;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

import Shooter.model.Player;


public class MenuPage extends JFrame {

    private CardLayout cardLayout;
    private Player player;
    private JFrame frame;
    public MenuPage(Player player) {
        this.player = player;

        JPanel mainPanel = new JPanel(new GridBagLayout()) {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                URL imageUrl = getClass().getResource("../image/cible.png");
                System.out.println("URL de l'image : " + (imageUrl != null ? imageUrl.toExternalForm() : "null"));
        
                ImageIcon originalImage = new ImageIcon(imageUrl);
                int newWidth = getWidth() - 150;
                int newHeight = getHeight() - 150;
                Image resizedImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                ImageIcon backgroundImage = new ImageIcon(resizedImage);
        
                // Calculer la position pour centrer l'image
                int x = (getWidth() - newWidth) / 2;
                int y = (getHeight() - newHeight) / 2;
        
                g.drawImage(backgroundImage.getImage(), x, y, newWidth, newHeight, this);
            }
        
            {
                setBackground(Color.BLACK);  // Définir la couleur de fond noire dans l'initialiseur d'instance
            }
        };
        
        
        
        
    
        mainPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("MENU");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 20, 0); 
        mainPanel.add(titleLabel, gbc);

        JButton settingsButton = new JButton("Settings");
        JButton levelButton = new JButton("Levels");
        JButton playButton = new JButton("Play");
        JButton exitButton = new JButton("Quit");
        exitButton.addActionListener(e -> System.exit(0));


        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0); 
        mainPanel.add(settingsButton, gbc);

        gbc.gridy = 2;
        mainPanel.add(levelButton, gbc);

        gbc.gridy = 3;
        mainPanel.add(playButton, gbc);

        gbc.gridy = 4;
        mainPanel.add(exitButton, gbc);


        getContentPane().add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);    
        setResizable(false);
        setVisible(true);
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
                    new MenuPage(player);
                }
            });
        });
    }
    

}
