package Shooter.Managers;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Shooter.model.Case;
import Shooter.model.Enregistrement;


public class ManagerCase {

	
    protected static final int tailleCase = 40;
    public static final int SOL = 0;
    public static final int HERBE = 1;
    public static final int MUR_HAUT = 2;
    public static final int MUR_BAS = 3;
    public static final int MUR_GAUCHE = 4;
    public static final int MUR_DROIT = 5;
    public static final int MUR_COIN = 6;
    public static final int MUR_MILIEU = 7;
    public static final int MUR_C_BAS = 8;
    public static final int MUR_C_HAUT = 9;
    public static final int MUR_C_GAUCHE = 10;
    public static final int MUR_C_DROIT = 11;
    public static final int EAU_BAS = 12;
    public static final int EAU_HAUT = 13;
    public static final int EAU_GAUCHE = 14;
    public static final int EAU_DROIT = 15;
    public static final int EAU_COIN_DROITE_HAUT = 16;
    public static final int EAU_COIN_GAUCHE_BAS = 17;
    public static final int EAU_COIN_GAUCHE_HAUT = 18;
    public static final int EAU_COIN_DROITE_BAS = 19;
    public static final int EAU_MILIEU = 20;

    private BufferedImage atlas;
    public ArrayList<Case> cases = new ArrayList<>();

    public ManagerCase() {
        loadAtlas();
        createCases();
    }

    private void createCases() {
        cases.add(SOL, new Case(getSprite(0, 0)));
        cases.add(HERBE, new Case(getSprite(1, 0)));
        cases.add(MUR_HAUT, new Case(getSprite(2, 0)));
        cases.add(MUR_BAS, new Case(getSprite(3, 0)));
        cases.add(MUR_GAUCHE, new Case(getSprite(4, 0)));
        cases.add(MUR_DROIT, new Case(getSprite(5, 0)));
        cases.add(MUR_COIN, new Case(getSprite(6, 0)));
        cases.add(MUR_MILIEU, new Case(getSprite(0, 1)));
        cases.add(MUR_C_BAS, new Case(getSprite(1, 1)));
        cases.add(MUR_C_HAUT, new Case(getSprite(2, 1)));
        cases.add(MUR_C_GAUCHE, new Case(getSprite(3, 1)));
        cases.add(MUR_C_DROIT, new Case(getSprite(4, 1)));
        cases.add(EAU_BAS, new Case(getSprite(5, 1)));
        cases.add(EAU_HAUT, new Case(getSprite(6, 1)));
        cases.add(EAU_GAUCHE, new Case(getSprite(0, 2)));
        cases.add(EAU_DROIT, new Case(getSprite(1, 2)));
        cases.add(EAU_COIN_DROITE_HAUT, new Case(getSprite(2, 2)));
        cases.add(EAU_COIN_GAUCHE_BAS, new Case(getSprite(3, 2)));
        cases.add(EAU_COIN_GAUCHE_HAUT, new Case(getSprite(4, 2)));
        cases.add(EAU_COIN_DROITE_BAS, new Case(getSprite(5, 2)));
        cases.add(EAU_MILIEU, new Case(getSprite(6, 2)));
    }

    private void loadAtlas() {
        atlas = Enregistrement.getSpriteAtlas();
    }

    public BufferedImage getSprite(int id) {
        return cases.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        int subImageWidth = 40; // Largeur de la sous-image
        int subImageHeight = 40; // Hauteur de la sous-image
        
        // Calculer la position en x en prenant en compte les deux lignes d'images
        int xPosition = xCord * subImageWidth;
        
        // Calculer la position en y en prenant en compte la ligne d'image
        int yPosition = yCord * subImageHeight;
        
        // Vérifier les limites pour éviter une exception RasterFormatException
        if (xPosition + subImageWidth > atlas.getWidth() || yPosition + subImageHeight > atlas.getHeight()) {
            // Gérer cette situation d'une manière appropriée, par exemple, renvoyer une image par défaut ou lancer une exception
            System.err.println("Erreur : Coordonnées en dehors des limites de l'image atlas.");
            return getDefaultSprite(); // Remplacez ceci par la logique appropriée
        }
        
        return atlas.getSubimage(xPosition, yPosition, subImageWidth, subImageHeight);
    }
    
    private BufferedImage getDefaultSprite() {
        // Remplacez ceci par la logique appropriée pour renvoyer une image par défaut
        return null;
    }

    public int getTailleCase() {
        return tailleCase;
    }

}
