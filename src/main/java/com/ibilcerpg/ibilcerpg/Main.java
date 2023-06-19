package com.ibilcerpg.ibilcerpg;
import com.ibilcerpg.ibilcerpg.Design.CombatManager;
import com.ibilcerpg.ibilcerpg.Personagens.Biologo;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
import com.ibilcerpg.ibilcerpg.Personagens.Traduteiro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("8_bit_hud.ttf"), 78);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menu.fxml"));
        Scene sceneMenu = new Scene(fxmlLoader.load(), 930, 540);
        stage.setTitle("IbilceRPG");
        stage.setScene(sceneMenu);
        stage.setResizable(false);
        stage.show();

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
