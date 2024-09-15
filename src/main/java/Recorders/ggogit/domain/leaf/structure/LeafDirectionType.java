package Recorders.ggogit.domain.leaf.structure;

import lombok.Getter;

@Getter
public enum LeafDirectionType {
    INIT("init", "생성후", 0),
    START("start", "시작", 1),
    RIGHT("right", "오른쪽", 2),
    SIDE("side", "양쪽", 3),
    DOWN("down", "아래", 4),
    LEFT("left", "왼쪽", 5),
    END("end", "끝", 6);

    private final String value;
    private final String description;
    private final Integer num;

    LeafDirectionType(String value, String description, Integer num) {
        this.value = value;
        this.description = description;
        this.num = num;
    }

    public static LeafDirectionType of(String value) {
        for (LeafDirectionType type : LeafDirectionType.values()) {
            if (type.value.equals(value))
                return type;
        }
        throw new IllegalArgumentException("LeafDirectionType의 value 인자를 잘못 받았습니다.");
    }
}
