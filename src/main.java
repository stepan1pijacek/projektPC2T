/**
 * @author Stepan Pijacek(223313)
 **/
import CommonCRUD.Create;
import CommonCRUD.Delete;
import CommonCRUD.Read;
import StudentsCRUD.CreateStudents;
import StudentsCRUD.DeleteStudents;
import StudentsCRUD.ReadStudents;
import StudentsCRUD.UpdateStudents;
import TeachersCRUD.CreateTeachers;
import TeachersCRUD.DeleteTeacher;
import TeachersCRUD.ReadTeachers;
import TeachersCRUD.UpdateTeacher;
import Utilities.ReadConsoleInput;
import Utilities.Subjects;
import dbConnect.DBConnection;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        boolean run = true;
        int iteration = 0;

        DeleteStudents deleteStudents = new DeleteStudents();
        ReadStudents readStudents = new ReadStudents();
        UpdateStudents updateStudents = new UpdateStudents();

        DeleteTeacher deleteTeacher = new DeleteTeacher();
        ReadTeachers readTeachers = new ReadTeachers();
        UpdateTeacher updateTeacher = new UpdateTeacher();

        while(run){
            if(iteration == 0){
                iteration ++;
            }
            else{
                System.out.println(System.lineSeparator());
                System.out.println("Please select desired operation:");
                System.out.println("1 Create new student");
                System.out.println("2 Create new teacher");
                System.out.println("3 Create new relationship between student and teacher");
                System.out.println("4 Create new score");
                System.out.println("5 Delete student by ID");
                System.out.println("6 Delete teacher by ID");
                System.out.println("7 Delete relationship");
                System.out.println("8 Delete score");
                System.out.println("9 Get all the users (Teachers and Students)");
                System.out.println("10 Get one user (Teacher or Student)");
                System.out.println("11 Get students score");
                System.out.println("12 Get all the funds");
                System.out.println("13 Get students teachers");
                System.out.println("14 Get teachers students ordered by AVG");
                System.out.println("15 Give student scholarship");
                System.out.println("16 Take students scholarship");
                System.out.println("17 Give teacher bonus");
                System.out.println("18 Take teachers bonus");
                System.out.println("19 Increase teachers pay");
                System.out.println("20 Exit");

                choice = ReadConsoleInput.readNumberInputFromConsole(sc);

                switch (choice) {
                    //1 Create new student
                    case 1 -> {
                        System.out.println("Please input students name");
                        String name = ReadConsoleInput.readStringInput(sc);
                        System.out.println("Please input students surname");
                        String surname = ReadConsoleInput.readStringInput(sc);
                        System.out.println("Please input students birth date in pater dd-MM-yyyy");
                        String sDate = ReadConsoleInput.readStringInput(sc);
                        try {
                            CreateStudents createStudents = new CreateStudents(name, surname, new SimpleDateFormat("dd-MM-yyyy").parse(sDate), 0);
                            createStudents.insertNewUser();
                        } catch (ParseException pe) {
                            System.out.println("Failed to parse date" + pe.toString());
                        }
                    }
                    //2 Create new teacher
                    case 2 ->{
                        System.out.println("Please input teachers name");
                        String name = ReadConsoleInput.readStringInput(sc);
                        System.out.println("Please input teachers surname");
                        String surname = ReadConsoleInput.readStringInput(sc);
                        System.out.println("Please input teachers birth date");
                        String sDate = ReadConsoleInput.readStringInput(sc);
                        System.out.println("Please input teachers pay");
                        int pay = ReadConsoleInput.readNumberInputFromConsole(sc);

                        try{
                            CreateTeachers createTeachers = new CreateTeachers(name, surname, new SimpleDateFormat("dd-MM-yyyy").parse(sDate), pay);
                            createTeachers.insertNewUser();
                        } catch (ParseException pe){
                            System.out.println("Failed to parse date" + pe.toString());
                        }
                    }
                    //3 Create new relationship
                    case 3 ->{
                        System.out.println("Please input students ID");
                        int studentId = ReadConsoleInput.readNumberInputFromConsole(sc);
                        System.out.println("Please input teachers ID");
                        int teachersID = ReadConsoleInput.readNumberInputFromConsole(sc);

                        Create create = new Create();
                        create.assignTeacherToStudent(studentId,teachersID);
                    }
                    //4 Create new score
                    case 4 ->{
                        System.out.println("Please input score for the student");
                        int score = ReadConsoleInput.readNumberInputFromConsole(sc);
                        System.out.println("Please input students ID");
                        int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        System.out.println("Please select one of the subjects: 1 - PC2T, 2 - DAK, 3 - OOP, 4 - DE1, 5 - ARS (as a number)");
                        int selector = ReadConsoleInput.readNumberInputFromConsole(sc);
                        Subjects selectedSubject = ReadConsoleInput.readSubjectInput(selector);

                        CreateStudents.insertNewScore(score, selectedSubject, Id);
                    }
                    //5 Delete student by ID
                    case 5 ->{
                        System.out.println("Please input students ID");
                        int id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        deleteStudents.deleteStudentByID(id);
                    }
                    //6 Delete teacher by ID
                    case 6 ->{
                        System.out.println("Please input teachers ID");
                        int id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        deleteTeacher.deleteTeacher(id);
                    }
                    //7 Delete relationship
                    case 7 ->{
                        System.out.println("Please input students ID");
                        int studentsId = ReadConsoleInput.readNumberInputFromConsole(sc);
                        System.out.println("Please input teachers ID");
                        int teachersID = ReadConsoleInput.readNumberInputFromConsole(sc);
                        Delete.deleteStudentTeacherRelation(studentsId, teachersID);
                    }
                    //8 Delete score
                    case 8 ->{
                        System.out.println("Please input students ID");
                        int studentID = ReadConsoleInput.readNumberInputFromConsole(sc);
                        System.out.println("Please select one of the subjects: 1 - PC2T, 2 - DAK, 3 - OOP, 4 - DE1, 5 - ARS (as a number)");
                        int selector = ReadConsoleInput.readNumberInputFromConsole(sc);
                        Subjects selectedSubject = ReadConsoleInput.readSubjectInput(selector);

                        deleteStudents.deleteScore(studentID, selectedSubject);
                    }
                    //9 Get all the users (Teachers and Students)
                    case 9 ->{
                        Read.readAllUsers();
                    }
                    //10 Get one user (Teacher or Student)
                    case 10 ->{
                        System.out.println("Choose which user group to select from");
                        System.out.println("1 Students");
                        System.out.println("2 Teachers");
                        int group = ReadConsoleInput.readNumberInputFromConsole(sc);
                        switch (group){
                            case 1 ->{
                                System.out.println("Please input students ID");
                                int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                                readStudents.readOneUser(Id);
                            }
                            case 2 ->{
                                System.out.println("Please input teachers ID");
                                int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                                readTeachers.readOneUser(Id);
                            }
                        }
                    }
                    //11 Get students score
                    case 11 ->{
                        System.out.println("Please input students ID");
                        int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        readStudents.getStudentsAVG(Id);
                    }
                    //12 Read all the funds
                    case 12 ->{
                        Read.readAllTheFunds();
                    }
                    //13 Get students teachers
                    case 13 ->{
                        System.out.println("Please input students ID");
                        int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        readStudents.readStudentsTeachersRelations(Id);
                    }
                    //14 Get teachers students ordered by AVG
                    case 14 ->{
                        System.out.println("Please input teachers ID");
                        int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        readTeachers.readTeachersStudents(Id);
                    }
                    //15 Give student scholarship
                    case 15 ->{
                        System.out.println("Please input students ID");
                        int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        System.out.println("Please input desired scholarship");
                        int scholarship = ReadConsoleInput.readNumberInputFromConsole(sc);
                        updateStudents.giveScholarship(Id, scholarship);
                    }
                    //16 Take students scholarship
                    case 16 ->{
                        System.out.println("Please input students ID");
                        int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        updateStudents.takeScholarship(Id);
                    }
                    //17 Give teacher bonus
                    case 17 ->{
                        System.out.println("Please input teachers ID");
                        int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        updateTeacher.giveBonus(Id);
                    }
                    //18 Take teachers bonus
                    case 18 ->{
                        System.out.println("Please input teachers ID");
                        int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        updateTeacher.takeBonus(Id);
                    }
                    //19 Increase teachers pay
                    case 19 ->{
                        System.out.println("Please input teachers ID");
                        int Id = ReadConsoleInput.readNumberInputFromConsole(sc);
                        System.out.println("Please input increase in pay");
                        int pay = ReadConsoleInput.readNumberInputFromConsole(sc);
                        updateTeacher.increasePay(Id, pay);
                    }
                    //21 exit
                    case 20 -> {
                        run = false;
                        DBConnection.closeConnection();
                    }
                }
            }
        }
    }
}
