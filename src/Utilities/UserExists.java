/**
 * @author Stepan Pijacek(223313)
 **/
package Utilities;

import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExists {
    public static boolean testIfExistsByID(int ID, String table){
        Connection conn = DBConnection.getDbConnection();
        String testUserExistence = "SELECT * FROM "+ table +" WHERE ID = ?";

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
