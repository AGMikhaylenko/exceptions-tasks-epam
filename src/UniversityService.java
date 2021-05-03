import java.util.List;
import java.util.stream.Collectors;

public class UniversityService {


    public static double averageByStudent(List<Group> groups, int studentId) throws Group.StudentIsNotExistsException,
            Student.ListOfRatingIsEmptyException {
        Student s = null;
        for (Group g : groups) {
            try {
                s = g.getStudentById(studentId);
                break;
            } catch (Group.StudentIsNotExistsException | Group.GroupIsEmptyException e) {
                e.getMessage();
            }
        }
        if (s == null)
            throw new Group.StudentIsNotExistsException("Студент с Id = " + studentId + " отсутствует в списках");
        else
            try {
                return s.getAverageRating();
            } catch (Student.ListOfRatingIsEmptyException e) {
                throw e;
            }
    }


    public static double averageByDisciplineAndGroup(Discipline discipline, Group group)
            throws Student.DisciplineNotListedException, Group.GroupIsEmptyException {
        double average = 0;
        int n = 0;
        try {
            for (Student s : group.getStudents()) {
                try {
                    average += s.getRatingByDiscipline(discipline);
                    n++;
                } catch (Student.DisciplineNotListedException | Student.RatingIsIncorrectException e) {
                    e.getMessage();
                }
            }
        } catch (Group.GroupIsEmptyException e) {
            throw e;
        }

        if (n == 0)
            throw new Student.DisciplineNotListedException("Ни один студент не изучает дисциплины " + discipline.getName());
        else
            return average / n;
    }

    public static double averageByDisciplineAndFaculty(Discipline discipline, Faculty faculty, List<Group> groups)
            throws Faculty.FacultyIsEmptyException, Student.DisciplineNotListedException {
        double average = 0;
        int n = 0;
        List<Group> groupsInFaculty = groups.stream().filter(x -> x.getFaculty() == faculty).collect(Collectors.toList());
        if (groupsInFaculty.isEmpty())
            throw new Faculty.FacultyIsEmptyException("На факультете отсутствуют группы");

        else {
            for (Group g : groupsInFaculty) {
                try {
                    for (Student s : g.getStudents()) {
                        try {
                            average += s.getRatingByDiscipline(discipline);
                            n++;
                        } catch (Student.DisciplineNotListedException | Student.RatingIsIncorrectException e) {
                            e.getMessage();
                        }
                    }
                } catch (Group.GroupIsEmptyException e) {
                    e.getMessage();
                }
            }

            if (n == 0)
                throw new Student.DisciplineNotListedException("Ни один студент не изучает дисциплины " + discipline.getName());
            else
                return average / n;
        }
    }

    public static double averageByDiscipline(Discipline discipline, List<Group> groups) throws Student.DisciplineNotListedException {
        double average = 0;
        int n = 0;

        for (Group g : groups) {
            try {
                for (Student s : g.getStudents()) {
                    try {
                        average += s.getRatingByDiscipline(discipline);
                        n++;
                    } catch (Student.DisciplineNotListedException | Student.RatingIsIncorrectException e) {
                        e.getMessage();
                    }
                }
            } catch (Group.GroupIsEmptyException e) {
                e.getMessage();
            }
        }
        if (n == 0)
            throw new Student.DisciplineNotListedException("Ни один студент не изучает дисциплины " + discipline.getName());
        else
            return average / n;
    }
}
