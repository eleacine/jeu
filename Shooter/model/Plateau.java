package Shooter.model;

import java.awt.*;
import javax.swing.*;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import Shooter.Managers.*;
import Shooter.factory.PlateauLevelLoader;
import java.util.Queue;


public class Plateau extends JPanel {
    public BufferedImage[] imageArmes;
    public GameManager gameManager;

    public int[][] level_tab;
    public ManagerCase tile_manager;
    public Graphics plateau_graphic; // on utilise pour "enregistrer" notre image graphique puis pouvoir la modifier
                                     // dans le update

    public ArrayList<A3> pieges = new ArrayList<A3>();
    public ArrayList<A4> grenade = new ArrayList<A4>();

    public int[][] floodfill;;

    public Plateau(boolean gameMode) {
        if(!gameMode){
            this.level_tab = PlateauLevelLoader.loadPlayingBoard("Shooter/factory/PlateauLevels.txt", 0);
        }
        else{
            this.level_tab = PlateauLevelLoader.loadPlayingBoard("Shooter/factory/PlateauLevelsPerso.txt", 0);
        }
        this.floodfill = new int[this.level_tab.length][level_tab[0].length];
        this.tile_manager = new ManagerCase();
        loadArme();

    }

    public void render_plateau(Graphics g) {
        for (int y = 0; y < level_tab.length; y++) {
            for (int x = 0; x < level_tab[y].length; x++) {
                int id = level_tab[y][x];
                g.drawImage(tile_manager.getSprite(id), x * 40, y * 40, null);
            }
        }
        this.plateau_graphic = g;

        this.floodfill = newFloodFill(this.gameManager.getPlayer().getY() / 40,
                this.gameManager.getPlayer().getX() / 40);
    //   printFloodFill(floodfill);
    }

    public void update_pleateau(int x, int y, int type_case) {
        plateau_graphic.drawImage(tile_manager.getSprite(type_case), x * 40, y * 40,null);
    }

    private void loadArme() {
        BufferedImage atlas=Enregistrement.getSpriteAtlas();

        imageArmes=new BufferedImage[5];

        for (int i =0 ; i <5;i++){
            imageArmes[i]= atlas.getSubimage((i)*40, 4*40, 40, 40);
        }  
    }

    

    public void waitFortransiion() {
        System.out.println("I'm being called: waitFortransiion");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);


        // Create a new Graphics2D object for gameManager.getPlayer()
        Graphics2D gPlayer = (Graphics2D) g.create();
        drawPlayerMovement(gPlayer);

        // Dessiner les balles du joueur
        for (Bullet playerBullet : gameManager.getProjectilesManager().getplayerBullets()) {
            playerBullet.createBullet(g);
        }

        // Dessiner les balles des ennemis
        for (Bullet enemyBullet : gameManager.getProjectilesManager().getEnemyBullets()) {
            enemyBullet.createBulletEnnemy(g);
        }

        // Dessiner les ennemis
        for (Personnage perso : gameManager.getEnnemiManager().getPerso_list()) {
            if (perso instanceof Enemy) {
                Enemy ennemi = (Enemy) perso;

                if (ennemi instanceof Gardien) {
                    ((Gardien) ennemi).dessinerVision(g);

                } else {

                    // drawDetectionRadius(g, ennemi);
                }
                ennemi.drawEnemy(g);
                ennemi.drawBarVie(g);
            }
        }

  


