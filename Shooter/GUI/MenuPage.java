package Shooter.GUI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Shooter.model.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuPage extends GameScene {

    Font shooterFont =loadShooterFont();

    public MenuPage(Game g1) {
        super(g1);
        setPanelSize();
        initUI();
    }

    private void setPanelSize() {
        setMinimumSize(game.size_screen);
        setPreferredSize(game.size_screen);
        setMaximumSize(game.size_screen);
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1; // Allow vertical centering

        // Add title
        JLabel titleLabel = new JLabel("SHOOTER GAME");
        titleLabel.setFont(shooterFont.deriveFont(30));
        titleLabel.setForeground(TITLE_COLOR);
        add(titleLabel, gbc);

        // Add buttons
        JPanel buttonPanel = createButtonPanel();
        gbc.gridy = 1; // Position below the title
		gbc.weighty=3;
        add(buttonPanel, gbc);
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
        // En cas d'erreur :  police par défaut
        return new Font("SansSerif", Font.PLAIN, 14);
    }
}

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.BLACK); // Set background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 10;
        gbc.ipady = 10;
        gbc.insets.top = 10;

        // Add buttons to buttonPanel
        buttonPanel.add(createButton("Play", "Play"), gbc);
        gbc.gridy++;
        buttonPanel.add(createButton("Settings", "Settings"), gbc);
        gbc.gridy++;
        buttonPanel.add(createButton("Editing", "Editing"), gbc);
        gbc.gridy++;
        buttonPanel.add(createButtonExit("Quit"), gbc);

        return buttonPanel;
    }

}
