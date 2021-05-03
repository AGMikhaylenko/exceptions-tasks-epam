public enum Discipline {
    MATH("Математика"),
    CHEMISTRY("Химия"),
    PHYSICS("Физика"),
    ALGORITHMS("Алгоритмы"),
    HISTORY("История"),
    DATABASE("Базы данных"),
    SOCIOLOGY("Социология");

    private String name;

    Discipline(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
