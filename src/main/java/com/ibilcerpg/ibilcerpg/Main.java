package com.ibilcerpg.ibilcerpg;
import com.ibilcerpg.ibilcerpg.Controllers.FXController;
import com.ibilcerpg.ibilcerpg.Design.CombatManager;
import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Design.Save;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    private Player jogador;
    CombatManager combate;
    private static Scanner input = new Scanner(System.in);

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
        cont.setData(jogador);
        cont.setarMusica(musica);

    }


    public static void main(String[] args){
        launch();

        Save infos = new Save();


        Main main = new Main();
        Player jogador = new Player();
        Alejandro coordenador = new Alejandro();
        jogador.receberMissao(coordenador.primeiraMissao);
        jogador.receberMissao(coordenador.segundaMissao);
        jogador.receberMissao(coordenador.terceiraMissao);

        int op = -1;
        while (op != 0){
            System.out.println("1 - Biologo , 2 - Tradutor, 3 - Matematico, 4 - inventario");
            op = input.nextInt();
            main.opcoes(op,jogador);
        }

    }

    public void opcoes(int op, Player jogador){
        switch (op){
            case 1:
                combate = new CombatManager(jogador, new Biologo());
                break;
            case 2 :
                combate = new CombatManager(jogador, new Traduteiro());
                break;
            case 3:
                combate = new CombatManager(jogador, new Matematico());
                break;
            case 4:
                jogador.getInventario().printInventario();
                System.out.println("Escolha qual item quer equipar");
                int i = input.nextInt();
                jogador.getInventario().equiparHabilidade(i);
                break;
            case 0:
                System.out.println("Obrigado por jogar.");
                System.exit(0);
                break;
        }
    }


    }


