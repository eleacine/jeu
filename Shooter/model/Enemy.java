package Shooter.model;


import java.awt.Color;
import java.awt.Graphics;

import Shooter.Managers.ProjectilesManager;

public class Enemy extends Personnage {

	public int id;
	public int power = 10; // faire des dégats de collision et de tir
	public int collisionPower = 50;
	public int destX;
	public int destY;
	public float differenceX;
	public float differenceY;
	public int frequency;
	private long lastShotTime;
	public int detectionRadius; // Rayon de détection du joueur
	public int tailleBar = 40;
	public int vieTotal = 100;
	public double direction; //direction pour ajouter le hallo de vision

	public Color color;

	public Enemy(int x, int y, int size, int sante, int id, int maxSpeed, int power, int collisionPower, int frequency, int detectionRadius, Color color) {
		super(x, y, size, sante, maxSpeed);
		this.id = id;
		this.power = power;
		this.collisionPower = collisionPower;
		this.frequency = frequency;
		this.detectionRadius = detectionRadius;
		this.color = color;
	}

	public void updateBehavior(Player player) {

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
		this.differenceX = (float) Math.cos(angle) * this.maxSpeed;
		this.differenceY = (float) Math.sin(angle) * this.maxSpeed;
	}

	public void move() {
		if (x > destX + 10) {
			x -= maxSpeed;
		}
		if (x < destX + 10) {
			x += maxSpeed;
		}
		if (y > destY + 10) {
			y -= maxSpeed;
		}
		if (y < destY + 10) {
			y += maxSpeed;
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
		return maxSpeed;
	}

	public void setSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
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


	public float getDurerVie() {
        return this.sante / (float) this.vieTotal;
    }


    private int getTailleBar(Enemy e) {
        return (int) (this.tailleBar * e.getDurerVie());
    }


	public void drawBarVie(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int) this.getX() - (getTailleBar(this) / 2), (int) this.getY() - 10, getTailleBar(this), 7);
	}

}
