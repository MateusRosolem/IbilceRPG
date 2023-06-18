package com.ibilcerpg.ibilcerpg.Controllers;

import com.ibilcerpg.ibilcerpg.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FXCreditosController {
    @FXML
    public Button voltarButton;

    @FXML
    public void voltarButtonClick(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load((Main.class.getResource("Menu.fxml")));
        Scene menuScene = new Scene(menu);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.setTitle("IbilceRPG");
        window.show();
    }




}
