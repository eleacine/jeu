package Shooter.model;
import java.awt.Graphics;
import Shooter.GUI.LevelsPage;
import Shooter.Managers.ManagerCase;

public class Plateau {
    protected int[][] level_tab;
    protected ManagerCase  tile_manager;
    public Graphics plateau_graphic; //on utilise pour "enregistrer" notre image graphique puis pouvoir la modifier dans le update
    
    public Plateau(){
        this.level_tab=LevelsPage.getLevelData();
        tile_manager=new ManagerCase();
    }

    public void render_plateau(Graphics g) {
		for (int y = 0; y < level_tab.length; y++) {
			for (int x = 0; x < level_tab[y].length; x++) {
				int id = level_tab[y][x];
				g.drawImage(tile_manager.getSprite(id), x * 50, y * 50, null);
			}
		}
        this.plateau_graphic=g;
		//drawButtons(g);
	}

    public void update_pleateau(int x, int y, int type_case){
        plateau_graphic.drawImage(tile_manager.getSprite(type_case), x*50, y*50, null);	
    }
    
    
}
