package com.ibilcerpg.ibilcerpg.Design;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Musica {

    MediaPlayer musicaMenu;
    MediaPlayer musicaCombate;

    public void musicaMenu(){
        Media media = new Media(new File("src/main/resources/Menu.mp3").toURI().toString());
        MediaPlayer musicaMenu = new MediaPlayer(media);
        musicaMenu.setVolume(0.5);
        musicaMenu.setAutoPlay(true);
    }


    public void pararMusica(MediaPlayer musica){
        musica.pause();
    }

    public void musicaCombate(){

        Media media = new Media(new File("src/main/resources/Combate.mp3").toURI().toString());
        MediaPlayer musicaCombate = new MediaPlayer(media);
        musicaCombate.setVolume(0.5);
        musicaCombate.setAutoPlay(true);
    }

    public MediaPlayer getMusicaMenu(){
        return musicaMenu;
    }

    public MediaPlayer getMusicaCombate(){
        return musicaCombate;
    }

}
