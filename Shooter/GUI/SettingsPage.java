package Shooter.GUI;

import Shooter.model.Game;

public class SettingsPage extends GameScene {
	// contient réglages du type darkmode, musique, sauvegarde, skins, règles du jeu

	public SettingsPage(Game game) {
		super(game);
		add(createButton("Menu", "Menu"));
	}

}
