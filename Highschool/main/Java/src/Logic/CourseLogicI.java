package Logic;

import Model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseLogicI {
    boolean add(Course course) throws SQLException;

    boolean update(Course course) throws SQLException;

    boolean delete(Course course) throws SQLException;

    Course find(int id) throws SQLException, ClassNotFoundException;

    List<Course> findAll() throws SQLException, ClassNotFoundException;
}