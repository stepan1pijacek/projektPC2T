package Interfaces;

import java.util.Date;

public class Students {
    public int ID;
    private String Name;
    private String Surname;
    private Date Birth;
    private Boolean Scholarship;

    public Students(String name, String surname, Date birth, Boolean scholarship){
        Name = name;
        Surname = surname;
        Birth = birth;
        Scholarship = scholarship;
    }

    public String getName(){return Name;}
    public String getSurname(){return Surname;}
    public Date getBirth(){return Birth;}
    public Boolean getScholarship(){return Scholarship;}
}
