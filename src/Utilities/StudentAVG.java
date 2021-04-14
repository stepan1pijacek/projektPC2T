/**
 * @author Stepan Pijacek (223313)
 * */
package Utilities;

import Models.StudentTeacherGrade;
import dbConnect.DBConnection;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentAVG {

    public static double getStudentAVG(int id){
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String getAVG = "SELECT " +
                        "AVG(Grade) " +
                        "FROM students_score " +
                        "WHERE StudentsID = ? ";

        try(PreparedStatement prSmt = conn.prepareStatement(getAVG)){
            prSmt.setInt(1, id);
            ResultSet rs = prSmt.executeQuery();

            while (rs.next()){
                return rs.getInt("Grade");
            }

        } catch (SQLException e){
            e.printStackTrace();
            return 0.0;
        }

        return 0.0;
    }

    public static @Nullable
    List<ResultSet> getGradesForEveryStudentThatTeacherHas(int teachersID){
        List<ResultSet> studentTeacherGradeList = null;
        Connection conn = DBConnection.getDbConnection();
        String getAVG = "SELECT " +
                "students_score.Grade, " +
                "`students_&_teachers`.`TeachersID`, " +
                "students_score.StudentsID, " +
                "students.Name " +
                "FROM students_score " +
                "JOIN students " +
                "ON students.ID = students_score.StudentsID " +
                "JOIN `students_&_teachers` " +
                "ON students_score.StudentsID = `students_&_teachers`.`StudentsID`\n" +
                "WHERE `students_&_teachers`.`TeachersID` = ?";
        try(PreparedStatement prSmt = conn.prepareStatement(getAVG)){
            prSmt.setInt(1, teachersID);
            ResultSet rs = prSmt.executeQuery();
            while (rs.next()){
                studentTeacherGradeList.add(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
