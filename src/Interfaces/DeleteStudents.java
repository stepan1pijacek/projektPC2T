package Interfaces;

public interface DeleteStudents {
    void deleteStudentByID(int ID);
    void deleteStudentTeacherRelation(int studentID, int teacherID);
}
