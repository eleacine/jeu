package Shooter.GUI;

import Shooter.Managers.ManagerCase;
import Shooter.factory.PlateauLevelLoader;
import Shooter.model.Case;
import Shooter.model.Game;
import Shooter.model.Plateau;
import Shooter.model.Player;

import java.awt.*;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class EditingMode extends GameScene {
	// contient réglages du type darkmode, musique, sauvegarde, skins, règles du jeu
	private int levelselected;
	private int[][] level;
	private JPanel Plateau; 
	private JPanel Colonne; 
	private int clicked;
	private boolean levelpersonnalisé=false;
	private ManagerCase managerCase;

	public EditingMode(Game game) {
		super(game);
		levelselected=0;
		this.managerCase = new ManagerCase();
		this.level=creeLevelsimple();
		setLayout(new BorderLayout());
		createPlateau();
		createBarreBas();
		this.Colonne=new JPanel();
		creeColonne();
		add(Colonne,BorderLayout.WEST);
		barrehaut();
	}

	public int[][] creeLevelsimple(){
		int[][] l = new int[20][36];
		for(int i=0;i<20;i++){
			for(int j=0;j<36;j++){
				if(i==0||j==0||i==19||j==35){
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
		JPanel barreNord = new JPanel();
		barreNord.setLayout(new FlowLayout()); // Utilisez un FlowLayout pour aligner les composants horizontalement
		barreNord.setBackground(Color.BLACK);
		JPanel boutonMenuPanel = new JPanel();
		boutonMenuPanel.setBackground(Color.BLACK);
		boutonMenuPanel.add(createButton("Menu", "Menu"));

		String[] options = options();
		JComboBox<String> barrelevel = new JComboBox<>(options);
		barrelevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				int n = cb.getSelectedIndex();
				levelselected=n-1;
				System.out.println("n="+n);
				if(n!=0){
					level =PlateauLevelLoader.loadPlayingBoard(cheminFichier(), levelselected);
				}else{
					level=creeLevelsimple();
				}
				updatePlateau();
				
			}
		});

		barreNord.add(boutonMenuPanel);
		barreNord.add(barrelevel);

		add(barreNord, BorderLayout.NORTH);

	}

	public String [] options(){
		int levelmax;
		if(levelpersonnalisé){
			if(fichierExiste("Shooter/factory/PlateauLevelsPerso.txt")){
				levelmax =PlateauLevelLoader.levelmax("Shooter/factory/PlateauLevelsPerso.txt");
			}else{
				levelmax =0;}
		}else{
			levelmax =PlateauLevelLoader.levelmax("Shooter/factory/PlateauLevels.txt");
		}
		
		String [] options = new String[levelmax+1];
		System.out.println("LEVELMAX: "+levelmax);
		options[0]="Nouveau";
		for(int i=0;i<levelmax;i++){
			options[i+1]="Level "+(i+1);
		}
		return options;
	}

//-----------------------------CREER PLATEAU---------------------------
	public void createPlateau() {
		JPanel centre = new JPanel();
        centre.setBackground(Color.BLACK);
        int taille = 50;
        JPanel plateau = new JPanel();

        plateau.setLayout(new GridLayout(level.length, level[0].length));
		//revoir colonne et ligbe 720*420
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                JPanel casePanel = createCase(i, j);
                casePanel.setPreferredSize(new Dimension(taille, taille));
                plateau.add(casePanel);
            }
        }
        centre.setLayout(new BorderLayout());
        centre.add(plateau, BorderLayout.CENTER);
		this.Plateau =centre;
        add(centre, BorderLayout.CENTER);
    }

	public void updatePlateau(){
		Plateau.removeAll();
		Plateau.setBackground(Color.BLACK);
        int taille = 50;
        JPanel plateau = new JPanel();

        plateau.setLayout(new GridLayout(level.length, level[0].length));
		//revoir colonne et ligbe 720*420
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                JPanel casePanel = createCase(i, j);
                casePanel.setPreferredSize(new Dimension(taille, taille));
                plateau.add(casePanel);
            }
        }
		Plateau.add(plateau,BorderLayout.CENTER);
		Plateau.repaint();
		Plateau.revalidate();
	}

	public JPanel createCase(int li , int col){
		ImagePanel Case = new ImagePanel(li,col);
		Case.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e){
			level[li][col]=clicked;
			Case.draw(li,col);
		}			
		});
		return Case;
    }
	

