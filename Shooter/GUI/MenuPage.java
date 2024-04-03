package Shooter.GUI;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Shooter.model.Game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.*;



public class MenuPage extends GameScene {

    Font shooterFont =loadShooterFont();

    public MenuPage(Game g1) {
        super(g1);
        setPanelSize();
        initUI();
        loadBackgroundImage(1);
    }

    private void setPanelSize() {
        setMinimumSize(game.size_screen);
        setPreferredSize(game.size_screen);
        setMaximumSize(game.size_screen);
    }

    private void initUI() {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1; // Allow vertical centering

        // Add title
        JLabel titleLabel = new JLabel("Home Invasion");
        titleLabel.setFont(shooterFont);
        titleLabel.setForeground(TITLE_COLOR);
        add(titleLabel, gbc);

        // Add buttons
        JPanel buttonPanel = createButtonPanel();
        gbc.gridy = 1; // Position below the title
		gbc.weighty=80;
        add(buttonPanel, gbc);
    }

       


    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Set background color
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.insets.top = 10;
        gbc.insets.right = 15; // Add right padding between buttons
    
        // Add buttons to buttonPanel
        buttonPanel.add(createButton("Play", "Play"), gbc);
        
        gbc.gridx++; // Move to the next column
        buttonPanel.add(createButton("Creative", "Play"), gbc);
        
        gbc.gridx++; // Move to the next column
        buttonPanel.add(createButton("Settings", "Settings"), gbc);
        
        gbc.gridx++; // Move to the next column
        buttonPanel.add(createButton("Editing", "Editing"), gbc);
        
        gbc.gridx++; // Move to the next column
        gbc.insets.right = 0; // Remove right padding for the last button
        buttonPanel.add(createButtonExit("Quit"), gbc);
    
        return buttonPanel;
    }
    
    

}

