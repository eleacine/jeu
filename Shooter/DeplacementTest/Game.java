package Shooter.DeplacementTest;

import javax.swing.SwingUtilities;

public class Game {
    
    public Manager m;
    public Plateau p;
    public Player player;

    private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

    public Game() {
        player = new Player();
        p = new Plateau(player);
        m = new Manager(player, p);
        p.addKeyListener(m);
    }

    public void start() {
        	double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();


		while (true) {

			// if (this.pause) {

			// Render
			if (System.nanoTime() - lastFrame >= timePerFrame) {
				SwingUtilities.invokeLater(() -> {
                    p.update();
                    p.repaint();
				});

				lastFrame = System.nanoTime();
			}

			// Update
			if (System.nanoTime() - lastUpdate >= timePerUpdate) {
				lastUpdate = System.nanoTime();
			}

			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
			
				lastTimeCheck = System.currentTimeMillis();
			}
		}
    }
}
