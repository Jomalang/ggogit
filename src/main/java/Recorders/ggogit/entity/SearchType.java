package Recorders.ggogit.entity;

public enum SearchType {
    TITLE("title", "제목"),
    CONTENT("content", "내용"),
    ALL("all", "제목 + 내용");

    private final String value;
    private final String description;

    SearchType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static SearchType of(String value) {
        for (SearchType type : SearchType.values()) {
            if (type.value.equals(value))
                return type;
        }
        throw new IllegalArgumentException("SearchType의 value 인자를 잘못 받았습니다.");
    }
}
