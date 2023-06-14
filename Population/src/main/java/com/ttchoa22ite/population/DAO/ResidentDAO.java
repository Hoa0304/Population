package com.ttchoa22ite.population.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import com.ttchoa22ite.population.models.Resident;
import com.ttchoa22ite.population.utils.ConnectionDB;
public class ResidentDAO {

    public static Resident searchResident (String residentSshk) throws SQLException,ClassNotFoundException{
        String selectStmt = "SELECT * FROM resident WHERE sshk="+residentSshk;
        //Execute SELECT statement
        try {
            ResultSet rs = ConnectionDB.dbExecuteQuery(selectStmt);

            return getResidentFromResultSet(rs);
        }catch (SQLException e){
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    private static Resident getResidentFromResultSet(ResultSet resultSet)throws SQLException{
        Resident r = null;
        if (resultSet.next()){
            r = new Resident();
            r.setId(resultSet.getInt("id"));
            r.setSshk(resultSet.getString("sshk"));
            r.setCccd(resultSet.getString("cccd"));
            r.setName(resultSet.getString("name"));
            r.setNOphone(resultSet.getString("NOphone"));
            r.setAddress(resultSet.getString("address"));
            r.setJob(resultSet.getString("job"));
            r.setNationality(resultSet.getString("nationality"));
            r.setDomicile(resultSet.getString("domicile"));
            r.setUpDate(resultSet.getDate("upDate"));
            r.setUpDate(resultSet.getDate("date"));
        }
        return r;
    }



    private static ObservableList<Resident> getResidentsList(ResultSet rs) throws SQLException,ClassNotFoundException{
        ObservableList<Resident> residentsList = FXCollections.observableArrayList();
        while (rs.next()) {
            Resident resident = new Resident();
            resident.setId(rs.getInt("id"));
            resident.setSshk(rs.getString("sshk"));
            resident.setCccd(rs.getString("cccd"));
            resident.setName(rs.getString("name"));
            resident.setNOphone(rs.getString("NOphone"));
            resident.setAddress(rs.getString("address"));
            resident.setJob(rs.getString("job"));
            resident.setNationality(rs.getString("nationality"));
            resident.setDomicile(rs.getString("domicile"));
            resident.setUpDate(rs.getDate("upDate"));
            resident.setUpDate(rs.getDate("date"));
            residentsList.add(resident);
        }

        return residentsList;
    }
    public static void updateResident (String sshk, String name,Date date, String sex,String cccd,String NOphone,String address,String job,String nationalily,String domicile,Date upDate ) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE resident"+

                        "      SET name = '" + name + "'," +
                        "      date = '" + date + "'," +
                        "      sex = '" + sex + "'," +
                        "      cccd  = '" + cccd + "'," +
                        "      NOphone = '" + NOphone + "'," +
                        "      address = '" + address + "'," +
                        "      job = '" + job + "'," +
                        "      nationalily = '" + nationalily + "'," +
                        "      domicile = '" + domicile + "'," +
                        "      update = '" + upDate + "'\n" +
                        "    WHERE sshk = " + sshk + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
        try {
            ConnectionDB.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }


    public static void deleteResident(String sshk)throws SQLException,ClassNotFoundException{
        String updateStmt=
        "BEGIN\n" +
                        "   DELETE FROM resident\n" +
                        "         WHERE sshk ="+ sshk+";\n" +
                        "   COMMIT;\n" +
                        "END;";
        try {
            ConnectionDB.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Xảy ra lỗi trong khi thao tác XÓA: " + e);
            throw e;
        }
    }

//    public static void deleteResidentWithName (String residentName) throws SQLException, ClassNotFoundException {
//        String updateStmt =
//                "BEGIN\n" +
//                        "   DELETE FROM resident\n" +
//                        "         WHERE name ="+ residentName+";\n" +
//                        "   COMMIT;\n" +
//                        "END;";
//        try {
//            ConnectionDB.dbExecuteUpdate(updateStmt);
//        } catch (SQLException e) {
//            System.out.print("Xảy ra lỗi trong khi thao tác XÓA: " + e);
//            throw e;
//        }
//    }
//
//    public static void deleteResidentWithDate (Date residentDate) throws SQLException, ClassNotFoundException {
//        String updateStmt =
//                "BEGIN\n" +
//                        "   DELETE FROM resident\n" +
//                        "         WHERE name ="+ residentDate+";\n" +
//                        "   COMMIT;\n" +
//                        "END;";
//        try {
//            ConnectionDB.dbExecuteUpdate(updateStmt);
//        } catch (SQLException e) {
//            System.out.print("Xảy ra lỗi trong khi thao tác XÓA: " + e);
//            throw e;
//        }
//    }

//    public static void deleteResidentWithnOphone (Date residentnOphone) throws SQLException, ClassNotFoundException {
//        String updateStmt =
//                "BEGIN\n" +
//                        "   DELETE FROM resident\n" +
//                        "         WHERE name ="+ residentnOphone+";\n" +
//                        "   COMMIT;\n" +
//                        "END;";
//        try {
//            ConnectionDB.dbExecuteUpdate(updateStmt);
//        } catch (SQLException e) {
//            System.out.print("Xảy ra lỗi trong khi thao tác XÓA: " + e);
//            throw e;
//        }
//    }

    public static void insertResident (String name, String cccd, String sshk, String NOphone, String address,Date date,String job,String nationalily,String domicile,Date upDate) throws SQLException, ClassNotFoundException {

        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO resident\n" +
                        "(id, name, cccd, sshk, NOphone, address,date,update,job,domicile,nationality,job)\n" +
                        "VALUES\n" +
                        "(sequence_resident.nextval, '"+name+"', '"+cccd+"','"+sshk+"','"+NOphone+"','"+address+"','"+domicile+"', '"+date+"','"+job+"','"+nationalily+"','"+upDate+"', SYSDATE, 'IT_PROG');\n" +
                        "END;";
        //Execute DELETE operation
        try {
            ConnectionDB.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }



}
