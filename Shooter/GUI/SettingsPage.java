package Shooter.GUI;

import javax.swing.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Shooter.model.Game;
import Shooter.model.SoundPlayer;

public class SettingsPage extends GameScene {
	private SoundToggleButton soundToggleButton;
	protected SoundPlayer soundPlayer = new SoundPlayer();

	public SettingsPage(Game game) {
		super(game);

		ImageIcon soundOnIcon = new ImageIcon("Shooter/res/soundOn.jpg");
        ImageIcon muteIcon = new ImageIcon("Shooter/res/mute.png");

        // Adjust the size of the images to a specific dimension
        soundOnIcon = scaleImageIcon(soundOnIcon, 50, 50); 
        muteIcon = scaleImageIcon(muteIcon, 50, 50); 

		soundToggleButton = new SoundToggleButton("Activer son", "Désactiver son",
			soundOnIcon, muteIcon);
		add(soundToggleButton);
		add(createButton("Menu", "Menu"));
	}

	private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}

	public class SoundToggleButton extends JButton {
		private boolean soundEnabled;
		private ImageIcon soundEnabledIcon;
		private ImageIcon soundDisabledIcon;

		public SoundToggleButton(String enableText, String disableText, ImageIcon enabledIcon, ImageIcon disabledIcon) {
			soundEnabled = true; // Par défaut, le son est activé
			soundEnabledIcon = enabledIcon;
			soundDisabledIcon = disabledIcon;
			updateIcon();

			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					soundEnabled = !soundEnabled;
					updateIcon();
					// Ajoutez ici le code pour activer ou désactiver le son dans votre jeu
					// Par exemple, vous pourriez appeler une méthode de votre objet Game pour gérer
					game.updateSound(soundEnabled);
				}
			});
		}

		

		private void updateIcon() {
			if (soundEnabled) {
				setIcon(soundEnabledIcon);
				// System.out.println("Son desactivé");
				soundPlayer.playSound("Shooter/res/geometryDash.wav");

			} else {
				setIcon(soundDisabledIcon);
				// System.out.println("son active");
				soundPlayer.stop();
			}
		}
	}
}
