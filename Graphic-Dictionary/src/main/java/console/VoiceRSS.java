package console;

import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import java.io.FileOutputStream;


public class VoiceRSS {
    private static final String API_KEY = "bd575d5f42964670a6456427ab520658";
    private static final String AUDIO_PATH = "src/main/resources/application/audio/audio.wav";
    public static String voiceNameVN = "Chi";
    public static String voiceNameUS = "John";
    public static String voiceNameUK = "Alice";
    public static String vietnamese = "vi-vn";
    public static String englishUS = "en-us";
    public static String englishUK = "en-gb";
    public static double speed = 1;
    public static void speakWord(String word, String language) throws Exception {
        // Set audio properties
        VoiceProvider tts = new VoiceProvider(API_KEY);
        VoiceParameters params = new VoiceParameters(word, AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setLanguage(language);

        // Set voice depending on language
        if (language.equals(englishUK)) {
            params.setVoice(voiceNameUK);
        } else if (language.equals(englishUS)) {
            params.setVoice(voiceNameUS);
        } else if (language.equals(vietnamese)) {
            params.setVoice(voiceNameVN);
        }

        params.setRate((int) Math.round(-2.9936 * speed * speed + 15.2942 * speed - 12.7612));
        byte[] voice = tts.speech(params);
        FileOutputStream fos = new FileOutputStream(AUDIO_PATH);
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();

        // Play audio
        File file = new File(AUDIO_PATH);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public static void main(String[] args) throws Exception {
        speakWord("hello how are you i'm fine thank you and you", englishUK);
    }
}


