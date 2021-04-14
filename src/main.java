/**
 * @author Stepan Pijacek(223313)
 **/
import StudentsCRUD.CreateStudents;
import StudentsCRUD.DeleteStudents;
import StudentsCRUD.ReadStudents;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class main {
    public static void main(String[] args) throws ParseException {
        String sDate = "22.05.2000";
        CreateStudents c1 = new CreateStudents("John", "Cena", new SimpleDateFormat("dd.MM.yyyy").parse(sDate), 0);
        //c1.insertNewScore(3,"PC2T",1);
        DeleteStudents d1 = new DeleteStudents();
        d1.deleteUserByID(1);

        ReadStudents r1 = new ReadStudents();
        //r1.getStudentsAVG(1);


    }
}
