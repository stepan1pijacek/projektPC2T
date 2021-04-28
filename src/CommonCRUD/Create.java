package CommonCRUD;

import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create {
    public void assignTeacherToStudent(int studentID, int teacherID) {
        if(!UserExists.testIfExistsByID(studentID, "students") && !UserExists.testIfExistsByID(teacherID, "teachers")){
            throw new IllegalArgumentException("Please, double check provided ID's for both student and teacher");
        }

        Connection conn = DBConnection.getDbConnection();
        String insertRelationship = "INSERT INTO students_&_teachers (StudentsID, TeachersID) VALUES (?, ?)";

        System.out.println("New relationship has been created student x teacher");
        try(PreparedStatement prSmt = conn.prepareStatement(insertRelationship)){
            prSmt.setInt(1, studentID);
            prSmt.setInt(2, teacherID);

            prSmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
