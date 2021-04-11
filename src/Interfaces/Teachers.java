package Interfaces;

import java.util.Date;

public class Teachers {
    private int ID;
    private String Name;
    private String Surname;
    private Date Birth;
    private double FinancialFunds;
    private double Bonus;
    private int NoOfStudents;

    public Teachers(String name, String surname, Date birth, double financialFunds, double bonus, int noOfStudents){
        Name = name;
        Surname = surname;
        Birth = birth;
        FinancialFunds = financialFunds;
        Bonus = bonus;
        NoOfStudents = noOfStudents;
    }

    public String getName(){return Name;}
    public String getSurname(){return Surname;}
    public Date getBirth(){return Birth;}
    public double getFinancialFunds(){return FinancialFunds;}
    public double getBonus() {return Bonus;}

    public int getNoOfStudents() {return NoOfStudents;}
}
