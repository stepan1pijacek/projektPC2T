/**
 * @author Stepan Pijacek(223313)
 **/
package Models;

import java.util.Date;

public class Teachers {
    private  int ID;
    private  String Name;
    private  String Surname;
    private  Date Birth;
    private  int Pay;
    private  int Bonus;

    public Teachers(String name, String surname, Date birth, int pay){
        Name = name;
        Surname = surname;
        Birth = birth;
        Pay = pay;
    }

    public int getID(){return ID;}
    public String getName(){return Name;}
    public String getSurname(){return Surname;}
    public Date getBirth(){return Birth;}
    public int getPay(){return Pay;}
    public void setID(int id){
        ID = id;
    }
    public void setBonus(int bonus){Bonus = bonus;}

    @Override
    public String toString() {
        return "\n Teacher{" + "\n" +
                " ID = " + ID + ", \n" +
                " name = " + Name + ", \n" +
                " surname =" + Surname + ", \n" +
                " Birth = " + Birth + ", \n" +
                " Pay = " + Pay + ", \n" +
                " Bonus = " + Bonus + ", \n"+
                '}';
    }
}
