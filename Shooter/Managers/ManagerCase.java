package Shooter.Managers;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Shooter.model.Case;
import Shooter.model.Enregistrement;


public class ManagerCase {

	public static final int MARCHE = 0;
    public static final int OBSTACLE = 1;
    public static final int INTERDIT = 2;
    public static final int MINE = 3;
    public static final int GRENADE = 4;

    protected static final int tailleCase = 50;

    private BufferedImage atlas;
    public ArrayList<Case> cases = new ArrayList<>();

    public ManagerCase() {
        loadAtlas();
        createCases();
    }

    private void createCases() {
        cases.add(MARCHE, new Case(getSprite(0, 0)));
        cases.add(OBSTACLE, new Case(getSprite(1, 0)));
        cases.add(INTERDIT, new Case(getSprite(2, 0)));
        // cases.add(MINE, new Case(getSprite(3, 0)));
        // cases.add(GRENADE, new Case(getSprite(4, 0)));
    }

    private void loadAtlas() {
        atlas = Enregistrement.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id) {
        return cases.get(id).getSprite();
    }

    // private BufferedImage getSprite(int xCord, int yCord) {
    //     return atlas.getSubimage(xCord * 50, 0, 50, 50);
    // }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 40, 0, 40, 40);
    }

    public int getTailleCase() {
        return tailleCase;
    }

}
