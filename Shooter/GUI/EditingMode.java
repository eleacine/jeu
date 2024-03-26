package Shooter.GUI;

import Shooter.Managers.ManagerCase;
import Shooter.factory.PlateauLevelLoader;
import Shooter.model.*;

import java.awt.*;


import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.ArrayList;


public class EditingMode extends GameScene {
	// contient réglages du type darkmode, musique, sauvegarde, skins, règles du jeu
	private ManagerCase managerCase;
	private int levelselected; // numéro du niveau selectionné par l'utilisateur (si 0, c'est un nouveau niveau sinon c'est un niveau existant)
	private int[][] level; // niveau sélectionné par l'utilisateur
	private ArrayList<String> ListeEnnemis;
	private boolean levelpersonnalisé=true; // MODE PERSONNALISÉ OU CAMPAGNE
	private int clicked;

	private JPanel Plateau; 
	private JPanel Colonne; 
	
	
	public EditingMode(Game game) {
		super(game);
		levelselected=0;
		this.managerCase = new ManagerCase();
		this.ListeEnnemis = new ArrayList<>();
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
		int[][] l = new int[20][37];
		for(int i=0;i<l.length;i++){
			for(int j=0;j<l[i].length;j++){
				if(i==0||j==0||i==19||j==35){
					l[i][j]=7;
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
					level =PlateauLevelLoader.loadPlayingBoard(cheminFichierLevel(), levelselected);
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
		
		String [] options = new String[levelmax+2];
		System.out.println("LEVELMAX: "+levelmax);
		options[0]="Nouveau";
		for(int i=0;i<levelmax-1;i++){
			options[i+1]="Level "+(i+1);
		}
		options[levelmax+1]="Ennemis";
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
			if(clicked>=100){
				ArrayList<String> ListeEnnemis1 = ListeEnnemis();
				if(Case.ennemie!=null){
					for(int i=0;i<ListeEnnemis.size();i++){
						String[] ennemi = ListeEnnemis.get(i).split(",");
						if(Integer.parseInt(ennemi[1].replaceAll("[^0-9]", "").trim())==li*40-20 && Integer.parseInt(ennemi[2].replaceAll("[^0-9]", "").trim())==col*40-20){
							ListeEnnemis.remove(i);
						}
					}
				}
				Case.ennemie=getSprite(ListeEnnemis1.get(clicked-100));
				int x = (40*li)-20;
				int y = (40*col)-20;

				ListeEnnemis.add(ListeEnnemis1.get(clicked-100)+","+x+","+y+";");
				System.out.println("ListeEnnemis");
				for(int i=0;i<ListeEnnemis.size();i++){
					System.out.println(ListeEnnemis.get(i));
				}
				Case.repaint();
			}else{
				level[li][col]=clicked;
				Case.draw(li,col);
			}
		}			
		});
		return Case;
    }
	

//----------------------------CREER BARRE DU BAS(LA HONTE,VRAIMENT) (SAUVEGARDER/MODIFIER)---------------------------
	public void createBarreBas(){
		JPanel barre =new JPanel();
		barre.setLayout(new GridLayout());
		barre.setBackground(Color.BLACK);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		JButtonStyled sauvegarde = new JButtonStyled("Sauvegarder", new Font("SansSerif", Font.PLAIN, 14));
        sauvegarde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
				if(ListeEnnemis.size()>0){
					sauvegardeAction(cheminFichierLevel(),cheminFichierEnnemi());
					game.cardLayout.show(game.cardPanel, "Menu");
				}
				

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
		JComboBox<String> TypeCase = new JComboBox<>(new String[]{"Mur", "Sol", "Obstacle","Ennemi"});
		updatecolonne(b, "Mur");
		TypeCase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) TypeCase.getSelectedItem();
				updatecolonne(b,s);
				
			}
		});
		JScrollPane scrollPane = new JScrollPane(b);
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		barreHaut.add(TypeCase);
		Colonne.add(barreHaut,BorderLayout.NORTH);
		Colonne.add(b,BorderLayout.CENTER);
	}

	public void updatecolonne(JPanel j, String s) {
		j.removeAll();
		j.setLayout(new GridLayout(0, 2)); // Layout pour empiler les boutons verticalement
		if(s!="Ennemi"){
			ArrayList<Case> l = Liste(s);
			for (int i = 0; i < l.size(); i++) {
				ImageIcon CaseImage = new ImageIcon(l.get(i).getSprite());
				JButtonStyled b = new JButtonStyled(l.get(i).getId(), CaseImage);
				b.setPreferredSize(new Dimension(30,30));
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						clicked = b.getId();
					}
				});
				j.add(b);
			}
		}else{
			ArrayList<String> l = ListeEnnemis();
			for(int i=0;i<l.size();i++){
				JButtonStyled b = new JButtonStyled(i+100, getSprite(l.get(i)));
				b.setPreferredSize(new Dimension(30,30));
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						clicked = b.getId();
					}
				});
				j.add(b);
			}
		}

		j.revalidate();
		j.repaint();
		
	}

	public ImageIcon getSprite(String s){
		ImageIcon i = new ImageIcon();
		switch (s) {
			case "Enemy 1":
				i= new EnemyBasique(-1,-1).getSprite();
				break;
			case "Enemy 2":
				i= new EnemyMedium(-1,-1).getSprite();
				break;
			case "Enemy 3":
				i= new Gardien(-1,-1).getSprite();
				break;
			case "Enemy 4":
				i= new EnemyIA(-1,-1).getSprite();
				break;
			case "Enemy 5":
				i= new EnemySniper(-1,-1).getSprite();
				break;
		}

		Image img = i.getImage();
		Image resImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		i = new ImageIcon(resImage);
		
		return i;
	}

	public ArrayList<String> ListeEnnemis(){
		ArrayList<String> l = new ArrayList<>();
		l.add( "Enemy 1");//Basique
		l.add("Enemy 2");//Medium
		l.add("Enemy 3");// Guardien
		l.add("Enemy 4"); //IA
		
		l.add("Enemy 5"); // Sniper
		return l;
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

//---------------------------------------------------------------------------------------
	public String cheminFichierLevel(){
		if(levelpersonnalisé){
			return "Shooter/factory/PlateauLevelsPerso.txt";
		}
		return "Shooter/factory/PlateauLevels.txt";
	}
	public String cheminFichierEnnemi(){
		if(levelpersonnalisé){
			return "Shooter/factory/EnemiesForLevelsPerso.txt";
		}
		return "Shooter/factory/EnemiesForLevels.txt";
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
    public void sauvegardeAction(String cheminFichierLevel, String cheminFichierEnnemi){
        try {
            if (!fichierExiste(cheminFichierLevel) && !fichierExiste(cheminFichierEnnemi)) {
                // S'il n'existe pas, crée le fichier
				File fichier = new File(cheminFichierLevel);
                fichier.createNewFile();
				fichier = new File(cheminFichierEnnemi);
				fichier.createNewFile();
                System.out.println("Fichiers créés avec succès : " + cheminFichierLevel+" "+cheminFichierEnnemi);
            }
            FileWriter writer = new FileWriter(cheminFichierLevel,true);
            // Écrire les données à sauvegarder dans le fichier
            // Par exemple, vous pouvez parcourir votre tableau de données et écrire chaque élément dans le fichier
            writer.write("Level "+(PlateauLevelLoader.levelmax(cheminFichierLevel)+1)+":\n");
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
			writer = new FileWriter(cheminFichierEnnemi,true);
			writer.write((PlateauLevelLoader.levelmax(cheminFichierEnnemi)+1)+": ");
			for(int i=0;i<ListeEnnemis.size();i++){
				writer.write(ListeEnnemis.get(i)+" ");
			}
			writer.write("\n");
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
		private ImageIcon ennemie;
		boolean mouseOver;

    	public ImagePanel(int x, int y) {
			BufferedImage sprite = managerCase.getSprite(level[x][y]);
        	this.imageIcon = new ImageIcon(sprite);
        	this.darkenedImageIcon = createDarkenedImageIcon(imageIcon);
			ennemie=null;
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
			if(ennemie!=null){
				
				ennemie.paintIcon(this, g, 0, 0);
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

		public void setEnnemie(ImageIcon ennemie){
			this.ennemie=ennemie;
		}
	}
}



	
