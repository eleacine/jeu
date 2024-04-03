package Shooter.GUI;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Shooter.model.Game;
import Shooter.model.SoundPlayer;

public class SettingsPage extends GameScene {
    private SoundToggleButton soundToggleButton;
    protected SoundPlayer soundPlayer = new SoundPlayer();

    public SettingsPage(Game game) {
        super(game);
        initUI();
        loadBackgroundImage(1);
        
    }

    private void initUI(){
        setLayout(new GridBagLayout()); // GridBagLayout pour tout organiser

		//titre
        JLabel titleLabel = new JLabel("Settings");
        titleLabel.setFont(shooterFont); 
        titleLabel.setForeground(TITLE_COLOR); 

		//composition des elements dans le grid
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        add(titleLabel, gbc);

		//Image et taille 
        ImageIcon soundOnIcon = new ImageIcon("Shooter/res/soundOn.jpg");
        ImageIcon muteIcon = new ImageIcon("Shooter/res/mute.png");
        soundOnIcon = scaleImageIcon(soundOnIcon, 50, 50);
        muteIcon = scaleImageIcon(muteIcon, 50, 50);
        soundToggleButton = new SoundToggleButton("Activer son", "Désactiver son", soundOnIcon, muteIcon);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; // Center le bouton de son 
        add(soundToggleButton, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.PAGE_END; // Center le bouton de menu
        add(createButton("Menu", "Menu"), gbc);
    }

    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public class SoundToggleButton extends JButton { //nested class 
        private boolean soundEnabled;
        private ImageIcon soundEnabledIcon;
        private ImageIcon soundDisabledIcon;

        public SoundToggleButton(String enableText, String disableText, ImageIcon enabledIcon, ImageIcon disabledIcon) {
            soundEnabled = false; // Par défaut, le son est inactivé
            soundEnabledIcon = enabledIcon;
            soundDisabledIcon = disabledIcon;
            updateIcon();

            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    soundEnabled = !soundEnabled;
                    updateIcon();
                    game.updateSound(soundEnabled);
                }
            });
        }

        private void updateIcon() {
            if (soundEnabled) {
                setIcon(soundEnabledIcon);
                soundPlayer.playSound("Shooter/res/geometryDash.wav");

            } else {
                setIcon(soundDisabledIcon);
                soundPlayer.stop();
            }
        }
    }
}
