package Shooter.GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
//import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
//import javax.swing.*;

//import Shooter.model.Player;
import Shooter.model.Game;
import Shooter.model.MyButton;
import static Shooter.model.GameStates.*;


public class MenuPage extends GameScene implements SceneMethode {
/* 
    private CardLayout cardLayout;
    private Player player;
    private JFrame frame;
    public MenuPage(Player player) {
        this.player = player;
        Font PunkFont = loadPunkFont();
        JPanel mainPanel = new JPanel(new GridBagLayout()) {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                URL imageUrl = getClass().getResource("../image/cible.png");
                // System.out.println("URL de l'image : " + (imageUrl != null ? imageUrl.toExternalForm() : "null"));
        
                ImageIcon originalImage = new ImageIcon(imageUrl);
                int newWidth = getWidth() - 150;
                int newHeight = getHeight() - 150;
                Image resizedImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                ImageIcon backgroundImage = new ImageIcon(resizedImage);
        
                //  position pour centrer l'image
                int x = (getWidth() - newWidth) / 2;
                int y = (getHeight() - newHeight) / 2;
        
                g.drawImage(backgroundImage.getImage(), x, y, newWidth, newHeight, this);
            }
        
            {
                setBackground(Color.BLACK);  
            }
        };
        
    
        mainPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("MENU");
        titleLabel.setFont(PunkFont.deriveFont(Font.BOLD, 60));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 20, 0); 
        mainPanel.add(titleLabel, gbc);

        JButton settingsButton = new JButton("Settings");
        JButton levelButton = new JButton("Levels");
        JButton playButton = new JButton("Play");
        JButton exitButton = new JButton("Quit");
        exitButton.addActionListener(e -> System.exit(0));


        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0); 
        mainPanel.add(settingsButton, gbc);

        gbc.gridy = 2;
        mainPanel.add(levelButton, gbc);

        gbc.gridy = 3;
        mainPanel.add(playButton, gbc);

        gbc.gridy = 4;
        mainPanel.add(exitButton, gbc);


        getContentPane().add(mainPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);    
        setResizable(false);
        setVisible(true);
    }


private Font loadPunkFont() {
    try {
        InputStream fontStream = getClass().getResourceAsStream("../image/punk.ttf");
        if (fontStream == null) {
            throw new FileNotFoundException("Fichier de police introuvable.");
        }
        return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(14f);
    } catch (Exception e) {
        e.printStackTrace();
        // En cas d'erreur, utilisez la police par défaut
        return new Font("SansSerif", Font.PLAIN, 14);
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the Entry window
            WelcomePage welcomePage = new WelcomePage("Shooter Game");

            // Add a WindowListener to the Entry window
            welcomePage.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // When the Entry window is closed, get the player and create the Main window
                    Player player = welcomePage.getPlayer();
                    new MenuPage(player);
                }
            });
        });
    }*/

    private BufferedImage img;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();

	private MyButton bPlaying, bSettings, bQuit;

	public MenuPage(Game game) {
		super(game);
		importImg();
		loadSprites();
		initButtons();
	}

	private void initButtons() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;

		bPlaying = new MyButton("Play", x, y, w, h);
		bSettings = new MyButton("Settings", x, y + yOffset, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);

	}

	@Override
	public void render(Graphics g) {

		drawButtons(g);

	}

	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);

	}

	private void importImg() {

		URL is = getClass().getResource("../image/cible.png");
        

		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadSprites() {
        for (int x = 0; x < 3; x++) {
            sprites.add(img.getSubimage(50 * x, 0, 50, 50));
        }

	}

	@Override
	public void mouseClicked(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		} else if (bSettings.getBounds().contains(x, y)) {
			SetGameState(SETTINGS);
		} else if (bQuit.getBounds().contains(x, y))
			System.exit(0);
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		} else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMouseOver(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		}

	}

	@Override
	public void mousePressed(int x, int y) {

		if (bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		} else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMousePressed(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
	}

}
