package CommonCRUD;

import dbConnect.DBConnection;

import java.sql.*;

public class Read {

    public void readAllTheFunds(){
        int teachersPay = 0;
        int studentsPay = 0;
        Connection conn = DBConnection.getDbConnection();
        String selectQueryOne = "SELECT SUM(Pay) as teachersPay FROM teachers";
        String selectQueryTwo = "SELECT SUM(Scholarship) as studentsPay FROM students";

        try(PreparedStatement prSmt = conn.prepareStatement(selectQueryOne)){
            ResultSet rs = prSmt.executeQuery();
            teachersPay = rs.getInt("teachersPay");
        } catch (SQLException e){
            e.printStackTrace();
        }

        try(PreparedStatement prSmt = conn.prepareStatement(selectQueryTwo)){
            ResultSet rs = prSmt.executeQuery();
            studentsPay = rs.getInt("studentsPay");
        } catch (SQLException e){
            e.printStackTrace();
        }

        if(teachersPay > 0 || studentsPay > 0){
            System.out.println("Fund needed: { \n Scholarship = "+ studentsPay + "\n" +
                    "Pay = "+ teachersPay +"\n }");
        } else {
            System.out.println("Your tables seem to be empty.");
        }
    }
    public void readAllUsers(){
        Connection conn = DBConnection.getDbConnection();
        String selectQuery = "SELECT " +
                "ID, " +
                "Name, " +
                "Surname, " +
                "Birth, " +
                "FROM students" +
                "UNION " +
                "SELECT " +
                "ID, " +
                "Name, " +
                "Surname, " +
                "Birth " +
                "FROM teachers " +
                "ORDER BY Surname ASC";

        try(PreparedStatement prSmt = conn.prepareStatement(selectQuery)){
            ResultSet rs = prSmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                Date birth = rs.getDate("Birth");
                System.out.println("User: {" +
                        " ID = " + id +
                        " Name = " + name +
                        " Surname = " + surname +
                        " Birth = " + birth +
                        " }");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
