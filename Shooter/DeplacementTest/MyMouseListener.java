package Shooter.DeplacementTest;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    public Crosshair crosshair;
    public ProjectilesManager projectilesManager;
    public Player player;

    public MyMouseListener(Crosshair crosshair, ProjectilesManager projectilesManager, Player player) {
        this.crosshair = crosshair;
        this.projectilesManager = projectilesManager;
        this.player = player;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        crosshair.xCor = e.getX() - 23;
        crosshair.yCor = e.getY() - 46;
        mousePressed(e);
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // Bullet b = new Bullet(player.getX() + 10, player.getY() + 10, (float) player.direction);
            Bullet b = new Bullet(player.getX() + 10, player.getY() + 10, crosshair.xCor + crosshair.cushion, crosshair.yCor + crosshair.cushion, false);
            crosshair.animate();
            projectilesManager.getPlayerBullets().add(b);
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        crosshair.xCor = e.getX() - 23;
        crosshair.yCor = e.getY() - 46;
        mousePressed(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

}
