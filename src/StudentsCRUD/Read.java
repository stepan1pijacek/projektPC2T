/**
* @author Stepan Pijacek(223313)
**/
package StudentsCRUD;

import Models.Students;
import Interfaces.ReadStudents;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Read implements ReadStudents {
    @Override
    public void readAllStudents() {
        Connection conn = DBConnection.getDbConnection();
        String getAllStudents = "SELECT * FROM students";

        try(PreparedStatement prSmt = conn.prepareStatement(getAllStudents)){
            ResultSet rs = prSmt.executeQuery();

            while (rs.next()){
               int id = rs.getInt("ID");
               String name = rs.getString("Name");
               String surname = rs.getString("Surname");
               Date birth = rs.getDate("Birth");
               int scholarship = rs.getInt("Scholarship");

               Students students = new Students( name, surname, birth, scholarship);
               students.setID(id);
               System.out.println(students);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void readOneStudent(int id) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String getOneStudent = "SELECT " +
                "students.Name, " +
                "students.Surname, " +
                "students.Birth, " +
                "students.Scholarship, " +
                "students_score.ID, " +
                "students_score.Grade, " +
                "students_score.Subject" +
                "FROM students " +
                "INNER JOIN students_score ON students.ID = students_score.StudentsID " +
                "WHERE Students.ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(getOneStudent)){
            prSmt.setInt(1, id);
            ResultSet rs = prSmt.executeQuery();

            while (rs.next()){
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                Date birth = rs.getDate("Birth");
                int scholarship = rs.getInt("Scholarship");
                int scoreID = rs.getInt("ID");
                int grade = rs.getInt("Grade");
                String subject = rs.getString("Subject");
                //Students students = new Students( name, surname, birth, scholarship);
                System.out.println(
                        "Student{" +
                        " name = " + name + "," +
                        " surname =" + surname + "," +
                        " Birth = " + birth + "," +
                        " Scholarship = " + scholarship + "," +
                                " Score id = " + scoreID + "," +
                                " Subject = " + subject + "," +
                                " Grade = " + grade + "," +
                        '}');
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void readStudentsTeachersRelations(int id) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        //Select with using of junction table
        String getRelationShips = "SELECT " +
                "students.Name, " +
                "students.Surname, " +
                "teachers.Name, " +
                "teachers.Surname " +
                "FROM students " +
                "JOIN `students_&_teachers` " +
                "ON students.ID = `students_&_teachers`.`StudentsID` " +
                "JOIN teachers " +
                "ON teachers.ID = `students_&_teachers`.`TeachersID` " +
                "WHERE students.ID = ? ";
        try(PreparedStatement prSmt = conn.prepareStatement(getRelationShips)){
            prSmt.setInt(1, id);
            ResultSet rs = prSmt.executeQuery();

            while(rs.next()){
                String studentName = rs.getString(1);
                String studentSurname = rs.getString(2);
                String teachersName = rs.getString(3);
                String teachersSurname = rs.getString(4);

                System.out.println(
                        "Relation{" +
                                " Students name = " + studentName + "," +
                                " students surname = " + studentSurname + "," +
                                " Teachers name = " + teachersName + "," +
                                " teachers surname = " + teachersSurname +
                                '}');
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getStudentsAVG(int id) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String getOneStudent = "SELECT " +
                "students.ID," +
                "students.Name, " +
                "students.Surname, " +
                "AVG(students_score.Grade) " +
                "FROM students " +
                "JOIN students_score ON students.ID = students_score.StudentsID " +
                "WHERE Students.ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(getOneStudent)){
            prSmt.setInt(1, id);
            ResultSet rs = prSmt.executeQuery();

            while(rs.next()){
                int ID = rs.getInt(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                double average = rs.getDouble(4);

                System.out.println(
                        "Average{ "+
                                " ID = " + ID + " ," +
                                " Name = " + name + " ," +
                                " Surname = " + surname + " ," +
                                " Average = " + average +
                                " }"
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
