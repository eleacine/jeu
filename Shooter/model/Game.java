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
    private Thread gameThread;

    private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	public Game() {

		importImg();

		setSize(1500, 900);//taille de l'écran
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		gameScreen = new GameScreen(img);
		add(gameScreen);
        setVisible(true);

	}

	private void importImg() {

		InputStream fichier = getClass().getResourceAsStream("trois_carres.png");

		try {
			img = ImageIO.read(fichier);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    private void start() {
		gameThread = new Thread(this::run); // Utilisez une instance de Game comme Runnable
    gameThread.start();
	}

	private void updateGame() {

		// System.out.println("Game Updated!");
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            game.repaint(); // Ajoutez cette ligne pour forcer la redéfinition du contenu de la fenêtre
            game.start();
        });

	}

    
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		while (true) {

			// Render
			if (System.nanoTime() - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = System.nanoTime();
				frames++;
			}

			// Update
			if (System.nanoTime() - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate = System.nanoTime();
				updates++;
			}

			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}

		}

	}

}