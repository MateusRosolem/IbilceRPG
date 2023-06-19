package com.ibilcerpg.ibilcerpg.Controllers;

import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Main;
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

public class FXController {
    @FXML
    public Button jogarButton;
    @FXML
    public Button creditosButton;
    @FXML
    public Button sairButton;

    private Musica musica;

    public void setarMusica(Musica musica){
        this.musica=musica;
        musica.musicaMenu();
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
        if (cont!=null)cont.setarMusica(musica);

    }

    @FXML
    public void creditosButtonClick(ActionEvent event) throws IOException {
        Parent creditos = FXMLLoader.load((Main.class.getResource("Creditos.fxml")));
        Scene creditosScene = new Scene(creditos);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(creditosScene);
        window.setTitle("Creditos");
        window.show();
    }

    @FXML
    protected void sairButtonClick(){
        System.exit(0);
    }


}
