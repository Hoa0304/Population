package com.ttchoa22ite.population.controllers.login;
import com.ttchoa22ite.population.utils.ConnectionDB;
import com.ttchoa22ite.population.DAO.ResidentDAO;
import com.ttchoa22ite.population.models.Resident;
import com.ttchoa22ite.population.utils.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ttchoa22ite.population.DAO.ResidentDAO.*;

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

    @FXML
    private Button insertResident;
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public ResidentController(){
        con = ConnectionDB.DAO();
    }

    public void initialize() {
        try {
            ObservableList<Resident> residentList = ResidentDAO.getAllresident();
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
                Resident selectrsd = residentView.getSelectionModel().getSelectedItem();
                if(selectrsd!=null){
                    CccdText.setText(selectrsd.getCccd());
                    addressText.setText(selectrsd.getAddress());
                    jobText.setText(selectrsd.getJob());
                    dateText.setText(selectrsd.getBirt());
                    phoneText.setText(selectrsd.getNOphone());
                    sshkText.setText(selectrsd.getSshk());
                    nameText.setText(selectrsd.getName());
                    sexText.setText(selectrsd.getSex());
                }else{
                    CccdText.setText("");
                    addressText.setText("");
                    jobText.setText("");
                    dateText.setText("");
                    phoneText.setText("");
                    sshkText.setText("");
                    nameText.setText("");
                    sexText.setText("");
                }
            }
        });
    }
    @FXML
    void getChat(MouseEvent event) {

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("chat.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

    }
    @FXML
    void deleteAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            deleteResident(sshkText.getText());
            Resident selectedResident = residentView.getSelectionModel().getSelectedItem();
            if (selectedResident != null) {
                residentView.getItems().remove(selectedResident);
                clearfield();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @FXML
    void editAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            updateResident(sshkText.getText(), nameText.getText(),sexText.getText(), CccdText.getText(),phoneText.getText(), addressText.getText(), jobText.getText()); dateText.getText();
            Resident selectedResident = residentView.getSelectionModel().getSelectedItem();
            if (selectedResident != null) {
                selectedResident.setName(nameText.getText());
                selectedResident.setSshk(sshkText.getText());
                selectedResident.setSex(sexText.getText());
                selectedResident.setBirt(dateText.getText());
                selectedResident.setCccd(CccdText.getText());
                selectedResident.setNOphone(phoneText.getText());
                selectedResident.setJob(jobText.getText());
                residentView.refresh();
                clearfield();
            }
        } catch (SQLException e) {
        }

    }

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    private void searchResident(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {

            Resident resident1 = ResidentDAO.searchResident(nameTextS.getText());

            populateAndShowResident(resident1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    @FXML
    private void populateAndShowResident(Resident resident1) throws ClassNotFoundException {
        if (resident1 != null) {
            populateResident(resident1);
        }
    }

    @FXML
    private void populateResident(Resident resident1) throws ClassNotFoundException {

        ObservableList<Resident> residentData = FXCollections.observableArrayList();

        residentData.add(resident1);
        residentView.setItems(residentData);
    }

    @FXML
    private void insertResident(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            ResidentDAO.isertResident(sshkText.getText(), nameText.getText(), CccdText.getText(), phoneText.getText(), addressText.getText(), jobText.getText(), sexText.getText(),dateText.getText() );
            residentView.getItems().clear();
            try {
                ObservableList<Resident> residentList = ResidentDAO.getAllresident();
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
            clearfield();
        } catch (SQLException e) {
            throw e;
        }
    }
   private void clearfield(){
        addressText.setText("");
        jobText.setText("");
        nameText.setText("");
        phoneText.setText("");
        nameTextS.setText("");
        sshkText.setText("");
        dateText.setText("");
        sexText.setText("");
        CccdText.setText("");
   }
//    public Date date(){
//
////        Resident resident;
////        resident = new Resident();
////        dateText.setText(String.valueOf(resident.getDate()));
//        String inputDate = dateText.getText();
//
//        try{
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//            java.util.Date date = formatter.parse(inputDate);
//        } catch(Exception e) {
//        }
//        return Date.valueOf(inputDate);
//    }

//    public Date upDate(){
////        Resident resident;
////        resident = new Resident();
////        upDateText.setText(String.valueOf(resident.getDate()));
//        String inputDate = upDateText.getText();
//
//        try{
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//            java.util.Date date = formatter.parse(inputDate);
//        } catch(Exception e) {
//        }
//        return Date.valueOf(inputDate);
//    }
}


