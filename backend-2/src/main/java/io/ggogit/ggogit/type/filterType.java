package io.ggogit.ggogit.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
public enum filterType {
    ASC(0L, "ASC", "오름차순"),
    DESC(1L, "DESC", "내림차순"),
    UPDATE_TIME(10L, "UPDATE_TIME", "최근 수정 기준"),
    TITLE(11L, "TITLE", "제목 기준"),
    LEAF_CNT_(12L, "LEAF_CNT", "리프 수 기준"),
    VIEW_COUNT(13L, "VIEW_COUNT", "조회 수 기준"),
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

    // number로 FilterType을 찾는 메서드
    public static String findNameByNum(Long number) {
        for (filterType filter : filterType.values()) {
            if (filter.getNumber().equals(number)) {
                return filter.getValue();
            }
        }
        throw new IllegalArgumentException("No filterType with number " + number);
    }
    public static Sort createSort(filterType filter, filterType sort) {
        switch (filter) {
            case UPDATE_TIME:
                if (sort == ASC)
                    return Sort.by("updateTime").ascending();
                return Sort.by("updateTime").descending();
            case TITLE:
                if (sort == ASC)
                    return Sort.by("title").ascending();
                return Sort.by("title").descending();
            case LEAF_CNT_:
                if (sort == ASC)
                    return Sort.by("leafCount").ascending();
                return Sort.by("leafCount").descending();
            case VIEW_COUNT:
                if (sort == ASC)
                    return Sort.by("viewCount").ascending();
                return Sort.by("viewCount").descending();
            default:
                if (sort == ASC)
                    return Sort.by("updateTime").ascending();
                return Sort.by("updateTime").descending();
        }
    }

}
