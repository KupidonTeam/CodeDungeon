package KupidonTeam.utils;

import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

public class SoundPlayer {

    public SoundPlayer() {

    }

    public void spawnEffect() {
        playSound("/assets/sound/effects/card_spawn.mp3").play();
    }


    public AudioClip playSound(String path) {

        AudioClip audioClip = null;
        try {
            audioClip = new AudioClip(getClass().getResource(path).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return audioClip;
    }

}
