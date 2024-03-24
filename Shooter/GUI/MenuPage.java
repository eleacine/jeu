package Shooter.GUI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Shooter.model.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class MenuPage extends GameScene {

    

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
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(TITLE_COLOR);
        add(titleLabel, gbc);

        // Add buttons
        JPanel buttonPanel = createButtonPanel();
        gbc.gridy = 1; // Position below the title
		gbc.weighty=3;
        add(buttonPanel, gbc);
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
