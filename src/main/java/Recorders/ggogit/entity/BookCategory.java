package Recorders.ggogit.entity;

public enum BookCategory {
    FICTION("fiction", "소설"),
    NON_FICTION("nonFiction", "논픽션"),
    MYSTERY("mystery", "미스터리"),
    SCIENCE_FICTION("scienceFiction", "과학 소설"),
    BIOGRAPHY("biography", "전기");

    private final String value;
    private final String description;

    BookCategory(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}