/**
 * @author Stepan Pijacek(223313)
 * */
package TeachersCRUD;

import CommonCRUD.Delete;

public class DeleteTeacher {
    public boolean deleteTeacher(int ID){
        try {
            Delete delete = new Delete();
            delete.deleteUserByID(ID, "teachers");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
