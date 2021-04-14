/**
 * @author Stepan Pijacek(223313)
 **/
package Models;

import java.util.Date;

public class Teachers {
    private int ID;
    private final String Name;
    private final String Surname;
    private final Date Birth;
    private final int Bonus;
    private final int Pay;

    public Teachers(String name, String surname, Date birth, int bonus, int pay){
        Name = name;
        Surname = surname;
        Birth = birth;
        Bonus = bonus;
        Pay = pay;
    }

    public int getID(){return ID;}
    public String getName(){return Name;}
    public String getSurname(){return Surname;}
    public Date getBirth(){return Birth;}
    public int getBonus() {return Bonus;}
    public int getPay(){return Pay;}
    public void setID(int id){
        ID = id;
    }

    @Override
    public String toString() {
        return "Student{" + "\n" +
                " ID = " + ID + ", \n" +
                " name = " + Name + ", \n" +
                " surname =" + Surname + ", \n" +
                " Birth = " + Birth + ", \n" +
                " Bonus = " + Bonus + ", \n" +
                " Pay = " + Pay + "\n" +
                '}';
    }
}
