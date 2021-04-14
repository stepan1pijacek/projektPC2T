/**
 * @author Stepan Pijacek(223313)
 **/
package Interfaces;

import Utilities.Subjects;

public interface Create {
    void insertNewUser();
    void assignTeacherToStudent(int studentID, int teacherID);
}
