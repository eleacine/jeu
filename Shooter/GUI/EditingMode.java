package Shooter.GUI;

import Shooter.Managers.ManagerCase;
import Shooter.factory.*;
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
	private boolean levelpersonnalisé=false; // MODE PERSONNALISÉ OU CAMPAGNE
	private int clicked; //bouton cliquée

	private JPanel Plateau; 
	private JPanel Colonne;
	private JPanel barre = new JPanel();
	private JPanel barreNord = new JPanel();
	
	
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

	//Crée level basique.
	public int[][] creeLevelsimple(){
		int[][] l = new int[21][37];
		for(int i=0;i<l.length;i++){
			for(int j=0;j<l[i].length;j++){
				if(i==0||j==0||i==20||j==36){
					l[i][j]=7;
				}else{
					l[i][j]=0;
				}
			}
		}
		levelselected=0;
		return l;
	}

    //--------------------------------BARRE HAUT----------------------------------

	public void barrehaut(){
		barreNord.removeAll();
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
				levelselected=n;
				if(n!=0){
					level =PlateauLevelLoader.loadPlayingBoard(cheminFichierLevel(), levelselected-1);
					ListeEnnemis = EnemyLevelLoader.EnnemiDuNiveau(cheminFichierEnnemi(), levelselected);
				}else{
					level=creeLevelsimple();
					ListeEnnemis = new ArrayList<>();
				}
				
				updatePlateau();
				createBarreBas();
				
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
        }/* 
		Plateau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e){
				int x =(e.getX()+20)/40;
				int y = (e.getY()+20)/40;


			}
		});*/
		Plateau.add(plateau,BorderLayout.CENTER);
		Plateau.repaint();
		Plateau.revalidate();
	}

	public JPanel createCase(int li , int col){
		ImagePanel Case = new ImagePanel(li,col);
		Case.setEnnemie(recupererEnnemi(li, col));
		Case.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e){
			if(clicked>=100){
				ArrayList<String> ListeEnnemis1 = ListeEnnemis();
				if(Case.ennemie!=null){
					removeEnnemie(li, col);;
				}
				Case.ennemie=getSprite(ListeEnnemis1.get(clicked-100));
				int x = (40*li)-20;
				int y = (40*col)-20;

				ListeEnnemis.add(ListeEnnemis1.get(clicked-100)+","+x+","+y+";");
				Case.repaint();
			}else if (clicked==-1){
				removeEnnemie(li, col);
				Case.ennemie=null;
				Case.repaint();
			}else{
				level[li][col]=clicked;
				Case.draw(li,col);
			}
		}			
		});
		return Case;
    }

	public void removeEnnemie(int li, int col){
		for(int i=0;i<ListeEnnemis.size();i++){
			String[] ennemi = ListeEnnemis.get(i).split(",");
			if(Integer.parseInt(ennemi[1].replaceAll("[^0-9]", "").trim())==li*40-20 && Integer.parseInt(ennemi[2].replaceAll("[^0-9]", "").trim())==col*40-20){
				ListeEnnemis.remove(i);
			}
		}
	}

	public ImageIcon recupererEnnemi(int li, int col){
		for(int i=0;i<ListeEnnemis.size();i++){
			String[] ennemi = ListeEnnemis.get(i).split(",");
			if(Integer.parseInt(ennemi[1].replaceAll("[^0-9]", "").trim())==li*40-20 && Integer.parseInt(ennemi[2].replaceAll("[^0-9]", "").trim())==col*40-20){
				return getSprite(ennemi[0]);
			}
		}
		return null;
	}
	

