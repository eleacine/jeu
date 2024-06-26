package Shooter.model;


import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Shooter.GUI.*;
import Shooter.Managers.GameManager;
import Shooter.factory.EnemyLevelLoader;


public class Game extends JFrame implements Runnable {

	public Plateau gamePlateau;
	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;
	protected List<Personnage> perso_list;
	public Dimension size_screen;
	public CardLayout cardLayout;
	public JPanel cardPanel;

	// Classes
	private MenuPage menu;
	private PlayPage playing;
	private SettingsPage settings;

	public boolean isRunning = true;
	public boolean begin = false;
	public boolean gameMode=false; //False: mode normal 	//True: mode créateur 

	public int nbLevel = 5;

	public GameManager gameManager;

	protected boolean soundEnabled = false;

	public Game() {

		// initialisation:liste de perso, perso et des loaders 
		this.perso_list = new ArrayList<>();
		Player player = new Player(null);
		EnemyLevelLoader enemyLoader = new EnemyLevelLoader(player.getLevel());

		//ajout: enemies et perso
		enemyLoader.loadLevelEnemies("Shooter/factory/EnemiesForLevels.txt"); //peut-être faudrait load les enemies quand le bouton play est appuyé 
		this.perso_list = enemyLoader.createEnemiesForLevel();
		this.perso_list.add(0, player);

		// perso_list.add(new EnemyIA(1000, 150));
		// perso_list.add(new EnemySniper(1000, 150));
		perso_list.add(new Enemy5(1000, 150));

		// initialisation du plateau et du game Manager
		this.gamePlateau = new Plateau(gameMode);
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
		cardPanel.add(new GameWinPage(this), "GameWin");
		cardLayout.show(cardPanel, "Menu");

	}

	private void updateGame() {
		this.gamePlateau.repaint();
		this.gameManager.update();
		isGameOver();
		win();
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

		while (isRunning) {

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
		System.out.println("Level being created after clear:"+gameManager.getPlayer().getLevel());
		EnemyLevelLoader enemyLoader = new EnemyLevelLoader(gameManager.getPlayer().getLevel());
		enemyLoader.loadLevelEnemies("Shooter/factory/EnemiesForLevels.txt");
		tmp.addAll(enemyLoader.createEnemiesForLevel());

		// Vérifier si le premier élément est une instance de Player avant la conversion
		if (!perso_list.isEmpty()) {
			// Vider la liste
			perso_list.clear();
			this.perso_list = tmp;
		}

		gameManager.getEnnemiManager().setPerso_list(perso_list); 

	}

	private void win() {
		if (perso_list.size() == 1 && begin) {
			begin = false;

			Player player = (Player) perso_list.get(0);
			if (player.getLevel() == nbLevel) {
				cardLayout.show(cardPanel, "Win");
			} else { 
              showBlackScreenForDelay(1000);
				
			}
		}
	}

	public void waitFortransiion() {
		// System.out.println("I'm being called: waitFortransiion");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void nextLevel() {
		// System.out.println("I'm being called: nextLevel");
		gameManager.getPlayer().setLevel();
		reset();
		
	}

	private void showBlackScreenForDelay(int delay) {
		// System.out.println("I'm being called: showBlackScreenForDelay");
        JFrame blackFrame = new JFrame();
        blackFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        blackFrame.setUndecorated(true);
        blackFrame.getContentPane().setBackground(Color.BLACK);
        blackFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        blackFrame.setLocationRelativeTo(null);
        blackFrame.setAlwaysOnTop(true);

        blackFrame.setOpacity(0.0f);
        blackFrame.setVisible(true);
		// System.out.println("debut");
		Timer timer = new Timer(10, new ActionListener() {
			float opacity = 0.0f;
			float step = 0.06f;
			boolean expanding = true;
		
			@Override
			public void actionPerformed(ActionEvent e) {
				if (expanding) {
					opacity += step;
					if (opacity >= 1.0f) {
						expanding = false;
						try {
							Thread.sleep(delay);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
						
						nextLevel();
						begin = true;
					}
				} else {
					opacity -= step;
					if (opacity <= 0.0f) {
						((Timer) e.getSource()).stop();
						blackFrame.dispose();
					}
				}
				opacity = Math.min(Math.max(opacity, 0.0f), 1.0f);
				blackFrame.setOpacity(opacity);
			}
		});
		
		timer.start();
			// System.out.println("fin");
    }



	// --------- GETTERS et SETTERS -----------
	public MenuPage getMenu() {
		return menu;
	}

	public boolean isBegin(){
		return begin;
	}

	public PlayPage getPlaying() {
		return playing;
	}

	public SettingsPage getSettings() {
		return settings;
	}

	public List<Personnage> getPersoList() {
		return this.perso_list;
	}

	public boolean getSound () {
		return soundEnabled;
	}

	public void updateSound(boolean soundEnabled) {
		this.soundEnabled = soundEnabled;
	}

}