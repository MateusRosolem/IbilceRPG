package com.ibilcerpg.ibilcerpg.Controllers;


import com.ibilcerpg.ibilcerpg.Design.CombatManager;
import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Main;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class FXCombateController {

    private Player jogador;
    private CombatManager combate;

    @FXML
    public Button atacarButton;
    @FXML
    public Button defenderButton;
    @FXML
    public Button habilidadeButton;
    @FXML
    public Button itemButton;
    @FXML
    public ProgressBar inimigoVidaProgressBar;
    @FXML
    public ProgressBar playerVidaProgressBar;
    @FXML
    public TextArea caixaDeTexto;
    @FXML
    public TextArea playerStatus;
    @FXML
    public Text vidaInimigo;

    public void setData(Player jogador, CombatManager combate){
        this.jogador=jogador;
        this.combate=combate;

        vidaInimigo.setText("VIDA: " + combate.getAdversario().getVidaMaxima()+"/"+combate.getAdversario().getVidaMaxima());
        imprimirStatus();



    }

    public void imprimirTexto(String texto){
        caixaDeTexto.setText(texto);
    }

    public void imprimirStatus(){
        playerStatus = new TextArea();
        playerStatus.setText("Nome:" + jogador.getNome() + "\nExperiencia:" + jogador.getExperiencia()
                + "\nNivel:" + jogador.getNivel() + "\nAtaque Base:" + jogador.getAtaqueBase() + "\nDefesa Base:" +
                jogador.getDefesaBase() + "\nVida Atual:" + jogador.getVidaAtual() + "\nVida Maxima:" + jogador.getVidaMaxima());
    }


    @FXML
    protected void atacarButtonClick(ActionEvent event) {
            if(!combate.verificarVivos()) {
                try {
                    terminate(event);
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            combate.setAcao(jogador.jogadorAtacar());
            combate.novoTurno(this);
            imprimirStatus();
        if(!combate.verificarVivos()) {
            try {
                terminate(event);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    protected void defenderButtonClick(ActionEvent event){
        if(!combate.verificarVivos()) {
            try {
                terminate(event);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        combate.setAcao(jogador.jogadorDefender());
        combate.novoTurno(this);
        imprimirStatus();
        if(!combate.verificarVivos()) {
            try {
                terminate(event);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    @FXML
    protected void habilidadeButtonClick(ActionEvent event){
        if(!combate.verificarVivos()) {
            try {
                terminate(event);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

            combate.setAcao(jogador.jogadorHabilidade());
            if (combate.getJogador().getInventario().getHabilidadeEquipada().getEfeito().getT() == "PASSIVA") {
                imprimirTexto("A habilidade equipada é passiva, não é necessário ativá-la.");
                return;
            }
            if (combate.getJogador().getInventario().getHabilidadeEquipada().checarTempoDeRecarga()) {
                imprimirTexto("A habilidade equipada ainda está em tempo de recarga!");
                return;
            }
            combate.novoTurno(this);
            imprimirStatus();
            if(!combate.verificarVivos()) {
            try {
                terminate(event);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    protected void itemButtonClick(ActionEvent event){
        if(!combate.verificarVivos()) {
            try {
                terminate(event);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
            combate.setAcao(jogador.jogadorItem());
            combate.novoTurno(this);
            imprimirStatus();
        if(!combate.verificarVivos()) {
            try {
                terminate(event);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    public void inimigoVidaProgressBarUpdate() {
        if (inimigoVidaProgressBar == null) inimigoVidaProgressBar = new ProgressBar();
        inimigoVidaProgressBar.setProgress((double) combate.getAdversario().getVidaAtual() / combate.getAdversario().getVidaMaxima());
        vidaInimigo.setText("VIDA: " + combate.getAdversario().getVidaAtual() + "/" + combate.getAdversario().getVidaMaxima());
    }

    @FXML
    public void playerVidaProgressBarUpdate(){
        if(playerVidaProgressBar == null) playerVidaProgressBar = new ProgressBar();
        playerVidaProgressBar.setProgress((double) combate.getJogador().getVidaAtual() /combate.getJogador().getVidaMaxima());
    }

    public void terminate(ActionEvent event) throws IOException {
        FXMLLoader mapa = new FXMLLoader(Main.class.getResource("Mapa.fxml"));
        Scene mapaScene = new Scene(mapa.load());
        Musica.pararMusicaCombate();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mapaScene);
        window.setTitle("Mapa");
        window.show();

        FXMapaController cont = mapa.getController();
        cont.setData(jogador);
        if(!Musica.estaTocando())Musica.tocarMusicaMenu();


    }


}


