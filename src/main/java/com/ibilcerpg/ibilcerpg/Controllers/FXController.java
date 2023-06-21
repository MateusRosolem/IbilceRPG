package com.ibilcerpg.ibilcerpg.Controllers;

import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Main;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class FXController {

    private Player jogador;

    public Text hiddenText;
    @FXML
    public Button jogarButton;
    @FXML
    public Button creditosButton;
    @FXML
    public Button sairButton;





    public void setData(Player jogador) {
        this.jogador = jogador;
    }


    @FXML
    protected void jogarButtonClick(ActionEvent event) throws IOException{
        FXMLLoader mapa = new FXMLLoader(Main.class.getResource("Mapa.fxml"));
        Scene mapaScene = new Scene(mapa.load());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mapaScene);
        window.setTitle("Mapa");
        window.show();

        FXMapaController cont = mapa.getController();
        cont.setData(jogador);
        if(!Musica.estaTocando()) Musica.tocarMusicaMenu();

    }

    @FXML
    public void creditosButtonClick(ActionEvent event) throws IOException {
        FXMLLoader creditos = new FXMLLoader(Main.class.getResource("Creditos.fxml"));
        Scene creditosScene = new Scene(creditos.load());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(creditosScene);
        window.setTitle("Creditos");
        window.show();

        FXCreditosController cont = creditos.getController();
        cont.setData(jogador);
        if(!Musica.estaTocando()) Musica.tocarMusicaMenu();
    }

    @FXML
    protected void sairButtonClick(){
        hiddenText.setText("Obrigado por jogar!");
        System.exit(0);
    }


}
