package Recorders.ggogit.domain.leaf.structure;

import lombok.Getter;

@Getter
public enum LeafDirectionType {
    INIT("INIT", "초기화", 0),
    START_DOWN("START_DOWN", "아래로 시작", 1),
    START_RIGHT("START_RIGHT", "오른쪽으로 시작", 2),
    START_SIDE("START_SIDE", "양쪽으로 시작", 3),
    START_LEFT("START_LEFT", "왼쪽으로 시작", 4),
    DOWN("DOWN", "아래로", 5),
    RIGHT("RIGHT", "오른쪽으로", 6),
    SIDE("SIDE", "양쪽으로", 7),
    LEFT("LEFT", "왼쪽으로", 8),
    END_RIGHT("END_RIGHT", "오른쪽으로 끝", 9),
    END_LEFT("END_LEFT", "왼쪽으로 끝", 10),
    END_UP("END_UP", "위로 끝", 11);

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
