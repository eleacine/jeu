package Shooter.model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {


    public void playSound(String soundFilePath) {
        File soundFile = new File(soundFilePath);

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}

