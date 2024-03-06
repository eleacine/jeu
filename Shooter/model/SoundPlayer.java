package Shooter.model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private boolean soundEnabled = true;
    private Clip clip;

    public void playSound(String soundFilePath) {
        if (soundEnabled) {
            File soundFile = new File(soundFilePath);

            try {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void start() {
        if (clip != null && !clip.isActive()) {
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isActive()) {
            clip.stop();
            clip.flush();
            clip.setFramePosition(0);
        }
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean enabled) {
        soundEnabled = enabled;
        if (!soundEnabled) {
            stop();
        }
    }
}
