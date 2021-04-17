/**
 * @author Stepan Pijacek(223313)
 * */
package TeachersCRUD;

import CommonCRUD.Delete;

public class DeleteTeacher {
    public void deleteTeacher(int ID){
        Delete delete = new Delete();
        delete.deleteUserByID(ID, "teachers");
    }
}
