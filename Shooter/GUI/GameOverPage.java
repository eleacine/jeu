package Shooter.GUI;


import java.awt.*;
import javax.swing.*;

import Shooter.model.Game;

public class GameOverPage extends GameScene {
    //*page qui s’affiche si le niveau est perdu*
    
    public GameOverPage(Game game) {
        super(game);
        // setLayout(new GridLayout(3, 1));  
        drawGameOver();
        initButtons();
    }

    private void drawGameOver() {
        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(gameOverLabel);
    }

    public void initButtons() {
        add(createButton("Menu", "Menu"));
        // add(createButton("Replay", "Play"));
    }


}
