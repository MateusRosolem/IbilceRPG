package com.ibilcerpg.ibilcerpg.Controllers;


import com.ibilcerpg.ibilcerpg.Design.CombatManager;
import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Main;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
import javafx.event.ActionEvent;
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
    private Musica musica;
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

    public void setData(Player jogador, CombatManager combate,Musica musica){
        this.jogador=jogador;
        this.combate=combate;
        playerVidaProgressBar = new ProgressBar(100);
        inimigoVidaProgressBar = new ProgressBar(100);

        vidaInimigo.setText("VIDA: " + combate.getAdversario().getVidaMaxima()+"/"+combate.getAdversario().getVidaMaxima());
        imprimirStatus();

        musica = new Musica();
        this.musica=musica;
        musica.setMusica2();

    }

    public void imprimirTexto(String texto){
        caixaDeTexto.setText(texto);
    }

    public void imprimirStatus(){
        playerStatus = new TextArea();
        playerStatus.setText("Nome:" + jogador.getNome() + "\nExperiencia:" + jogador.getExperiencia()
                + "\nNivel:" + jogador.getNivel() + "\nAtaque Base:" + jogador.getAtaqueBase() + "\nDefesa Base:" +
                jogador.getDefesaBase() + "\nVida Atual:" + jogador.getVidaAtual() + "\nVida Maxima:" + jogador.getVidaMaxima()
                + "\nVelocidade:" + jogador.getVelocidade());
    }


    @FXML
    protected void atacarButtonClick() {
            if(!combate.verificarVivos()) {
                try {
                    terminate();
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
                terminate();
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    protected void defenderButtonClick(){
        if(!combate.verificarVivos()) {
            try {
                terminate();
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
                terminate();
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    @FXML
    protected void habilidadeButtonClick(){
        if(!combate.verificarVivos()) {
            try {
                terminate();
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
            combate.setAcao(jogador.jogadorHabilidade());
            combate.novoTurno(this);
            imprimirStatus();
        if(!combate.verificarVivos()) {
            try {
                terminate();
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    protected void itemButtonClick(){
        if(!combate.verificarVivos()) {
            try {
                terminate();
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
                terminate();
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    public void inimigoVidaProgressBarUpdate(){
        inimigoVidaProgressBar.setProgress( (double) combate.getAdversario().getVidaAtual() / combate.getAdversario().getVidaMaxima());
        vidaInimigo.setText("VIDA: "+ combate.getAdversario().getVidaAtual() + "/" + combate.getAdversario().getVidaMaxima());
    }

    @FXML
    public void playerVidaProgressBarUpdate(){
        playerVidaProgressBar.setProgress((double) combate.getJogador().getVidaAtual() /combate.getJogador().getVidaMaxima());
    }

    public void terminate() throws IOException {
        FXMLLoader mapa = new FXMLLoader(Main.class.getResource("Mapa.fxml"));
        Scene mapaScene = new Scene(mapa.load());

        Stage window = new Stage();//nao ta fechando
        window.setScene(mapaScene);
        window.setTitle("Mapa");
        window.show();

        FXMapaController cont = mapa.getController();
        cont.setData(jogador,musica);

    }


}


