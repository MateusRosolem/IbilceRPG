package com.ibilcerpg.ibilcerpg.Controllers;

import com.ibilcerpg.ibilcerpg.Design.Musica;
import com.ibilcerpg.ibilcerpg.Main;
import com.ibilcerpg.ibilcerpg.Personagens.Player;
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

public class FXCreditosController {
    private Player jogador;

    @FXML
    public Button voltarButton;
    public TextArea texto;



    public void setData(Player jogador){
        this.jogador=jogador;
        texto.setText("""
                Desenvolvido por Mateus e Yhan:
                Mateus Rosolem: Organização e programção de classes e Design de telas e audio
                Yhan Pena: Programação do combate, personagens e criativos

                Recursos Utilizados:

                Linguagem de Programação: Java. / Ambiente de Desenvolvimento Integrado (IDE): Intellij, Git e Scene Builder/ Bibliotecas: JavaFX
                Músicas e Efeitos Sonoros: Bibliotecas de áudio livres de direitos autorais

                Agradecemos à comunidade de desenvolvedores de jogos online por fornecer recursos e tutoriais valiosos.
                Agradecemos aos desenvolvedores de bibliotecas de terceiros que contribuíram para aprimorar a experiência de jogo.
                Copyright e Direitos Autorais:

                O jogo IBILCE RPG é um trabalho original de Mateus e Yhan.
                Todos os direitos autorais do jogo e seu conteúdo são de propriedade exclusiva dos desenvolvedores.

                Este jogo foi desenvolvido como um projeto acadêmico e não tem fins comerciais.
                Qualquer semelhança com pessoas ou eventos reais é mera coincidência.
                Agradecemos a todos que jogaram e apoiaram o nosso jogo RPG da Faculdade. Esperamos que tenham se divertido tanto quanto nós nos divertimos criando-o. Seus comentários e sugestões são sempre bem-vindos!

                Divirta-se explorando o mundo da nossa faculdade e desfrute da aventura!

                Atenciosamente,

                Mateus e Yhan
                """);

    }

    @FXML
    public void voltarButtonClick(ActionEvent event) throws IOException {
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




}
