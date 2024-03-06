package Shooter.Managers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Shooter.model.Player;
import Shooter.model.SoundPlayer;
import Shooter.model.A3;
import Shooter.model.A4;
import Shooter.model.Bullet;
import Shooter.model.Crosshair;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private Player player;
    private Crosshair crosshair;
    private ProjectilesManager projectilesManager;
    public GameManager gameManager;

    protected SoundPlayer soundPlayer = new SoundPlayer();

    public MyMouseListener(Player player, Crosshair crosshair, ProjectilesManager projectilesManager,
            GameManager gameManager) {
        this.player = player;
        this.crosshair = crosshair;
        this.projectilesManager = projectilesManager;
        this.gameManager = gameManager;
    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            int clickX = e.getX();
            int clickY = e.getY();

            double angle = Math.atan2(clickY - player.getY(), clickX - player.getX());
            player.setDirection(angle);

            if (player.getArmes().get(player.getCurrentArme()).getType()
                    && player.getArmes().get(player.getCurrentArme()).getMunition() > 0) {
                player.getArmes().get(player.getCurrentArme()).shoot();
                Bullet b = new Bullet(player.getX(), player.getY(), crosshair.getX() + crosshair.getCushion(),
                        crosshair.getY() + crosshair.getCushion(),
                        player.getArmes().get(player.getCurrentArme()).getPower(),
                        player.getArmes().get(player.getCurrentArme()).color);
                projectilesManager.addBulletPlayer(b);

                if (soundPlayer != null && gameManager.getSound()) {
                    soundPlayer.playSound("Shooter/res/tir_sound.wav");
                }

            }else if (player.getArmes().get(player.getCurrentArme()).getMunition() > 0
            && !player.getArmes().get(player.getCurrentArme()).getType()) {
        if (player.getArmes().get(player.getCurrentArme()) instanceof A3) {
         //   player.getArmes().get(player.getCurrentArme()).shoot();
            A3 mine = new A3(crosshair.getX(), crosshair.getY());

            // Vérifie si la distance jusqu'au joueur est inférieure ou égale à la distance de l'arme
            double distanceToPlayer = Math.sqrt(Math.pow(player.getX() - mine.getX(), 2) + Math.pow(player.getY() - mine.getY(), 2));
            if (distanceToPlayer <= mine.getDistance()) {
                gameManager.getGamePlateau().pieges.add(mine);
                player.getArmes().get(player.getCurrentArme()).shoot();
            } else {
                System.out.println("Impossible de placer la mine en dehors de la distance.");
                mine.deleteMine(); // Supprimer la mine
            }

        } else if (player.getArmes().get(player.getCurrentArme()) instanceof A4) {
          //  player.getArmes().get(player.getCurrentArme()).shoot();
            A4 grenade = new A4(crosshair.getX(), crosshair.getY());

            // Vérifie la distance 
            double distanceToPlayer = Math.sqrt(Math.pow(player.getX() - grenade.getX(), 2) + Math.pow(player.getY() - grenade.getY(), 2));
            if (distanceToPlayer <= grenade.getDistance()) {
                gameManager.getGamePlateau().grenade.add(grenade);
                grenade.activateGrenade();
                player.getArmes().get(player.getCurrentArme()).shoot();
            } else {
                System.out.println("Impossible de placer la grenade en dehors de la distance.");
              //  grenade.deleteGrenade(); // Supprimer la grenade
            }
        }
    }  else {
                player.getArmes().get(player.getCurrentArme()).besoinRecharge();

                if (soundPlayer != null && gameManager.getSound()){
                    soundPlayer.playSound("Shooter/res/arme_vide.wav");
                }
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

    public Crosshair getCrosshair() {
        return this.crosshair;
    }
}
