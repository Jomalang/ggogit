package Recorders.ggogit.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum filterType {
    ASC(0L, "asc", "오름차순"),
    DESC(1L, "desc", "내림차순"),
    UPDATE_TIME(10L, "UPDATE_STANDARD", "최근 수정 기준"),
    TITLE(11L, "TITLE_STANDARD", "제목 기준"),
    LEAF_CNT_STANDARD(12L, "LEAF_CNT_STANDARD", "리프 수 기준"),
    VIEW_COUNT(13L, "VIEW_CNT_STANDARD", "조회 수 기준"),
    ;
    private final Long number;
    private final String value;
    private final String description;


    // number로 FilterType을 찾는 메서드
    public static filterType fromNumber(Long number) {
        for (filterType filter : filterType.values()) {
            if (filter.getNumber().equals(number)) {
                return filter;
            }
        }
        throw new IllegalArgumentException("No filterType with number " + number);
    }
    // number로 FilterType을 찾는 메서드
    public static filterType fromValue(String value) {
        for (filterType filter : filterType.values()) {
            if (filter.getValue().equals(value)) {
                return filter;
            }
        }
        throw new IllegalArgumentException("No filterType with value " + value);
    }
    // number로 FilterType을 찾는 메서드
    public static filterType fromDescription(String description) {
        for (filterType filter : filterType.values()) {
            if (filter.getDescription().equals(description)) {
                return filter;
            }
        }
        throw new IllegalArgumentException("No filterType with description " + description);
    }

}
