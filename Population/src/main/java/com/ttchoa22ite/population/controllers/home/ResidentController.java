package com.ttchoa22ite.population.controllers.home;

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
    private Button homeBtn;

    @FXML
    private TextField nameText;

    @FXML
    private TextField phoneText;

    @FXML
    private TableColumn<Resident, Integer> residentAddressCol;

    @FXML
    private TableColumn<Resident, String> residentCccdCol;

    @FXML
    private TableColumn<Resident, String> residentDomicileCol;

    @FXML
    private TableColumn<Resident, String> residentIdColumn;

    @FXML
    private TableColumn<Resident, String> residentJobCol;

    @FXML
    private TableColumn<Resident, String> residentNameCol;

    @FXML
    private TableColumn<Resident, String> residentNationalityCol;

    @FXML
    private TableColumn<Resident, String> residentPhoneCol;

    @FXML
    private TableColumn<Resident, String> residentSexCol;

    @FXML
    private TableColumn<Resident, String> residentSshkCol;

    @FXML
    private TableColumn<Resident, String> residentUpDateCol;
    @FXML
    private TableColumn<Resident, Date> residentDateCol;

    @FXML
    private TableView<Resident> residentView;


    @FXML
    private TextField sexText;

    @FXML
    private TextField sshkText;
    @FXML
    private TextField dateText;

    @FXML
    private TextField upDateText;
    @FXML
    private TextField jobText;
    @FXML
    private TextField nationalityText;

    @FXML
    private TextField domicileText;

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
            ResidentDAO.updateResident(sshkText.getText(), nameText.getText(), date(),sexText.getText(), CccdText.getText(),phoneText.getText(), addressText.getText(), jobText.getText(), nationalityText.getText(), domicileText.getText(),upDate());
        } catch (SQLException e) {
        }

    }

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    private void searchResident(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {

            Resident resident1 = ResidentDAO.searchResident(sshkText.getText());

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
            ResidentDAO.insertResident(sshkText.getText(), nameText.getText(), CccdText.getText(), phoneText.getText(), addressText.getText(), date(), jobText.getText(), nationalityText.getText(), domicileText.getText(),upDate() );
        } catch (SQLException e) {
            throw e;
        }
    }

    public Date date(){

//        Resident resident;
//        resident = new Resident();
//        dateText.setText(String.valueOf(resident.getDate()));
        String inputDate = dateText.getText();

        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date = formatter.parse(inputDate);
        } catch(Exception e) {
        }
        return Date.valueOf(inputDate);
    }

    public Date upDate(){
//        Resident resident;
//        resident = new Resident();
//        upDateText.setText(String.valueOf(resident.getDate()));
        String inputDate = upDateText.getText();

        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date = formatter.parse(inputDate);
        } catch(Exception e) {
        }
        return Date.valueOf(inputDate);
    }


}


