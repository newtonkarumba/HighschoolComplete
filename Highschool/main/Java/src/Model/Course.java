package Model;

public class Course {

    //course has a name and score
    private int courseId;
    private String name;
    private Teacher teacher;

    //gettter and setters
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String toStringRow() {
        return "[Course Id:" + this.getCourseId()+ ", Subject Name: " + this.getName() +", Teacher: " + this.getTeacher().getName() + "]";
    }
    @Override
    public String toString() {
        return "\nCourse Id:" + courseId+
                "\nSubject Name: " + this.getName()+
                "\nTeacher:" + this.getTeacher().getName()+ '\'';
    }


}