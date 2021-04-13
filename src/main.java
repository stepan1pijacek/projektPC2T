/**
 * @author Stepan Pijacek(223313)
 **/
import StudentsCRUD.Create;
import StudentsCRUD.Delete;
import StudentsCRUD.Read;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class main {
    public static void main(String[] args) throws ParseException {
        String sDate = "22.05.2000";
        Create c1 = new Create("John", "Cena", new SimpleDateFormat("dd.MM.yyyy").parse(sDate), 0);
        //c1.insertNewScore(3,"PC2T",1);
        Delete d1 = new Delete();
        d1.deleteStudentByID(1);

        Read r1 = new Read();
        r1.readAllStudents();
        //r1.getStudentsAVG(1);


    }
}
