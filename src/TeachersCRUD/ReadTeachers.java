package TeachersCRUD;

import Interfaces.Read;
import Models.Teachers;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ReadTeachers implements Read {
    @Override
    public void readAllUser() {
        Connection conn = DBConnection.getDbConnection();
        String getAllTeachers = "SELECT * FROM teachers";

        try(PreparedStatement prSmt = conn.prepareStatement(getAllTeachers)){
            ResultSet rs = prSmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                Date birth = rs.getDate("Birth");
                int pay = rs.getInt("pay");
                int bonus = rs.getInt("Bonus");

                Teachers teachers = new Teachers(name, surname, birth,pay);
                teachers.setID(id);
                teachers.setBonus(bonus);
                System.out.println(teachers);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void readOneUser(int id) {
        Connection conn = DBConnection.getDbConnection();
        String getAllTeachers = "SELECT * FROM teachers where ID = id";

        try(PreparedStatement prSmt = conn.prepareStatement(getAllTeachers)){
            ResultSet rs = prSmt.executeQuery();

            while (rs.next()){
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                Date birth = rs.getDate("Birth");
                int pay = rs.getInt("pay");
                int bonus = rs.getInt("Bonus");

                Teachers teachers = new Teachers(name, surname, birth,pay);
                teachers.setID(id);
                teachers.setBonus(bonus);
                System.out.println(teachers);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void readTeachersStudents(int id){
        Connection conn = DBConnection.getDbConnection();
        String getTeachersStudentsOrderByAVG= "SELECT " +
                "students.ID as studentsID, " +
                "students.Name as studentsName, " +
                "AVG(students_score.Grade) as Average, " +
                "teachers.ID as teachersID, " +
                "teachers.Name as teachersName, " +
                "teachers.Surname as teachersSurname " +
                "FROM teachers " +
                "JOIN `students_&_teachers` " +
                "ON teachers.ID = `students_&_teachers`.`TeachersID` " +
                "JOIN students " +
                "ON students.ID = `students_&_teachers`.`ID` " +
                "JOIN students_score " +
                "ON students_score.StudentsID = `students_&_teachers`.ID " +
                "WHERE teachers.ID = ? " +
                "GROUP BY students_score.StudentsID " +
                "HAVING students_score.StudentsID = students.ID " +
                "ORDER BY Average ASC";
        try(PreparedStatement prSmt = conn.prepareStatement(getTeachersStudentsOrderByAVG)){
            prSmt.setInt(1,id);
            ResultSet rs = prSmt.executeQuery();

            while(rs.next()){
                int studentID = rs.getInt("studentsID");
                String studentName = rs.getString("studentsName");
                String studentSurname = rs.getString("studentsSurname");
                double studentsAVG = rs.getDouble("Average");
                int teacherID = rs.getInt("teachersID");
                String teacherName = rs.getString("teachersName");
                String teacherSurname = rs.getString("teachersSurname");

                System.out.println("Result: { students ID = "+ studentID +" students Name = "+ studentName +" " +
                        "students Surname = "+ studentSurname +" students Average = "+ studentsAVG +" " +
                        "teachers ID = "+ teacherID +" teachers Name = "+ teacherName +" " +
                        "teachers Surname = "+ teacherSurname +" }");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
