package com.ibilcerpg.ibilcerpg.Controllers;


import com.ibilcerpg.ibilcerpg.Design.Musica;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class FXCombateController {

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

    public TextArea caixaDeTexto;

    @FXML
    public Text vidaInimigo;
    private Musica musica;

    public void setarMusica(Musica musica){
        this.musica=musica;
        musica.pararMusica(musica.getMusicaMenu());
        musica.musicaCombate();

    }

    @FXML
    protected void atacarButtonClick(){
//        combateGame().getPlayer().
//
//        caixaDeTexto.setText(combateGame.imprimirStatus());
    }
    @FXML
    protected void defenderButtonClick(){
       // combateGame.

    }
    @FXML
    protected void habilidadeButtonClick(){}
    @FXML
    protected void itemButtonClick(){}
    @FXML
    protected void vidaProgressBarUpdate(){}

}
