/**
 * @author Stepan Pijacek(223313)
 **/
package Models;

import java.util.Date;

public class Students {
    private int ID;
    private final String Name;
    private final String Surname;
    private final Date Birth;
    private final int Scholarship;

    public Students(String name, String surname, Date birth, int scholarship){
        Name = name;
        Surname = surname;
        Birth = birth;
        Scholarship = scholarship;
    }

    public int getID(){return ID;}
    public String getName(){return Name;}
    public String getSurname(){return Surname;}
    public Date getBirth(){return Birth;}
    public int getScholarship(){return Scholarship;}
    public void setID(int id){
        ID = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                " ID = " + ID + "," +
                " name = " + Name + "," +
                " surname =" + Surname + "," +
                " Birth = " + Birth + "," +
                " Scholarship = " + Scholarship + "," +
                '}';
    }
}
