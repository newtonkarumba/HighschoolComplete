package View;

import Logic.StudentLogic;
import Logic.StudentLogicI;
import Logic.TeacherLogic;
import Logic.TeacherLogicI;
import Model.Student;
import Model.Teacher;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TeacherView implements TeacherViewI {
    Scanner scanner;
    TeacherLogicI teacherLogicI;
    public TeacherView() throws SQLException, ClassNotFoundException  {
        scanner = new Scanner(System.in);
        teacherLogicI = new TeacherLogic();
    }

    private void register() throws SQLException{
        Teacher teacher = new Teacher();
        System.out.println("Enter name:");
        teacher.setName(scanner.nextLine());
        System.out.println("Enter registration #:");
        teacher.setStaffNo(scanner.nextLine());
        System.out.println("Enter course:");
        teacher.setCourse(scanner.nextLine());
        System.out.println("Enter ID #:");
        teacher.setId(scanner.nextLong());
        System.out.println("You are about to register the following details:\n" + teacher.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1)
            teacherLogicI.add(teacher);
    }

    private void show() throws SQLException{
        System.out.println("List of teachers from the DB");
        List<Teacher> teachers = teacherLogicI.findAll();
        Iterator iterator = teachers.iterator();
        while(iterator.hasNext()){
            System.out.println(((Teacher) iterator.next()).toString());
        }
    }
    private void delete() throws SQLException{
        Teacher teacher = new Teacher();
        System.out.println("Please Enter Staff Number of teacher to be deleted:");
        teacher.setStaffNo(scanner.nextLine());
        System.out.println("You are about to Delete teacher with Staff Number:"
                +teacher.getStaffNo()+ "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 1)
            teacherLogicI.delete(teacher);
        System.out.println("Teacher Record Successfully Deleted... ");
    }

    private void edit() throws SQLException{

        Teacher teacher = new Teacher();
        System.out.println("Please Enter Staff Number of teacher to edit details:");
        teacher.setStaffNo(scanner.nextLine());
        System.out.println("Edit the teacher Details:");
        System.out.println("Enter name:");
        teacher.setName(scanner.nextLine());
        System.out.println("Enter course:");
        teacher.setCourse(scanner.nextLine());
        System.out.println("You are about to edit teacher with the details:\n"+ teacher.toString()
                + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        if(choice == 1)
            teacherLogicI.update(teacher);
        System.out.println("Teacher Record Successfully Updated... ");


    }


    @Override
    public void menu() throws SQLException {
        int option;
        do {
            System.out.println("Welcome to Teacher Module. \n" +
                    "Please select an option: \n" +
                    "1. Register a teacher \n" +
                    "2. Edit a teacher \n" +
                    "3. Delete a teacher \n" +
                    "4. Show list of teachers \n" +
                    "0. Back to main menu \n");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    this.register();
                    break;
                case 2:
                    this.edit();
                    break;
                case 3:

                    this.delete();
                    break;
                case 4:
                    this.show();
                    break;
                case 0:
                    break;
            }
        } while(option != 0);
        teacherLogicI = null;
    }
}

