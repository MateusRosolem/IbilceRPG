package com.ibilcerpg.ibilcerpg;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import com.ibilcerpg.ibilcerpg.Personagens.*;
import com.ibilcerpg.ibilcerpg.Objetos.*;
import com.ibilcerpg.ibilcerpg.Design.*;
import com.ibilcerpg.ibilcerpg.SuperClasses.*;


public class MainJogo extends Application {

    CombatManager combate;
    private static Scanner input = new Scanner(System.in);


    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("Files/8_bit_hud.ttf"), 78);
        FXMLLoader fxmlLoader = new FXMLLoader(MainJogo.class.getResource("JavaFX/Menu/Menu.fxml"));
        Scene sceneMenu = new Scene(fxmlLoader.load(), 930, 540);
        sceneMenu.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("JavaFX/stylebutton.css")).toExternalForm());
        stage.setTitle("IbilceRPG");
        stage.setScene(sceneMenu);
        stage.setResizable(false);
        stage.show();

    }


    public static void main(String[] args){
        launch();
        MainJogo main = new MainJogo();
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
                break;
        }
    }



}
