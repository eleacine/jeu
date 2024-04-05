package Shooter.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.*;

import java.awt.geom.Path2D;

public class Player extends Personnage {

    protected String pseudo;
    protected double direction = 0;
    protected double rotationSpeed = Math.PI / 100;
    protected ArrayList<Armes> armes = new ArrayList<Armes>();
    protected int currentArme;

    protected int level = 1;
    protected ImageIcon sprite;

    //protected Path2D.Double boundingPolygon; // Polygone englobant le joueur

    // Chargement des images de marche
    private ImageIcon[] walkSprites;
    private static final int NUM_WALK_FRAMES = 3;

    public Player(String pseudo) {
        super(1000, 500, 0, 0, 50, 2, 1000);
        this.pseudo = pseudo;

        this.armes.add(new A1());
        this.armes.add(new A2());
        this.armes.add(new A3());
        this.armes.add(new A4());
        this.armes.add(new A5());
        currentArme = 0;

    
        loadWalkSprites();
        //createBoundingPolygon(); // Créer le polygone englobant une fois que le sprite est chargé
        
        
    }

    // Chargement des images de marche depuis des fichiers ou des ressources
    private void loadWalkSprites() {
        walkSprites = new ImageIcon[NUM_WALK_FRAMES]; // NUM_WALK_FRAMES est le nombre total de frames de marche
        for (int i = 0; i < NUM_WALK_FRAMES; i++) {
            String filename = "walk_" + i + ".png"; // Nom du fichier de l'image de marche
            
            URL imageUrl = getClass().getResource(filename);
            ImageIcon walkSprite = new ImageIcon(imageUrl);

            double scale = 5;
            int newWidth = (int) (size * scale);
            int newHeight = (int) (size * scale);

            Image resizedImage = walkSprite.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon joueurImage = new ImageIcon(resizedImage);
            walkSprites[i] = joueurImage;
        }
        
    }


    // Méthode pour dessiner le joueur en utilisant l'image de marche actuelle
    public Image imagePlayer(int animationIndex) {
        //System.out.println(currentWalkFrame);
        return walkSprites[animationIndex].getImage();
    }

    // Méthode pour créer le polygone englobant basé sur les coordonnées de l'image
    /*private void createBoundingPolygon() {
        // Définissez les coordonnées des sommets de votre forme de joueur
        double[] xPoints = { x, x + sprite.getIconWidth(), x + sprite.getIconWidth(), x };
        double[] yPoints = { y, y, y + sprite.getIconHeight(), y + sprite.getIconHeight() };
        int numPoints = 4; // Nombre de sommets (dans ce cas, un rectangle)

        // Créez le polygone englobant en utilisant les coordonnées définies
        boundingPolygon = new Path2D.Double();
        boundingPolygon.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < numPoints; i++) {
            boundingPolygon.lineTo(xPoints[i], yPoints[i]);
        }
        boundingPolygon.closePath();
    }*/

    // Méthode pour mettre à jour les coordonnées du polygone englobant en fonction
    // de la position du joueur
   /*  private void updateBoundingPolygon() {
        double[] xPoints = {285, 286, 278, 277, 278};
        double[] yPoints = {234, 246, 247, 250, 265};
        int numPoints = 4;

        boundingPolygon.reset();
        boundingPolygon.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < numPoints; i++) {
            boundingPolygon.lineTo(xPoints[i], yPoints[i]);
        }
        boundingPolygon.closePath();
    }

    // Méthode pour dessiner le polygone englobant avec un contour coloré
    public void drawBoundingPolygon(Graphics2D g2d) {
       // g2d.setColor(Color.RED); // Couleur du contour
        //g2d.draw(boundingPolygon); // Dessiner le contour du polygone
    }*/

    // récupérer une éventuelle sauvegarde
    public static Player loadPlayer() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Game\\sauvegarde.ser"))) {
            return (Player) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadSprite() {
        URL imageUrl = getClass().getResource("walk_0.png");
        ImageIcon originalImage = new ImageIcon(imageUrl);

        double scale = 5;
        int newWidth = (int) (size * scale);
        int newHeight = (int) (size * scale);

        Image resizedImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon joueurImage = new ImageIcon(resizedImage);
        this.sprite = joueurImage;
    }

    public boolean isPseudoIdentique(String playerName) {
        return this.pseudo.equals(playerName);
    }

    public void reset() {
        this.x = 1000;
        this.y = 500;
        this.direction = 0;
        this.sante = 200;
        this.currentArme = 0;

    }

    // ------ Getter et Setter ---------

    // Redéfinissez les méthodes setX() et setY() pour mettre à jour le polygone à
    // chaque fois que la position du joueur change
    @Override
    public void setX(int x) {
        super.setX(x);
        //updateBoundingPolygon(); // Mettre à jour le polygone englobant lorsque la position en X du joueur est
                                 // modifiée
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        //updateBoundingPolygon(); // Mettre à jour le polygone englobant lorsque la position en Y du joueur est
                                 // modifiée
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String newPseudo) {
        this.pseudo = newPseudo;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public ArrayList<Armes> getArmes() {
        return armes;
    }

    public void setArmes(ArrayList<Armes> armes) {
        this.armes = armes;
    }

    public int getCurrentArme() {
        return currentArme;
    }

    public void setCurrentArme(int currentArme) {
        this.currentArme = currentArme;
    }

    public ImageIcon getSprite() {
        return sprite;
    }

    public void setSprite(ImageIcon sprite) {
        this.sprite = sprite;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel() {
        this.level++;
    }

    public void setSprite(String newPath) {
        loadSprite(newPath);
    }

}