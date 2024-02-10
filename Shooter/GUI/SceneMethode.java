package Shooter.GUI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface SceneMethode {
    public void render(Graphics g);

	public void mouseClicked(int x, int y);

	public void mouseMoved(int x, int y);

	public void mousePressed(int x, int y);

	public void mouseReleased(int x, int y);
}
