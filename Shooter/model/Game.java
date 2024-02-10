package Shooter.model;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game extends JFrame {

	private GameScreen gameScreen;

	private BufferedImage img;

	public Game() {

		importImg();

		setSize(1500, 900);//taille de l'écran
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		gameScreen = new GameScreen(img);
		add(gameScreen);


	}

	private void importImg() {

		InputStream is = getClass().getResourceAsStream("trois_carres.png");

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            game.repaint(); // Ajoutez cette ligne pour forcer la redéfinition du contenu de la fenêtre
        });

	}

}