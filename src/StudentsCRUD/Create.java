package StudentsCRUD;

import Interfaces.Students;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Create extends Students {
    public Create(String name, String surname, Date birth, Boolean schoolership) {
        super(name, surname, birth, schoolership);
    }

    public void insertNewStudent(Students students){
        if(students == null){
            throw new NullPointerException("All parameters must be set in order to insert new student");
        }

        Connection conn = DBConnection.getDbConnection();
        String insertQuery = "INSERT INTO students (ID, Name, Surname, Birth, Schoolership) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement prSmt = conn.prepareStatement(insertQuery)){
            prSmt.setObject(1, students.ID);
            prSmt.setObject(2, students.getName());
            prSmt.setObject(3, students.getSurname());
            prSmt.setObject(4, students.getBirth());
            prSmt.setObject(5, students.getScholarship());

            prSmt.executeUpdate();
            System.out.println("New student has been added!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
