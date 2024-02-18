package Shooter.GUI;

import Shooter.Managers.ManagerCase;

import Shooter.model.Game;

import java.awt.Graphics;

import javax.swing.JPanel;
import Shooter.factory.*;
public class PlayPage extends GameScene  {
    //page principale oú se déroule le jeu, contient la représentation graphique du plateau
    
	private int[][] lvl;
	private ManagerCase tileManager;
	public Game game; 

	public PlayPage(Game game) {
		super(game);
		lvl=PlateauLevelLoader.loadPlayingBoard("C:\\Users\\Leono\\OneDrive\\Documentos\\UNI\\CS\\M\\jeu\\Shooter\\factory\\PlateauLevels.txt", 2);
		//lvl = LevelsPage.getLevelData();
		tileManager = new ManagerCase();
		add(createButton("Menu", "Menu"));
	}

	public void paintComponent(Graphics g) { // permet de visualiser ou creer les graphics
		super.paintComponents(g);
		this.game.gamePlateau.render_plateau(g); // ancien render pour gamePlateau
		this.game.gamePlateau.paintComponent(g);
	
		
	}
}
