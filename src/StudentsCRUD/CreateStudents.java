/**
 * @author Stepan Pijacek(223313)
 **/
package StudentsCRUD;

import Models.Students;
import Utilities.Subjects;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class CreateStudents extends Students implements Interfaces.Create {

    public CreateStudents(String name, String surname, Date birth, int scholarship) {
        super(name, surname, birth, scholarship);
    }

    @Override
    public void insertNewUser(){
        Connection conn = DBConnection.getDbConnection();
        String insertQuery = "INSERT INTO students ( Name, Surname, Birth, Scholarship) VALUES ( ?, ?, ?, ?)";

        try(PreparedStatement prSmt = conn.prepareStatement(insertQuery)){
            prSmt.setObject(1, this.getName());
            prSmt.setObject(2, this.getSurname());
            prSmt.setObject(3, this.getBirth());
            prSmt.setObject(4, this.getScholarship());

            prSmt.executeUpdate();
            System.out.println("New student has been added!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void insertNewScore(int score, Subjects subjects, int id) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String insertScore = "INSERT INTO students_score (StudentsID, Grade, Subject) VALUES (?, ?, ?)";

        try(PreparedStatement prSmt = conn.prepareStatement(insertScore)){
            prSmt.setInt(1, id);
            prSmt.setInt(2, score);
            prSmt.setString(3,subjects.name());

            prSmt.executeUpdate();
            System.out.println("New score has been assigned to student");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
