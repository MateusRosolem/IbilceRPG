package com.ibilcerpg.ibilcerpg.Controllers;



import com.ibilcerpg.ibilcerpg.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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


//    public void atualizarStatus(ActionEvent event,Player jogador){
//        Object oi = event.getSource();
//        status.setText("Nome:" + jogador.getNome() + "\nVida:" + jogador.getVida() + "\n");
//    }

    public void selectedButton(ActionEvent event){

        Object botao = event.getSource();
        if(botao.equals(tradutorButton))
            imprimirTexto("-Vocês podem tentar desvendar os segredos da minha língua, mas enquanto decifram palavras," +
                    " eu decifro suas fraquezas, seus planos e seus destinos");
        if(botao.equals(matematicoButton))
            imprimirTexto("-Eu sou a equação impenetrável que desafia a lógica e a razão. Enquanto vocês tentam " +
                    "resolver-me, eu calculo a derrota de vocês em cada movimento");
        if(botao.equals(biologoButton))
            imprimirTexto("-Seus esforços são tão frágeis quanto as células que você estuda. Serei o vírus que " +
                    "infectará cada pensamento seu, destruindo a sua ciência com uma evolução imparável.");
        if(botao.equals(computeiroButton))
            imprimirTexto("-Vocês podem lutar com todas as suas armas físicas, mas eu domino o mundo digital. Cada " +
                    "linha de código que escrevo é um obstáculo intransponível para vocês, presos em um labirinto virtual de vulnerabilidades.");
       // CombatManager combate = new CombatManager();
    }

    public void imprimirTexto(String texto){
        caixaDeTexto.setText(texto);

    }


    @FXML
    protected void inventarioClickButton(ActionEvent event) throws IOException {
        Parent inventario = FXMLLoader.load((Main.class.getResource("Inventario.fxml")));
        Scene inventarioScene = new Scene(inventario);

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

    protected void irButtonClick(ActionEvent event)throws IOException{
        Parent combate = FXMLLoader.load((Main.class.getResource("Combate.fxml")));
        Scene combateScene = new Scene(combate);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(combateScene);
        window.setTitle("Combate");
        window.show();

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
