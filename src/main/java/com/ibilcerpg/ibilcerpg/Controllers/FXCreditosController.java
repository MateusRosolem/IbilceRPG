package com.ibilcerpg.ibilcerpg.Controllers;

import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Main;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FXCreditosController {
    private Player jogador;

    private Musica musica;
    @FXML
    public Button voltarButton;



    public void setData(Player jogador,Musica musica){
        this.jogador=jogador;
        musica = new Musica();
        this.musica=musica;
    }

    @FXML
    public void voltarButtonClick(ActionEvent event) throws IOException {
        FXMLLoader menu = new FXMLLoader(Main.class.getResource("Menu.fxml"));
        Scene menuScene = new Scene(menu.load());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.setTitle("IbilceRPG");
        window.show();

        FXController cont = menu.getController();
        cont.setData(jogador,musica);

    }




}
