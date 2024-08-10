package Recorders.ggogit.entity;

import lombok.Getter;

@Getter
public enum TreeType {
    BOOK("BOOK", "도서"),
    INSPIRATION("INSPIRATION", "영감"),
    YOUTUBE("YOUTUBE", "유튜브"),
    ETC("ETC", "기타");

    private final String value;
    private final String description;

    TreeType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static TreeType of(String value) {
        for (TreeType type : TreeType.values()) {
            if (type.value.equals(value))
                return type;
        }
        throw new IllegalArgumentException("TreeType의 value 인자를 잘못 받았습니다.");
    }
}
