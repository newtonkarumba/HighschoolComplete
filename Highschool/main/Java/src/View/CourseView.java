package View;

import Logic.CourseLogic;
import Logic.CourseLogicI;
import Logic.TeacherLogic;
import Model.Course;
import Model.Teacher;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CourseView implements CourseViewI{
    private Scanner scanner;
    private CourseLogicI courseLogicI;

    public CourseView() throws SQLException, ClassNotFoundException {
        scanner = new Scanner(System.in);
        courseLogicI = new CourseLogic();
    }

    //register a new course
    private void register() throws SQLException, ClassNotFoundException {
        Course course = new Course();
        System.out.println("Enter Course Id");
        course.setCourseId(scanner.nextInt());
        System.out.println("Enter Name:");
        course.setName(scanner.next());
        scanner.nextLine();
        Teacher teacher;
        System.out.println("Enter the Staff Number of the teacher of course:");
        teacher = new TeacherLogic().find(scanner.nextLine());
        if(teacher ==null){
            System.err.println("Teacher not found");}
        else{
            course.setTeacher(teacher);
        }

        System.out.println("You are about to register the following course details:\n" + course.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            courseLogicI.add(course);
    }

    //show registered courses
    private void show() throws SQLException, ClassNotFoundException {
        System.out.println("List of Courses from the DB");
        List<Course> courses = courseLogicI.findAll();
        Iterator iterator = courses.iterator();
        while(iterator.hasNext()){
            System.out.println(((Course) iterator.next()).toStringRow());
        }
    }

    /**/
    @Override
    public void menu() throws SQLException, ClassNotFoundException {
        int option;
        do {
            System.out.println("Welcome to Courses Module. \n" +
                    "Please Select an Option: \n" +
                    "1. Register a Course \n" +
                    "2. Edit a Course \n" +
                    "3. Delete a Course \n" +
                    "4. Show list of Courses \n" +
                    "0. Back to Main Menu \n");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
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
        } while (option != 0);
        courseLogicI = null;
    }

    //update courses info
    private void edit() throws SQLException, ClassNotFoundException {
        Course course = new Course();
        System.out.println("Update Course");
        System.out.println("Enter course ID #:");
        course.setCourseId(scanner.nextInt());
        if (course == null) {
            System.out.println("course not registered");
            return;
        }
        System.out.println("Enter New Name:");
        course.setName(scanner.next());
        scanner.nextLine();
        Teacher teacher;
        System.out.println("Enter course teacher staff Number:");
        teacher = new TeacherLogic().find(scanner.nextLine());
        if(teacher ==null){
            System.err.println("Teacher not found");}
        else{
            course.setTeacher(teacher);
        }
        System.out.println("You are about to update the following courses' details:\n" + course.toString() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        if (choice == 1)
            courseLogicI.update(course);

    }

    //To Remove course from DB
    private void delete() throws SQLException, ClassNotFoundException {
        Course course = new Course();
        System.out.println("Enter course ID # to delete :");
        course.setCourseId(scanner.nextInt());
        System.out.println("You are about to delete course with courseId:"
                +course.getCourseId() + "\nContinue?\n1. Yes\n2. No");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1)
            courseLogicI.delete(course);
    }
}
