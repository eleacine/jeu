package Shooter.GUI;

import javax.swing.*;

// import Shooter.model.Player;

// import java.awt.*;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import java.io.InputStream;

public class WelcomePage extends JFrame {
/*
    private Player player;
    private JLabel title;
    private JTextField textField;
    private JButton nouvellePartie;
    private JButton partieCharge;
    private JPanel backgroundPanel;

    public WelcomePage() {
        init();
        JLabel label = new JLabel("Welcome to Shooter");
       // label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.add(label, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public WelcomePage(String titre) {
        init();
        Font PunkFont = loadPunkFont();

        JLabel welcome = new JLabel(" Shooter");
        welcome.setFont(PunkFont.deriveFont(Font.BOLD, 60));
        welcome.setForeground(Color.WHITE);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.setBackground(Color.BLACK);

        backgroundPanel.add(Box.createVerticalStrut(100)); // Ajouter un espace rigide pour pousser vers le haut

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);;
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        title = new JLabel("Entrez votre pseudo :");
        title.setSize(new Dimension(350, 100));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        textField = new JTextField(20);
        textField.setMaximumSize(new Dimension(330, 30));
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        nouvellePartie = new JButton("GO !");
        //nouvellePartie.setBackground(Color.BLACK);
        nouvellePartie.setOpaque(false);
        nouvellePartie.setForeground(Color.BLACK);
        nouvellePartie.setAlignmentX(Component.CENTER_ALIGNMENT);
        nouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(textField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(nouvellePartie);

        backgroundPanel.add(welcome);
        backgroundPanel.add(Box.createVerticalStrut(100));
        backgroundPanel.add(formPanel);
        backgroundPanel.add(Box.createVerticalStrut(20));

        setBackground(Color.BLACK);
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);     
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    private void start() {
        String name = textField.getText(); // Récupérer le nom du joueur
        if (!name.isEmpty()) {
            Player playerExistant = Player.loadPlayer(); // Charger le joueur existant
            if (playerExistant != null && playerExistant.isPseudoIdentique(name)) { // Si le joueur existe déjà
                player = playerExistant; // Utiliser le joueur existant
                dispose(); // Fermer la fenêtre
            } else {
                player = new Player(name); // Créer un nouveau joueur
                dispose(); // Fermer la fenêtre
            }
        } else {
            JOptionPane.showMessageDialog(this.backgroundPanel, "Veuillez entrer un nom valide."); // Afficher un message d'erreur
        }
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




    public Player getPlayer() {
        return this.player;
    }

    // public static void main(String[] args) {
    //     new WelcomePage();
    //     new WelcomePage("Shooter");
    // }*/
}
