package Bone;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws UnsupportedAudioFileException,
            IOException, LineUnavailableException, InterruptedException {
        File file = new File("src/main/resources/media/audio/audio.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();

        // Keep the program running while the audio plays




    }
}
