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



public class MenuPage extends JPanel {
   
    

    private BufferedImage img;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();

    private Game g1;

	public MenuPage(Game game) {
        
        g1=game;
		setPanelSize();
        //super(game);
		importImg();
		loadSprites();
		//initButtons();
        add(createButton("play", "Play"));
        
        
    }

    private JButton createButton(String text, String pageName) {//creates buttons and connects them to their page 
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = g1.cardLayout;
                JPanel panel = g1.cardPanel;
                layout.show(panel, pageName);
            }
        });
        return button;
    }

    private void setPanelSize() {
		g1.size_screen = new Dimension(1440, 840);

		setMinimumSize(g1.size_screen);
		setPreferredSize(g1.size_screen);
		setMaximumSize(g1.size_screen);

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
