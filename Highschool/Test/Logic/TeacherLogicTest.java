package Logic;

import Logic.StudentLogic;
import Logic.TeacherLogic;
import Logic.TeacherLogicI;
import Model.Student;
import Model.Teacher;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TeacherLogicTest {
    TeacherLogicI teacherLogicI;

    public TeacherLogicTest() throws SQLException, ClassNotFoundException{
        teacherLogicI = new TeacherLogic();
    }
    @Test
    public void add() throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setStaffNo("10101010");
        teacher.setCourse("FOOD SCIENCE");
        teacher.setName("Jack Ma");
        if(!teacherLogicI.add(teacher))
            Assert.assertFalse(false); // confirmation that it has failed
        Teacher search = teacherLogicI.find(teacher.getStaffNo());
        Assert.assertEquals(teacher.getStaffNo(), search.getStaffNo());
        Assert.assertEquals(teacher.getName(), search.getName());
        Assert.assertEquals(teacher.getCourse(), search.getCourse());
    }

    @Test
    public void update() throws SQLException {
        Teacher search = teacherLogicI.find("10101010");
        search.setName("Ma Jack");
        search.setCourse("SCIENCE FOOD");
        if(!teacherLogicI.update(search))
            // confirm that it has failed
            Assert.assertFalse(false);
        // Means the update was successful. We need to test for correctness of the updated data
        Teacher teacher = teacherLogicI.find(search.getStaffNo());
        Assert.assertEquals(teacher.getStaffNo(), search.getStaffNo());
        Assert.assertEquals(teacher.getName(), search.getName());
        Assert.assertEquals(teacher.getCourse(), search.getCourse());
    }

    @Test
    public void delete() throws SQLException {
        Teacher search = teacherLogicI.find("345");
        if(!teacherLogicI.delete(search))
            Assert.assertFalse(false);
        Teacher teacher = teacherLogicI.find(search.getStaffNo());
        Assert.assertEquals(teacher.getStaffNo(), search.getStaffNo());
    }

    @Test
    public void findAll() throws SQLException {
        Teacher teacher =new Teacher();
        List<Teacher> find=new ArrayList<>() {};
        find=teacherLogicI.findAll();
        if(find.isEmpty());
        Assert.assertFalse(false);
        teacher.setStaffNo("c2020");
        Teacher search=teacherLogicI.find(teacher.getStaffNo());
        Assert.assertEquals(search.getStaffNo(),teacher.getStaffNo());
    }

    @Test
    public void find() throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setStaffNo("e267");
        Teacher search=teacherLogicI.find(teacher.getStaffNo());
        Assert.assertEquals(search.getStaffNo(),teacher.getStaffNo());
    }

}