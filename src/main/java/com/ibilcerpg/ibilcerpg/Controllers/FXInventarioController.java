package com.ibilcerpg.ibilcerpg.Controllers;

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

public class FXInventarioController {
    private Player jogador;
    @FXML
    public Button voltarButton;
    @FXML
    public Button equiparButton;
    @FXML
    public Button desequiparButton;


    public void setData(Player jogador){
        this.jogador=jogador;
    }

    @FXML
    public void voltarButtonClick(ActionEvent event) throws IOException {
        FXMLLoader mapa = new FXMLLoader(Main.class.getResource("Mapa.fxml"));
        Scene mapaScene = new Scene(mapa.load());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mapaScene);
        window.setTitle("Mapa");
        window.show();

        FXMapaController cont = mapa.getController();
        cont.setData(jogador);
    }


}
