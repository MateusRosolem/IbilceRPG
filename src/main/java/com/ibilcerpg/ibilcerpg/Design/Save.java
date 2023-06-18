package com.ibilcerpg.ibilcerpg.Design;

import com.ibilcerpg.ibilcerpg.Personagens.Player;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.io.IOException;
import java.nio.file.Files;


public class Save extends Player implements Serializable{


    //Salvar
    public void salvar(Player usuario) {
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            FileOutputStream fileOut = new FileOutputStream("Save.obj");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(usuario);
            objectOut.writeObject(dateTime);

            objectOut.close();
            fileOut.close();
            //System.out.println("Jogo salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //abrir save
    public Player carregarSave() {//mandar pra função qual save que quer abrir como parametro
        try {
            FileInputStream fileIn = new FileInputStream("Save.obj");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Player usuario = (Player) objectIn.readObject();
            Player player = new Player();//novo jogador que vai receber o save
            player.setVivo(usuario.getVivo());
            player.setVidaMaxima(usuario.getVidaMaxima());
            player.setVidaAtual(usuario.getVidaAtual());
            player.setAtaqueBase(usuario.getAtaqueBase());
            player.setDefesaBase(usuario.getDefesaBase());
            player.setVelocidade(usuario.getVelocidade());
            player.setMultiplicadorAtaque(usuario.getMultiplicadorAtaque());
            player.setMultiplicadorDefesa(usuario.getMultiplicadorDefesa());
            player.setExperiencia(usuario.getExperiencia());
            player.setNivel(usuario.getNivel());
            //Printar pra testar

            objectIn.close();
            fileIn.close();
            return player;
        } catch (ClassNotFoundException | IOException ex) {
            throw new RuntimeException(ex);
        }

    }







//        public static void main(String[] args) throws ClassNotFoundException {
//            Player usuario = new Player("mateus", "mateus12", true, 12, 5, 2, 2, 5, 1, 1, 1, 1);
//            Save save = new Save();
//            save.salvar(usuario);
//
//        }
}
