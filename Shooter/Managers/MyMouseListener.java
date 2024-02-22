package Shooter.Managers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Shooter.model.Player;
import Shooter.model.A3;
import Shooter.model.A4;
import Shooter.model.Bullet;
import Shooter.model.Crosshair;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private Player player;
    private Crosshair crosshair;
    private ProjectilesManager projectilesManager;
    public GameManager gameManager;

    public MyMouseListener(Player player, Crosshair crosshair, ProjectilesManager projectilesManager,
            GameManager gameManager) {
        this.player = player;
        this.crosshair = crosshair;
        this.projectilesManager = projectilesManager;
        this.gameManager = gameManager;
    }

    /*
     * @Override
     * public void mousePressed(MouseEvent e) {
     * if (e.getButton() == MouseEvent.BUTTON1) {
     * if (player.armes.get(player.currentArme).type &&
     * player.armes.get(player.currentArme).munition > 0) {
     * // Tirer avec une arme de type 1
     * player.armes.get(player.currentArme).shoot();
     * Bullet b = new Bullet(player.getX(), player.getY(), crosshair.xCor +
     * crosshair.cushion,
     * crosshair.yCor + crosshair.cushion,
     * player.armes.get(player.currentArme).power,
     * player.armes.get(player.currentArme).color);
     * projectilesManager.addBulletPlayer(b);
     * } else if (player.armes.get(player.currentArme).munition > 0 &&
     * !player.armes.get(player.currentArme).type) {
     * // Tirer avec une arme de type 2
     * player.armes.get(player.currentArme).shoot();
     * 
     * int xTile = crosshair.xCor / 50; // Coordonnée x de la case sous le curseur
     * int yTile = crosshair.yCor / 50; // Coordonnée y de la case sous le curseur
     * 
     * // Récupérer le type de la case sous le curseur
     * int caseType = gameManager.getGamePlateau().level_tab[yTile][xTile];
     * 
     * if (caseType == ManagerCase.TYPE_MINE) {
     * // Poser une mine
     * A3 mine = new A3(crosshair.xCor, crosshair.yCor);
     * gameManager.getGamePlateau().pieges.add(mine);
     * } else if (caseType == ManagerCase.TYPE_GRENADE) {
     * // Poser une grenade
     * A4 grenade = new A4(crosshair.xCor, crosshair.yCor);
     * gameManager.getGamePlateau().grenade.add(grenade);
     * grenade.activateGrenade();
     * }
     * }
     * }
     * }
     */

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            // si l'arme est de type tir
            if (player.getArmes().get(player.getCurrentArme()).getType()
                    && player.getArmes().get(player.getCurrentArme()).getMunition() > 0) {
                player.getArmes().get(player.getCurrentArme()).shoot();
                Bullet b = new Bullet(player.getX(), player.getY(), crosshair.getX() + crosshair.getCushion(),
                        crosshair.getY() + crosshair.getCushion(),
                        player.getArmes().get(player.getCurrentArme()).getPower(),
                        player.getArmes().get(player.getCurrentArme()).color);
                projectilesManager.addBulletPlayer(b);
            } else if (player.getArmes().get(player.getCurrentArme()).getMunition() > 0
                    && !player.getArmes().get(player.getCurrentArme()).getType()) {
                if (player.getArmes().get(player.getCurrentArme()) instanceof A3) {
                    player.getArmes().get(player.getCurrentArme()).shoot();
                    A3 mine = new A3(crosshair.getX(), crosshair.getY());

                    gameManager.getGamePlateau().pieges.add(mine);
                } else if (player.getArmes().get(player.getCurrentArme()) instanceof A4) {
                    player.getArmes().get(player.getCurrentArme()).shoot();
                    A4 grenade = new A4(crosshair.getX(), crosshair.getY());
                    gameManager.getGamePlateau().grenade.add(grenade);
                    grenade.activateGrenade();
                }
            } // else{
              // player.armes.get(player.currentArme).besoinRecharge();

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

    public Crosshair getCrosshair() {
        return this.crosshair;
    }
}
