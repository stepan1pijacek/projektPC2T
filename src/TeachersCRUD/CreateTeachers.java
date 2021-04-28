package TeachersCRUD;

import Interfaces.Create;
import Models.Teachers;
import StudentsCRUD.CreateStudents;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class CreateTeachers extends Teachers implements Create {

    public CreateTeachers(String name, String surname, Date birth, int pay) {
        super(name, surname, birth, pay);
    }

    @Override
    public void insertNewUser() {
        Connection conn = DBConnection.getDbConnection();
        String insertQuery = "INSERT INTO teachers ( Name, Surname, Birth, Pay) VALUES ( ?, ?, ?, ?, ?)";

        try(PreparedStatement prSmt = conn.prepareStatement(insertQuery)){
            prSmt.setObject(1, this.getName());
            prSmt.setObject(2, this.getSurname());
            prSmt.setObject(3, this.getBirth());
            prSmt.setObject(4, this.getPay());

            prSmt.executeUpdate();
            System.out.println("New teacher has been added!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
