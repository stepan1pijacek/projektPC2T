/**
 * @author Stepan Pijacek(223313)
 **/
package StudentsCRUD;

import Models.Students;
import Interfaces.UpdateStudent;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Update extends Students implements UpdateStudent {
    private String Name;
    private String Surname;
    private Date Birth;
    private int Scholarship;
    public Update(String name, String surname, Date birth, int scholarship) {
        super(name, surname, birth, scholarship);

        Name = name;
        Surname = surname;
        Birth = birth;
        Scholarship = scholarship;
    }

    Students students;


    @Override
    public void giveOrTakeScholarship(int id, int scholarship) {
        if(!UserExists.testIfExistsByID(id, "students")){
            throw new IllegalArgumentException("There is no student with provided ID");
        }

        Connection conn = DBConnection.getDbConnection();
        String updateQuery = "UPDATE students SET Schoolership = ? WHERE ID = ?";

        try(PreparedStatement prSmt = conn.prepareStatement(updateQuery)){
            prSmt.setInt(1, scholarship);
            prSmt.setInt(2, id);
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
            prSmt.setString(1 , Name);
            prSmt.setString(2, Surname);
            prSmt.setDate(3, (java.sql.Date) Birth);
            prSmt.setInt(4, id);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
