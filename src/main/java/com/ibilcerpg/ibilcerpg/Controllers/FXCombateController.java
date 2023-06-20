package com.ibilcerpg.ibilcerpg.Controllers;


import com.ibilcerpg.ibilcerpg.Design.CombatManager;
import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

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
    public TextArea caixaDeTexto;
    @FXML
    public TextArea caixaStatus;
    @FXML
    public Text vidaInimigo;




    public void setData(Player jogador,CombatManager combate){
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
        caixaDeTexto.setText(texto);
    }

    public void imprimirStatus(){
        caixaStatus.setText("Nome:" + jogador.getNome() + "\nExperiencia:" + jogador.getExperiencia()
                + "\nNivel:" + jogador.getNivel() + "\nAtaque Base:" + jogador.getAtaqueBase() + "\nDefesa Base:" +
                jogador.getDefesaBase() + "\nVida Atual:" + jogador.getVidaAtual() + "\ntVida Maxima:" + jogador.getVidaMaxima()
                + "\nVelocidade:" + jogador.getVelocidade());
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
        if(verificarTurno()) {

            combate.setAcao(jogador.jogadorAtacar());
            //vidaProgressBarUpdate(vidaProgressBar);

            // jogador.ataque();
            // vidaProgressBarUpdate(vidaProgressBar,dano);
            imprimirStatus();

        }

    }
    @FXML
    protected void defenderButtonClick(){
        if(verificarTurno()) {

            combate.setAcao(jogador.jogadorDefender());

           // jogador.defesa();
            imprimirStatus();

        }

    }
    @FXML
    protected void habilidadeButtonClick(){
        if(verificarTurno()) {

            combate.setAcao(jogador.jogadorHabilidade());
            //vidaProgressBarUpdate(playerVidaProgresBar);

            //jogador.habilidade();
            //vidaProgressBarUpdate(playerVidaProgresBar,dano);
            imprimirStatus();

        }
    }
    @FXML
    protected void itemButtonClick(){
        if(verificarTurno()) {

            combate.setAcao(jogador.jogadorItem());
            //vidaProgressBarUpdate(playerVidaProgresBar);
    }

            //  jogador.item();
            // vidaProgressBarUpdate(playerVidaProgresBar,);
            imprimirStatus();
        }


    }
    //@FXML
//    protected void vidaProgressBarUpdate(ProgressBar ProgressBar,int dano){
//        ProgressBar.setProgress(ProgressBar.getProgress() - dano);
//        vidaInimigo.setText("VIDA: " + dano + "/20");
//    }


