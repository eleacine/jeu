package Shooter.GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import Shooter.model.Game;



public class MenuPage extends GameScene {
   
    

    private BufferedImage img;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();

	public MenuPage(Game g1) {
        super(g1);
		setPanelSize();
        //super(game);
		importImg();
		loadSprites();
		//initButtons();
        add(createButton("Play", "Play"));
        add(createButton("Settings", "Settings"));
		add(createButtonExit("Quit"));
        
    }

   

    private void setPanelSize() {
		game.size_screen = new Dimension(1440, 840);

		setMinimumSize(game.size_screen);
		setPreferredSize(game.size_screen);
		setMaximumSize(game.size_screen);

	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;
        JButton button = new JButton("Click Me!");
		
	}




	private void importImg() {

		URL is = getClass().getResource("../image/cible.png");
        

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadSprites() {
        for (int x = 0; x < 3; x++) {
            sprites.add(img.getSubimage(50 * x, 0, 50, 50));
        }

	}

	

	
	
	
	

}
