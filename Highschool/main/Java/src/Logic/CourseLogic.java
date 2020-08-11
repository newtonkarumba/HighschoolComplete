package Logic;

import Db.DbConnection;
import Db.DbConnectionI;
import Model.Course;
import Model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseLogic implements CourseLogicI {
    private DbConnectionI dbConnectionI;

    public CourseLogic() throws SQLException, ClassNotFoundException {
        this.dbConnectionI = new DbConnection();
    }

    @Override
    public boolean add(Course course) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("INSERT INTO course(courseId, name, teacherstaffno) VALUES(?, ?, ?)");
        preparedStatement.setInt(1, course.getCourseId());
        preparedStatement.setString(2, course.getName());
        preparedStatement.setString(3, course.getTeacher().getStaffNo());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public boolean update(Course course) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("UPDATE course SET name = ? WHERE courseId = ?");
        preparedStatement.setString(1, course.getName());
        preparedStatement.setInt(2, course.getCourseId());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public boolean delete(Course course) throws SQLException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("DELETE FROM course WHERE courseId= ?");
        preparedStatement.setLong(1, course.getCourseId());
        return dbConnectionI.execute(preparedStatement);
    }

    @Override
    public Course find(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM students WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        if (resultSet.next()) {
            Course course = new Course();
            course.setName(resultSet.getString("name"));
            course.setCourseId(resultSet.getInt("courseId"));
            TeacherLogicI teacherLogicI = new TeacherLogic();
            Teacher teacher = teacherLogicI.find(resultSet.getString("teacherstaffno"));
            course.setTeacher(teacher);
            return course;
        } else
            return null;
    }

    @Override
    public List<Course> findAll() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = this
                .dbConnectionI
                .getConnection()
                .prepareStatement("SELECT * FROM course");
        ResultSet resultSet = dbConnectionI.executeQuery(preparedStatement);
        List<Course> courses = new ArrayList<>();
        TeacherLogicI teacherLogicI = new TeacherLogic();
        while (resultSet.next()) {
            Course course = new Course();
            course.setCourseId(resultSet.getInt("courseId"));
            course.setName(resultSet.getString("name"));
            Teacher teacher = teacherLogicI.find(resultSet.getString("teacherstaffno"));
            course.setTeacher(teacher);
            courses.add(course);
        }
        return courses;
    }

    @Override
    protected void finalize() throws Throwable {
        dbConnectionI.close();
    }
}
