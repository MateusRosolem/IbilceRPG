package com.ibilcerpg.ibilcerpg.Controllers;

import com.ibilcerpg.ibilcerpg.Main;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
import com.ibilcerpg.ibilcerpg.SuperClasses.Habilidade;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
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
    @FXML
    public TextArea descricaoItem;
    @FXML
    public TextArea itensEquipados;
    @FXML
    public TextArea statusPlayer;
    @FXML
    public ListView listaItens;

    public void setDescricaoItem(String texto){
        descricaoItem.setText(texto);
    }
    public void setItensEquipados(String texto){
        itensEquipados.setText(texto);
    }
    public void setStatusPlayer(){
        statusPlayer.setText("Nome:" + jogador.getNome() + "\nExperiencia:" + jogador.getExperiencia()
                + "\nNivel:" + jogador.getNivel() + "\nAtaque Base:" + jogador.getAtaqueBase() + "\nDefesa Base:" +
                jogador.getDefesaBase() + "\nVida Atual:" + jogador.getVidaAtual() + "\nVida Maxima:" + jogador.getVidaMaxima()
                + "\nVelocidade:" + jogador.getVelocidade());
    }


    public void setData(Player jogador){
        this.jogador=jogador;
        setStatusPlayer();
        jogador.getInventario().printInventario();
        setDescricaoItem("Este é seu inventário");

        ObservableList<Label> ob = FXCollections.observableArrayList();
        //Habilidade hab : jogador.getInventario().getHabilidades()
        for(int i=0; i<10; i++) {
            //ob.add(hab.getNome()+hab.getTipo()+hab.getEfeito().getT()+hab.getTempoDeRecarga());
            Label lb = new Label("Dente de vampiro   Ataque   +5 de vida   3 rodadas"+i);
            lb.setFont(Font.font(16));
            int finalI = i;
            lb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    descricaoItem.setText(String.format("Dente de vampiro   Ataque   +5 de vida   3 rodadas"+ finalI));
                }
            });
            ob.add(lb);
        }

        listaItens.setItems(ob);

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
