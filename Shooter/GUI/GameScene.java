package Shooter.GUI;
import Shooter.model.Game;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameScene extends JPanel {
    public Game game;

    //Constantes pour les couleurs et fonts
    protected Font shooterFont =loadShooterFont();
    protected Image backgroundImage;
    protected static final Color TITLE_COLOR = Color.WHITE;
    protected static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    protected final Font BUTTON_FONT = shooterFont;


    public GameScene(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public JButtonStyled createButton(String text, String pageName) {
        JButtonStyled button = new JButtonStyled(text, shooterFont);
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

    public JButtonStyled createButtonExit(String text) {
        JButtonStyled button = new JButtonStyled(text, shooterFont);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return button;
    }

        protected Font loadShooterFont() {
         try {
        InputStream fontStream = getClass().getResourceAsStream("../../image/Cream Cake.otf");
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

protected void loadBackgroundImage(int i) {
    try {
        InputStream inputStream;
        switch(i){
            case 1:inputStream = getClass().getResourceAsStream("../../image/background.jpg");
            break;
            case 2: inputStream = getClass().getResourceAsStream("../../image/background2.jpg");
            break;
            case 3: inputStream = getClass().getResourceAsStream("../../image/background3.jpg");
            break;
            default: inputStream = getClass().getResourceAsStream("../../image/background.jpg");
        }
        if (inputStream != null) {
            backgroundImage = ImageIO.read(inputStream);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (backgroundImage != null) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}



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
