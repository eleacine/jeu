package Shooter.Managers;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Shooter.model.Player;
import Shooter.model.SoundPlayer;
import Shooter.model.A3;
import Shooter.model.A4;
import Shooter.model.Armes;
import Shooter.model.Bullet;
import Shooter.model.Case;
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

    // @Override
    // public void mousePressed(MouseEvent e) {
    // if (e.getButton() == MouseEvent.BUTTON1) {

    // if (player.getArmes().get(player.getCurrentArme()).getType()
    // && player.getArmes().get(player.getCurrentArme()).getMunition() > 0) {
    // player.getArmes().get(player.getCurrentArme()).shoot();
    // Bullet b = new Bullet(player.getX(), player.getY(), crosshair.getX() +
    // crosshair.getCushion(),
    // crosshair.getY() + crosshair.getCushion(),
    // player.getArmes().get(player.getCurrentArme()).getPower(),
    // player.getArmes().get(player.getCurrentArme()).getTypeMunition());
    // projectilesManager.addBulletPlayer(b);

    // if (soundPlayer != null && gameManager.getSound()) {
    // soundPlayer.playSound("Shooter/res/tir_sound.wav");
    // }

    // } else if (player.getArmes().get(player.getCurrentArme()).getMunition() > 0
    // && !player.getArmes().get(player.getCurrentArme()).getType()) {
    // if (player.getArmes().get(player.getCurrentArme()) instanceof A3) {
    // int currentXIndex = (int) (crosshair.getX() / 40);
    // int currentYIndex = (int) (crosshair.getY() / 40);
    // // System.out.println("currentXIndex: " + currentXIndex);
    // // System.out.println("currentYIndex: " + currentYIndex);

    // if (currentXIndex >= 0 && currentXIndex <
    // gameManager.getGamePlateau().level_tab[0].length &&
    // currentYIndex >= 0 && currentYIndex <
    // gameManager.getGamePlateau().level_tab.length) {

    // int caseType =
    // gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex];
    // // System.out.println("casetype:" + caseType);
    // if (caseType == 8 || caseType == 7) {
    // // System.out.println("Impossible de placer la mine sur un mur.");
    // } else {
    // // player.getArmes().get(player.getCurrentArme()).shoot();
    // A3 mine = new A3(crosshair.getX(), crosshair.getY());

    // // Vérifie si la distance jusqu'au joueur est inférieure ou égale à la
    // distance
    // // de l'arme
    // double distanceToPlayer = Math.sqrt(Math.pow(player.getX() - mine.getX(), 2)
    // + Math.pow(player.getY() - mine.getY(), 2));
    // if (distanceToPlayer <= mine.getDistance()) {
    // gameManager.getGamePlateau().pieges.add(mine);
    // player.getArmes().get(player.getCurrentArme()).shoot();
    // } else {
    // // System.out.println("Impossible de placer la mine en dehors de la
    // distance.");
    // // mine.deleteMine(); // Supprimer la mine
    // }
    // }
    // }
    // } else if (player.getArmes().get(player.getCurrentArme()) instanceof A4) {
    // int currentXIndex = (int) (crosshair.getX() / 40);
    // int currentYIndex = (int) (crosshair.getY() / 40);

    // if (currentXIndex >= 0 && currentXIndex <
    // gameManager.getGamePlateau().level_tab[0].length &&
    // currentYIndex >= 0 && currentYIndex <
    // gameManager.getGamePlateau().level_tab.length) {

    // int caseType =
    // gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex];
    // // System.out.println("caseType: " + caseType);
    // if/*
    // * (
    // * caseType==ManagerCase.MUR_COIN || caseType==ManagerCase.MUR_MILIEU ||
    // * caseType==ManagerCase.MUR_HAUT ||
    // * caseType==ManagerCase.MUR_BAS || caseType==ManagerCase.MUR_GAUCHE ||
    // * caseType==ManagerCase.MUR_DROIT||
    // * caseType==ManagerCase.MUR_C_BAS || caseType==ManagerCase.MUR_C_HAUT ||
    // * caseType==ManagerCase.MUR_C_DROIT ||
    // * caseType==ManagerCase.MUR_C_GAUCHE)
    // */
    // (caseType == 8 || caseType == 7) {
    // } else {

    // A4 grenade = new A4(crosshair.getX(), crosshair.getY());
    // double distanceToPlayer = Math.sqrt(Math.pow(player.getX() - grenade.getX(),
    // 2)
    // + Math.pow(player.getY() - grenade.getY(), 2));
    // if (distanceToPlayer <= grenade.getDistance()) {
    // // System.out.println("ici");
    // gameManager.getGamePlateau().grenade.add(grenade);
    // grenade.activateGrenade();
    // player.getArmes().get(player.getCurrentArme()).shoot();
    // } else {

    // // System.out.println("Impossible de placer la grenade en dehors de la
    // // distance.");

    // }
    // }
    // }

    // else {
    // player.getArmes().get(player.getCurrentArme()).besoinRecharge();

    // if (soundPlayer != null && gameManager.getSound()) {
    // soundPlayer.playSound("Shooter/res/arme_vide.wav");
    // }
    // }
    // }
    // }
    // }
    // }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Armes currentArme = player.getArmes().get(player.getCurrentArme());
            if (currentArme.getMunition() > 0) {
                if (currentArme.getType() && currentArme.getMunition() > 0) {
                    currentArme.shoot();
                    Bullet b = new Bullet(player.getX(), player.getY(), crosshair.getX() + crosshair.getCushion(),
                            crosshair.getY() + crosshair.getCushion(), currentArme.getPower(),
                            currentArme.getTypeMunition());
                    projectilesManager.addBulletPlayer(b);
                    if (soundPlayer != null && gameManager.getSound()) {
                        soundPlayer.playSound("Shooter/res/tir_sound.wav");
                    }
                    
                }  else {
                    // Gère les paramètres des mines et des grenades
                    if (player.getArmes().get(player.getCurrentArme()).getMunition() > 0 && !player.getArmes().get(player.getCurrentArme()).getType()) {
                        // Vérifie que c'est une grenade
                        if (player.getArmes().get(player.getCurrentArme()) instanceof A3) {
                            int currentXIndex = (int) (crosshair.getX() / 40);
                            int currentYIndex = (int) (crosshair.getY() / 40);
                            // System.out.println("currentXIndex: " + currentXIndex);
                            // System.out.println("currentYIndex: " + currentYIndex);
        
                            if (currentXIndex >= 0 && currentXIndex < gameManager.getGamePlateau().level_tab[0].length && currentYIndex >= 0 && currentYIndex < gameManager.getGamePlateau().level_tab.length) {
                                int caseType = gameManager.getGamePlateau().getTile_manager().getCaseType(gameManager.getGamePlateau().level_tab[currentYIndex][currentXIndex]);
                                if (caseType == ManagerCase.MUR || caseType == ManagerCase.BLOQUE || caseType == ManagerCase.MUR_CASSANT) {
                                    System.out.println("Impossible de placer la mine sur un mur.");
                                } else {
                                        boolean canPlace = true;
                                        int playerXIndex = (int) (player.getX() / 40);
                                        int playerYIndex = (int) (player.getY() / 40);
                                    if (playerXIndex == currentXIndex && playerYIndex == currentYIndex) {
                                        System.out.println("Impossible de placer la grenade, le joueur est en face d'un mur.");
                                        canPlace = false;
                                     }
                                    if (canPlace) {
                                        A3 mine = new A3(crosshair.getX(), crosshair.getY());
        
                                        // Vérifie si la distance jusqu'au joueur est inférieure ou égale à la distance de l'arme
                                        double distanceToPlayer = Math.sqrt(Math.pow(player.getX() - mine.getX(), 2) + Math.pow(player.getY() - mine.getY(), 2));
        
                                        if (distanceToPlayer <= mine.getDistance()) {
                                            gameManager.getGamePlateau().pieges.add(mine);
                                            player.getArmes().get(player.getCurrentArme()).shoot();
                                        } else {
                                            System.out.println("Impossible de placer la mine en dehors de la distance.");
                                        }
                                    }
                                }
                            }
                        } else if (player.getArmes().get(player.getCurrentArme()) instanceof A4) {
        
                            int currentXIndex = (int) (crosshair.getX() / 40);
                            int currentYIndex = (int) (crosshair.getY() / 40);
        
                            if (currentXIndex >= 0 && currentXIndex < gameManager.getGamePlateau().level_tab[0].length && currentYIndex >= 0 && currentYIndex < gameManager.getGamePlateau().level_tab.length) {
                                int playerXIndex = (int) (player.getX() / 40);
                                int playerYIndex = (int) (player.getY() / 40);
        
                                boolean canPlace = true;
        
                                // Vérifie s'il y a un mur entre le joueur et la case 
                                if (currentXIndex != playerXIndex || currentYIndex != playerYIndex) {
                                    int startX = Math.min(playerXIndex, currentXIndex);
                                    int endX = Math.max(playerXIndex, currentXIndex);
                                    int startY = Math.min(playerYIndex, currentYIndex);
                                    int endY = Math.max(playerYIndex, currentYIndex);
        
                                    for (int x = startX; x <= endX; x++) {
                                        for (int y = startY; y <= endY; y++) {
                                            int caseType = gameManager.getGamePlateau().getTile_manager().getCaseType(gameManager.getGamePlateau().level_tab[y][x]);
                                            if (caseType == ManagerCase.MUR || caseType == ManagerCase.OBSTACLE) {
                                                System.out.println("Impossible de placer la mine, un mur bloque le chemin.");
                                                canPlace = false;
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("Impossible de placer la mine, le joueur est en face de la case.");
                                    canPlace = false;
                                }
        
                                    if (canPlace) {
                                        A4 grenade = new A4(crosshair.getX(), crosshair.getY());
                                        double distanceToPlayer = Math.sqrt(Math.pow(player.getX() - grenade.getX(), 2) + Math.pow(player.getY() - grenade.getY(), 2));
                                        if (distanceToPlayer <= grenade.getDistance()) {
                                            gameManager.getGamePlateau().grenade.add(grenade);
                                            //grenade.activateGrenade();
                                            player.getArmes().get(player.getCurrentArme()).shoot();
                                        } else {
                                            System.out.println("Impossible de placer la grenade en dehors de la distance.");
                                        }
                                    }
                                }
                            } else {
                                player.getArmes().get(player.getCurrentArme()).besoinRecharge();
        
                                if (soundPlayer != null && gameManager.getSound()) {
                                    soundPlayer.playSound("Shooter/res/arme_vide.wav");
                                }
                            }
                        }
                  
                  }  }
            }
        }
    
    

    @Override
    public void mouseMoved(MouseEvent e) {
        crosshair.setX(e.getX() - 23);
        crosshair.setY(e.getY() - 46);
        int clickX = e.getX();
        int clickY = e.getY();

        double angle = Math.atan2(clickY - player.getY(), clickX - player.getX());
        player.setDirection(angle);
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
