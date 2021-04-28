package Utilities;

import java.util.Date;
import java.util.Scanner;

public class ReadConsoleInput {
    public static int readNumberInputFromConsole(Scanner sc) {
        int number = 0;
        try {
            number = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Exception has occurred " + e.toString());
            System.out.println("Please input whole number ");
            sc.nextLine();
            number = readNumberInputFromConsole(sc);
        }
        return number;
    }

    public static String readStringInput(Scanner sc){
        String input = "";
        try{
            input = sc.next();
        } catch (Exception e){
            System.out.println("Exception has occurred " + e.toString());
            System.out.println("Please input whole number ");
            sc.nextLine();
            input = readStringInput(sc);
        }
        return input;
    }

    public static Subjects readSubjectInput(int subject){
        switch (subject){
            case 1 ->{
                return Subjects.PC2T;
            }
            case 2 ->{
                return  Subjects.DAK;
            }
            case 3 ->{
                return  Subjects.OOP;
            }
            case 4 ->{
                return  Subjects.DE1;
            }
            case 5 ->{
                return  Subjects.ARS;
            }
        }

        return null;
    }
}
