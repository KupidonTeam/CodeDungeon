package KupidonTeam.utils;

import javafx.scene.media.AudioClip;

import java.net.URISyntaxException;

public class SoundPlayer {
    private AudioClip audioClip;
    private AudioClip mainTheme;

    public SoundPlayer() {
    }

    public void spawnEffect() {
        audioClip = playSound("/assets/sound/effects/card_spawn.mp3");
        audioClip.setVolume(0.45);
        audioClip.play();
    }

    public AudioClip mainTheme() {
        mainTheme = playSound("/assets/sound/music/tavern.mp3");
        mainTheme.setVolume(0.2);
        mainTheme.setCycleCount(AudioClip.INDEFINITE);

        return mainTheme;
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
        audioClip.setVolume(0.2);
        audioClip.play();
    }

    public void setVolume(double volume) {
        audioClip.setVolume(volume);
    }

    public void damaged() {
        audioClip = playSound("/assets/sound/effects/confusion.wav");
        audioClip.setVolume(0.25);
        audioClip.play();
    }

    public void shop() {
        audioClip = playSound("/assets/sound/effects/shop.wav");
        audioClip.setVolume(0.3);
        audioClip.play();
    }

    public void pvp() {
        audioClip = playSound("/assets/sound/effects/pvp.wav");
        audioClip.setVolume(0.3);
        audioClip.play();
    }

    public void soldItem() {
        audioClip = playSound("/assets/sound/effects/sold_item.wav");
        audioClip.setVolume(0.2);
        audioClip.play();
    }

    public void dungeon() {
        audioClip = playSound("/assets/sound/effects/dungeon.wav");
        audioClip.setVolume(0.3);
        audioClip.play();
    }

    public void victory() {
        audioClip = playSound("/assets/sound/music/victory.wav");
        audioClip.setVolume(0.45);
        audioClip.play();
    }

    public void skill(String effect) {
        System.out.println("effect = " + effect);
        audioClip = playSound("/assets/sound/effects/" + effect.toLowerCase() + ".wav");
        audioClip.setVolume(0.25);
        audioClip.play();
    }

    public AudioClip getMainTheme() {
        return mainTheme;
    }
}