        // Dessiner les pièges
        for (A3 mine : pieges) {
            if (gameManager.getPlayer().detectCollision(mine.getX(), mine.getY(), mine.getDimension())){
              //  System.out.println("collision mine");
                gameManager.getPlayer().infligerDegats(mine.getPower());
               // waitFortransiion();
                mine.drawExplosion(mine.getX(), mine.getY(), g);
                   // mine.dimension = 0;s
            
                   mine.setDimension(0);
            } else{
                mine.draw(g, gameManager.getPlayer().getX(), gameManager.getPlayer().getY());
                
            }if (gameManager.getEnnemiManager().getPerso_list().size() > 1){
 
                for (Personnage perso : gameManager.getEnnemiManager().getPerso_list()){
       
                    if(perso instanceof Enemy){
           
                        Enemy ennemi=(Enemy)perso;
                    
                        if (ennemi.detectCollision(mine.getX(), mine.getY(), mine.getDimension())){
            
                            ennemi.infligerDegats(mine.getPower());
                       
                       
                            mine.drawExplosion(mine.getX(), mine.getY(), g);
                      
                            // mine.dimension = 0;
                            mine.setDimension(0);
                         
                         }    
                    }
                }
                } else {
                  //  System.out.println("14");
                        mine.draw(g, gameManager.getPlayer().getX(), gameManager.getPlayer().getY());
                 }

           // piege.draw(g, gameManager.getPlayer().getX(), gameManager.getPlayer().getY());
        }

        for (A4 grenade : grenade) {

            grenade.drawGrenade(g);
            //grenade.drawExplosion(g);
   
                int grenadeX = (int) (grenade.getX() / 40);
                int grenadeY = (int) (grenade.getY() / 40);

                for (int x = grenadeX - 2; x <= grenadeX + 2; x++) {
                    for (int y = grenadeY - 2; y <= grenadeY + 2; y++) {
  
                        if (x >= 0 && x < gameManager.getGamePlateau().level_tab[0].length &&
                            y >= 0 && y < gameManager.getGamePlateau().level_tab.length) {
                               double distance = Math.sqrt(Math.pow(x - grenadeX, 2) + Math.pow(y - grenadeY, 2));
                            if (distance <= 2) {
                                int caseID = gameManager.getGamePlateau().level_tab[y][x];
                                int caseType = ManagerCase.getCaseType(caseID);
                                if (caseType == ManagerCase.MUR_CASSANT) {
                                    gameManager.getGamePlateau().level_tab[y][x] = ManagerCase.SOL;
                                }
                            }
                        }
            
                    }
                }
                
            for (A3 mine : pieges) {
                double distance = Math.sqrt(Math.pow(mine.getX() - grenade.getX(), 2) + Math.pow(mine.getY() - grenade.getY(), 2));
                if (distance <= 75) { 
                    mine.drawExplosion(mine.getX(), mine.getY(), g);
                    mine.setDimension(0);
                }
            
            } 
                for (Personnage personnage : gameManager.getEnnemiManager().getPerso_list()) {
                     double distance = Math.sqrt(Math.pow(personnage.getX() - grenade.getX(), 2) + Math.pow(personnage.getY() - grenade.getY(), 2));
                    if (distance <= 75) {
                        personnage.infligerDegats(10);
                    }               
            }
        
    }
        /* 
           if(grenade.getIsGrenadeActivated()==true){
               // System.out.println("grenade"+grenade.isGrenadeActivated());
          /*   for (Personnage personnage : gameManager.getEnnemiManager().getPerso_list()) {
                double distance = Math.sqrt(Math.pow(personnage.x - grenade.getX(), 2) + Math.pow(personnage.y - grenade.getY(), 2));
                if (distance <= 75) {
                    personnage.infligerDegats(10);
                }
            }  
             if (grenade.getIsGrenadeActivated()){
            //   grenade.activationTime = System.currentTimeMillis();
               grenade.startExplosionAnimation(g, gameManager.getPersoList()); 
                grenade.shoot();
             }
             */
            //}
         

        // print arme et nombre munitions
        int currentArme = gameManager.getPlayer().currentArme;
        Armes armeCourante = gameManager.getPlayer().armes.get(currentArme);

