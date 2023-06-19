package com.ibilcerpg.ibilcerpg.Controllers;



import com.ibilcerpg.ibilcerpg.Design.CombatManager;
import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.ibilcerpg.ibilcerpg.Main.jogador;

public class FXMapaController {


    @FXML
    public TextArea caixaDeTexto;

    @FXML
    public TextArea status;

    @FXML
    public Button inventarioButton;
    @FXML
    public Button irButton;
    @FXML
    public Button salvarButton;
    @FXML
    public Button sairButton;

    @FXML
    public Button tradutorButton;
    @FXML
    public Button matematicoButton;
    @FXML
    public Button biologoButton;
    @FXML
    public Button computeiroButton;

    Object botaoPressionado;

    private Musica musica;

//    public void atualizarStatus(ActionEvent event,Player jogador){
//        Object oi = event.getSource();
//        status.setText("Nome:" + jogador.getNome() + "\nVida:" + jogador.getVida() + "\n");
//    }

    public void selectedButton(ActionEvent event){

        botaoPressionado = event.getSource();
        if(botaoPressionado.equals(tradutorButton))
            imprimirTexto("-Vocês podem tentar desvendar os segredos da minha língua, mas enquanto decifram palavras," +
                    " eu decifro suas fraquezas, seus planos e seus destinos");
        if(botaoPressionado.equals(matematicoButton))
            imprimirTexto("-Eu sou a equação impenetrável que desafia a lógica e a razão. Enquanto vocês tentam " +
                    "resolver-me, eu calculo a derrota de vocês em cada movimento");
        if(botaoPressionado.equals(biologoButton))
            imprimirTexto("-Seus esforços são tão frágeis quanto as células que você estuda. Serei o vírus que " +
                    "infectará cada pensamento seu, destruindo a sua ciência com uma evolução imparável.");
        if(botaoPressionado.equals(computeiroButton))
            imprimirTexto("-Vocês podem lutar com todas as suas armas físicas, mas eu domino o mundo digital. Cada " +
                    "linha de código que escrevo é um obstáculo intransponível para vocês, presos em um labirinto virtual de vulnerabilidades.");
    }

    public void imprimirTexto(String texto){
        caixaDeTexto.setText(texto);

    }


    public void setarMusica(Musica musica){
        this.musica=musica;
        musica.pararMusica(musica.getMusicaMenu());
        musica.musicaCombate();
    }

    @FXML
    protected void inventarioClickButton(ActionEvent event) throws IOException {
        FXMLLoader inventario = new FXMLLoader(Main.class.getResource("Inventario.fxml"));
        Scene inventarioScene = new Scene(inventario.load());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inventarioScene);
        window.setTitle("Inventario");
        window.show();
    }

    @FXML
    protected void salvarButtonClick(ActionEvent event) throws IOException{
        Parent save = FXMLLoader.load((Main.class.getResource("Save.fxml")));
        Scene saveScene = new Scene(save);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(saveScene);
        window.setTitle("Save");
        window.show();

    }

    @FXML
    protected void irButtonClick(ActionEvent event)throws IOException{

        //CombatManager combateGame = new CombatManager(Main.jogador,Main.adversario);
        FXMLLoader combate = new FXMLLoader(Main.class.getResource("Combate.fxml"));
        Scene combateScene = new Scene(combate.load());
        //combateGame.iniciarCombate();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(combateScene);
        window.setTitle("Combate");
        window.show();

        FXCombateController cont = combate.getController();
        if(botaoPressionado==null) cont.setarMusica(musica);



    }

    public void sairButtonClick(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load((Main.class.getResource("Menu.fxml")));
        Scene menuScene = new Scene(menu);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.setTitle("IbilceRPG");
        window.show();
    }
}
