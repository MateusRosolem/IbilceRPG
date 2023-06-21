package com.ibilcerpg.ibilcerpg.Controllers;



import com.ibilcerpg.ibilcerpg.Design.CombatManager;
import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Main;
import com.ibilcerpg.ibilcerpg.Personagens.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;


public class FXMapaController {
    private Player jogador;

    CombatManager combateGame;



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
    public Button traduteiroButton;
    @FXML
    public Button matematicoButton;
    @FXML
    public Button biologoButton;
    @FXML
    public Button computeiroButton;
    public Button bibliotecariaButton;
    public Button cozinhieroButton;
    public Button atleticaButton;
    public Button bateriaButton;
    public Button dAFButton;
    public Button entorpecidaButton;
    Object botaoPressionado;


//    public void atualizarStatus(ActionEvent event,Player jogador){
//        Object oi = event.getSource();
//        status.setText("Nome:" + jogador.getNome() + "\nVida:" + jogador.getVida() + "\n");
//    }

    public void setData(Player jogador){
        this.jogador=jogador;
        caixaDeTexto.setText("Este é o RPG do IBILCE!");
        setStatusPlayer();


    }

    public void setStatusPlayer(){
        if(jogador==null) jogador = new Player();
        status.setText("Nome:" + jogador.getNome() + "\nExperiencia:" + jogador.getExperiencia()
                + "\nNivel:" + jogador.getNivel() + "\nAtaque Base:" + jogador.getAtaqueBase() + "\nDefesa Base:" +
                jogador.getDefesaBase() + "\nVida Atual:" + jogador.getVidaAtual() + "\nVida Maxima:" + jogador.getVidaMaxima()
                + "\nVelocidade:" + jogador.getVelocidade());
    }

    public void selectedButton(ActionEvent event){

        botaoPressionado = event.getSource();
        if(botaoPressionado.equals(traduteiroButton))
            imprimirTexto("-Vocês podem tentar desvendar os segredos da minha língua, mas enquanto decifram palavras," +
                    " eu decifro suas fraquezas, seus planos e seus destinos");
        else if(botaoPressionado.equals(matematicoButton))
            imprimirTexto("-Eu sou a equação impenetrável que desafia a lógica e a razão. Enquanto vocês tentam " +
                    "resolver-me, eu calculo a derrota de vocês em cada movimento");
        else if(botaoPressionado.equals(biologoButton))
            imprimirTexto("-Seus esforços são tão frágeis quanto as células que você estuda. Serei o vírus que " +
                    "infectará cada pensamento seu, destruindo a sua ciência com uma evolução imparável.");
        else if(botaoPressionado.equals(computeiroButton))
            imprimirTexto("-Vocês podem lutar com todas as suas armas físicas, mas eu domino o mundo digital. Cada " +
                    "linha de código que escrevo é um obstáculo intransponível para vocês, presos em um labirinto virtual de vulnerabilidades.");
    }

    public void imprimirTexto(String texto){
        caixaDeTexto.setText(texto);

    }



    @FXML
    protected void inventarioClickButton(ActionEvent event) throws IOException {
        FXMLLoader inventario = new FXMLLoader(Main.class.getResource("Inventario.fxml"));
        Scene inventarioScene = new Scene(inventario.load());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inventarioScene);
        window.setTitle("Inventario");
        window.show();

        FXInventarioController cont = inventario.getController();
        cont.setData(jogador);
        if(!Musica.estaTocando()) Musica.tocarMusicaMenu();
    }

    @FXML
    protected void salvarButtonClick(ActionEvent event) throws IOException{
        FXMLLoader save = new FXMLLoader(Main.class.getResource("Save.fxml"));
        Scene saveScene = new Scene(save.load());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(saveScene);
        window.setTitle("Save");
        window.show();

        FXSaveController cont = save.getController();
        cont.setData(jogador);
        if(!Musica.estaTocando()) Musica.tocarMusicaMenu();

    }

    @FXML
    protected void irButtonClick(ActionEvent event) throws IOException, InterruptedException {
        if(botaoPressionado==null) return;

        if(jogador==null) jogador = new Player();

        if( botaoPressionado.equals(traduteiroButton) ){
            combateGame= new CombatManager(jogador, new <Traduteiro>Traduteiro());
        }else if(botaoPressionado.equals(computeiroButton)){
            combateGame= new CombatManager(jogador, new <Alejandro>Alejandro());
        }else if(botaoPressionado.equals(biologoButton)){
            combateGame= new CombatManager(jogador, new <Biologo>Biologo());
        }else if(botaoPressionado.equals(matematicoButton)){
            combateGame= new CombatManager(jogador, new <Matematico>Matematico());
        }


        FXMLLoader combate = new FXMLLoader(Main.class.getResource("Combate.fxml"));
        Scene combateScene = new Scene(combate.load());
        Musica.pararMusicaMenu();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(combateScene);
        window.setTitle("Combate");
        window.show();

        FXCombateController cont = combate.getController();
        cont.setData(jogador,combateGame);
        Musica.tocarMusicaCombate();




    }

    public void sairButtonClick(ActionEvent event) throws IOException {
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

    public void bibliotecariaButtonClick(ActionEvent event){
        jogador.receberMissao(Bibliotecaria.darMissao(this));
    }
    public void cozinheiroButtonClick(ActionEvent event){
        jogador.receberMissao(Cozinheiro.darMissao(this));
    }
    public void atleticaButtonClick(ActionEvent event){
        jogador.receberMissao(Atletica.darMissao(this));
    }
    public void bateriaButtonClick(ActionEvent event){
        jogador.receberMissao(Bateria.darMissao(this));
    }
    public void DAFButtonClick(ActionEvent event){
        jogador.receberMissao(DAF.darMissao(this));
    }
    public void entorpecidaButtonClick(ActionEvent event){
        jogador.receberMissao(Entorpecida.darMissao(this));
    }
}
