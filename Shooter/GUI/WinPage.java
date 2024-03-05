package Shooter.GUI;

import java.awt.*;
import javax.swing.*;

import Shooter.model.Game;

public class WinPage extends GameScene {
    //*page qui s’affiche si la partie est gagné*
    
    public WinPage(Game game) {
        super(game);
        // setLayout(new GridLayout(3, 1));  
        drawGameOver();
        initButtons();
    }

    private void drawGameOver() {
        JLabel winLabel = new JLabel("Partie gagnée", SwingConstants.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 30));
        winLabel.setForeground(Color.WHITE);
        add(winLabel);
    }

    public void initButtons() {
        add(createButton("Menu", "Menu"));
        // add(createButton("Replay", "Play"));
    }


}

