/**
 * @author Stepan Pijacek(223313)
 **/
package StudentsCRUD;

import Models.Students;
import Interfaces.CreateStudents;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Create extends Students implements CreateStudents{
    private String Name;
    private String Surname;
    private Date Birth;
    private int Scholarship;
    public Create(String name, String surname, Date birth, int scholarship) {
        super(name, surname, birth, scholarship);

        Name = name;
        Surname = surname;
        Birth = birth;
        Scholarship = scholarship;
    }

    @Override
    public void insertNewStudent(){
        Connection conn = DBConnection.getDbConnection();
        String insertQuery = "INSERT INTO students ( Name, Surname, Birth, Scholarship) VALUES ( ?, ?, ?, ?)";

        try(PreparedStatement prSmt = conn.prepareStatement(insertQuery)){
            prSmt.setObject(1, Name);
            prSmt.setObject(2, Surname);
            prSmt.setObject(3, Birth);
            prSmt.setObject(4, Scholarship);

            prSmt.executeUpdate();
            System.out.println("New student has been added!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertNewScore(int score, String subject, int id) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String insertScore = "INSERT INTO students_score (StudentsID, Grade, Subject) VALUES (?, ?, ?)";

        try(PreparedStatement prSmt = conn.prepareStatement(insertScore)){
            prSmt.setInt(1, id);
            prSmt.setInt(2, score);
            prSmt.setString(3,subject);

            prSmt.executeUpdate();
            System.out.println("New score has been assigned to student");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void assignTeacherToStudent(int studentID, int teacherID) {
        if(!UserExists.testIfExistsByID(studentID, "students") && !UserExists.testIfExistsByID(teacherID, "teachers")){
            throw new IllegalArgumentException("Please, double check provided ID's for both student and teacher");
        }

        Connection conn = DBConnection.getDbConnection();
        String insertRelationship = "INSERT INTO students_&_teachers (StudentsID, TeachersID) VALUES (?, ?)";

        try(PreparedStatement prSmt = conn.prepareStatement(insertRelationship)){
            prSmt.setInt(1, studentID);
            prSmt.setInt(2, teacherID);

            prSmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
