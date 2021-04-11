package StudentsCRUD;

import Interfaces.Studnts;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create extends Studnts {
    public void inseetNewStudent(Studnts studnts){
        if(studnts == null){
            throw new NullPointerException("All parameters must be set in order to insert new student");
        }

        Connection conn = DBConnection.getDbConnection();
        String insertQuery = "INSERT INTO students (ID, Name, Surname, Birth, Schoolership) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement prSmt = conn.prepareStatement(insertQuery)){
            prSmt.setObject(1, studnts.ID);
            prSmt.setObject(2, studnts.Name);
            prSmt.setObject(3, studnts.Surname);
            prSmt.setObject(4, studnts.Birth);
            prSmt.setObject(5, studnts.Schoolership);

            prSmt.executeUpdate();
            System.out.println("New student has been added!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
