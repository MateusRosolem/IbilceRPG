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
           // jogador.ataque();
            vidaProgressBarUpdate(vidaProgressBar);
        }

    }
    @FXML
    protected void defenderButtonClick(){
        if(verificarTurno()) {
           // jogador.defesa();
        }

    }
    @FXML
    protected void habilidadeButtonClick(){
        if(verificarTurno()) {
            //jogador.habilidade();
            vidaProgressBarUpdate(playerVidaProgresBar);
        }
    }
    @FXML
    protected void itemButtonClick(){
        if(verificarTurno()) {
          //  jogador.item();
            vidaProgressBarUpdate(playerVidaProgresBar);
    }

    }
    @FXML
    protected void vidaProgressBarUpdate(ProgressBar ProgressBar){
        ProgressBar.setProgress(50);
        vidaInimigo.setText("sim");
    }

}
