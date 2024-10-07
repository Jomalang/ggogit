package Recorders.ggogit.type;

public enum SortType {
    NONE("none", "없음", 0L),
    RECENT("recent", "최신순", 1L),
    LIKE("like", "좋아요 수", 2L),
    COMMENT("comment", "댓글 수", 3L),
    VIEW("view", "조회 수", 4L);

    private final String value;
    private final String description;
    private final Long num;

    SortType(String value, String description, Long num) {
        this.value = value;
        this.description = description;
        this.num = num;
    }
}