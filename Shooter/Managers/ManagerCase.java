package Shooter.Managers;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Shooter.model.Case;
import Shooter.model.Enregistrement;


public class ManagerCase {

    public Case SOL_CASE,SOL_SALLE_BAIN, HERBE, MUR_HAUT ,MUR_BAS , MUR_GAUCHE,MUR_DROIT, MUR_COIN_DROIT,MUR_COIN_GAUCHE ,MUR_COIN_HAUT_DROIT,MUR_COIN_HAUT_GAUCHE, MUR_MILIEU , MUR_C_BAS , MUR_C_HAUT , MUR_C_GAUCHE , MUR_C_DROIT, CHAISE,CHAISE_BAS,CHAISE_GAUCHE,CHAISE_DROITE
    , EAU_BAS ,EAU_HAUT, EAU_GAUCHE , EAU_DROIT , EAU_COIN_DROITE_HAUT, EAU_COIN_GAUCHE_BAS ,EAU_COIN_GAUCHE_HAUT ,EAU_COIN_DROITE_BAS , EAU_MILIEU, TABLE_MILIEU,TABLE_BAS,TABLE_HAUT,DRAP_1_HAUT,DRAP_1_BAS,DRAP_1_DROITE,DRAP_1_GAUCHE,DRAP_2,COUSSIN,COUSSIN_MILIEU,
    PLAN_CUISINE,PLANCHE_DECOUPE,PLAQUE_CUISSON,GARDE_MANGER_OBSTACLE,GARDE_MANGER_VIDE,GARDE_MANGER_LEGUMES,GARDE_MANGER_CONSERVE,TROTOIRE,TROTOIRE_PASSAGE,PASSAGE,PORTE,PORTE2,TAPIS_1,TAPIS_2,TAPIS_MILIEU,BUISSON,BUISSON2,BUISSON3,POT_FLEUR,POT_FLEUR_INT,TABLE,TABLE_BASSE_2,TABLE_BASSE,

    OBSTACLE_MANGER,OBSTACLE_JOUET,OBSTACLE_ENTRE_1,OBSTACLE_ENTRE_2,OBSTACLE_CHAMBRE,OBSTACLE_CUISINE,OBSTACLE_BAIN,OBSTACLE_BAIN_2,FLEUR_1,FLEUR_2,CHAUSSURE,LAMPE_1,LAMPE_2,LAMPE_3,BAC,BUREAU,BUREAU_DROITE,BUREAU_GAUCHE,TOILETTE,FRIGO,EVIER_CUISINE,POUBELLE,PLANCHE_DECOUPE_2,BEBE_1,BEBE_2,BEBE_3,BEBE_4,BEBE_5,BEBE_6,BEBE_7,BEBE_8
    ,BAIN_BAS,BAIN_DROITE,BAIN_GAUCHE,BAIN_HAUT,BAIN_MILIEU,BAIN_MILIEU_P,DOUCHE,DOUCHE_MILIEU,MEUBLE_BAIN,DRAP_J,DRAP_G,DRAP_V,DRAP_V_BAS, DRAP_J_BAS,DRAP_G_BAS,DRAP_G_DROITE,DRAP_V_DROITE,DRAP_J_DROITE,DRAP_G_HAUT,DRAP_J_HAUT,DRAP_V_HAUT,DRAP_G_GAUCHE,DRAP_J_GAUCHE,DRAP_V_GAUCHE;
    ;

    public static final int SOL = 0;
	public static final int MUR = 1;
    public static final int CASSANT = 2;
	public static final int OBSTACLE = 3;
    public static final int BLOQUE = 4;
	public static final int DECOR =5;
    protected static final int tailleCase = 40;
    

    private BufferedImage atlas;
    public static ArrayList<Case> cases = new ArrayList<>();

    public ArrayList<Case> mur = new ArrayList<>();
    public ArrayList<Case> obstacle = new ArrayList<>();
    public ArrayList<Case> sol = new ArrayList<>();

    public ManagerCase() {
        loadAtlas();
        createCases();
    }

