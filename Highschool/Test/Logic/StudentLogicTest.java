package Logic;

import org.junit.Test;

import static org.junit.Assert.*;

import Logic.StudentLogic;
import Logic.StudentLogicI;
import Model.Student;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentLogicTest {
    StudentLogicI studentLogicI;
    public StudentLogicTest() throws SQLException, ClassNotFoundException {
        studentLogicI = new StudentLogic();
    }
    @Test
    public void add() throws SQLException{
        Student student = new Student();
        student.setIdNumber("10101010");
        student.setCourse("FOOD SCIENCE");
        student.setRegistrationNo("FS04/220/445");
        student.setName("GORDON KITCHEN NIGHTMARES");
        if(!studentLogicI.add(student))
            Assert.assertFalse(false); // confirmation that it has failed
        Student search = studentLogicI.find(student.getRegistrationNo());
        Assert.assertEquals(student.getRegistrationNo(), search.getRegistrationNo());
        Assert.assertEquals(student.getName(), search.getName());
        Assert.assertEquals(student.getCourse(), search.getCourse());
        Assert.assertEquals(student.getIdNumber(), search.getIdNumber());
    }

    @Test
    public void update() throws SQLException {
        Student search = studentLogicI.find("FS04/220/445");
        search.setName("CHEF THE RAMSEY");
        search.setCourse("SCIENCE FOOD");
        search.setIdNumber("01010101");
        if(!studentLogicI.update(search))
            // confirm that it has failed
            Assert.assertFalse(false);
        // Means the update was successful. We need to test for correctness of the updated data
        Student student = studentLogicI.find(search.getId());
        Assert.assertEquals(student.getRegistrationNo(), search.getRegistrationNo());
        Assert.assertEquals(student.getName(), search.getName());
        Assert.assertEquals(student.getCourse(), search.getCourse());
        Assert.assertEquals(student.getIdNumber(), search.getIdNumber());
    }

    @Test
    public void delete()  throws SQLException {
        Student search = studentLogicI.find("FS04/220/445");
        if(!studentLogicI.delete(search))
            Assert.assertFalse(false);
        Student student = studentLogicI.find(search.getRegistrationNo());
        Assert.assertEquals(student.getRegistrationNo(), search.getRegistrationNo());
    }

    @Test
    public void findAll()  throws SQLException {
        Student student=new Student();
        List<Student> find=new ArrayList<>() {};
        find=studentLogicI.findAll();
        if(find.isEmpty());
        Assert.assertFalse(false);
        student.setRegistrationNo("c2020");
        Student search=studentLogicI.find(student.getRegistrationNo());
        Assert.assertEquals(search.getRegistrationNo(),student.getRegistrationNo());

    }

    @Test
    public void find()  throws SQLException {
        Student student=new Student();
        student.setRegistrationNo("267");
        Student search=studentLogicI.find(student.getRegistrationNo());
        Assert.assertEquals(search.getRegistrationNo(),student.getRegistrationNo());
    }
}