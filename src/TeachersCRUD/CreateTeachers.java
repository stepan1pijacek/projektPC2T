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

    public CreateTeachers(String name, String surname, Date birth, int bonus, int pay) {
        super(name, surname, birth, bonus, pay);
    }

    @Override
    public void insertNewUser() {
        Connection conn = DBConnection.getDbConnection();
        String insertQuery = "INSERT INTO teachers ( Name, Surname, Birth, Bonus, Pay) VALUES ( ?, ?, ?, ?, ?)";

        try(PreparedStatement prSmt = conn.prepareStatement(insertQuery)){
            prSmt.setObject(1, this.getName());
            prSmt.setObject(2, this.getSurname());
            prSmt.setObject(3, this.getBirth());
            prSmt.setObject(4, this.getBonus());
            prSmt.setObject(5, this.getPay());

            prSmt.executeUpdate();
            System.out.println("New teacher has been added!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void assignTeacherToStudent(int studentID, int teacherID) {
        CreateStudents createStudents = null;
        createStudents.assignTeacherToStudent(studentID, teacherID);
        System.out.println("New teacher student relation ahs been added");
    }
}
