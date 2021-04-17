/**
 * @author Stepan Pijacek(223313)
 **/
package StudentsCRUD;

import CommonCRUD.Delete;
import Utilities.Subjects;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStudents {

    public void deleteStudent(int ID){
        Delete delete = new Delete();
        delete.deleteUserByID(ID, "students");
    }

    public void deleteScore(int id, Subjects subjects){
        if(!UserExists.testIfExistsByID(id,"students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String deleteScore = "DELETE FROM students_score WHERE studentsID = ? AND Subject = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(deleteScore)){
            prSmt.setInt(1, id);
            prSmt.setString(2, subjects.name());
            prSmt.executeUpdate();

            System.out.println("Score has been removed");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
