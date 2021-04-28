/**
 * @author: Stepan Pijacek
 * @description: Main test suit for Read operations
 * */
package TestSuit;



import CommonCRUD.Read;
import Models.Students;
import Models.Teachers;
import StudentsCRUD.ReadStudents;
import TeachersCRUD.ReadTeachers;

import Utilities.UserExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReadTest {

    private ReadStudents readStudents ;
    private ReadTeachers readTeachers ;
    private Teachers teachers;

    @BeforeEach
    public void setUp() throws Exception{
        readStudents = new ReadStudents();
        readTeachers = new ReadTeachers();
    }

    @Test
    @DisplayName("Get for the students")
    public void getForStudents() {
        assertTrue(readStudents.readAllUser(), "Getting all the students");
        assertTrue(readStudents.readOneUser(2), "Getting one specific user");
        assertTrue(readStudents.readStudentsTeachersRelations(2), "Getting students teachers");
        assertTrue(readStudents.getStudentsAVG(2), "Getting students AVG");
    }

    @Test
    @DisplayName("Incorrect input handling test for students")
    public void getErrorsForStudents(){
       assertFalse(UserExists.testIfExistsByID(0, "students"), "Incorrect student ID");
    }

    @Test
    @DisplayName("Get for teachers")
    public void getForTeachers(){
        assertTrue(readTeachers.readOneUser(1), "Getting one teacher");
        assertTrue(readTeachers.readAllUser(), "Getting all the teachers");
        assertTrue(readTeachers.readTeachersStudents(1));
    }

    @Test
    @DisplayName("Incorrect input handling test for teachers")
    public void getErrorForTeachers(){
        assertFalse(UserExists.testIfExistsByID(0, "teachers"), "Incorrect teacher ID");
    }

}
