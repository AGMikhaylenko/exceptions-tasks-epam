public enum Faculty {
    FIT("Факультет информационных технологий"),
    FUSK("Факультет управления и социальный коммуникаций"),
    FPIE("Факультет природопользования и экологии");

    private String name;

    Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class FacultyIsEmptyException extends Exception{
        public FacultyIsEmptyException() {
        }

        public FacultyIsEmptyException(String message) {
            super(message);
        }

        public FacultyIsEmptyException(String message, Throwable cause) {
            super(message, cause);
        }

        public FacultyIsEmptyException(Throwable cause) {
            super(cause);
        }
    }
}
