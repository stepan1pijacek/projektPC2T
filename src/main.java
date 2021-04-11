import StudentsCRUD.Create;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class main {
    public static void main(String[] args) throws ParseException {
        String sDate = "22.05.2000";
        Create c1 = new Create("John", "Cena", new SimpleDateFormat("dd.MM.yyyy").parse(sDate), false);
        c1.insertNewStudent(c1);
    }
}
