package Shooter.GUI;

import java.awt.Color;
import java.awt.Graphics;

import Shooter.model.Game;
import Shooter.model.MyButton;
import static Shooter.model.GameStates.*;

public class SettingsPage extends GameScene implements SceneMethode{
    //contient réglages du type darkmode, musique, sauvegarde, skins, règles du jeu
    private MyButton bMenu;

	public SettingsPage(Game game) {
		super(game);
		initButtons();

	}

	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 2, 100, 30);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 1500, 900);

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
		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();

	}
}
