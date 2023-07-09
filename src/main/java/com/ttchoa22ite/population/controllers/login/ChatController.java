package com.ttchoa22ite.population.controllers.login;
import com.ttchoa22ite.population.models.ChatModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatController {
    @FXML
    private AnchorPane onl;
    @FXML
    private TextFlow emojiList;
    @FXML
    private VBox chatBox;
    @FXML
    private TextArea chatArea;
    @FXML
    private HBox inputBox;
    @FXML
    private TextField messageField;
    @FXML
    private Button emojiButton;
    @FXML
    private Button sendButton;

    private ChatModel chatModel;




    public void initialize() {
        chatModel = new ChatModel();

        // Set up listener for receiving messages
        chatModel.setOnMessageReceivedListener(this::appendMessage);

        // Set up actions for UI elements
        emojiButton.setOnAction(event -> emojiAction());
        sendButton.setOnAction(event -> sendMessage());
    }

    public void emojiAction() {
        // TODO: Implement emoji action
    }

    public void sendMessage() {
        String message = messageField.getText();
        chatModel.sendMessage(message);
        chatArea.appendText(message+"\n");
        messageField.clear();

    }

    public void appendMessage(String message) {
        chatArea.appendText(message + "\n");
    }

    public void shutdown() {
        chatModel.shutdown();
    }

    public void getHome(javafx.scene.input.MouseEvent mouseEvent) {
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
}
