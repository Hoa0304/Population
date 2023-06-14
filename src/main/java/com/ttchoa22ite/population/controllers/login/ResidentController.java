package com.ttchoa22ite.population.controllers.login;

import com.ttchoa22ite.population.DAO.ResidentDAO;
import com.ttchoa22ite.population.models.Resident;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
    private TableColumn<?, ?> residentAddressCol;

    @FXML
    private TableColumn<?, ?> residentBirtCol;

    @FXML
    private TableColumn<?, ?> residentCccdCol;

    @FXML
    private TableColumn<?, ?> residentIdColumn;

    @FXML
    private TableColumn<?, ?> residentJobCol;

    @FXML
    private TableColumn<?, ?> residentNameCol;

    @FXML
    private TableColumn<?, ?> residentPhoneCol;

    @FXML
    private TableColumn<?, ?> residentSexCol;

    @FXML
    private TableColumn<?, ?> residentSshkCol;

    @FXML
    private TableView<Resident> residentView;

    @FXML
    private TextField sexText;

    @FXML
    private TextField sshkText;

    @FXML
    private Button insertResident;
    @FXML
    void deleteAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        try {
            ResidentDAO.deleteResident(sshkText.getText());
        } catch (SQLException e) {
            throw e;
        }
    }

    @FXML
    void editAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            ResidentDAO.updateResident(sshkText.getText(), nameText.getText(),sexText.getText(), CccdText.getText(),phoneText.getText(), addressText.getText(), jobText.getText());
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
            ResidentDAO.insertResident(sshkText.getText(), nameText.getText(), CccdText.getText(), phoneText.getText(), addressText.getText(), jobText.getText(), sexText.getText(),dateText.getText() );
        } catch (SQLException e) {
            throw e;
        }
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


