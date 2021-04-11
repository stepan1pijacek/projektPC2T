package StudentsCRUD;

import Interfaces.Students;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Update extends Students implements UserExists {

    public Update(String name, String surname, Date birth, boolean scholarship) {
        super(name, surname, birth, scholarship);
    }

    public void giveOrTakeStudentScholarship(int ID){
        if(testIfExistsByID(ID) == false){
            throw new IllegalArgumentException("There is no student with provided ID");
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
