import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class test  extends JFrame {
    
        // je croie qu'on a trouver une solutionnnnnnnnn
        // test 50
        // hello marylouuu
        // //
        // me parle pas en anglais je répondrais pas ``
        // how are you

        // M+I = <3
        // bonjour
        // comment tu vaas
        // socejfofbtrecx
        // voici une blagounette
        // comment on appele un ytruc qui court et qui se jette
        // UNE COURGETTE
        //Marylou? oui? est ce que ton pere est un voleur?Par ce qu'il a voler toutes les etoiles du ciels pour les mettre dans tes yeux
        //pardon ... jarrete 
        // y a des moments faut savoir s'arrêter 
        // je suis d'accord
        //ehh calme toi 
        // test fusion pour la 5e fois 



    public test(){
        // Charger l'image de fond depuis les ressources du projet
        URL imageUrl = getClass().getResource("Shooter/GUI/image/cible.png");
        System.out.println("URL de l'image : " + (imageUrl != null ? imageUrl.toExternalForm() : "null"));
                 ImageIcon backgroundImage = new ImageIcon(imageUrl);

        // Créer un JLabel avec l'image de fond
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        // Ajouter un JLabel pour le texte "Hello, World!"
        JLabel helloLabel = new JLabel("Hello, World!");
        helloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        helloLabel.setForeground(Color.WHITE);
        helloLabel.setHorizontalAlignment(JLabel.CENTER);
        helloLabel.setVerticalAlignment(JLabel.CENTER);

        // Ajouter le JLabel du texte au JLabel de l'image de fond
        backgroundLabel.add(helloLabel);

        // Ajouter le JLabel de l'image de fond à la JFrame
        add(backgroundLabel);

        // Configurer la JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new test());
    }
}


    
