package KupidonTeam.utils;

import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

public class SoundPlayer {

    private AudioClip audioClip;

    public SoundPlayer() {

    }

    public void spawnEffect() {
        audioClip = playSound("/assets/sound/effects/card_spawn.mp3");
        audioClip.setVolume(0.25);
        audioClip.play();
    }


    public AudioClip playSound(String path) {


        try {
            audioClip = new AudioClip(getClass().getResource(path).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        audioClip.setVolume(0.1);
        return audioClip;
    }

    public void deathEffect() {
        audioClip = playSound("/assets/sound/effects/death.wav");
        audioClip.setVolume(0.25);
        audioClip.play();
    }

    public void setVolume(double volume) {
        audioClip.setVolume(volume);
    }
}
