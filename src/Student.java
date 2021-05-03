import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private static int currentId = 0;

    private int id;
    private String name;
    private Map<Discipline, Integer> rating;

    public Student(String name) {
        this.name = name;
        this.rating = new HashMap<>();
        this.id = currentId++;
    }

    public Student(String name, Map<Discipline, Integer> rating) {
        this.name = name;
        this.rating = rating;
        this.id = currentId++;
    }

    public String getName() {
        return name;
    }

    public Map<Discipline, Integer> getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public int getRatingByDiscipline(Discipline discipline) throws DisciplineNotListedException, RatingIsIncorrectException {
        Integer value = rating.get(discipline);
        if (value == null)
            throw new DisciplineNotListedException("Дисциплина отсутствует в списке студента");
        else if (value < 0 || value > 10)
            throw new RatingIsIncorrectException("Значение оценки должно находиться в пределах [0, 10]");
        else
            return value;
    }

    public boolean listOfDisciplineIsEmpty() {
        return rating.isEmpty();
    }

    public double getAverageRating() throws ListOfRatingIsEmptyException {
        if (rating.isEmpty())
            throw new ListOfRatingIsEmptyException("У студента нет дисциплин");
        else {
            Collection<Integer> values = rating.values();
            long n = values.stream().filter(x -> x >= 0 && x <= 10).count();
            double average = values.stream().filter(x -> x >= 0 && x <= 10).reduce(0, (acc, x) -> acc + x).doubleValue();
            if (n == 0)
                throw new ListOfRatingIsEmptyException("У студента нет дисциплин");
            else
                return average / n;
        }
    }

    public static class DisciplineNotListedException extends Exception {
        public DisciplineNotListedException() {
        }

        public DisciplineNotListedException(String message) {
            super(message);
        }

        public DisciplineNotListedException(String message, Throwable cause) {
            super(message, cause);
        }

        public DisciplineNotListedException(Throwable cause) {
            super(cause);
        }
    }

    public static class ListOfRatingIsEmptyException extends Exception {
        public ListOfRatingIsEmptyException() {
        }

        public ListOfRatingIsEmptyException(String message) {
            super(message);
        }

        public ListOfRatingIsEmptyException(String message, Throwable cause) {
            super(message, cause);
        }

        public ListOfRatingIsEmptyException(Throwable cause) {
            super(cause);
        }
    }

    public static class RatingIsIncorrectException extends Exception {
        public RatingIsIncorrectException() {
        }

        public RatingIsIncorrectException(String message) {
            super(message);
        }

        public RatingIsIncorrectException(String message, Throwable cause) {
            super(message, cause);
        }

        public RatingIsIncorrectException(Throwable cause) {
            super(cause);
        }
    }
}
