package Shooter.GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Shooter.model.Game;

public class GameScene extends JPanel {
    public Game game;

    public GameScene(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    // creates buttons and connects them to their page
    public JButton createButton(String text, String pageName) {
        JButton button = new JButton(text);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = game.cardLayout;
                JPanel panel = game.cardPanel;
                if (pageName.equals("Play")) {
                    game.begin = true;
                }
                layout.show(panel, pageName);
            }
        });
        return button;
    }

    // creates a button to exit the game
    public JButton createButtonExit(String text) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the entire window and exit the program
                System.exit(0);
            }
        });
        return button;
    }
}
