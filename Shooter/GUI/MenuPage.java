package Shooter.GUI;

// import java.awt.*;

import Shooter.model.Game;

public class MenuPage extends GameScene {

	public MenuPage(Game g1) {
		super(g1);
		setPanelSize();
		initButtons();
	}

	private void setPanelSize() {
		// game.size_screen = new Dimension(1440, 840);

		setMinimumSize(game.size_screen);
		setPreferredSize(game.size_screen);
		setMaximumSize(game.size_screen);

	}

	private void initButtons() {
		add(createButton("Play", "Play"));
		add(createButton("Settings", "Settings"));
		add(createButtonExit("Quit"));
	}

}
