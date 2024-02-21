package Shooter.Managers;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Shooter.model.Player;
import Shooter.model.Bullet;
import Shooter.model.Crosshair;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private Player player;
    private Crosshair crosshair;
    private ProjectilesManager projectilesManager;
    public GameManager gameManager;

    public MyMouseListener(Player player, Crosshair crosshair, ProjectilesManager projectilesManager, GameManager gameManager) {
        this.player = player;
        this.crosshair = crosshair;
        this.projectilesManager = projectilesManager;
        this.gameManager = gameManager;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            if (player.armes.get(player.currentArme).type && player.armes.get(player.currentArme).munition > 0) {
                player.armes.get(player.currentArme).shoot();
                Bullet b = new Bullet(player.getX(), player.getY(), crosshair.xCor + crosshair.cushion,
                        crosshair.yCor + crosshair.cushion, player.armes.get(player.currentArme).power, player.armes.get(player.currentArme).color);
                projectilesManager.addBulletPlayer(b);
            } else if (player.armes.get(player.currentArme).munition > 0 && !player.armes.get(player.currentArme).type) {
                player.armes.get(player.currentArme).shoot();
        
            }
   
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        crosshair.xCor = e.getX() - 23;
        crosshair.yCor = e.getY() - 46;
        mousePressed(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        crosshair.xCor = e.getX() - 23;
        crosshair.yCor = e.getY() - 46;
        mousePressed(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public Crosshair getCrosshair (){
        return this.crosshair;
    }
}
