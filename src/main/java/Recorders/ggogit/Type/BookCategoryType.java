package Recorders.ggogit.Type;

import lombok.Getter;

@Getter
public enum BookCategoryType {
    FICTION("fiction", "소설"),
    NON_FICTION("nonFiction", "논픽션"),
    MYSTERY("mystery", "미스터리"),
    SCIENCE_FICTION("scienceFiction", "과학 소설"),
    BIOGRAPHY("biography", "전기");

    private final String value;
    private final String description;

    BookCategoryType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static BookCategoryType of(String value) {
        for (BookCategoryType type : BookCategoryType.values()) {
            if (type.value.equals(value))
                return type;
        }
        throw new IllegalArgumentException("BookCategoryType의 value 인자를 잘못 받았습니다.");
    }
}