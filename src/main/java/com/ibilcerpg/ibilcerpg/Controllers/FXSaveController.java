package com.ibilcerpg.ibilcerpg.Controllers;

import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Design.Save;
import com.ibilcerpg.ibilcerpg.Main;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FXSaveController {
    // Save save = new Save();

    private Player jogador;
    private Save save;

    @FXML
    public Label labSingleFile;
    @FXML
    public Button salvarButton;
    @FXML
    public Button carregarSaveButton;
    @FXML
    public Button voltarButton;
    @FXML
    public Button jogarButton;
    @FXML
    private ObservableList<Pane> list = FXCollections.observableArrayList();;


    public void setData(Player jogador){
        this.jogador=jogador;

    }

    @FXML
    protected void salvarButton(ActionEvent event) throws IOException {
          Save.salvar(jogador);
    }

    @FXML
    public void carregarSaveButton(ActionEvent event) throws IOException {
        Player jogador =Save.carregarSave();
        setData(jogador);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:/Users/baron/OneDrive/Área de Trabalho/CODES/3SEM/IbilceRPG/src/main/resources/Saves"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("OBJ Files","*.obj"));
        File f = fileChooser.showOpenDialog(null);

        if(f!=null) {
            if (jogador == null) {
                labSingleFile.setText("\nSelected File:" + f.getPath());
            } else {
                labSingleFile.setText("Nome:" + jogador.getNome() + "\nExperiencia:" + jogador.getExperiencia()
                        + "\nNivel:" + jogador.getNivel() + "\nAtaque Base:" + jogador.getAtaqueBase() + "\nDefesa Base:" +
                        jogador.getDefesaBase() + "\nVida Atual:" + jogador.getVidaAtual() + "\nVida Maxima:" + jogador.getVidaMaxima()
                        + "\nVelocidade:" + jogador.getVelocidade());
            }
        }
    }

    @FXML
    public void voltarButtonClick(ActionEvent event) throws IOException {
        //ACHO QUE NAO VAI PASSAR COM O SAVE
        FXMLLoader menu = new FXMLLoader(Main.class.getResource("Menu.fxml"));
        Scene menuScene = new Scene(menu.load());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.setTitle("IbilceRPG");
        window.show();

        FXController cont = menu.getController();
        cont.setData(jogador);
        if(!Musica.estaTocando()) Musica.tocarMusicaMenu();
    }

    @FXML
    public void jogarButtonClick(ActionEvent event) throws IOException {
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



}
