package Recorders.ggogit.entity;

import lombok.Getter;

@Getter
public enum SeedCategoryType {
    BOOK("book", "도서"),
    IDEA("idea", "생각"),
    PHRASE("phrase", "문장"),
    STUDY("study", "공부"),
    YOUTUBE("youtube", "유튜브");

    private final String value;
    private final String description;

    SeedCategoryType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static SeedCategoryType of(String value) {
        for (SeedCategoryType type : SeedCategoryType.values()) {
            if (type.value.equals(value))
                return type;
        }
        throw new IllegalArgumentException("SeedCategoryType의 value 인자를 잘못 받았습니다.");
    }
}
