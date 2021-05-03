import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Group> groups = initialization();

        try {
            System.out.printf("Средняя оценка у студента %d = %.2f \n", 2, UniversityService.averageByStudent(groups, 2));
        } catch (Group.StudentIsNotExistsException | Student.ListOfRatingIsEmptyException e) {
            System.out.println(e.getMessage());
        }


        try {
            System.out.printf("Средняя оценка по дисциплине %s в группе номер %d = %.2f \n", Discipline.DATABASE.getName(),
                    0, UniversityService.averageByDisciplineAndGroup(Discipline.DATABASE, groups.get(0)));
        } catch (Student.DisciplineNotListedException | Group.GroupIsEmptyException e) {
            System.out.println(e.getMessage());
        }


        try {
            System.out.printf("Средняя оценка по дисциплине %s на %s = %.2f \n", Discipline.ALGORITHMS.getName(),
                    Faculty.FIT.getName(), UniversityService.averageByDisciplineAndFaculty(Discipline.ALGORITHMS, Faculty.FIT, groups));
        } catch (Faculty.FacultyIsEmptyException | Student.DisciplineNotListedException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.printf("Средняя оценка по предмету %s = %.2f \n",Discipline.HISTORY.getName(), UniversityService.averageByDiscipline(Discipline.HISTORY, groups));
        } catch (Student.DisciplineNotListedException e) {
            System.out.println(e.getMessage());
        }

    }


    public static List<Group> initialization() {
        Student studentOne = new Student("Alexander", new HashMap<>());
        Map<Discipline, Integer> ratingTwo = new HashMap<>();
        ratingTwo.put(Discipline.ALGORITHMS, 8);
        ratingTwo.put(Discipline.HISTORY, 7);
        ratingTwo.put(Discipline.PHYSICS, 11);
        Student studentTwo = new Student("Sergey", ratingTwo);
        Map<Discipline, Integer> ratingThree = new HashMap<>();
        ratingThree.put(Discipline.HISTORY, 5);
        ratingThree.put(Discipline.SOCIOLOGY, 8);
        Student studentThree = new Student("Alexey", ratingThree);
        Group groupOne = new Group(Arrays.asList(new Student[]{studentOne, studentTwo, studentThree}), Faculty.FIT);

        Map<Discipline, Integer> ratingFour = new HashMap<>();
        ratingFour.put(Discipline.ALGORITHMS, 3);
        ratingFour.put(Discipline.HISTORY, 4);
        ratingFour.put(Discipline.PHYSICS, 9);
        Student studentFour = new Student("Roman", ratingFour);
        Map<Discipline, Integer> ratingFive = new HashMap<>();
        ratingFive.put(Discipline.CHEMISTRY, 8);
        ratingFive.put(Discipline.SOCIOLOGY, 7);
        ratingFive.put(Discipline.MATH, 11);
        Student studentFive = new Student("Sergey", ratingFive);
        Map<Discipline, Integer> ratingSix = new HashMap<>();
        ratingSix.put(Discipline.MATH, 10);
        ratingSix.put(Discipline.ALGORITHMS, 9);
        ratingSix.put(Discipline.DATABASE, 18);
        Student studentSix = new Student("Pavel", ratingSix);
        Group groupTwo = new Group(Arrays.asList(new Student[]{studentFour, studentFive, studentSix}), Faculty.FUSK);

        Map<Discipline, Integer> ratingSeven = new HashMap<>();
        ratingSeven.put(Discipline.SOCIOLOGY, 3);
        ratingSeven.put(Discipline.HISTORY, 4);
        ratingSeven.put(Discipline.CHEMISTRY, 10);
        Student studentSeven = new Student("Kseniya", ratingSeven);
        Map<Discipline, Integer> ratingEight = new HashMap<>();
        ratingEight.put(Discipline.CHEMISTRY, 5);
        ratingEight.put(Discipline.SOCIOLOGY, 6);
        ratingEight.put(Discipline.MATH, 6);
        Student studentEight = new Student("Julia", ratingEight);
        Map<Discipline, Integer> ratingNine = new HashMap<>();
        ratingNine.put(Discipline.MATH, 10);
        ratingNine.put(Discipline.SOCIOLOGY, 9);
        ratingNine.put(Discipline.PHYSICS, 7);
        Student studentNine = new Student("Viktoria", ratingNine);
        Group groupThree = new Group(Arrays.asList(new Student[]{studentSeven, studentEight, studentNine}), Faculty.FUSK);

        Group groupFour = new Group(Faculty.FIT);


        return Arrays.asList(groupOne, groupTwo, groupThree, groupFour);
    }
}