    private void createCases() {
        int id=0;
        sol.add(SOL_CASE= new Case(getSprite(0, 0),id++,SOL));//0
        sol.add(SOL_SALLE_BAIN= new Case(getSprite(1, 0),id++,SOL));//0
        sol.add(HERBE=new Case(getSprite(2, 0),id++,SOL));//1

        sol.add(TROTOIRE= new Case(getSprite(3, 0),id++,SOL));//2
        sol.add(TROTOIRE_PASSAGE= new Case(getSprite(4, 0),id++,SOL));//3
        sol.add(PASSAGE= new Case(getSprite(5, 0),id++,SOL));//4

        mur.add(MUR_HAUT= new Case(getSprite(4, 4),id++,MUR));//7
        mur.add(MUR_BAS= new Case(getRotImg(getSprite(4, 4), 180),id++,MUR));//8
        mur.add(MUR_GAUCHE= new Case(getRotImg(getSprite(4, 4), 270),id++,MUR));//9
        mur.add(MUR_DROIT= new Case(getRotImg(getSprite(4, 4), 90),id++,MUR));//10

        mur.add(MUR_COIN_GAUCHE= new Case(getSprite(5, 4),id++,MUR));//11
        mur.add(MUR_COIN_DROIT= new Case(getRotImg(getSprite(5, 4), 90),id++,MUR));//12
        mur.add(MUR_COIN_HAUT_DROIT= new Case(getRotImg(getSprite(5, 4), 270),id++,MUR));//13
        mur.add(MUR_COIN_HAUT_GAUCHE= new Case(getRotImg(getSprite(5, 4), 180),id++,MUR));//14
        mur.add(MUR_MILIEU= new Case(getSprite(6, 4),id++,1));//15
        mur.add(MUR_C_BAS= new Case(getSprite(1, 2),id++,CASSANT));//16
        mur.add(MUR_C_HAUT= new Case(getRotImg(getSprite(1, 2), 180),id++,CASSANT));//17
        mur.add(MUR_C_GAUCHE=new Case(getRotImg(getSprite(1, 2), 90),id++,CASSANT));//18
        mur.add(MUR_C_DROIT= new Case(getRotImg(getSprite(1, 2), 270),id++,CASSANT));//19

        obstacle.add(EAU_BAS= new Case(getSprite(6, 0),id++,OBSTACLE));//20
        obstacle.add(EAU_HAUT= new Case(getRotImg(getSprite(6, 0), 180),id++,OBSTACLE));//21
        obstacle.add(EAU_GAUCHE= new Case(getRotImg(getSprite(6, 0), 90),id++,OBSTACLE));//22
        obstacle.add(EAU_DROIT= new Case(getRotImg(getSprite(6, 0), 270),id++,OBSTACLE));//23

        obstacle.add(EAU_COIN_DROITE_HAUT= new Case(getSprite(7, 0),id++,OBSTACLE));//24
        obstacle.add(EAU_COIN_GAUCHE_BAS= new Case(getRotImg(getSprite(7, 0), 180),id++,OBSTACLE));//25
        obstacle.add(EAU_COIN_GAUCHE_HAUT= new Case(getRotImg(getSprite(7, 0), 270),id++,OBSTACLE));//26
        obstacle.add(EAU_COIN_DROITE_BAS= new Case(getRotImg(getSprite(7, 0), 90),id++,OBSTACLE));//27

        obstacle.add(EAU_MILIEU= new Case(getSprite(8, 0),id++,OBSTACLE));//28

        obstacle.add(OBSTACLE_MANGER= new Case(getSprite(0, 1),id++,OBSTACLE));//20
        obstacle.add(OBSTACLE_JOUET= new Case(getSprite(1, 1),id++,OBSTACLE));//20
        obstacle.add(OBSTACLE_ENTRE_1= new Case(getSprite(2, 1),id++,OBSTACLE));//20
        obstacle.add(OBSTACLE_ENTRE_2= new Case(getSprite(3, 1),id++,OBSTACLE));//20
        obstacle.add(OBSTACLE_CHAMBRE= new Case(getSprite(4, 1),id++,OBSTACLE));//20
        obstacle.add(OBSTACLE_CUISINE= new Case(getSprite(5, 1),id++,OBSTACLE));//20
        obstacle.add(OBSTACLE_BAIN= new Case(getSprite(6, 1),id++,OBSTACLE));//20
        obstacle.add(OBSTACLE_BAIN_2= new Case(getSprite(7, 1),id++,OBSTACLE));//20

        obstacle.add(FLEUR_1= new Case(getSprite(8, 1),id++,CASSANT));//5
        obstacle.add(FLEUR_2= new Case(getSprite(0, 2),id++,CASSANT));//5
       
        obstacle.add(PORTE= new Case(getSprite(2, 2),id++,CASSANT));//5
        obstacle.add(PORTE2= new Case(getSprite(3, 2),id++,CASSANT));//5
        obstacle.add(TABLE= new Case(getSprite(4, 2),id++,CASSANT));//5
        obstacle.add(TABLE_BAS= new Case(getSprite(5, 2),id++,CASSANT));//5
        obstacle.add(TABLE_HAUT= new Case(getRotImg(getSprite(5, 2), 180),id++,CASSANT));//31
        obstacle.add(CHAISE= new Case(getSprite(6, 2),id++,CASSANT));//32
        obstacle.add(CHAISE_GAUCHE= new Case(getRotImg(getSprite(6, 2), 90),id++,CASSANT));//33
        obstacle.add(CHAISE_BAS= new Case(getRotImg(getSprite(6, 2), 180),id++,CASSANT));//33
        obstacle.add(CHAISE_DROITE= new Case(getRotImg(getSprite(6, 2), 270),id++,CASSANT));//33

        obstacle.add(TABLE_BASSE= new Case(getSprite(7, 2),id++,CASSANT));//5
        obstacle.add(TABLE_BASSE_2= new Case(getSprite(8, 2),id++,CASSANT));//5

        obstacle.add(CHAUSSURE= new Case(getSprite(0, 3),id++,CASSANT));//5

        obstacle.add(LAMPE_1= new Case(getSprite(1, 3),id++,CASSANT));//5
        obstacle.add(LAMPE_2= new Case(getSprite(2, 3),id++,CASSANT));//5
        obstacle.add(LAMPE_3= new Case(getSprite(3, 3),id++,CASSANT));//5
        obstacle.add(GARDE_MANGER_LEGUMES= new Case(getSprite(4, 3),id++,CASSANT));//5
        obstacle.add(GARDE_MANGER_VIDE= new Case(getSprite(5, 3),id++,CASSANT));//5
        obstacle.add(GARDE_MANGER_CONSERVE= new Case(getSprite(6, 3),id++,CASSANT));//5
        
        obstacle.add(BAC= new Case(getSprite(7, 3),id++,CASSANT));//5
        obstacle.add(BUREAU_GAUCHE= new Case(getSprite(8, 3),id++,CASSANT));//5
        obstacle.add(BUREAU= new Case(getSprite(0, 4),id++,CASSANT));//5
        obstacle.add(BUREAU_DROITE= new Case(getSprite(1, 4),id++,CASSANT));//5

        obstacle.add(TOILETTE= new Case(getSprite(2, 4),id++,CASSANT));//5
        
        obstacle.add(BUISSON= new Case(getSprite(3, 4),id++,BLOQUE));//29

       

        obstacle.add(FRIGO= new Case(getSprite(7, 4),id++,BLOQUE));//29
        
        
        obstacle.add(PLAN_CUISINE= new Case(getSprite(8, 4),id++,BLOQUE));//34
        obstacle.add(EVIER_CUISINE= new Case(getSprite(0, 5),id++,BLOQUE));//35
        obstacle.add(PLAQUE_CUISSON= new Case(getSprite(1, 5),id++,BLOQUE));//35
        obstacle.add(PLANCHE_DECOUPE= new Case(getSprite(2, 5),id++,BLOQUE));//36
        obstacle.add(PLANCHE_DECOUPE_2= new Case(getSprite(3, 5),id++,BLOQUE));//36
        obstacle.add(POUBELLE= new Case(getSprite(4, 5),id++,BLOQUE));//36
    

        obstacle.add(COUSSIN= new Case(getSprite(5, 5),id++,BLOQUE));//57
        obstacle.add(COUSSIN_MILIEU=new Case(getSprite(6, 5),id++,BLOQUE));//58
        obstacle.add(DRAP_J_HAUT= new Case(getSprite(7, 5),id++,BLOQUE));//52
        obstacle.add(DRAP_J_BAS= new Case(getRotImg(getSprite(7, 5), 180),id++,BLOQUE));//53
        obstacle.add(DRAP_J_GAUCHE= new Case(getRotImg(getSprite(7, 5), 270),id++,BLOQUE));//54
        obstacle.add(DRAP_J_DROITE= new Case(getRotImg(getSprite(7, 5), 90),id++,BLOQUE));//55

        obstacle.add(DRAP_J= new Case(getSprite(8, 5),id++,BLOQUE));//56

        obstacle.add(DRAP_G_HAUT= new Case(getSprite(0, 6),id++,BLOQUE));//52
        obstacle.add(DRAP_G_BAS= new Case(getRotImg(getSprite(0, 6), 180),id++,BLOQUE));//53
        obstacle.add(DRAP_G_GAUCHE= new Case(getRotImg(getSprite(0, 6), 270),id++,BLOQUE));//54
        obstacle.add(DRAP_G_DROITE= new Case(getRotImg(getSprite(0, 6), 90),id++,BLOQUE));//55

        obstacle.add(DRAP_G= new Case(getSprite(1, 6),id++,BLOQUE));//56

        obstacle.add(DRAP_V_HAUT= new Case(getSprite(2, 6),id++,BLOQUE));//52
        obstacle.add(DRAP_V_BAS= new Case(getRotImg(getSprite(2, 6), 180),id++,BLOQUE));//53
        obstacle.add(DRAP_V_GAUCHE= new Case(getRotImg(getSprite(2, 6), 270),id++,BLOQUE));//54
        obstacle.add(DRAP_V_DROITE= new Case(getRotImg(getSprite(2, 6), 90),id++,BLOQUE));//55

        obstacle.add(DRAP_V= new Case(getSprite(3, 6),id++,BLOQUE));//56

        obstacle.add(BEBE_1= new Case(getSprite(4, 6),id++,BLOQUE));//56
        obstacle.add(BEBE_2= new Case(getSprite(5, 6),id++,BLOQUE));//56
        obstacle.add(BEBE_3= new Case(getSprite(6, 6),id++,BLOQUE));//56
        obstacle.add(BEBE_4= new Case(getSprite(7, 6),id++,BLOQUE));//56
        obstacle.add(BEBE_5= new Case(getSprite(8, 6),id++,BLOQUE));//56
        obstacle.add(BEBE_6= new Case(getSprite(0, 7),id++,BLOQUE));//56
        obstacle.add(BEBE_7= new Case(getSprite(1, 7),id++,BLOQUE));//56
        obstacle.add(BEBE_8= new Case(getSprite(2, 7),id++,BLOQUE));//56
        obstacle.add(BAIN_HAUT= new Case(getSprite(3, 7),id++,BLOQUE));//56
        obstacle.add(BAIN_DROITE= new Case(getRotImg(getSprite(3, 7), 90),id++,BLOQUE));//53
        obstacle.add(BAIN_BAS= new Case(getRotImg(getSprite(3, 7), 180),id++,BLOQUE));//53
        obstacle.add(BAIN_GAUCHE= new Case(getRotImg(getSprite(3, 7), 270),id++,BLOQUE));//53
        obstacle.add(BAIN_MILIEU= new Case(getSprite(4, 7),id++,BLOQUE));//56
        obstacle.add(BAIN_MILIEU_P= new Case(getRotImg(getSprite(4, 7), 90),id++,BLOQUE));//53
        obstacle.add(DOUCHE_MILIEU= new Case(getSprite(5, 7),id++,BLOQUE));//56
        obstacle.add(DOUCHE= new Case(getSprite(6, 7),id++,BLOQUE));//56
        obstacle.add(MEUBLE_BAIN= new Case(getSprite(8, 7),id++,BLOQUE));//56
        





        
        

        
        cases.addAll(sol);
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

    public Case getMUR() {
        return MUR_HAUT;
    }

}
