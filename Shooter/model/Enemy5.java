package Shooter.model;

import java.awt.Color;

import Shooter.Managers.ProjectilesManager;

public class Enemy5 extends Enemy {

    public Enemy5(int x, int y) {
        super(x, y, 50, 100, 1, 2, 20, 50, 350, 200, new Color(255, 0, 255));
    }

    @Override
    public void updateBehavior(Player player, int[][] floodfill, ProjectilesManager projectilesManager) {
        // if (isPlayerDetected(player) && !isWallBetween(getX(), getY(), floodfill, player.getX(), player.getY())) {
        //     shootBehavior(player, projectilesManager);
        // } else {
        //     moveTowardsPlayer(floodfill, player);
        // }
        eviteTir(projectilesManager);
    }

    public void eviteTir(ProjectilesManager projectilesManager){
        for (Bullet b : projectilesManager.getplayerBullets()){
            if (detecteBullet(b)){
                avoidBullet(b);
            } 
        }

    }

    public boolean detecteBullet(Bullet b){
        if (Math.abs(b.getX() - getX()) < 50 && Math.abs(b.getY() - getY()) < 250){
            return true;
        }
        return false;
    }

    // public void avoidBullet(Bullet b) {
    //     // Calculer la direction de la balle par rapport à l'ennemi
    //     double angleToBullet = Math.atan2(b.getY() - getY(), b.getX() - getX());
        
    //     // Inverser la direction pour éviter la balle
    //     angleToBullet += Math.PI;
        
    //     // Calculer la direction perpendiculaire à la direction de la balle
    //     double perpendicularAngle = angleToBullet + Math.PI / 2; // 90 degrés
        
    //     // Calculer les coordonnées de déplacement en fonction de la nouvelle direction
    //     double newDifferenceX = Math.cos(perpendicularAngle) * getMaxSpeed();
    //     double newDifferenceY = Math.sin(perpendicularAngle) * getMaxSpeed();
        
    //     // Convertir les coordonnées de déplacement en entiers
    //     int intDifferenceX = (int) Math.round(newDifferenceX);
    //     int intDifferenceY = (int) Math.round(newDifferenceY);
        
    //     // Déplacer l'ennemi dans la nouvelle direction
    //     move(intDifferenceX, intDifferenceY);
    // }

    public void avoidBullet(Bullet b) {
        // Calculer la direction de la balle par rapport à l'ennemi
        double angleToBullet = Math.atan2(b.getY() - getY(), b.getX() - getX());
        
        // Calculer la direction de déplacement actuelle de l'ennemi
        double currentAngle = Math.atan2(getDifferenceY(), getDifferenceX());
        
        // Calculer la différence d'angle entre la balle et l'ennemi
        double angleDifference = angleToBullet - currentAngle;
        
        // Rendre l'angleDifference dans le domaine de -PI à PI pour faciliter la comparaison
        while (angleDifference > Math.PI) angleDifference -= 2 * Math.PI;
        while (angleDifference < -Math.PI) angleDifference += 2 * Math.PI;
        
        // Déterminer la direction de déplacement en fonction de la différence d'angle
        if (Math.abs(angleDifference) < Math.PI / 4) {
            // Si l'angle de la balle est similaire à la direction de l'ennemi, avancer
            // moveForward();
            move(0, 1);
        } else if (Math.abs(angleDifference) > 3 * Math.PI / 4) {
            // Si l'angle de la balle est presque opposé à la direction de l'ennemi, reculer
            // moveBackward();
            move(0, -1);
        } else {
            // Sinon, déplacer latéralement (aller à droite ou à gauche)
            if (angleDifference > 0) {
                // moveRight();
                move(1, 0);
            } else {
                // moveLeft();
                move(-1, 0);
            }
        }
    }

 

    
    
    
    
    
}
