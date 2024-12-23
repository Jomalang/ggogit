
package io.ggogit.ggogit.type;

import lombok.Getter;

@Getter
public enum AladinBookSearchType {

    DEFAULT("keyword", "제목+저자", "Keyword"),
    TITLE("title", "제목", "Title"),
    AUTHOR("author", "저자", "Author"),
    PUBLISHER("publisher", "출판사", "Publisher");

    private final String value;
    private final String description;
    private final String apiValue;

    AladinBookSearchType(String value, String description, String apiValue) {
        this.value = value;
        this.description = description;
        this.apiValue = apiValue;
    }

    public static AladinBookSearchType of(String value) {
        for (AladinBookSearchType type : AladinBookSearchType.values()) {
            if (type.value.equals(value)) return type;
        }
        throw new IllegalArgumentException("AladinBookSearchType의 value 인자를 잘못 받았습니다.");
    }

    // 존재 여부 확인
    public static boolean isExist(String value) {
        for (AladinBookSearchType type : AladinBookSearchType.values()) {
            if (type.value.equals(value)) return true;
        }
        return false;
    }
}
