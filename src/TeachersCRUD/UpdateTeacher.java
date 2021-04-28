/**
  @author Stepan Pijacek (223313)
 * */
package TeachersCRUD;

import Interfaces.Update;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UpdateTeacher implements Update {

    //Constant of bonus that cannot be changed by user input or any other class
    private static final int BONUS = 1500;

    /**
     * Operation to give bonus to the teacher based on number of students with Scholarship
     *
     * It consists of one SELECT and one UPDATE, where SELECT query selects from students executes JOIN on the junction table with students and teachers
     * and final uses where clause with two parameters. Based on this it returns table with only students ID which are then counted using while loop.
     * Counted students are then used as multiplier for BONUS const. If there is no student with scholarship for the given teacher it simply writes zero to
     * the bonus field in the table
     * */
    public boolean giveBonus(int id) {
        if(!UserExists.testIfExistsByID(id, "teachers")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        int bonusGiven;
        int studentCount;
        studentCount = 0;
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
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }

        try(PreparedStatement prSmt = conn.prepareStatement(giveBonus)){
            bonusGiven = BONUS * studentCount;
            prSmt.setInt(1, bonusGiven);
            prSmt.setInt(2,id);
            prSmt.executeUpdate();

            System.out.println("Bonus has been given");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Simple UPDATE that sets zero to the bonus field. In more advanced system it could be automated to take or give bonus once per given time frame.
     * */
    public boolean takeBonus(int id) {
        if(!UserExists.testIfExistsByID(id, "teachers")){
            throw new IllegalArgumentException("There is no teacher with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String takeBonus = "UPDATE teachers SET Bonus = 0 WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(takeBonus)){
            prSmt.setInt(1 , id);
            prSmt.executeUpdate();

            System.out.println("Bonus has been taken");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updatePersonalInfo(int id, String Name, String Surname, Date Birth) {
        if(!UserExists.testIfExistsByID(id, "teachers")){
            throw new IllegalArgumentException("There is no teacher with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String updateTeacher = "UPDATE teachers SET Name = ?, Surname = ?, Birth = ? WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(updateTeacher)){
            prSmt.setString(1, Name);
            prSmt.setString(2, Surname);
            prSmt.setDate(3, (java.sql.Date) Birth);
            prSmt.setInt(4, id);

            prSmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean increasePay(int id, int pay) {

        if(!UserExists.testIfExistsByID(id, "teachers")){
            throw new IllegalArgumentException("There is no teacher with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String selectPay = "SELECT Pay FROM teachers WHERE ID = ?";
        String updatePay = "UPDATE teachers SET Pay = ? WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(selectPay)){
            prSmt.setInt(1, id);
            ResultSet rs = prSmt.executeQuery();

            pay += rs.getInt("Pay");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }

        try(PreparedStatement prSmt = conn.prepareStatement(updatePay)){
            prSmt.setInt(1, pay);
            prSmt.setInt(2, id);
            prSmt.executeUpdate();

            System.out.println("Teachers pay has been updated");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
