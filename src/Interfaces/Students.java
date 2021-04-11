package Interfaces;

import java.util.Date;

public class Students {
    private int ID;
    private String Name;
    private String Surname;
    private Date Birth;
    private boolean Scholarship;

    public Students(String name, String surname, Date birth, boolean scholarship){
        Name = name;
        Surname = surname;
        Birth = birth;
        Scholarship = scholarship;
    }

    public int getID(){return ID;}
    public String getName(){return Name;}
    public String getSurname(){return Surname;}
    public Date getBirth(){return Birth;}
    public boolean getScholarship(){return Scholarship;}
}
