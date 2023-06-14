package com.ttchoa22ite.population.controllers.home;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeController {

    @FXML
    private Button logoutBtn;

    @FXML
    private Button manageButton;

    @FXML
    private Button permanan;

    @FXML
    void getResident(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(((getClass().getResource(""))));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ResidentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

