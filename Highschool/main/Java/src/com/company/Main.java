package com.company;

import Model.Course;
import View.StudentView;
import View.TeacherView;
import View.CourseView;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int option;
        TeacherView teacherView = new TeacherView();
        StudentView studentView = new StudentView();
        CourseView courseView = new CourseView();
        do {
            System.out.println("Welcome to School System:\n Please select an option:\n1. Manage Teachers\n2. Manage Students\n3. Manage Courses\n4. Exit");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    teacherView.menu();
                    break;
                case 2:
                    studentView.menu();
                    break;
                case 3:
                    courseView.menu();
            }
        } while(option != 4);
        studentView = null;
        teacherView=null;
        courseView=null;
    }
}
