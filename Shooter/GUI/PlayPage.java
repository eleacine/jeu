package Shooter.GUI;

import Shooter.model.Game;
import Shooter.model.ManagerCase;
import Shooter.model.MyButton;
import static Shooter.model.GameStates.*;

import java.awt.Graphics;

public class PlayPage extends GameScene implements SceneMethode {
    //page principale oú se déroule le jeu, contient la représentation graphique du plateau
    
	private int[][] lvl;
	private ManagerCase tileManager;
	private MyButton bMenu;

	public PlayPage(Game game) {
		super(game);

		initButtons();
		lvl = LevelsPage.getLevelData();
		tileManager = new ManagerCase();

	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 2, 100, 30);

	}

	@Override
	public void render(Graphics g) {

		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(tileManager.getSprite(id), x * 50, y * 50, null);
			}
		}

		drawButtons(g);

	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);

	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);

	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);

	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);

	}

	@Override
	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();

	}
}
