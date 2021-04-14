/**
 * @author Stepan Pijacek (223313)
 * */
package TeachersCRUD;

import Interfaces.UpdateTeachers;
import Models.Teachers;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTeacher implements UpdateTeachers {

    private static final int BONUS = 1500;

    @Override
    public void giveBonus(int id) {
        if(!UserExists.testIfExistsByID(id, "teachers")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        int bonusGiven = 0;
        int studentCount = 0;
        Connection conn = DBConnection.getDbConnection();
        String selectStudents = "SELECT " +
                "students.ID " +
                "FROM students " +
                "JOIN `students_&_teachers` " +
                "ON students.ID = `students_&_teachers`.`StudentsID` " +
                "WHERE `students_&_teachers`.`TeachersID` = ? AND students.Scholarship > 0";

        String giveBonus = "UPDATE teachers SET Bonus = ? WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(selectStudents)){
            prSmt.setInt(1, id);
            ResultSet rs = prSmt.executeQuery();

            while (rs.next()){
                studentCount += 1;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        bonusGiven = BONUS * studentCount;

        try(PreparedStatement prSmt = conn.prepareStatement(giveBonus)){
            prSmt.setInt(1, bonusGiven);
            prSmt.setInt(2,id);
            prSmt.executeUpdate();

            System.out.println("Bonus has been given");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void takeBonus(int id) {
        if(!UserExists.testIfExistsByID(id, "teachers")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String takeBonus = "UPDATE teachers SET Bonus = 0 WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(takeBonus)){
            prSmt.setInt(1 , id);
            prSmt.executeUpdate();

            System.out.println("Bonus has been taken");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePersonalInfo(int id) {

    }

    @Override
    public void increaseOrDecreasePay(int id) {

    }
}
