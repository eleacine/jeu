package Shooter.model;


import java.awt.Color;

import Shooter.Managers.ProjectilesManager;

public class Enemy extends Personnage {

	public int id;
	public int power = 10; // faire des dégats de collision et de tir
	public int collisionPower = 50;
	public int speed;
	public int destX;
	public int destY;
	public float differenceX;
	public float differenceY;
	public int frequency;
	private long lastShotTime;
	public int detectionRadius; // Rayon de détection du joueur

	public Color color;


	// public Ennemi(int id, int destX, int destY) {
	// 	this.speed = 2;
	// 	this.sante = 100;
	// 	this.id = id;
	// 	this.size = 55;
	// }

	public void updateBehavior(Player player) {
        // Implémentez le comportement spécifique pour cet ennemi
        // Par exemple, suivi simple du joueur


        // float angle = calculateAngle(x, y, player.x, player.y);
        // calculateDifferences(angle);
        // x += differenceX;
        // y += differenceY;

    }

    public void shootBehavior(Player player, ProjectilesManager projectilesManager) {
        // Implémentez la logique de tir spécifique pour cet ennemi
        // Par exemple, tirer une balle vers le joueur
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime > getFrequency()) {
            Bullet bullet = new Bullet(x, y, player.x, player.y, power);
            projectilesManager.getEnemyBullets().add(bullet);
            lastShotTime = currentTime;
        }
    }

    protected float calculateAngle(int x1, int y1, int x2, int y2) {
        return (float) Math.atan2(y2 - y1, x2 - x1);
    }

	public void calculateDifferences(float angle) {
		this.differenceX = (float) Math.cos(angle) * this.speed;
		this.differenceY = (float) Math.sin(angle) * this.speed;
	}

	public void move() {
		if (x > destX + 10) {
			x -= speed;
		}
		if (x < destX + 10) {
			x += speed;
		}
		if (y > destY + 10) {
			y -= speed;
		}
		if (y < destY + 10) {
			y += speed;
		}
	}

	public boolean isPlayerDetected(Player player) {
		// Calculer la distance entre l'ennemi et le joueur
		double distance = Math.sqrt(Math.pow(player.x - this.x, 2) + Math.pow(player.y - this.y, 2));

		// Vérifier si le joueur est dans le rayon de détection
		if (distance <= detectionRadius) {
			// Le joueur est détecté
			return true;
		}
		return false;
	}

	public boolean detectCollision(float newX, float newY, int newSize) {
		if (x - newSize < newX && x + size > newX && y - newSize < newY && y + size > newY) {
			return true;
		}
		return false;
	}

	// ------------- Getters et setters ---------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getCollisionPower() {
		return collisionPower;
	}

	public void setCollisionPower(int collisionPower) {
		this.collisionPower = collisionPower;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDestX() {
		return destX;
	}

	public void setDestX(int destX) {
		this.destX = destX;
	}

	public int getDestY() {
		return destY;
	}

	public void setDestY(int destY) {
		this.destY = destY;
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

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public long getLastShotTime() {
		return lastShotTime;
	}

	public int getDetectionRadius() {
		return detectionRadius;
	}

}
