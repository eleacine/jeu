package Shooter.Managers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Shooter.model.Player;
import Shooter.model.A3;
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

            // si l'arme est de type tir
            if (player.getArmes().get(player.getCurrentArme()).type && player.getArmes().get(player.getCurrentArme()).munition > 0) {
                player.getArmes().get(player.getCurrentArme()).shoot();
                Bullet b = new Bullet(player.getX(), player.getY(), crosshair.getX() + crosshair.getCushion(),
                        crosshair.getY() + crosshair.getCushion(), player.getArmes().get(player.getCurrentArme()).power, player.getArmes().get(player.getCurrentArme()).color);
                projectilesManager.addBulletPlayer(b);
            
            //  si l'arme est de type piege / mine / grenade
            } else if (player.getArmes().get(player.getCurrentArme()).munition > 0 && !player.getArmes().get(player.getCurrentArme()).type) {
                player.getArmes().get(player.getCurrentArme()).shoot();
                A3 mine = new A3(crosshair.getX(), crosshair.getY());
                gameManager.getGamePlateau().pieges.add(mine);
        
            }else{
                player.getArmes().get(player.getCurrentArme()).besoinRecharge();

            }
   
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        crosshair.setX(e.getX() - 23);
        crosshair.setY(e.getY() - 46);
        mousePressed(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

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
