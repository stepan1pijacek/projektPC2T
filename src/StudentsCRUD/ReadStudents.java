/**
* @author Stepan Pijacek(223313)
**/
package StudentsCRUD;

import Utilities.UserExists;
import dbConnect.DBConnection;
import Models.Students;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ReadStudents implements Interfaces.Read {

    @Override
    public boolean readAllUser() {
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
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean readOneUser(int id) {
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
                "students_score.Subject " +
                "FROM students  " +
                "JOIN students_score ON  students_score.StudentsID = students.ID " +
                "WHERE students.ID = ? ";

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
                        " Scholarship = " + scholarship + ",\n" +
                                " Subject: " + subject + " { " +
                                " Score id = " + scoreID + "," +
                                " Grade = " + grade + " }\n" +
                        '}');
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean readStudentsTeachersRelations(int id) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        //Select with using of junction table
        String getRelationShips = "SELECT " +
                "students.ID, " +
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
                int ID = rs.getInt(1);
                String studentName = rs.getString(2);
                String studentSurname = rs.getString(3);
                String teachersName = rs.getString(4);
                String teachersSurname = rs.getString(5);

                System.out.println(
                        "Relation{" +
                                " students ID = " + ID + "," +
                                " Students name = " + studentName + "," +
                                " students surname = " + studentSurname + ", \n" +
                                " Teacher{"  +
                                "           Teachers name = " + teachersName + "," +
                                "           Teachers surname = " + teachersSurname +
                                "        } \n" +
                                '}');
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean getStudentsAVG(int id) {
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
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
