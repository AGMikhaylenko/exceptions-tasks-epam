import java.util.List;

public class Group {
    private static int currentNumber = 0;

    private int id;
    private List<Student> students;
    private Faculty faculty;

    public Group(List<Student> students) {
        this.students = students;
        this.id = currentNumber++;
    }

    public Group(List<Student> students, Faculty faculty) {
        this.students = students;
        this.faculty = faculty;
        this.id = currentNumber++;
    }

    public Group(Faculty faculty) {
        this.faculty = faculty;
        this.id = currentNumber++;
    }

    public List<Student> getStudents() throws GroupIsEmptyException {
        if (students == null || students.size() == 0)
            throw new GroupIsEmptyException("В группе отсутствуют студенты");
        else
            return students;
    }

    public boolean containStudent(Student student) throws GroupIsEmptyException {
        if (students == null || students.size() == 0)
            throw new GroupIsEmptyException("В группе отсутствуют студенты");
        else
            return students.contains(student);
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Student getStudentById(int id) throws StudentIsNotExistsException, GroupIsEmptyException {
        Student student = null;
        try {
            student = getStudents().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        } catch (GroupIsEmptyException e) {
            throw new GroupIsEmptyException("В группе нет студентов");
        }
        if (student == null)
            throw new StudentIsNotExistsException();
        else
            return student;
    }

    public static class GroupIsEmptyException extends Exception {
        public GroupIsEmptyException() {
        }

        public GroupIsEmptyException(String message) {
            super(message);
        }

        public GroupIsEmptyException(String message, Throwable cause) {
            super(message, cause);
        }

        public GroupIsEmptyException(Throwable cause) {
            super(cause);
        }
    }

    public static class StudentIsNotExistsException extends Exception {
        public StudentIsNotExistsException() {
        }

        public StudentIsNotExistsException(String message) {
            super(message);
        }

        public StudentIsNotExistsException(String message, Throwable cause) {
            super(message, cause);
        }

        public StudentIsNotExistsException(Throwable cause) {
            super(cause);
        }
    }

}
