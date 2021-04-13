/**
 * @author Stepan Pijacek(223313)
 **/
package Interfaces;

public interface CreateStudents {
    void insertNewStudent();
    void insertNewScore(int score, String subject, int studentID);
    void assignTeacherToStudent(int studentID, int teacherID);
}
