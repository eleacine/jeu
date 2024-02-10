package Shooter.model;


    import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GameScreen extends JPanel {

	private Random random;
	private BufferedImage img;

	private ArrayList<BufferedImage> sprites = new ArrayList<>();

	public GameScreen(BufferedImage img) {
		this.img = img;
		loadSprites();
		random = new Random();
	}
	
	//permet de couper l'image trois_carrees en 3 carres séparé, principe coordonnees
	private void loadSprites() {

			for (int x = 0; x < 3; x++) {
				sprites.add(img.getSubimage(50 * x, 0, 50, 50));
			}
		

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        
		//nombre de carreaux sur l'ecran 18 dans la longeueur et 30 dans la largeur pour dimension 1500*900
	
        for (int y = 0; y < 18; y++) {
            for (int x = 0; x < 30; x++) {
				g.drawImage(sprites.get(getRndInt()), x * 50, y * 50, null);
				//c'est comme des coordonnées en position (0,0)sur l'ecran il aura un carreau vert en (14,20) un rouge
			}
        }
		
	}
	//génére des couleurs au hasard a partir de l'image
	private int getRndInt() {
		return random.nextInt(3);
	}

}


