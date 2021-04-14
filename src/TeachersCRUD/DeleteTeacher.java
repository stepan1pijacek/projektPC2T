/**
 * @author Stepan Pijacek(223313)
 * */
package TeachersCRUD;

import CommonOperations.DeleteOperation;
import Utilities.UserExists;
import dbConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTeacher {
    public void deleteTeacher(int ID){
        DeleteOperation deleteOperation = new DeleteOperation();
        deleteOperation.deleteUserByID(ID, "teachers");
    }
}
