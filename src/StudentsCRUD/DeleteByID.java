package StudentsCRUD;

import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteByID implements UserExists {

    public void deleteStudentByID(int ID){
        if(testIfExistsByID(ID) == false){
            throw new IllegalArgumentException("There is no student with provided ID");
        }
        Connection conn = DBConnection.getDbConnection();
        String deleteStudent = "SELECT * FROM students WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(deleteStudent)){
            prSmt.setInt(1, ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean testIfExistsByID(int ID) {
        Connection conn = DBConnection.getDbConnection();
        String testUserExistence = "SELECT * FROM students WHERE ID = ?";

        try (PreparedStatement prStmt = conn.prepareStatement(testUserExistence);) {
            prStmt.setInt(1, ID);
            ResultSet rs = prStmt.executeQuery();
            if (rs.next())
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