        g.setColor(Color.BLACK);
        g.fillRect(1300, 1, 125, 100);
        g.drawImage(imageArmes[armeCourante.getTypeMunition()], 1325, 10, null);
        g.setColor(Color.WHITE);
        if (currentArme >= 0 && currentArme < gameManager.getPlayer().armes.size()) {
            g.drawString("type:" + armeCourante.nom, 1325, 70);
            g.drawString("munitions:" + armeCourante.munition, 1325, 80);
            g.drawString("vie:" + gameManager.getPlayer().sante, 1325, 90);
        }


        // Dessin crosshair
        gameManager.getMyMouseListener().getCrosshair().draw(g);

        int centerX = (int) (gameManager.getPlayer().getX() + gameManager.getPlayer().getSize() / 2
                - armeCourante.distance * 2 / 2);
        int centerY = (int) (gameManager.getPlayer().getY() + gameManager.getPlayer().getSize() / 2
                - armeCourante.distance * 2 / 2);
        /*
         * g.setColor(Color.RED);
         * g.drawOval(centerX, centerY, armeCourante.distance * 2, armeCourante.distance
         * * 2);
         */

        // Définir la couleur du trait
        g2d.setColor(Color.RED);

        // Définir le style de trait discontinu
        float[] dashPattern = { 5, 5 }; // Exemple : alternance de 5 pixels pleins et 5 pixels vides
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, dashPattern, 0.0f));

        // Dessiner le cercle avec le contour discontinu
        // int diametre = armeCourante.distance * 2;
        g2d.drawOval(centerX, centerY, armeCourante.distance * 2, armeCourante.distance * 2);

    }

    private void drawPlayerMovement(Graphics2D g) {

        double scale = 5;
        int newWidth = (int) (gameManager.getPlayer().getSize() * scale);
        int newHeight = (int) (gameManager.getPlayer().getSize() * scale);

        double x = gameManager.getPlayer().getX();
        double y = gameManager.getPlayer().getY();

        AffineTransform at = new AffineTransform();
        at.translate(x - newWidth / 2, y - newHeight / 2);
        at.rotate(gameManager.getPlayer().getDirection(), newWidth / 2, newHeight / 2);

        g.drawImage(gameManager.getPlayer().getSprite().getImage(), at, this);
        gameManager.getPlayer().drawBoundingPolygon(g);
    }

    // private void drawDetectionRadius(Graphics g, Enemy ennemi) {
    // // if (ennemi instanceof EnemyMedium) {
    // // Convertir le centre de l'ennemi et le rayon de détection en coordonnées
    // // d'écran
    // int centerX = (int) ennemi.x;
    // int centerY = (int) ennemi.y;
    // int radius = (ennemi).getDetectionRadius();

    // // Définir le niveau de transparence (0.0f complètement transparent, 1.0f
    // // complètement opaque)
    // float alpha = 0.4f;

    // // Créer une nouvelle couleur avec la transparence appropriée
    // Color transparentColor = new Color(ennemi.color.getRed(),
    // ennemi.color.getGreen(), ennemi.color.getBlue(),
    // (int) (alpha * 255));

    // // Dessiner le rayon de détection comme un cercle transparent
    // Graphics2D g2d = (Graphics2D) g.create();
    // g2d.setColor(transparentColor);

    // Ellipse2D.Double detectionCircle = new Ellipse2D.Double(centerX - radius,
    // centerY - radius, 2 * radius,
    // 2 * radius);
    // g2d.fill(detectionCircle);
    // g2d.dispose();
    // }

    public void reset() {
        pieges.clear();
        grenade.clear();
        if(!gameManager.getGame().gameMode){
            this.level_tab = PlateauLevelLoader.loadPlayingBoard("Shooter/factory/PlateauLevels.txt", gameManager.getPlayer().getLevel() - 1);
        }
        else{
            this.level_tab = PlateauLevelLoader.loadPlayingBoard("Shooter/factory/PlateauLevelsPerso.txt", 0);
        }
    }

    // ------------- Flood fill ----------------

    public int[][] floodfill(int xCible, int yCible) {
        int[][] res = new int[this.level_tab.length][this.level_tab[0].length];
        for (int i = 0; i < this.level_tab.length; i++) {
            for (int j = 0; j < this.level_tab[i].length; j++) {

                if (ManagerCase.getCaseType(this.level_tab[i][j]) == ManagerCase.MUR
                        || ManagerCase.getCaseType(this.level_tab[i][j]) == ManagerCase.MUR_CASSANT
                        || ManagerCase.getCaseType(this.level_tab[i][j]) == ManagerCase.BLOQUE) {
                    res[i][j] = 100;
                } else {
                    res[i][j] = -1;
                }
            }
        }
        res[xCible][yCible] = 0; // on met la case cible à 0
        res = floodfill2(xCible, yCible, res);
        return res;
    }

    public int[][] floodfill2(int x, int y, int[][] res) {
        int v = res[x][y] + 1;

        // Liste des directions
        int[][] directions = {
                { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
        };

        // Parcours de toutes les directions
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            // Vérification des limites de la grille
            if (newX >= 0 && newX < res.length && newY >= 0 && newY < res[0].length) {
                // Vérification si la case est vide et la valeur non mise à jour
                if ((res[newX][newY] == -1 || res[newX][newY] > v) && res[newX][newY] != 100) {
                    res[newX][newY] = v;
                    // Appel récursif pour la nouvelle case
                    floodfill2(newX, newY, res);
                }
            }

        }

        return res;
    }

    public int[][] newFloodFill(int xCible, int yCible) {
        int[][] res = new int[this.level_tab.length][this.level_tab[0].length];
        res[xCible][yCible] = 0; // on met la case cible à 0
        res = newFloodFill2(xCible, yCible, res);

        for (int i = 0; i < this.level_tab.length; i++) {
            for (int j = 0; j < this.level_tab[i].length; j++) {
                if (ManagerCase.getCaseType(this.level_tab[i][j]) == ManagerCase.MUR
                        || ManagerCase.getCaseType(this.level_tab[i][j]) == ManagerCase.MUR_CASSANT
                        || ManagerCase.getCaseType(this.level_tab[i][j]) == ManagerCase.BLOQUE) {
                    res[i][j] = 1000;
                }
            }
        }

        res[xCible][yCible] = 0; // on met la case cible à 0

        return res;
    }

    public int[][] newFloodFill2(int x, int y, int[][] res) {
        Queue<int[]> queue = new LinkedList<>(); // Utiliser une file pour le parcours en largeur (BFS)
        queue.offer(new int[] { x, y }); // Ajouter la cellule initiale à la file
        int distance = 0;

        // Liste des directions
        int[][] directions = {
                { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
        };
        // Tant que la file n'est pas vide, continuez le parcours en largeur
        while (!queue.isEmpty()) {
            int size = queue.size(); // Taille actuelle de la file (nombre de cellules à ce niveau de distance)
            distance++; // Incrémenter la distance pour le niveau suivant
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll(); // Récupérer la cellule de la file
                int cellX = cell[0];
                int cellY = cell[1];

                // Parcourir les cellules adjacentes
                for (int[] dir : directions) {
                    int newX = cellX + dir[0];
                    int newY = cellY + dir[1];
                    // Vérifier si la cellule adjacente est dans la grille et non visitée
                    if (newX >= 0 && newX < res.length && newY >= 0 && newY < res[0].length && res[newX][newY] == 0 &&
                            !isObstacle(newX, newY)) {
                        res[newX][newY] = distance; // Assigner la distance à la cellule adjacente
                        queue.offer(new int[] { newX, newY }); // Ajouter la cellule adjacente à la file pour
                                                               // exploration
                    }
                }
            }
        }
        return res;
    }

    // Vérifier si une cellule est un obstacle
    private boolean isObstacle(int x, int y) {
        return ManagerCase.getCaseType(this.level_tab[x][y]) == ManagerCase.MUR ||
                ManagerCase.getCaseType(this.level_tab[x][y]) == ManagerCase.MUR_CASSANT ||
                ManagerCase.getCaseType(this.level_tab[x][y]) == ManagerCase.BLOQUE;
    }

    // public int[][] newFloodFill2(int x, int y, int[][] res) {
    // Queue<int[]> queue = new LinkedList<>(); // Utiliser une file pour le
    // parcours en largeur (BFS)
    // queue.offer(new int[] { x, y }); // Ajouter la cellule initiale à la file
    // int distance = 0;

    // // Liste des directions
    // int[][] directions = {
    // { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }
    // };

    // // Tant que la file n'est pas vide, continuez le parcours en largeur
    // while (!queue.isEmpty()) {
    // int size = queue.size(); // Taille actuelle de la file (nombre de cellules à
    // ce niveau de distance)
    // distance++; // Incrémenter la distance pour le niveau suivant
    // for (int i = 0; i < size; i++) {
    // int[] cell = queue.poll(); // Récupérer la cellule de la file
    // int cellX = cell[0];
    // int cellY = cell[1];

    // // Parcourir les cellules adjacentes
    // for (int[] dir : directions) {
    // int newX = cellX + dir[0];
    // int newY = cellY + dir[1];
    // // Vérifier si la cellule adjacente est dans la grille et non visitée
    // if (newX >= 0 && newX < res.length && newY >= 0 && newY < res[0].length &&
    // res[newX][newY] == 0 &&
    // ManagerCase.getCaseType(this.level_tab[newX][newY]) != ManagerCase.MUR &&
    // ManagerCase.getCaseType(this.level_tab[newX][newY]) !=
    // ManagerCase.MUR_CASSANT &&
    // ManagerCase.getCaseType(this.level_tab[newX][newY]) != ManagerCase.BLOQUE) {
    // // Vérifier si la case adjacente n'est pas un cul-de-sac
    // boolean isCulDeSac = true;
    // for (int[] adjDir : directions) {
    // int adjX = newX + adjDir[0];
    // int adjY = newY + adjDir[1];
    // if (adjX >= 0 && adjX < res.length && adjY >= 0 && adjY < res[0].length &&
    // (ManagerCase.getCaseType(this.level_tab[adjX][adjY]) != ManagerCase.MUR &&
    // ManagerCase.getCaseType(this.level_tab[adjX][adjY]) !=
    // ManagerCase.MUR_CASSANT &&
    // ManagerCase.getCaseType(this.level_tab[adjX][adjY]) != ManagerCase.BLOQUE)) {
    // isCulDeSac = false;
    // break;
    // }
    // }
    // if (!isCulDeSac) {
    // res[newX][newY] = distance; // Assigner la distance à la cellule adjacente
    // queue.offer(new int[] { newX, newY }); // Ajouter la cellule adjacente à la
    // file pour
    // // exploration
    // }
    // }
    // }
    // }
    // }
    // return res;
    // }

    public void printFloodFill(int[][] res) {
        // Déterminer la largeur maximale de chaque colonne
        int numRows = res.length;
        int numCols = res[0].length;
        int[] colWidths = new int[numCols];

        for (int j = 0; j < numCols; j++) {
            int maxWidth = 0;
            for (int i = 0; i < numRows; i++) {
                int width = String.valueOf(res[i][j]).length();
                maxWidth = Math.max(maxWidth, width);
            }
            colWidths[j] = maxWidth;
        }

        // Afficher les éléments en alignant les colonnes
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // Formater chaque nombre pour s'adapter à la largeur maximale de la colonne
                System.out.printf("%-" + (colWidths[j] + 1) + "d", res[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // --------- GETTERS et SETTERS -----------

    public int[][] getLevel_tab() {
        return level_tab;
    }

    public ManagerCase getTile_manager() {
        return tile_manager;
    }

    public Graphics getPlateau_graphic() {
        return plateau_graphic;
    }

    public ArrayList<A3> getPieges() {
        return pieges;
    }

    public ArrayList<A4> getGrenade() {
        return grenade;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
    


}
