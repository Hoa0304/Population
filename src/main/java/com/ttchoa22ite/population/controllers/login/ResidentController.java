package com.ttchoa22ite.population.controllers.login;

import com.ttchoa22ite.population.utils.ConnectionDB;
import com.ttchoa22ite.population.DAO.ResidentDAO;
import com.ttchoa22ite.population.models.Resident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResidentController {

    @FXML
    private TextField CccdText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField dateText;

    @FXML
    private TextField jobText;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button manageButton;

    @FXML
    private TextField nameText;

    @FXML
    private TextField nameTextS;

    @FXML
    private TextField phoneText;

    @FXML
    private TableColumn<Resident, String> residentAddressCol;

    @FXML
    private TableColumn<Resident, String> residentBirtCol;

    @FXML
    private TableColumn<Resident, String> residentCccdCol;

    @FXML
    private TableColumn<Resident, Integer> residentIdColumn;

    @FXML
    private TableColumn<Resident, String> residentJobCol;

    @FXML
    private TableColumn<Resident, String> residentNameCol;

    @FXML
    private TableColumn<Resident, String> residentPhoneCol;

    @FXML
    private TableColumn<Resident, String> residentSexCol;

    @FXML
    private TableColumn<Resident, String> residentSshkCol;

    @FXML
    private TableView<Resident> residentView;

    @FXML
    private TextField sexText;

    @FXML
    private TextField sshkText;

    private ResidentDAO residentDAO;

    public ResidentController() {
        residentDAO = new ResidentDAO();
    }

    public void initialize() {
        try {
            ObservableList<Resident> residentList = residentDAO.getAllResidents();
            residentView.setItems(residentList);
            residentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            residentSshkCol.setCellValueFactory(new PropertyValueFactory<>("sshk"));
            residentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            residentAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            residentBirtCol.setCellValueFactory(new PropertyValueFactory<>("birt"));
            residentJobCol.setCellValueFactory(new PropertyValueFactory<>("job"));
            residentCccdCol.setCellValueFactory(new PropertyValueFactory<>("cccd"));
            residentPhoneCol.setCellValueFactory(new PropertyValueFactory<>("NOphone"));
            residentSexCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }

        residentView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Resident selectedResident = residentView.getSelectionModel().getSelectedItem();
                if (selectedResident != null) {
                    CccdText.setText(selectedResident.getCccd());
                    addressText.setText(selectedResident.getAddress());
                    jobText.setText(selectedResident.getJob());
                    dateText.setText(selectedResident.getBirt());
                    phoneText.setText(selectedResident.getNOphone());
                    sshkText.setText(selectedResident.getSshk());
                    nameText.setText(selectedResident.getName());
                    sexText.setText(selectedResident.getSex());
                } else {
                    clearFields();
                }
            }
        });
    }

    @FXML
    void getChat(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("chat.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void getHome(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("home.fxml"));
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

    @FXML
    void deleteAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            String sshk = sshkText.getText();
            residentDAO.deleteResident(sshk);
            Resident selectedResident = residentView.getSelectionModel().getSelectedItem();
            if (selectedResident != null) {
                residentView.getItems().remove(selectedResident);
                clearFields();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @FXML
    void editAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            String sshk = sshkText.getText();
            String name = nameText.getText();
            String sex = sexText.getText();
            String cccd = CccdText.getText();
            String NOphone = phoneText.getText();
            String address = addressText.getText();
            String job = jobText.getText();
            residentDAO.updateResident(sshk, name, sex, cccd, NOphone, address, job);
            Resident selectedResident = residentView.getSelectionModel().getSelectedItem();
            if (selectedResident != null) {
                selectedResident.setName(name);
                selectedResident.setSshk(sshk);
                selectedResident.setSex(sex);
                selectedResident.setBirt(dateText.getText());
                selectedResident.setCccd(cccd);
                selectedResident.setNOphone(NOphone);
                selectedResident.setJob(job);
                residentView.refresh();
                clearFields();
            }
        } catch (SQLException e) {
            // Handle the exception
        }
    }

    @FXML
    private void searchResident(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            String residentName = nameTextS.getText();
            Resident resident = residentDAO.searchResident(residentName);
            populateAndShowResident(resident);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    private void populateAndShowResident(Resident resident) throws ClassNotFoundException {
        if (resident != null) {
            populateResident(resident);
        }
    }

    @FXML
    private void populateResident(Resident resident) throws ClassNotFoundException {
        ObservableList<Resident> residentData = FXCollections.observableArrayList();
        residentData.add(resident);
        residentView.setItems(residentData);
    }

    @FXML
    private void insertResident(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try{
            String name = nameText.getText();
            String cccd = CccdText.getText();
            String sshk = sshkText.getText();
            String NOphone = phoneText.getText();
            String address = addressText.getText();
            String job = jobText.getText();
            String sex = sexText.getText();
            String birt = dateText.getText();
            residentDAO.insertResident(name, cccd, sshk, NOphone, address, job, sex, birt);
            residentView.getItems().clear();
            try {
                ObservableList<Resident> residentList = residentDAO.getAllResidents();
                residentView.setItems(residentList);
                residentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                residentSshkCol.setCellValueFactory(new PropertyValueFactory<>("sshk"));
                residentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                residentAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
                residentBirtCol.setCellValueFactory(new PropertyValueFactory<>("birt"));
                residentJobCol.setCellValueFactory(new PropertyValueFactory<>("job"));
                residentCccdCol.setCellValueFactory(new PropertyValueFactory<>("cccd"));
                residentPhoneCol.setCellValueFactory(new PropertyValueFactory<>("NOphone"));
                residentSexCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
            } catch (SQLException e) {
                System.err.println("Error: " + e);
            }
            residentView.refresh();
            clearFields();
        } catch (SQLException e) {
            throw e;
        }
    }

    private void clearFields() {
        CccdText.setText("");
        addressText.setText("");
        dateText.setText("");
        jobText.setText("");
        nameText.setText("");
        nameTextS.setText("");
        phoneText.setText("");
        sexText.setText("");
        sshkText.setText("");
    }
}
