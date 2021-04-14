/**
 * @author Stepan Pijacek(223313)
 **/
package StudentsCRUD;

import Utilities.Subjects;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStudents implements Interfaces.Delete {

    @Override
    public void deleteUserByID(int ID){
        if(!UserExists.testIfExistsByID(ID, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }
        Connection conn = DBConnection.getDbConnection();
        String deleteStudent = "DELETE * FROM students WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(deleteStudent)){
            prSmt.setInt(1, ID);
            prSmt.executeUpdate();

            System.out.println("Student has been deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudentTeacherRelation(int studentID, int teachersID) {
        if(!UserExists.testIfExistsByID(studentID, "students") || !UserExists.testIfExistsByID(teachersID, "teachers")){
            throw new IllegalArgumentException("Double check provided ID, one of them might be faulty");
        }

        Connection conn = DBConnection.getDbConnection();
        String deleteRelationship = "DELETE FROM `students_&_teachers` WHERE StudentsID = ? AND TeachersID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(deleteRelationship)){
            prSmt.setInt(1, studentID);
            prSmt.setInt(2, teachersID);
            prSmt.executeUpdate();

            System.out.println("Student and teacher relation has been deleted");
        } catch(SQLException e){
            e.printStackTrace();
        }
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
