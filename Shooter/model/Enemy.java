package Shooter.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.awt.Point;

import javax.swing.ImageIcon;

import Shooter.Managers.ManagerCase;
import Shooter.Managers.ProjectilesManager;

public class Enemy extends Personnage {

	protected int id;
	protected int power; // faire des dégats de collision et de tir
	protected int collisionPower;
	protected int destX;
	protected int destY;
	protected float differenceX;
	protected float differenceY;
	protected int frequency;
	protected long lastShotTime;
	protected int detectionRadius; // Rayon de détection du joueur
	protected int tailleBar = 40;
	protected int vieTotal = 100;
	private Deque<Point> previousPositions; 
    private static final int MAX_PREVIOUS_POSITIONS = 5;
	
	
	public double direction; // direction pour ajouter le halo de vision
	protected ImageIcon sprite;

	public Color color;

	protected int prevX = 0;
	protected int prevY = 0;

	protected int[][] direction2 = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public Enemy(int size, int sante, int id, int maxSpeed, int power, int collisionPower, int frequency,
			int detectionRadius, Color color) {
		super(size, sante, maxSpeed);
		previousPositions = new ArrayDeque<>();
		this.x = new Random().nextInt(1400);
		this.y = new Random().nextInt(800);
		this.id = id;
		this.power = power;
		this.collisionPower = collisionPower;
		this.frequency = frequency;
		this.detectionRadius = detectionRadius;
		this.color = color;
		loadSprite();
	}

	public Enemy(int x, int y, int size, int sante, int maxSpeed, int id, int power, int collisionPower, int frequency,
			int detectionRadius, Color color) {
		super(x, y, size, sante, maxSpeed);
		previousPositions = new ArrayDeque<>();
		this.id = id;
		this.power = power;
		this.collisionPower = collisionPower;
		this.frequency = frequency;
		this.detectionRadius = detectionRadius;
		this.color = color;
		loadSprite();
	}

	public Enemy(int x, int y, int size, int sante, int maxSpeed, int id, int power, int collisionPower,
			int detectionRadius, Color color) {
		super(x, y, size, sante, maxSpeed);
		previousPositions = new ArrayDeque<>();
		this.id = id;
		this.power = power;
		this.collisionPower = collisionPower;
		this.detectionRadius = detectionRadius;
		this.color = color;
		loadSprite();
	}

	public void loadSprite() {
		URL imageUrl = getClass().getResource("Ee_test.png");
		ImageIcon originalImage = new ImageIcon(imageUrl);

		double scale = 5;
		int newWidth = (int) (size * scale);
		int newHeight = (int) (size * scale);

		Image resizedImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		ImageIcon enemyImage = new ImageIcon(resizedImage);
		this.sprite = enemyImage;
	}

	public void updateBehavior(Player player, int[][] map, ProjectilesManager projectilesManager) {
	}

	public void shootBehavior(Player player, ProjectilesManager projectilesManager) {
	}

	// protected float calculateAngle(int x1, int y1, int x2, int y2) {
	// 	return (float) Math.atan2(y2 - y1, x2 - x1);
	// }

	// public void calculateDifferences(float angle) {
	// 	this.differenceX = (float) Math.cos(angle) * this.maxSpeed;
	// 	this.differenceY = (float) Math.sin(angle) * this.maxSpeed;
	// }

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

	public void drawEnemy(Graphics g) {
		// Dessiner l'ennemi à sa position actuelle
        if (sprite != null && sprite.getImage() != null) {
            g.drawImage(sprite.getImage(), x, y, size, size, null);
        } else {
            g.setColor(this.color);
            g.fillOval(this.x, this.y, this.size, this.size);
        }

        
        g.setColor(Color.BLACK); 
        for (Point point : previousPositions) {
            g.fillOval(point.x+40, point.y+15, 5, 15); 
        }

    }
	

