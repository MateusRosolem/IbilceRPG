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
    public ProgressBar vidaProgressBar;
    @FXML
    public ProgressBar playerVidaProgresBar;
    @FXML
    public TextArea caixaDeTexto = new TextArea();

    @FXML
    public Text vidaInimigo;

    public void setData(Player jogador, CombatManager combate){
        this.jogador=jogador;
        this.combate=combate;
    }
    public void setarMusica(Musica musica){
        this.musica=musica;
        musica.pararMusica(musica.getMusicaMenu());
        musica.musicaCombate();
        vidaProgressBar.setStyle("-fx-acccent: red;");

    }
    public void imprimirTexto(String texto){
        caixaDeTexto.setText(caixaDeTexto.getText() + "\n" + texto);
    }

    public void limparCaixaDeTexto(){
        caixaDeTexto.setText("");
    }

    public boolean verificarTurno(){
        if(combate.getTurno())
            return true;
        else {
            imprimirTexto("Espere seu turno!");
            return false;
        }

    }

    @FXML
    protected void atacarButtonClick(){
            limparCaixaDeTexto();
            combate.setAcao(jogador.jogadorAtacar(this));
            //vidaProgressBarUpdate(vidaProgressBar);

            // jogador.ataque();
            // vidaProgressBarUpdate(vidaProgressBar,dano);
            //imprimirStatus();



            combate.novoTurno(this);
    }
    @FXML
    protected void defenderButtonClick(){
        limparCaixaDeTexto();
        combate.setAcao(jogador.jogadorDefender(this));
        vidaProgressBarUpdate(playerVidaProgresBar);
        combate.novoTurno(this);

    }
    @FXML
    protected void habilidadeButtonClick(){
            limparCaixaDeTexto();
            combate.setAcao(jogador.jogadorHabilidade(this));

            //vidaProgressBarUpdate(playerVidaProgresBar);
            vidaProgressBarUpdate(playerVidaProgresBar);
            combate.novoTurno(this);

    }
    @FXML
    protected void itemButtonClick(){

        limparCaixaDeTexto();
            combate.setAcao(jogador.jogadorItem(this));

            //vidaProgressBarUpdate(playerVidaProgresBar);

            vidaProgressBarUpdate(playerVidaProgresBar);
            combate.novoTurno(this);

    }
    @FXML
    public void vidaProgressBarUpdate(ProgressBar ProgressBar){
        ProgressBar.setProgress((double) combate.getAdversario().getVidaAtual() /combate.getAdversario().getVidaMaxima());
        vidaInimigo.setText("VIDA: " + combate.getAdversario().getVidaAtual() + "/" + combate.getAdversario().getVidaMaxima());
    }

    public void terminate() throws IOException {
        FXMLLoader mapa = new FXMLLoader(Main.class.getResource("Mapa.fxml"));
        Scene mapaScene = new Scene(mapa.load());

        Stage window = new Stage();
        window.setScene(mapaScene);
        window.setTitle("Mapa");
        window.show();

    }
    //@FXML
//    protected void vidaProgressBarUpdate(ProgressBar ProgressBar,int dano){
//        ProgressBar.setProgress(ProgressBar.getProgress() - dano);
//        vidaInimigo.setText("VIDA: " + dano + "/20");
//    }


//        FXMapaController cont = mapa.getController();
//        cont.setData(jogador);
    }


