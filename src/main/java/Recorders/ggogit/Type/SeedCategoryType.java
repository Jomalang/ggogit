package Recorders.ggogit.Type;

import lombok.Getter;

@Getter
public enum SeedCategoryType {
    BOOK("book", "도서", 1),
    IDEA("idea", "생각", 2),
    PHRASE("phrase", "문장", 3),
    STUDY("study", "공부", 4),
    YOUTUBE("youtube", "유튜브", 5);

    private final String value;
    private final String description;
    private final Integer num;

    SeedCategoryType(String value, String description, Integer num) {
        this.value = value;
        this.description = description;
        this.num = num;
    }

    public static boolean contains(String value) {
        for (SeedCategoryType type : SeedCategoryType.values()) {
            if (type.value.equals(value))
                return true;
        }
        return false;
    }

    public static boolean isBook(Integer value) {
        return SeedCategoryType.BOOK.num.equals(value);
    }

    public static SeedCategoryType of(String value) {
        for (SeedCategoryType type : SeedCategoryType.values()) {
            if (type.value.equals(value))
                return type;
        }
        throw new IllegalArgumentException("SeedCategoryType의 value 인자를 잘못 받았습니다.");
    }

    public static SeedCategoryType of(Integer num) {
        for (SeedCategoryType type : SeedCategoryType.values()) {
            if (type.num.equals(num))
                return type;
        }
        throw new IllegalArgumentException("SeedCategoryType의 num 인자를 잘못 받았습니다.");
    }
}
