package Shooter.Managers;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Shooter.model.Case;
import Shooter.model.Enregistrement;


public class ManagerCase {

	public Case MARCHE, OBSTACLE, INTERDIT, MINE;
	private BufferedImage atlas;
	public ArrayList<Case> cases = new ArrayList<>();

	public ManagerCase() {

		loadAtalas();
		creationCase();

	}

	private void creationCase() {

		cases.add(MARCHE = new Case(getSprite(0, 0)));
		cases.add(OBSTACLE = new Case(getSprite(1, 0)));
		cases.add(INTERDIT = new Case(getSprite(2, 0)));
		// cases.add(MINE = new Case(getSprite(3, 0)));

	}

	private void loadAtalas() {

		atlas = Enregistrement.getSpriteAtlas();

	}

	public BufferedImage getSprite(int id) {
		return cases.get(id).getSprite();
	}

	private BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord * 50, 0, 50, 50);
	}

}