	public void moveTowardsPlayer(int[][] distances, Player player) {
		int nextX = convertPositionToTile(x); // Convertir la position X de l'ennemi en coordonnées de tableau
		int nextY = convertPositionToTile(y); // Convertir la position Y de l'ennemi en coordonnées de tableau

		if (prevX > this.x){
			nextX += 1; // Convertir la position X de l'ennemi en coordonnées de tableau
		} 

		if (prevY > this.y){
			nextY += 1; // Convertir la position Y de l'ennemi en coordonnées de tableau
		}
		
		// Déterminer la direction vers la case avec la distance la plus courte
		int minDistance = distances[nextY][nextX];
		int dirX = 0;
		int dirY = 0;

		// Parcourir les directions pour trouver la case avec une distance plus courte
		for (int[] dir : direction2) {
			int newX = nextX + dir[0];
			int newY = nextY + dir[1];

			// Vérifier si la case est valide et si la distance est plus courte
			if (newX >= 0 && newX < distances[0].length && newY >= 0 && newY < distances.length && distances[newY][newX] < minDistance && distances[newY][newX] != 100){

				minDistance = distances[newY][newX];
				dirX = dir[0];
				dirY = dir[1];
				break;
		
			}
		
		}

		// Déplacer l'ennemi dans la direction choisie
		move(dirX, dirY);
	}

	public void move (int x, int y){
		prevX = this.x;
		prevY = this.y;
		if (x == 1) {
			this.x += this.maxSpeed;
		} else if (x == -1) {
			this.x -= this.maxSpeed;
		}
	
		if (y == 1) {
			this.y += this.maxSpeed;
		} else if (y == -1) {
			this.y -= this.maxSpeed;
		}
		
		Point newPoint = new Point(this.x, this.y);
		if (previousPositions.isEmpty() || distanceBetweenPoints(previousPositions.getFirst(), newPoint) >= 10) {
			previousPositions.addFirst(newPoint);
			
			
			while (previousPositions.size() > MAX_PREVIOUS_POSITIONS) {
				previousPositions.removeLast();
			}
		}
	}
	
	
	private double distanceBetweenPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
	}	


    public void shoot(Player player, ProjectilesManager projectilesManager) {
        // Implémentez la logique de tir spécifique pour cet ennemi
        // Par exemple, tirer une balle vers le joueur
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime > getFrequency()) {
            Bullet bullet = new Bullet(x, y, player.x, player.y, power);
            projectilesManager.getEnemyBullets().add(bullet);
            lastShotTime = currentTime;
        }
    }

	public void infligerDegatsCollision(Player player) {
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastShotTime > getFrequency()) {
			player.infligerDegats(collisionPower);
			lastShotTime = currentTime;
		}
	}


	  public boolean isWallBetween(int x1, int y1, int[][] map, int x2, int y2) {
        // Calculer les coordonnées des cases de la ligne de mire entre (x1, y1) et (x2,
        // y2)
        List<Point> lineOfSight = calculateLineOfSight(x1, y1, x2, y2);

        // Vérifier chaque case de la ligne de mire pour la présence d'un mur
        for (Point p : lineOfSight) {

            if (isWall(p.x, p.y, map)) {
                return false; // Un mur a été trouvé sur la ligne de mire
            }
        }

        return true; // Aucun mur trouvé sur la ligne de mire
    }

    private List<Point> calculateLineOfSight(int x1, int y1, int x2, int y2) {
        List<Point> lineOfSight = new ArrayList<>();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        int currentX = x1;
        int currentY = y1;

        while (true) {
            lineOfSight.add(new Point(currentX, currentY));

            if (currentX == x2 && currentY == y2) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                currentX += sx;
            }
            if (e2 < dx) {
                err += dx;
                currentY += sy;
            }
        }

        return lineOfSight;
    }

	protected int convertPositionToTile(int position) {
		// Convertit une position en coordonnées de tableau
		return position / 40;
	}

	
    protected boolean isWall(int xPos, int yPos, int[][] map) {
        // Vérifie si une case est un mur
        int x = convertPositionToTile(xPos);
        int y = convertPositionToTile(yPos);
        return map[y][x] == ManagerCase.MUR || map[y][x] == ManagerCase.CASSANT;
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
		// Déterminer les coordonnées x et y pour centrer le rectangle sur l'ennemi
		int barX = (int) this.getX() - (getTailleBar(this) / 2)+20;
		int barY = (int) this.getY() - 10;
	
		// Dessiner le rectangle de la barre de vie
		g.setColor(Color.GREEN);
		g.fillRect(barX, barY, getTailleBar(this), 7);
	}

	public ImageIcon getSprite(){
		return this.sprite;
	}
}
