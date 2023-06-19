package com.ttchoa22ite.population.controllers.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatController {
    @FXML
    private TextFlow emojiList;


    @FXML
    void getHome(MouseEvent event) {

        try {
            Parent parent = FXMLLoader.load(((getClass().getResource("home.fxml"))));
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initialize(URL location, ResourceBundle resources) {

//        System.out.println(username);
        for (Node text : emojiList.getChildren()) {
            text.setOnMouseClicked(event -> {
                javafx.scene.text.Text txtMsg = null;
                txtMsg.setText(txtMsg.getDepthTest() + " " + ((Text) text).getTextContent());
                emojiList.setVisible(false);
            });
        }
    }

    @FXML
    void emojiAction(ActionEvent event) {

        if(emojiList.isVisible()){

            emojiList.setVisible(false);
        }else {
            emojiList.setVisible(true);
        }
    }
}
