/**
 * @author: Stepan Pijacek
 * @description: Main test suit for Update operations
 * */
package TestSuit;

import StudentsCRUD.UpdateStudents;
import TeachersCRUD.UpdateTeacher;
import Utilities.UserExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateTest {
    private UpdateStudents updateStudents;
    private UpdateTeacher updateTeacher;

    @BeforeEach
    public void setUp() throws Exception{
        updateStudents = new UpdateStudents();
        updateTeacher = new UpdateTeacher();
    }

    @Test
    @DisplayName("Update student")
    public void updateStudent(){
        assertTrue(updateStudents.giveScholarship(2, 5000), "Updating students scholarship - give");
        assertTrue(updateStudents.takeScholarship(2), "Updating students scholarship - take away");
    }

    @Test
    @DisplayName("Update teacher")
    public void updateTeachers(){
        assertTrue(updateTeacher.giveBonus(1), "Giving teacher bonus");
        assertTrue(updateTeacher.takeBonus(1), "Take away teachers bonus");
        assertTrue(updateTeacher.increasePay(1,5000), "Increasing pay");
    }
}
