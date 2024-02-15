package Shooter.model;
import java.awt.Color;
import java.awt.Graphics;

public class Bullet {

    public int size = 10;  // Taille du projectile
    public float x;  // Coordonnée x du projectile
    public float y;  // Coordonnée y du projectile
    public float differenceX;  // Composante de mouvement en x
    public float differenceY;  // Composante de mouvement en y
    private int degats;
    private float slowdownFactor = 5f;  // Facteur de ralentissement pour ajuster la vitesse du projectile

    // Constructeur de la classe Bullet
    // x, y : Coordonnées initiales du projectile
    // destX, destY : Coordonnées de la destination du projectile
    // collision : Non utilisé dans cette version
    public Bullet(int x, int y, int destX, int destY, int degats) {
        this.x = x;
        this.y = y;
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
        return (float) Math.atan2(destY - y, destX - x);
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
        g.fillOval((int) x, (int) y, size, size);
        this.x += differenceX;  // Met à jour la coordonnée x en fonction de la composante de mouvement en x
        this.y += differenceY;  // Met à jour la coordonnée y en fonction de la composante de mouvement en y
    }

    public boolean isOutOfBounds (int width, int height){
        if (this.x <= this.size || this.y <= this.size || this.x >= width || this.y >= height){
            return true;
        }
        return false;
    }

    public int getDegats( ){
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

    // 
}
