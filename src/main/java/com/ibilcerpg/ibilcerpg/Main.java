package com.ibilcerpg.ibilcerpg;
import com.ibilcerpg.ibilcerpg.Controllers.FXController;
import com.ibilcerpg.ibilcerpg.Design.CombatManager;
import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Personagens.Biologo;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
import com.ibilcerpg.ibilcerpg.Personagens.Traduteiro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    public static Player jogador = new Player();
    public static Biologo adversario = new Biologo();
    Traduteiro adversario2 = new Traduteiro();
    Traduteiro adversario3 = new Traduteiro();
    CombatManager combate = new CombatManager(jogador, adversario);
    CombatManager combate2 = new CombatManager(jogador, adversario2);



    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("8_bit_hud.ttf"), 78);
        Musica musica = new Musica();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu.fxml"));

        Scene sceneMenu = new Scene(fxmlLoader.load(), 930, 540);
        stage.setTitle("IbilceRPG");
        stage.setScene(sceneMenu);
        stage.setResizable(false);
        stage.show();
        FXController cont = fxmlLoader.getController();
        if (cont!=null) cont.setarMusica(musica);

    }


    public static void main(String[] args){
        launch();
        
        Player jogador = new Player();
        Biologo adversario = new Biologo();
        Traduteiro adversario2 = new Traduteiro();
        Traduteiro adversario3 = new Traduteiro();
        CombatManager combate = new CombatManager(jogador, adversario);
        CombatManager combate2 = new CombatManager(jogador, adversario2);





    }



}
