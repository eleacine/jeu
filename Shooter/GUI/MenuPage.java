package Shooter.GUI;
import Shooter.model.Game;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.awt.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class MenuPage extends GameScene {

    Font shooterFont = loadShooterFont();
    private Image backgroundImage;

    public MenuPage(Game g1) {
        super(g1);
        setPanelSize();
        initUI();
        //loadBackgroundImage(); // Load the background image
    }

    private void setPanelSize() {
        setMinimumSize(game.size_screen);
        setPreferredSize(game.size_screen);
        setMaximumSize(game.size_screen);
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        // Set background color if image fails to load or while it's loading
        setBackground(Color.BLACK);
    }

    /*private void loadBackgroundImage() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("Shooter/images/background.jpg");
            if (inputStream != null) {
                backgroundImage = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fail");
        }
    }*/

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private Font loadShooterFont() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("../res/shooter.ttf");
            if (fontStream == null) {
                throw new FileNotFoundException("Fichier de police introuvable.");
            }
            return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(130f);
        } catch (Exception e) {
            e.printStackTrace();
            // In case of an error, return a default font
            return new Font("SansSerif", Font.PLAIN, 14);
        }
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(0, 0, 0, 0)); // Set transparent background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.insets.top = 10;

        // Add buttons to buttonPanel
        buttonPanel.add(createButton("Play", "Play"), gbc);
        gbc.gridy++;
        buttonPanel.add(createButton("Creative", "Play"), gbc);
        gbc.gridy++;
        buttonPanel.add(createButton("Settings", "Settings"), gbc);
        gbc.gridy++;
        buttonPanel.add(createButton("Editing", "Editing"), gbc);
        gbc.gridy++;
        buttonPanel.add(createButtonExit("Quit"), gbc);

        return buttonPanel;
    }

    // Other methods...

}
