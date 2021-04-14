/**
 * @author Stepan Pijacek(223313)
 **/
package StudentsCRUD;

import Interfaces.UpdateStudent;
import Models.Students;
import Utilities.StudentAVG;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class UpdateStudents extends Students implements UpdateStudent {


    public UpdateStudents(String name, String surname, Date birth, int scholarship) {
        super(name, surname, birth, scholarship);
    }

    Students students;


    @Override
    public void giveScholarship(int id) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        if(StudentAVG.getStudentAVG(id) <= 1.6 ){
            Connection conn = DBConnection.getDbConnection();
            String updateQuery = "UPDATE students SET Scholarship = ? WHERE ID = ?";

            try(PreparedStatement prSmt = conn.prepareStatement(updateQuery)){
                prSmt.setInt(1, this.getScholarship());
                prSmt.setInt(2, id);

                prSmt.executeUpdate();

                System.out.println("Student has gained scholarship");
            } catch (SQLException e){
                e.printStackTrace();
            }
        } else{
            throw new IllegalArgumentException("Students AVG is not low enough");
        }
    }

    @Override
    public void takeScholarship(int id) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String updateQuery = "UPDATE students SET Scholarship = 0.0 WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(updateQuery)){
            prSmt.setInt(1,id);
            prSmt.executeUpdate();

            System.out.println("Student lost his scholarship");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePersonalInfo(int id) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String updatePersonalInfo = "UPDATE students SET Name = ?, Surname = ?, Birth = ? WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(updatePersonalInfo)){
            prSmt.setString(1 , this.getName());
            prSmt.setString(2, this.getSurname());
            prSmt.setDate(3, (java.sql.Date) this.getBirth());
            prSmt.setInt(4, id);

            prSmt.executeUpdate();

            System.out.println("Students personal info has been updated!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
