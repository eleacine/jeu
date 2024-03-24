package Shooter.GUI;
import Shooter.model.Game;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameScene extends JPanel {
    public Game game;

    //Constantes pour les couleurs et fonts
    protected static final Color TITLE_COLOR = Color.WHITE;
    protected static final Font TITLE_FONT = new Font("Comic Sans MS", Font.BOLD, 40);

    protected static final Color BUTTON_COLOR = new Color(59, 89, 182);
    protected static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    protected static final Font BUTTON_FONT = new Font("Comic Sans MS", Font.BOLD, 20);

    public GameScene(Game game) {
        this.game = game;
        setBackground(Color.BLACK);
    }

    public Game getGame() {
        return game;
    }

    // Creation des boutons
    public JButton createButton(String text, String pageName) {
        JButton button = new JButton(text);
        styleButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = game.cardLayout;
                JPanel panel = game.cardPanel;
                if (pageName.equals("Play")) {
                    game.begin = true;
                }
                else if(pageName.equals( "Menu") && game.isBegin()){
                    game.reset();
                }
                layout.show(panel, pageName);
            }
        });
        return button;
    }

    // creates a button to exit the game
    public JButton createButtonExit(String text) {
        JButton button = new JButton(text);
        styleButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the entire window and exit the program
                System.exit(0);
            }
        });
        return button;
    }

    //fonction pour styliser les bouttons
    private void styleButton(JButton button) {
        button.setBackground(BUTTON_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFont(BUTTON_FONT);
        button.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    
}
