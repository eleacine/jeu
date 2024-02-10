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
		/*this.img = img;

		loadSprites();*/

		random = new Random();
	}

	private void loadSprites() {

		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				sprites.add(img.getSubimage(x * 50, y * 50, 50, 50));
			}
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.setColor(Color.red);
        //g.fillRect(0,0,50,50);


//		g.drawImage(sprites.get(8), 0, 0, null);

//		g.drawImage(img.getSubimage(32 * 9, 32, 32, 32), 0, 0, null);

		for (int x = 0; x < 30; x++) {
            g.setColor(getRndColor());
            g.fillRect(x*50,0,50,50);
        }
        for (int y = 0; y < 18; y++) {
            for (int x = 0; x < 30; x++) {
                g.setColor(getRndColor());
                g.fillRect(x*50,y*50,50,50);
            }

            
        }
			//for (int x = 0; x < 30; x++) {
				//g.drawImage(sprites.get(getRndInt()), x * 50, y * 50, null);
                
			//}
		
	}

	private int getRndInt() {
		return random.nextInt(200);
	}

    //génére des couleurs au hasard
	private Color getRndColor() {
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);

		return new Color(r, g, b);

	}

}


