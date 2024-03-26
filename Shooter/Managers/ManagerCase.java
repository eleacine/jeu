package Shooter.Managers;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Shooter.model.Case;
import Shooter.model.Enregistrement;


public class ManagerCase {

    public Case SOL_CASE, HERBE, MUR_HAUT ,MUR_BAS , MUR_GAUCHE,MUR_DROIT, MUR_COIN_DROIT,MUR_COIN_GAUCHE ,MUR_COIN_HAUT_DROIT,MUR_COIN_HAUT_GAUCHE, MUR_MILIEU , MUR_C_BAS , MUR_C_HAUT , MUR_C_GAUCHE , MUR_C_DROIT 
    , EAU_BAS ,EAU_HAUT, EAU_GAUCHE , EAU_DROIT , EAU_COIN_DROITE_HAUT, EAU_COIN_GAUCHE_BAS ,EAU_COIN_GAUCHE_HAUT ,EAU_COIN_DROITE_BAS , EAU_MILIEU, TABLE_MILIEU,TABLE_BAS,TABLE_HAUT,DRAP_1_HAUT,DRAP_1_BAS,DRAP_1_DROITE,DRAP_1_GAUCHE,DRAP_2,COUSSIN,COUSSIN_MILIEU;

    public static final int SOL = 0;
	public static final int MUR = 1;
    public static final int MUR_CASSANT = 2;
	public static final int OBSTACLE = 3;
    public static final int BLOQUE = 4;
	public static final int DECOR =5;
    protected static final int tailleCase = 40;
    

    private BufferedImage atlas;
    public static ArrayList<Case> cases = new ArrayList<>();

    public ArrayList<Case> mur = new ArrayList<>();
    public ArrayList<Case> obstacle = new ArrayList<>();

    public ManagerCase() {
        loadAtlas();
        createCases();
    }

    private void createCases() {
        int id=0;
        cases.add(SOL_CASE= new Case(getSprite(0, 0),id++,SOL));
        cases.add(HERBE=new Case(getSprite(1, 0),id++,SOL));

        mur.add(MUR_HAUT= new Case(getSprite(2, 0),id++,MUR));
        mur.add(MUR_BAS= new Case(getRotImg(getSprite(2, 0), 180),id++,MUR));
        mur.add(MUR_GAUCHE= new Case(getRotImg(getSprite(2, 0), 270),id++,MUR));
        mur.add(MUR_DROIT= new Case(getRotImg(getSprite(2, 0), 90),id++,MUR));

        mur.add(MUR_COIN_GAUCHE= new Case(getSprite(3, 0),id++,MUR));
        mur.add(MUR_COIN_DROIT= new Case(getRotImg(getSprite(3, 0), 90),id++,MUR));
        mur.add(MUR_COIN_HAUT_DROIT= new Case(getRotImg(getSprite(3, 0), 270),id++,MUR));
        mur.add(MUR_COIN_HAUT_GAUCHE= new Case(getRotImg(getSprite(3, 0), 180),id++,MUR));
        mur.add(MUR_MILIEU= new Case(getSprite(4, 0),id++,1));
        
        mur.add(MUR_C_BAS= new Case(getSprite(5, 0),id++,MUR_CASSANT));
        mur.add(MUR_C_HAUT= new Case(getRotImg(getSprite(5, 0), 180),id++,MUR_CASSANT));
        mur.add(MUR_C_GAUCHE=new Case(getRotImg(getSprite(5, 0), 90),id++,MUR_CASSANT));
        mur.add(MUR_C_DROIT= new Case(getRotImg(getSprite(5, 0), 270),id++,MUR_CASSANT));

        obstacle.add(EAU_BAS= new Case(getSprite(6, 0),id++,OBSTACLE));
        obstacle.add(EAU_HAUT= new Case(getRotImg(getSprite(6, 0), 180),id++,OBSTACLE));
        obstacle.add(EAU_GAUCHE= new Case(getRotImg(getSprite(6, 0), 90),id++,OBSTACLE));
        obstacle.add(EAU_DROIT= new Case(getRotImg(getSprite(6, 0), 270),id++,OBSTACLE));

        obstacle.add(EAU_COIN_DROITE_HAUT= new Case(getSprite(7, 0),id++,OBSTACLE));
        obstacle.add(EAU_COIN_GAUCHE_BAS= new Case(getRotImg(getSprite(7, 0), 180),id++,OBSTACLE));
        obstacle.add(EAU_COIN_GAUCHE_HAUT= new Case(getRotImg(getSprite(7, 0), 270),id++,OBSTACLE));
        obstacle.add(EAU_COIN_DROITE_BAS= new Case(getRotImg(getSprite(7, 0), 90),id++,OBSTACLE));

        obstacle.add(EAU_MILIEU= new Case(getSprite(8, 0),id++,OBSTACLE));//23

        obstacle.add(TABLE_MILIEU= new Case(getSprite(0, 1),id++,BLOQUE));//24
        obstacle.add(TABLE_BAS= new Case(getSprite(1, 1),id++,BLOQUE));//25
        obstacle.add(TABLE_HAUT= new Case(getRotImg(getSprite(1, 1), 180),id++,BLOQUE));//26

        obstacle.add(DRAP_1_HAUT= new Case(getSprite(2, 1),id++,BLOQUE));//27
        obstacle.add(DRAP_1_BAS= new Case(getRotImg(getSprite(2, 1), 180),id++,BLOQUE));//28
        obstacle.add(DRAP_1_GAUCHE= new Case(getRotImg(getSprite(2, 1), 270),id++,BLOQUE));//29
        obstacle.add(DRAP_1_DROITE= new Case(getRotImg(getSprite(2, 1), 90),id++,BLOQUE));//30

        obstacle.add(DRAP_2= new Case(getSprite(3, 1),id++,BLOQUE));//31
       
        obstacle.add(COUSSIN= new Case(getSprite(4, 1),id++,BLOQUE));//32
        obstacle.add(COUSSIN_MILIEU= new Case(getSprite(5, 1),id++,BLOQUE));//33

        
        
        cases.addAll(mur);
        cases.addAll(obstacle);
    }

    private void loadAtlas() {
        atlas = Enregistrement.getSpriteAtlas();
    }

    public static BufferedImage getRotImg(BufferedImage input, int rotAngle) {
		int width = input.getWidth();
		int height = input.getHeight();
		
		BufferedImage nouveauImg = new BufferedImage(width, height, input.getType());
		Graphics2D g2d = nouveauImg.createGraphics();
		
		g2d.rotate(Math.toRadians(rotAngle),width/2,height/2);
		g2d.drawImage(input, 0, 0, null);
		g2d.dispose();
		
		return nouveauImg;
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
            System.out.println(xCord+","+yCord);
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
    
    public static int getCaseType(int id){
        for (Case c : cases) {
            if (c.getId() == id) {
                return c.getType();
            }
        }
        // Si aucun identifiant correspondant n'est trouvé, vous pouvez choisir de renvoyer une valeur par défaut ou lancer une exception selon vos besoins.
        System.err.println("Erreur : Aucune case trouvée avec l'identifiant " + id);
        return -1; // Remplacez ceci par la valeur par défaut ou la logique appropriée.
    }

}
