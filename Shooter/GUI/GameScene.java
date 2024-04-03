package Shooter.GUI;
import Shooter.model.Game;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameScene extends JPanel {
    public Game game;

    //Constantes pour les couleurs et fonts
     Font shooterFont =loadShooterFont();
    protected static final Color TITLE_COLOR = Color.WHITE;
    //protected static final Font TITLE_FONT = new Font("Comic Sans MS", Font.BOLD, 40);
    //protected static final Color BUTTON_COLOR = new Color(59, 89, 182);
    protected static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    protected final Font BUTTON_FONT = shooterFont;


    public GameScene(Game game) {
        this.game = game;
        setBackground(Color.BLACK);
    }

    public Game getGame() {
        return game;
    }

    // Creation des boutons
    public JButtonStyled createButton(String text, String pageName) {
        JButtonStyled button = new JButtonStyled(text, shooterFont);
        //styleButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = game.cardLayout;
                JPanel panel = game.cardPanel;
                if (pageName.equals("Play")) {
                    game.begin = true;
                }
                else if(pageName.equals( "Menu") && game.isBegin()){
                    game.reset();
                }
                else if(pageName.equals("Creative") && game.isBegin()){
                    game.begin=true;
                    
                }
                layout.show(panel, pageName);
            }
        });
        return button;
    }

    // creates a button to exit the game
    public JButtonStyled createButtonExit(String text) {
        JButtonStyled button = new JButtonStyled(text, shooterFont);
        //styleButton(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the entire window and exit the program
                System.exit(0);
            }
        });
        return button;
    }

           private  Font loadShooterFont() {
         try {
        InputStream fontStream = getClass().getResourceAsStream("../res/shooter.ttf");
        if (fontStream == null) {
            throw new FileNotFoundException("Fichier de police introuvable.");
        }
        return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(130f);
    } catch (Exception e) {
        e.printStackTrace();
        // En cas d'erreur :  police par défaut
        return new Font("SansSerif", Font.PLAIN, 14);
    }
}
/* 
    //fonction pour styliser les bouttons
    private void styleButton(JButton button) {
        button.setBackground(BUTTON_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFont(BUTTON_FONT);
        button.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
*/


public class JButtonStyled extends JButton {
    private int id;

    public JButtonStyled(String text, Font font) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        setForeground(Color.WHITE); 
        setBackground(new Color(128, 128, 128)); 
        setFont(font.deriveFont(Font.PLAIN, 45)); 
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new LineBorder(Color.WHITE)); 
        adjustButtonSize();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(0,0,0));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(128, 128, 128)); 
            }
        });
    }

    JButtonStyled(int id, Icon icon) {
        super(icon);
        
        this.id = id;
    }

    private void adjustButtonSize() {
        setVerticalTextPosition(SwingConstants.CENTER); 
        setMargin(new Insets(0, 0, 60, 0)); 
        FontMetrics metrics = getFontMetrics(getFont());
        int textWidth = metrics.stringWidth(getText());
        int textHeight = metrics.getHeight();
        int extraWidth = 0; 
        int extraHeight = -20; 
        int buttonWidth = Math.max(textWidth + extraWidth, 150); 
        int buttonHeight = Math.max(textHeight + extraHeight, 40); 
        setPreferredSize(new Dimension(buttonWidth, buttonHeight));
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}




    
}
