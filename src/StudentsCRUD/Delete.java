/**
 * @author Stepan Pijacek(223313)
 **/
package StudentsCRUD;

import Interfaces.DeleteStudents;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete implements DeleteStudents {

    @Override
    public void deleteStudentByID(int ID){
        if(!UserExists.testIfExistsByID(ID, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }
        Connection conn = DBConnection.getDbConnection();
        String deleteStudent = "DELETE * FROM students WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(deleteStudent)){
            prSmt.setInt(1, ID);
            prSmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudentTeacherRelation(int studentID, int teachersID) {
        if(!UserExists.testIfExistsByID(studentID, "students") || !UserExists.testIfExistsByID(teachersID, "teachers")){
            throw new IllegalArgumentException(("Double check provided ID, one of them might be faulty"));
        }

        Connection conn = DBConnection.getDbConnection();
        String deleteRelationship = "DELETE FROM `students_&_teachers` WHERE StudentsID = ? AND TeachersID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(deleteRelationship)){
            prSmt.setInt(1, studentID);
            prSmt.setInt(2, teachersID);
            prSmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
