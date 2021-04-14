package Interfaces;

public interface Delete {
    void deleteUserByID(int ID, String table);
    void deleteStudentTeacherRelation(int studentID, int teacherID);
}
