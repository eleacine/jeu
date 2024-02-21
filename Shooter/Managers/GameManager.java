package Shooter.Managers;

import Shooter.model.Crosshair;
import Shooter.model.Plateau;
import Shooter.model.Player;

public class GameManager {

    private Plateau gamePlateau;
    private Player player;
    private PlayerManager playerManager;
    private ProjectilesManager projectilesManager;
    private MyMouseListener myMouseListener;
    private EnnemiManager ennemiManager;
    public ManagerArmes managerArmes;

    public GameManager(Plateau gamePlateau, Player player) {
        
        this.gamePlateau = gamePlateau;
        this.player = player;
        this.playerManager = new PlayerManager(this);
        this.ennemiManager = new EnnemiManager(this);
        this.projectilesManager = new ProjectilesManager(player, this);
        this.myMouseListener = new MyMouseListener(player, new Crosshair(), projectilesManager, this);
        this.managerArmes = new ManagerArmes(this);

    }

    public void update() {
        playerManager.handleKeyPress();
        ennemiManager.update();
        ennemiManager.suppEnnemi();
        playerManager.update();

        projectilesManager.update();
    }

    public Plateau getGamePlateau() {
        return gamePlateau;
    }

    public void setGamePlateau(Plateau gamePlateau) {
        this.gamePlateau = gamePlateau;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public ProjectilesManager getProjectilesManager() {
        return projectilesManager;
    }

    public void setProjectilesManager(ProjectilesManager projectilesManager) {
        this.projectilesManager = projectilesManager;
    }

    public MyMouseListener getMyMouseListener() {
        return myMouseListener;
    }

    public void setMyMouseListener(MyMouseListener myMouseListener) {
        this.myMouseListener = myMouseListener;
    }

    public EnnemiManager getEnnemiManager() {
        return ennemiManager;
    }

    public void setEnnemiManager(EnnemiManager ennemiManager) {
        this.ennemiManager = ennemiManager;
    }


    

}
