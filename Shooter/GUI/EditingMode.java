package Shooter.GUI;

import Shooter.model.Game;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;


public class EditingMode extends GameScene {
	// contient réglages du type darkmode, musique, sauvegarde, skins, règles du jeu
    private int levelmax;
	private int[][] level;
	private int clicked;

	public EditingMode(Game game) {
		super(game);
        this.levelmax=3;
		this.level=creeLevelsimple();
		setLayout(new BorderLayout());
		barrehaut();
		//creePlateau();
		createPlateau();
		createBarreBas();
	}

	public int[][] creeLevelsimple(){
		int[][] l = new int[17][29];
		for(int i=0;i<17;i++){
			for(int j=0;j<29;j++){
				if(i==0||j==0||i==16||j==28){
					l[i][j]=2;
				}else{
					l[i][j]=0;
				}
			}
		}
		return l;
	}

    //--------------------------------BARRE HAUT----------------------------------

	public void barrehaut(){
		JPanel j= new JPanel();
		j.setBackground(Color.BLACK);
		j.add(createButton("Menu", "Menu"));
		add(j,BorderLayout.NORTH);

	}


//-----------------------------CREER PLATEAU---------------------------
	public void createPlateau() {
        int taille = 100;
        JPanel plateau = new JPanel();
   
        plateau.setLayout(new GridLayout(17, 29));
    
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 29; j++) {
                JPanel casePanel = createCase(i, j);
                casePanel.setPreferredSize(new Dimension(taille, taille));
                plateau.add(casePanel);
            }
        }
        
    
        add(plateau,BorderLayout.CENTER);
    }

	public JPanel createCase(int li , int col){
        JPanel casePanel = new JPanel();
        casePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		draw(casePanel, li, col);

        casePanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
				level[li][col]=clicked;
				draw(casePanel, li, col);
            }
			public void mouseEntered(MouseEvent evt) {
				drawdarker(casePanel, li, col);
			}
			public void mouseExited(MouseEvent e){
				draw(casePanel, li, col);
			}
        });

        return casePanel;
    }
	//----------------------DESSINER CASE----------------------------

	public void draw(JPanel p,int x, int y){
		if(this.level[x][y]==2){
			p.setBackground(Color.RED);
		}else if(this.level[x][y]==1){
			p.setBackground(Color.WHITE);
		}else if(this.level[x][y]==0){
			p.setBackground(Color.GREEN);
		}
	}

	public void drawdarker(JPanel p,int x, int y){
		if(this.level[x][y]==2){
			p.setBackground(new Color(150,0,0));
		}else if(this.level[x][y]==1){
			p.setBackground(Color.GRAY);
		}else if(this.level[x][y]==0){
			p.setBackground(new Color(0,100,0));
		}
	}
//----------------------------------------BARRE BAS-----------------------------
	public void createBarreBas(){
		JPanel barre =new JPanel();
		barre.setLayout(new GridLayout(5,5));
		barre.setBackground(Color.BLACK);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		JButton marche = bouton("MARCHE");
		barre.add(marche,gbc);
		gbc.gridx=1;
		JButton obstacle = bouton("OBSTACLE");
		barre.add(obstacle,gbc);
		gbc.gridx=2;
		JButton mur = bouton("MUR");
		barre.add(mur,gbc);
		gbc.gridx=3;
		gbc.gridy=1;
		JButton sauvegarde = new JButton("Sauvegarder");
        sauvegarde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                sauvegardeAction();
                game.cardLayout.show(game.cardPanel, "Menu");

            }

        });
		barre.add(sauvegarde, gbc);

		add(barre,BorderLayout.SOUTH);
		
	}

	public JButton bouton(String t){
		JButton j= new JButton(t);
		Dimension boutonDimension = new Dimension(100, 50); // Définir la taille souhaitée pour les boutons (largeur x hauteur)
    	j.setPreferredSize(boutonDimension); // Définir la taille préférée pour le bouton
		j.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if(t=="MARCHE"){
					clicked=0;
				}else if(t=="OBSTACLE"){
					clicked=1;
				}else if (t=="MUR"){
					clicked=2;
				}
			}
		});
		return j;
	}

    public void sauvegardeAction(){
        String cheminFichier = "Shooter/factory/PlateauLevels.txt";

        try {
            FileWriter writer = new FileWriter(cheminFichier,true);
            // Écrire les données à sauvegarder dans le fichier
            // Par exemple, vous pouvez parcourir votre tableau de données et écrire chaque élément dans le fichier
            writer.write("Level"+levelmax+":\n");
            for (int i = 0; i < level.length; i++) {
                writer.write("{"); 
                for (int j = 0; j < level[i].length; j++) {
                    writer.write(level[i][j]);
                    if(j!=level[i].length-1){
                        writer.write(", ");
                    }
                }
                writer.write("},\n"); // Ajoutez une nouvelle ligne après chaque ligne de données
            }
            writer.close();
            levelmax++;
            System.out.println("Données sauvegardées avec succès dans le fichier texte.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des données dans le fichier texte : " + e.getMessage());
        }
    }
}

	
