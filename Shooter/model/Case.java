package Shooter.model;

import java.awt.image.BufferedImage;

public class Case {
    private BufferedImage sprite;

	public Case(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public BufferedImage getSprite() {
		return sprite;
	}
}
