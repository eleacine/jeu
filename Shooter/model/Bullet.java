package Shooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bullet {

    public int size = 10; // Taille du projectile
    public float x; // Coordonnée x du projectile
    public float y; // Coordonnée y du projectile
    public float differenceX; // Composante de mouvement en x
    public float differenceY; // Composante de mouvement en y
    private int degats;
    private float slowdownFactor = 15f; // Facteur de ralentissement pour ajuster la vitesse du projectile
    public Color color; // Couleur du projectile
    private BufferedImage[] mImages;
    public float distanceTraveled = 0;
    public int typeMunitionArme;

    public boolean rebond = false;

    // Constructeur de la classe Bullet
    // x, y : Coordonnées initiales du projectile
    // destX, destY : Coordonnées de la destination du projectile
    // collision : Non utilisé dans cette version
    public Bullet(int x, int y, int destX, int destY, int degats, int typeMunitionArme) {
        this.x = x;
        this.y = y;
        this.degats = degats;
        this.typeMunitionArme = typeMunitionArme;
        calculateMovement(destX, destY); // Calcul des composantes de mouvement en fonction de la destination
        loadMunitionImage();
    }

    public Bullet(int x, int y, int destX, int destY, int degats, int typeMunitionArme, boolean rebond) {
        this.x = x;
        this.y = y;
        this.degats = degats;
        this.typeMunitionArme = typeMunitionArme;
        this.rebond = rebond;
        calculateMovement(destX, destY); // Calcul des composantes de mouvement en fonction de la destination
        loadMunitionImage();
    }

    public Bullet(int x, int y, int destX, int destY, int degats) {
         this.x = x;
         this.y = y;
         this.degats = degats;
         calculateMovement(destX, destY); // Calcul des composantes de mouvement en fonction de la destination
         loadMunitionImage();
     }

    // public Bullet(int x, int y, int destX, int destY, int degats, Color color) {
    //     this.x = x;
    //     this.y = y;
    //     this.degats = degats;
    //     this.color = color;
    //     calculateMovement(destX, destY); // Calcul des composantes de mouvement en fonction de la destination
    //     loadMunitionImage();
    // }



    public void loadMunitionImage() {
        BufferedImage atlas = Enregistrement.getSpriteAtlas();
        mImages = new BufferedImage[2];
        for (int i = 0; i < 2; i++) {
            mImages[i] = atlas.getSubimage((6 + i) * 40, 8 * 40, 40, 40);
        }

    }

    private void updateDistanceTraveled() {
        // float distanceSquared = differenceX * differenceX + differenceY *
        // differenceY;
        float diffX = differenceX * differenceX;
        float diffY = differenceY * differenceY;
        float distanceSquared = diffX + diffY + 3 * size;
        distanceTraveled += Math.sqrt(distanceSquared);
    }

    // Calcule les composantes de mouvement en fonction de la destination
    private void calculateMovement(int destX, int destY) {
        float angle = calculateAngle(destX, destY); // Calcul de l'angle entre la position actuelle et la destination
        calculateDifferences(angle); // Calcul des composantes de mouvement en fonction de l'angle
    }

    // Calcule l'angle entre la position actuelle et la destination
    private float calculateAngle(int destX, int destY) {
        return (float) Math.atan2(destY - y, destX - x);
    }

    // Calcule les composantes de mouvement en fonction de l'angle
    private void calculateDifferences(float angle) {
        // Les composantes de mouvement sont obtenues en utilisant l'angle et le facteur
        // de ralentissement
        differenceX = (float) Math.cos(angle) * slowdownFactor;
        differenceY = (float) Math.sin(angle) * slowdownFactor;
    }

    // Dessine le projectile à sa position actuelle
    public void createBullet(Graphics g) {

        updateDistanceTraveled();
        // Cast Graphics to Graphics2D
        Graphics2D g2d = (Graphics2D) g.create();

        // Translate and rotate the graphics context
        g2d.translate(x, y); // Translate to the position of the bullet
        double angle = Math.atan2(differenceY, differenceX); // Calculate angle of motion
        g2d.rotate(angle); // Rotate the image to align with the direction of motion

        // Draw the bullet image
        g2d.drawImage(mImages[typeMunitionArme], -size / 2, -size / 2, null); // Draw from the center of the bullet

        // Dispose the graphics context to release resources
        g2d.dispose();
        // Mettre à jour les coordonnées du projectile
        this.x += differenceX;
        this.y += differenceY;
    }

    public void createBulletEnnemy(Graphics g) {

        updateDistanceTraveled();
        // Cast Graphics to Graphics2D
        Graphics2D g2d = (Graphics2D) g.create();

        // Translate and rotate the graphics context
        g2d.translate(x, y); // Translate to the position of the bullet
        double angle = Math.atan2(differenceY, differenceX); // Calculate angle of motion
        g2d.rotate(angle); // Rotate the image to align with the direction of motion

        // Draw the bullet image
        g2d.drawImage(mImages[0], -size / 2, -size / 2, null); // Draw from the center of the bullet

        // Dispose the graphics context to release resources
        g2d.dispose();

        // Mettre à jour les coordonnées du projectile
        this.x += differenceX;
        this.y += differenceY;
    }

    public boolean isOutOfBounds(int width, int height) {
        if (this.x <= this.size || this.y <= this.size || this.x >= width || this.y >= height) {
            return true;
        }
        return false;
    }

    public void rebound (){
        this.differenceX = -this.differenceX;
        this.differenceY = -this.differenceY;
        
    }



    // --------- Getters et Setters ---------

    public int getDegats() {
        return this.degats;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getDifferenceX() {
        return differenceX;
    }

    public void setDifferenceX(float differenceX) {
        this.differenceX = differenceX;
    }

    public float getDifferenceY() {
        return differenceY;
    }

    public void setDifferenceY(float differenceY) {
        this.differenceY = differenceY;
    }

    public float getSlowdownFactor() {
        return slowdownFactor;
    }

    public void setSlowdownFactor(float slowdownFactor) {
        this.slowdownFactor = slowdownFactor;
    }

    public float getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public BufferedImage[] getmImages() {
        return mImages;
    }

    public void setmImages(BufferedImage[] mImages) {
        this.mImages = mImages;
    }

    public void setDistanceTraveled(float distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public int getTypeMunitionArme() {
        return typeMunitionArme;
    }

    public void setTypeMunitionArme(int typeMunitionArme) {
        this.typeMunitionArme = typeMunitionArme;
    }

    public boolean getRebond() {
        return rebond;
    }

    public void setRebond(boolean rebond) {
        this.rebond = rebond;
    }

    
}
