// package Shooter.DeplacementTest;

// import javax.swing.SwingUtilities;

// import Shooter.Managers.EnnemiManager;
// import Shooter.Managers.PlayerManager;
// import Shooter.Managers.ProjectilesManager;
// import Shooter.model.Player;
// import Shooter.DeplacementTest.Plateau;


// public class Game {

// 	public PlayerManager m;
// 	public ProjectilesManager projectilesManager;
// 	public EnnemiManager ennemiManager;
// 	public Plateau p;
// 	public Player player;

// 	private final double FPS_SET = 120.0;
// 	private final double UPS_SET = 60.0;

// 	public Game() {
// 		this.player = new Player("test");
// 		p = new Plateau(player);
// 		m = new PlayerManager(player, p);
// 		ennemiManager = new EnnemiManager(player, p);
// 		projectilesManager = new ProjectilesManager(player, p);
// 	}

// 	public void start() {
// 		double timePerFrame = 1000000000.0 / FPS_SET;
// 		double timePerUpdate = 1000000000.0 / UPS_SET;

// 		long lastFrame = System.nanoTime();
// 		long lastUpdate = System.nanoTime();
// 		long lastTimeCheck = System.currentTimeMillis();

// 		while (true) {

// 			// if (this.pause) {

// 			// Render
// 			if (System.nanoTime() - lastFrame >= timePerFrame) {
// 				SwingUtilities.invokeLater(() -> {
// 					p.update();
// 					p.repaint();
// 				});

// 				lastFrame = System.nanoTime();
// 			}

// 			// Update
// 			if (System.nanoTime() - lastUpdate >= timePerUpdate) {
// 				lastUpdate = System.nanoTime();
// 			}

// 			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {

// 				lastTimeCheck = System.currentTimeMillis();
// 			}
// 		}
// 	}

// }
