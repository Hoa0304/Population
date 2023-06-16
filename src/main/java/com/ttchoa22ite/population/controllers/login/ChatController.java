package com.ttchoa22ite.population.controllers.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.TextFlow;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController {
    @FXML
    private TextFlow emojiList;
    @FXML
    void emojiAction(ActionEvent event) {

        if(emojiList.isVisible()){

            emojiList.setVisible(false);
        }else {
            emojiList.setVisible(true);
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
}
