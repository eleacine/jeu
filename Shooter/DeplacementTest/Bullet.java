package Shooter.DeplacementTest;

import java.awt.Color;
import java.awt.Graphics;

// test faire plus tard une classe projectile avec des attributs comme la vitesse et les degats qui changent en fonction de l'arme
public class Bullet {

    public int size = 10;  // Taille du projectile
    public float xCor;  // Coordonnée x du projectile
    public float yCor;  // Coordonnée y du projectile
    public float differenceX;  // Composante de mouvement en x
    public float differenceY;  // Composante de mouvement en y
    

    private int degats;  // Degats du projectile
    private float slowdownFactor = 5f;  // Facteur de ralentissement pour ajuster la vitesse du projectile

    // Constructeur de la classe Bullet
    // xCor, yCor : Coordonnées initiales du projectile
    // destX, destY : Coordonnées de la destination du projectile
    // collision : Non utilisé dans cette version
    public Bullet(int xCor, int yCor, int destX, int destY, boolean collision) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.degats = 50;
        calculateMovement(destX, destY);  // Calcul des composantes de mouvement en fonction de la destination
    }

    public Bullet(int xCor, int yCor, float angle) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.degats = 50;
        calculateDifferences(angle);  // Calcul des composantes de mouvement en fonction de l'angle
    }

    // pour les ennemis pour l'instant
    public Bullet(int xCor, int yCor, int destX, int destY, boolean collision, int degats) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.degats = degats;
        calculateMovement(destX, destY);  // Calcul des composantes de mouvement en fonction de la destination
    }

    // Calcule les composantes de mouvement en fonction de la destination
    private void calculateMovement(int destX, int destY) {
        float angle = calculateAngle(destX, destY);  // Calcul de l'angle entre la position actuelle et la destination
        calculateDifferences(angle);  // Calcul des composantes de mouvement en fonction de l'angle
    }

    // Calcule l'angle entre la position actuelle et la destination
    private float calculateAngle(int destX, int destY) {
        return (float) Math.atan2(destY - yCor, destX - xCor);
    }

    // Calcule les composantes de mouvement en fonction de l'angle
    private void calculateDifferences(float angle) {
        // Les composantes de mouvement sont obtenues en utilisant l'angle et le facteur de ralentissement
        differenceX = (float) Math.cos(angle) * slowdownFactor;
        differenceY = (float) Math.sin(angle) * slowdownFactor;
    }

    // Dessine le projectile à sa position actuelle
    public void createBullet(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillOval((int) xCor, (int) yCor, size, size);

        this.xCor += differenceX;  // Met à jour la coordonnée x en fonction de la composante de mouvement en x
        this.yCor += differenceY;  // Met à jour la coordonnée y en fonction de la composante de mouvement en y
    }

    public boolean isOutOfBounds(int width, int height) {
        return xCor < 0 || xCor > width || yCor < 0 || yCor > height;
    }

 // ----------------- Getters et Setters ---------------------

    public int getDegat (){
        return this.degats;
    }

    public float getX(){
        return this.xCor;
    }

    public float getY(){
        return this.yCor;
    }

    public int getSize(){
        return this.size;
    }

    
}

