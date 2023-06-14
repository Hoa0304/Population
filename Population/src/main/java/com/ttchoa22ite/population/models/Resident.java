package com.ttchoa22ite.population.models;

import javafx.beans.property.*;

import java.sql.Date;

public class Resident {

    private IntegerProperty id;
    private StringProperty sshk;
    private StringProperty cccd;
    private StringProperty name;
    private StringProperty NOphone;
    private StringProperty address;
    private StringProperty job;
    private StringProperty nationality;
    private StringProperty domicile;
    private SimpleObjectProperty<Date> date;
    private SimpleObjectProperty<Date> upDate;

    public Resident() {
        this.id = new SimpleIntegerProperty();
        this.sshk = new SimpleStringProperty();
        this.cccd = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.NOphone = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.job = new SimpleStringProperty();
        this.nationality = new SimpleStringProperty();
        this.domicile = new SimpleStringProperty();
        this.upDate =  new SimpleObjectProperty<>();
        this.date =  new SimpleObjectProperty<>();
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getSshk() {
        return sshk.get();
    }

    public StringProperty sshkProperty() {
        return sshk;
    }

    public void setSshk(String sshk) {
        this.sshk.set(sshk);
    }

    public String getCccd() {
        return cccd.get();
    }

    public StringProperty cccdProperty() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd.set(cccd);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getNOphone() {
        return NOphone.get();
    }

    public StringProperty NOphoneProperty() {
        return NOphone;
    }

    public void setNOphone(String NOphone) {
        this.NOphone.set(NOphone);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getJob() {
        return job.get();
    }

    public StringProperty jobProperty() {
        return job;
    }

    public void setJob(String job) {
        this.job.set(job);
    }

    public String getNationality() {
        return nationality.get();
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public String getDomicile() {
        return domicile.get();
    }

    public StringProperty domicileProperty() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile.set(domicile);
    }

    public Date getUpDate() {
        return upDate.get();
    }

    public SimpleObjectProperty<Date> upDateProperty() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate.set(upDate);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }
}