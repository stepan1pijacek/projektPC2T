/**
 * @author Stepan Pijacek(223313)
 **/
package StudentsCRUD;

import Interfaces.Update;
import Models.Students;
import Utilities.StudentAVG;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class UpdateStudents implements Update {


    Students students;


    public boolean giveScholarship(int id, int scholarship) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        if(StudentAVG.getStudentAVG(id) <= 1.6 ){
            Connection conn = DBConnection.getDbConnection();
            String updateQuery = "UPDATE students SET Scholarship = ? WHERE ID = ?";

            try(PreparedStatement prSmt = conn.prepareStatement(updateQuery)){
                prSmt.setInt(1, scholarship);
                prSmt.setInt(2, id);

                prSmt.executeUpdate();

                System.out.println("Student has gained scholarship");
                return true;
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        } else{
            throw new IllegalArgumentException("Students AVG is not low enough");
        }
    }

    public boolean takeScholarship(int id) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String updateQuery = "UPDATE students SET Scholarship = 0.0 WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(updateQuery)){
            prSmt.setInt(1,id);
            prSmt.executeUpdate();

            System.out.println("Student lost his scholarship");
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updatePersonalInfo(int id, String Name, String Surname, Date Birth) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String updatePersonalInfo = "UPDATE students SET Name = ?, Surname = ?, Birth = ? WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(updatePersonalInfo)){
            prSmt.setString(1 , Name);
            prSmt.setString(2, Surname);
            prSmt.setDate(3, (java.sql.Date) Birth);
            prSmt.setInt(4, id);

            prSmt.executeUpdate();

            System.out.println("Students personal info has been updated!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
