package Recorders.ggogit.type;

import lombok.Getter;

@Getter
public enum SearchType {
    NONE("none", "없음", 0L),
    TITLE("title", "제목", 1L),
    CONTENT("content", "내용", 2L),
    ALL("all", "제목 + 내용", 3L);

    private final String value;
    private final String description;
    private final Long num;

    SearchType(String value, String description, Long num) {
        this.value = value;
        this.description = description;
        this.num = num;
    }

    public static SearchType of(String value) {
        for (SearchType type : SearchType.values()) {
            if (type.value.equals(value))
                return type;
        }
        throw new IllegalArgumentException("SearchType의 value 인자를 잘못 받았습니다.");
    }
}
