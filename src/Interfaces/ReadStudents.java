/**
 * @author Stepan Pijacek(223313)
 **/
package Interfaces;

public interface ReadStudents {
    void readAllStudents();
    void readOneStudent(int id);
    void readStudentsTeachersRelations(int id);
    void getStudentsAVG(int id);
}
