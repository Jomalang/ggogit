package Recorders.ggogit.entity;

public enum SeedCategory {
    BOOK("book", "도서"),
    IDEA("idea", "생각"),
    PHRASE("phrase", "문장"),
    STUDY("study", "공부"),
    YOUTUBE("youtube", "유튜브");

    private final String value;
    private final String description;

    SeedCategory(String value, String description) {
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
