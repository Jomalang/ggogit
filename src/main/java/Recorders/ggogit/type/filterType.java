package Recorders.ggogit.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum filterType {
    ASC_SORT(0L, "asc", "오름차순"),
    DESC_SORT(1L, "desc", "내림차순"),
    UPDATE_STANDARD(2L, "UPDATE_STANDARD", "최근 수정 기준"),
    TITLE_STANDARD(3L, "TITLE_STANDARD", "제목 기준"),
    LEAF_CNT_STANDARD(4L, "LEAF_CNT_STANDARD", "리프 수 기준"),
    VIEW_CNT_STANDARD(5L, "VIEW_CNT_STANDARD", "조회 수 기준"),
    ;
    private final Long number;
    private final String value;
    private final String description;

}
