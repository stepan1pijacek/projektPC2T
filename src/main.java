import Interfaces.Studnts;
import StudentsCRUD.Create;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {
    public static void main(String[] args) throws ParseException {
        Studnts studnts = new Studnts();
        String sDate = "22.05.2000";
        studnts.Name = "John";
        studnts.Surname = "Cena";
        studnts.Birth = new SimpleDateFormat("dd.MM.yyyy").parse(sDate);
        studnts.Schoolership = false;
        Create c1 = new Create();
        c1.inseetNewStudent(studnts);
    }
}
