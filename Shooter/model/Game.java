package Shooter.model;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Shooter.GUI.EditingMode;
import Shooter.GUI.GameOverPage;
import Shooter.GUI.MenuPage;
import Shooter.GUI.PlayPage;
import Shooter.GUI.SettingsPage;
import Shooter.Managers.GameManager;
import Shooter.factory.EnemyLevelLoader;

public class Game extends JFrame implements Runnable {

	public Plateau gamePlateau;
	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;
	protected List<Personnage> perso_list;
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

		// load des personnages
		this.perso_list = new ArrayList<>();
		Player player = new Player(null);
		this.perso_list.add(player);
		EnemyLevelLoader enemyLoader = new EnemyLevelLoader(level);
		enemyLoader.loadLevelEnemies("Shooter/factory/EnemiesForLevels.txt");
		this.perso_list = enemyLoader.createEnemiesForLevel();

		//perso_list.add(new Gardien());

		//print enemy list to check
		System.out.println("Enemy List:");
    	for (Personnage enemy : perso_list) {
        System.out.println("Type: " + enemy.getClass().getSimpleName());
        System.out.println("Coordinates: (" + enemy.getX() + ", " + enemy.getY() + ")");
        System.out.println("---");
    	}
    	System.out.println("Total Enemies: " + perso_list.size());

		// initialisation du plateau et du game Manager
		this.gamePlateau = new Plateau();
		this.gameManager = new GameManager(this, gamePlateau, player);
		this.gamePlateau.gameManager = gameManager;

		// initialisation des listeners
		this.addKeyListener(gameManager.getPlayerManager());
		setFocusable(true);
		this.addMouseMotionListener(gameManager.getMyMouseListener());
		this.addMouseListener(gameManager.getMyMouseListener());

		// initialisation du card layout
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
		cardPanel.add(new EditingMode(this), "Editing");
		cardPanel.add(new GameOverPage(this), "GameOver");
		cardLayout.show(cardPanel, "Menu");

	}

	private void updateGame() {
		this.gameManager.update();
		this.gamePlateau.repaint();
		isGameOver();
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

		// int frames = 0;
		// int updates = 0;

		long now;

		while (true) {
			now = System.nanoTime();

			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = now;
				// frames++;

			}

			// Update
			if (now - lastUpdate >= timePerUpdate) {
				// play est cliqué = début du jeu
				if (begin) {
					SwingUtilities.invokeLater(() -> {
						updateGame();
					});
				}
				lastUpdate = now;
				// updates++;
			}

			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				// System.out.println("FPS: " + frames + " | UPS: " + updates);
				// frames = 0;
				// updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}

		}

	}

	public void isGameOver() {
		if (gameManager.getPlayer().getSante() <= 0) {
			reset();
			begin = false;

			cardLayout.show(cardPanel, "GameOver");

		}
	}

	public void reset() {
		gameManager.reset();
		clearArrayList();
	}

	private void clearArrayList() {
		List<Personnage> tmp = new ArrayList<>();
		tmp.add(perso_list.get(0));
		EnemyLevelLoader enemyLoader = new EnemyLevelLoader(level);
		enemyLoader.loadLevelEnemies("Shooter/factory/EnemiesForLevels.txt");

		// tmp = enemyLoader.createEnemiesForLevel();
		tmp.addAll(enemyLoader.createEnemiesForLevel());

		// Vérifier si le premier élément est une instance de Player avant la conversion
		if (!perso_list.isEmpty()) {

			// Vider la liste
			perso_list.clear();
			this.perso_list = tmp;
		}

		gameManager.getEnnemiManager().perso_list = perso_list;

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

	public List<Personnage> getPersoList() {
		return this.perso_list;
	}

	public void addLevel() {
		this.level++;
	}

}