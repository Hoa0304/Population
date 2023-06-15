package com.ttchoa22ite.population.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import com.ttchoa22ite.population.models.Resident;
import com.ttchoa22ite.population.utils.ConnectionDB;
public class ResidentDAO {
    static Connection con = null;
    static PreparedStatement pstmt = null;
    static Statement stm ;
    ResultSet resultSet = null;
     public ResidentDAO(){

    con = ConnectionDB.DAO();
         try {
             stm = con.createStatement();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }
    public static Resident searchResident (String residentName) throws SQLException,ClassNotFoundException{
        String selectStmt = "SELECT * FROM resident WHERE name="+residentName;

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

        }
        return r;
    }



    public static ObservableList<Resident> getAllresident() throws SQLException{
         Connection conn = ConnectionDB.DAO();
         PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Resident> residentsList = FXCollections.observableArrayList();
        try {
            stmt = conn.prepareStatement("SELECT * from resident");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Resident resident = new Resident();
                resident.setId(rs.getInt("id"));
                resident.setSshk(rs.getString("sshk"));
                resident.setCccd(rs.getString("cccd"));
                resident.setName(rs.getString("name"));
                resident.setNOphone(rs.getString("NOphone"));
                resident.setAddress(rs.getString("address"));
                resident.setJob(rs.getString("job"));
                resident.setSex(rs.getString("sex"));
                resident.setBirt(rs.getString("birt"));
                residentsList.add(resident);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(rs!=null) rs.close();
            if(stmt!=null)  stmt.close();
            if(conn !=null) conn.close();
        }
        return residentsList;
    }
    public static void updateResident (String sshk, String name, String sex,String cccd,String NOphone,String address,String job ) throws SQLException, ClassNotFoundException {
       Connection conn = ConnectionDB.DAO();
       PreparedStatement stmt = null;
        String updateStmt =

                "UPDATE resident SET name = '"+name+"', sex = '"+sex+"', cccd = '"+cccd+"', NOphone = '"+NOphone+"', address = '"+address+"', job = '"+job+"' WHERE sshk = '"+sshk+"'";
        try {
            stmt = conn.prepareStatement(updateStmt);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }


    public static void deleteResident(String sshk)throws SQLException,ClassNotFoundException{
         Connection conn = ConnectionDB.DAO();
         PreparedStatement stmt = null;
        String updateStmt=

                        "   DELETE FROM resident\n" +
                        "         WHERE sshk ='"+ sshk+"'";

        try {
            stmt = conn.prepareStatement(updateStmt);
            stmt.executeUpdate();
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
public static int getId() throws SQLException {
         Connection conn = ConnectionDB.DAO();
    String selectStmt = "SELECT MAX(id) FROM resident";
    try {
        pstmt = conn.prepareStatement(selectStmt);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("SQL select operation has been failed: " + e);
        throw e;
    }
    return 0;
}
     private static int id;



    public static void isertResident(String name, String cccd, String sshk, String NOphone, String address, String job, String sex, String birt) throws SQLException, ClassNotFoundException {
       id = getId()+1;
        Connection conn = ConnectionDB.DAO();
        String insertStmt = "INSERT INTO resident (name, sex, cccd, NOphone, address, job, id, sshk, birt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
             pstmt = conn.prepareStatement(insertStmt);
            pstmt.setString(1, name);
            pstmt.setString(2, sex);
            pstmt.setString(3, cccd);
            pstmt.setString(4, NOphone);
            pstmt.setString(5, address);
            pstmt.setString(6, job);
            pstmt.setInt(7,id );
            pstmt.setString(8, sshk);
            pstmt.setString(9, birt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
        //Execute DELETE operation
//        try {
//
//            System.out.println(updateStmt);

//        } catch (SQLException e) {
//            System.out.print("Error occurred while DELETE Operation: " + e);
//            throw e;
//        }
    }



}