//----------------------------CREER BARRE DU BAS(LA HONTE,VRAIMENT) (SAUVEGARDER/MODIFIER)---------------------------
	public void createBarreBas(){
		barre.removeAll();
		barre.setLayout(new GridLayout());
		barre.setBackground(Color.BLACK);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		JButtonStyled sauvegarde = new JButtonStyled("Sauvegarder", shooterFont);
        sauvegarde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
				if(ListeEnnemis.size()>0){
					sauvegardeAction();
					update();
				}
            }

        });

		barre.add(sauvegarde, gbc);
		gbc.gridx=1;
		JButtonStyled modifier = new JButtonStyled("Modifier", shooterFont);
		modifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				modifierAction();
				update();
				
			}

		});
		if(levelselected!=0){
			barre.add(modifier, gbc);
		}
		add(barre,BorderLayout.SOUTH);
		
	}

	public void update(){
		level = creeLevelsimple();
		ListeEnnemis = new ArrayList<>();
		barrehaut();
		updatePlateau();
		createBarreBas();
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
			JButtonStyled b = new JButtonStyled(-1, new ImageIcon("Shooter/images/Supprimer.png"));
			b.setPreferredSize(new Dimension(30,30));
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					clicked = b.getId();
				}
			});
			j.add(b);
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
		Image resImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
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

	//A VOIR 
	public ArrayList<JButtonStyled> ListeBouton(String s){
		ArrayList<JButtonStyled> l = new ArrayList<>();
		switch (s) {
			case "Sol":
				for(int i=0;i<managerCase.sol.size();i++){
					JButtonStyled b =new JButtonStyled(managerCase.sol.get(i).getId(), new ImageIcon(managerCase.sol.get(i).getSprite()));
					
					l.add(b);
				}
				break;
			case "Mur":
				for(int i=0;i<managerCase.mur.size();i++){
					JButtonStyled b =new JButtonStyled(managerCase.mur.get(i).getId(), new ImageIcon(managerCase.mur.get(i).getSprite()));
					if(b.getId()==6 || b.getId()==7 || b.getId()==8 || b.getId()==10 || b.getId()==11 || b.getId()==12 ||  b.getId()==15 || b.getId()==16 || b.getId()==17 ){
						b.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent e){
								if(e.getKeyCode()==KeyEvent.VK_R){
									int i = b.getId();
									b.setId(i+1);
									b.setIcon(new ImageIcon(managerCase.mur.get(i+1).getSprite()));
								}
							}
						});
					}
					
					l.add(b);
				}
				break;
			case "Obstacle":
				for(int i=0;i<managerCase.obstacle.size();i++){
					JButtonStyled b =new JButtonStyled(managerCase.obstacle.get(i).getId(), new ImageIcon(managerCase.obstacle.get(i).getSprite()));
					l.add(b);
				}
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
	
	public String niveauExistant(int avant,int apres){
		String s="";
		for(int i=avant;i<apres;i++){
			s+=PlateauLevelLoader.getLevelString(cheminFichierLevel(),i);
		}
		return s;
	}

	public String StringEnnemie(int debut, int fin){
		String s="";
		for(int i=debut;i<fin;i++){
			s+=EnemyLevelLoader.EnnemiNiveau(cheminFichierEnnemi(),i);
		}
		return s;
	}

	public void modifierAction(){
		int levelmax = PlateauLevelLoader.levelmax(cheminFichierLevel());
		String avantLevel = niveauExistant(1,levelselected);
		String apresLevel = niveauExistant(levelselected+1,levelmax+1);
		String avantEnnemi = StringEnnemie(1,levelselected);
		String apresEnnemi = StringEnnemie(levelselected+1,levelmax+1);
		try{
			FileWriter writer = new FileWriter(cheminFichierLevel());
				writer.write(avantLevel);
				writer.write("Level "+levelselected+":\n");
				writer.write(nouveauTableau());
				writer.write(apresLevel);
				writer.close();

				writer = new FileWriter(cheminFichierEnnemi());
				writer.write(avantEnnemi);
				writer.write(levelselected+": ");
				writer.write(nouveauEnnemi());
				writer.write(apresEnnemi);
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	public boolean fichierExiste(String cheminFichier){
		File fichier = new File(cheminFichier);
		return fichier.exists();
	}
    public void sauvegardeAction(){
		String cheminFichierLevel = cheminFichierLevel();
		String cheminFichierEnnemi = cheminFichierEnnemi();
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
            writer.write(nouveauTableau());
            writer.close();

			writer = new FileWriter(cheminFichierEnnemi,true);
			writer.write((PlateauLevelLoader.levelmax(cheminFichierLevel))+": ");
			writer.write(nouveauEnnemi());
			writer.close();
            System.out.println("Données sauvegardées avec succès dans le fichier texte.");
        } catch (IOException e) {
			
            System.out.println("Erreur lors de la sauvegarde des données dans le fichier texte : " + e.getMessage());
        }
    }

	public String nouveauEnnemi(){
		String s="";
		for(int i=0;i<ListeEnnemis.size();i++){
			s+=ListeEnnemis.get(i);
		}
		s+="\n";
		return s;
	}

	public String nouveauTableau(){
		String s="";
		for (int i = 0; i < level.length; i++) {
			s+="{";
			for (int j = 0; j < level[i].length; j++) {
				s+=level[i][j];
				if(j!=level[i].length-1){
					s+=", ";
				}
			}
			s+="}\n";
		}
		return s;
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



	
