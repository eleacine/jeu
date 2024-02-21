package Shooter.model;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Shooter.GUI.MenuPage;
import Shooter.GUI.PlayPage;
import Shooter.GUI.SettingsPage;
import Shooter.Managers.GameManager;


public class Game extends JFrame implements Runnable {

	
	public Plateau gamePlateau;
	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;
	protected List<Personnage> perso_list;
	protected Armes[] armes_list;
	public int level = 1;

	public Dimension size_screen;
	public CardLayout cardLayout;
	public JPanel cardPanel;
	// Classes
	private MenuPage menu;
	private PlayPage playing;
	private SettingsPage settings;

	public boolean begin = false;


	public GameManager gameManager;
	

	public Game() {
		Player player = new Player(null);

		// A MODIFIER POUR PLUS TARD -----------------------------------------------------------

		this.gamePlateau = new Plateau();
		this.gameManager = new GameManager(gamePlateau, player);
		this.gamePlateau.gameManager = gameManager;

		this.addKeyListener(gameManager.getPlayerManager());
		setFocusable(true);
		this.addMouseMotionListener(gameManager.getMyMouseListener());
		this.addMouseListener(gameManager.getMyMouseListener());

		// ----------------------------------------------------------------------------------------

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		getContentPane().add(cardPanel);
		createPages();
		size_screen = new Dimension(1440, 840);
		setMinimumSize(size_screen);
		setPreferredSize(size_screen);
		setMaximumSize(size_screen);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}

	private void createPages() {

		cardPanel.add(new MenuPage(this), "Menu");
		this.playing = new PlayPage(this);
		cardPanel.add(playing, "Play");
		cardPanel.add(new SettingsPage(this), "Settings");
		cardLayout.show(cardPanel, "Menu");

	}


	private void updateGame() {

	}

	public static void main(String[] args) {

		Game game = new Game();
		game.setVisible(true);
		game.run();

	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		long now;

		while (true) {
			now = System.nanoTime();

			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = now;
				frames++;

			}

			// Update
			if (now - lastUpdate >= timePerUpdate) {
				if (begin) {
					SwingUtilities.invokeLater(() -> {
						this.gameManager.update();
						this.gamePlateau.repaint();
					});
				}
				lastUpdate = now;
				updates++;
			}

			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				// System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}

		}

	}

	public MenuPage getMenu() {
		return menu;
	}

	public PlayPage getPlaying() {
		return playing;
	}

	public SettingsPage getSettings() {
		return settings;
	}

	public int getLevel() {
		return this.level;
	}

	public void addLevel() {
		this.level++;
	}

}