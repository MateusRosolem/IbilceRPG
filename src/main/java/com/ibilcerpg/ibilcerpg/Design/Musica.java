package com.ibilcerpg.ibilcerpg.Design;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Musica {

    static MediaPlayer musicaMenu;
    static MediaPlayer musicaCombate;


    public static void tocarMusicaMenu() {
        Media media = new Media(new File("src/main/resources/Menu.mp3").toURI().toString());
        musicaMenu = new MediaPlayer(media);
        musicaMenu.setVolume(0.5);
        musicaMenu.setAutoPlay(true);
    }

    public static void pararMusicaMenu() {
        if (musicaMenu != null) {
            musicaMenu.setVolume(0);
            musicaMenu.pause();
        }
    }

    public static void tocarMusicaCombate() {
        Media media = new Media(new File("src/main/resources/Combate.mp3").toURI().toString());
        musicaCombate = new MediaPlayer(media);
        musicaCombate.setVolume(0.5);
        musicaCombate.setAutoPlay(true);
    }

    public static void pararMusicaCombate() {
        if (musicaCombate != null) {
            musicaCombate.setVolume(0);
            musicaCombate.pause();
        }
    }

    public static boolean estaTocando() {
        return !(musicaMenu.isMute() && musicaCombate.isMute());
    }
}

