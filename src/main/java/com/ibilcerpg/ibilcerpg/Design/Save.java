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

    private Player usuario;


    //Salvar
    public static void salvar(Player jogador) {
        try {
            if(jogador==null) jogador = new Player();
            System.out.println(jogador.getNome());

            LocalDateTime dateTime = LocalDateTime.now();
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/Saves/Save.obj");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(dateTime);
            objectOut.writeObject(jogador);

            FileInputStream fileStream = new FileInputStream("src/main/resources/Saves/Save.obj");
            ObjectInputStream objStream = new ObjectInputStream(fileStream);
            System.out.println("data data :" + (LocalDateTime) objStream.readObject());
            System.out.println("usuario data: " + (Player) objStream.readObject());



            objectOut.close();
            fileOut.close();
            System.out.println("Jogo salvo com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //abrir save
    public static Player carregarSave() {//mandar pra função qual save que quer abrir como parametro
        try {
            FileInputStream fileIn = new FileInputStream("src/main/resources/Saves/Save.obj");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            LocalDateTime dateTime = (LocalDateTime) objectIn.readObject();
            Player usuario = (Player) objectIn.readObject();


            objectIn.close();
            fileIn.close();
            return usuario;
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