//----------------------------CREER BARRE DU BAS(LA HONTE,VRAIMENT) (SAUVEGARDER/MODIFIER)---------------------------
	public void createBarreBas(){
		JPanel barre =new JPanel();
		barre.setLayout(new GridLayout(5,5));
		barre.setBackground(Color.BLACK);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		JButton sauvegarde = new JButton("Sauvegarder");
        sauvegarde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
				sauvegardeAction(cheminFichier());
                game.cardLayout.show(game.cardPanel, "Menu");

            }

        });
		barre.add(sauvegarde, gbc);

		add(barre,BorderLayout.SOUTH);
		
	}

	public void creeColonne(){
		Colonne.setBackground(Color.BLACK);
		Colonne.setLayout(new BorderLayout());
		JPanel barreHaut=new JPanel();
		barreHaut.setBackground(Color.BLACK);
		JPanel b = new JPanel();
		b.setBackground(Color.BLACK);
		JComboBox<String> TypeCase = new JComboBox<>(new String[]{"Mur", "Sol", "Obstacle"});
		updatecolonne(b, "Mur");
		TypeCase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) TypeCase.getSelectedItem();
				updatecolonne(b,s);
				
			}
		});
		
		barreHaut.add(TypeCase);
		Colonne.add(barreHaut,BorderLayout.NORTH);
		Colonne.add(b,BorderLayout.CENTER);
	}

	public void updatecolonne(JPanel j, String s){
		j.removeAll();
		GridBagLayout gbl = new GridBagLayout();
		j.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints(); 
		c.insets=new Insets(1, 1, 1, 1);
		c.gridx = 0; 
		// ligne 0
		c.gridy = 0; 
		
		ArrayList<Case> l = Liste(s);
		for(int i=0;i<l.size();i++){
			JButton b=new JButton(Integer.toString(l.get(i).getId()),new ImageIcon(l.get(i).getSprite()));
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					clicked=Integer.parseInt(b.getText());
				}
			});
			j.add(b,c);
			c.gridy++;
		}
		j.revalidate();
		j.repaint();
	}


	public ArrayList<Case> Liste(String s){
		ArrayList<Case> l = new ArrayList<>();
		switch (s) {
			case "Sol":
				l= managerCase.sol;
				break;

			case "Mur":
				l= managerCase.mur;
				break;

			case "Obstacle":
				l= managerCase.obstacle;
				break;
			
		}
		return l;
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

//---------------------------------------------------------------------------------------
	public String cheminFichier(){
		if(levelpersonnalisé){
			return "Shooter/factory/PlateauLevelsPerso.txt";
		}
		return "Shooter/factory/PlateauLevels.txt";
	}
	public void modifierAction(String cheminFichier){
		File writer = new File(cheminFichier);

		try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Level "+levelselected)) {
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public boolean fichierExiste(String cheminFichier){
		File fichier = new File(cheminFichier);
		return fichier.exists();
	}
    public void sauvegardeAction(String cheminFichier){

        try {
            if (!fichierExiste(cheminFichier)) {
                // S'il n'existe pas, crée le fichier
				File fichier = new File(cheminFichier);
                fichier.createNewFile();
                System.out.println("Fichier créé avec succès : " + cheminFichier);
            }
            FileWriter writer = new FileWriter(cheminFichier,true);
            // Écrire les données à sauvegarder dans le fichier
            // Par exemple, vous pouvez parcourir votre tableau de données et écrire chaque élément dans le fichier
            writer.write("Level "+PlateauLevelLoader.levelmax(cheminFichier)+1+":\n");
            for (int i = 0; i < level.length; i++) {
                writer.write("{"); 
                for (int j = 0; j < level[i].length; j++) {
                    writer.write(level[i][j]+"");
                    if(j!=level[i].length-1){
                        writer.write(", ");
                    }
                }
                writer.write("},\n"); // Ajoutez une nouvelle ligne après chaque ligne de données
            }
            writer.close();
            System.out.println("Données sauvegardées avec succès dans le fichier texte.");
        } catch (IOException e) {
			
            System.out.println("Erreur lors de la sauvegarde des données dans le fichier texte : " + e.getMessage());
        }
    }
//---------------------------------------------image et bouton------------------------------------------------------
	private class ImagePanel extends JPanel {
		private ImageIcon imageIcon;
    	private ImageIcon darkenedImageIcon;
		boolean mouseOver;

    	public ImagePanel(int x, int y) {
			BufferedImage sprite = managerCase.getSprite(level[x][y]);
        	this.imageIcon = new ImageIcon(sprite);
        	this.darkenedImageIcon = createDarkenedImageIcon(imageIcon);
			mouseOver=false;

			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					mouseOver = true;
					repaint(); // Redessiner pour afficher l'image assombrie
				}
	
				@Override
				public void mouseExited(MouseEvent e) {
					mouseOver = false;
					repaint(); // Redessiner pour afficher l'image normale
				}
			});
    	}


		private ImageIcon createDarkenedImageIcon(ImageIcon originalIcon) {
			Image originalImage = originalIcon.getImage();
			int width = originalImage.getWidth(null);
			int height = originalImage.getHeight(null);

			BufferedImage darkenedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = darkenedImage.createGraphics();
			g2d.drawImage(originalImage, 0, 0, null);
			g2d.setColor(new Color(0, 0, 0, 100)); // Couleur semi-transparente noire
			g2d.fillRect(0, 0, width, height); // Remplit l'image avec une couleur noire semi-transparente
			g2d.dispose();

			return new ImageIcon(darkenedImage);
		}
		public void draw(int x,int y){
			BufferedImage sprite = managerCase.getSprite(level[x][y]);
			this.imageIcon = new ImageIcon(sprite);
			this.darkenedImageIcon = createDarkenedImageIcon(imageIcon);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (imageIcon != null && !mouseOver) {
				imageIcon.paintIcon(this, g, 0, 0);
			}
			if(darkenedImageIcon!=null && mouseOver){
				darkenedImageIcon.paintIcon(this, g,0, 0);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			if (imageIcon != null) {
				return new Dimension(20,20);
			} else {
				return super.getPreferredSize();
			}
		}
	}
}



	
