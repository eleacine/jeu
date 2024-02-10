package Shooter.model;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

//import Shooter.model.Enregistrement;
//import Shooter.model.Case;

public class ManagerCase {

	public Case MARCHE, OBSTACLE, INTERDIT;
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

	}

	private void loadAtalas() {

		atlas = Enregistrement.getSpriteAtlas();

	}

	public BufferedImage getSprite(int id) {
		return cases.get(id).getSprite();
	}

	private BufferedImage getSprite(int xCord, int yCord) {
		return atlas.getSubimage(xCord * 50, yCord * 50, 50, 50);
	}

}
